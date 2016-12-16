package com.firebugsoft.common.tag.bootstrap;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

public class PageTag extends TagSupport {
    private static final long serialVersionUID = -2753167076529983230L;
    private int    pn = 1;
    private int    maxpn;
    private String formId;

    public PageTag() {}

    public PageTag(int pn, int maxpn, String formId) {
        this.pn = pn;
        this.maxpn = maxpn;
        this.formId = formId;
    }

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

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    @Override
    public int doStartTag() throws JspException {
        String hiddenId = formId + "-pn";
        StringBuilder html = new StringBuilder();
        html.append("<input type=\"hidden\" name=\"pn\" value=\"" + this.pn + "\" id=\""+hiddenId+"\" form=\""+formId+"\">");
        html.append("<ul class=\"pagination\">");
        boolean canPrev = this.pn > 1;
        if(canPrev) {
            html.append("<li><a href=\"javascript:void(0)\" onclick=\"pageSubmit(1)\"><span class=\"glyphicon glyphicon-step-backward\"></span></a></li>");
            html.append("<li><a href=\"javascript:void(0)\" onclick=\"pageSubmit(" + (this.pn - 1) + ")\"><span class=\"glyphicon glyphicon-arrow-left\"></span></a></li>");
        } else {
            html.append("<li class=\"disabled\"><span class=\"glyphicon glyphicon-step-backward\"></span></li>");
            html.append("<li class=\"disabled\"><span class=\"glyphicon glyphicon-arrow-left\"></span></li>");
        }
        html.append("<li class=\"active\"><a href=\"javascript:void(0)\">" + this.pn + "</a></li>");
        boolean canNext = this.pn < this.maxpn;
        if(canNext) {
            html.append("<li><a href=\"javascript:void(0)\" onclick=\"pageSubmit(" + (this.pn + 1) + ")\"><span class=\"glyphicon glyphicon-arrow-right\"></span></a></li>");
            html.append("<li><a href=\"javascript:void(0)\" onclick=\"pageSubmit(" + maxpn + ")\"><span class=\"glyphicon glyphicon-step-forward\"></span></a></li>");
        } else {
            html.append("<li class=\"disabled\"><span class=\"glyphicon glyphicon-arrow-right\"></span></li>");
            html.append("<li class=\"disabled\"><span class=\"glyphicon glyphicon-step-forward\"></span></li>");
        }
        html.append("</ul>");
        html.append("<script type=\"text/javascript\">");
        html.append("function pageSubmit(pn){");
        html.append("  document.getElementById('" + hiddenId + "').value=pn;");
        html.append("  document.getElementById('" + formId + "').submit();");
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
