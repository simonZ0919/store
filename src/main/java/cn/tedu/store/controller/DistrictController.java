package cn.tedu.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.store.entity.District;
import cn.tedu.store.entity.ResponseResult;
import cn.tedu.store.service.IDistrictService;

@RestController
@RequestMapping("/district")
public class DistrictController extends BaseController {
	@Autowired private IDistrictService service;
	
	// path: "list/parent"
	@RequestMapping("/list/{parent}")
	public ResponseResult<List<District>> getListByParent(
			@PathVariable("parent") String parent){
		// get list of data
		List<District> list=service.getListByParent(parent);
		return new ResponseResult<List<District>>(SUCCESS, list);
	}
}
