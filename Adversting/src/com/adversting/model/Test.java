package com.adversting.model;

import java.io.*;
import java.sql.Timestamp;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		AdverstingJDBCDAO dao = new AdverstingJDBCDAO();

		// 新增
//		AdverstingVO adverstingVO1 = new AdverstingVO();
//		try {
//			adverstingVO1.setCom_no("2005");
//			adverstingVO1.setStartDay(Timestamp.valueOf("2017-7-18 20:20:20"));
//			adverstingVO1.setEndDay(Timestamp.valueOf("2017-8-18 20:20:20"));
//			adverstingVO1.setPrice(17000);
//
//			BufferedReader br = new BufferedReader(new FileReader("items/text2.txt"));
//			StringBuilder sb = new StringBuilder();
//			String str;
//			while ((str = br.readLine()) != null) {
//				sb.append(str);
//				sb.append("\n");
//			}
//			br.close();
//			
//			FileInputStream fis1 = new FileInputStream(new File("items/b03.jpg"));
//			ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
//			byte[] buffer1 = new byte[8192];
//			int i;
//			while ((i = fis1.read(buffer1)) != -1) {
//				baos1.write(buffer1, 0, i);
//			}
//			baos1.close();
//			fis1.close();
//			
//			FileInputStream fis2 = new FileInputStream(new File("items/video.mp4"));
//			ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
//			byte[] buffer2 = new byte[8192];
//			int j;
//			while ((j = fis2.read(buffer2)) != -1) {
//				baos2.write(buffer2, 0, j);
//			}
//			baos2.close();
//			fis2.close();
//			
//			adverstingVO1.setText(sb.toString());
//			adverstingVO1.setImg(baos1.toByteArray());
//			adverstingVO1.setVdo(baos2.toByteArray());
//			adverstingVO1.setStatus("0");
//		} catch (IOException e) {
//			System.out.println(e);
//		}
//		dao.insert(adverstingVO1);
//		System.out.println("新增成功");

		// 修改
//		AdverstingVO adverstingVO2 = new AdverstingVO();
//		try {
//			adverstingVO2.setCom_no("2001");
//			adverstingVO2.setStartDay(Timestamp.valueOf("2017-9-09 00:00:00"));
//			adverstingVO2.setEndDay(Timestamp.valueOf("2017-9-09 00:00:00"));
//			adverstingVO2.setPrice(2000);
//
//			BufferedReader br = new BufferedReader(new FileReader("items/text.txt"));
//			StringBuilder sb = new StringBuilder();
//			String str;
//			while ((str = br.readLine()) != null) {
//				sb.append(str);
//				sb.append("\n");
//			}
//			br.close();
//			
//			FileInputStream fis1 = new FileInputStream(new File("items/wedding6.jpg"));
//			ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
//			byte[] buffer1 = new byte[8192];
//			int i;
//			while ((i = fis1.read(buffer1)) != -1) {
//				baos1.write(buffer1, 0, i);
//			}
//			baos1.close();
//			fis1.close();
//			
//			FileInputStream fis2 = new FileInputStream(new File("items/video.mp4"));
//			ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
//			byte[] buffer2 = new byte[8192];
//			int j;
//			while ((j = fis2.read(buffer2)) != -1) {
//				baos2.write(buffer2, 0, j);
//			}
//			baos2.close();
//			fis2.close();
//			
//			adverstingVO2.setText(sb.toString());
//			adverstingVO2.setImg(baos1.toByteArray());
//			adverstingVO2.setVdo(baos2.toByteArray());
//			adverstingVO2.setStatus("1");
//			adverstingVO2.setAdv_no("0020");
//		} catch (IOException e) {
//			System.out.println(e);
//		}
//		dao.update(adverstingVO2);
//		System.out.println("修改成功");
//
//		// 刪除
		dao.delete("0020");
		System.out.println("刪除成功");
//
//		// 查詢一筆
//		AdverstingVO adverstingVO3 = dao.findByPrimaryKey("0001");
//		System.out.print(adverstingVO3.getCom_no() + ",");
//		System.out.print(adverstingVO3.getStartDay() + ",");
//		System.out.print(adverstingVO3.getEndDay() + ",");
//		System.out.print(adverstingVO3.getPrice() + ",");
//		System.out.print(adverstingVO3.getText());
//		System.out.print(adverstingVO3.getImg() + ",");
//		System.out.print(adverstingVO3.getVdo() + ",");
//		System.out.print(adverstingVO3.getStatus());
//		System.out.println("查詢成功");
//
//		// 查詢ALL
//		List<AdverstingVO> list = dao.getAll();
//		for (AdverstingVO aAdversting : list) {
//			System.out.print(aAdversting.getAdv_no() + ",");
//			System.out.print(aAdversting.getCom_no() + ",");
//			System.out.print(aAdversting.getStartDay() + ",");
//			System.out.print(aAdversting.getEndDay() + ",");
//			System.out.print(aAdversting.getPrice() + ",");
//			System.out.print(aAdversting.getText() + ",");
//			System.out.print(aAdversting.getImg() + ",");
//			System.out.print(aAdversting.getVdo() + ",");
//			System.out.print(aAdversting.getStatus());
//			System.out.println();
//		}
//		System.out.println("查詢成功");
	}
}
