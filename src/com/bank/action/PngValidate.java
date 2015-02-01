package com.bank.action;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.DefaultActionSupport;

public class PngValidate extends DefaultActionSupport {

	// 得到图像验证码
	@Override
	public String execute() throws Exception {
		com.bank.util.PngValidate validate = new com.bank.util.PngValidate();
		BufferedImage img = validate.gntImgValidate();
	
		HttpServletResponse response = ServletActionContext.getResponse();// 在action中得到request和response的方法，在ServletActionContext中得到
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		
		//在session中设置validate的值
		session.setAttribute("validate", validate.getS());
		
		// 设置不缓存图片
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "No-cache");
		response.setDateHeader("Expires", 0);
		// 指定生成的相应图片
		response.setContentType("image/jpeg");
		ImageIO.write(img, "JPEG", response.getOutputStream());
		return null;
	}

}
