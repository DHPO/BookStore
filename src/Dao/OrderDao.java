package Dao;

import entities.OrdersEntity;
import entities.UserEntity;

import java.util.List;

/**
 * Created by jimmy on 17-5-23.
 */

public interface OrderDao{
    List<OrdersEntity> getOrders();
    int insertOrder(OrdersEntity order);
    int updateOrder(OrdersEntity order);
    int deleteOrder(short id);
    OrdersEntity getCart(UserEntity user);
    void refresh(OrdersEntity order);
    List<OrdersEntity> getOrdersByUser(UserEntity user);
}

