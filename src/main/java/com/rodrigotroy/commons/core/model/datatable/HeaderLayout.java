package com.rodrigotroy.commons.core.model.datatable;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * $ Project: java-commons
 * User: rodrigotroy
 * Date: 9/10/18
 * Time: 18:52
 */
public class HeaderLayout implements Serializable {
    private Integer colspan;
    private Integer rowspan;
    private String headerText;

    public HeaderLayout() {
    }

    public HeaderLayout(Integer colspan,
                        Integer rowspan,
                        String headerText) {
        this.colspan = colspan;
        this.rowspan = rowspan;
        this.headerText = headerText;
    }

    public Integer getColspan() {
        return colspan;
    }

    public void setColspan(Integer colspan) {
        this.colspan = colspan;
    }

    public Integer getRowspan() {
        return rowspan;
    }

    public void setRowspan(Integer rowspan) {
        this.rowspan = rowspan;
    }

    public String getHeaderText() {
        return headerText;
    }

    public void setHeaderText(String headerText) {
        this.headerText = headerText;
    }
}
