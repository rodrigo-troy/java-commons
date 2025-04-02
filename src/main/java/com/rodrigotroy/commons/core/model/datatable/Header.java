package com.rodrigotroy.commons.core.model.datatable;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * $ Project: java-commons
 * User: rodrigotroy
 * Date: 27-07-16
 * Time: 11:24
 */
public class Header implements Serializable {
    private DataType dataType;

    private Boolean isVisible;
    private Boolean isDetailVisible;
    private Boolean showZeros;

    private String headerText;
    private Integer decimalPlaces;
    private Double proportion;

    private ColumnAlignment columnAlignment;

    private transient @Nullable List<Object> list;

    public Header(String headerText,
                  DataType dataType,
                  Integer decimalPlaces,
                  Boolean showZeros,
                  Boolean isVisible,
                  Boolean isDetailVisible,
                  Double proportion,
                  ColumnAlignment columnAlignment) {
        this.dataType = dataType;
        this.isVisible = isVisible;
        this.isDetailVisible = isDetailVisible;
        this.showZeros = showZeros;
        this.headerText = headerText;
        this.decimalPlaces = decimalPlaces;
        this.proportion = proportion;
        this.columnAlignment = columnAlignment;
        this.list = null;
    }

    public Header(String headerText,
                  DataType dataType,
                  Integer decimalPlaces,
                  Boolean showZeros,
                  Boolean isVisible,
                  Boolean isDetailVisible,
                  Double proportion,
                  ColumnAlignment columnAlignment,
                  List<Object> list) {
        this(headerText,
             dataType,
             decimalPlaces,
             showZeros,
             isVisible,
             isDetailVisible,
             proportion,
             columnAlignment);
        this.list = list;
    }

    public Boolean isDetailVisible() {
        return isDetailVisible;
    }

    public void setDetailVisible(Boolean detailVisible) {
        isDetailVisible = detailVisible;
    }

    public @NotNull List<Object> getList() {
        if (list == null) {
            list = new ArrayList<>();
        }

        return list;
    }

    public void setList(List<Object> list) {
        this.list = list;
    }

    public @NotNull ColumnAlignment getColumnAlignment() {
        if (columnAlignment == null) {
            columnAlignment = ColumnAlignment.LEFT;
        }

        return columnAlignment;
    }

    public void setColumnAlignment(ColumnAlignment columnAlignment) {
        this.columnAlignment = columnAlignment;
    }

    public Double getProportion() {
        return proportion;
    }

    public void setProportion(Double proportion) {
        this.proportion = proportion;
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public Boolean isVisible() {
        return isVisible;
    }

    public void setVisible(Boolean visible) {
        isVisible = visible;
    }

    public Boolean getShowZeros() {
        return showZeros;
    }

    public void setShowZeros(Boolean showZeros) {
        this.showZeros = showZeros;
    }

    public String getHeaderText() {
        return headerText;
    }

    public void setHeaderText(String headerText) {
        this.headerText = headerText;
    }

    public Integer getDecimalPlaces() {
        return decimalPlaces;
    }

    public void setDecimalPlaces(Integer decimalPlaces) {
        this.decimalPlaces = decimalPlaces;
    }

    @Override
    public String toString() {
        return "Header{" +
               "dataType=" + dataType +
               ", isVisible=" + isVisible +
               ", isDetailVisible=" + isDetailVisible +
               ", showZeros=" + showZeros +
               ", headerText='" + headerText + '\'' +
               ", decimalPlaces=" + decimalPlaces +
               ", proportion=" + proportion +
               ", columnAlignment=" + columnAlignment +
               '}';
    }
}
