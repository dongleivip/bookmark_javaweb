package com.homework.web;

import java.io.IOException;
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
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		JSONObject jsonObj = new JSONObject();
		try {
			BookMarkService service = BasicFactory.getFactory().getInstance(BookMarkService.class); 
			// 1.封装数据
			BookMark bookmark = new BookMark();
			BeanUtils.populate(bookmark, request.getParameterMap());
			// 校验数据
			bookmark.checkValue();

			// 2.调用Service中的添加方法
			service.add(bookmark);
			
			// 3.添加成功
			jsonObj.put("flag", "ok");
			response.getWriter().write(jsonObj.toString());
		} catch (MsgException e) {
			jsonObj.put("flag", "fail");
			jsonObj.put("msg",e.getMessage());
			response.getWriter().write(jsonObj.toString());
		} catch (Exception e) {
			jsonObj.put("flag", "fail");
			jsonObj.put("msg", "系统出错了...");
			response.getWriter().write(jsonObj.toString());
			
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
