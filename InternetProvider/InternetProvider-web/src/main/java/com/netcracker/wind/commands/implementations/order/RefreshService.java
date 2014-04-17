/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.commands.implementations.order;

import com.netcracker.wind.commands.ICommand;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Anatolii
 */
public class RefreshService implements ICommand {

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        //TODO getParameters
        //TODO logic(calculate answer) 
        JSONArray array = new JSONArray();
        JSONObject jsono = new JSONObject();
        //TODO build answer
        return array.toString();
    }

}
