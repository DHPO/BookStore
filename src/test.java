import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

/**
 * Created by jimmy on 17-5-27.
 */
public class test {
    private static org.springframework.orm.hibernate4.LocalSessionFactoryBean session;

    public void setSession(LocalSessionFactoryBean session){
        this.session = session;
    }

    public static void main(String args[]){
        System.out.println(session.getObject());
    }
}
