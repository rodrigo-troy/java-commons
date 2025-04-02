package com.rodrigotroy.commons.core.model.list;

import com.rodrigotroy.commons.core.domain.selectable.DefaultSelectable;
import com.rodrigotroy.commons.core.domain.selectable.Selectable;
import com.rodrigotroy.commons.core.model.datatable.IDomainObjectMapper;
import com.rodrigotroy.commons.core.util.SecureValue;
import com.rodrigotroy.commons.core.util.Validator;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * $ Project: java-commons
 * User: rodrigotroy
 * Date: 8/21/18
 * Time: 22:12
 */
public class SelectableDomainObjectMapper implements IDomainObjectMapper<Selectable> {
    private final Integer claveIndex;
    private final Integer labelIndex;

    public SelectableDomainObjectMapper() {
        this.claveIndex = 0;
        this.labelIndex = 1;
    }

    public SelectableDomainObjectMapper(Integer claveIndex,
                                        Integer labelIndex) {
        this.claveIndex = claveIndex;
        this.labelIndex = labelIndex;
    }

    @Override
    public @NotNull Selectable createObject(List<Object> list) {
        DefaultSelectable opcionCombo = new DefaultSelectable();

        if (Validator.isNotEmpty(list)) {
            opcionCombo.setClave(SecureValue.cellRowToString(list,
                                                             claveIndex));
            opcionCombo.setLabel(SecureValue.cellRowToString(list,
                                                             labelIndex));
            opcionCombo.setList(list);
        }

        return opcionCombo;
    }
}
