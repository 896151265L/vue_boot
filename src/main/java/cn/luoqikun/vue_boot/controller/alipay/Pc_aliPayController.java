package cn.luoqikun.vue_boot.controller.alipay;

import cn.luoqikun.vue_boot.config.pay_config.ConfigProperty;
import cn.luoqikun.vue_boot.entity.dto.req.dtoAliPayReqInfo;
import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Author: lqk
 * @Date: 2019/2/28 16:30
 * @Version: 1.0  PC端支付  沙箱环境APPID 密钥
 */
@RestController
@RequestMapping("aliPay")
public class Pc_aliPayController {


    @Autowired
    private ConfigProperty configProperty;

    //PC端支付下单
    @RequestMapping("pcPay")
    public void doPost(HttpServletRequest req, HttpServletResponse resp,dtoAliPayReqInfo dtoReq) throws ServletException, IOException {
        String url = configProperty.getUrl();
        String appId = configProperty.getAppId();
        String aliPayPublicKey = configProperty.getAliPayPublicKey();
        String appPrivateKey = configProperty.getAppPrivateKey();
        String charset = configProperty.getCharset();
        String format = configProperty.getFormat();
        String signType = configProperty.getSignType();

        //在SDK调用前需要进行初始化
        DefaultAlipayClient alipayClient = new DefaultAlipayClient(url, appId, appPrivateKey, format, charset, aliPayPublicKey, signType);
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();              //创建API对应的request
        alipayRequest.setReturnUrl("http://170abfb0.nat123.cc/views/payNotificationSync.html"); //同步通知(内网穿透)
        alipayRequest.setNotifyUrl("http://170abfb0.nat123.cc/aliPay/payNotifyUrl");            //异步通知(内网穿透)

        dtoReq.setOut_trade_no("20882021764312133");  //订单号
        dtoReq.setSubject("小米9 64G");               //订单标题
        dtoReq.setTotal_amount("0.02");               //订单金额

        alipayRequest.setBizContent(JSON.toJSONString(dtoReq));

        String form = "";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        resp.setContentType("text/html;charset=" + charset);
        resp.getWriter().write(form);//直接将完整的表单html输出到页面
        resp.getWriter().flush();
        resp.getWriter().close();
    }

    //异步通知地址  必须POST方式
    @SneakyThrows
    @PostMapping("payNotifyUrl")
    public String payNotifyUrl(HttpServletRequest request,HttpServletResponse response){

        request.setCharacterEncoding("UTF-8"); //解决乱码
        System.out.println("异步通知。。。");
        HashMap<String, String> mapParams = new HashMap<>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            mapParams.put(name, valueStr);
        }
        //使用支付宝sdk验签
        boolean signVerified = AlipaySignature.rsaCheckV1(mapParams, configProperty.getAliPayPublicKey() , configProperty.getCharset() , configProperty.getSignType());
        /**
         * 通知成功返回success
         * 否则返回failure 支付宝会间断的向我发起通知
         */
        if(signVerified) {
            //TODO...处理业务自己的逻辑代码 修改订单状态
            System.out.println("success");
           return "success";
        }else {
            System.out.println("failure");
            return "failure";
        }
    }

    //手机网页支付
    @RequestMapping("appPay")
    public void doPost(HttpServletRequest httpRequest,
                       HttpServletResponse httpResponse) throws ServletException, IOException {
        AlipayClient alipayClient = new DefaultAlipayClient(configProperty.getUrl(), configProperty.getAppId(), configProperty.getAppPrivateKey(), configProperty.getFormat(), configProperty.getCharset(), configProperty.getAliPayPublicKey(), configProperty.getSignType()); //获得初始化的AlipayClient
        AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();//创建API对应的request
        alipayRequest.setReturnUrl("http://domain.com/CallBack/return_url.jsp");
        alipayRequest.setNotifyUrl("http://domain.com/CallBack/notify_url.jsp");//在公共参数中设置回跳和通知地址
        alipayRequest.setBizContent("{" +
                " \"out_trade_no\":\"20150320010101003\"," +
                " \"total_amount\":\"88.88\"," +
                " \"subject\":\"Iphone6 16G\"," +
                " \"product_code\":\"QUICK_WAP_PAY\"" +
                " }");//填充业务参数
        String form = "";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        httpResponse.setContentType("text/html;charset=" + configProperty.getCharset());
        httpResponse.getWriter().write(form);//直接将完整的表单html输出到页面
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }

}
