package com.netcracker.wind.dao.interfaces;

import com.netcracker.wind.entities.Cable;
import java.util.List;

/**
 *
 * @author Oksana
 */
public interface ICableDAO {

    public void add(Cable cable);

    public void delete(int idCable);

    public Cable findByID(int idCable);

    public Cable findByPort(int idPort);

    public List<Cable> findAll();

    public Cable findByServiceLocation(int idSL);

    public void update(Cable cable);
}
