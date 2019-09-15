package cn.tedu.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.store.entity.Goods;
import cn.tedu.store.mapper.GoodsMapper;
import cn.tedu.store.service.IGoodsService;

@Service
public class GoodsService implements IGoodsService {

	@Autowired private GoodsMapper mapper;
	
	@Override
	public List<Goods> getByCategory(long categoryId, Integer offset, Integer count) {
		return findByCategory(categoryId, offset, count);
	}
	
	@Override
	public Goods getById(long id) {
		return findById(id);
	}	
	
	@Override
	public List<Goods> GetByPri(Integer count) {
		return findByPri(count);
	}	
	
	private List<Goods> findByCategory(long categoryId, Integer offset, Integer count) {
		return mapper.findByCategory(categoryId, offset, count);
	}
	
	private Goods findById(long id) {
		return mapper.findById(id);
	}
	
	private List<Goods> findByPri(Integer count){
		return mapper.findByPri(count);
	}




}
