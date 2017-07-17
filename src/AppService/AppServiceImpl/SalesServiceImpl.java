package AppService.AppServiceImpl;

import AppService.SalesService;
import Dao.OrderDao;

import java.util.List;

/**
 * Created by jimmy on 17-7-17.
 */
public class SalesServiceImpl implements SalesService {
    private OrderDao orderDao;

    public void setOrderDao(OrderDao orderDao){this.orderDao = orderDao;}

    public List salesByUser(){
        return orderDao.salesByUser();
    }

    public List salesByBook(){
        return orderDao.salesByBook();
    }

    public List salesByCategory(){
        return orderDao.salesByCategory();
    }
}
