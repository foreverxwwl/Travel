package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.UserServicempi;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @outhor li
 * @create 2019-11-21 18:17
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取页面参数
        Map<String, String[]> login = request.getParameterMap();
        //2.封装user对象
        User loginUser = new User();
        try {
            BeanUtils.populate(loginUser, login);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println("fafasfsaf");
        //3.调用service查询
        UserService service = new UserServicempi();
        User user = service.login(loginUser);
        ResultInfo info = new ResultInfo();//用于封装登录信息
        //4.对查询到的用户进行判断
        if (user == null){
            //没有该用户
            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误");
        }
        if (user != null && user.getStatus().equals("N")){
            //没有激活
            info.setFlag(false);
            info.setErrorMsg("没有激活，请先激活");
        }
        if (user != null && user.getStatus().equals("Y")){
            request.getSession().setAttribute("user", user);
            info.setFlag(true);
        }

        //响应数据
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),info);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);

    }
}
