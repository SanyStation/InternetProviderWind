/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.dao.factory;

import com.netcracker.wind.dao.IRoleDAO;
import com.netcracker.wind.dao.IUserDAO;
import com.netcracker.wind.dao.impl.RoleDAO;
import com.netcracker.wind.dao.impl.UserDAO;

/**
 *
 * @author Oksana
 */
public class DAOFactory {

    public static IUserDAO createUserDAO() {
        return new UserDAO();
    }
     public static IRoleDAO createRoleDAO() {
        return new RoleDAO();
    }

}
