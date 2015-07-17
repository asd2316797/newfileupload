package com.wxx.servlet;

/**
 * �����������ƶȼ���
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
		System.out.println("�Ѿ��ض���Similarity��");
		// 1.��request�л�ȡfileName1 ��fileName2
		String fileName1 = request.getParameter("fileName1");
		String fileName2 = request.getParameter("fileName2");
		// System.out.println("fileName1" + fileName1);
		// System.out.println("fileName2" + fileName2);

		// 2.���� Detaction�е�startDetaction�������ƶȱȶ�
		// ��¼�����ַ���������
		List<String> simliarityArray = new ArrayList<String>();
		Detaction detaction = new Detaction();
		// ���ڼ�¼�ı�1���ı�2�е�����  text[0]�����ļ�1 
		List<String> text = new ArrayList<String>();
		float num = detaction.startDetaction(fileName1, fileName2,
				simliarityArray, text);
		// 3.�����ƶ�ֵ�������ַ����������request��
		request.setAttribute("num", num);
		request.setAttribute("simliarityArray", simliarityArray);
		request.setAttribute("text", text);
		// 4.������ת����index.jsp��
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
