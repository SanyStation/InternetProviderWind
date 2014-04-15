/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.dao;

import com.netcracker.wind.entities.User;
import java.util.List;

/**
 *
 * @author Anatolii
 */
public interface IUserDAO {

    public void add(User user);

    public void delete(int id);

    public User findByID(int id);

    public User findByEmailAndPassword(String email, String password);

    public void update(User user);
    public List<User> findAll();

    public List<User> findByRole(int roleID);

}
