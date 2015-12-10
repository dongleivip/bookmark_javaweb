package com.homework.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsDateJsonBeanProcessor;
import com.homework.domain.BookMark;
import com.homework.factory.BasicFactory;
import com.homework.service.BookMarkService;

public class QureyBookMarkServlet extends HttpServlet {

	private static final long serialVersionUID = -6002346336931787199L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		BookMarkService service = BasicFactory.getFactory().getInstance(BookMarkService.class);

		List<BookMark> list = service.queryAllByList();
		JsonConfig jsonConfig = new JsonConfig();
		// 避免自包含
		// jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		//设置默认忽略
		// jsonConfig.setIgnoreDefaultExcludes(false);
		
		// 解决两种Date类型转换问题
		jsonConfig.registerJsonBeanProcessor(java.sql.Date.class,new JsDateJsonBeanProcessor());

		JSONArray jsonArray = JSONArray.fromObject(list,jsonConfig);
		//System.out.println(jsonArray.toString());

		// 设置字符编码 避免中文乱码
		response.setCharacterEncoding("UTF-8");
		// 设置ContentType 为"application/x-json"
		// response.setContentType("application/x-json");
		PrintWriter out = response.getWriter();
		// 输出JSONObject字符串到前台
		out.println(jsonArray.toString());
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
