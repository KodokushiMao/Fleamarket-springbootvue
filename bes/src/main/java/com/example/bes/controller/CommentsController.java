package com.example.bes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.bes.Entity.Comment;
import com.example.bes.Service.CommentService;
import com.example.bes.Service.UserService;

@CrossOrigin
@RestController
public class CommentsController {
	@Autowired
	private CommentService cS;
	@Autowired
	private UserService uS;
	
	@RequestMapping(value = "/addcomment",method = RequestMethod.POST)
	public String addcomment(String account,String word,String goodid,String comment,String rank) { //用户评价
		if(uS.get(account, word).size() > 0) {
			if(cS.ucomment(account, goodid).size() > 0) {
				return "您已评论,请勿重复评论！";
			}else {
				cS.addcomment(account, goodid, account, rank);
				return "评论成功！";
			}
		}
		return null;
	}

	@RequestMapping(value = "/comment",method = RequestMethod.POST)
    public List<Comment> getcomment(String goodid){  //获取商品评价
    	return cS.getcomment(goodid);
    }

    @RequestMapping(value = "/uc",method = RequestMethod.POST)
    public String ucomment(String account,String goodid){
    	return cS.ucomment(account, goodid).get(0).getComment();
    }
    
}	
