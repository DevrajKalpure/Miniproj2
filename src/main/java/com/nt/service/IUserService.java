package com.nt.service;

import com.nt.binding.LoginForm;
import com.nt.binding.SignUpForm;
import com.nt.binding.UnLockForm;

public interface IUserService {
	
	public boolean signUp(SignUpForm signUpForm) throws Exception;
	public String unlock(UnLockForm unlockForm);
	public String login(LoginForm loginForm);
	public boolean forgetPwd(String email);
	//boolean unlock(UnLockForm unlockForm);
}
