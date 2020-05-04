package com.forum.controller;

import org.json.JSONObject;
import org.jsoup.Jsoup; 
import org.jsoup.nodes.Document;

import com.gro.group.groupService;

import redis.clients.jedis.Jedis;

import java.net.*;
import java.sql.Timestamp;
import java.io.*;
import java.io.IOException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class gymServlet {
		
	public static Map<String,Map> gymNum(){
		
		 Map<String, Map> GYM = new HashMap<String,Map>() ;
		 Map <String,String> gymMap = new HashMap<String,String>() ;
		 Map <String,String> swimMap = new HashMap<String,String>() ;
		 Map <Integer,String> locMap = new HashMap<Integer,String>() ;
		 Map <Integer,String> swimLimit = new HashMap<Integer,String>() ;
		 Map <Integer,String> gymLimit = new HashMap<Integer,String>() ;
		 
		String loc[][] ={
			{"北投","80","200","http://www.btsport.org.tw/zh-TW/onsitenum","h3.gym_flow span.flow_number","h3.swimming_flow span.flow_number"},
			{"士林","100","200","http://www.slsc-taipei.org/","li#data1 span.number","li#data2 span.number"},
			{"內湖","130","200","https://nhsc.cyc.org.tw/api","gym\":[\"","\",","7","0","swim\":[\"","]}","8","-11"},
			{"南港","110","200","https://ngsc.cyc.org.tw/api","gym\":[\"","\",","7","0","swim\":[\"","]}","8","-11"},
//			{"松山","150","400","http://sssc.com.tw/","span.venue_number","span.venue_number"},
			{"信義","65","164","https://xysc.cyc.org.tw/api","gym\":[\"","\",","7","0","swim\":[\"","]}","8","-11"},
//			{"大同","90","230","http://www.dtsc-wdyg.com.tw/web/ajax/check_po.jsp","健身房","人","3","-4","游泳池","人\"}","3","-4"},
			{"中山","70","180","https://cssc.cyc.org.tw/api","gym\":[\"","\",","7","0","swim\":[\"","]}","8","-11"},
//			{"萬華","200","180","http://whsc.com.tw/","span.venue_number","span.venue_number"},
			{"中正","120","300","http://www.tpejjsports.com.tw/zh-TW/onsitenum","h3.gym_flow span.flow_number","h3.swimming_flow span.flow_number"},
			{"大安","85","350","https://dasc.cyc.org.tw/api","gym\":[\"","\",","7","0","swim\":[\"","]}","8","-11"},
			{"文山","85","200","https://wssc.cyc.org.tw/api","gym\":[\"","\",","7","0","swim\":[\"","]}","8","-11"},
//			{"新莊","150","350","https://www.xzsports.com.tw/","section#my-section font.z2","section#my-section font.z1"},
			{"蘆洲","75","210","https://lzcsc.cyc.org.tw/api","gym\":[\"","\",","7","0","swim\":[\"","]}","8","-11"},
			{"淡水","70","400","http://www.tssc.tw/","li.gym span.number-current","li.pool span.number-current"},
//			三重國民運動中心 網站太爛 沒有
			{"土城","90","230","https://tccsc.cyc.org.tw/api","gym\":[\"","\",","7","0","swim\":[\"","ice","8","-14"},
			{"中和","80","400","http://www.zhsports.com.tw/zh-TW/onsitenum","h3.gym_flow span.flow_number","h3.swimming_flow span.flow_number"},
			{"板橋","80","400","http://bqsports.com.tw/zh-TW/onsitenum","h3.gym_flow span.flow_number","h3.swimming_flow span.flow_number"},
//			新五泰國民運動中心 網站太爛 沒有
			{"永和","65","300","https://yhcsc.cyc.org.tw/api","gym\":[\"","\",","7","0","swim\":[\"","]}","8","-11"},
			{"汐止","80","200","https://xzcsc.cyc.org.tw/api","gym\":[\"","\",","7","0","swim\":[\"","]}","8","-11"},
//			樹林國民運動中心 網站太爛 沒有
//			{"鶯歌","70",null,"https://scysports.com.tw/","gym\":[\"","\",","7","0","swim\":[\"","]}","8","-11"},
//			{"三鶯","80","300","https://scysports.com.tw/","gym\":[\"","\",","7","0","swim\":[\"","]}","8","-11"},
			{"桃園","90","120","https://tycsc.cyc.org.tw/api","gym\":[\"","\",","7","0","swim\":[\"","]}","8","-11"},
//			南平國民運動中心  網站太爛 沒有
			{"中壢","90","180","https://zlcsc.cyc.org.tw/api","gym\":[\"","\",","7","0","swim\":[\"","]}","8","-11"},
//			{"中山","70","180","https://cssc.cyc.org.tw/api","gym\":[\"","\",","7","0","swim\":[\"","]}","8","-11"},
//			{"中山","70","180","https://cssc.cyc.org.tw/api","gym\":[\"","\",","7","0","swim\":[\"","]}","8","-11"},
			
		};
			
			for(int i=0; i<loc.length; i++) {
				
				String[] number=null;
				if(loc[i][3].contains("api")) {
					number =getFromWebOrApi(loc[i][3],loc[i][4],loc[i][5],loc[i][6],loc[i][7],loc[i][8],loc[i][9],loc[i][10],loc[i][11]);
				}else {
					number =getFromWebOrApi(loc[i][3],loc[i][4],loc[i][5]);
					if( (number[0].contains("人") || number[1].contains("人"))) { //去掉人這個字
						number[0] = number[0].substring(0, number[0].length()-1);
						number[1] = number[1].substring(0, number[1].length()-1);
					}
				}
//				System.out.println(loc[i][0] + "健身房 " + number[0] + " / " + loc[i][1]+ "人" + ", 游泳池 " + number[1] + " / " + loc[i][2] + "人");
				locMap.put(i,loc[i][0]);
				gymLimit.put(i,loc[i][1]);
				swimLimit.put(i,loc[i][2]);
				gymMap.put(loc[i][0], number[0]);
				swimMap.put(loc[i][0], number[1]);
				
			}
			GYM.put("游泳", swimMap);
			GYM.put("健身", gymMap);
			GYM.put("地點", locMap);
			GYM.put("泳池上限", swimLimit);
			GYM.put("健身上限", gymLimit);
			return GYM ;
	} 
	
	
	
	
	
	
	//從網頁 Jsoup 爬
	public static String[] getFromWebOrApi(String url, String gym_tag, String swim_tag) {
		// 利用URL解析網址
		URL urlObj = null;
		try {
			urlObj = new URL(url);
		} catch (MalformedURLException e) {
			System.out.println("The url was malformed!");
			return null;
		} // URL連接
		URLConnection urlCon = null;
		try {
			urlCon = urlObj.openConnection(); //
			// 打開URL連接 // 將HTML內容解析成UTF-8格式
			Document doc = Jsoup.parse(urlCon.getInputStream(), "utf-8", url);
			// 刷選需要的網頁內容 String

			String[] number = { doc.select(gym_tag).text(), doc.select(swim_tag).text() };
			return number;
			
		} catch (IOException e) {
			System.out.println("There was an error connecting to the URL");
			return null;
		}
	}
	//從API 爬
	public static String[] getFromWebOrApi(String url, String index_a1, String index_a2,String a1,String a2, String index_b1, String index_b2,String b1,String b2) {
		String[]number=new String[2];
		try {
			String httpsURL = url; 
			// 以HttpsUtil工具類別建立URLConnection物件
			URLConnection urlConn = HttpsUtil.getURLConnection(httpsURL);
			// 以下模擬瀏覽器的user-agent請求標頭(Servlet講義p79-範例HeaderSnoop.java ; 或講義p185-範例EL10.jsp)
			urlConn.setRequestProperty("user-agent",
					"Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; rv:11.0) like Gecko");
			// 以URLConnection物件取得輸入資料流
			urlConn.connect();
			InputStream ins = urlConn.getInputStream();
			// 建立URL資料流
			BufferedReader br = new BufferedReader(new InputStreamReader(ins, "UTF-8"));
			String data;
			while ((data = br.readLine()) != null) {
				
//				JSONObject jsonObject = new JSONObject(data);
//				String sendTo = jsonObject.getString("swim[0]");
//				System.out.println("send"+ sendTo);
//				
				number[0] = data.substring(data.indexOf(index_a1)+Integer.parseInt(a1),data.indexOf(index_a2)+Integer.parseInt(a2));
				number[1] = data.substring(data.indexOf(index_b1)+Integer.parseInt(b1),data.indexOf(index_b2)+Integer.parseInt(b2));
			}
			br.close();
			ins.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return number;
	}
//	public static String getFromAPI(String url, String index_a1, String index_a2,String a1,String a2) {
//		String gym_num  = null;
//		try {
//			String httpsURL = url; 
//			// 以HttpsUtil工具類別建立URLConnection物件
//			URLConnection urlConn = HttpsUtil.getURLConnection(httpsURL);
//			// 以下模擬瀏覽器的user-agent請求標頭(Servlet講義p79-範例HeaderSnoop.java ; 或講義p185-範例EL10.jsp)
//			urlConn.setRequestProperty("user-agent",
//					"Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; rv:11.0) like Gecko");
//			// 以URLConnection物件取得輸入資料流
//			urlConn.connect();
//			InputStream ins = urlConn.getInputStream();
//			// 建立URL資料流
//			BufferedReader br = new BufferedReader(new InputStreamReader(ins, "UTF-8"));
//			String data;
//			while ((data = br.readLine()) != null) {
//				gym_num = data.substring(data.indexOf(index_a1)+Integer.parseInt(a1),data.indexOf(index_a2)+Integer.parseInt(a2));
//				
//			}
//			br.close();
//			ins.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return gym_num;
//	}
}

