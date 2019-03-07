package cn.luoqikun.vue_boot.controller.alipay;

import cn.luoqikun.vue_boot.config.pay_config.PcConfigProperty;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: lqk
 * @Date: 2019/3/7 11:32
 * @Version: 1.0        退款接口
 */
@RestController
@RequestMapping("refund")
public class RefundController {

    @Autowired
    private PcConfigProperty pcConfigProperty;

    @SneakyThrows
    @RequestMapping("refund")
    public void refund(){
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do","app_id","your private_key","json","GBK","alipay_public_key");
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        request.setBizContent("{" +
                "    \"out_trade_no\":\"20150320010101001\"," +
                "    \"trade_no\":\"2014112611001004680073956707\"," +
                "    \"refund_amount\":200.12," +
                "    \"refund_reason\":\"正常退款\"," +
                "    \"out_request_no\":\"HZ01RF001\"," +
                "    \"operator_id\":\"OP001\"," +
                "    \"store_id\":\"NJ_S_001\"," +
                "    \"terminal_id\":\"NJ_T_001\"" +
                "  }");
        AlipayTradeRefundResponse response = alipayClient.execute(request);
        if(response.isSuccess()){
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
    }
}
