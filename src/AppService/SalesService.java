package AppService;

import java.util.List;

/**
 * Created by jimmy on 17-7-17.
 */
public interface SalesService {
    List salesByUser();
    List salesByBook();
    List salesByCategory();
}
