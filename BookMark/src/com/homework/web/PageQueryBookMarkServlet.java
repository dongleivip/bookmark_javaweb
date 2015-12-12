package com.homework.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.homework.domain.BookMark;
import com.homework.domain.Page;
import com.homework.domain.PageRequest;
import com.homework.factory.BasicFactory;
import com.homework.service.BookMarkService;

public class PageQueryBookMarkServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		BookMarkService service = BasicFactory.getFactory().getInstance(BookMarkService.class);
		
		String keyword = request.getParameter("keyword");
		BookMark bookmark = new BookMark();
		bookmark.setTitle(keyword);
		
		PageRequest pageRequest = new PageRequest();
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		//设置起始页码
		pageRequest.setPageNo(pageNo);
		
		Page<BookMark> page = service.queryByPage(bookmark,pageRequest);
		
//		JsonConfig jsonConfig = new JsonConfig();
//		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
		
//		JSONObject json = JSONObject.fromObject(page,jsonConfig);
		
		JSONObject json = JSONObject.fromObject(page);
		response.getWriter().print(json.toString());
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
