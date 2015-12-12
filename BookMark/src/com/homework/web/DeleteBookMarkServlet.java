package com.homework.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import com.homework.factory.BasicFactory;
import com.homework.service.BookMarkService;

public class DeleteBookMarkServlet extends HttpServlet {

	private static final long serialVersionUID = 1111880123491132181L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		BookMarkService service = BasicFactory.getFactory().getInstance(BookMarkService.class); 
		
		String str_Id = request.getParameter("itemId"); 
		JSONObject jsonObj = new JSONObject();
		if(!StringUtils.isEmpty(str_Id)){
			service.deleteById(Integer.parseInt(str_Id));
			jsonObj.put("flag", "ok");
		} else {
			jsonObj.put("flag", "fail");
		}
		
		PrintWriter out = response.getWriter();
		out.write(jsonObj.toString());
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
