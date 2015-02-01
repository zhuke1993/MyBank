package com.bank.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

/**
 * 生成图像验证码
 * 
 * @author ZHUKE
 * 
 */
public class PngValidate {
	private String s;
	

	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}

	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		BufferedImage img = new PngValidate().gntImgValidate();
		ImageIO.write(img, "JPEG",new FileOutputStream(new File("validate.png")));
	}

	/**
	 * 生成图像验证码
	 * 
	 * @return 含有随机数字信息的图像流
	 */
	public BufferedImage gntImgValidate() {
		// 生成4位随机数字
		String s = this.generateString(4);
		this.setS(s);
		// 将4位数的整数写入到图像流中
		// 新建图像缓存对象
		BufferedImage img = new BufferedImage(50, 25,
				BufferedImage.TYPE_INT_RGB);
		// 得到graphics2D对象，用于在图片上进行绘图
		Graphics2D g2d = img.createGraphics();
		// 绘制数字
		g2d.drawString(s, 10, 20);
		//绘制干扰线
		g2d.setColor(this.getRandomColor(160, 200));
		for (int i = 0; i < 4; i++) {
			int x1 = new Random().nextInt(80);
			int y1 = new Random().nextInt(40);
			int x2 = new Random().nextInt(12);
			int y2 = new Random().nextInt(12);
			g2d.drawLine(x1, y1, x2, y2);
		}
		// 释放资源
		g2d.dispose();
		return img;
	}

	/**
	 * 随机生成指定长度的随机数字
	 * 
	 * @param length
	 *            生成的随机数字的长度
	 * @return 随机字符串
	 */
	public String generateString(int length) {
		String s = new String();
		// 生成4位的随机数字
		for (int i = 0; i < length; i++) {
			s = s + new Random().nextInt(10);
		}
		return s;
	}

	/**
	 * 生成随机颜色
	 * 
	 * @param fc
	 *            前景色
	 * @param bc
	 *            背景色
	 * @return Color对象，此Color对象是RGB形式的。
	 */
	public Color getRandomColor(int fc, int bc) {
		if (fc > 255)
			fc = 200;
		if (bc > 255)
			bc = 255;
		int r = fc + (new Random().nextInt(bc - fc));
		int g = fc + (new Random().nextInt(bc - fc));
		int b = fc + (new Random().nextInt(bc - fc));
		return new Color(r, g, b);
	}
}
