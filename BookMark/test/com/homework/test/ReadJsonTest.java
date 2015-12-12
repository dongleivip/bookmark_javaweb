package com.homework.test;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import org.junit.Test;

import com.homework.domain.BookMark;
import com.homework.factory.BasicFactory;
import com.homework.service.BookMarkService;
import com.homework.util.json.JsonFileUtil;

@SuppressWarnings("unchecked")
public class ReadJsonTest {
	
	@Test
	public void readJsonFileTest(){
		
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[] { "yyyy-MM-dd" }));
		String JsonContext = JsonFileUtil.ReadJsonFile();
		JSONArray jsonArray = JSONArray.fromObject(JsonContext);
		
		for (int i = 0; i < jsonArray.size(); i++) {
			
			JSONObject jo = (JSONObject) jsonArray.get(i);
			
			String date = jo.get("created").toString();
			System.out.println(Long.parseLong(date) * 1000);
			jo.element("created",new Date(Long.parseLong(date) * 1000));
			
			System.out.println(jo.get("created").toString());
		}
		

		
		List<BookMark> list = (List<BookMark>)JSONArray.toCollection(jsonArray, BookMark.class);  
		
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[] { "yyyy-MM-dd" }));

		for(BookMark model : list){
			System.out.println(model.getCreated());
		}
		
		//System.out.println(json);
		
	}
	
	
	@Test
	public void testJsonDateConvert(){
		
		try{
		
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[] { "yyyy-MM-dd" }));
		          
		String jsonStr = "[{\"name\": \"husband\", \"age\": \"26\", \"born\": \"1984-01-12\"},{\"name\": \"wife\", \"age\": \"20\", \"born\": \"1990-05-01\"}]";

		JSONArray jsonArray = JSONArray.fromObject(jsonStr);
		
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject jo = (JSONObject) jsonArray.get(i);
			System.out.println(jo.get("born"));
		}
		
		
		Collection<JsonBean> list = JSONArray.toCollection(jsonArray, JsonBean.class);
		      //DateUtil.getFormatDate(date,fmtstr)日期转字符串这里不再写代码了
		for (JsonBean o : list) {
		   System.out.println(o.getBorn()); 
		}}
		catch (Exception ex){
			ex.printStackTrace();
		}
	}
	
	
	@Test
	public void testSaveFromJsonData(){
		
		BookMarkService service = BasicFactory.getFactory().getInstance(BookMarkService.class);
		String JsonContext = JsonFileUtil.ReadJsonFile();
		JSONArray jsonArray = JSONArray.fromObject(JsonContext);
		List<BookMark> list = (List<BookMark>)JSONArray.toCollection(jsonArray, BookMark.class);
		
		int successed = service.saveBookMarkByList(list);
		
		System.out.println("成功插入" + successed + "条");
		System.out.println("插入失败" + (list.size() - successed)+ "条");
		
	}
	
	
	@Test
	public void testUtilGetPath(){
		
		JsonFileUtil.ReadJsonFile();
	}
	
	
	

}


