package com.chinaopensource.apiserver.common.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Map和Bean互相转换
 * 
 * @author lqw
 * 2017年3月18日 下午5:56:57
 */
public class BeanMapTransformation {

	private static final Logger LOGGER = LoggerFactory.getLogger(BeanMapTransformation.class);

	public static Map<String, String> transBeanToMap(Object obj, List<String> exclusions) {

		if (obj == null) {
			return null;
		}
		Map<String, String> map = new HashMap<String, String>();
		BeanInfo beanInfo;
		try {
			beanInfo = Introspector.getBeanInfo(obj.getClass());
		} catch (IntrospectionException e1) {
			LOGGER.error("transBean2Map Error:{} ", e1);
			return null;
		}
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (PropertyDescriptor property : propertyDescriptors) {
			try {
				String key = property.getName();
				// 过滤class属性,过滤不需要的数次属性
				if (!key.equals("class") && (exclusions == null || !exclusions.contains(key))) {
					// 得到property对应的getter方法
					Method getter = property.getReadMethod();
					Object value = getter.invoke(obj);
					map.put(key, value != null ? value.toString() : "");
				}

			} catch (Exception e) {
				LOGGER.error("transBean2Map Error:{} ", e);
				continue;
			}
		}
		return map;

	}

	public static void transMapToBean(Map<String, String> map, Object obj) {

		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();
				if (map.containsKey(key)) {
					Object value = map.get(key);
					// 得到property对应的setter方法
					Method setter = property.getWriteMethod();
					if("java.lang.String".equals((setter.getParameterTypes()[0]).getName())){
					setter.invoke(obj, value);
					}
				}

			}

		} catch (Exception e) {
			System.out.println("transMap2Bean Error " + e);
		}
		return;
	}

}
