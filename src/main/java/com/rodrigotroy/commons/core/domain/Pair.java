package com.rodrigotroy.commons.core.domain;

import java.io.Serial;
import java.io.Serializable;

/**
 * Represents a pair of values.
 */
public record Pair<L, R>(L left, R right) implements Serializable {
    @Serial
    private static final long serialVersionUID = 2L;

    public static <L, R> Pair<L, R> of(L left,
                                       R right) {
        return new Pair<>(left,
                          right);
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        final Pair<?, ?> pair = (Pair<?, ?>) obj;

        return left.equals(pair.left) && right.equals(pair.right);
    }

    @Override
    public String toString() {
        return "Pair{" +
               "left=" + left +
               ", right=" + right +
               '}';
    }
}
