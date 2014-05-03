package com.netcracker.wind.dao.interfaces;

import com.netcracker.wind.entities.User;
import java.util.List;

/**
 *
 * @author Anatolii
 */
public interface IUserDAO {

    public void add(User user);

    public void delete(int id);

    public User findById(int id);

    public User findByEmail(String email);

    public void update(User user);
    
    public List<User> findAll(int pageNumber, int pageSize);
    
    public List<User> findByRole(int roleID, int pageNumber, int pageSize);
    
    public boolean hasEmail(String email);
    
    public boolean hasLogin(String login);
    
    public int getRows();

}
