package cn.tedu.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.store.entity.Goods;
import cn.tedu.store.entity.ResponseResult;
import cn.tedu.store.service.IGoodsService;

@RestController
@RequestMapping("/goods")
public class GoodsController extends BaseController {

	@Autowired private IGoodsService service;
	
	@RequestMapping("/list/{categoryId}")
	public ResponseResult<List<Goods>> getByParent(
			@PathVariable("categoryId") long categoryId) {
		List<Goods> list=service.getByCategory(categoryId, 0, 30);
		return new ResponseResult<List<Goods>>(SUCCESS,list);
	}
	
	@RequestMapping("/details/{id}")
	public ResponseResult<Goods> getById(@PathVariable("id") long id){
		Goods goods=service.getById(id);
		return new ResponseResult<Goods>(SUCCESS,goods);
	}
	
	@GetMapping("/hot")
	public ResponseResult<List<Goods>> getHotSales(){
		List<Goods> list=service.GetByPri(4);
		return new ResponseResult<List<Goods>>(SUCCESS,list);
	}
}
