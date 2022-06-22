package com.example.bes.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.bes.Entity.Trolley;
import com.example.bes.Service.GoodsService;
import com.example.bes.Service.TrolleyService;
import com.example.bes.Service.UserService;

@CrossOrigin
@RestController
public class TrolleyController {
	@Autowired //自动连接到UserService Bean
    private TrolleyService tS;
	@Autowired
	private UserService uS;
	@Autowired
	private GoodsService gS;
	
	@RequestMapping(value = "/addcar",method = RequestMethod.POST)
	public void addgood(String account,String password,String shopaccount,String goodid,String name,String price,String details,String oan,String type,
			String model,String counteroffer,String num,String stock,String imgUrl) {
		if(uS.get(account, password).size() > 0) {
			if(tS.getcar(account, goodid).size() > 0) {
				int s = Integer.parseInt(num) + Integer.parseInt(tS.getcar(account, goodid).get(0).getNum());
				tS.updatecount(account, goodid, String.valueOf(s));
			}else {
				tS.addcar(account,shopaccount, goodid, name, price, details, oan, type, model, counteroffer, num, stock,imgUrl);
			}
		}
	}

	@RequestMapping(value = "/car",method = RequestMethod.POST)
	public List<Trolley> car(String account, String password){  //用户购物车的所有商品
		if(uS.get(account, password).size() > 0) {
			return tS.car(account,"1");
		}
		return null;
	}

	@RequestMapping(value = "/dgoodcar",method = RequestMethod.POST)
	public void dgoodcar(String account,String password,String goodid){  //从购物车删除商品
		if(uS.get(account, password).size() > 0) {
			tS.dgoodcar(account, goodid);
		}
	}

	@RequestMapping(value = "/changecount",method = RequestMethod.POST)
	public void changecount(String account,String password,String goodid,String num){  //从购物车删除商品
		if(uS.get(account, password).size() > 0) {
				tS.updatecount(account, goodid, num);
		}
	}

	@RequestMapping(value = "/pay",method = RequestMethod.POST)
	public void pay (String account,String password,String[] goodid,String phone,String username,String address,String remainder) {      //用户付款的商品
		System.out.println(goodid[0]);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//设置日期格式
		String time = df.format(new Date());
		//System.out.println(time);
		for(int i = 0; i < goodid.length; i++) {
			String a = tS.getcar(account, goodid[i]).get(0).getStock();
			String b = tS.getcar(account, goodid[i]).get(0).getNum();
			String stock = String.valueOf( Integer.parseInt(a) - Integer.parseInt(b));
			tS.pay(account, goodid[i], stock,time, phone, username, address);
			gS.changeamount(goodid[i], stock);
		}
		String m = uS.get(account, password).get(0).getRemainder();
		double rmb = Double.parseDouble(m) - Double.parseDouble(remainder);
		uS.pay(account, String.valueOf(rmb));
	}

	@RequestMapping(value = "/payed",method = RequestMethod.POST)
	public List<Trolley> payed(String account, String password){  //用户已经下单的商品
		if(uS.get(account, password).size() > 0) {
			return tS.car1(account);
		}
		return null;
	}

	@RequestMapping(value = "/tuikuan",method = RequestMethod.POST)
	public String tuikuan(String account,String password,String goodid,String time) throws ParseException{  //退款
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//设置日期格式
		String t = df.format(new Date());
		long from = df.parse(time).getTime();
		long to = df.parse(t).getTime();
		int minutes = (int) ((to - from)/(1000 * 60));
		if(minutes / 60 > 24) {
			return "下单时间超过24小时，无法申请退款！";
		}
		if(uS.get(account, password).size() > 0) {
			tS.tuikuan(account, goodid);
		}
		return "申请退款成功，请等待商家进行审核！";
	}

	@RequestMapping(value = "/sendgood",method = RequestMethod.POST)
	public List<Trolley> sendgood(String account, String password){  //商家待发货
		if(uS.get(account, password).get(0).getIdentity().equals("3")) {
			return tS.sendgood(account, "2");
		}
		return null;
	}

	@RequestMapping(value = "/fahuo",method = RequestMethod.POST)
	public String fahuo(String account,String shopaccount,String password,String goodid){  //商家确认发货
		if(uS.get(shopaccount, password).size() > 0) {
			 tS.fahuo(account, shopaccount, goodid, "4");
		}
		return null;
	}

	@RequestMapping(value = "/tui",method = RequestMethod.POST)
	public List<Trolley> tui(String account, String password){  //商家退款订单
		if(uS.get(account, password).size() > 0) {
			return tS.sendgood(account, "3");
		}
		return null;
	}

	@RequestMapping(value = "/jutui",method = RequestMethod.POST)
	public void jutui(String account,String shopaccount,String password,String goodid){  //商家拒绝退款
		if(uS.get(shopaccount, password).size() > 0) {
			tS.jutui(account, goodid);
		}
	}

	@RequestMapping(value = "/allowtui",method = RequestMethod.POST)
	public void allowtui(String account,String shopaccount,String password,String goodid,String price,String num,String stock) {    //同意退款
		if(uS.get(shopaccount, password).size() > 0) {
			tS.tuigood(account, goodid);
			int a = Integer.parseInt(num) + Integer.parseInt(stock);
			gS.tui(shopaccount,goodid , String.valueOf(a));
			double b = Double.parseDouble(uS.umoney(account).get(0).getRemainder()) + Double.parseDouble(price);
			uS.pay(account, String.valueOf(b));
		}
	}

	@RequestMapping(value = "/shouhuo",method = RequestMethod.POST)
	public List<Trolley> shouhuo(String account, String password){  //用户确认收货表
		if(uS.get(account, password).size() > 0) {
			return tS.car(account, "4");
		}
		return null;
	}

	@RequestMapping(value = "/ushou",method = RequestMethod.POST)
	public void ushou(String account,String password,String shopaccount,String goodid,String price,String num) { //用户确认收货改变商品状态为5
		if(uS.get(account, password).size() > 0) {
			tS.shouhuo(account, goodid);
			int a = Integer.parseInt(gS.goodinfo(goodid).get(0).getSold()) + Integer.parseInt(num);
			gS.shouchu(goodid, String.valueOf(a));
			double b = 0;
			int fen = 0;
			double p = 0;
			fen =  Integer.parseInt(uS.umoney(shopaccount).get(0).getGrade()) +
					(int)(Double.parseDouble(price)/1);
			if(uS.umoney(shopaccount).get(0).getLevel().equals("1") || uS.umoney(shopaccount).get(0).getLevel().equals("2") ) {
				b =  Double.parseDouble(uS.umoney(shopaccount).get(0).getRemainder()) +
						Double.parseDouble(price)*(1-(Double.parseDouble(uS.umoney(shopaccount).get(0).getLevel())/1000));
				p =  Double.parseDouble(uS.umoney("admin").get(0).getRemainder()) +
						Double.parseDouble(price)*Double.parseDouble(uS.umoney(shopaccount).get(0).getLevel())/1000;
			}else if(uS.umoney(shopaccount).get(0).getLevel().equals("3")) {
				b =  Double.parseDouble(uS.umoney(shopaccount).get(0).getRemainder()) +
						Double.parseDouble(price)*(1-0.005);
				p =  Double.parseDouble(uS.umoney("admin").get(0).getRemainder()) +
						Double.parseDouble(price)*0.005;
			}else if(uS.umoney(shopaccount).get(0).getLevel().equals("4")) {
				b =  Double.parseDouble(uS.umoney(shopaccount).get(0).getRemainder()) +
						Double.parseDouble(price)*(1-0.075);
				p =  Double.parseDouble(uS.umoney("admin").get(0).getRemainder()) +
						Double.parseDouble(price)*0.075;
			}else if(uS.umoney(shopaccount).get(0).getLevel().equals("5")) {
				b =  Double.parseDouble(uS.umoney(shopaccount).get(0).getRemainder()) +
						Double.parseDouble(price)*(1-0.01);
				p =  Double.parseDouble(uS.umoney("admin").get(0).getRemainder()) +
						Double.parseDouble(price)*0.01;
			}
			System.out.println(p);
			uS.pay(shopaccount, String.valueOf(b));
			uS.pay("admin", String.valueOf(p));
			uS.grade(account, String.valueOf(fen));
		}
	}

	@RequestMapping(value = "/horder",method = RequestMethod.POST)
	public List<Trolley> horder(String account, String password){  //用户历史订单
		if(uS.get(account, password).size() > 0) {
			return tS.car(account, "5");
		}
		return null;
	}

	@RequestMapping(value = "/horder1",method = RequestMethod.POST)
	public List<Trolley> horder1(String account, String password){  //用户历史订单
		if(uS.get(account, password).size() > 0) {
			if(uS.get(account, password).size() > 0 && uS.get(account, password).get(0).getIdentity().equals("3")) {
				return tS.car2(account);
			}
		}
		return null;
	}
}
