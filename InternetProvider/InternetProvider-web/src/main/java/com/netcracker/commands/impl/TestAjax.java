/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.commands.impl;

import com.netcracker.commands.ICommand;
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
        names.add("Толя");
        names.add("Саня");
        names.add("Сашко");
        names.add("Ваня");
        names.add("Оксана");
        names.add("Ден");
        names.add("Міша");
        names.add("Аня");
        names.add("Олег");
        names.add("Артем");
        names.add("Андрій");
        names.add("Вася");
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return "<br>" + names.get(r.nextInt(12));
    }

}
