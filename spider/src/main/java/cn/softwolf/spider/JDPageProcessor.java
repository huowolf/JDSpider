package cn.softwolf.spider;

import java.util.List;

import cn.softwolf.pojo.Product;
import cn.softwolf.utils.UrlUtil;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public class JDPageProcessor implements PageProcessor {

	private Site site = Site.me().setRetryTimes(3)
			// 设置userAgent,否则抓取不到数据
			.setUserAgent(
					"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36")
			.setSleepTime(50);

	public void process(Page page) {
		System.out.println("正在抓取数据......");
		
		// 当前的页面地址
		String currentUrl = page.getUrl().toString();

		for (String url : UrlUtil.BuildListUrl()) {
			if (url.equals(currentUrl)) {
				//String wholeHtml = page.getHtml().toString();
				//page.putField("html", wholeHtml);

				// 得到商品id
				List<String> ids = page.getHtml().css("li.gl-item", "data-sku").all();
				for (String id : ids) {
					// 访问商品详情页
					page.addTargetRequest("http://item.jd.com/" + id + ".html");
					// 访问商品评论数据
					page.addTargetRequest(
							"http://club.jd.com/comment/productCommentSummaries.action?referenceIds=" + id);
					// 访问商品价格数据
					page.addTargetRequest("http://p.3.cn/prices/get?pduid=296602895&skuid=J_" + id);
				}
				
			}
		}
		
		
		if (currentUrl.contains("http://item.jd.com/")) {
			// 提取出商品id
			String id = currentUrl.replace("http://item.jd.com/", "").replace(".html", "");
			
			// 提取出商品name(可能会有不同的页面结构)
			String name = page.getHtml().css("#itemInfo #name h1", "text").toString();
			if(name == null || name.equals("")){
				name = page.getHtml().css(".itemInfo-wrap .sku-name", "text").toString();
			}
			
			// 提取出商品图片地址(可能会有不同的页面结构)
			String img = page.getHtml().css("#spec-n1 img", "src").toString();
			if(img == null || img.equals("")){
				img = page.getHtml().css("#spec-n1 img", "data-origin").toString();
			}

			Product p = new Product();
			p.setId(id);
			p.setName(name);
			p.setImage("http:" + img);
			page.putField("itemDetil", p);
		}
		else if (currentUrl.contains("http://club.jd.com/comment/productCommentSummaries.action")) {
			// 提取出商品id
			String id = page.getJson().jsonPath("$.CommentsCount[0].SkuId").toString();
			// 提取出商品评论量
			String comment = page.getJson().jsonPath("$.CommentsCount[0].CommentCount").toString();
			long commentCount = Long.parseLong(comment);
			// 提取出商品好评率
			String priseRate = page.getJson().jsonPath("$.CommentsCount[0].GoodRateShow").toString();

			Product p = new Product();
			p.setId(id);
			p.setCommentCount(commentCount);
			p.setPraiseRate(priseRate + "%");
			page.putField("itemComment", p);
		} 
		else if (currentUrl.contains("http://p.3.cn/prices/get")) {
			// 提取出商品id
			String id = page.getJson().jsonPath("$[0].id").toString();
			id = id.replace("J_", "");
			// 提取出商品价格
			String price = page.getJson().jsonPath("$[0].p").toString();

			Product p = new Product();
			p.setId(id);
			p.setPrice(Double.parseDouble(price));
			page.putField("itemPrice", p);
		}


	}

	public Site getSite() {
		return site;
	}

}
