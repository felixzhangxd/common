package com.firebugsoft.common.bean.core;

/**
 * 分页工具
 * @author felix
 */
public class Page {
    /** page number */
    private int pn  = 1;
    /** page size */
    private int ps  = 20;
    /** recond count */
    private int rct = 0;

    public Page() {}

    public Page(int pn, int ps) {
        this.setPn(pn);
        this.setPs(ps);
    }

    public int getPn() {
        return pn;
    }

    public void setPn(int pn) {
        if (pn < 1) {
            this.pn = 1;
            return;
        }
        this.pn = pn;
    }

    public int getPs() {
        return ps;
    }

    public void setPs(int ps) {
        if (ps < 1) {
            this.ps = 1;
            return;
        }
        if (ps > 50) {
            this.ps = 50;
            return;
        }
        this.ps = ps;
    }

    public int getRct() {
        return rct;
    }

    public void setRct(int rct) {
        this.rct = rct;
    }

    /** 返回总页数 */
    public int getPct() {
        int pct = rct / ps;
        if (pct == 0 || rct % ps != 0) {
            pct++;
        }
        return pct;
    }

    /** mysql limit */
    public int limit() {
        return ps;
    }

    /** mysql offset */
    public int offset() {
        return (pn - 1) * ps;
    }

    /** oracle start rowno */
    public int startRowno() {
        return (pn - 1) * ps + 1;
    }

    /** oracle end rowno */
    public int endRowno() {
        return pn * ps;
    }

    /** solr start */
    public int start() {
        return (pn - 1) * ps + 1;
    }

    /** solr rows */
    public int rows() {
        return ps;
    }

    @Override
    public String toString() {
        return String.format("{pn:%s, ps:%s, rct:%s}", pn, ps, rct);
    }
}
