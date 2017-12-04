package com.tuz.coffemachine.commands;

import com.tuz.coffemachine.commands.client.CommandEnum;
import com.tuz.coffemachine.models.Drink;
import com.tuz.coffemachine.models.Order;
import com.tuz.coffemachine.models.User;
import com.tuz.coffemachine.models.exception.IncorrectArgumentException;
import com.tuz.coffemachine.resources.ConfigurationManager;
import com.tuz.coffemachine.resources.MessageManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class DeleteDrinkCommand implements ActionCommand {
    private static final Logger LOG= Logger.getLogger(DeleteDrinkCommand.class);
    private static final String ATTR_NAME_PAGE = "current_page";
    private static final String ATTR_NAME_LANG = "lang";

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        int id_drink_for_delete = Integer.parseInt(request.getParameter("delete_drink"));
        Order order = (Order)session.getAttribute("user_order");
        ArrayList<Drink> drinks = order.getDrinks();
        String page = ConfigurationManager.getProperty("path.page.order");
        String locale=null;
        if(session.getAttribute(ATTR_NAME_LANG)==null){
            locale="ru";
            session.setAttribute(ATTR_NAME_LANG,"ru_RU");
        }else{
            locale = (String) session.getAttribute(ATTR_NAME_LANG);
            locale = locale.split("_")[0];
        }

        System.out.println(drinks.size());
        try {
            for (int i = 0; i < drinks.size(); i++) {
                if (drinks.get(i).getId_drink() == id_drink_for_delete){
                    if (drinks.get(i).getCountInOrder()>1){
                        drinks.get(i).setCountInOrder(drinks.get(i).getCountInOrder() - 1);
                        order.setTotal_sum(order.getTotal_sum() - drinks.get(i).getCost());
                    } else {
                        System.out.println("drinks size" + drinks.size());
                        order.setTotal_sum(order.getTotal_sum() - drinks.get(i).getCost());
                        drinks.remove(i);
                        if (i > 0) i--;
                    }
                }
            }
        }catch (IncorrectArgumentException ex){
            LOG.error(ex);
            page=ConfigurationManager.getProperty("path.page.error");
            return page;
        }

        if (drinks==null || drinks.isEmpty()){
            User user = (User) session.getAttribute("user");
            ArrayList<Drink> ddrinks = (ArrayList<Drink>) session.getAttribute("drinks_list");
            String langAtr = (String) session.getAttribute(ATTR_NAME_LANG);
            request.getSession().invalidate();
            HttpSession session1 = request.getSession(true);
            session1.setAttribute("lang", langAtr);
            session1.setAttribute("user", user);
            session1.setAttribute("drinks_list", ddrinks);
            session1.setAttribute("message", new MessageManager().getProperty("message.wrongchoose", locale));
            page = ConfigurationManager.getProperty("path.page.usermain");
            session1.setAttribute("current_page", page);
            return page;
        }
        session.setAttribute(ATTR_NAME_PAGE, page);
        return page;
    }
}
