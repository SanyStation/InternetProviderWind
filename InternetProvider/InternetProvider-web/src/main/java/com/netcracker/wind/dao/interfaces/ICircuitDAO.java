package com.netcracker.wind.dao.interfaces;

import com.netcracker.wind.entities.Circuit;
import java.util.List;

/**
 * interface for working with Circuit entity and database
 * @author Oksana
 */
public interface ICircuitDAO extends IRowsCounter {

    public void add(Circuit circuit);

    public void delete(int шd);

    public Circuit findById(int id);

    public Circuit findByPort(int portId);
    
    public List<Circuit> findByNullPort();

    public Circuit findByServInst(int siId);

    public List<Circuit> findAll(int pageNumber, int pageSize);

    public void update(Circuit circuit);
    
}
