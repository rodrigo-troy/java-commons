package com.rodrigotroy.commons.core.model;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DefaultComboBoxModel implements IComboBoxModel,
                                             Serializable {
    private transient Object selectedOption;
    private transient List<List<Object>> options;

    @Override
    public @NotNull List<List<Object>> getOptions() {
        if (this.options == null) {
            this.options = new ArrayList<>();
        }

        return this.options;
    }

    @Override
    public void setOptions(List<List<Object>> options) {
        this.options = options;
    }

    @Override
    public Object getSelectedOption() {
        return this.selectedOption;
    }

    @Override
    public void setSelectedOption(Object option) {
        this.selectedOption = option;
    }

    @Override
    public @NotNull IComboBoxModel clone() throws CloneNotSupportedException {
        IComboBoxModel clone = (IComboBoxModel) super.clone();
        clone.setOptions(new ArrayList<>(this.getOptions()));
        clone.setSelectedOption(this.getSelectedOption());

        return clone;
    }
}
