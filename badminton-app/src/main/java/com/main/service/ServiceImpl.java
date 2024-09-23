package com.main.service;

import java.util.Random;
import java.util.UUID;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.main.entity.Users;
import com.main.event.SendVerificationLinkEvent;
import com.main.model.ForgotPasswordModel;
import com.main.model.LoginModel;
import com.main.model.LoginResponseModel;
import com.main.model.PasswordResetModel;
import com.main.model.SignUpModel;
import com.main.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Service
@Data
@RequiredArgsConstructor
public class ServiceImpl {
	
	private final UserRepository userRepository;
	private final ApplicationEventPublisher applicationEventPublisher;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final TokenGenerationService tokenGenerationService;
	
	public int handleSignUpRequest(SignUpModel signUpModel,HttpServletRequest request) {
		System.out.println("value = "+signUpModel.getEmailId());
		Users user = userRepository.findByEmailId(signUpModel.getEmailId());
		if(user == null) {
			String verificationToken = generateVerificationToken();
			Users newUser = Users.builder()
					.userCode(10)
					.userName(signUpModel.getUserName())
					.emailId(signUpModel.getEmailId())
					.password(passwordEncoder.encode(signUpModel.getPassword()))
					.verificationToken(verificationToken)
					.isVerified(false)
					.build();
			//sendToken();
			System.out.println(verificationToken);
			userRepository.save(newUser);
			
			// verification Url
			String verificationUrl = generateVerificationUrl(verificationToken,request);
			// sendVerification email 
			applicationEventPublisher.publishEvent(new SendVerificationLinkEvent(this, signUpModel.getEmailId(), verificationUrl));
			
			return 100;
		}
		else {
			return 101;
		}
//		private int userCode;
//		private String userName;
//		private String emailId; 
//		private String password;
//		private String jwtToken;
//		private boolean isJwtTokenValid;
//		private boolean isVerified;
//		private String verificationToken;
	}
	
	public int handleEmailVerificationRequest(String verificationToken) { // add expiration
		Users user = userRepository.findByVerificationToken(verificationToken);
		if(user != null) {
			user.setVerified(true);
			userRepository.save(user);
			return 100;
		}
		else {
			return 101;
		}
	}
	
	/**
	 * 
	 * @param loginModel
	 * @return
	 */

	public LoginResponseModel handleLoginRequest(LoginModel loginModel) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				loginModel.getEmailId(),loginModel.getPassword()));
		//Need to add exception handling
		var user = userRepository.getByEmailId(loginModel.getEmailId());
		var token = tokenGenerationService.generateToken(user);

		LoginResponseModel response = LoginResponseModel.builder()
				.jwtToken(token).build();
		return response;
		
	}



	public int handleForgotPasswordRequest(ForgotPasswordModel forgotPasswordModel,HttpServletRequest request) {
		Users user = userRepository.findByEmailId(forgotPasswordModel.getEmailId());
		if(user == null)
			return 101;
		long OTP = generateResetOTP();
		user.setResetPasswordotp(OTP);
		userRepository.save(user);
		// sendVerification email 
		System.out.println(OTP);
		return 100;
	}
	
	private String generateVerificationToken() {
		return  UUID.randomUUID().toString();
	}

	
	private String generateVerificationUrl(String verificationToken, HttpServletRequest request) {
		return ("http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/verificationLink?token="+verificationToken);
	}
	
	private long generateResetOTP() {
		Random random = new Random();
		long otp = random.nextInt(10000, 1000000);
		return otp;
	}

	public int handlePasswordResetRequest(PasswordResetModel passwordResetModel) {
		Users user = userRepository.findByResetPasswordotp(passwordResetModel.getOtp()); // find get user details
		if(user == null)
			return 101;
		// check for existing password;
		user.setPassword(passwordEncoder.encode(passwordResetModel.getNewPassword()));
		user.setResetPasswordotp(null);
		userRepository.save(user);
			return 100;
	}


//	public int handlePassowordResetRequest(String passwordResetToken) {
//		Users user = userRepository.findByResetPasswordToken(passwordResetToken);
//		if(user == null)
//			return 101;
//		else
//			return 100;
//		
//	}
}
