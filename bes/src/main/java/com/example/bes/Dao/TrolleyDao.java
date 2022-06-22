package com.example.bes.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.bes.Entity.Trolley;

@Mapper
public interface TrolleyDao {
	@Results({
        @Result(property = "account", column = "account"),
        @Result(property = "shopaccount", column = "shopaccount"),
        @Result(property = "goodid", column = "goodid"),
        @Result(property = "name", column = "name"),
        @Result(property = "price", column = "price"),     
        @Result(property = "discount", column = "discount"),   
        @Result(property = "details", column = "details"),
        @Result(property = "oan", column = "new"),         
        @Result(property = "type", column = "type"),
        @Result(property = "model", column = "model"),
        @Result(property = "counteroffer", column = "counteroffer"),
        @Result(property = "num", column = "num"),
        @Result(property = "stock", column = "stock"),
        @Result(property = "imgUrl", column = "imgUrl"),
        @Result(property = "identity", column = "identity"),
        @Result(property = "time", column = "time"),
        @Result(property = "phone", column = "phone"),
        @Result(property = "username", column = "username"),
        @Result(property = "address", column = "address")
	})

	@Insert("INSERT into trolley(account,shopaccount,goodid,name,price,details,oan,type,model,counteroffer,num,stock,imgUrl,identity) " //用户添加商品到购物车
			+ "values(#{0},#{1},#{2},#{3},#{4},#{5},#{6},#{7},#{8},#{9},#{10},#{11},#{12},#{13}) ")
	void addcar(String account,String shopaccount,String goodid,String name,String price,String details,String oan,String type,
			String model,String counteroffer,String num,String stock,String imgUrl,String identity);
	
	@Select("SELECT * FROM trolley WHERE account = #{0} and goodid = #{1} and identity = '1'") 
    List<Trolley> getcar(String account, String goodid);  //添加的商品是否已经在购物车
    
    @Update("UPDATE trolley SET num = #{2} WHERE account = #{0} and goodid = #{1}")  //已经在购物车则更新数量
	void updatecount(String account,String goodid,String num);
	
    @Select("SELECT * FROM trolley WHERE account = #{0} and identity = #{1}") 
    List<Trolley> trolley(String account, String identity);  //用户购物车的所有商品
    
    @Select("SELECT * FROM trolley WHERE account = #{0} and identity = '2' or account = #{0} and identity = '3'") 
    List<Trolley> car1(String account);
    
    @Select("SELECT * FROM trolley WHERE shopaccount = #{0} and identity = '5' ") 
    List<Trolley> car2(String shopaccount);
    
    @Delete("Delete  FROM trolley WHERE account = #{0} and goodid = #{1} and identity = '1'") 
    void dgoodcar(String account,String goodid);      //从购物车删除商品
    
    @Update("UPDATE trolley SET identity = '2',stock = #{2}, time = #{3},phone = #{4},username = #{5},address = #{6} WHERE account = #{0} and goodid = #{1}")  
	void pay(String account,String goodid,String stock,String time,String phone,String username,String address); //付款后改变购物车的商品状态为已付款   2
    
    @Update("UPDATE trolley SET identity = '3' WHERE account = #{0} and goodid = #{1}")  
   	void tuikuan(String account,String goodid); //退款
    
    @Select("SELECT * FROM trolley WHERE shopaccount = #{0} and identity = #{1}") 
    List<Trolley> sendgood(String account, String identity);  //商家待发货
    
    @Update("UPDATE trolley SET identity = #{3} WHERE account = #{0} and shopaccount = #{1} and goodid = #{2}")  //商家发货
	void fahuo(String account,String shopaccount,String goodid,String identity);
    
    @Delete("Delete  FROM trolley WHERE account = #{0} and goodid = #{1}")   //退款
    void tuigood(String account,String goodid);
    
    @Update("UPDATE trolley SET identity = '2' WHERE account = #{0} and goodid = #{1}")  
   	void jutui(String account,String goodid); //拒绝退款
    
    @Update("UPDATE trolley SET identity = '5' WHERE account = #{0} and goodid = #{1}")  
   	void shouhuo(String account,String goodid); //用户确认收货改变商品状态为5
    
    @Update("UPDATE trolley SET stock = #{2} WHERE shopaccount = #{0} and goodid = #{1}")  
   	void changeamount(String shopaccount,String goodid,String stock); //商家更新库存
    
    @Update("UPDATE trolley SET price = #{2} WHERE shopaccount = #{0} and goodid = #{1}")  
   	void changeprice(String shopaccount,String goodid,String price); 
    
 
}
