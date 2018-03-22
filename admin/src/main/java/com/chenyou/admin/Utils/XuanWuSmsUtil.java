package com.chenyou.admin.Utils;

import com.chenyou.admin.domain.GdTask;
import com.esms.MessageData;
import com.esms.PostMsg;
import com.esms.common.entity.Account;
import com.esms.common.entity.GsmsResponse;
import com.esms.common.entity.MTPack;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class XuanWuSmsUtil {

	/**
	 * 短信下发范例
	 *
	 */
	public  GsmsResponse doSendSms(String phoneNumber, String content) throws Exception {
		Account ac = new Account("zjcywl@zjcywl", "PSd72V6O");//
		PostMsg pm = new PostMsg();
		pm.getCmHost().setHost("211.147.239.62", 9080);// 设置网关的IP和port，用于发送信息
		pm.getWsHost().setHost("211.147.239.62", 9070);// 设置网关的
														// IP和port，用于获取账号信息、上行、状态报告等等
		MTPack pack = new MTPack();
		pack.setBatchID(UUID.randomUUID());
		// pack.setBatchName("短信测试批次");//短信批次名称（不输入自动生成）
		pack.setMsgType(MTPack.MsgType.SMS);
		pack.setBizType(0);
		pack.setDistinctFlag(false);
		ArrayList<MessageData> msgs = new ArrayList<MessageData>();

		/** 群发，多号码一内容 */
		pack.setSendType(MTPack.SendType.MASS);
		msgs.add(new MessageData(phoneNumber, content));
		pack.setMsgs(msgs);

		GsmsResponse resp = pm.post(ac, pack);
		return resp;
	}
	public  GsmsResponse doSendIms(String phoneNumber) throws Exception {
		Account ac = new Account("zjcywl@zjcywl", "PSd72V6O");//
		PostMsg pm = new PostMsg();
		pm.getCmHost().setHost("211.147.239.62", 9080);// 设置网关的IP和port，用于发送信息
		pm.getWsHost().setHost("211.147.239.62", 9070);// 设置网关的
		// IP和port，用于获取账号信息、上行、状态报告等等
		MTPack pack = new MTPack();
		pack.setBatchID(UUID.randomUUID());
		// pack.setBatchName("短信测试批次");//短信批次名称（不输入自动生成）
		pack.setMsgType(MTPack.MsgType.SMS);
		pack.setBizType(0);
		pack.setDistinctFlag(false);
		ArrayList<MessageData> msgs = new ArrayList<MessageData>();

		/** 群发，多号码一内容 */
		pack.setSendType(MTPack.SendType.MASS);
		msgs.add(new MessageData(phoneNumber, "恭喜，您申请的（POLO衫定制T恤（只发黑色））将在13点截止领奖，请即时领取，过时未领将取消资格并降低下次中奖概率"));
		pack.setMsgs(msgs);

		GsmsResponse resp = pm.post(ac, pack);
		return resp;
	}

	public  void doSendEmg(List<GdTask> list){
		if (list == null || list.size() == 0 ) {
			return;
		}
		for (GdTask gdTask : list) {
			try {
				StringBuffer content = new StringBuffer();
				content.append("恭喜，您申请的（");
				content.append(gdTask.getGoodsName());
				content.append("）将在13点截止领奖，请即时领取，过时未领将取消资格并降低下次中奖概率");
				doSendSms(gdTask.getUserAccount(),content.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		try {
			if ("18177416191".matches("[0-9]+")) {
				String sendContent = "恭喜，您申请的（单丛红茶）将在17点截止领奖，请即时领取，过时未领将取消资格并降低下次中奖概率";
//				sendContent = sendContent.replace("话费", "");
				XuanWuSmsUtil xuanWuSmsUtil = new XuanWuSmsUtil();
				System.out.println(xuanWuSmsUtil.doSendSms("18177416191", sendContent).toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
