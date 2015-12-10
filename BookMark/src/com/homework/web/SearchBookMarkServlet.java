package com.homework.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import com.homework.domain.BookMark;
import com.homework.factory.BasicFactory;
import com.homework.service.BookMarkService;
import com.homework.util.json.JsonDateValueProcessor;

public class SearchBookMarkServlet extends HttpServlet {

	private static final long serialVersionUID = -6002346336931787199L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		BookMarkService service = BasicFactory.getFactory().getInstance(BookMarkService.class);
		request.setCharacterEncoding("UTF-8");
		String keyword = request.getParameter("keyword");
		
		List<BookMark> list = service.queryByKeyword(keyword);
		
		JsonConfig jsonConfig = new JsonConfig();
		
		// 解决Date类型转换问题
		//jsonConfig.registerJsonBeanProcessor(java.sql.Date.class,new JsDateJsonBeanProcessor());
		
		// 指定Date转换格式
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
			
		JSONArray jsonArray = JSONArray.fromObject(list,jsonConfig);
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.println(jsonArray.toString());
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
