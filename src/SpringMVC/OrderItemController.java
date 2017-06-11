package SpringMVC;

import entities.OrderItemEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by jimmy on 17-5-25.
 */
@Controller
public class OrderItemController {
    private AppService.AppService appService;

    public void setAppService(AppService.AppService appService){this.appService = appService;}

    @RequestMapping(value = "/getOrderItem")
    public @ResponseBody
    List<OrderItemEntity> getOrderItems(HttpSession session){
        return appService.getOrderItems(session);
    }

    @RequestMapping(value = "/updateOrderItem")
    public @ResponseBody int updateOrderItem(OrderItemEntity orderItem, HttpSession session){
        return appService.updateOrderItem(orderItem, session);
    }

    @RequestMapping(value = "/insertOrderItem")
    public @ResponseBody int insertOrderItem(OrderItemEntity orderItem, HttpSession session){
        return appService.insertOrderItem(orderItem, session);
    }

    @RequestMapping(value = "/deleteOrderItem")
    public @ResponseBody int deleteBook(short orderid, short bookid, HttpSession session){
        return appService.deleteOrderItem(orderid, bookid, session);
    }
}
