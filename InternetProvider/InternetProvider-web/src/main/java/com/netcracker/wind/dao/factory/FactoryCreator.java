/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.dao.factory;

import com.netcracker.wind.dao.factory.impl.OracleDAOFactory;

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
