/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.netcracker.wind.mail;
import com.netcracker.wind.entities.Task;
import com.netcracker.wind.entities.User;



/**
 *
 * @author Oksana
 */
public class FormatedMail {
    private final String HELLO = "Welcome to Boreas Internet Provider!\n\n";
    private final String CONGRATULATION="Congratulation, ";
    private final String DEAR="Dear, ";
    private final String OK="!";
    private final String MESSAGE_ABOUT_TASK="We want to inform you that new task have been create.";
    private final String TASK_INFORMATION="\n\t Information about task:";
    private final String TASK_TAKE="\n If tou want to take your that task sign in boreas system.";
    private final String MESSAGE_BLOCKING_ACCOUNT="\nUnfortunally, we want to inform you that your acount have been blocked.";
    private final String CONTACT_US="\nFor more information contact our service senter";
    private final String MESSAGE_REGISTRATION = "\n\tYou have succesfully registred in boreas system\n"
            + "Your login is : ";
    private final String HOPE_PLEASED="\n\t We hope that you will be pleased with our service!";
    private final String END_OF_MASSAGE="\nSincerely,\n\tboreas staff.";
    public String getUserRegistrationMassage(User user){
        return HELLO+CONGRATULATION+user.getName()+OK+MESSAGE_REGISTRATION
                +user.getEmail()+HOPE_PLEASED+END_OF_MASSAGE;
    }
    
    public String getBlockedAccountMessage(User user){
        return DEAR+user.getName()+OK+MESSAGE_BLOCKING_ACCOUNT
                +CONTACT_US+END_OF_MASSAGE;
    }
    
    public String getInformGroupAboutTaskMessage(Task task){
        return MESSAGE_ABOUT_TASK+TASK_INFORMATION+task.getType()+TASK_TAKE+END_OF_MASSAGE;
    }
}
    
    

