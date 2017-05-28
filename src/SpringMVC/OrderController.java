package SpringMVC;

import entities.OrdersEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by jimmy on 17-5-25.
 */
@Controller
public class OrderController {
    private AppService.AppService appService;

    public void setAppService(AppService.AppService appService){this.appService = appService;}

    @RequestMapping(value = "/getOrders")
    public @ResponseBody
    List<OrdersEntity> getOrders(){
        return appService.getOrders();
    }

    @RequestMapping(value = "/updateOrder")
    public @ResponseBody int updateOrder(OrdersEntity order){
        return appService.updateOrder(order);
    }

    @RequestMapping(value = "/insertOrder")
    public @ResponseBody int insertOrder(OrdersEntity order){ return appService.insertOrder(order);}

    @RequestMapping(value = "/deleteOrder")
    public @ResponseBody int deleteOrder(short id){
        return appService.deleteOrder(id);
    }
}
