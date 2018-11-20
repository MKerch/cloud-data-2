/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cloud.service;

import com.cloud2.metadata.MetaDataDAOImpl;
import com.cloud2.metadata.MetaDateDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kerch
 */
@WebServlet(name = "testServlet", urlPatterns = {"/testServlet"})
public class SaveServlet extends HttpServlet {

    private MetaDateDAO dao;
    
    
    @Override
    public void init() throws ServletException {
    dao = new MetaDataDAOImpl();
    }
 
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
               
    }
}
