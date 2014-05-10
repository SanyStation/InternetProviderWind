/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.commands.implementations.csedashboard;

/**
 *
 * @author Anna
 */
@Deprecated
public class RequestTableTypes {

    public enum RequestTableType {

        SI_TABLE("SItable"),
        SI_TABLE_BY_PROVIDER_LOC("SItableByProvLoc");

        private String typeValue;

        private RequestTableType(String type) {
            typeValue = type;
        }

        static public RequestTableType getType(String pType) {
            for (RequestTableType type : RequestTableType.values()) {
                if (type.getTypeValue().equals(pType)) {
                    return type;
                }
            }
            throw new RuntimeException("unknown type");
        }

        public String getTypeValue() {
            return typeValue;
        }

    }
}
