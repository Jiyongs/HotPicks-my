package com.kitri.hotpicks.contents.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kitri.hotpicks.contents.dao.ReviewDao;
import com.kitri.hotpicks.contents.model.CommentDto;
import com.kitri.hotpicks.contents.model.ContentsDto;
import com.kitri.hotpicks.contents.model.ReviewDto;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int writeArticle(ReviewDto reviewDto) {
		System.out.println("ReviewService 들어옴");
		int cnt = sqlSession.getMapper(ReviewDao.class).writeArticle(reviewDto);
		return cnt != 0 ? reviewDto.getRseq() : 0;
		
		
	}

	@Override
	public String reviewlist(int contentsId) {
//		System.out.println("리스트뽑으러 서비스 도착");
		return makeJson(contentsId);
		
	}
	
	private String makeJson(int contentsId) {
		List<ReviewDto> list = sqlSession.getMapper(ReviewDao.class).reviewlist(contentsId);
//		System.out.println("list : " + list);
		JSONArray array = new JSONArray(list);
		JSONObject json = new JSONObject();
		json.put("reviewlist", array);
		
		return json.toString();
	}

	@Override
	public void writeMemo(CommentDto commentDto) {
		System.out.println("서비스들어왔니?");
		sqlSession.getMapper(ReviewDao.class).writeMemo(commentDto);
		System.out.println("서비스에서 나왔니?");
		
	}

	@Override
	public String listMemo(int rceq) {
		List<CommentDto> list =  sqlSession.getMapper(ReviewDao.class).listMemo(rceq);
		JSONArray array = new JSONArray(list);
		JSONObject json = new JSONObject();
		json.put("memolist", array);
		
		return json.toString();
	}
	
	@Override
	public void insHashList(List<String> nonHashList, int rseq, int contentsid) {
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println(nonHashList);
		map.put("nonHashList", nonHashList);
		map.put("rseq", rseq);
		map.put("contentsid", contentsid);
		sqlSession.getMapper(ReviewDao.class).insHashList(map);
		
	}
	
	

}
