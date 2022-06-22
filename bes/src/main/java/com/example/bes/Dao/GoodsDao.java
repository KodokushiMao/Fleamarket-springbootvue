package com.example.bes.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.bes.Entity.Goods;

@Mapper
public interface GoodsDao {
	@Results({
        @Result(property = "account", column = "account"),
        @Result(property = "goodid", column = "goodid"),
        @Result(property = "name", column = "name"),
        @Result(property = "price", column = "price"),
        @Result(property = "discount", column = "discount"),
        @Result(property = "details", column = "details"),
        @Result(property = "newo", column = "new"),
        @Result(property = "type", column = "type"),
        @Result(property = "model", column = "model"),
        @Result(property = "counteroffer", column = "counteroffer"),
        @Result(property = "stock", column = "stock"),
        @Result(property = "imgUrl", column = "imgUrl"),
        @Result(property = "identity", column = "identity"),
        @Result(property = "sold", column = "sold")
	})

	@Insert("INSERT into goods(account,goodid,name,price,discount,details,oan,type,model,counteroffer,stock,imgUrl,identity,sold) " // 商家上架新商品
			+ "values(#{0},#{1},#{2},#{3},#{4},#{5},#{6},#{7},#{8},#{9},#{10},#{11},#{12},#{13}) ")
	void addgood(String account,String goodid,String name,String price,String discount,String details,String oan,String type,
			String model,String counteroffer,String stock,String imgUrl,String identity,String sold);

	@Select("SELECT account,goodid,name,price,discount,details,oan,type,model,counteroffer,stock,imgUrl FROM goods WHERE identity = '1'")
    List<Goods> getgood();      //管理员获取待审核上架的商品

	@Update("UPDATE goods SET identity = #{1} WHERE goodid = #{0}")  //允许上架商品
	void allowgood(String goodid,String identity);

	@Select("SELECT * FROM goods WHERE identity = '2'")
    List<Goods> getAllgood();   //获取所有允许上架的商品展示在主页面

	@Select("SELECT * FROM goods WHERE goodid = #{0}")
    List<Goods> goodinfo(String goodid);   //商品详情页

	@Select("SELECT * FROM goods WHERE account = #{0} and identity = '2'")
    List<Goods> shopgood(String account);   //商家的店铺

	@Update("UPDATE goods SET stock = #{1} WHERE goodid = #{0}")  //用户下单商品库存减少
	void changeamount(String goodid,String stock);

	@Update("UPDATE goods SET price = #{1} WHERE goodid = #{0}")
	void changeprice(String goodid,String price);

	@Update("UPDATE goods SET discount = #{1} WHERE goodid = #{0}")
	void changezhekou(String goodid,String discount);

	@Update("UPDATE goods SET stock = #{2} WHERE account = #{0} and goodid = #{1}")  //退款  商品库存加回来
	void tui(String account,String goodid,String stock);

	@Update("UPDATE goods SET sold = #{1} WHERE goodid = #{0}")  //历史销量增加
	void shouchu(String goodid,String sold);

	@Select("SELECT * FROM goods WHERE account = #{0} and identity = #{1}")
    List<Goods> getxiajia(String account,String identity);   //下架的商品
} 
