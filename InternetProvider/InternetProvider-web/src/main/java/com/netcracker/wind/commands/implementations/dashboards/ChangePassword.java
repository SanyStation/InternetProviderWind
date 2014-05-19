package com.netcracker.wind.commands.implementations.dashboards;

import com.netcracker.wind.annotations.RolesAllowed;
import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.commands.implementations.registration.Validator;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.IUserDAO;
import com.netcracker.wind.entities.Role;
import com.netcracker.wind.entities.User;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class-command allows change own password. CSE user can change own password
 * and password for customer user. Another roles that exist in system can change
 * only own password.
 *
 * @author Anatolii
 * @author j_mart
 */
@RolesAllowed(roles
        = {Role.Roles.Administrator,
            Role.Roles.CustomerSupportEngineer,
            Role.Roles.CustomerUser,
            Role.Roles.InstallationEngineer,
            Role.Roles.ProvisioningEngineer})
public class ChangePassword implements ICommand {
    
    private static final Logger LOGGER
            = Logger.getLogger(ChangePassword.class.getName());

    private static final String PASSWORD = "password";
    private static final String CONF_PASSWORD = "conf_password";
    private static final String OLD_PASSWORD = "old_password";
    private static final String USER = "user";
    private static final String USER_ID = "user_id";
    private static final String ANSWER = "answer";
    private static final String MESSAGE = "message";

    private static final String VALIDATION_PASSWORD = "pass";
    private static final String VALIDATION_CONF_PASSWORD = "confpass";

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String pass = request.getParameter(PASSWORD);
        String confPass = request.getParameter(CONF_PASSWORD);
        String oldPass = request.getParameter(OLD_PASSWORD);
        HashMap<String, String> newUserData = new HashMap();
        newUserData.put(VALIDATION_PASSWORD, pass);
        newUserData.put(VALIDATION_CONF_PASSWORD, confPass);
        //Validation
        Validator validator = new Validator(newUserData);
        String validMessage = validator.validatePasswords();
        JSONObject answer = new JSONObject();
        //if ("".equals(pass) || !pass.equals(confPass)) {
        if (validator.getIsvalid() == 0) {
            try {
                answer.put(ANSWER, false);
                answer.put(MESSAGE, validMessage);
            } catch (JSONException ex) {
                LOGGER.error(null, ex);
            }
            return answer.toString();
        }

        User user = (User) request.getSession(false).getAttribute(USER);
        IUserDAO userDAO = FactoryCreator.getInstance().getFactory().createUserDAO();
        int userId = Integer.parseInt(((String) request.getParameter(USER_ID)));
        if (user.getRoleId() != Role.CSE_GROUP_ID && userId != user.getId() || (userId == user.getId() && ((oldPass instanceof String) ? (!DigestUtils.md5Hex(oldPass).equals(user.getPassword())) : true) ) ) {
             try {
                answer.put(ANSWER, false);
                answer.put(MESSAGE, "Wrong current password!");
            } catch (JSONException ex) {
                LOGGER.error(null, ex);
            }
            return answer.toString();
        }
        User user2 = userDAO.findById(userId);
        if(user.getRoleId() == Role.CSE_GROUP_ID && user2.getRoleId() != Role.CU_GROUP_ID && userId != user.getId()){
             try {
                answer.put(ANSWER, false);
                answer.put(MESSAGE, "Access error");
            } catch (JSONException ex) {
                LOGGER.error(null, ex);
            }
            return answer.toString();
        }
        user2.setPassword(pass);
        int result = userDAO.updatePass(user2);
        try {
            if (result == 1) {
                answer.put(ANSWER, true);
            } else {
                answer.put(ANSWER, false);
                answer.put(MESSAGE, "Unknown server error");
            }
        } catch (JSONException ex) {
            LOGGER.error(null, ex);
        }
        return answer.toString();
    }

}
