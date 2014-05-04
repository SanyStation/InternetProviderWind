package com.netcracker.wind.dao.interfaces;

/**
 *
 * @author Alexander Kovriga
 */
public interface IRowsCounter {
    
    /**
     * Returns number of total rows of specified query.
     * 
     * @return number of total rows
     */
    public int countRows();
    
}
