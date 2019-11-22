package com.briup.demo.web.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.briup.demo.service.IArticleService;
import com.briup.demo.service.impl.ArticleServiceImpl;

/**
 * 点击审核对话框上面的【审核通过】【审核不通过】
 * 跳转到该servlet对数据库中资讯 的状态 进行修改！
 * 	通过 		修改state=1
 * 	不通过 	修改state=2
 */
@WebServlet("/admin/examineArticle")
public class ExamineArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.获取提交参数信息
		String articleIds = request.getParameter("articleIds");
		String state = request.getParameter("state");
		
		IArticleService as = new ArticleServiceImpl();
		//2.修改资讯表中对应资讯 状态
		as.examineArticle(articleIds,Long.parseLong(state));
		
		//3.返回提示信息
		PrintWriter pw = response.getWriter();
		pw.write("ok");
		pw.flush();
		pw.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
