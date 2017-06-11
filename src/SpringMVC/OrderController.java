package SpringMVC;

import entities.BookSimple;
import entities.OrdersEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by jimmy on 17-5-25.
 */
@Controller
public class OrderController {
    private AppService.AppService appService;

    public void setAppService(AppService.AppService appService){this.appService = appService;}

    @RequestMapping(value = "/getOrder")
    public @ResponseBody
    List<OrdersEntity> getOrders(HttpSession session){
        return appService.getOrders(session);
    }

    @RequestMapping(value = "/updateOrder")
    public @ResponseBody int updateOrder(OrdersEntity order, HttpSession session){
        return appService.updateOrder(order, session);
    }

    @RequestMapping(value = "/insertOrder")
    public @ResponseBody int insertOrder(OrdersEntity order, HttpSession session){ return appService.insertOrder(order, session);}

    @RequestMapping(value = "/deleteOrder")
    public @ResponseBody int deleteOrder(short orderid, HttpSession session){
        return appService.deleteOrder(orderid, session);
    }

    @RequestMapping(value = "/getCart")
    public @ResponseBody List<BookSimple> getCart(HttpSession session){return appService.getBooksInCart(session);}

    @RequestMapping(value = "/submitCart")
    public @ResponseBody int submitCart(HttpSession session){return appService.submitCart(session);}

    @RequestMapping(value = "/getUserOrders")
    public @ResponseBody List<List<BookSimple>> getUserOrders(HttpSession session){return appService.getUserOrders(session);}
}
