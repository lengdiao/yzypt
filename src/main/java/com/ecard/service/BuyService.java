package com.ecard.service;

import com.ecard.pojo.Response;
import com.ecard.pojo.ResponseHasData;
import com.ecard.pojo.queryResult.MallOrderQr;
import com.ecard.pojo.queryResult.MallOrderQrr;
import com.ecard.pojo.queryResult.UserQuestionQr;

public interface BuyService {
    Response insertGoods(Long goodsNo, Long number, String remark, int platform);

    Response insertQuestion(UserQuestionQr userQuestion);

    void wxpay(String mallNo, Long packetId) throws Exception;

    Response updateMallOrder(MallOrderQr mallOrderQr);

    ResponseHasData saveMrPicture(Long mallNo);

    Response selectQuestion(Long goodsNo);

    Response isNullQuestion(Long goodsNo);

    Response notice(Long mallNo);

    Response updateShippingStatus(Long mallNo, int shippingStatus, Long address);

    Response createSJMallOrder(MallOrderQrr mallOrder);

    Response isRegister();
}
