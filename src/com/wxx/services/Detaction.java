package com.wxx.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * �����������ƶ�
 * 
 * @author FAFAFA
 * 
 */
public class Detaction {
	float similarity;

	/**
	 * ��ʼ �ȶ������ļ����ƶ�
	 * 
	 * @param fileName1
	 *            �ļ�1������
	 * @param fileName2
	 *            �ļ�2������
	 * @param simliarityArray
	 *            ��¼�������洢������
	 * @param text
	 *            �ı�1���ı�2���� List<String>
	 * @return ���ƶ�
	 */

	public float startDetaction(String fileName1, String fileName2,
			List<String> simliarityArray, List<String> text) {
		fileName1 = "D:\\temp\\" + fileName1;
		fileName2 = "D:\\temp\\" + fileName2;
		System.out.println("fileName1" + fileName1);
		System.out.println("fileName2" + fileName2);
		File sf = new File(fileName1);
		File tf = new File(fileName2);

		String a = "";
		String b = "";
		// ���ĵ��������ƶȱȶ�
		float xiangsidu = parse(sf, tf, simliarityArray, text);
		if (xiangsidu > 100) {
			xiangsidu = 100;
		}
		// �޸Ĳ���sStr��tStr�Ӷ����ز���
		System.out.println("���ƶ�" + xiangsidu);

		return xiangsidu;
	}

	/**
	 * �ж�Ŀ���ļ���Ŀ���ļ��е����ƶ�
	 * 
	 * @param sf
	 *            Ŀ���ļ�
	 * @param tf
	 *            �ȶ��ļ�
	 * @param simliarityArray
	 *            ��¼�������洢������
	 * @param text
	 *            �ı�1���ı�2���� List<String>
	 * @return ���ƶ�
	 */
	public float parse(File sf, File tf, List<String> simliarityArray,
			List<String> text) {
		// ��¼��ͬ���ӵĸ���
		int TRUE = 0;
		String sStr = "";
		String tStr = "";
		BufferedReader br = null;
		// �ָ��ĵ�֮����ַ�������
		String[] sSplit = null;
		String[] tSplit = null;
		String s = "";
		Pattern p = null;
		List<Pattern> Patterns = new ArrayList<Pattern>();

		try {
			br = new BufferedReader(new FileReader(sf));
			// ��ȡһ���ı��С�ͨ�������ַ�֮һ������Ϊĳ������ֹ
			// ������ ('\n')���س� ('\r') ��س���ֱ�Ӹ��Ż��С�
			while ((s = br.readLine()) != null) {
				s = s.trim();
				sStr = sStr + s;
			}

			sSplit = sStr.split("[������.?!]");
			// ��������ʽ����sf���ָ����ַ���,�����������patterns�ַ����б���
			for (int i = 0; i < sSplit.length; i++) {
				// System.out.println(sSplit[i]);
				p = Pattern.compile(sSplit[i]);
				Patterns.add(p);
			}

			br = new BufferedReader(new FileReader(tf));
			while ((s = br.readLine()) != null) {
				s = s.trim();
				tStr = tStr + s;
			}
			// �ò�������ֵ
			text.add(sStr);
			text.add(tStr);

			tSplit = tStr.split("[������.?!]");
			// ������ʽ������ÿһ��ƥ��tf�ָ������е�ÿ���ַ��������ƥ����TRUE+1
			for (int j = 0; j < Patterns.size(); j++)
				for (int i = 0; i < tSplit.length; i++) {
					p = Patterns.get(j);
					// �ж�������ʽ���ַ����Ƿ�ƥ��
					Matcher m = p.matcher(tSplit[i]);
					// System.out.println(m.matches());
					if (m.matches()) {
						TRUE++;

						System.out.println("");
						System.out.print("��ͬ��䣺");
						// System.out.println(sSplit[i]);
						simliarityArray.add(sSplit[i]);
					}
				}
			System.out
					.println("---------------TRUE----------------------------"
							+ TRUE);
			similarity = (float) TRUE
					/ (float) (tSplit.length + sSplit.length - TRUE);
			similarity = similarity * 100;
			System.out.println(sStr);
			System.out.println(tStr);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
					br = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return similarity;

	}
}
