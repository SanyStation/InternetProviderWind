package com.netcracker.wind.dao.implementations.oracle.reports;

import com.netcracker.wind.dao.interfaces.reports.ISiOrdersDAO;
import com.netcracker.wind.entities.reports.SiOrder;
import java.util.List;

/**
 *
 * @author Alexander Kovriga
 */
public class OracleSiOrdersDAO extends AbstractOracleSiDAO {

    public OracleSiOrdersDAO(ISiOrdersDAO.Scenario scenario) {
        super(scenario);
    }
    
    public List<SiOrder> findDateFromTo(String dateFrom, String dateTo) {
        return super.findDateFromTo(AbstractOracleSiDAO.SELECT, dateFrom,
                dateTo);
    }
    
    public List<SiOrder> findAll() {
        return findDateFromTo("", "");
    }

}
