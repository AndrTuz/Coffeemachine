package com.tuz.coffemachine.tag;

import com.tuz.coffemachine.models.Drink;
import com.tuz.coffemachine.models.Order;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;
import java.util.ArrayList;

public class AdminTableTag extends BodyTagSupport {
    private ArrayList<Drink> drinks;

    @Override
    public int doStartTag() throws JspTagException {
        HttpSession session = pageContext.getSession();
        try{
            drinks = (ArrayList<Drink>) session.getAttribute("drinks_list");
            JspWriter out = pageContext.getOut();
            out.write("<table border='1' width='80%'>");
            out.write("<tbody>");
            out.write("<tr><th align='center' >Id_drink</th><th align='center'>Name</th><th align='center'>Cost</th><th align='center'>Availiable_sum</th><th align='center'>Capacity</th></tr>");
            for(int i = 0; i < drinks.size(); i++){
                out.write("<tr><td align='center'>"+drinks.get(i).getId_drink()+"</td><td align='center'>"+drinks.get(i).getName()+"</td><td align='center'>"+drinks.get(i).getCost()+"</td><td align='center'>"+
                        drinks.get(i).getAvailiable_sum()+"</td><td align='center'>"+drinks.get(i).getCapacity()+"</td></tr>");
            }
        } catch (IOException ex) {
            throw new JspTagException(ex);
        }
        return SKIP_BODY;
    }
    @Override
    public int doAfterBody() throws JspTagException {
        try {
            pageContext.getOut().write("</tbody>");
        } catch (IOException ex) {
            throw new JspTagException(ex);
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspTagException{
        try {
            pageContext.getOut().write("</table>");
        } catch (IOException ex) {
            throw new JspTagException(ex);
        }
        return EVAL_PAGE;
    }
}
