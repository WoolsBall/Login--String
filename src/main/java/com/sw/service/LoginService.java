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
	 * У�����Ա�˺ź�����
	 * @param adminCode
	 * @param password
	 * @return ��֤ͨ��ʱ���ع���Ա����
	 */
	public Admin checkAdminCodeAndPwd(String adminCode,String password)
	throws AdminCodeException,PasswordException{
		Admin admin=adminDao.findByCode(adminCode);
		if(admin==null){
			throw  new AdminCodeException("�˺Ŵ���");
		}else if(!admin.getPassword().equals(password)){
			throw new PasswordException("�������");
		}else{
			return admin;
		}
	}
}
