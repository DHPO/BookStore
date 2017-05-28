package Dao;

import entities.OrderItemEntity;

import java.util.List;

/**
 * Created by jimmy on 17-5-23.
 */
/*public class OrderItemDao {
    public OrderItemEntity getOrderItemByIds(short orderid, short bookid){
        Session session = MySession.getSession();
        OrderItemEntity orderItem = null;
        try{
            OrdersEntity orders = session.load(OrdersEntity.class, orderid);
            BookEntity book = session.load(BookEntity.class, bookid);
            ArrayList<OrderItemEntity> orderItemList = (ArrayList) session.createQuery("from OrderItemEntity where ordersByOrderid = :orders and bookByBookid = :book ")
                    .setParameter("book",book)
                    .setParameter("orders", orders)
                    .list();
            orderItem = orderItemList.get(0);
        }catch(Exception e){
            System.err.println(e);
        }finally{
            session.close();
        }
        return orderItem;
    }

    public ArrayList<OrderItemEntity> getOrderItems(){
        Session session = MySession.getSession();
        List orderItemList;
        try{
            session.beginTransaction();
            orderItemList = session.createQuery("from OrderItemEntity").list();
            session.getTransaction().commit();

        }finally {
            session.close();
        }
        return (ArrayList<OrderItemEntity>) orderItemList;
    }

    public int updateOrderItem(OrderItemEntity orderItem){
        Session session = MySession.getSession();
        OrderItemEntity oldOrderItem = getOrderItemByIds(orderItem.getOrderid(), orderItem.getBookid());
        try{
            session.beginTransaction();
            if(oldOrderItem != null){
                oldOrderItem.setAmount(orderItem.getAmount());
                System.out.println(oldOrderItem.getAmount());
                session.update(oldOrderItem);
                session.getTransaction().commit();
            }
        }finally {
            session.close();
        }
        return oldOrderItem == null?0:1;
    }

    public int insertOrderItem(OrderItemEntity orderItem){
        Session session = MySession.getSession();
        int result;
        try{
            session.beginTransaction();
            orderItem.setBookByBookid(session.load(BookEntity.class, orderItem.getBookid()));
            orderItem.setOrdersByOrderid(session.load(OrdersEntity.class, orderItem.getOrderid()));
            session.save(orderItem);
            session.getTransaction().commit();
            result = 1;
        }catch (Exception e){
            result = 0;
        }finally{
            session.close();
        }
        return result;
    }

    public int deleteOrderItem(short orderid, short bookid){
        Session session = MySession.getSession();
        int result;
        try{
            session.beginTransaction();
            OrderItemEntity orderItem = getOrderItemByIds(orderid, bookid);
            session.delete(orderItem);
            session.getTransaction().commit();
            result = 1;
        }catch(Exception e){
            result = 0;
        }finally {
            session.close();
        }
        return result;
    }
}*/

public interface OrderItemDao{
    List<OrderItemEntity> getOrderItems();
    int insertOrderItem(OrderItemEntity item);
    int updateOrderItem(OrderItemEntity item);
    int deleteOrderItem(short orderid, short bookid);
}

