package com.netcracker.wind.dao.interfaces;

import com.netcracker.wind.dao.implementations.helper.AbstractOracleDAO.Direction;
import com.netcracker.wind.entities.User;
import java.util.List;

/**
 *
 * @author Anatolii
 */
public interface IUserDAO extends IRowsCounter {

    public int add(User user);

    public void delete(int id);

    public User findById(int id);

    public User findByEmail(String email);

    public void update(User user);

    public int updatePass(User user);

    public List<User> findAll(int pageNumber, int pageSize);
    
    public List<User> findAll(int pageNumber, int pageSize, String orderPara,
            Direction direction);

    public List<User> findByRole(int roleID);

    public List<User> findByRole(int roleID, int pageNumber, int pageSize);
    
    public List<User> findByRole(int roleID, int pageNumber, int pageSize,
            String orderPara, Direction direction);

    public boolean hasEmail(String email);

    public boolean hasLogin(String login);

}
