package com.lastartupsaas.workbench.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import javax.imageio.ImageIO;

import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;

/**
 * 动态图片验证码工具类
 * 
 * @author lifeilong
 */
public class CaptchaUtil {

	public static final String CAPTCHA_CODE = "CAPTCHA_CODE";

	/**
	 * 动态生成图片验证码
	 */
	public static StreamResource makeCaptchaImg() {
		StreamResource streamResource = new StreamResource(new StreamSource() {
			@Override
			public InputStream getStream() {

				ByteArrayOutputStream out = null;
				try {
					// 在内存中创建图象
					int width = 75, height = 25;
					BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
					// 获取图形上下文
					Graphics g = image.getGraphics();
					// 生成随机类
					Random random = new Random();
					// 设定背景色
					g.setColor(getRandColor(200, 250));
					// g.setColor(new Color(255, 255, 255));
					g.fillRect(0, 0, width, height);
					// 设定字体
					g.setFont(new Font("Times New Roman", Font.PLAIN, 24));
					// 画边框
					g.setColor(getRandColor(160, 200));
					g.drawRect(0, 0, width - 1, height - 1);
					// 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
					g.setColor(getRandColor(160, 200));
					for (int i = 0; i < 155; i++) {
						int x = random.nextInt(width);
						int y = random.nextInt(height);
						int xl = random.nextInt(12);
						int yl = random.nextInt(12);
						g.drawLine(x, y, x + xl, y + yl);
					}
					// 取随机产生的认证码(4位字符)
					StringBuffer sRand = new StringBuffer();
					String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
					for (int i = 0; i < 4; i++) {
						String rand = String.valueOf(str.charAt(random.nextInt(62)));
						sRand.append(rand);
						// 将认证码显示到图象中
						g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
						// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
						g.drawString(rand, 13 * i + 14, 20);
					}

					// 将认证码存入SESSION
					SessionUtil.setToSession(CAPTCHA_CODE, sRand.toString());

					// 图象生效
					g.dispose();
					// 输出图象到页面
					out = new ByteArrayOutputStream();
					ImageIO.write(image, "JPEG", out);
					return new ByteArrayInputStream(out.toByteArray());
				} catch (Throwable ex) {
					ex.printStackTrace();
					return null;
				} finally {
					if (null != out) {
						try {
							out.flush();
							out.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}, new Random().nextFloat() + "Captcha.jpeg");
		streamResource.setCacheTime(0);

		return streamResource;
	}

	/**
	 * 验证图片验证码
	 * 
	 * @param captcha
	 *            用户输入的验证码
	 * @return
	 */
	public static boolean validateCaptcha(String captcha) {
		try {
			// 从session中获取验证码
			String codeInSession = (String) SessionUtil.getFromSession(CAPTCHA_CODE);
			if (captcha.equalsIgnoreCase(codeInSession))
				return true;
		} catch (Throwable ex) {
			ex.printStackTrace();
		}
		return false;
	}

	/**
	 * 给定范围获得随机颜色
	 * 
	 * @param fc
	 * @param bc
	 * @return
	 */
	private static Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}
}