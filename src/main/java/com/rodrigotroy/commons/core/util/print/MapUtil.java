package com.rodrigotroy.commons.core.util.print;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class MapUtil {
    private MapUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static String convertTOstring(@Nullable Map<String, Object> map) {
        if (map != null) {
            StringBuilder sb = new StringBuilder(map.size() * 2);
            sb.append("(");


            NumberFormat decimalFormat = new DecimalFormat();
            decimalFormat.setGroupingUsed(false);
            decimalFormat.setMaximumFractionDigits(10);

            for (Object o : map.values()) {
                if (o == null) {
                    sb.append("null");
                } else if (o instanceof String) {
                    sb.append("'");
                    sb.append(o);
                    sb.append("'");
                } else if (o instanceof Double) {
                    sb.append(decimalFormat.format(o));
                } else {
                    sb.append(o);
                }

                sb.append(",");
            }

            sb.delete(sb.length() - 1,
                      sb.length());
            sb.append(")");

            return sb.toString();
        }

        return null;
    }

    public static @NotNull List<List<Object>> convertTOList(@NotNull Map<String, Object> map) {
        List<List<Object>> list = new ArrayList<>();

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            List<Object> row = new ArrayList<>();
            row.add(entry.getKey());
            row.add(entry.getValue());
            list.add(row);
        }

        return list;
    }

    public static <K, V> @NotNull String toString(@NotNull Map<K, V> map) {
        StringBuilder sb = new StringBuilder();
        Iterator<Map.Entry<K, V>> iterator = map.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<K, V> entry = iterator.next();
            sb.append(entry.getKey());
            sb.append('=').append('"');
            sb.append(entry.getValue());
            sb.append('"');

            if (iterator.hasNext()) {
                sb.append(',').append(' ');
            }
        }

        return sb.toString();
    }
}
