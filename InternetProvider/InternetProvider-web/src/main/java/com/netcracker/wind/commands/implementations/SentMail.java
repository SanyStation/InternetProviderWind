package com.netcracker.wind.commands.implementations;

import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.entities.User;
import com.netcracker.wind.mail.FormatedMail;
import com.netcracker.wind.mail.MailSendler;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Oksana
 */
public class SentMail implements ICommand {

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int userID = Integer.parseInt(request.getParameter("user_id"));
        AbstractFactoryDAO factoryDAO
                = FactoryCreator.getInstance().getFactory();
        User user = factoryDAO.createUserDAO().findById(userID);
        List<User> users = new ArrayList<User>();
        if (user != null) {
            new MailSendler().sendEmail(users, "Boreas Registration", new FormatedMail().getUserRegistrationMassage(user));
        }
        return "/index.jsp";
    }

}
