package SpringMVC;

import Dao.OrderItemDao;
import entities.OrderItemEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

/**
 * Created by jimmy on 17-5-25.
 */
@Controller
public class OrderItemController {
    private OrderItemDao orderItemDao = new OrderItemDao();
    public void setOrderItemDao(OrderItemDao orderItemDao){this.orderItemDao = orderItemDao;}

    @RequestMapping(value = "/getOrderItems")
    public @ResponseBody
    ArrayList<OrderItemEntity> getOrderItems(){
        return orderItemDao.getOrderItems();
    }

    @RequestMapping(value = "/updateOrderItem")
    public @ResponseBody int updateOrderItem(OrderItemEntity orderItem){
        return orderItemDao.updateOrderItem(orderItem);
    }

    @RequestMapping(value = "/insertOrderItem")
    public @ResponseBody int insertOrderItem(OrderItemEntity orderItem){
        return orderItemDao.insertOrderItem(orderItem);
    }

    @RequestMapping(value = "/deleteOrderItem")
    public @ResponseBody int deleteBook(short orderid, short bookid){
        return orderItemDao.deleteOrderItem(orderid, bookid);
    }
}
