package com.nclodger.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * User: reshet
 * Date: 10/15/13
 * Time: 7:12 PM
 * To change this template use File | Settings | File Templates.
 */
@WebServlet(name = "StaticServlet")
public class StaticServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getRequestURI();
        /*if(path.contains("/css/") || path.contains("/img/") || path.contains("/js/")){

            response.sendRedirect(path);
        }*/
        /*ServletContext context = getServletContext();

        InputStream inp = context.getResourceAsStream(path);

        if(inp!=null){
            byte[] buffer = new byte[1024]; // Adjust if you want
            int bytesRead;
            while ((bytesRead = inp.read(buffer)) != -1)
            {
                response.getOutputStream().write(buffer, 0, bytesRead);
            }
            //response.getOutputStream().write(inp.read());
        }*/
/*        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(path);
        dispatcher.forward(request,response);*/
    }
}
