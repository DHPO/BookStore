package Dao;

import entities.OrdersEntity;

import java.util.List;

/**
 * Created by jimmy on 17-5-23.
 */

public interface OrderDao{
    List<OrdersEntity> getOrders();
    int insertOrder(OrdersEntity order);
    int updateOrder(OrdersEntity order);
    int deleteOrder(short id);
}

