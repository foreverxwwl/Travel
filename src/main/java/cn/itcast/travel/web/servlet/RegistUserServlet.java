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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @outhor li
 * @create 2019-11-19 1:55
 * 注册页面servlet
 */
@WebServlet("/registUserServlet")
public class RegistUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ffafsafas");
        //获取页面数据
        String check = request.getParameter("check");
        //获取session数据中真确的验证码
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        //判断是否正确
        if(check == null || !checkcode_server.equalsIgnoreCase(check)){
            ResultInfo resultInfo = new ResultInfo();
            //编写返回信息
            resultInfo.setFlag(false);
            System.out.println("yanzhengmacuowu");
            resultInfo.setErrorMsg("验证码错误");
            //将info对象序列化为json
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(resultInfo);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);
            return;
        }

        //1.获取页面数据
        Map<String, String[]> userMap = request.getParameterMap();
        //2.封装对象
        User user = new User();
        try {
            //调用Bean直接封装
            BeanUtils.populate(user, userMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //3.调用service查询
        UserService service = new UserServicempi();
        //返回是否查询成功
        boolean flag = service.regist(user);
        ResultInfo resultInfo = new ResultInfo();
        if (flag){
            //如果注册成功
            resultInfo.setFlag(true);
        }else {
            //如果注册失败
            resultInfo.setFlag(false);
            System.out.println("zhuceshibai");
            resultInfo.setErrorMsg("注册失败");
        }
        //4.返回页面
        //将info对象序列化为json
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(resultInfo);

        //将json数据写回客户端
        //设置content-type
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);

    }
}
