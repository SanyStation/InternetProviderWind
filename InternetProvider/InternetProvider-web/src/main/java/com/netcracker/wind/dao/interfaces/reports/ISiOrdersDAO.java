package com.netcracker.wind.dao.interfaces.reports;

import com.netcracker.wind.entities.reports.SiOrder;
import java.util.List;

/**
 *
 * @author Alexander Kovriga
 */
public interface ISiOrdersDAO {

    public enum Scenario {

        New, disconnect
    }

    List<SiOrder> findDateFromTo(String dateFrom, String dateTo);
    List<SiOrder> findAll();

}
