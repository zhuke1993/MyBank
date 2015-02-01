package com.bank.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
	public String str;

	public void transFormMD5(String text) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(text.getBytes());
			byte b[] = md.digest();
			int x;
			StringBuffer buf = new StringBuffer("");
			for (int i = 0; i < b.length; i++) {
				x = b[i];
				if (x < 0)
					x += 256;
				if (x < 16)
					buf.append("0");
				buf.append(Integer.toHexString(x));
			}
			str = buf.toString();
			System.out.println("32λ���ܺ���ַ���: " + buf.toString());// 32λ�ļ���
			System.out
					.println("16λ���ܺ���ַ���: " + buf.toString().substring(8, 24));// 16λ�ļ���
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String agrs[]) {
		MD5 tm = new MD5();
		tm.transFormMD5("password");// ����ת��
	}
}
