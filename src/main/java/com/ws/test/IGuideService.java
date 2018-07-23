package com.ws.test;

import java.util.List;

public interface IGuideService {
	List<Guide> findAll();
	Guide get(int id);
	int save(Guide guide);
	void delete(int id);
}
