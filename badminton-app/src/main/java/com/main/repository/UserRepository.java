package com.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.main.entity.Users;
@Repository
public interface UserRepository extends JpaRepository<Users, Integer>{
	Users findByEmailId(String email);

	Users findByVerificationToken(String token);

	UserDetails getByEmailId(String emailId);

	Users findByResetPasswordotp(long otp);

//	Users findByResetPasswordotp(String passwordResetToken);
}
