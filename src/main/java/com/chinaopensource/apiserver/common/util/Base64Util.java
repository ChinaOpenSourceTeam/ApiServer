package com.chinaopensource.apiserver.common.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.Base64;

public class Base64Util {

	public static String  Decoder(String content) {
		byte[] asBytes = Base64.getDecoder().decode(content);
		String result ="";
		try {
			result = new String(asBytes, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static String Encoder(String content) {
		String result = "";
		try {
			result = Base64.getEncoder().encodeToString(content.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 把object类中的content属性 用base64解码
	 * @param o
	 */
	public static void DecoderContent(Object o) {
	    /* 
        * 得到类中的所有属性集合 
        */  
       Field[] fs = o.getClass().getDeclaredFields();  
       for (Field field : fs) {
    	   //修改content内容
    	   if(field.getName().equals("content")) {
    		   field.setAccessible(true); //设置些属性是可以访问的  
    		   try {
    			   Object old = field.get(o);
    			   //System.out.println(old);
    			   Object now = Decoder(old.toString());
					field.set(o, now);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    	   }
       }
	}
	
	/**
	 * 把object类中的content属性 用base64编码
	 * @param o
	 */
	public static void EncoderContent(Object o) {
	    /* 
        * 得到类中的所有属性集合 
        */  
       Field[] fs = o.getClass().getDeclaredFields();  
       for (Field field : fs) {
    	   //修改content内容
    	   if(field.getName().equals("content")) {
    		   field.setAccessible(true); //设置些属性是可以访问的  
    		   try {
    			   Object old = field.get(o);
    			   //System.out.println(old);
    			   Object now = Encoder(old.toString());
					field.set(o, now);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    	   }
       }
	}
	
}
