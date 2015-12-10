package com.homework.util.json;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class JsonDateValueProcessor implements JsonValueProcessor {

	private String format ="yyyy-MM-dd";
	
	public JsonDateValueProcessor(){
		super();
	}
	
	/**
	 * 可指定转换格式
	 * @param format 指定的format格式  默认是 yyyy-MM-dd
	 */
	public JsonDateValueProcessor(String format){
		super();
		this.format = format;
	}
	
	public Object processArrayValue(Object paramObject, JsonConfig paramJsonConfig) {
		return process(paramObject);  
	}

	public Object processObjectValue(String key, Object paramObject, JsonConfig paramJsonConfig) {
		return process(paramObject);  
	}
	
	private Object process(Object value){
		if(value instanceof Date){    
            SimpleDateFormat sdf = new SimpleDateFormat(format);    
            return sdf.format(value); 
        }   
        return value == null ? "" : value.toString();   
    }  
}
