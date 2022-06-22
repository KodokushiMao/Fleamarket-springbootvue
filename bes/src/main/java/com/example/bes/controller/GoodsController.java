package com.example.bes.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.bes.Entity.Goods;
import com.example.bes.Service.GoodsService;
import com.example.bes.Service.TrolleyService;
import com.example.bes.Service.UserService;

@CrossOrigin
@RestController
public class GoodsController {
	@Autowired //自动连接到UserService Bean
    private GoodsService gS;
	@Autowired
	private UserService uS;
	@Autowired
	private TrolleyService tS;
	
	@CrossOrigin
	@RequestMapping(value = "/addgood",method = RequestMethod.POST)
	public String addgood(@RequestParam(value="file",required=false) MultipartFile[] file,String account,String password,
			String name,String price,String discount,String details,String oan,String type,String model,
			String counteroffer,String stock) throws IllegalStateException, IOException {   //商家上架新商品
		System.out.println(account);
		System.out.println(stock);
		if(uS.get(account, password).size() < 1) {
			return "fail";
		}else if(!uS.get(account, password).get(0).getIdentity().equals("3"))   {
			return "fail";
		}
		if(file == null  ) {
			return "请上传商品图片";
		}
		String goodid = UUID.randomUUID().toString();
		String imgUrl = "";
		String allname = "";
		String imgName = "";
		for(int i = 0; i < file.length; i++) {
			allname = file[i].getOriginalFilename();
			imgName = goodid+String.valueOf(i)+allname.substring(allname.lastIndexOf("."));
			file[i].transferTo(new File("C:\\Users\\14505\\Pictures\\good\\"+imgName));
			imgUrl += imgName + ",";
		}
		gS.addgood(account, goodid, name, price, discount, details, oan, type, model, counteroffer, stock, imgUrl);
		return "请等待管理员审核后，方可正式上架！";
	}

	@CrossOrigin
	@RequestMapping(value = "/getgood",method = RequestMethod.POST)
	public List<Goods> getgood(String account,String password) {    //管理员获取待审核的商品
		if(uS.get(account, password).size()  > 0 && uS.get(account, password).get(0).getIdentity().equals("0")) {
			return gS.getgood();
		}
		return null;
	}

	@CrossOrigin
	@RequestMapping(value = "/allowgood",method = RequestMethod.POST)
	public void allowgood(String account,String password,String goodid) {      //允许商品上架
		System.out.println(goodid);
		if(uS.get(account, password).size() == 1 && uS.get(account, password).get(0).getIdentity().equals("0")) {
			gS.allowgood(goodid,"2");
		}
	}

	@CrossOrigin
	@RequestMapping(value = "/getAllgood",method = RequestMethod.POST)
	public List<Goods> getAllgood() {     //获取所有允许上架的商品展示在主页面
		//System.out.println(gS.getAllgood().toString());
		return gS.getAllgood();
	}

	@CrossOrigin
	@RequestMapping(value = "/goodinfo",method = RequestMethod.POST)
	public List<Goods> goodinfo(String goodid) {     //商品详情页
		System.out.println(goodid);
		return gS.goodinfo(goodid);
	}

	@CrossOrigin
	@RequestMapping(value = "/shopgood",method = RequestMethod.POST)
	public List<Goods> shopgood(String account){   //商家的店铺
		System.out.println(account);
		return gS.shopgood(account);
	}

	@RequestMapping(value = "/allgood",method = RequestMethod.POST)
	public List<Goods>	allgood(String account,String password){   //商家的店铺
		if(uS.get(account, password).size() > 0) {
			return gS.shopgood(account);
		}
		return null;
	}

	@RequestMapping(value = "/xiajia",method = RequestMethod.POST)
	public void xiajia(String account,String password,String goodid) {      //允许商品上架
		if(uS.get(account, password).size() > 0) {
			gS.allowgood(goodid,"3");
		}
	}

	@RequestMapping(value = "/shangjia",method = RequestMethod.POST)
	public void shangjia(String account,String password,String goodid) {      //允许商品上架
		if(uS.get(account, password).size() > 0) {
			gS.allowgood(goodid,"2");
		}
	}

	@RequestMapping(value = "/xiajiagood",method = RequestMethod.POST)
	public List<Goods> xiajiagood(String account,String password) {
		if(uS.get(account, password).size() > 0) {
			return gS.getxiajia(account, "3");
		}
		return null;
	}

	@RequestMapping(value = "/changeamount",method = RequestMethod.POST)
	public void changeamount(String account,String password,String goodid,String stock){
		if(uS.get(account, password).size() > 0) {
			gS.tui(account, goodid, stock);
			tS.changeamount(account, goodid, stock);
		}
	}

	@RequestMapping(value = "/changeprice",method = RequestMethod.POST)
	public void changeprice(String account,String password,String goodid,String price,String p){
		System.out.println(p);
		if(uS.get(account, password).size() > 0) {
			gS.changeprice(goodid, price);
			tS.changeprice(account, goodid, p);
		}
	}

	@RequestMapping(value = "/changezhekou",method = RequestMethod.POST)
	public void changezhekou(String account,String password,String goodid,String discount,String p){
		if(uS.get(account, password).size() > 0) {
			gS.changezhekou(goodid, discount);
			tS.changeprice(account, goodid, p);
		}
	}
	
}
