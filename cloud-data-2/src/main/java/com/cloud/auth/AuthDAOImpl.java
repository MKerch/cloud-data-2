/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cloud.auth;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author kerch
 */
public class AuthDAOImpl implements AuthDAO {

    private DataSource ds;

    public AuthDAOImpl() throws NamingException {
        InitialContext context = new InitialContext();
        ds = (DataSource) context.lookup("java:comp/env/cloudData2DataSource");
    }

    @Override
    public void saveUser(String name, String password, String email, String role) {
        try (Connection con = ds.getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO users (name, password, email, role) VALUES(?,?,?,?)");
            ps.setString(1, name);
            ps.setString(2, password);
            ps.setString(3, email);
            ps.setString(4, role);
            ps.execute();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean validateUserPassword(String userName, String password) {
        try (Connection con = ds.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM users WHERE name=? and password=?");
            ps.setString(1, userName);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1) == 1;
            }
            return false;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
