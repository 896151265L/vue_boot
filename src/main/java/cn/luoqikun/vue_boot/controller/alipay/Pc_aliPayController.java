package cn.luoqikun.vue_boot.controller.alipay;

import cn.luoqikun.vue_boot.config.pay_config.PcConfigProperty;
import cn.luoqikun.vue_boot.entity.dto.req.dtoAliPayReqInfo;
import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
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
    private PcConfigProperty pcConfigProperty;

    //支付下单
    @RequestMapping("pcPay")
    public void doPost(HttpServletRequest req, HttpServletResponse resp,dtoAliPayReqInfo dtoReq) throws ServletException, IOException {
        String url = pcConfigProperty.getUrl();
        String appId = pcConfigProperty.getAppId();
        String aliPayPublicKey = pcConfigProperty.getAliPayPublicKey();
        String appPrivateKey = pcConfigProperty.getAppPrivateKey();
        String charset = pcConfigProperty.getCharset();
        String format = pcConfigProperty.getFormat();
        String signType = pcConfigProperty.getSignType();

        //在SDK调用前需要进行初始化
        DefaultAlipayClient alipayClient = new DefaultAlipayClient(url, appId, appPrivateKey, format, charset, aliPayPublicKey, signType);
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();  //创建API对应的request
        alipayRequest.setReturnUrl("http://ibook.viphk.ngrok.org/views/payNotificationSync.html"); //同步通知
        alipayRequest.setNotifyUrl("http://ibook.viphk.ngrok.org/aliPay/payNotifyUrl");            //异步通知

        dtoReq.setOut_trade_no("2088202176431264");//订单号
        dtoReq.setSubject("小米9 64G"); //订单标题
        dtoReq.setTotal_amount("0.02"); //订单金额

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
        boolean signVerified = AlipaySignature.rsaCheckV1(mapParams, pcConfigProperty.getAliPayPublicKey() ,pcConfigProperty.getCharset() , pcConfigProperty.getSignType());
        /**
         * 通知成功返回success
         * 否则返回failure 支付宝会间断的向我发起通知
         */
        if(signVerified) {
            //TODO...处理业务自己的逻辑代码 修改顶大状态

           return "success";
        }else {
            return "failure";
        }
    }

}
