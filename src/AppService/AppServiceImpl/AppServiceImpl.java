package AppService.AppServiceImpl;

import AppService.AppService;
import Dao.BookDao;
import Dao.OrderDao;
import Dao.OrderItemDao;
import Dao.UserDao;
import entities.BookEntity;
import entities.OrderItemEntity;
import entities.OrdersEntity;
import entities.UserEntity;

import java.util.List;

/**
 * Created by jimmy on 17-5-28.
 */
public class AppServiceImpl implements AppService {
    private BookDao bookDao;
    private UserDao userDao;
    private OrderDao orderDao;
    private OrderItemDao orderItemDao;

    public void setBookDao(BookDao bookDao){this.bookDao = bookDao;}
    public void setUserDao(UserDao userDao){this.userDao = userDao;}
    public void setOrderDao(OrderDao orderDao){this.orderDao = orderDao;}
    public void setOrderItemDao(OrderItemDao orderItemDao){this.orderItemDao = orderItemDao;}

    public List<BookEntity> getBooks(){return bookDao.getBooks();}
    public List<UserEntity> getUsers(){return userDao.getUsers();}
    public List<OrdersEntity> getOrders(){return orderDao.getOrders();}
    public List<OrderItemEntity> getOrderItems(){return orderItemDao.getOrderItems();}

    public int insertBook(BookEntity book){return bookDao.insertBook(book);}
    public int insertUser(UserEntity user){return userDao.insertUser(user);}
    public int insertOrder(OrdersEntity order){return orderDao.insertOrder(order);}
    public int insertOrderItem(OrderItemEntity item){return orderItemDao.insertOrderItem(item);}

    public int updateBook(BookEntity book){return bookDao.updateBook(book);}
    public int updateUser(UserEntity user){return userDao.updateUser(user);}
    public int updateOrder(OrdersEntity order){return orderDao.updateOrder(order);}
    public int updateOrderItem(OrderItemEntity item){return orderItemDao.updateOrderItem(item);}

    public int deleteBook(short id){return bookDao.deleteBook(id);}
    public int deleteUser(short id){return userDao.deleteUser(id);}
    public int deleteOrder(short id){return orderDao.deleteOrder(id);}
    public int deleteOrderItem(short orderid, short bookid){return orderItemDao.deleteOrderItem(orderid, bookid);}

}
