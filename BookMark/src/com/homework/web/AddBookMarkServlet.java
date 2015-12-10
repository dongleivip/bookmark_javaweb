package com.homework.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;

import com.homework.domain.BookMark;
import com.homework.exception.MsgException;
import com.homework.factory.BasicFactory;
import com.homework.service.BookMarkService;

public class AddBookMarkServlet extends HttpServlet {

	private static final long serialVersionUID = -7268960406119993220L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			BookMarkService service = BasicFactory.getFactory().getInstance(BookMarkService.class); 
			
			request.setCharacterEncoding("utf-8");
			
			// 1.封装数据
			BookMark bookmark = new BookMark();
			BeanUtils.populate(bookmark, request.getParameterMap());
			// 校验数据
			bookmark.checkValue();

			// 2.调用Service中的添加方法
			service.add(bookmark);
			
			// 3.添加成功
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("flag", "ok");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.write(jsonObj.toString());
			
		} catch (MsgException e) {
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("flag", "fail");
			jsonObj.put("msg",e.getMessage());
			PrintWriter out = response.getWriter();
			out.write(jsonObj.toString());
		} catch (Exception e) {
			e.printStackTrace();
			// 简单处理一下先
			throw new RuntimeException(e);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
