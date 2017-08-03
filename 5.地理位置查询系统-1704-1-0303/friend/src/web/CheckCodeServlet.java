package web;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CheckCodeServlet extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		BufferedImage image = new BufferedImage(80, 30,
				BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		Random r = new Random();
		g.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
		g.fillRect(0, 0, 80, 30);
		String number = getNumber(5);
		HttpSession session = request.getSession();
		session.setAttribute("number", number);
		g.setColor(new Color(0, 0, 0));
		g.setFont(new Font(null, Font.ITALIC, 20));
		g.drawString(number, 4, 25);
		for (int i = 0; i < 5; i++) {
			g
					.setColor(new Color(r.nextInt(255), r.nextInt(255), r
							.nextInt(255)));
			g.drawLine(r.nextInt(80), r.nextInt(30), r.nextInt(80), r
					.nextInt(30));

		}
		response.setContentType("image/jpeg");
		OutputStream os = response.getOutputStream();
		javax.imageio.ImageIO.write(image, "jpeg", os);

	}

	private String getNumber(int size) {
		String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random r = new Random();
		String rs = "";
		for (int i = 0; i < 5; i++) {
			rs += str.charAt(r.nextInt(str.length()));
		}

		return rs;
	}
}
