package com.macro.mall.service.impl;

import com.alipay.api.AlipayResponse;
import com.alipay.api.domain.TradeFundBill;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.macro.mall.alipay.config.Configs;
import com.macro.mall.alipay.model.ExtendParams;
import com.macro.mall.alipay.model.GoodsDetail;
import com.macro.mall.alipay.model.builder.AlipayTradePrecreateRequestBuilder;
import com.macro.mall.alipay.model.builder.AlipayTradeQueryRequestBuilder;
import com.macro.mall.alipay.model.result.AlipayF2FPrecreateResult;
import com.macro.mall.alipay.model.result.AlipayF2FQueryResult;
import com.macro.mall.alipay.service.AlipayTradeService;
import com.macro.mall.alipay.service.impl.AlipayTradeServiceImpl;
import com.macro.mall.alipay.utils.Utils;
import com.macro.mall.alipay.utils.ZxingUtils;
import com.macro.mall.common.exception.BusinessException;
import com.macro.mall.config.properties.TradePayProp;
import com.macro.mall.domain.OrderDetail;
import com.macro.mall.service.TradeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * ,;,,;
 * ,;;'(    社
 * __      ,;;' ' \   会
 * /'  '\'~~'~' \ /'\.)  主
 * ,;(      )    /  |.     义
 * ,;' \    /-.,,(   ) \    码
 * ) /       ) / )|    农
 * ||        ||  \)
 * (_\       (_\
 *
 * @author ：杨过
 * @date ：Created in 2020/3/26
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description:
 **/
@Slf4j
@Service
public class TradeServiceImpl implements TradeService {

    @Autowired
    private TradePayProp tradePayProp;

    // 支付宝当面付2.0服务
    private static AlipayTradeService tradeService;

    @PostConstruct
    private void init(){
        /** 一定要在创建AlipayTradeService之前调用Configs.init()设置默认参数
         *  Configs会读取classpath下的zfbinfo.properties文件配置信息，如果找不到该文件则确认该文件是否在classpath目录
         */
        Configs.init("zfbinfo.properties");

        /** 使用Configs提供的默认参数
         *  AlipayTradeService可以使用单例或者为静态成员对象，不需要反复new
         */
        tradeService = new AlipayTradeServiceImpl.ClientBuilder().build();
    }


    @Override
    public String tradeQrCode(OrderDetail orderDetail) throws BusinessException {
        //支付二维码的访问路径
        String qrCodePath = null;

        // (必填) 商户网站订单系统中唯一订单号，64个字符以内，只能包含字母、数字、下划线，
        // 需保证商户系统端不能重复，建议通过数据库sequence生成，
        String outTradeNo = "" + System.currentTimeMillis()
                + (long) (Math.random() * 10000000L);

        // (必填) 订单标题，粗略描述用户的支付目的。如“xxx品牌xxx门店当面付扫码消费”
        String subject = orderDetail.getItemList().get(0).getProductName();

        // (必填) 订单总金额，单位为元，不能超过1亿元
        // 如果同时传入了【打折金额】,【不可打折金额】,【订单总金额】三者,则必须满足如下条件:【订单总金额】=【打折金额】+【不可打折金额】
        String totalAmount = orderDetail.getPayAmount().toString();

        // (可选) 订单不可打折金额，可以配合商家平台配置折扣活动，如果酒水不参与打折，则将对应金额填写至此字段
        // 如果该值未传入,但传入了【订单总金额】,【打折金额】,则该值默认为【订单总金额】-【打折金额】
        String undiscountableAmount = "0";

        // 卖家支付宝账号ID，用于支持一个签约账号下支持打款到不同的收款账号，(打款到sellerId对应的支付宝账号)
        // 如果该字段为空，则默认为与支付宝签约的商户的PID，也就是appid对应的PID
        String sellerId = "";

        // 订单描述，可以对交易或商品进行一个详细地描述，比如填写"购买商品2件共15.00元"
        String body = String.format("购买商品%s件共%s元",orderDetail.getItemList().size(),orderDetail.getPayAmount().toString());

        // 商户操作员编号，添加此参数可以为商户操作员做销售统计
        String operatorId = "yangguo";

        // (必填) 商户门店编号，通过门店号和商家后台可以配置精准到门店的折扣信息，详询支付宝技术支持
        String storeId = "tuling-yangguo";

        // 业务扩展参数，目前可添加由支付宝分配的系统商编号(通过setSysServiceProviderId方法)，详情请咨询支付宝技术支持
        ExtendParams extendParams = new ExtendParams();
        extendParams.setSysServiceProviderId("2088100200300400500");

        // 支付超时，定义为120分钟
        String timeoutExpress = "120m";

        // 商品明细列表，需填写购买商品详细信息，
        List<GoodsDetail> goodsDetailList = new ArrayList<GoodsDetail>();

        orderDetail.getItemList().stream().forEach((item)->{
            // 创建一个商品信息，参数含义分别为商品id（使用国标）、名称、单价（单位为分）、数量，如果需要添加商品类别，详见GoodsDetail
            GoodsDetail goods1 = GoodsDetail.newInstance(
                    item.getOrderSn(),
                    item.getProductName(),
                    item.getProductPrice().multiply(new BigDecimal(100)).longValue(),
                    item.getProductQuantity());
            // 创建好一个商品后添加至商品明细列表
            goodsDetailList.add(goods1);
        });

        // 创建扫码支付请求builder，设置请求参数
        AlipayTradePrecreateRequestBuilder builder = new AlipayTradePrecreateRequestBuilder()
                .setSubject(subject).setTotalAmount(totalAmount).setOutTradeNo(outTradeNo)
                .setUndiscountableAmount(undiscountableAmount).setSellerId(sellerId).setBody(body)
                .setOperatorId(operatorId).setStoreId(storeId).setExtendParams(extendParams)
                .setTimeoutExpress(timeoutExpress)
                .setNotifyUrl(tradePayProp.getPaySuccessCallBack())//支付宝服务器主动通知商户服务器里指定的页面http路径,根据需要设置
                .setGoodsDetailList(goodsDetailList);

        AlipayF2FPrecreateResult result = tradeService.tradePrecreate(builder);
        switch (result.getTradeStatus()) {
            case SUCCESS:
                log.info("支付宝预下单成功: )");

                AlipayTradePrecreateResponse response = result.getResponse();
                dumpResponse(response);

                // 需要修改为运行机器上的路径
                String filePath = String.format(tradePayProp.getAliPayPath()+"/qr-%s.png",
                        response.getOutTradeNo());
                String storePath = tradePayProp.getStorePath()+filePath;
                log.info("storePath:" + storePath);
                //创建二维码
                ZxingUtils.getQRCodeImge(response.getQrCode(), 256, storePath);
                qrCodePath = tradePayProp.getHttpBasePath()+filePath;
                break;

            case FAILED:
                log.error("支付宝预下单失败!!!");
                break;

            case UNKNOWN:
                log.error("系统异常，预下单状态未知!!!");
                break;

            default:
                log.error("不支持的交易状态，交易返回异常!!!");
                break;
        }
        return qrCodePath;
    }

    /**
     * 查询订单的支付状态
     * @param orderSn
     * @return
     * @throws Exception
     */
    @Override
    public String alipayTradeQuery(String orderSn) throws Exception {
        String responseResult = null;
        // (必填) 商户订单号，通过此商户订单号查询当面付的交易状态
        String outTradeNo = orderSn;

        // 创建查询请求builder，设置请求参数
        AlipayTradeQueryRequestBuilder builder = new AlipayTradeQueryRequestBuilder()
                .setOutTradeNo(outTradeNo);

        AlipayF2FQueryResult result = tradeService.queryTradeResult(builder);
        switch (result.getTradeStatus()) {
            case SUCCESS:
                log.info("查询返回该订单支付成功: )");
                responseResult = "查询返回该订单支付成功: )";
                AlipayTradeQueryResponse response = result.getResponse();
                dumpResponse(response);

                log.info(response.getTradeStatus());
                if (Utils.isListNotEmpty(response.getFundBillList())) {
                    for (TradeFundBill bill : response.getFundBillList()) {
                        log.info(bill.getFundChannel() + ":" + bill.getAmount());
                    }
                }
                break;

            case FAILED:
                responseResult = "查询返回该订单支付失败或被关闭!!!";
                log.error("查询返回该订单支付失败或被关闭!!!");
                break;

            case UNKNOWN:
                responseResult = "系统异常，订单支付状态未知!!!";
                log.error("系统异常，订单支付状态未知!!!");
                break;

            default:
                log.error("不支持的交易状态，交易返回异常!!!");
                break;
        }

        return responseResult;
    }

    // 简单打印应答
    private void dumpResponse(AlipayResponse response) {
        if (response != null) {
            log.info(String.format("code:%s, msg:%s", response.getCode(), response.getMsg()));
            if (StringUtils.isNotEmpty(response.getSubCode())) {
                log.info(String.format("subCode:%s, subMsg:%s", response.getSubCode(),
                        response.getSubMsg()));
            }
            log.info("body:" + response.getBody());
        }
    }

}
