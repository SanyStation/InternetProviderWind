package com.netcracker.wind.dao.interfaces.reports;

import com.netcracker.wind.entities.reports.SiProfit;
import java.util.List;

/**
 *
 * @author Alexander Kovriga
 */
public interface ISiProfitDAO {
    
    public List<SiProfit> findByDateTo(String dateTo);
    
}
