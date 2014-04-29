/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
