package com.nt.binding;

import lombok.Data;

@Data
public class UnLockForm {
	String tempPwd;
	String newPwd;
	String confirmPwd;
	String email;
}
