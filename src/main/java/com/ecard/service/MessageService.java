package com.ecard.service;

import com.ecard.pojo.ResponseHasData;

public interface MessageService {

    ResponseHasData selectMsgByPtNo(String drNo, String ptNo);

    ResponseHasData insertMsgByPtNo(String drNo, String ptNo, String messageContent);

    ResponseHasData selectMsgByDrNo(String drNo, String ptNo);

    ResponseHasData insertMsgByDrNo(String drNo, String ptNo, String messageContent);
}
