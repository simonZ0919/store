package cn.tedu.store.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.store.entity.Cart;
import cn.tedu.store.entity.ResponseResult;
import cn.tedu.store.service.ICartService;
import cn.tedu.store.vo.CartVO;

@RestController
@RequestMapping("/cart")
public class CartController extends BaseController {
	@Autowired private ICartService service;
	
	@PostMapping("/add")
	public ResponseResult<Void> add2Cart(HttpSession session, Cart cart){
		String username=session.getAttribute("username").toString();
		Integer uid=getIdFromSession(session);
		cart.setUid(uid);
		service.add2Cart(username,cart);
		
		return new ResponseResult<Void>(SUCCESS);
	}
	
	@GetMapping("/add_count")
	public ResponseResult<Void> addCount(
			@RequestParam("id") Integer id, HttpSession session){
		Integer uid=getIdFromSession(session);
		service.addCount(id, uid);
		return new ResponseResult<Void>(SUCCESS);
	}
	
	@GetMapping("/list")
	public ResponseResult<List<CartVO>> getByUid(HttpSession session){
		Integer uid=getIdFromSession(session);
		List<CartVO> list=service.getByUid(uid);
		return new ResponseResult<List<CartVO>>(SUCCESS,list);
	}
	
	@GetMapping("/id_list")
	public ResponseResult<List<CartVO>> getByIdList(
			@RequestParam("cart_id") Integer[] ids){
		List<CartVO> list=service.getByIdList(ids);
		return new ResponseResult<List<CartVO>>(SUCCESS,list);
	}
}
