/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.netcracker.wind.commands.implementations.csedashboard;

import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.ITaskDAO;
import com.netcracker.wind.entities.Task;
import com.netcracker.wind.entities.User;
import com.netcracker.wind.mail.FormatedMail;
import com.netcracker.wind.mail.MailSendler;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Oksana
 */
public class SentBill implements ICommand{

    public String execute(HttpServletRequest request, HttpServletResponse response) {
         HttpSession session = request.getSession(false);
        if(session == null){
            return "";
        }
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "";
        }
        Task task = (Task) session.getAttribute("task");
        List<User> users = new ArrayList<User>();
                users.add(user);
                
         new MailSendler().sendEmail(users, "Boreas Bill", new FormatedMail().getSentBillMassage(null, null, user));
         task.setStatus(Task.Status.COMPLETED);
         ITaskDAO taskDAO = FactoryCreator.getInstance().getFactory().createTaskDAO();
         taskDAO.update(task); 
        return "";
    }
    
}
