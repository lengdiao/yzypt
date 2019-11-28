package com.ecard.service.serviceImpl;

import com.ecard.dao.MessageMapper;
import com.ecard.entity.Message;
import com.ecard.pojo.ResponseHasData;
import com.ecard.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageMapper messageMapper;

	public ResponseHasData selectMsgByDrNo(String drNo, String ptNo) {
		ResponseHasData response = new ResponseHasData();
		try {
			List<Message> msg = messageMapper.selectMsgByDrNo(Long.valueOf(drNo), Long.valueOf(ptNo));
			response.setData(msg);
			for (Message msgone : msg) {
				if (msgone.getMessageKind().equals("2")) {
					msgone.setReadStatus(1);
					messageMapper.updateByPrimaryKeySelective(msgone);
				}
			}
			response.setMsg("msg为list+查询成功");
			response.setStatus(0);
			return response;
		} catch (NumberFormatException e) {
			response.setMsg("查询失败");
			response.setStatus(1);
			return response;
		}

	}

	public ResponseHasData insertMsgByDrNo(String drNo, String ptNo, String messageContent) {
		ResponseHasData response = new ResponseHasData();
		try {
			Message msg = new Message();
			msg.setCreateTime(new Date());
			msg.setCreateUser(drNo);
			msg.setMessageContent(messageContent);
			msg.setDrNo(Long.valueOf(drNo));
			msg.setPtNo(Long.valueOf(ptNo));
			msg.setMessageKind("1");
			msg.setReadStatus(0);
			messageMapper.insertSelective(msg);
			response.setMsg("发送成功");
			response.setStatus(0);
			return response;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			response.setMsg("发送失败");
			response.setStatus(1);
			return response;
		}
	}

	public ResponseHasData selectMsgByPtNo(String drNo, String ptNo) {
		ResponseHasData response = new ResponseHasData();
		try {
			List<Message> msg = messageMapper.selectMsgByDrNo(Long.valueOf(drNo), Long.valueOf(ptNo));
			response.setData(msg);
			for (Message msgone : msg) {
				if (msgone.getMessageKind().equals("1")) {
					msgone.setReadStatus(1);
					messageMapper.updateByPrimaryKeySelective(msgone);
				}
			}
			response.setMsg("msg为list+查询成功");
			response.setStatus(0);
			return response;
		} catch (NumberFormatException e) {
			response.setMsg("查询失败");
			response.setStatus(1);
			return response;
		}

	}

	public ResponseHasData insertMsgByPtNo(String drNo, String ptNo, String messageContent) {
		ResponseHasData response = new ResponseHasData();
		try {
			Message msg = new Message();
			msg.setCreateTime(new Date());
			msg.setCreateUser(ptNo);
			msg.setMessageContent(messageContent);
			msg.setDrNo(Long.valueOf(drNo));
			msg.setPtNo(Long.valueOf(ptNo));
			msg.setMessageKind("2");
			msg.setReadStatus(0);
			messageMapper.insertSelective(msg);
			response.setMsg("发送成功");
			response.setStatus(0);
			return response;
		} catch (NumberFormatException e) {
			response.setMsg("发送失败");
			response.setStatus(1);
			return response;
		}
	}
}
