package com.briup.demo.web.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.briup.demo.common.bean.Article;
import com.briup.demo.service.IArticleService;
import com.briup.demo.service.impl.ArticleServiceImpl;

/**
 * 点击管理员主页上面待审核超链接，跳转到此处， 查询数据库中所有待审核的资讯，显示到页面上
 */
@RestController
public class ShowArticleAdminServlet {

	@RequestMapping("/admin/showArticleAdmin")
								// 获取state状态值
	public ModelAndView test1(@RequestParam("state") int state) {
		// 根据咨询状态获取所有 资讯
		IArticleService as = new ArticleServiceImpl();
		List<Article> list = as.findArticlesByState(state);
		
		// -----------------------
		ModelAndView mv = new ModelAndView();
		// 设置资讯列表到request中
		mv.addObject("articles", list);
		// 页面上展示不同的图标
		mv.addObject("mark", state);

		// 根据咨询状态，跳转到不同页面
		if (state == 0) {
			System.out.println("待审核页面");
			//request.getRequestDispatcher("/WEB-INF/jsp/admin/page/ExamineingSub.jsp").forward(request, response);
		} else if (state == 1) {
			System.out.println("正常资讯页面");
			//request.getRequestDispatcher("/WEB-INF/jsp/admin/page/ExaminedSub.jsp").forward(request, response);
		}

		return mv;
	}

}
