package com.rodrigotroy.commons.core.util.print;

import com.rodrigotroy.commons.core.util.Validator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Printer implements IMapUtil,
                                IListUtil {

    @Override
    public @NotNull String mapValuesToString(@Nullable Map<String, Object> map) {
        StringBuilder sb = new StringBuilder();

        if (map != null) {

            for (Object o : map.values()) {
                if (o instanceof String) {
                    sb.append("'");
                    sb.append(o);
                    sb.append("'");
                } else if (o instanceof Integer ||
                           o instanceof Double ||
                           o instanceof Short ||
                           o instanceof Long) {
                    sb.append(o);
                } else if (o instanceof ArrayList<?> list) {
                    sb.append(this.convertToString(new ArrayList<>(list)));
                }

                sb.append(",");
            }
        }

        return sb.toString();
    }

    @Override
    public @NotNull List<List<Object>> mapEntriesToList(@NotNull Map<String, Object> map) {
        return MapUtil.convertTOList(map);
    }

    public @NotNull String listToString(@NotNull List<Object> list,
                                        String separator,
                                        @NotNull String delimiter1,
                                        String delimiter2) {
        StringBuilder sb = new StringBuilder(delimiter1);

        if (Validator.isNotEmpty(list)) {
            for (int x = 0; x < list.size(); x++) {
                Object obj = list.get(x);

                if (obj instanceof ArrayList<?> arrayList) {
                    sb.append(this.listToString(new ArrayList<>(arrayList),
                                                separator,
                                                delimiter1,
                                                delimiter2));
                } else if (obj != null) {
                    sb.append(String.valueOf(obj).trim());
                }

                if (x < list.size() - 1) {
                    sb.append(separator);
                }
            }

            sb.append(delimiter2);
        }

        return sb.toString();
    }

    @Override
    public String convertToString(@NotNull List<Object> list) {
        return this.listToString(list,
                                 ",",
                                 "[",
                                 "]");
    }

    public @NotNull String listToString(Object[] param) {
        StringBuilder sb = new StringBuilder("[");

        if (param != null) {
            for (Object obj : param) {
                if (obj instanceof ArrayList<?> list) {
                    sb.append(this.convertToString(new ArrayList<>(list)));
                } else if (obj != null) {
                    sb.append(String.valueOf(obj).trim());
                }
            }
        }

        return sb.toString();
    }
}
