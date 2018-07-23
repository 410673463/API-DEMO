package com.ws.test;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
@Service
public class GuideService implements IGuideService{
	private static final Logger LOGGER = LoggerFactory.getLogger(GuideService.class);
	
	@Autowired
	private GuideDao guideDao;
	
	@Autowired
	private RedisTemplate<String,Object> redisTemplate;

	@Override
	public List<Guide> findAll() {
		List<Guide> list = null;
		if(redisTemplate.hasKey("ALL_GUIDE")){
			list = (List<Guide>) redisTemplate.opsForValue().get("ALL_GUIDE");
		}
		System.out.println(redisTemplate.hasKey("ALL_GUIDE"));
		if(list != null && list.size() > 0){
			LOGGER.info("缓存--查询全部");
			return list;
		}
		return guideDao.findAll();
	}

	@Override
	public Guide get(int id) {
		return guideDao.getByGuId(id);
	}

	@Override
	public int save(Guide guide) {
		guideDao.save(guide);
		return guide.getGuId();
	}

	@Override
	public void delete(int id) {
		String gId = "guide_"+id;
		guideDao.deleteById(id);
		if(redisTemplate.hasKey(gId)){
			redisTemplate.delete(gId);
			LOGGER.info("缓存--删除delete");
		}
	}
	
	
	
}
