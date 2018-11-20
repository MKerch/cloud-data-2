/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cloud.auth;

/**
 *
 * @author kerch
 */
public interface AuthDAO {
    
    public void saveUser(String name, String password, String email, String role);
    
    public boolean validateUserPassword(String userName, String password);
    
}
