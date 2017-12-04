package com.tuz.coffemachine.commands;

import com.tuz.coffemachine.logic.DrinkLogic;
import com.tuz.coffemachine.logic.LoginLogic;
import com.tuz.coffemachine.logic.OrderLogic;
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

public class LoginCommand implements ActionCommand {

    private static final Logger LOG= Logger.getLogger(LoginCommand.class);
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static final String ATTR_NAME_LANG = "lang";
    private static final String ATTR_NAME_PAGE = "current_page";

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);
        LoginLogic loginLogic = new LoginLogic();
        HttpSession session = request.getSession(true);
        String locale = null;
        if (session.getAttribute(ATTR_NAME_LANG) == null) {
            locale = "ru";
            session.setAttribute(ATTR_NAME_LANG, "ru_RU");
        } else {
            locale = (String) session.getAttribute(ATTR_NAME_LANG);
            locale = locale.split("_")[0];
        }
        try {
            ArrayList<Drink> drinks = new DrinkLogic().getAllDrinks();
            ArrayList<Order> orders = new OrderLogic().getAllOrders();
            User user = loginLogic.getUserByLogin(login);

            if (loginLogic.checkLogin(login, pass)) {
                String role = user.getRole();
                switch (role.toUpperCase()) {
                    case "ADMINISTRATOR":
                        session.setAttribute("user", user);
                        session.setAttribute("drinks_list", drinks);
                        page = ConfigurationManager.getProperty("path.page.adminpage");
                        session.setAttribute(ATTR_NAME_PAGE, page);
                        break;
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
                        session.setAttribute(ATTR_NAME_PAGE, page);
                        break;
                }
            } else {
                request.setAttribute("errorLoginPassMessage", new MessageManager().getProperty("message.loginerror", locale));
                page = ConfigurationManager.getProperty("path.page.login");
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
