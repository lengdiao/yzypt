package com.ecard.service.serviceImpl;

import com.ecard.Constants;
import com.ecard.dao.DrInfoMapper;
import com.ecard.dao.MessageMapper;
import com.ecard.dao.PtInfoMapper;
import com.ecard.dao.PtOpenMapper;
import com.ecard.entity.DrInfo;
import com.ecard.entity.Message;
import com.ecard.entity.PtOpen;
import com.ecard.pojo.ResponseHasData;
import com.ecard.pojo.queryResult.DrInfoQr;
import com.ecard.pojo.queryResult.PtInfoQr;
import com.ecard.service.MessageService;
import com.ecard.utils.WeiXinUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageMapper messageMapper;
	@Autowired
	private PtOpenMapper ptOpenMapper;
	@Autowired
	private DrInfoMapper drInfoMapper;
	@Autowired
	private PtInfoMapper ptInfoMapper;

	@Transactional(rollbackFor=Exception.class)
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
				PtInfoQr ptInfoQr =  ptInfoMapper.selectByPtNo(msgone.getPtNo());
				msgone.setPtInfoQr(ptInfoQr);
			}
			response.setMsg("msg为list+查询成功");
			response.setStatus(Constants.STATUS_SUCCESS);
			return response;
		} catch (Exception e) {
			response.setMsg("查询失败");
			response.setStatus(Constants.STATUS_FAIL);
			e.printStackTrace();
			return response;
		}

	}
	@Transactional(rollbackFor=Exception.class)
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
			DrInfoQr drInfoQr = drInfoMapper.selectByDrNo(Long.valueOf(drNo));
			WeiXinUtils z=new WeiXinUtils();
			List<PtOpen> ptopen=ptOpenMapper.selectByPtNo(Long.valueOf(ptNo));
			if(ptopen==null||ptopen.equals("")) {}else {
				z.newmsg(ptopen.get(0).getOpenId(), messageContent,drInfoQr.getDrName());}
			response.setMsg("发送成功");
			response.setStatus(Constants.STATUS_SUCCESS);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			response.setMsg("发送失败");
			response.setStatus(Constants.STATUS_FAIL);
			return response;
		}
	}
	@Transactional(rollbackFor=Exception.class)
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
				DrInfoQr drInfoQr = drInfoMapper.selectByDrNo(msgone.getDrNo());
				msgone.setDrInfoQr(drInfoQr);
			}
			response.setMsg("msg为list+查询成功");
			response.setStatus(Constants.STATUS_SUCCESS);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			response.setMsg("查询失败");
			response.setStatus(Constants.STATUS_FAIL);
			return response;
		}

	}
	@Transactional(rollbackFor=Exception.class)
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
			response.setStatus(Constants.STATUS_SUCCESS);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			response.setMsg("发送失败");
			response.setStatus(Constants.STATUS_FAIL);
			return response;
		}
	}
}
