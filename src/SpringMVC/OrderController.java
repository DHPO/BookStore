package SpringMVC;

import Dao.OrderDao;
import entities.OrdersEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

/**
 * Created by jimmy on 17-5-25.
 */
@Controller
public class OrderController {
    private OrderDao orderDao = new OrderDao();
    public void setOrderDao(OrderDao orderDao){this.orderDao = orderDao;}

    @RequestMapping(value = "/getOrders")
    public @ResponseBody
    ArrayList<OrdersEntity> getOrders(){
        return orderDao.getOrders();
    }

    @RequestMapping(value = "/updateOrder")
    public @ResponseBody int updateOrder(OrdersEntity order){
        return orderDao.updateOrder(order);
    }

    @RequestMapping(value = "/insertOrder")
    public @ResponseBody int insertOrder(OrdersEntity order){ return orderDao.insertOrder(order);}

    @RequestMapping(value = "/deleteOrder")
    public @ResponseBody int deleteOrder(short id){
        return orderDao.deleteOrder(id);
    }
}
