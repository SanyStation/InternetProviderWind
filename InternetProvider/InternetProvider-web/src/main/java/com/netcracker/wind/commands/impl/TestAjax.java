/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.commands.impl;

import com.netcracker.wind.commands.ICommand;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Anatolii
 */
public class TestAjax implements ICommand {

    private final List<String> names = new ArrayList<String>();
    private final Random r = new Random();

    public TestAjax() {
        names.add("xottabut");
        names.add("SanyStation");
        names.add("sashko");
        names.add("j-martyn");
        names.add("Oksana");
        names.add("t0xicDen");
        names.add("myshkoua");
        names.add("AnnetG");
        names.add("Oleg");
        names.add("Artem");
        names.add("ANdrii");
        names.add("my little pony");
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return "<br>" + names.get(r.nextInt(12));
    }

}
