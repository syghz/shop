package com.netease.course.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.netease.course.model.Content;
import com.netease.course.model.Trx;
import com.netease.course.service.ContentService;
import com.netease.course.service.TrxService;



/**
 * 页面跳转controller
 * @author Administrator
 *
 */
@Controller
public class pageController {
	@Autowired
	private ContentService contentService;
	@Autowired
	private TrxService trxService;
	
	/**
	 * 默认打开首页
	 * @return
	 */
	@RequestMapping(value="/")
	public String showIndex(ModelMap map,HttpSession hs){
		@SuppressWarnings("unchecked")
		Map<String, Object> user = (Map<String, Object>)hs.getAttribute("user");
		List<Map<String, Object>> productList =new ArrayList<Map<String,Object>>();
		List<Content> list = contentService.getAllContent();
		for(Content ct :list){
			Trx trx = trxService.getTrxByContentId(ct.getId());
			Map<String, Object> product =new HashMap<String,Object>();
			if (trx!=null && ct.getId()==trx.getContentid()) {
				product.put("id", ct.getId());
				product.put("title", ct.getTitle());
				product.put("image", ct.getImage());
				product.put("price", ct.getPrice());
				product.put("isSell", true);
				if (user == null) {
					product.put("isBuy", false);
				} else {
					product.put("isBuy", true);
				}
			}
			else {
				product.put("id",ct.getId());
				product.put("title", ct.getTitle());
				product.put("image", ct.getImage());
				product.put("price", ct.getPrice());
				product.put("isSell", false);
				product.put("isBuy", false);
			}
			productList.add(product);
		}
		map.addAttribute("productList",productList );
		map.addAttribute("user",user);
		return "index";
	}
	/**
	 * 登录
	 * @param hs
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(HttpSession hs) {
		@SuppressWarnings("unchecked")
		Map<String, Object> user = (Map<String, Object>) hs.getAttribute("user");
		if (user == null) {
			return "login";
		} else {
			return "/";
		}
	}
	
	/**
	 * 退出登录
	 * @param hs
	 * @return
	 */
	@RequestMapping(value = "/logout")
	public String logout(HttpSession hs) {
		@SuppressWarnings("unchecked")
		Map<String, Object> user = (Map<String, Object>) hs.getAttribute("user");
		if (user == null) {
			return "login";
		} else {
			hs.removeAttribute("user");
			return "login";
		}
	}
	
	/**
	 * 查看详细内容页
	 * @param id
	 * @param map
	 * @param hs
	 * @return
	 */
	@RequestMapping(value = "/show")
	public String show(@RequestParam("id") int id, ModelMap map, HttpSession hs) {
		@SuppressWarnings("unchecked")
		Map<String, Object> user = (Map<String, Object>) hs.getAttribute("user");
		Content ct = contentService.getByContentId(id);
		Map<String, Object> product = new HashMap<String, Object>();
		if (ct == null) {
			;
		} else {
			Trx tx = trxService.getTrxByContentId(id);
			if (tx != null && ct.getId() == tx.getContentid()) {
				product.put("id", ct.getId());
				product.put("title", ct.getTitle());
				product.put("image", ct.getImage());
				product.put("price", ct.getPrice());
				product.put("summary", ct.getSummary());
				product.put("detail", ct.getDetail());
				product.put("isSell", true);
				product.put("buyPrice", tx.getPrice());
				if (user == null) {
					product.put("isBuy", false);
				} else {
					product.put("isBuy", true);
				}
			} else {
				product.put("id", ct.getId());
				product.put("title", ct.getTitle());
				product.put("image", ct.getImage());
				product.put("price", ct.getPrice());
				product.put("summary", ct.getSummary());
				product.put("detail", ct.getDetail());
				product.put("isSell", false);
				product.put("isBuy", false);
			}
		}
		product.put("buyNum", id);
		product.put("saleNum", id);
		map.addAttribute("product", product);
		map.addAttribute("user", user);
		return "show";
	}
	
	/**
	 * 编辑页
	 * @param id
	 * @param map
	 * @param hs
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String apiEdit(@RequestParam("id") int id, ModelMap map, HttpSession hs) {
		@SuppressWarnings("unchecked")
		Map<String, Object> user = (Map<String, Object>) hs.getAttribute("user");
		if (user == null || (Integer) user.get("usertype") == 0) {
			return "login";
		} else {
			Content content = contentService.getByContentId(id);
			map.addAttribute("product", content);
			return "edit";
		}
	}
	/**
	 * 编辑提交处理
	 * @param id
	 * @param title
	 * @param summary
	 * @param detail
	 * @param image
	 * @param price
	 * @param map
	 * @param hs
	 * @return
	 */
	@RequestMapping(value = "/editSubmit", method = RequestMethod.POST)
	public String apiEditSummit(@RequestParam("id") int id, @RequestParam("title") String title,
			@RequestParam("summary") String summary, @RequestParam("detail") String detail,
			@RequestParam("image") String image, @RequestParam("price") int price, ModelMap map, HttpSession hs) {
		@SuppressWarnings("unchecked")
		Map<String, Object> user = (Map<String, Object>) hs.getAttribute("user");
		if (user == null || (Integer) user.get("usertype") == 0) {
			return "login";
		} else {
			Content content = new Content();
			map.addAttribute("id", id);
			map.addAttribute("title", title);
			map.addAttribute("summary", summary);
			map.addAttribute("detail", detail);
			map.addAttribute("image", image);
			map.addAttribute("price", price);
			content.setId(id);
			content.setTitle(title);
			content.setSummary(summary);
			content.setTitle(title);
			content.setImage(image);
			content.setPrice(price);
			content.setDetail(detail);
			contentService.updateContent(content);
			map.addAttribute("product", content);
			return "editSubmit";
		}
	}
	/**
	 * 发布显示
	 * @return
	 */
	@RequestMapping(value = "/public", method = RequestMethod.GET)
	public String apiPublic() {
		return "public";
	}
	/**
	 * 发布处理页
	 * @param title
	 * @param summary
	 * @param detail
	 * @param image
	 * @param price
	 * @param map
	 * @param hs
	 * @return
	 */
	@RequestMapping(value = "/publicSubmit", method = RequestMethod.POST)
	public String apiPublicSubmit(@RequestParam("title") String title, @RequestParam("summary") String summary,
			@RequestParam("detail") String detail, @RequestParam("image") String image,
			@RequestParam("price") int price, ModelMap map, HttpSession hs) {
		@SuppressWarnings("unchecked")
		Map<String, Object> user = (Map<String, Object>) hs.getAttribute("user");
		if (user == null || (Integer) user.get("usertype") == 0) {
			return "login";
		} else {
			Content content = new Content();
			content.setTitle(title);
			content.setPrice(price);
			content.setSummary(summary);
			content.setImage(image);
			content.setDetail(detail);
			contentService.insertContent(content);
			Map<String, Object> product = new HashMap<String, Object>();
			product.put("id", content.getId());
			map.addAttribute("product", product);
			return "publicSubmit";
		}
	}
	/**
	 * 财务页面加载
	 * @param map
	 * @param hs
	 * @return
	 */
	@RequestMapping(value = "/account")
	public String account(ModelMap map, HttpSession hs) {
		@SuppressWarnings("unchecked")
		Map<String, Object> user = (Map<String, Object>) hs.getAttribute("user");
		if (user == null || (Integer) user.get("usertype") == 1) {
			return "login";
		} else {
			List<Map<String, Object>> buyList = new ArrayList<Map<String, Object>>();
			List<Trx> list = trxService.getAllTrx();
			for (Trx tx : list) {
				Content content = contentService.getByContentId(tx.getContentid());
				Map<String, Object> buyMap = new HashMap<String, Object>();
				buyMap.put("id", content.getId());
				buyMap.put("title", content.getTitle());
				buyMap.put("image", content.getImage());
				buyMap.put("buyNum", tx.getId());
				buyMap.put("buyPrice", tx.getPrice());
				buyMap.put("buyTime", tx.getTime());
				buyMap.put("total", tx.getPrice() * tx.getId());
				buyList.add(buyMap);
			}
			map.addAttribute("buyList", buyList);
			return "account";
		}
	}
	/**
	 * 购物车页面
	 * @return
	 */
	@RequestMapping(value = "/settleAccount", method = RequestMethod.GET)
	public String apiSettleAccount() {
		return "settleAccount";
	}
	
}
