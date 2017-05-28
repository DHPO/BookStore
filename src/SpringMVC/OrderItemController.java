package SpringMVC;

import entities.OrderItemEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by jimmy on 17-5-25.
 */
@Controller
public class OrderItemController {
    private AppService.AppService appService;

    public void setAppService(AppService.AppService appService){this.appService = appService;}

    @RequestMapping(value = "/getOrderItems")
    public @ResponseBody
    List<OrderItemEntity> getOrderItems(){
        return appService.getOrderItems();
    }

    @RequestMapping(value = "/updateOrderItem")
    public @ResponseBody int updateOrderItem(OrderItemEntity orderItem){
        return appService.updateOrderItem(orderItem);
    }

    @RequestMapping(value = "/insertOrderItem")
    public @ResponseBody int insertOrderItem(OrderItemEntity orderItem){
        return appService.insertOrderItem(orderItem);
    }

    @RequestMapping(value = "/deleteOrderItem")
    public @ResponseBody int deleteBook(short orderid, short bookid){
        return appService.deleteOrderItem(orderid, bookid);
    }
}
