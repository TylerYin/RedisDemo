package com.redis.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.redis.domain.Product;
import com.redis.service.RedisSendMessageService;

@Controller
public class HomeController {

	@Autowired
	private RedisSendMessageService redisSendMessageService;

	private final String CART = "cart";

	@RequestMapping(value = "home", method = GET)
	public String test(Model model) {
		return "home";
	}

	@ResponseBody
	@RequestMapping(value = "insert", method = GET)
	public Boolean insert(Model model) {
		Product product = redisSendMessageService.generateProduct();
		redisSendMessageService.writeProductIntoRedisByP2P(CART, product);
		return true;
	}

	@ResponseBody
	@RequestMapping(value = "select", method = GET)
	public Boolean select(Model model) {
		redisSendMessageService.readProductFromRedis(CART);
		return true;
	}
}