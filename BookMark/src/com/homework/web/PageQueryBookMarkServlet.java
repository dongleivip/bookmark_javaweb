package com.homework.web;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.homework.domain.BookMark;
import com.homework.domain.Page;
import com.homework.domain.PageRequest;
import com.homework.factory.BasicFactory;
import com.homework.service.BookMarkService;
import com.homework.util.json.JsonDateValueProcessor;

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
		
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
		
		JSONObject json = JSONObject.fromObject(page,jsonConfig);
		response.getWriter().print(json.toString());
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
