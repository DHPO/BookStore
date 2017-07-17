package Dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

/**
 * Created by jimmy on 17-5-28.
 */
public class BasicMovement extends HibernateDaoSupport {
    @Autowired
    MongoTemplate mongoTemplate;

    public int insert(Object o){
        try{
            getHibernateTemplate().save(o);
            return 1;
        }catch(Exception e){
            System.out.println(e);
            return 0;
        }
    }

    public int update(Object o){
        try{
            getHibernateTemplate().merge(o);
            return 1;
        }catch (Exception e){
            System.out.println(e);
            return 0;
        }
    }
}
