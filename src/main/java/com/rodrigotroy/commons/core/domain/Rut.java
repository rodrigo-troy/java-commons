package com.rodrigotroy.commons.core.domain;


import com.rodrigotroy.commons.core.util.DigitoVerificador;
import com.rodrigotroy.commons.core.util.Validator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * The `Rut` class represents a chilean RUT (Rol Único Tributario) and provides methods to manipulate and validate it.
 */
public class Rut {
    private String rutSinDV;
    private String rutConDV;
    private String digitoVerificador;

    /**
     * Creates a new instance of the `Rut` class with the specified RUT (Rol Único Tributario).
     * <p>
     * The RUT is validated before creating the instance.
     * If the RUT is valid, the instance is created with the RUT and its verification digit.
     * If the RUT is not valid, the instance is created with empty values.
     * The RUT is stored without any dots, dashes, or spaces.
     * The verification digit is stored as an uppercase letter.
     * </p>
     *
     * @param rutConDV The RUT with the verification digit.
     */
    private Rut(String rutConDV) {
        if (Validator.validateRUT(rutConDV)) {
            this.rutConDV = rutConDV;
            this.rutSinDV = rutConDV.substring(0,
                                               rutConDV.indexOf("-")).replace(".",
                                                                                 "");
            this.digitoVerificador = rutConDV.substring(rutConDV.indexOf("-") + 1,
                                                        rutConDV.indexOf("-") + 2).toUpperCase();
        }
    }

    /**
     * Creates a new instance of {@code Rut} class with the specified RUT (Rol Único Tributario).
     *
     * @param rutConDV The RUT with the verification digit.
     * @return A new instance of {@code Rut} class.
     * @see #Rut(String)
     */
    public static @NotNull Rut createRut(String rutConDV) {
        return new Rut(rutConDV);
    }

    /**
     * Retrieves the RUT (Rol Único Tributario) without the verification digit as an integer.
     * <p>
     * If the RUT is valid, the RUT without the verification digit is parsed into an integer and returned.
     * If the RUT is not valid, null is returned.
     * </p>
     *
     * @return The RUT without the verification digit as an integer, or null if the RUT is not valid.
     * @see Rut#esValido()
     * @see Rut#getRutSinDV()
     */
    public @Nullable Integer getRutSinDVAsInteger() {
        if (this.esValido()) {
            return Integer.parseInt(this.getRutSinDV());
        }

        return null;
    }

    /**
     * Calculates the verification digit for the Chilean RUT (Rol Único Tributario).
     *
     * @return The calculated verification digit as a string.
     */
    public String getDigitoVerificadorCalculado() {
        return DigitoVerificador.calcular(Integer.parseInt(this.getRutSinDV()));
    }

    /**
     * Checks if the given Chilean RUT (Rol Único Tributario) is valid.
     *
     * @return {@code true} if the RUT is valid, {@code false} otherwise.
     */
    public @NotNull Boolean esValido() {
        if (Validator.validateRUT(this.rutConDV)) {
            Boolean rutSinDVEsNumerico = Validator.isNumber(rutSinDV);
            Boolean dvEsValido = DigitoVerificador.calcular(Integer.parseInt(rutSinDV)).equals(digitoVerificador);
            return rutSinDVEsNumerico && dvEsValido;
        } else {
            return false;
        }
    }

    /**
     * Returns the RUT (Rol Único Tributario) without the verification digit.
     * <p>
     * The RUT is returned as a string.
     * If the RUT is null, an empty string is returned.
     * If the RUT is not null, the RUT is returned without the verification digit.
     * The RUT is returned without any dots.
     * The RUT is returned without any dashes.
     * The RUT is returned without any spaces.
     * </p>
     *
     * @return The RUT without the verification digit as a string.
     */
    public @NotNull String getRutSinDV() {
        if (rutSinDV == null) {
            rutSinDV = "";
        }

        return rutSinDV;
    }

    public @NotNull String getRutConDV() {
        if (rutConDV == null) {
            rutConDV = "";
        }

        return rutConDV;
    }

    public @NotNull String getDigitoVerificador() {
        if (digitoVerificador == null) {
            digitoVerificador = "";
        }

        return digitoVerificador;
    }
}
