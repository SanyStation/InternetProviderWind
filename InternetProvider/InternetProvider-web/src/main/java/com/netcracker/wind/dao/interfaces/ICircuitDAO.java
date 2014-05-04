package com.netcracker.wind.dao.interfaces;

import com.netcracker.wind.entities.Circuit;
import java.util.List;

/**
 *
 * @author Oksana
 */
public interface ICircuitDAO extends IRowsCounter {

    public void add(Circuit circuit);

    public void delete(int шd);

    public Circuit findById(int id);

    public Circuit findByPort(int portId);

    public Circuit findByServInst(int siId);

    public List<Circuit> findAll(int pageNumber, int pageSize);

    public void update(Circuit circuit);
    
}
