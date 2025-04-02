package com.rodrigotroy.commons.core.model;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * The {@code IListModel} interface represents a model for a list of data.
 * It provides methods for accessing and modifying the data in the list.
 * <p>
 * Implementations of this interface typically manage a collection of rows,
 * each represented as a {@code List<Object>}.
 * <p>
 * <p>This interface defines the following methods:
 * <ul>
 *   <li>{@link #getRows()}: Returns a {@code List} of {@code List<Object>}
 *   representing the rows of the list.</li>
 *   <li>{@link #setRows(List)}: Sets the rows of the list to the specified
 *   {@code List} of {@code List<Object>}.</li>
 *   <li>{@link #getSelectedRow()}: Returns a {@code List<Object>} representing
 *   the selected row in the list.</li>
 *   <li>{@link #setSelectedRow(List)}: Sets the selected row in the list to
 *   the specified {@code List<Object>}.</li>
 * </ul>
 * <p>
 * Example usage:
 * <pre>
 * {@code
 * IListModel model = new DefaultListModel();
 * List<List<Object>> rows = model.getRows();
 * for (List<Object> row : rows) {
 *     // Process each row
 * }
 * model.setSelectedRow(rows.get(0));
 * }
 * </pre>
 */
public interface IListModel {

    /**
     * Sets the rows of the list to the specified {@code List<List<Object>>}.
     *
     * @param rows the list of rows to be set.
     */
    void setRows(List<List<Object>> rows);

    /**
     * Returns a {@code List<Object>} representing the selected row in the list.
     *
     * @return a {@code List<Object>} representing the selected row, or an empty
     * list if no row is selected.
     */
    default List<Object> getSelectedRow() {
        return Collections.emptyList();
    }

    /**
     * Sets the selected row in the list to the specified {@code List<Object>}.
     *
     * @param row the selected row to be set.
     */
    void setSelectedRow(List<Object> row);

    /**
     * Returns a {@code List} of {@code List<Object>} representing the rows of the list.
     * If the rows are {@code null}, it initializes them to an empty list.
     *
     * @return a {@code List<List<Object>>} representing the rows of the list.
     */
    default List<List<Object>> getRows() {
        return Collections.emptyList();
    }

    /**
     * Creates an {@code IListModel} instance populated with the specified rows.
     *
     * @param rows the list of rows to initialize the {@code IListModel} with. Each row
     *             should be represented as a {@code List<Object>}.
     * @return an {@code IListModel} instance initialized with the provided rows.
     */
    default IListModel from(List<List<Object>> rows) {
        return new DefaultListModel(rows);
    }

    /**
     * Retrieves the value from the specified row and column in the list of rows if it exists.
     *
     * @param rowIndex    the index of the row to retrieve the value from. Must be greater
     *                    than or equal to 0 and less than the total number of rows.
     * @param columnIndex the index of the column to retrieve the value from within the specified row.
     *                    Must be greater than or equal to 0 and less than the number of columns in the row.
     * @return an {@code Optional<Object>} containing the value at the specified row and column,
     * or an empty {@code Optional} if the indices are out of bounds or if the value is null.
     */
    default Optional<Object> getValueAtRow(int rowIndex,
                                           int columnIndex) {
        return Optional.ofNullable(getRows())
                       .filter(rows -> rowIndex >= 0 && rowIndex < rows.size())
                       .map(rows -> rows.get(rowIndex))
                       .filter(row -> columnIndex >= 0 && columnIndex < row.size())
                       .map(row -> row.get(columnIndex));
    }
}
