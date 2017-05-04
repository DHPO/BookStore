package entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by jimmy on 17-5-4.
 */
@Entity
@Table(name = "orderItem", schema = "bookstore")
public class OrderItemEntity implements Serializable {
    //private short orderid;
    private Integer amount;
    //private short bookid;
    private OrdersEntity ordersByOrderid;
    private BookEntity bookByBookid;

    /*@Id
    @Column(name = "orderid", nullable = false)
    public short getOrderid() {
        return orderid;
    }

    public void setOrderid(short orderid) {
        this.orderid = orderid;
    }*/

    @Basic
    @Column(name = "amount")
    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /*@Id
    @Column(name = "bookid", nullable = false)
    public short getBookid() {
        return bookid;
    }

    public void setBookid(short bookid) {
        this.bookid = bookid;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderItemEntity that = (OrderItemEntity) o;

        //if (orderid != that.orderid) return false;
        //if (bookid != that.bookid) return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ordersByOrderid.hashCode();
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + bookByBookid.hashCode();
        return result;
    }

    @Id
    @ManyToOne
    @JoinColumn(name = "orderid", referencedColumnName = "orderid", nullable = false)
    public OrdersEntity getOrdersByOrderid() {
        return ordersByOrderid;
    }

    public void setOrdersByOrderid(OrdersEntity ordersByOrderid) {
        this.ordersByOrderid = ordersByOrderid;
    }

    @Id
    @ManyToOne
    @JoinColumn(name = "bookid", referencedColumnName = "id", nullable = false)
    public BookEntity getBookByBookid() {
        return bookByBookid;
    }

    public void setBookByBookid(BookEntity bookByBookid) {
        this.bookByBookid = bookByBookid;
    }
}
