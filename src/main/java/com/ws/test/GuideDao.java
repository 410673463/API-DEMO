package com.ws.test;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;


@CacheConfig(cacheNames="guide")
public interface GuideDao extends JpaRepository<Guide,Integer>{
	@Cacheable(key="#p0")
	Guide getByGuId(int id);
	
	@CachePut(key="#p0.guId")
	@Override
	Guide save(Guide guide);

}
