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
 * 用于描述相似度
 * 
 * @author FAFAFA
 * 
 */
public class Detaction {
	float similarity;

	/**
	 * 开始 比对两个文件相似度
	 * 
	 * @param fileName1
	 *            文件1的名称
	 * @param fileName2
	 *            文件2的名称
	 * @param simliarityArray
	 *            记录相似语句存储的数组
	 * @param text
	 *            文本1和文本2内容 List<String>
	 * @return 相似度
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
		// 两文档进行相似度比对
		float xiangsidu = parse(sf, tf, simliarityArray, text);
		if (xiangsidu > 100) {
			xiangsidu = 100;
		}
		// 修改参数sStr和tStr从而返回参数
		System.out.println("相似度" + xiangsidu);

		return xiangsidu;
	}

	/**
	 * 判断目标文件与目标文件夹的相似度
	 * 
	 * @param sf
	 *            目标文件
	 * @param tf
	 *            比对文件
	 * @param simliarityArray
	 *            记录相似语句存储的数组
	 * @param text
	 *            文本1和文本2内容 List<String>
	 * @return 相似度
	 */
	public float parse(File sf, File tf, List<String> simliarityArray,
			List<String> text) {
		// 记录相同句子的个数
		int TRUE = 0;
		String sStr = "";
		String tStr = "";
		BufferedReader br = null;
		// 分割文档之后的字符串数组
		String[] sSplit = null;
		String[] tSplit = null;
		String s = "";
		Pattern p = null;
		List<Pattern> Patterns = new ArrayList<Pattern>();

		try {
			br = new BufferedReader(new FileReader(sf));
			// 读取一个文本行。通过下列字符之一即可认为某行已终止
			// ：换行 ('\n')、回车 ('\r') 或回车后直接跟着换行。
			while ((s = br.readLine()) != null) {
				s = s.trim();
				sStr = sStr + s;
			}

			sSplit = sStr.split("[。？！.?!]");
			// 用正则表达式处理sf被分割后的字符串,并将结果存入patterns字符串列表中
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
			// 用参数返回值
			text.add(sStr);
			text.add(tStr);

			tSplit = tStr.split("[。？！.?!]");
			// 正则表达式数组中每一个匹配tf分割数组中的每个字符串，如果匹配则TRUE+1
			for (int j = 0; j < Patterns.size(); j++)
				for (int i = 0; i < tSplit.length; i++) {
					p = Patterns.get(j);
					// 判断正则表达式和字符串是否匹配
					Matcher m = p.matcher(tSplit[i]);
					// System.out.println(m.matches());
					if (m.matches()) {
						TRUE++;

						System.out.println("");
						System.out.print("相同语句：");
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
