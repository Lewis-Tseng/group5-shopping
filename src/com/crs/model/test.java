package com.crs.model;

public class test {

	public static void main(String[] args) {

		weekCrsTransfer("0011000");

	}

	public static String weekCrsTransfer(String week_crs) {
		String weekCrs = week_crs;
		String date[] = new String[7];
		int index = 0;
		int j = 1;

		String anwser = "";

		for (int i = 0; i < 7; i++) {
			date[index] = weekCrs.substring(i, j);


			index++;
			j++;
		}

		if (date[0].equals("1")) {
			anwser += "一";
		}
		if (date[1].equals("1")) {
			anwser += "二";
		}
		if (date[2].equals("1")) {
			anwser += "三";
		}
		if (date[3].equals("1")) {
			anwser += "四";
		}
		if (date[4].equals("1")) {
			anwser += "五";
		}
		if (date[5].equals("1")) {
			anwser += "六";
		}
		if (date[6].equals("1")) {
			anwser += "日";
		}

		return anwser;

	}

}
