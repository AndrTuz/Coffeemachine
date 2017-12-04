package com.tuz.coffemachine.commands;

import com.tuz.coffemachine.logic.OrderLogic;
import com.tuz.coffemachine.logic.exception.LogicException;
import com.tuz.coffemachine.models.Order;
import com.tuz.coffemachine.models.User;
import com.tuz.coffemachine.models.exception.IncorrectArgumentException;
import com.tuz.coffemachine.resources.ConfigurationManager;
import com.tuz.coffemachine.resources.MessageManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ConfirmOrderCommand implements ActionCommand {
    private static final Logger LOG= Logger.getLogger(ConfirmOrderCommand.class);
    private static final String USER = "user";
    private static final String ROOM = "room";
    private static final String ATTR_NAME_LANG = "lang";
    private static final String ATTR_NAME_PAGE = "current_page";

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        OrderLogic orderLogic = new OrderLogic();
        User currentUser = (User) session.getAttribute(USER);
        Order order = (Order) session.getAttribute("user_order");
        String roomS = request.getParameter(ROOM);
        String page = null;
        String locale=null;
        if(session.getAttribute(ATTR_NAME_LANG)==null){
            locale="ru";
            session.setAttribute(ATTR_NAME_LANG,"ru_RU");
        }else{
            locale = (String) session.getAttribute(ATTR_NAME_LANG);
            locale = locale.split("_")[0];
        }
        if (roomS==null || roomS.isEmpty()){
            session.setAttribute("errorConfirmOrder", new MessageManager().getProperty("message.wrongroom", locale));
            page = ConfigurationManager.getProperty("path.page.order");
            session.setAttribute(ATTR_NAME_PAGE, page);
            return page;
        }
        int room = Integer.parseInt(roomS);

        for (int i = 0; i < order.getDrinks().size(); i++) {
            int check = order.getDrinks().get(i).getAvailiable_sum() - order.getDrinks().get(i).getCountInOrder();
            if (check < 0){
                session.setAttribute("errorConfirmOrder", new MessageManager().getProperty("message.wrongavailiablecount", locale));
                page = ConfigurationManager.getProperty("path.page.order");
                session.setAttribute(ATTR_NAME_PAGE, page);
                return page;
            }
        }

        try {
            order.setDelivery_room(room);
            order.setUser(currentUser);
            if (currentUser.getTotal_sum() >= order.getTotal_sum()){
                if(!orderLogic.fillOrder(order)){
                    session.setAttribute("errorConfirmOrder", new MessageManager().getProperty("message.errorOrder", locale));
                    page = ConfigurationManager.getProperty("path.page.order");
                    session.setAttribute(ATTR_NAME_PAGE, page);
                } else {
                    int newSum = orderLogic.payForOrder(order);
                    order.getUser().setTotal_sum(newSum);
                    currentUser.setTotal_sum(newSum);
                    session.setAttribute("user", order.getUser());
                    session.setAttribute("message", new MessageManager().getProperty("message.successOrder", locale));
                    page = ConfigurationManager.getProperty("path.page.usermain");
                    session.setAttribute(ATTR_NAME_PAGE, page);
                }
            }else {
                session.setAttribute("errorConfirmOrder", new MessageManager().getProperty("message.errorSumOrder", locale));
                page = ConfigurationManager.getProperty("path.page.order");
                session.setAttribute(ATTR_NAME_PAGE, page);
            }
        }catch (LogicException | IncorrectArgumentException ex) {
            LOG.error(ex);
            page=ConfigurationManager.getProperty("path.page.error");
            return page;
        }
        return page;
    }
}
