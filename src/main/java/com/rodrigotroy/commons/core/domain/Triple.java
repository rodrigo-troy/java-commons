package com.rodrigotroy.commons.core.domain;

import org.jetbrains.annotations.NotNull;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * A class representing a triple of values.
 *
 * <p>This class is immutable and thread-safe if its component values are immutable.</p>
 *
 * @param <L> the type of the left value
 * @param <M> the type of the middle value
 * @param <R> the type of the right value
 */
public record Triple<L, M, R>(L left, M middle, R right) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Creates a Triple instance holding the given values.
     *
     * @param left   The left value, can be of any type L.
     * @param middle The middle value, can be of any type M.
     * @param right  The right value, can be of any type R.
     * @param <L>    The type of the left value.
     * @param <M>    The type of the middle value.
     * @param <R>    The type of the right value.
     * @return A new Triple instance containing the provided values.
     */
    public static <L, M, R> @NotNull Triple<L, M, R> of(L left,
                                                        M middle,
                                                        R right) {
        return new Triple<>(left,
                            middle,
                            right);
    }

    /**
     * Creates a Triple instance with all values set to null.
     *
     * @param <L> The type of the left value.
     * @param <M> The type of the middle value.
     * @param <R> The type of the right value.
     * @return A new Triple instance containing null for all its values.
     */
    public static <L, M, R> Triple<L, M, R> ofNull() {
        return new Triple<>(null,
                            null,
                            null);
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj instanceof Triple<?, ?, ?> other) {
            return Objects.equals(left(),
                                  other.left())
                   && Objects.equals(middle(),
                                     other.middle())
                   && Objects.equals(right(),
                                     other.right());
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(left()) ^ Objects.hashCode(middle()) ^ Objects.hashCode(right());
    }

    @Override
    public @NotNull String toString() {
        return "Triple{"
               + "left=" + left()
               + ", middle=" + middle()
               + ", right=" + right()
               + '}';
    }
}
