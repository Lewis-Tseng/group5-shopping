package jdbc.util_CompositeQueryProduct;

import java.util.*;

public class jdbcUtil_CompositeQuery_Product {
	public static String get_aCondition_For_Oracle(String columnName, String value) {

		String aCondition = null;

		if ("pro_pri".equals(columnName) || "pro_sto".equals(columnName) || "pro_sta".equals(columnName)) // 用於其他
			aCondition = columnName + "=" + value;
		else if ("pro_no".equals(columnName) || "cat_no".equals(columnName) || "pro_nam".equals(columnName) || "pro_con".equals(columnName) || "del_add".equals(columnName)) // 用於varchar
			aCondition = columnName + " like '%" + value + "%'";
		else if ("ord_dat".equals(columnName))                          // 用於Oracle的date
			aCondition = "to_char(" + columnName + ",'yyyy-mm-dd')='" + value + "'";

		return aCondition + " ";
	}

	public static String get_WhereCondition(Map<String, String[]> map) {
		Map newMap = jdbcUtil_CompositeQuery_Product.getParameterMap(map);
		Set<String> keys = newMap.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for (String key : keys) {
//			String value = ((String[])map.get(key))[0];
			String value = (String) newMap.get(key);
			if (value != null && value.trim().length() != 0	&& !"action".equals(key)) {
				count++;
				String aCondition = get_aCondition_For_Oracle(key, value.trim());

				if (count == 1)
					whereCondition.append(" where " + aCondition);
				else
					whereCondition.append(" and " + aCondition);

				System.out.println("有送出查詢資料的欄位數count = " + count);
			}
		}
		
		return whereCondition.toString();
	}

	public static void main(String argv[]) {

		// 配合 req.getParameterMap()方法 回傳 java.util.Map<java.lang.String,java.lang.String[]> 之測試
		Map<String, String[]> map = new TreeMap<String, String[]>();
		map.put("empno", new String[] { "7001" });
		map.put("ename", new String[] { "KING" });
		map.put("job", new String[] { "PRESIDENT" });
		map.put("hiredate", new String[] { "1981-11-17" });
		map.put("sal", new String[] { "5000.5" });
		map.put("comm", new String[] { "0.0" });
		map.put("deptno", new String[] { "10" });
		map.put("action", new String[] { "getXXX" }); // 注意Map裡面會含有action的key

		String finalSQL = "select * from product_order "
				          + jdbcUtil_CompositeQuery_Product.get_WhereCondition(map)
				          + "order by ord_no";
		System.out.println("●●finalSQL = " + finalSQL);

	}
	
	public static Map getParameterMap(Map<String, String[]> map) {
	    // 引數Map
	    Map properties = map;
	    // 返回值Map
	    Map returnMap = new HashMap();
	    Iterator entries = properties.entrySet().iterator();
	    Map.Entry entry;
	    String name = "";
	    String value = "";
	    while (entries.hasNext()) {
	        entry = (Map.Entry) entries.next();
	        name = (String) entry.getKey();
	        Object valueObj = entry.getValue();
	        if(null == valueObj){
	            value = "";
	        }else if(valueObj instanceof String[]){
	            String[] values = (String[])valueObj;
	            for(int i=0;i<values.length;i++){
	                value = values[i] + ",";
	            }
	            value = value.substring(0, value.length()-1);
	        }else{
	            value = valueObj.toString();
	        }
	        returnMap.put(name, value);
	    }
	    return returnMap;
	}
	
	
}