package entities;

import javax.persistence.*;
import java.util.*;

/**
 * Created by jimmy on 17-5-4.
 */
@Entity
@Table(name = "orders", schema = "bookstore")
public class OrdersEntity {
    private short orderid;
    private Collection<OrderItemEntity> orderItemsByOrderid;
    private UserEntity userByUserid;

    public OrdersEntity(){
        orderItemsByOrderid = new HashSet<>();
    }

    @Id
    @Column(name = "orderid")
    public short getOrderid() {
        return orderid;
    }

    public void setOrderid(short orderid) {
        this.orderid = orderid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrdersEntity that = (OrdersEntity) o;

        if (orderid != that.orderid) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) orderid;
    }

    @OneToMany(mappedBy = "ordersByOrderid")
    public Collection<OrderItemEntity> getOrderItemsByOrderid() {
        return orderItemsByOrderid;
    }

    public void setOrderItemsByOrderid(Collection<OrderItemEntity> orderItemsByOrderid) {
        this.orderItemsByOrderid = orderItemsByOrderid;
    }

    @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "id", nullable = false)
    public UserEntity getUserByUserid() {
        return userByUserid;
    }

    public void setUserByUserid(UserEntity userByUserid) {
        this.userByUserid = userByUserid;
    }

    public void addBook(BookEntity book, int amount){
        OrderItemEntity item = new OrderItemEntity();
        item.setBookByBookid(book);
        item.setOrdersByOrderid(this);
        item.setAmount(amount);
        this.orderItemsByOrderid.add(item);
    }
}
