/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.netcracker.wind.dao;

/**
 *
 * @author Oksana
 */
public class DAOFactory {
    public static UserDAO createUserDAO(){
        return new UserDAO();
    } 
    
}
