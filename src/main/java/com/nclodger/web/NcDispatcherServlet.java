package com.nclodger.web;

import com.nclodger.dao.UserDao;
import com.nclodger.dao.Users;
import com.nclodger.mail.MailServiceBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.annotation.PostConstruct;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.registry.infomodel.User;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: reshet
 * Date: 10/15/13
 * Time: 6:42 PM
 * To change this template use File | Settings | File Templates.
 */
@WebServlet(name = "NcDispatcherServlet")
public class NcDispatcherServlet extends HttpServlet {
    UserDao userdao;
    MailServiceBean mailbean;

    //@PostConstruct
    public void init() {
        try {
            super.init();
        } catch (ServletException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        System.out.println("============================= Init");
        WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        //ApplicationContext context = new ClassPathXmlApplicationContext("/WEB-INF/mvc-dispatcher-servlet.xml");
        userdao = ctx.getBean("userdao", UserDao.class);
        mailbean = ctx.getBean("mail-service", MailServiceBean.class);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getRequestURI();
        if(path.equals(b+"register")
            // && !bo
                ){
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String pswd = request.getParameter("password");
            String user_type = request.getParameter("user_type");
            Users user = new Users(0,email,pswd,username,0);
            userdao.insert(user);
            mailbean.sendRegisterMail(email);


            response.getWriter().append("<h2>Register done! Confirmation email sent out to "+username+"</h2>");

        }
    }

    String b = "/NCLodger/";
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getRequestURI();
      /*  Object obj = request.getAttribute("dispatched");
        Boolean bo = false;
        if(obj != null){
            bo = (Boolean)obj;
        }*/
        if(path.equals(b)
               // && !bo
                ){
           String nextURI = "/index.jsp";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextURI);
            dispatcher.forward(request,response);
        }

       /* else{
            request.setAttribute("dispatched",true);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(path);
            dispatcher.forward(request,response);
        }*/



    }
}
