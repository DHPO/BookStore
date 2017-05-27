package Dao;

import Database.MySession;
import entities.OrdersEntity;
import entities.UserEntity;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jimmy on 17-5-23.
 */
public class OrderDao {
    public ArrayList<OrdersEntity> getOrders(){
        Session session = MySession.getSession();
        List orderList;
        try{
            session.beginTransaction();
            orderList = session.createQuery("from OrdersEntity").list();
            session.getTransaction().commit();

        }finally {
            session.close();
        }
        return (ArrayList<OrdersEntity>) orderList;
    }

    public int updateOrder(OrdersEntity order){
        Session session = MySession.getSession();
        OrdersEntity oldOrder;
        try{
            session.beginTransaction();
            oldOrder = session.load(OrdersEntity.class, order.getOrderid());
            if(oldOrder != null){
                oldOrder.setUserByUserid(order.getUserByUserid());
                session.getTransaction().commit();
            }
        }finally {
            session.close();
        }
        return oldOrder == null?0:1;
    }

    public int insertOrder(OrdersEntity order){
        Session session = MySession.getSession();
        int result;
        try{
            session.beginTransaction();
            System.out.println(order.getUserid());
            UserEntity user = session.load(UserEntity.class, order.getUserid());
            order.setUserByUserid(user);
            session.save(order);
            session.getTransaction().commit();
            result = 1;
        }catch (Exception e){
            System.out.println(e);
            result = 0;
        }finally{
            session.close();
        }
        return result;
    }

    public int deleteOrder(short id){
        Session session = MySession.getSession();
        int result;
        try{
            session.beginTransaction();
            OrdersEntity order = session.load(OrdersEntity.class, id);
            session.delete(order);
            session.getTransaction().commit();
            result = 1;
        }catch(Exception e){
            result = 0;
        }finally {
            session.close();
        }
        return result;
    }
}

