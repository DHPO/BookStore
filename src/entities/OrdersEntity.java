package entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

/**
 * Created by jimmy on 17-5-4.
 */
@Entity
@Table(name = "orders", schema = "bookstore")
public class OrdersEntity {
    private short orderid;
    private Collection<OrderItemEntity> orderItemsByOrderid;
    private UserEntity userByUserid;
    private short userid;

    public OrdersEntity(){
        orderItemsByOrderid = new HashSet<>();
    }

    public short getUserid(){
        if(userByUserid != null)
            setUserid(userByUserid.getId());
        return this.userid;}
    public void setUserid(short id){this.userid = id;}

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

    @JsonIgnore
    @OneToMany(mappedBy = "ordersByOrderid")
    public Collection<OrderItemEntity> getOrderItemsByOrderid() {
        return orderItemsByOrderid;
    }

    public void setOrderItemsByOrderid(Collection<OrderItemEntity> orderItemsByOrderid) {
        this.orderItemsByOrderid = orderItemsByOrderid;
    }

    @JsonIgnore
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
