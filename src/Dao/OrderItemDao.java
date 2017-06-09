package Dao;

import entities.OrderItemEntity;

import java.util.List;

/**
 * Created by jimmy on 17-5-23.
*/
public interface OrderItemDao{
    List<OrderItemEntity> getOrderItems();
    int insertOrderItem(OrderItemEntity item);
    int updateOrderItem(OrderItemEntity item);
    int deleteOrderItem(short orderid, short bookid);
}

