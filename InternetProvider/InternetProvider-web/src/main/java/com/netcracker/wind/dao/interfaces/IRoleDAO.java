package com.netcracker.wind.dao.interfaces;

import com.netcracker.wind.entities.Role;
import java.util.List;

/**
 *
 * @author Oksana
 */
public interface IRoleDAO {

    public void add(Role role);

    public void delete(int id);

    public Role findByID(int role);

    public List<Role> findAll();

    public void update(Role role);

}
