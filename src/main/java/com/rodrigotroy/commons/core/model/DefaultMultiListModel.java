package com.rodrigotroy.commons.core.model;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * $ Project: java-commons
 * User: rodrigotroy
 * Date: 2019-02-15
 * Time: 16:37
 */
public class DefaultMultiListModel implements IMultiListModel {
    private final @NotNull List<List<List<Object>>> datasets;

    public DefaultMultiListModel() {
        this.datasets = new ArrayList<>();
    }

    @Override
    public List<List<Object>> getDataset(Integer index) {
        if (index < datasets.size()) {
            return this.datasets.get(index);
        }

        return new ArrayList<>();
    }

    @Override
    public void addDataset(List<List<Object>> dataset) {
        this.datasets.add(dataset);
    }

    @Override
    public int size() {
        return this.datasets.size();
    }
}
