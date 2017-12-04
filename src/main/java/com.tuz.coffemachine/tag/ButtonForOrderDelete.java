package com.tuz.coffemachine.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;

public class ButtonForOrderDelete extends BodyTagSupport{
    private int id_drink;

    public void setId_drink(Integer id_drink) {
        this.id_drink = id_drink;
    }

    @Override
    public int doStartTag() throws JspException {

        try{
            JspWriter out = pageContext.getOut();
            out.write("<form name=\"DeleteDrink\" action=\"controller\">");
            out.write("<input type=\"hidden\" name=\"command\" value=\"deletedrink\"/>");
            out.write("<input type=\"hidden\" name=\"delete_drink\" value=\""+id_drink+"\"/>");
            out.write("<input type=\"submit\" class=\"btn btn-danger btn-xs\" value=\"X\">");
            out.write("</form>");
        } catch (IOException ex) {
            throw new JspTagException(ex);
        }
        return SKIP_BODY;
    }
}
