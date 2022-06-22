package com.example.bes.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.bes.Dao.TrolleyDao;
import com.example.bes.Entity.Trolley;

@Service
public class TrolleyService {
	@Autowired
	private TrolleyDao tDao;
	
	public void addcar(String account,String shopaccount,String goodid,String name,String price,String details,String oan,String type,
			String model,String counteroffer,String num,String stock,String imgUrl) {   //用户添加商品到购物车
		tDao.addcar(account,shopaccount, goodid, name, price, details, oan, type, model, counteroffer, num,stock, imgUrl, "1");
	}

	public List<Trolley> getcar(String account, String goodid){      //添加的商品是否已经在购物车
		return tDao.getcar(account, goodid);
	}

	public void updatecount(String account,String goodid,String num) {  //已经在购物车则更新数量
		tDao.updatecount(account, goodid, num);
	}

	public List<Trolley> car(String account, String identity){  //用户购物车的所有商品
		return tDao.trolley(account,identity);
	}

	public List<Trolley> car1(String account){
		return tDao.car1(account);
	}

	public List<Trolley> car2(String shopaccount){
		return tDao.car2(shopaccount);
	}

	public void dgoodcar(String account,String goodid) {     //从购物车删除商品
		tDao.dgoodcar(account, goodid);
	}

	public void pay (String account,String goodid,String stock,String time,String phone,String username,String address) {      //用户付款的商品
		tDao.pay(account, goodid,stock, time, phone, username, address);
	}

   	public void tuikuan(String account,String goodid) {//退款
   		tDao.tuikuan(account, goodid);
   	}

    public List<Trolley> sendgood(String account, String identity){ //商家待发货
    	return tDao.sendgood(account, identity);
    }

	public void fahuo(String account,String shopaccount,String goodid,String identity) {  //商家发货
		tDao.fahuo(account, shopaccount, goodid, identity);
	}

	public void jutui(String account,String goodid) { //拒绝退款
		tDao.jutui(account, goodid);
	}

	public void tuigood(String account,String goodid) {  //退款
	    	tDao.tuigood(account, goodid);
	}

	public void shouhuo(String account,String goodid) { //用户确认收货改变商品状态为5
		tDao.shouhuo(account, goodid);
	}
	
	public void changeamount(String shopaccount,String goodid,String stock) { //商家更新库存
		tDao.changeamount(shopaccount, goodid, stock);
	}
	
	public void changeprice(String shopaccount,String goodid,String p) { //商家更新库存
		tDao.changeprice(shopaccount, goodid, p);
	}

}
