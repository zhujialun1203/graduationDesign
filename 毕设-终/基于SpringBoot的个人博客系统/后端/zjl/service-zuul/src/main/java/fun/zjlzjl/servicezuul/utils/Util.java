package fun.zjlzjl.servicezuul.utils;

//import org.sang.bean.User;
import fun.zjlzjl.servicezuul.bean.User;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by sang on 2017/12/20.
 */
public class Util {
    public static User getCurrentUser() {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return user;





    }
}
