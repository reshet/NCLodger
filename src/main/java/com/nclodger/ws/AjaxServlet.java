package com.nclodger.ws;

import com.google.gson.Gson;
import com.nclodger.control.action.Action;
import com.nclodger.control.action.ActionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Iaroslav
 * Date: 20.10.13
 * Time: 11:36
 */
@Component("ajaxservlet")
public class AjaxServlet extends HttpServlet {
    //@Autowired ApplicationContext ctx;
    static public ApplicationContext ctx = new ClassPathXmlApplicationContext("bean-config.xml");
    static Gson gs = new Gson();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //response.setContentType("text/html;charset=UTF-8");
        String contents = null;
        //Action action = ActionFactory.getAction(request);
        String servicename = request.getPathInfo().substring(1,request.getPathInfo().length());
        AjaxService service = (AjaxService)ctx.getBean(servicename);
        try {
            Serializable s = service.execute(request, response);
            response.getWriter().print(gs.toJson(s));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        //dispatch(request,response,view);
       //request.
    }

   /* private void dispatch(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException {
       *//* String prefix ="";
        String sufix =".jsp";
        //request.setAttribute("");
        request.getRequestDispatcher(prefix + view + sufix).forward(request, response);*//*
    }*/
}
