package com.main.event.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.main.event.SendVerificationLinkEvent;

@Component
public class SendVerificationEmailListener implements ApplicationListener<SendVerificationLinkEvent>{

	@Override
	public void onApplicationEvent(SendVerificationLinkEvent event) {
		System.out.println("abvv"+event.getUrl());
		
		// email sending code comes here;
	}
	
}
