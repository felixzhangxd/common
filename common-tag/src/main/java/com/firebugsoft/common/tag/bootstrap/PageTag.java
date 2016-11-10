package com.firebugsoft.common.tag.bootstrap;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

public class PageTag extends TagSupport {
    private int    pn;
    private int    maxpn;
    private String form;

    public int getPn() {
        return pn;
    }

    public void setPn(int pn) {
        this.pn = pn;
    }

    public int getMaxpn() {
        return maxpn;
    }

    public void setMaxpn(int maxpn) {
        this.maxpn = maxpn;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    @Override
    public int doStartTag() throws JspException {
        String prev = this.pn == 1 ? " class=\"disabled\"" : "";
        String next = this.pn == this.maxpn ? " class=\"disabled\"" : "";
        StringBuilder html = new StringBuilder();
        html.append("<input type=\"hidden\" name=\"pn\" value=\"" + this.pn + "\">");
        html.append("<ul class=\"pagination\">");
        html.append("<li" + prev + "><a href=\"javascript:void(0)\" onclick=\"pageSubmit(1)\"><span class=\"glyphicon glyphicon-step-backward\"></span></a></li>");
        html.append("<li" + prev + "><a href=\"javascript:void(0)\" onclick=\"pageSubmit(" + (this.pn - 1) + ")><span class=\"glyphicon glyphicon-arrow-left\"></span></a></li>");
        html.append("<li class=\"active\"><a href=\"javascript:void(0)\">" + this.pn + "</a></li>");
        html.append("<li" + next + "><a href=\"javascript:void(0)\" onclick=\"pageSubmit(" + (this.pn + 1) + ")><span class=\"glyphicon glyphicon-arrow-right\"></span></a></li>");
        html.append("<li" + next + "><a href=\"javascript:void(0)\" onclick=\"pageSubmit(" + maxpn + ")><span class=\"glyphicon glyphicon-step-forward\"></span></a></li>");
        html.append("</ul>");
        html.append("<script type=\"text/javascript\">");
        html.append("function pageSubmit(pn){");
        html.append("  alert(\"fuck\");");
        html.append("  document.getElementById('" + form + "').pn.value=pn;");
        html.append("  document.getElementById('" + form + "').submit();");
        html.append("}");
        html.append("</script>");
        try {
            this.pageContext.getOut().print(html.toString());
        } catch (IOException e) {
            throw new JspException(e);
        }
        return Tag.EVAL_BODY_INCLUDE;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}
