package cn.tedu.store.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.store.entity.Order;
import cn.tedu.store.entity.ResponseResult;
import cn.tedu.store.service.IOrderService;
import cn.tedu.store.vo.OrderVO;

@RestController
@RequestMapping("/order")
public class OrderController extends BaseController {
	@Autowired private IOrderService service;
	
	@RequestMapping("/create")
	public ResponseResult<Order> createOrder(HttpSession session, 
	    @RequestParam("address") Integer addressId, 
	    @RequestParam("id_list") Integer[] cartIds) {
	    Integer uid=getIdFromSession(session);
	    String username=session.getAttribute("username").toString();
		Order order=service.createOrder(uid, username, addressId, cartIds);
		return new ResponseResult<Order>(SUCCESS, order);
	}
	
	@GetMapping("/details/{id}")
	public ResponseResult<OrderVO> getById(@PathVariable("id") Integer id){
		OrderVO data=service.getById(id);
		return new ResponseResult<OrderVO>(SUCCESS,data);
	}
}
