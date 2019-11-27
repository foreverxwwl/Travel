package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.service.RouteService;
import cn.itcast.travel.service.impl.RouteSerciceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @outhor li
 * @create 2019-11-26 19:33
 * 路线展示，分页查询
 */
@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {
    RouteService routeService = new RouteSerciceImpl();
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接受参数
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String cidstr = request.getParameter("cid");
        //2.处理参数
        int cid = 0;
        if (cidstr != null && cidstr.length() > 0){
            cid = Integer.parseInt(cidstr);
        }
        int currentPage = 0;//当前页码默认为第一页
        if (currentPageStr != null && currentPageStr.length() > 0){
            currentPage = Integer.parseInt(currentPageStr);
        }else {
            currentPage = 1;
        }
        //页面大小、默认为5
        int pageSize = 0;
        if (pageSizeStr != null && pageSizeStr.length() > 0){
            pageSize = Integer.parseInt(pageSizeStr);
        }else {
            pageSize = 5;
        }
        //3.调动service查询
        PageBean<Route> pb = routeService.pageQuery(cid, currentPage, pageSize);
        //4.序列化为Jason返回
        writeValue(pb, response);
    }


}
