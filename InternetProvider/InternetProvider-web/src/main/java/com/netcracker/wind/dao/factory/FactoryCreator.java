package com.netcracker.wind.dao.factory;

import com.netcracker.wind.dao.factory.implementations.OracleDAOFactory;

/**
 * Class for abstracting from creating particular DAO factory. This allows easy
 * change implementation DAO factory.
 *
 * @author Anatolii
 */
public class FactoryCreator {

    private static final FactoryCreator FACTORY_HELPER = new FactoryCreator();
    private final AbstractFactoryDAO factoryDAO;

    private FactoryCreator() {
        factoryDAO = new OracleDAOFactory();
    }

    /**
     * Method allows to get instance of singleton {@link FactoryCreator}
     *
     * @return instance of singleton {@link FactoryCreator}
     */
    public static final FactoryCreator getInstance() {
        return FACTORY_HELPER;
    }

    /**
     * Method allows to get instance of DAO factory.
     *
     * @return instance of DAO factory.
     */
    public AbstractFactoryDAO getFactory() {
        return factoryDAO;
    }

}
