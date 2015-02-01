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
 * ����ͼ����֤��
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
	 * ����ͼ����֤��
	 * 
	 * @return �������������Ϣ��ͼ����
	 */
	public BufferedImage gntImgValidate() {
		// ����4λ�������
		String s = this.generateString(4);
		this.setS(s);
		// ��4λ��������д�뵽ͼ������
		// �½�ͼ�񻺴����
		BufferedImage img = new BufferedImage(50, 25,
				BufferedImage.TYPE_INT_RGB);
		// �õ�graphics2D����������ͼƬ�Ͻ��л�ͼ
		Graphics2D g2d = img.createGraphics();
		// ��������
		g2d.drawString(s, 10, 20);
		//���Ƹ�����
		g2d.setColor(this.getRandomColor(160, 200));
		for (int i = 0; i < 4; i++) {
			int x1 = new Random().nextInt(80);
			int y1 = new Random().nextInt(40);
			int x2 = new Random().nextInt(12);
			int y2 = new Random().nextInt(12);
			g2d.drawLine(x1, y1, x2, y2);
		}
		// �ͷ���Դ
		g2d.dispose();
		return img;
	}

	/**
	 * �������ָ�����ȵ��������
	 * 
	 * @param length
	 *            ���ɵ�������ֵĳ���
	 * @return ����ַ���
	 */
	public String generateString(int length) {
		String s = new String();
		// ����4λ���������
		for (int i = 0; i < length; i++) {
			s = s + new Random().nextInt(10);
		}
		return s;
	}

	/**
	 * ���������ɫ
	 * 
	 * @param fc
	 *            ǰ��ɫ
	 * @param bc
	 *            ����ɫ
	 * @return Color���󣬴�Color������RGB��ʽ�ġ�
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
