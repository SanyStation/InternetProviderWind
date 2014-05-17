package com.netcracker.wind.dao.interfaces;

/**
 * The interface {@code IRowsCounter} designed to provide the ability counting
 * result rows after all "SELECT" queries for all DAO classes that implement
 * this interface.
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
