package com.sw.service;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sw.dao.AdminDao;
import com.sw.entity.Admin;
import com.sw.exception.AdminCodeException;
import com.sw.exception.PasswordException;

@Service
public class LoginService implements Serializable {

	@Resource
	private AdminDao adminDao;
	
	/**
	 * 校验管理员账号和密码
	 * @param adminCode
	 * @param password
	 * @return 验证通过时返回管理员对象
	 */
	public Admin checkAdminCodeAndPwd(String adminCode,String password)
	throws AdminCodeException,PasswordException{
		Admin admin=adminDao.findByCode(adminCode);
		if(admin==null){
			throw  new AdminCodeException("账号错误");
		}else if(!admin.getPassword().equals(password)){
			throw new PasswordException("密码错误");
		}else{
			return admin;
		}
	}
}
