package json;

import java.util.ArrayList;
import java.util.List;

import bean.Stock;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonTest {
	/**
	 * 如何将java对象转换成
	 * json字符串。
	 * {"code":"000410","name":"沈阳机床",
	 * "price":10}   
	 */
	public static void test1(){
		Stock s = new Stock();
		s.setCode("000410");
		s.setName("沈阳机床");
		s.setPrice(10);
		//使用json官方提供的工具 json-lib
		//来将java对象转换成json字符串。
		JSONObject jsonObj = 
				JSONObject.fromObject(s);
		String jsonStr = jsonObj.toString();
		System.out.println(jsonStr);
	}
	
	/**
	 * 如何将多个对象组成的数组或者集合
	 * 转换成json字符串。
	 */
	public static void test2(){
		List<Stock> stocks = 
			new ArrayList<Stock>();
		for(int i = 0; i < 3; i++){
			Stock s = new Stock();
			s.setCode("00041" + i);
			s.setName("沈阳机床" + i);
			s.setPrice(10 + i);
			stocks.add(s);
		}
		//fromObject方法也可以使用数组
		//作为参数。
		JSONArray jsonArr = 
				JSONArray.fromObject(stocks);
		String jsonStr = jsonArr.toString();
		System.out.println(jsonStr);
		
	}

	public static void main(String[] args) {
			test2();
	}

}
