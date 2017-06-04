package AppService;

import entities.BookEntity;
import entities.OrderItemEntity;
import entities.OrdersEntity;
import entities.UserEntity;

import java.util.List;

/**
 * Created by jimmy on 17-5-27.
 */
public interface AppService {
    List<BookEntity> getBooks();
    List<UserEntity> getUsers();
    List<OrdersEntity> getOrders();
    List<OrderItemEntity> getOrderItems();

    int insertBook(BookEntity book);
    int insertUser(UserEntity user);
    int insertOrder(OrdersEntity order);
    int insertOrderItem(OrderItemEntity item);

    int updateBook(BookEntity book);
    int updateUser(UserEntity user);
    int updateOrder(OrdersEntity order);
    int updateOrderItem(OrderItemEntity item);

    int deleteBook(short id);
    int deleteUser(short id);
    int deleteOrder(short id);
    int deleteOrderItem(short orderid, short bookid);

    UserEntity login(String username, String password);
    List<BookEntity> findBooksByName(String name);
}
