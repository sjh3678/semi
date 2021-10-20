package com.fulldoping.free.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fulldoping.free.dto.FreeComments;
import com.fulldoping.free.service.face.FreeService;
import com.fulldoping.free.service.impl.FreeServiceImpl;

@WebServlet("/free/comment/delete")
public class FreeCommentDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private FreeService freeService = new FreeServiceImpl();   
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
		System.out.println("/free/Comment/Delete/ [GET]");
		
		FreeComments comment = new FreeComments();
		comment.setCommentNo(Integer.parseInt(req.getParameter("commentNo")));
		
		freeService.commentDelete(comment);
		
		//댓글 전체 조회
    	List<FreeComments> commentList = freeService.getCommentList(Integer.parseInt(req.getParameter("boardNo")));
    									
    	//조회결과 MODEL값 전달
    	req.setAttribute("commentList", commentList);
    					
    	//VIEW 지정 및 응답 - forward
    	req.getRequestDispatcher("/WEB-INF/views/freeboard/comment.jsp").forward(req, resp);
    }

}
