package cn.tedu.store.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.store.entity.Address;
import cn.tedu.store.entity.Order;
import cn.tedu.store.entity.OrderItem;
import cn.tedu.store.exception.AddressNotFoundException;
import cn.tedu.store.exception.InsertException;
import cn.tedu.store.mapper.OrderMapper;
import cn.tedu.store.service.IAddressService;
import cn.tedu.store.service.ICartService;
import cn.tedu.store.service.IOrderService;
import cn.tedu.store.vo.CartVO;

@Service
public class OrderServiceImpl implements IOrderService {
	
	@Autowired private OrderMapper mapper;
	@Autowired private IAddressService addressService;
	@Autowired private ICartService cartService;
	
	@Override
	@Transactional
	public Order createOrder(Integer uid, String username, Integer addressId, Integer[] idList)
			throws InsertException {
		Date date=new Date();
		long pay=0L;
		
	    List<CartVO> carts=cartService.getByIdList(idList);
	    List<OrderItem> orderItems=new ArrayList<OrderItem>();	    
	    // 遍历集合，过程中，计算总价pay
	    for (CartVO cartVO : carts) {
			pay+=cartVO.getNewPrice()*cartVO.getCount();
			// -- 创建OrderItem
			OrderItem item=new OrderItem();
			// -- item属性：goods_5，OK
			item.setGoodsId(cartVO.getGid());
			item.setGoodsTitle(cartVO.getTitle());
			item.setGoodsPrice(cartVO.getNewPrice());
			item.setGoodsImage(cartVO.getImage());
			item.setGoodsCount(cartVO.getCount());
			// -- item属性：4个日志，OK
			item.setCreatedUser(username);
			item.setModifiedUser(username);
			item.setCreatedTime(date);
			item.setModifiedTime(date);
			orderItems.add(item);
		}
	    
	    // 创建Order对象
	    Order order=new Order();
	    // order属性：uid，OK
	    order.setUid(uid);
	    // order属性：pay，OK
	    order.setPay(pay);
	    // 通过addressService.getById()得到收货地址数据
	    Address address=addressService.getById(addressId);
	    if (address == null) {
			throw new AddressNotFoundException("address not found");
		}
	    // order属性：recv_4，OK
	    order.setRecvAddress(address.getAddress());
	    order.setRecvPhone(address.getPhone());
	    order.setRecvDistrict(address.getDistrict());
	    order.setRecvName(address.getName());
	    // order属性：order_time，OK
	    order.setOrderTime(date);
	    // order属性：status，OK，值为0
	    order.setStatus(0);
	    // order属性：4个日志，OK
		order.setCreatedUser(username);
		order.setModifiedUser(username);
		order.setCreatedTime(date);
		order.setModifiedTime(date);
		
	    // 插入订单数据并获取oid：insertOrder(order)
		insertOrder(order);
		
	    // 遍历orderItems
		for (OrderItem orderItem : orderItems) {
			// item属性：oid
			orderItem.setId(order.getId());
			// 插入订单商品数据
			insertOrderItem(orderItem);
		}     
		
		return order;
	}

	
	private void insertOrder(Order order) {
		Integer rows=mapper.insertOrder(order);
		if(rows!=1) {
			throw new InsertException("fail to add order");
		}
	}
	
	private void insertOrderItem(OrderItem orderItem) {
		Integer rows=mapper.insertOrderItem(orderItem);
		if(rows!=1) {
			throw new InsertException("fail to add order item");
		}
	}
}
