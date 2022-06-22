package com.example.bes.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.example.bes.Entity.Comment;

@Mapper
public interface CommentDao {
	@Results({
        @Result(property = "account", column = "account"),
        @Result(property = "goodid", column = "goodid"),
        @Result(property = "comment", column = "comment"),
        @Result(property = "rate", column = "rate")
	})

	@Insert("INSERT into comment(account,goodid,comment,rate)" //用户评价
			+ "values(#{0},#{1},#{2},#{3}) ")
	void addcomment(String account,String goodid,String comment,String rank);

	@Select("SELECT * FROM comment WHERE account = #{0} and goodid = #{1}")
    List<Comment> ucomment(String account, String goodid);  //防止重复评价
	
	@Select("SELECT * FROM comment WHERE goodid = #{0} ") 
    List<Comment> getcomment(String goodid);  //获取商品评价
}
