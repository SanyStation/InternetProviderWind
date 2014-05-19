package com.netcracker.wind.dao.interfaces.reports;

import com.netcracker.wind.entities.reports.SiOrder;
import java.util.List;

/**
 * The {@code ISiOrdersDAO} interface designed to provide interface for working
 * with persistence to manipulate {@code SiOrder} entities.
 * 
 * @author Alexander Kovriga
 */
public interface ISiOrdersDAO {

    /**
     * Types of order scenario.
     */
    public enum Scenario {

        NEW, DISCONNECT
    }

    /**
     * Finds all orders which have defined scenario per certain period.
     * 
     * @param dateFrom left limit of period
     * @param dateTo right limit of period
     * @return list of orders
     */
    List<SiOrder> findDateFromTo(String dateFrom, String dateTo);

}
