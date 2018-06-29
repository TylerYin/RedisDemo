package com.redis.service;

import java.text.SimpleDateFormat;
import java.util.Date;




import org.apache.commons.lang3.RandomStringUtils;
//import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.redis.domain.Product;

@Component
public class RedisSendMessageService {

	@Autowired
	private RedisTemplate<String, Product> redisTemplate;

	private SimpleDateFormat SDF_WITH_DELIMITER = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	/**
	 * 点对点模式
	 * 
	 * Redis中写入对象
	 */
	public void writeProductIntoRedisByP2P(String cart, Product product) {
		redisTemplate.opsForList().rightPush(cart, product);
		System.out.println("添加产品编号 为" + product.getNo() + " 的产品");
	}

	/**
	 * 点对点模式
	 * 
	 * Redis中读取对象
	 */
	public void readProductFromRedis(String cart) {
		Product product = redisTemplate.opsForList().leftPop(cart);
		if (null != product) {
			System.out.println("当前获取到的产品为编号 " + product.getNo() + " 的产品");
		} else {
			System.out.println("redis is empty");
		}
	}

	/**
	 * 生成Product
	 * 
	 * @return
	 */
	public Product generateProduct() {
		Product product = new Product();
		product.setNo(RandomStringUtils.randomNumeric(6));
		product.setName(RandomStringUtils.randomAlphabetic(6));
		product.setNum(50000);
		product.setCreateDate(SDF_WITH_DELIMITER.format(new Date()));
		product.setManufacture("臻诚科技");
		return product;
	}
}
