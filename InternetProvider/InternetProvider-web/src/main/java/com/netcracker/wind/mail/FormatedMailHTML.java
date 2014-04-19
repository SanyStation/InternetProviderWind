/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.mail;

import com.hp.gagawa.java.Document;
import com.hp.gagawa.java.DocumentType;
import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Br;
import com.hp.gagawa.java.elements.Div;
import com.hp.gagawa.java.elements.H1;
import com.hp.gagawa.java.elements.Text;
import com.netcracker.wind.entities.Task;
import com.netcracker.wind.entities.User;

/**
 *
 * @author Oksana
 */
public class FormatedMailHTML {

    private final String HELLO = "Welcome to Boreas Internet Provider!";
    private final String CONGRATULATION = "Congratulation, ";
    private final String DEAR = "Dear, ";
    private final String OK = "!";
    private final String TASK = "New Task information";
    private final String MESSAGE_ABOUT_TASK = "We want to inform you that new task have been create.";
    private final String TASK_INFORMATION = "Information about task:";
    private final String TASK_TAKE = "If tou want to take your that task sign in boreas system.";
    private final String MESSAGE_BLOCKING_ACCOUNT = "Unfortunally, we want to inform you that your acount have been blocked.";
    private final String CONTACT_US = "For more information contact our service senter";
    private final String MESSAGE_REGISTRATION = "You have succesfully registred in boreas system"
            + "Your login is : ";
    private final String HOPE_PLEASED = " We hope that you will be pleased with our service!";
    private final String END_OF_MASSAGE = "Sincerely,";
    private final String BOREAS_STAFF = "boreas staff.";
    private final String MORE_INFO = "For more information go to";
    private final String LINK = "http://www.boreas.ml/InternetProvider-web/index.jsp";
    private final String BOREAS = "boreas.ml";

    public String getUserRegistrationMassage(User user) {
        Document document = new Document(DocumentType.XHTMLStrict);

        document.body.setBgcolor("#0000FF");
        H1 hello = new H1();
        hello.appendChild(new Text(HELLO));

        Div d1 = new Div().setId("myDiv");
        d1.appendChild(new Text(CONGRATULATION + user.getName() + OK));
        d1.appendChild(new Br());
        d1.appendChild(new Text(MESSAGE_REGISTRATION + user.getEmail()));
        d1.appendChild(new Br());
        d1.appendChild(new Text(HOPE_PLEASED));
        d1.appendChild(new Br());
        d1.appendChild(new Text(MORE_INFO));
        d1.appendChild(new A(LINK, "_blank")
                .appendChild(new Text(BOREAS)));
        d1.appendChild(new Br());
        d1.appendChild(new Text(END_OF_MASSAGE));
        d1.appendChild(new Br());
        d1.appendChild(new Text(BOREAS_STAFF));
        // Img img = new Img("http://www.w3schools.com/tags/angry.gif","");
        //d2.appendChild(new Br());
        //d2.appendChild(img);
        document.body.appendChild(d1);
        return document.write();

    }

    public String getBlockedAccountMessage(User user) {
        Document document = new Document(DocumentType.XHTMLStrict);

        document.body.setBgcolor("#0000FF");
        H1 hello = new H1();
        hello.appendChild(new Text(DEAR + user.getName()));

        Div d1 = new Div().setId("myDiv");
        d1.appendChild(new Text(MESSAGE_BLOCKING_ACCOUNT));
        d1.appendChild(new Br());
        d1.appendChild(new Text(CONTACT_US));
        d1.appendChild(new Br());
        d1.appendChild(new A(LINK, "_blank")
                .appendChild(new Text(BOREAS)));
        d1.appendChild(new Br());
        d1.appendChild(new Text(END_OF_MASSAGE));
        d1.appendChild(new Br());
        d1.appendChild(new Text(BOREAS_STAFF));
        // Img img = new Img("http://www.w3schools.com/tags/angry.gif","");
        //d2.appendChild(new Br());
        //d2.appendChild(img);
        document.body.appendChild(d1);
        return document.write();

    }

    public String getInformGroupAboutTaskMessage(Task task) {
        Document document = new Document(DocumentType.XHTMLStrict);

        document.body.setBgcolor("#0000FF");
        H1 hello = new H1();
        hello.appendChild(new Text(TASK));

        Div d1 = new Div().setId("myDiv");
        d1.appendChild(new Text(MESSAGE_ABOUT_TASK));
        d1.appendChild(new Br());
        d1.appendChild(new Text(TASK_INFORMATION));
        d1.appendChild(new Br());
        d1.appendChild(new Text(TASK_TAKE));
        d1.appendChild(new Br());
        d1.appendChild(new A(LINK, "_blank")
                .appendChild(new Text(BOREAS)));
        d1.appendChild(new Br());
        d1.appendChild(new Text(END_OF_MASSAGE));
        d1.appendChild(new Br());
        d1.appendChild(new Text(BOREAS_STAFF));
        // Img img = new Img("http://www.w3schools.com/tags/angry.gif","");
        //d2.appendChild(new Br());
        //d2.appendChild(img);
        document.body.appendChild(d1);
        return document.write();
    }
}
