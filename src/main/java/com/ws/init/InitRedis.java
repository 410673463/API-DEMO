package com.ws.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;


/**
 * 启动项目加载缓存资源
 * @author zhanghan
 *
 */
@Component
public class InitRedis implements InitializingBean{
	private static final Logger LOGGER = LoggerFactory.getLogger(InitRedis.class);
	
	@Autowired
	private  RedisTemplate redisTemplate;
	
	
	@Override
	public void afterPropertiesSet() throws Exception {
		
	}

}
