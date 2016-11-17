package cn.softwolf.utils;

import java.util.ArrayList;
import java.util.List;

public class UrlUtil {

	private static String keyword;
	
	// 构造列表页的url
	public static List<String> BuildListUrl() {
		List<String> urlList = new ArrayList<String>(); // 列表页的url
		
		if(keyword == null){
			return null;
		}else{
			
			/*
			 * offest=2代表页面上的按销量排序
			 */		
			// 默认列表页(对应着一页的前30条数据，非ajax请求)
			String urlList1 = "http://search.jd.com/Search?keyword=手机&enc=utf-8&offset=2&psort=3&page=1";
			// 默认列表页(对应着一页的后30条数据，ajax请求)
			String urlList2 = "http://search.jd.com/s_new.php?keyword=手机&enc=utf-8&offset=2&psort=3&page=2&s=31&scrolling=y&pos=30";
	
			int sIndex = 31;// 对应url请求参数中的s
	
			// 抓取代表销量较高的20页数据，也就是总共600条数据
			for (int i = 1; i <= 10; i++) {
				if (i % 2 != 0) {
					String url = urlList1.replace("手机", keyword).replace("page=1", "page=" + Integer.toString(i));
					urlList.add(url);
				} else {
					sIndex = (i - 1) * 30 + 1;
					String url = urlList2.replace("手机", keyword).replace("page=2", "page=" + Integer.toString(i))
							.replace("s=31", "s=" + Integer.toString(sIndex));
					urlList.add(url);
				}
			}
		}
		return urlList;
	}


	public static void setKeyword(String keyword) {
		UrlUtil.keyword = keyword;
	}
	
	
}
