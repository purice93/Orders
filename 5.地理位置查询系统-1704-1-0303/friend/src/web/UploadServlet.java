package web;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import util.DAOFactory;
import dao.PicDAO;
import entity.Pic;

public class UploadServlet extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			DiskFileItemFactory dfif = new DiskFileItemFactory();
			ServletFileUpload sfu = new ServletFileUpload(dfif);
			List<FileItem> items = sfu.parseRequest(request);
			for (int i = 0; i < items.size(); i++) {
				FileItem item = items.get(i);
				if (!item.isFormField()) {
					ServletContext sc = getServletContext();
					String path = sc.getRealPath("upload");
					String filePath = item.getName();
					String fileName = filePath.substring(filePath
							.lastIndexOf("\\"));
					File file1 = new File(path + "/pic_" + id);
					if (!file1.exists()) {
						file1.mkdir();
					}
					File file2 = new File(file1 + "/" + fileName);
					item.write(file2);
					PicDAO picDAO = DAOFactory.getPicDAO();
					Pic pic = new Pic();
					pic.setUserId(id);
					pic.setPicName(fileName);
					picDAO.save(pic);
					response.sendRedirect("load.do?id=" + id);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
