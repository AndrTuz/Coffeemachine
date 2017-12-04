package com.tuz.coffemachine.commands;

import com.tuz.coffemachine.logic.DrinkLogic;
import com.tuz.coffemachine.logic.LoginLogic;
import com.tuz.coffemachine.logic.OrderLogic;
import com.tuz.coffemachine.logic.RegistrationLogic;
import com.tuz.coffemachine.logic.exception.LogicException;
import com.tuz.coffemachine.models.Drink;
import com.tuz.coffemachine.models.Order;
import com.tuz.coffemachine.models.User;
import com.tuz.coffemachine.resources.ConfigurationManager;
import com.tuz.coffemachine.resources.MessageManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class RegistrationNewUserCommand implements ActionCommand {
    private static final Logger LOG= Logger.getLogger(RegistrationNewUserCommand.class);
    private static final String PARAM_NAME_LOGIN = "registration_login";
    private static final String PARAM_NAME_PASSWORD = "registration_password";
    private static final String PARAM_NAME_CARDPIN = "registration_cardpin";
    private static final String REGISTRATION_ROLE = "registration_role";
    private static final String ATTR_NAME_LANG = "lang";
    private static final String ATTR_NAME_PAGE = "current_page";

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String registration_login = request.getParameter(PARAM_NAME_LOGIN);
        String registration_password = request.getParameter(PARAM_NAME_PASSWORD);
        String registration_cardpin = request.getParameter(PARAM_NAME_CARDPIN);
        String registration_role = request.getParameter(REGISTRATION_ROLE);
        HttpSession session = request.getSession(true);
        String locale=null;
        if(session.getAttribute(ATTR_NAME_LANG)==null){
            locale="ru";
            session.setAttribute(ATTR_NAME_LANG,"ru_RU");
        }else{
            locale = (String) session.getAttribute(ATTR_NAME_LANG);
            locale = locale.split("_")[0];
        }
        try {
            ArrayList<Drink> drinks = new DrinkLogic().getAllDrinks();
            ArrayList<Order> orders = new OrderLogic().getAllOrders();
            RegistrationLogic registrationLogic = new RegistrationLogic();
            Boolean checkLogin = registrationLogic.checkLogin(registration_login);
            Boolean checkPasswordLength = (registration_password.length() > 5);
            if (!checkLogin && checkPasswordLength && !registration_cardpin.isEmpty()) {
                if (registrationLogic.createNewUser(registration_login, registration_password, registration_cardpin, registration_role)) {
                    String role = registration_role;
                    User user = new LoginLogic().getUserByLogin(registration_login);
                    switch (role.toUpperCase()) {
                        case "USER":
                            session.setAttribute("user", user);
                            session.setAttribute("drinks_list", drinks);
                            page = ConfigurationManager.getProperty("path.page.usermain");
                            session.setAttribute(ATTR_NAME_PAGE, page);
                            break;
                        case "DELIVERYMAN":
                            session.setAttribute("user", user);
                            session.setAttribute("order_list", orders);
                            page = ConfigurationManager.getProperty("path.page.deliverymanpage");
                            session.setAttribute(ATTR_NAME_PAGE, page);
                            break;
                        default:
                            page = ConfigurationManager.getProperty("path.page.error");
                            request.getSession().invalidate();
                            break;
                    }
                } else {
                    request.setAttribute("errorRegistrationMessage", new MessageManager().getProperty("message.errorcreate", locale));
                    page = ConfigurationManager.getProperty("path.page.registration");
                    session.setAttribute(ATTR_NAME_PAGE, page);
                }
            } else {
                if (checkLogin == true) {
                    request.setAttribute("errorRegistrationMessage", new MessageManager().getProperty("message.userexists", locale));
                } else if (!checkPasswordLength) {
                    request.setAttribute("errorRegistrationMessage", new MessageManager().getProperty("message.shortpassword", locale));
                } else {
                    request.setAttribute("errorRegistrationMessage", new MessageManager().getProperty("message.pinisempty", locale));
                }
                page = ConfigurationManager.getProperty("path.page.registration");
                session.setAttribute(ATTR_NAME_PAGE, page);
            }
        }catch (LogicException ex){
            LOG.error(ex);
            page=ConfigurationManager.getProperty("path.page.error");
            return page;
        }
        return page;
    }
}
