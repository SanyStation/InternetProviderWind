package com.netcracker.wind.dao.interfaces.reports;

import com.netcracker.wind.entities.reports.SiProfit;
import java.util.List;

/**
 *
 * @author Alexander Kovriga
 */
public interface ISiProfitDAO {
    
    public List<SiProfit> findByMonth(int month, int year);
    public List<SiProfit> findByDateFromTo(String dateFrom, String dateTo);
    public List<SiProfit> findAll();
    
}
