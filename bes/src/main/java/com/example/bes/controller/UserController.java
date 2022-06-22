package com.example.bes.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.bes.Entity.User;
import com.example.bes.Service.UserService;

@CrossOrigin
@RestController
public class UserController {    //用户控制器
	@Autowired //自动连接到UserService Bean
    private UserService uS;
	
	@RequestMapping(value = "/show")
	public String show() {
		return uS.show();
	}
	
	            //登录验证接口
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public  String login(String account, String password) {
		System.out.println(account);
		System.out.println(password);
		if(uS.get(account, password).size() < 1 || uS.get(account, password) == null) {
			return "账号或密码错误！";
		}else if(uS.get(account, password).get(0).getIdentity().equals("0")) {
			return "admin";
		}else if(uS.get(account, password).get(0).getIdentity().equals("1") || uS.get(account, password).get(0).getIdentity().equals("2") ||
				uS.get(account, password).get(0).getIdentity().equals("3")) {
			return "user";
		}
		return "账号或密码错误！";
	}
	
	@RequestMapping(value = "/login1",method = RequestMethod.POST)
	public  List<User> login1(String account, String password) {
		if(uS.get(account, password).size() > 0) {
			return uS.get(account, password);
		}
		return null;
	}
	
	             
	@RequestMapping(value = "/insertZhuce",method = RequestMethod.POST)
	public void insertZhuce(String account,String password,String name,String sex,
			String phone,String email,String city,String bankcard) {    //注册用户信息插入待验证用户表
		uS.insertZhuce(account, password, name, sex, phone, email, city, bankcard);
	}
	
	@CrossOrigin             //验证注册用户是否重复提交
	@RequestMapping(value = "/getZhuceUser",method = RequestMethod.POST)
	public  List<User> getZhuceUser(String account) {
		return uS.getZhuceUser(account);
	}
	
	
	@CrossOrigin             //获取待验证的注册用户列表
	@RequestMapping(value = "/getUser",method = RequestMethod.POST)
	public  List<User> getUser(String account,String password) {
		if(uS.get(account, password).get(0).getIdentity().equals("0")) {
			return uS.getUser();
		}
		return null;
	}
	
	@CrossOrigin            
	@RequestMapping(value = "/deleteUser",method = RequestMethod.POST)
	public void deleteUser(String account) {   //删除待验证表的允许登录的注册用户
   	 	uS.deleteUser(account);
   	 	System.out.println(account);
	}
	
	@CrossOrigin            
	@RequestMapping(value = "/insertUser",method = RequestMethod.POST)
	public void insertUser(String account,String password,String name,String sex,
			String phone,String email,String city,String bankcard) {    //允许登录的注册用户信息插入用户表
		uS.insertUser(account, password, name, sex, phone, email, city, bankcard,"1");
	}
	
	@CrossOrigin            
	@RequestMapping(value = "/personal",method = RequestMethod.POST)
	public List<User> getpersonnalinfo(String account,String password) {    //获取用户个人信息
		System.out.println(account);
		System.out.println(password);
		return uS.getuserinfo(account, password);
	}
	
	@CrossOrigin            
	@RequestMapping(value = "/updateInfo",method = RequestMethod.POST)
	public void updateInfo(String name,String phone,String email,String city,String bankcard,String account,String password) {  //用户更改个人信息
		uS.updateInfo(name, phone, email, city, bankcard, account, password);
	}
	
	@CrossOrigin            
	@RequestMapping(value = "/shopzhuce",method = RequestMethod.POST)
	public String shopzhuce(@RequestParam(value="licence",required=false) MultipartFile licence,
			@RequestParam(value="idcard",required=false) MultipartFile idcard,String account,String password) throws IllegalStateException, IOException {    //用户注册商家
		System.out.println(account);
		if(licence == null || idcard == null) {
			return "上传失败，请您重新上传！";
		}
		String allname = licence.getOriginalFilename();    //营业执照保存至文件夹
		String licenceName = account+"licence"+allname.substring(allname.lastIndexOf("."));
		licence.transferTo(new File("C:\\Users\\14505\\Pictures\\shop\\"+licenceName));
		
		String allname2 = idcard.getOriginalFilename();      //身份证照片保存至文件夹
		String cardName = account+"bankcard"+allname2.substring(allname2.lastIndexOf("."));
		idcard.transferTo(new File("C:\\Users\\14505\\Pictures\\shop\\"+cardName));
		
		uS.shopzhuce(account, password, licenceName, cardName, "2");
		return "您已申请注册，请等待管理员确认！";
	}
	            
	@RequestMapping(value = "/getshopzhuce",method = RequestMethod.POST)
	public List<User> getshopzhuce(String account,String password) {    //获取注册商家的用户信息
		if(this.login(account, password) == "admin") {
			return uS.getshopzhuce();
		}
		return null;
	}
	            
	@RequestMapping(value = "/allowshopzhuce",method = RequestMethod.POST)
	public void allowshopzhuce(String account) {  //用户更改个人信息
		uS.allowshopzhuce(account);;
	}
	
	@RequestMapping(value = "/getshoplevel",method = RequestMethod.POST)
	public List<User> getshoplevel(String account,String password) {  
		if(uS.get(account, password).size() > 0) {
			return uS.getshoplevel();
		}
		return null;
	}
	
	@RequestMapping(value = "/changelevel",method = RequestMethod.POST)
	public void changelevel(String account,String password,String uid,String level) {  
		if(uS.get(account, password).size() > 0) {
			uS.changelevel(uid, level);
		}
	}
	
	@RequestMapping(value = "/chongzhi",method = RequestMethod.POST)
	public void chongzhi(String account,String password,String remainder,String v) {  
		if(uS.get(account, password).size() > 0) {
			double a = Double.parseDouble(remainder) + Double.parseDouble(v);
			uS.chongzhi(account, String.valueOf(a));
		}
	}
}
