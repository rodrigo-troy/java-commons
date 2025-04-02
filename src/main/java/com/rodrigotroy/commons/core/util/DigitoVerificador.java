package com.rodrigotroy.commons.core.util;

import org.jetbrains.annotations.Nullable;

/**
 * The `DigitoVerificador` class provides utility methods to calculate the verification digit for a given RUT (Rol Único Tributario) number.
 * This class cannot be instantiated as it contains only static methods.
 */
public final class DigitoVerificador {
    private DigitoVerificador() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Calculates the verification digit for a given RUT (Rol Único Tributario) number.
     *
     * @param rut the RUT number to calculate the verification digit for
     * @return the verification digit as a string (can be "0", "K", or a single digit from 1 to 9)
     */
    public static String calcular(@Nullable Integer rut) {
        if (rut == null) {
            return null;
        }

        if (rut < 0) {
            return null;
        }

        int suma = 0, factor = 2;

        for (int aux = rut; aux != 0; aux /= 10) {
            suma += (aux % 10) * factor;
            factor = (factor == 7) ? 2 : factor + 1;
        }

        int dv = 11 - suma % 11;
        if ((dv == 11)) {
            return "0";
        }
        return (dv == 10) ? "K" : String.valueOf(dv);
    }
}
