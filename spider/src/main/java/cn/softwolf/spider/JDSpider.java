package cn.softwolf.spider;

import java.util.List;

import cn.softwolf.utils.UrlUtil;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;

public class JDSpider {
	
	public static void main(String[] args) {
		
		//UrlUtil.setKeyword("手机");
		UrlUtil.setKeyword("零食");
		List<String> urls = UrlUtil.BuildListUrl(); //设置要抓取的关键词
		
		Spider.create(new JDPageProcessor())
		//.addPipeline(new FilePipeline("d:/test"))
		.addPipeline(new MySQLPipeLine())
		.addUrl(urls.toArray(new String[urls.size()]))
		.thread(5).run();
		
		
	}
}
