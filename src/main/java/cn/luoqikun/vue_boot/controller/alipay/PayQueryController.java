package cn.luoqikun.vue_boot.controller.alipay;

import cn.luoqikun.vue_boot.config.pay_config.ConfigProperty;
import cn.luoqikun.vue_boot.entity.dto.req.dtoAliPayReqInfo;
import cn.luoqikun.vue_boot.utils.Context;
import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * @Author: lqk
 * @Date: 2019/3/8 14:28
 * @Version: 1.0
 */
@RestController
@RequestMapping("aliPayQuery")
public class PayQueryController {

    @Autowired
    private ConfigProperty configProperty;

    @SneakyThrows
    @RequestMapping("query")
    public void payQuery(){

        String url = configProperty.getUrl();
        String appId = configProperty.getAppId();
        String appPrivateKey = configProperty.getAppPrivateKey();
        String format = configProperty.getFormat();
        String charset = configProperty.getCharset();
        String aliPayPublicKey = configProperty.getAliPayPublicKey();
        String signType = configProperty.getSignType();


        AlipayClient alipayClient = new DefaultAlipayClient(url,appId,appPrivateKey,format,charset,aliPayPublicKey,signType);
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();

        dtoAliPayReqInfo dtoAliPayReqInfo = new dtoAliPayReqInfo();
        dtoAliPayReqInfo.setOut_trade_no("20882021764312123");  //订单号和支付宝交易号二选一

        request.setBizContent(JSON.toJSONString(dtoAliPayReqInfo));

        AlipayTradeQueryResponse response = alipayClient.execute(request);

        if(response.getCode().equals("10000")){                                 //接口调用成功
            System.out.println(Context.TRADE_SUCCESS);
                //TODO... 返回订单状态
        } else {
            System.out.println("接口调用失败");

            HashMap<String, List<Integer>> map = new HashMap<String, List<Integer>>();
        }

    }

}
