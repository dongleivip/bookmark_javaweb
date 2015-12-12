package com.homework.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.homework.domain.BookMark;
import com.homework.factory.BasicFactory;
import com.homework.service.BookMarkService;
import com.homework.util.json.JsonFileUtil;

public class InitDataBaseServlet extends HttpServlet {

	private static final long serialVersionUID = -6173425265411012193L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		try {

			BookMarkService service = BasicFactory.getFactory().getInstance(BookMarkService.class);

			String jsonFileName = config.getInitParameter("initJsonFile");

			String JsonContext = JsonFileUtil.ReadJsonFile(jsonFileName);
			JSONArray jsonArray = JSONArray.fromObject(JsonContext);

			@SuppressWarnings("unchecked")
			List<BookMark> list = (List<BookMark>) JSONArray.toCollection(jsonArray, BookMark.class);

			int count = service.saveBookMarkByList(list);
			//应该用log来打印...
			System.out.println("");
			System.out.println("-----------Init Info-----------------");
			System.out.println("Inserted " + count + "record(s)");
			System.out.println("Failed Inserted" + (list.size() - count) + "record(s)");
			System.out.println("-----------Init Info-----------------");
			System.out.println("");
					
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException();
		}
	}

}
