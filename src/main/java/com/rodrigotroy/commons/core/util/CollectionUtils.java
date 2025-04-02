package com.rodrigotroy.commons.core.util;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * $ Project: java-commons
 * User: rodrigotroy
 * Date: 2025-04-02
 * Time: 14:06
 */
public class CollectionUtils {
    private CollectionUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static <T> @NotNull List<List<T>> chopIntoParts(List<T> ls,
                                                           int iParts) {
        List<List<T>> lsParts = new ArrayList<>();
        int iChunkSize = ls.size() / iParts;
        int iLeftOver = ls.size() % iParts;
        int iTake;

        for (int i = 0, iT = ls.size(); i < iT; i += iTake) {
            if (iLeftOver > 0) {
                iLeftOver--;

                iTake = iChunkSize + 1;
            } else {
                iTake = iChunkSize;
            }

            lsParts.add(new ArrayList<>(ls.subList(i,
                                                   Math.min(iT,
                                                            i + iTake))));
        }

        return lsParts;
    }
}
