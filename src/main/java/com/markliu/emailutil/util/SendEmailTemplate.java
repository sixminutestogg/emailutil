package com.markliu.emailutil.util;

import java.util.List;

import javax.mail.Session;

import com.markliu.emailutil.entities.EmailInfo;
import com.markliu.emailutil.entities.EmailServerInfo;
import com.markliu.emailutil.entities.ReadEmailInfo;
import com.markliu.emailutil.service.EmailServerService;

/**
 * 
 * 
 * @auther SunnyMarkLiu
 * @time Apr 12, 2016 5:43:46 PM
 */
public class SendEmailTemplate {

	/**
	 * 发送邮件的模板方法
	 * @param emailServerInfo
	 * @param email
	 * @return
	 */
	public static boolean sendEmail(EmailServerInfo emailServerInfo, EmailInfo email) {
		EmailServerService emailServerService = new EmailServerService();
		// 如果登陆成功，则进行发送邮件
		
		Session sendMailSession = emailServerService.loginEmailServer(emailServerInfo, false);
		if (sendMailSession != null) {
			System.out.println(emailServerInfo.getMailServerHost() + " 登陆成功！");
			System.out.println("正在发送邮件...");
			boolean result = emailServerService.sendEmail(sendMailSession, emailServerInfo, email);
			if (result) {
				System.out.println("发送成功！");
			} else {
				System.out.println("发送失败！");
			}
			return result;
		} else {
			System.out.println(emailServerInfo.getMailServerHost() + " 登陆失败！");
			return false;
		}
	}
	
	public static List<ReadEmailInfo> readAllEmailInfos(EmailServerInfo emailServerInfo) {
		EmailServerService emailServerService = new EmailServerService();
		// 如果登陆成功，则进行发送邮件
		
		Session sendMailSession = emailServerService.loginEmailServer(emailServerInfo, true);
		if (sendMailSession != null) {
			System.out.println(emailServerInfo.getMailServerHost() + " 登陆成功！");
			System.out.println("正在读取邮件...");
			List<ReadEmailInfo> emailInfos = emailServerService.readAllEmailInfos(sendMailSession, emailServerInfo);
			return emailInfos;
		} else {
			System.out.println(emailServerInfo.getMailServerHost() + " 登陆失败！");
			return null;
		}
	}
}
