package com.rodrigotroy.commons.core.model;

import java.util.List;

public interface IComboBoxModel {

    List<List<Object>> getOptions();

    void setOptions(List<List<Object>> options);

    Object getSelectedOption();

    void setSelectedOption(Object option);

    IComboBoxModel clone() throws CloneNotSupportedException;
}
