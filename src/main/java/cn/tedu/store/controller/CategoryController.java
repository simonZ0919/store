package cn.tedu.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.store.entity.Category;
import cn.tedu.store.entity.ResponseResult;
import cn.tedu.store.service.ICategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController extends BaseController {

	@Autowired private ICategoryService service;
	
	@GetMapping("/list/{parent}")
	public ResponseResult<List<Category>> getByParent(
			@PathVariable("parent") Long parentId) {
		List<Category> list=service.getByParent(parentId);
		return new ResponseResult<List<Category>>(SUCCESS,list);
	}
}
