package com.netcracker.wind.mail;

import com.netcracker.wind.entities.Service;
import com.netcracker.wind.entities.ServiceInstance;
import com.netcracker.wind.entities.ServiceOrder;
import com.netcracker.wind.entities.Task;
import com.netcracker.wind.entities.User;

/**
 *
 * @author Oksana and Sashko
 */
public class FormatedMail {

    private final String HELLO = "Welcome to Boreas Internet Provider!\n\n";
    private final String CONGRATULATION = "Congratulation, ";
    private final String DEAR = "Dear, ";
    private final String OK = "!";
    private final String ORDER = "/n/tYour order � ";
    private final String COMPLETED = " is completed.";
    private final String SERVICE = "/n/tService ";
    private final String SERVICEMOD = "/n/tYour service instant modify to ";
    private final String SERVWILLM = "/n/tYour service instant'll be modify to ";
    private final String INSTALLED = " is already installed in your location.";
    private final String DISCONNECT = " is already disconnect in your location.";
    private final String WILLDISCONNECT = " will be disconnect in your location.";
    private final String DORT = ".";
    private final String RECEIVED = " is received.";
    private final String WILLINST = " will be installed in your location";
    private final String MESSAGE_ABOUT_TASK = "We want to inform you that new task have been create.";
    private final String TASK_INFORMATION = "\n\t Information about task:";
    private final String TASK_TAKE = "\n If tou want to take your that task sign in boreas system.";
    private final String MESSAGE_BLOCKING_ACCOUNT = "\nUnfortunally, we want to inform you that your acount have been blocked.";
    private final String CONTACT_US = "\nFor more information contact our service senter";
    private final String MESSAGE_REGISTRATION = "\n\tYou have succesfully registred in boreas system\n"
            + "Your login is : ";
    private final String YOUR_PASSWORD = "\n\tYour password is : ";
    private final String HOPE_PLEASED = "\n\t We hope that you will be pleased with our service!";
    private final String END_OF_MASSAGE = "\nSincerely,\n\tboreas staff.";

    public String getUserRegistrationMassage(User user) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(HELLO).append(CONGRATULATION).append(user.getName()).append(OK)
                .append(MESSAGE_REGISTRATION).append(user.getEmail())
                .append(YOUR_PASSWORD).append(user.getPassword())
                .append(HOPE_PLEASED).append(END_OF_MASSAGE);
        return stringBuffer.toString();
    }

    public String getBlockedAccountMessage(User user) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(DEAR).append(user.getName()).append(OK).append(MESSAGE_BLOCKING_ACCOUNT)
                .append(CONTACT_US).append(END_OF_MASSAGE);
        return stringBuffer.toString();
    }

    public String getInformGroupAboutTaskMessage(Task task) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(MESSAGE_ABOUT_TASK).append(TASK_INFORMATION).append(task.getType())
                .append(TASK_TAKE).append(END_OF_MASSAGE);
        return stringBuffer.toString();
    }

    public String getNewSOComplMassage(ServiceOrder order, Service service, User user) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(DEAR).append(user.getName()).append(OK).append(ORDER)
                .append(order.getId()).append(COMPLETED).append(SERVICE)
                .append(service.getName()).append(INSTALLED).append(END_OF_MASSAGE);
        return stringBuffer.toString();
    }

    public String getNewSOTakeMassage(ServiceOrder order, Service service, User user) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(DEAR).append(user.getName()).append(OK)
                .append(ORDER).append(order.getId()).append(RECEIVED)
                .append(SERVICE).append(service.getName()).append(WILLINST)
                .append(END_OF_MASSAGE);
        return stringBuffer.toString();
    }

    public String getModifySOComplMassage(ServiceOrder order, Service service, User user) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(DEAR).append(user.getName()).append(OK)
                .append(ORDER).append(order.getId()).append(COMPLETED)
                .append(SERVICEMOD).append(service.getName()).append(DORT)
                .append(END_OF_MASSAGE);
        return stringBuffer.toString();
    }

    public String getModifySOTakeMassage(ServiceOrder order, Service service, User user) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(DEAR).append(user.getName()).append(OK)
                .append(ORDER).append(order.getId()).append(RECEIVED)
                .append(SERVWILLM).append(service.getName()).append(DORT)
                .append(END_OF_MASSAGE);
        return stringBuffer.toString();
    }

    public String getDiscSOComplMassage(ServiceOrder order, Service service, User user) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(DEAR).append(user.getName()).append(OK)
                .append(ORDER).append(order.getId()).append(COMPLETED)
                .append(SERVICE).append(service.getName()).append(DISCONNECT)
                .append(END_OF_MASSAGE);
        return stringBuffer.toString();
    }

    public String getDiscSOTakeMassage(ServiceOrder order, Service service, User user) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(DEAR).append(user.getName()).append(OK).append(ORDER)
                .append(order.getId()).append(RECEIVED).append(SERVICE)
                .append(service.getName()).append(WILLDISCONNECT).append(END_OF_MASSAGE);
        return stringBuffer.toString();
    }

    public String getSentBillMassage(ServiceInstance serviceInstance, Service service, User user) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Sent Bill Massage");
        return stringBuffer.toString();
    }
}
