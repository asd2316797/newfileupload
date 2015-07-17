package com.wxx.servlet;

/**
 * 用来控制相似度计算
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wxx.services.Detaction;

public class Similarity extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("已经重定向到Similarity中");
		// 1.从request中获取fileName1 和fileName2
		String fileName1 = request.getParameter("fileName1");
		String fileName2 = request.getParameter("fileName2");
		// System.out.println("fileName1" + fileName1);
		// System.out.println("fileName2" + fileName2);

		// 2.调用 Detaction中的startDetaction进行相似度比对
		// 记录相似字符串的数组
		List<String> simliarityArray = new ArrayList<String>();
		Detaction detaction = new Detaction();
		// 用于记录文本1和文本2中的内容  text[0]代表文件1 
		List<String> text = new ArrayList<String>();
		float num = detaction.startDetaction(fileName1, fileName2,
				simliarityArray, text);
		// 3.将相似度值和相似字符串数组放入request中
		request.setAttribute("num", num);
		request.setAttribute("simliarityArray", simliarityArray);
		request.setAttribute("text", text);
		// 4.将请求转发到index.jsp中
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
