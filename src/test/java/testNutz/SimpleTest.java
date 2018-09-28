package testNutz;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.dao.Dao;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.sensor.bean.User;

@RunWith(MyNutTestRunner.class)
@IocBean // 必须有
public class SimpleTest extends Assert {
    
    //private static final Log log = Logs.get();
    
    // 跟通常的@Inject完全一样.
    @Inject("refer:$ioc")
    protected Ioc ioc;
    
    @Inject
    protected Dao dao;
    
    @Test
    public void test_user_service_create_user() {
        dao.create(User.class, false);
        
        User user = new User();
        user.setUsername("wendal");
        user.setPassword("123");
        dao.insert(user);
        
        user = dao.fetch(User.class, "wendal");
        assertNotNull(user);
        assertNotNull(user.getUsername());
    }
}