package com.homework.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

			// 封装数据
			BookMark bookmark = new BookMark();
			BeanUtils.populate(bookmark, request.getParameterMap());
			bookmark.checkValue();

			// 调用Service中的添加方法
			service.add(bookmark);
			
			// 提示添加成功
			response.getWriter().write("添加成功");
			response.setHeader("refresh", "3;url=" + request.getContextPath() + "/index.jsp");
		} catch (MsgException e) {
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("/index.jsp");
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
