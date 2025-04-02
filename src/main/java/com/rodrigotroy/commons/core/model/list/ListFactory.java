package com.rodrigotroy.commons.core.model.list;

import com.rodrigotroy.commons.core.domain.selectable.Selectable;
import com.rodrigotroy.commons.core.model.DefaultListModel;
import com.rodrigotroy.commons.core.model.IListModel;
import com.rodrigotroy.commons.core.model.datatable.DefaultDomainObjectMapper;
import com.rodrigotroy.commons.core.model.datatable.IDomainObjectMapper;
import com.rodrigotroy.commons.core.model.datatable.IListHolder;
import com.rodrigotroy.commons.core.util.Validator;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * $ Project: java-commons
 * User: rodrigotroy
 * Date: 04-03-20
 * Time: 13:29
 */
public class ListFactory {
    public static <T extends IListHolder> @NotNull IList<T> createList(@NotNull IListModel listModel,
                                                                       @NotNull IDomainObjectMapper<T> domainObjectMapper) {
        IList<T> list = new com.rodrigotroy.commons.core.model.list.List<>();

        if (Validator.isNotEmpty(listModel.getSelectedRow())) {
            list.setSelectedRow(domainObjectMapper.createObject(listModel.getSelectedRow()));
        }

        for (List<Object> row : listModel.getRows()) {
            list.getRows().add(domainObjectMapper.createObject(row));
        }

        return list;
    }

    public static @NotNull IList<Selectable> createSelectableList() {
        return ListFactory.createList(new DefaultListModel(),
                                      new SelectableDomainObjectMapper());
    }

    public static @NotNull IList<IListHolder> createDefaultList() {
        return ListFactory.createList(new DefaultListModel(),
                                      new DefaultDomainObjectMapper());
    }
}
