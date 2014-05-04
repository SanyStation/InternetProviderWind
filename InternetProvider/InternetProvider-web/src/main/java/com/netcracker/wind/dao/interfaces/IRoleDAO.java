package com.netcracker.wind.dao.interfaces;

import com.netcracker.wind.entities.Role;
import java.util.List;

/**
 *
 * @author Oksana
 */
public interface IRoleDAO extends IRowsCounter {

    public void add(Role role);

    public void delete(int id);

    public Role findById(int id);

    public List<Role> findAll(int pageNumber, int pageSize);

    public void update(Role role);

}
