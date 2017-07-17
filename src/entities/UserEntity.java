package entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

/**
 * Created by jimmy on 17-5-4.
 */
@Entity
@Table(name = "user", schema = "bookstore")
public class UserEntity {
    private short id;
    private String name;
    private String email;
    private String password;
    private int role;
    private String photoId;
    private Collection<OrdersEntity> ordersById;

    public UserEntity(){
        ordersById = new HashSet<>();
    }

    @Id
    @Column(name = "id")
    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "password")
    public String getPassword(){return password;}
    public void setPassword(String password){this.password = password;}

    @Basic
    @Column(name = "role")
    public int getRole(){return role;}
    public void setRole(int role){this.role = role;}

    @Basic
    @Column(name = "photoId")
    public String getPhotoId(){return photoId;}
    public void setPhotoId(String photoId){this.photoId = photoId;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "userByUserid")
    public Collection<OrdersEntity> getOrdersById() {
        return ordersById;
    }

    public void setOrdersById(Collection<OrdersEntity> ordersById) {
        this.ordersById = ordersById;
    }

    public OrdersEntity newOrder(){
        OrdersEntity order = new OrdersEntity();
        order.setUserByUserid(this);
        ordersById.add(order);
        return order;
    }
}
