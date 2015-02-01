package com.bank.action;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.DefaultActionSupport;

public class PngValidate extends DefaultActionSupport {

	// �õ�ͼ����֤��
	@Override
	public String execute() throws Exception {
		com.bank.util.PngValidate validate = new com.bank.util.PngValidate();
		BufferedImage img = validate.gntImgValidate();
	
		HttpServletResponse response = ServletActionContext.getResponse();// ��action�еõ�request��response�ķ�������ServletActionContext�еõ�
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		
		//��session������validate��ֵ
		session.setAttribute("validate", validate.getS());
		
		// ���ò�����ͼƬ
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "No-cache");
		response.setDateHeader("Expires", 0);
		// ָ�����ɵ���ӦͼƬ
		response.setContentType("image/jpeg");
		ImageIO.write(img, "JPEG", response.getOutputStream());
		return null;
	}

}
