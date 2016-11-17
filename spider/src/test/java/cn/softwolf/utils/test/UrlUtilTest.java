package cn.softwolf.utils.test;

import java.util.List;

import org.junit.Test;

import cn.softwolf.utils.UrlUtil;


public class UrlUtilTest {

	@Test
	public void testBuildListUrl() {
		UrlUtil.setKeyword("张三");
		List<String> urls=  UrlUtil.BuildListUrl();
		System.out.println(urls.size());
		System.out.println(urls);
	}

}
