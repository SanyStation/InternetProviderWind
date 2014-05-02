package com.netcracker.wind.dao.interfaces;

import com.netcracker.wind.entities.Circuit;
import java.util.List;

/**
 *
 * @author Oksana
 */
public interface ICircuitDAO {

    public void add(Circuit circuit);

    public void delete(int idCircuit);

    public Circuit findByID(int idCircuit);

    public Circuit findByPort(int idPort);

    public Circuit findByServInst(int idSI);

    public List<Circuit> findAll();

    public void update(Circuit circuit);
}
