package AppService;

import entities.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by jimmy on 17-5-27.
 */
public interface AppService {
    List<BookSimple> getBooks();
    List<BookEntity> getDetailBooks();
    List<UserEntity> getUsers(HttpSession session);
    List<OrdersEntity> getOrders(HttpSession session);
    List<OrderItemEntity> getOrderItems(HttpSession session);

    int insertBook(BookEntity book, HttpSession session);
    int insertUser(UserEntity user, HttpSession session);
    int insertOrder(OrdersEntity order, HttpSession session);
    int insertOrderItem(OrderItemEntity item, HttpSession session);

    int updateBook(BookEntity book, HttpSession session);
    int updateUser(UserEntity user, HttpSession session);
    int updateOrder(OrdersEntity order, HttpSession session);
    int updateOrderItem(OrderItemEntity item, HttpSession session);

    int deleteBook(short id, HttpSession session);
    int deleteUser(short id, HttpSession session);
    int deleteOrder(short id, HttpSession session);
    int deleteOrderItem(short orderid, short bookid, HttpSession session);

    UserEntity login(String username, String password);
    List<BookSimple> findBooksByName(String name);
    BookEntity getBookById(short id);

    int getBookNumInCart(short bookid, HttpSession session);
    int updateBookNumInCart(short bookid, int num, HttpSession session);
    List<BookSimple> getBooksInCart(HttpSession session);

    List<BookSimple> getOrderDetail(OrdersEntity order);
    int submitCart(HttpSession session);

    List<List<BookSimple>> getUserOrders(HttpSession session);
    int updateProfile(UserEntity user, HttpSession session);

}
