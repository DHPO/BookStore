package Dao.impl;

import Dao.OrderDao;
import entities.OrdersEntity;
import entities.UserEntity;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

/**
 * Created by jimmy on 17-5-28.
 */
public class OrderDaoImpl extends HibernateDaoSupport implements OrderDao{
    private BasicMovement basicMovement;

    public void setBasicMovement(BasicMovement b){basicMovement = b;}

    public List<OrdersEntity> getOrders(){
        return (List<OrdersEntity>) getHibernateTemplate().find("from OrdersEntity ");
    }

    public void refresh(OrdersEntity order){
        getHibernateTemplate().refresh(order);
    }

    public int deleteOrder(short id){
        try{
            OrdersEntity oldOrder = getHibernateTemplate().load(OrdersEntity.class, id);
            getHibernateTemplate().delete(oldOrder);
            return 1;
        }catch (Exception e){
            System.out.println(e);
            return 0;
        }
    }

    public int insertOrder(OrdersEntity order){
        try{
            UserEntity user = getHibernateTemplate().get(UserEntity.class, order.getUserid());
            order.setUserByUserid(user);
            if(user == null)
                return 0;
            return basicMovement.insert(order);
        }catch(Exception e){
            System.out.println(e);
            return 0;
        }
    }

    public int updateOrder(OrdersEntity order){
        UserEntity user = getHibernateTemplate().get(UserEntity.class, order.getUserid());
        order.setUserByUserid(user);
        if(user == null)
            return 0;
        return basicMovement.update(order);
    }

    public OrdersEntity getCart(UserEntity user){
        List<OrdersEntity> list = (List<OrdersEntity>)getHibernateTemplate().find("from OrdersEntity where userByUserid = ? and status = 0", user);
        return list.isEmpty()?null:list.get(0);
        }

    public List<OrdersEntity> getOrdersByUser(UserEntity user){
        HibernateTemplate template = getHibernateTemplate();
        template.setMaxResults(5);
        return (List<OrdersEntity>) template.find("from OrdersEntity where userByUserid = ? and status > 0 order by orderid desc ", user);
    }
}
