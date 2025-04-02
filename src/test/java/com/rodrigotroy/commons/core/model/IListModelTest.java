package com.rodrigotroy.commons.core.model;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

public class IListModelTest {

    /**
     * Tests for the IListModel interface,
     * focusing on the getValueAtRow() method.
     */
    @Test
    public void testGetValueAtRow_ValidIndices() {
        IListModel model = new DefaultListModel(Arrays.asList(
                Arrays.asList("A1",
                              "B1",
                              "C1"),
                Arrays.asList("A2",
                              "B2",
                              "C2"),
                Arrays.asList("A3",
                              "B3",
                              "C3")
        ));
        Optional<Object> value = model.getValueAtRow(1,
                                                     1);
        Assert.assertTrue(value.isPresent());
        Assert.assertEquals(value.get(),
                            "B2");
    }

    @Test
    public void testGetValueAtRow_InvalidRowIndex() {
        IListModel model = new DefaultListModel(Arrays.asList(
                Arrays.asList("A1",
                              "B1",
                              "C1"),
                Arrays.asList("A2",
                              "B2",
                              "C2"),
                Arrays.asList("A3",
                              "B3",
                              "C3")
        ));
        Optional<Object> value = model.getValueAtRow(5,
                                                     1);
        Assert.assertFalse(value.isPresent());
    }

    @Test
    public void testGetValueAtRow_InvalidColumnIndex() {
        IListModel model = new DefaultListModel(Arrays.asList(
                Arrays.asList("A1",
                              "B1",
                              "C1"),
                Arrays.asList("A2",
                              "B2",
                              "C2"),
                Arrays.asList("A3",
                              "B3",
                              "C3")
        ));
        Optional<Object> value = model.getValueAtRow(1,
                                                     5);
        Assert.assertFalse(value.isPresent());
    }

    @Test
    public void testGetValueAtRow_NegativeIndices() {
        IListModel model = new DefaultListModel(Arrays.asList(
                Arrays.asList("A1",
                              "B1",
                              "C1"),
                Arrays.asList("A2",
                              "B2",
                              "C2"),
                Arrays.asList("A3",
                              "B3",
                              "C3")
        ));
        Optional<Object> value = model.getValueAtRow(-1,
                                                     -1);
        Assert.assertFalse(value.isPresent());
    }

    @Test
    public void testGetValueAtRow_EmptyRows() {
        IListModel model = new DefaultListModel(Collections.emptyList());
        Optional<Object> value = model.getValueAtRow(0,
                                                     0);
        Assert.assertFalse(value.isPresent());
    }

    @Test
    public void testGetValueAtRow_NullRows() {
        IListModel model = new DefaultListModel(null);
        Optional<Object> value = model.getValueAtRow(0,
                                                     0);
        Assert.assertFalse(value.isPresent());
    }
}
