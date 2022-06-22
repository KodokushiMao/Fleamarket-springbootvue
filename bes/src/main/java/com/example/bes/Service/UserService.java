package com.example.bes.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.bes.Dao.UserDao;
import com.example.bes.Entity.User;

@Service
public class UserService {
	@Autowired //连接到UserDao Bean
	private UserDao userDao;
	
	public List<User> get(String account,String password) {		//登录验证
		return userDao.get(account, password);
	}
	
	public void insertZhuce(String account,String password,String name,String sex,
			String phone,String email,String city,String bankcard) {    //注册用户信息插入待验证用户表
		userDao.insertZhuce(account, password, name, sex, phone, email, city, bankcard);
	}
	
	public List<User> getZhuceUser(String account){    //验证注册用户是否重复提交信息
		return userDao.getZhuceUser(account);
	}
	
	public List<User> getUser(){    //获取待验证的注册用户列表
		return userDao.getUser();
	}
	
    public void deleteUser(String account) {   //删除允许登录的注册用户
    	 userDao.deleteUser(account);
    };      
		
	public void insertUser(String account,String password,String name,String sex,
			String phone,String email,String city,String bankcard,String identity) {    //允许登录的注册用户信息插入用户表
		userDao.insertUser(account, password, name, sex, phone, email, city, bankcard,identity);
	}
	
	public List<User> getuserinfo(String account,String password){    //获取用户个人信息
		return userDao.getUserinfo(account, password);
	}
	
	public void updateInfo(String name,String phone,String email,String city,String bankcard,String account,String password) {  //用户更改个人信息
		userDao.updateInfo(name, phone, email, city, bankcard, account, password);
	}
	
	public void shopzhuce(String account,String password,String licence,String idcard,String identity) {  //用户注册商家
		userDao.shopzhuce(account, password, licence, idcard, identity);
	}
	
	public List<User> getshopzhuce(){    //获取注册商家的用户信息
		return userDao.getshopzhuce("2");
	}
	
	public List<User> getshoplevel(){    //获取商家的等级
		return userDao.getshoplevel("3");
	}
	
	public void allowshopzhuce(String account){   //允许注册商家的用户status更改为3
		userDao.allowshopzhuce("3", account);
	}
	 
	public void pay(String account,String money) { //用户下单后扣除相应的钱数
		userDao.pay(account, money);
	}

	public List<User> umoney(String account){ //获取用户的钱数
		return userDao.umoney(account);
	}

	public void grade(String account,String grade) { //更新用户积分
		userDao.grade(account, grade);
	}

	public void changelevel(String account,String level) { //更新商家等级
		userDao.changelevel(account, level);
	}

	public void chongzhi(String account,String money) { //充值
		userDao.chongzhi(account, money);
	}
	
	public String show() {
        return "Hello World!";
    }
}
