/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cloud.auth;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kerch
 */
@WebServlet(name = "LogInServlet", urlPatterns = {"/LogInServlet"})
public class LoginServlet extends HttpServlet {

    
    private AuthDAO dao;
    
    @Override
    public void init() throws ServletException {
        try {
            dao = new AuthDAOImpl();
        } catch (Exception ex) {
        throw new RuntimeException(ex);
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("username");
        String password = req.getParameter("password");
        boolean userexist = dao.validateUserPassword(userName, password);
        if(userexist){
            req.getSession().setAttribute("user", userName);
            resp.sendRedirect("pages/account.jsp");
        }else{
            resp.sendRedirect("pages/welcome.jsp");
        }
    }

    

}
