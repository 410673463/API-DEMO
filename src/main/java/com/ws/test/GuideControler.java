package com.ws.test;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ws.pojo.Result;
import com.ws.pojo.ResultCode;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
public class GuideControler {
	
	@Autowired
	private IGuideService guideService;
	
	@RequestMapping("/getAll")
	public List<Guide> getAll(){
		return guideService.findAll();
	}
	
	@RequestMapping(value="/get",method=RequestMethod.GET)
	public Result  get(int id){
		Guide guide = guideService.get(id);
		 Result result = new Result();
		if(guide != null){
			return result.success(guide);
		}
		return result.failure(ResultCode.RESULE_DATA_NONE, null);
	}
	
	@RequestMapping("/init")
	public String init(){
		int i = 10;
		for (int j = 0; j < i; j++) {
			Guide guide = new Guide();
			guide.setCreateTime(new Date());
			guide.setName("test"+j);
			//guideService.save(guide);
		}
		return "ok";
	}
	
	@RequestMapping("/save")
	public String save() {
		Guide guide = new Guide();
		guide.setCreateTime(new Date());
		guide.setName("testGudieAdd");
		guideService.save(guide);
		return "ok";
	}
}
