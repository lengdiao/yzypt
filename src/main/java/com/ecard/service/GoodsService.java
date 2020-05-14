package com.ecard.service;

import com.ecard.entity.Goods;
import com.ecard.pojo.Response;
import com.ecard.pojo.queryResult.DrugSetGoodsQr;
import com.ecard.pojo.queryResult.GoodsQr;
import com.ecard.pojo.queryResult.QuestionQr;

import java.math.BigDecimal;
import java.util.List;

public interface GoodsService {

    Response insertTemplate(List<QuestionQr> template);

    Response updateTemplate(List<QuestionQr> template);

    Response insert(GoodsQr goodsQr);

    Response update(GoodsQr goodsQr);

    Response selectQuestion(Long goodsNo);

}
