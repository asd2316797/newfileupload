package com.wxx.servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * 用于上传文件
 * 
 * @author FAFAFA
 * 
 */
@SuppressWarnings("serial")
public class Upload extends HttpServlet {
	private String uploadPath = "D:\\temp"; // 上传文件的目录
	private String tempPath = "d:\\temp\\buffer\\"; // 临时文件目录
	File tempPathFile;

	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		try {
			// servlet中获得项目绝对路径
			// String projectRealPath = this.getServletConfig()
			// .getServletContext().getRealPath("/");
			// System.out.println("projectRealPath" + project);

			// Create a factory for disk-based file items
			DiskFileItemFactory factory = new DiskFileItemFactory();

			// Set factory constraints
			factory.setSizeThreshold(4096); // 设置缓冲区大小，这里是4kb
			factory.setRepository(tempPathFile);// 设置缓冲区目录

			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);

			// Set overall request size constraint
			upload.setSizeMax(4194304); // 设置最大文件尺寸，这里是4MB

			List<FileItem> items = upload.parseRequest(request);// 得到所有的文件
			Iterator<FileItem> iter = items.iterator();
			// 存储文件的名称
			List<String> fileNames = new ArrayList<String>();
			String fileName1 = "";
			String fileName2 = "";

			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
				item.getInputStream();

				if (!item.isFormField()) {
					// 文件流
					String fileName = item.getName();
					fileNames.add(fileName);
					System.out.println("fileName" + fileName);
					if (fileName != null) {
						File fullFile = new File(item.getName());
						File savedFile = new File(uploadPath,
								fullFile.getName());
						item.write(savedFile);
					}
				} else {
					// 非文件流
					String value = item.getString();
					value = new String(value.getBytes("ISO-8859-1"), "UTF-8");
				}
			}
			System.out.println("upload succeed");
			// request.getRequestDispatcher("/uploadsuccess.jsp").forward(request,
			// response);
			fileName1 = fileNames.get(0);
			fileName2 = fileNames.get(1);
			// response.sendRedirect("uploadsuccess.jsp?fileName1=" + fileName1
			// + "fileName2 =" + fileName2);

			// 重定向到Servlet Similarity并且添加参数
			response.sendRedirect("similarity?fileName1=" + fileName1
					+ "&fileName2=" + fileName2);
		} catch (Exception e) {
			// 可以跳转出错页面
			e.printStackTrace();
		}
	}

	public void init() throws ServletException {
		File uploadFile = new File(uploadPath);
		if (!uploadFile.exists()) {
			uploadFile.mkdirs();
		}
		File tempPathFile = new File(tempPath);
		if (!tempPathFile.exists()) {
			tempPathFile.mkdirs();
		}
	}
}
