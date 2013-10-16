package com.nclodger.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
