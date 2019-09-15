package cn.tedu.store.mapper;

import cn.tedu.store.entity.Order;
import cn.tedu.store.entity.OrderItem;
import cn.tedu.store.vo.OrderVO;

public interface OrderMapper {
	/**
	 * insert to table: order
	 * @param order
	 * @return affected rows
	 */
	Integer insertOrder(Order order);
	
	/**
	 * insert to table: orderitem
	 * @param orderItem
	 * @return affected rows
	 */
	Integer insertOrderItem(OrderItem orderItem);
	
	/**
	 * find order detail by id
	 * @param id
	 * @return detail of order
	 */
	OrderVO findById(Integer id);
}
