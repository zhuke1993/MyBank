package com.bank.test;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleTest {
	public static void main(String[] args) {
/*		Locale[] locales = Locale.getAvailableLocales();
		for (Locale locale : locales) {
			//�������֧�ֵĹ���
			System.out.println(locale.getDisplayCountry()+":"+locale.getCountry());
			//�������֧�ֵ�����
			System.out.println(locale.getDisplayLanguage()+":"+locale.getLanguage());
			
		}*/
		//���ñ����������ԣ�Ĭ�����ģ�
		//Locale locale = Locale.getDefault();
		//ʹ��Locale�ĳ������þ�������Ի���
		Locale locale = Locale.US;
		//���ݵ����Ĳ�ͬ���ز�ͬ����Դ�ļ�
		ResourceBundle rb = ResourceBundle.getBundle("globalMessages",locale);
		//����key���value��ֵ
		String error1 = rb.getString("error.reg.email.null");
		System.out.println(error1);
		System.out.println(locale.getDisplayCountry()+":"+locale.getCountry());
		System.out.println(locale.getDisplayLanguage()+":"+locale.getLanguage());
	}
}
