/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cloud.auth;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author kerch
 */
@WebFilter(filterName = "AuthFilter2", urlPatterns = {"/pages/account.jsp"})
public class AuthFilter2 implements Filter {


    
    private FilterConfig config;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        config=filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest)request;
        HttpSession session = req.getSession();
        if(session==null||session.getAttribute("user")==null){
          HttpServletResponse res = (HttpServletResponse)response;
          res.sendRedirect("login.jsp");
        }
        else{
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        
    }
    
    
}
