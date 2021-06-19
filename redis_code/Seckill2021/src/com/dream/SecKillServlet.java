package com.dream;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

/**
 * @Author : huzejun
 * @Date: 2021/6/19-17:00
 * 秒杀案例
 */

public class SecKillServlet  extends HttpServlet {
   private static final long serialVersionUID = 1L;

    public SecKillServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userid = new Random().nextInt(50000) + "";
        String prodid = req.getParameter("prodid");

        boolean isSuccess = SecKill_redisByScript.doSecKill(userid, prodid);
//        boolean isSuccess = SecKill_redis.doSecKill(userid, prodid);
        resp.getWriter().print(isSuccess);
    }
}
