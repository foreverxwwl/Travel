package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.FavoriteService;
import cn.itcast.travel.service.RouteService;
import cn.itcast.travel.service.impl.FavoriteServiceImpl;
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
    FavoriteService favoriteService = new FavoriteServiceImpl();
    /**
     * 分页显示
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接受参数
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String cidstr = request.getParameter("cid");
        String rname = request.getParameter("rname");
        //解决git请求中文乱码问题
        rname = new String(rname.getBytes("iso-8859-1"), "utf-8");
        if ("null".equals(rname)){
            rname = null;
        }
        //2.处理参数
        int cid = 0;
        if (cidstr != null && cidstr.length() > 0 && !"null".equals(cidstr)){
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
        PageBean<Route> pb = routeService.pageQuery(cid, currentPage, pageSize, rname);
        //4.序列化为Json返回
        writeValue(pb, response);
    }

    /**
     * 根据ID查询一个旅游路线的详细信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //1,接收id
        String rid = request.getParameter("rid");
        //2.调用service查询
        Route route = routeService.findOne(rid);
        //3.转为json返回
        writeValue(route,response);
    }

    /**
     * 判断是否收藏
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void isFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //1,获取线路id
        String rid = request.getParameter("rid");
        //2，获取当前登录用户user
        User user = (User) request.getSession().getAttribute("user");
        int uid = 0;
        //获取uid
        if (user == null){
            //用户未登录
            uid = 0;
        }else {
            uid = user.getUid();
        }
        //3.调用favoriteService查询是否收藏
        boolean flag = favoriteService.isFavorite(rid, uid);

        //4.写回客户端
        writeValue(flag,response);
    }

    public void addFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //1.获取rid
        String rid = request.getParameter("rid");
        //2.获取当前登录的用户
        User user = (User) request.getSession().getAttribute("user");
        int uid = 0;
        //获取uid
        if (user == null){
            //用户未登录
            return;
        }else {
            uid = user.getUid();
        }
        //3.调用service插入
        favoriteService.add(rid, uid);
    }


}
