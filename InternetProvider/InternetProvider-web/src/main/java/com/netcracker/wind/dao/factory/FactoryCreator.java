package com.netcracker.wind.dao.factory;

import com.netcracker.wind.dao.factory.implementations.OracleDAOFactory;

/**
 *
 * @author Anatolii
 */
public class FactoryCreator {

    private static final FactoryCreator FACTORY_HELPER = new FactoryCreator();
    private final AbstractFactoryDAO factoryDAO;

    private FactoryCreator() {
        factoryDAO = new OracleDAOFactory();
    }

    public static final FactoryCreator getInstance() {
        return FACTORY_HELPER;
    }

    public AbstractFactoryDAO getFactory() {
        return factoryDAO;
    }

}
