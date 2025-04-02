package com.rodrigotroy.commons.core.model.value;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created with IntelliJ IDEA.
 * $ Project: java-commons
 * User: rodrigotroy
 * Date: 17-07-2024
 * Time: 13:59
 */
public class PeriodRange {
    private static final int MONTHS_IN_YEAR = 12;
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private final @NotNull String strFechaDesde;
    private final @NotNull String strFechaHasta;

    public PeriodRange(Integer codigoPeriodo) {
        int anoPeriodo = codigoPeriodo / MONTHS_IN_YEAR;
        int mesPeriodo = codigoPeriodo % MONTHS_IN_YEAR;

        if (mesPeriodo == 0) {
            anoPeriodo = anoPeriodo - 1;
            mesPeriodo = MONTHS_IN_YEAR;
        }

        Calendar fechaHasta;
        Calendar fechaActual = Calendar.getInstance();
        Calendar fechaDesde = new GregorianCalendar(anoPeriodo,
                                                    mesPeriodo - 1,
                                                    1);

        // if it's current period then set the end date to yesterday
        if ((mesPeriodo - 1) == fechaActual.get(Calendar.MONTH)) {
            fechaHasta = Calendar.getInstance();
            fechaHasta.add(Calendar.DATE,
                           -1); // previous day
        } else {
            fechaHasta = new GregorianCalendar(anoPeriodo,
                                               mesPeriodo - 1,
                                               1);
            fechaHasta.add(Calendar.MONTH,
                           1); // first day of next month
            fechaHasta.add(Calendar.DATE,
                           -1); // last day of the month
        }

        SimpleDateFormat formatoFecha = new SimpleDateFormat(DATE_FORMAT);
        this.strFechaDesde = formatoFecha.format(fechaDesde.getTime());
        this.strFechaHasta = formatoFecha.format(fechaHasta.getTime());
    }

    public @NotNull String getStrFechaDesde() {
        return strFechaDesde;
    }

    public @NotNull String getStrFechaHasta() {
        return strFechaHasta;
    }
}
