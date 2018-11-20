/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cloud.auth;

import java.io.IOException;
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
@WebServlet(name = "SignupServlet", urlPatterns = {"/SignupServlet"})
public class SignupServlet extends HttpServlet {

    private AuthDAO daoauth;
    
    @Override
    public void init(){
        try {
            daoauth = new AuthDAOImpl();
        } catch (NamingException ex) {
        throw new RuntimeException(ex);
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password1 = req.getParameter("password1");
        String password2 = req.getParameter("password2");
        String role = "userrole";
        if(password1.equals(password2)){
            daoauth.saveUser(username, password1, email, role);
            resp.sendRedirect("pages/welcome.jsp");
        }else{
            resp.sendRedirect("pages/error_signup.jsp");
        }
    }

}
