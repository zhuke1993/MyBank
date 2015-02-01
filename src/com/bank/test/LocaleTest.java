package com.bank.test;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleTest {
	public static void main(String[] args) {
/*		Locale[] locales = Locale.getAvailableLocales();
		for (Locale locale : locales) {
			//输出所有支持的国家
			System.out.println(locale.getDisplayCountry()+":"+locale.getCountry());
			//输出所有支持的语言
			System.out.println(locale.getDisplayLanguage()+":"+locale.getLanguage());
			
		}*/
		//设置本地区的语言（默认中文）
		//Locale locale = Locale.getDefault();
		//使用Locale的常量设置具体的语言环境
		Locale locale = Locale.US;
		//根据地区的不同加载不同的资源文件
		ResourceBundle rb = ResourceBundle.getBundle("globalMessages",locale);
		//根据key获得value的值
		String error1 = rb.getString("error.reg.email.null");
		System.out.println(error1);
		System.out.println(locale.getDisplayCountry()+":"+locale.getCountry());
		System.out.println(locale.getDisplayLanguage()+":"+locale.getLanguage());
	}
}
