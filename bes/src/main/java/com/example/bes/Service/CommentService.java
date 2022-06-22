package com.example.bes.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.bes.Dao.CommentDao;
import com.example.bes.Entity.Comment;

@Service
public class CommentService {
	@Autowired
	private CommentDao cDao;
	
	public void addcomment(String account,String goodid,String comment,String rank) { //用户评价
		cDao.addcomment(account, goodid, comment, rank);
	}

    public List<Comment> getcomment(String goodid){  //获取商品评价
    	return cDao.getcomment(goodid);
    }

    public List<Comment> ucomment(String account, String goodid){ //防止重复评价
    	return cDao.ucomment(account, goodid);
    }
}
