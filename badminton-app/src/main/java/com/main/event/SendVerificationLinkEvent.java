package com.main.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.Configuration;

import com.main.entity.Users;

import lombok.Data;
@Data
public class SendVerificationLinkEvent extends ApplicationEvent{
	
	private String emailId;
	private String url;
	public SendVerificationLinkEvent(Object source, String emailId,String url) {
		super(source);
		this.emailId = emailId;
		this.url = url;
		System.out.println("reacher");
	}

}
