package Dao.impl;

import Dao.OrderItemDao;
import entities.BookEntity;
import entities.OrderItemEntity;
import entities.OrdersEntity;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

/**
 * Created by jimmy on 17-5-28.
 */
public class OrderItemDaoImpl extends HibernateDaoSupport implements OrderItemDao{
    private BasicMovement basicMovement;

    public void setBasicMovement(BasicMovement b){basicMovement = b;}

    public List<OrderItemEntity> getOrderItems(){
        return (List<OrderItemEntity>)getHibernateTemplate().find("from OrderItemEntity ");
    }

    public OrderItemEntity loadOrderItem(short orderid, short bookid){
        OrdersEntity order = getHibernateTemplate().load(OrdersEntity.class, orderid);
        BookEntity book = getHibernateTemplate().load(BookEntity.class, bookid);
        List<OrderItemEntity> items = (List<OrderItemEntity>)getHibernateTemplate().find("from OrderItemEntity as item where item.ordersByOrderid = ? and item.bookByBookid = ?", order, book);
        return items.size()>1||items.size()==0?null:items.get(0);
    }

    public int deleteOrderItem(short orderid, short bookid){
        OrderItemEntity item = loadOrderItem(orderid, bookid);
        if(item == null)
            return 0;
        getHibernateTemplate().delete(item);
        return 1;
    }

    public int insertOrderItem(OrderItemEntity item){
        if(item.getOrdersByOrderid() == null){
            OrdersEntity order = getHibernateTemplate().get(OrdersEntity.class, item.getOrderid());
            item.setOrdersByOrderid(order);
            if(order == null)return 0;
        }
        if(item.getBookByBookid() == null){
            BookEntity book = getHibernateTemplate().get(BookEntity.class, item.getBookid());
            item.setBookByBookid(book);
            if(book == null) return 0;
        }
        return basicMovement.insert(item);
    }

    public int updateOrderItem(OrderItemEntity item){
        OrdersEntity order = getHibernateTemplate().get(OrdersEntity.class, item.getOrderid());
        item.setOrdersByOrderid(order);
        BookEntity book = getHibernateTemplate().get(BookEntity.class, item.getBookid());
        item.setBookByBookid(book);
        if(order == null || book == null)
            return 0;
        return basicMovement.update(item);
    }

}
