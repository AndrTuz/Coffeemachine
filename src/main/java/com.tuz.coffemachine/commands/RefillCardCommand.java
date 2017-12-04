package com.tuz.coffemachine.commands;

import com.tuz.coffemachine.logic.LoginLogic;
import com.tuz.coffemachine.logic.exception.LogicException;
import com.tuz.coffemachine.models.User;
import com.tuz.coffemachine.resources.ConfigurationManager;
import com.tuz.coffemachine.resources.MessageManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RefillCardCommand implements ActionCommand {
    private static final Logger LOG= Logger.getLogger(RefillCardCommand.class);
    private static final String USER = "user";
    private static final String PARAM_PIN_USER = "pin";
    private static final String PARAM_NAME_REFILL_SUM = "refill_sum";
    private static final String ATTR_NAME_LANG = "lang";
    private static final String ATTR_NAME_PAGE = "current_page";

    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.refillcard");
        HttpSession session = request.getSession(true);
        String locale=null;
        if(session.getAttribute(ATTR_NAME_LANG)==null){
            locale="ru";
            session.setAttribute(ATTR_NAME_LANG,"ru_RU");
        }else{
            locale = (String) session.getAttribute(ATTR_NAME_LANG);
            locale = locale.split("_")[0];
        }
        User currentUser = (User) session.getAttribute(USER);
        String login = currentUser.getLogin();
        String pin = request.getParameter(PARAM_PIN_USER);
        LoginLogic loginLogic = new LoginLogic();
        String refill_sum_par = request.getParameter(PARAM_NAME_REFILL_SUM);
        if (refill_sum_par.isEmpty() || refill_sum_par == null){
            try {
                Integer.parseInt(refill_sum_par);
            } catch (NumberFormatException e) {
                System.out.println(e);
            }
            session.setAttribute("errorRefill", new MessageManager().getProperty("message.errorSum", locale));
            page = ConfigurationManager.getProperty("path.page.refillcard");
            session.setAttribute(ATTR_NAME_PAGE, page);
            return page;
        }
        int refill_sum = Integer.parseInt(refill_sum_par);

        try {
            if (loginLogic.checkPIN(login, pin)) {
                loginLogic.refillSum(login, refill_sum);
                session.setAttribute("user", loginLogic.getUserByLogin(login));
                session.setAttribute("message", new MessageManager().getProperty("message.balanceRefilled", locale));
                String prev_page = (String) session.getAttribute("prev_page");
                if (prev_page.equals("/jsp/order.jsp")) {
                    session.removeAttribute("errorConfirmOrder");
                    page = prev_page;
                } else page = ConfigurationManager.getProperty("path.page.usermain");
                session.setAttribute(ATTR_NAME_PAGE, page);
                session.removeAttribute("errorRefill");
                return page;
            } else {
                session.setAttribute("errorRefill", new MessageManager().getProperty("message.errorPIN", locale));
            }
            page = ConfigurationManager.getProperty("path.page.refillcard");
            session.setAttribute(ATTR_NAME_PAGE, page);
        }catch (LogicException ex){
            LOG.error(ex);
            page=ConfigurationManager.getProperty("path.page.error");
            return page;
        }
        return page;
    }
}
