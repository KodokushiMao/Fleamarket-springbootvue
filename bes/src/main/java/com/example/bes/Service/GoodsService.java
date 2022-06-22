package com.example.bes.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.bes.Dao.GoodsDao;
import com.example.bes.Entity.Goods;

@Service
public class GoodsService {
	@Autowired //连接到UserDao Bean
	private GoodsDao gDao;
	
	public void addgood(String account,String goodid,String name,String price,String discount,String details,String newo,String type,
			String model,String counteroffer,String stock,String imgUrl) {   //商家上架新商品
		gDao.addgood(account, goodid, name, price, discount, details, newo, type, model, counteroffer, stock, imgUrl, "1", "0");
	}

	public List<Goods> getgood(){  //管理员获取待审核的商品
		return gDao.getgood();
	}

	public void allowgood(String goodid,String identity) {   //管理员允许上架的商品
		gDao.allowgood(goodid,identity);
	}

	public List<Goods> getAllgood(){     //获取所有允许上架的商品展示在主页面
		return gDao.getAllgood();
	}

	public List<Goods> goodinfo(String goodid){   //商品详情页
		return gDao.goodinfo(goodid);
	}

	public List<Goods> shopgood(String account){   //商家的店铺
		return gDao.shopgood(account);
	}

	public void changeamount(String goodid,String stock) {	 //商家更改数量
		gDao.changeamount(goodid, stock);
	}

	public void changeprice(String goodid,String price) {
		gDao.changeprice(goodid, price);
	}

	public void changezhekou(String goodid,String discount) {
		gDao.changezhekou(goodid, discount);
	}

	public void tui(String account,String goodid,String stock) { //退款  商品库存加回来
		gDao.tui(account, goodid, stock);
	}

	public void shouchu(String goodid,String sold) { //历史销量增加
		gDao.shouchu(goodid, sold);
	}

	public List<Goods> getxiajia(String account,String identity){  //下架的商品
		return gDao.getxiajia(account, identity);
	}
}
