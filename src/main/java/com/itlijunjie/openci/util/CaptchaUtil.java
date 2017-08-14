package com.itlijunjie.openci.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("captchaUtil")
public class CaptchaUtil {
	@Value("100")
	private int width;
	@Value("20")
	private int height;
	@Value("5")
	private int number;
	@Value("0123456789")
	private String code;
	private static final Random RAN = new Random();
	
	/**
	 * 随机生成字符串
	 * @return 根据code随机生成number数量的字符串
	 */
	public String generatorStr() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < number; i++) {
			sb.append(RAN.nextInt(code.length()));
		}
		return sb.toString();
	}
	
	/**
	 * 根据字符串生成BufferedImage对象
	 * @param str 验证码字符串
	 * @return 根据 str 生成的Image
	 */
	public BufferedImage generatorImageByStr(String str) {
		BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		Graphics2D graphic = image.createGraphics();
		//填充图像
		graphic.setColor(Color.WHITE);
		graphic.fillRect(0, 0, width, height);
		//画边框
		graphic.setColor(Color.BLACK);
		graphic.drawRect(0, 0, width-1, height-1);
		//画字符串
		int x = width/number;
		int y = height - 5;
		Font font = new Font("宋体",Font.BOLD,14);
		for (int i = 0; i < str.length(); i++) {
			graphic.setFont(font);
			graphic.setColor(new Color(RAN.nextInt(255), RAN.nextInt(255), RAN.nextInt(255)));
			graphic.drawString(String.valueOf(str.charAt(i)), x*i+5, y);
		}
		//随机生成点
		for (int i = 0; i < 60; i++) {
			graphic.setColor(new Color(RAN.nextInt(255), RAN.nextInt(255), RAN.nextInt(255)));
			graphic.drawOval(RAN.nextInt(width),RAN.nextInt(height),1,1);
		}
		return image;
	}
}
