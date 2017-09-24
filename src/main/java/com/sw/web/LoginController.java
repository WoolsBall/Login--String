package com.sw.web;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sw.entity.Admin;
import com.sw.exception.AdminCodeException;
import com.sw.exception.PasswordException;
import com.sw.service.LoginService;

@Controller
@RequestMapping("/login")
public class LoginController implements Serializable {
	@Resource
	private LoginService loginService;
	
    @RequestMapping("/toLogin.do")
    public String toLogin() {
        return "main/login";
    }
    
    @RequestMapping("/toIndex.do")
    public String toIndex() {
        return "main/index";
    }
    
    @RequestMapping("/checkLogin.do")
    public String checkLogin(String adminCode, String password, 
            ModelMap model, HttpSession session) {
        try {
            Admin admin = 
                loginService.checkAdminCodeAndPwd(adminCode, password);
            session.setAttribute("admin", admin);
        } catch (AdminCodeException e) {
            model.addAttribute("message", e.getMessage());
            model.addAttribute("adminCode", adminCode);
            model.addAttribute("password", password);
            return "main/login";
        } catch (PasswordException e) {
            model.addAttribute("message", e.getMessage());
            model.addAttribute("adminCode", adminCode);
            model.addAttribute("password", password);
            return "main/login";
        }
        return "redirect:toIndex.do";
    }
}
