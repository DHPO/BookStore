package AppService.AppServiceImpl;

import AppService.AppService;
import Dao.BookDao;
import Dao.OrderDao;
import Dao.OrderItemDao;
import Dao.UserDao;
import entities.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collection;
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

    public List<BookSimple> getBooks(){return bookDao.getSimpleBooks();}
    public List<BookEntity> getDetailBooks(){return bookDao.getBooks();}
    public List<UserEntity> getUsers(HttpSession session){
        if(!loginCheck(session) || ((UserEntity)session.getAttribute("user")).getRole() < 2)
            return new ArrayList<>();
        return userDao.getUsers();
    }
    public List<OrdersEntity> getOrders(HttpSession session){
        if(!loginCheck(session) || ((UserEntity)session.getAttribute("user")).getRole() < 2)
            return new ArrayList<>();
        return orderDao.getOrders();
    }
    public List<OrderItemEntity> getOrderItems(HttpSession session){
        if(!loginCheck(session) || ((UserEntity)session.getAttribute("user")).getRole() < 2)
            return new ArrayList<>();
        return orderItemDao.getOrderItems();
    }

    public int insertBook(BookEntity book, HttpSession session){
        if(!loginCheck(session) || ((UserEntity)session.getAttribute("user")).getRole() < 2)
            return -1;
        return bookDao.insertBook(book);
    }
    public int insertUser(UserEntity user, HttpSession session){
        if(!loginCheck(session) || ((UserEntity)session.getAttribute("user")).getRole() < 2)
            return -1;
        return userDao.insertUser(user);
    }
    public int insertOrder(OrdersEntity order, HttpSession session){
        if(!loginCheck(session) || ((UserEntity)session.getAttribute("user")).getRole() < 2)
            return -1;
        return orderDao.insertOrder(order);
    }
    public int insertOrderItem(OrderItemEntity item, HttpSession session){
        if(!loginCheck(session) || ((UserEntity)session.getAttribute("user")).getRole() < 2)
            return -1;
        return orderItemDao.insertOrderItem(item);
    }

    public int updateBook(BookEntity book, HttpSession session){
        if(!loginCheck(session) || ((UserEntity)session.getAttribute("user")).getRole() < 2)
            return -1;
        return bookDao.updateBook(book);
    }
    public int updateUser(UserEntity user, HttpSession session){
        if(!loginCheck(session) || (((UserEntity)session.getAttribute("user")).getRole() < 2))
            return -1;
        return userDao.updateUser(user);
    }
    public int updateOrder(OrdersEntity order, HttpSession session){
        if(!loginCheck(session) || ((UserEntity)session.getAttribute("user")).getRole() < 2)
            return -1;
        return orderDao.updateOrder(order);
    }
    public int updateOrderItem(OrderItemEntity item, HttpSession session){
        if(!loginCheck(session) || ((UserEntity)session.getAttribute("user")).getRole() < 2)
            return -1;
        return orderItemDao.updateOrderItem(item);
    }

    public int deleteBook(short id, HttpSession session){
        if(!loginCheck(session) || ((UserEntity)session.getAttribute("user")).getRole() < 2)
            return -1;
        return bookDao.deleteBook(id);
    }
    public int deleteUser(short id, HttpSession session){
        if(!loginCheck(session) || ((UserEntity)session.getAttribute("user")).getRole() < 2)
            return -1;
        return userDao.deleteUser(id);
    }
    public int deleteOrder(short id, HttpSession session){
        if(!loginCheck(session) || ((UserEntity)session.getAttribute("user")).getRole() < 2)
            return -1;
        return orderDao.deleteOrder(id);
    }
    public int deleteOrderItem(short orderid, short bookid, HttpSession session){
        if(!loginCheck(session) || ((UserEntity)session.getAttribute("user")).getRole() < 2)
            return -1;
        return orderItemDao.deleteOrderItem(orderid, bookid);
    }

    public UserEntity login(String username, String password){
        UserEntity user = userDao.getUserByName(username);
        if(user == null || !user.getPassword().equals(password))
            return null;
        else
            return user;
    }

    public List<BookSimple> findBooksByName(String name){
        return bookDao.findBooksByName(name);
    }

    public BookEntity getBookById(short id){return bookDao.getBookById(id);}

    public boolean loginCheck(HttpSession session){
        return (session.getAttribute("user") != null);
    }

    OrdersEntity loadCart(HttpSession session){
        if(session.getAttribute("cart") == null){
            OrdersEntity cart = orderDao.getCart((UserEntity) session.getAttribute("user"));
            if(cart == null){
                cart = new OrdersEntity();
                cart.setStatus(0);
                cart.setUserByUserid((UserEntity) session.getAttribute("user"));
                orderDao.insertOrder(cart);
                cart = orderDao.getCart((UserEntity) session.getAttribute("user"));
            }
            session.setAttribute("cart", cart);
        }
        return (OrdersEntity) session.getAttribute("cart");
    }

    public int getBookNumInCart(short bookid, HttpSession session){
        if(!loginCheck(session))
            return -1;
        OrdersEntity cart = loadCart(session);
        OrderItemEntity item = orderItemDao.loadOrderItem(cart.getOrderid(), bookid);
        return item==null?0:item.getAmount();
    }

    public int updateBookNumInCart(short bookid, int num, HttpSession session){
        if(!loginCheck(session))
            return -1;
        if(num < 0)
            return -2;
        OrdersEntity cart = loadCart(session);
        OrderItemEntity item = orderItemDao.loadOrderItem(cart.getOrderid(), bookid);
        if(item == null){
            item = new OrderItemEntity();
            item.setOrdersByOrderid(cart);
            item.setBookid(bookid);
            item.setAmount(num);
            return orderItemDao.insertOrderItem(item);
        }
        if(num == 0) {
            return orderItemDao.deleteOrderItem(item.getOrderid(), item.getBookid());
        }
        item.setAmount(num);
        return orderItemDao.updateOrderItem(item);
    }

    public List<BookSimple> getOrderDetail(OrdersEntity order){
        Collection<OrderItemEntity> items =  order.getOrderItemsByOrderid();
        List<BookSimple> result = new ArrayList<BookSimple>();
        for(OrderItemEntity item : items){
            BookSimple book = new BookSimple();
            book.fromBookEntity(item.getBookByBookid());
            book.setNum(item.getAmount());
            result.add(book);
        }
        return result;
    }

    public List<BookSimple> getBooksInCart(HttpSession session){
        if(!loginCheck(session))
            return new ArrayList<>();
        OrdersEntity cart = loadCart(session);
        orderDao.refresh(cart);
        return getOrderDetail(cart);
    }

    public int submitCart(HttpSession session){
        if(!loginCheck(session))
            return -1;
        OrdersEntity cart = loadCart(session);
        orderDao.refresh(cart);
        if(cart.getOrderItemsByOrderid().isEmpty())
            return -2;
        for(OrderItemEntity item: cart.getOrderItemsByOrderid()){
            item.setPrice(item.getBookByBookid().getPrice());
            orderItemDao.updateOrderItem(item);
        }
        cart.setStatus(1);
        session.setAttribute("cart", null);
        orderDao.updateOrder(cart);
        return 1;
    }

    public List<List<BookSimple>> getUserOrders(HttpSession session){
        if(!loginCheck(session))
            return new ArrayList<>();
        UserEntity user = (UserEntity) session.getAttribute("user");
        List<OrdersEntity> orders = orderDao.getOrdersByUser(user);
        ArrayList<List<BookSimple>> result = new ArrayList<>();
        for(OrdersEntity order : orders){
            orderDao.refresh(order);
            result.add(getOrderDetail(order));
        }
        return result;
    }

    public int updateProfile(UserEntity user, HttpSession session){
        if(((UserEntity)session.getAttribute("user")).getId() == user.getId()){
            userDao.updateUser(user);
            session.setAttribute("user", user);
            return 1;
        }
        else
            return -1;
    }
}
