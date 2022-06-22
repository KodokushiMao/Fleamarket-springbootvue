package com.example.bes.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Results;

import com.example.bes.Entity.User;

@Mapper
public interface UserDao {
	@Results({
        @Result(property = "account", column = "account"),
        @Result(property = "password", column = "password"),
        @Result(property = "name", column = "name"),
        @Result(property = "sex", column = "sex"),
        @Result(property = "phone", column = "phone"),
        @Result(property = "email", column = "email"),
        @Result(property = "city", column = "city"),
        @Result(property = "bankcard", column = "bankcard"),
        @Result(property = "identity", column = "identity"),
        @Result(property = "licence", column = "licence"),
        @Result(property = "idcard", column = "idcard"),
        @Result(property = "remainder", column = "remainder"),
        @Result(property = "level", column = "level"),
        @Result(property = "grade", column = "grade")
	})

	@Select("SELECT * FROM user WHERE account = #{0} and password = #{1}")
    List<User> get(String account,String password);      //登录验证

	@Insert("INSERT into register(account,password,name,sex,phone,email,city,bankcard) "   //注册用户信息插入待验证用户表
			+ "values(#{0},#{1},#{2},#{3},#{4},#{5},#{6},#{7}) ")
	void insertZhuce(String account,String password,String name,String sex,String phone,String email,String city,String bankcard);

	@Select("SELECT * FROM register where account = #{0}")
    List<User> getZhuceUser(String account);      //验证用户注册是否重复提交

	@Select("SELECT * FROM register ")
    List<User> getUser();      //获取待验证的注册用户列表

	@Delete("Delete  FROM register WHERE account = #{0}")
    void deleteUser(String account);      //删除允许登录的注册用户

	@Insert("INSERT into user(account,password,name,sex,phone,email,city,bankcard,identity,remainder,grade) "   //允许登录的注册用户信息插入用户表
			+ "values(#{0},#{1},#{2},#{3},#{4},#{5},#{6},#{7},#{8},'0','0') ")
	void insertUser(String account,String password,String name,String sex,String phone,String email,String city,String bankcard,String identity);

	@Select("SELECT * from user where account = #{0} and password = #{1}" )  //获取用户个人信息
	List<User> getUserinfo(String account,String password);

	@Update("UPDATE user SET name = #{0},phone = #{1},email = #{2},city = #{3},bankcard = #{4}  where account = #{5} and password = #{6}" )  //用户更改个人 信息
	void updateInfo(String name,String phone,String email,String city,String bankcard,String account,String password);

	@Update("UPDATE user SET licence = #{2},idcard = #{3},identity = #{4} where account = #{0} and password = #{1}" )  //用户注册商家
	void shopzhuce(String account,String password,String licence,String idcard,String identity);

	@Select("SELECT account,password,name,sex,phone,email,city,bankcard,licence,idcard from user where identity = #{0} " ) //获取所有注册商家的用户
	List<User> getshopzhuce(String identity);

	@Select("SELECT * from user where identity = #{0} " )
	List<User> getshoplevel(String identity);  //获取商家等级

	@Update("UPDATE user SET identity = #{0},level = '5' where account = #{1}" )  //允许注册商家的用户status更改为3
	void allowshopzhuce(String identity,String account);

	@Update("UPDATE user SET remainder = #{1} where account = #{0}" )  //用户下单后扣除相应的钱数
	void pay(String account,String remainder);

	@Select("SELECT * from user where account = #{0} " ) //获取用户的钱数
	List<User> umoney(String account);

	@Update("UPDATE user SET grade = #{1} where account = #{0}" )  //更新用户积分
	void grade(String account,String grade);

	@Update("UPDATE user SET level = #{1} where account = #{0}" )
	void changelevel(String account,String level); //更新商家等级

	@Update("UPDATE user SET remainder = #{1} where account = #{0}" )
	void chongzhi(String account,String remainder); //充值
}
