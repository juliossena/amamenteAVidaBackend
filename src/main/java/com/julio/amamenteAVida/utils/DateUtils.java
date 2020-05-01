package com.julio.amamenteAVida.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

public final class DateUtils {

    private static final DateTimeFormatter FORMAT_DATE_BR =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private DateUtils() {
        throw new IllegalStateException();
    }

    public static LocalDate convertStringBrazilianFormatToDate(final String date) {
        return LocalDate.parse(date, FORMAT_DATE_BR);
    }

    public static LocalDate convertStringUniversalFormatToDate(final String date) {
        return LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
    }

    public static String convertLocalDateForStringUniversalFormat(final LocalDate date) {
        Objects.requireNonNull(date, "Parâmetro data não pode ser nulo");
        return date.format(DateTimeFormatter.ISO_DATE);
    }

    public static String convertLocalDateForStringBrazilianFormat(final LocalDate date) {
        Objects.requireNonNull(date, "Parâmetro data não pode ser nulo");
        return date.format(FORMAT_DATE_BR);
    }

    public static String convertStringBrazilianFormatForStringUniversalFormat(final String date) {
        return convertLocalDateForStringUniversalFormat(convertStringBrazilianFormatToDate(date));
    }

    public static String convertStringUniversalFormatForStringBrazilianFormat(final String date) {
        return convertLocalDateForStringBrazilianFormat(convertStringUniversalFormatToDate(date));
    }

    public static boolean validUniversalFormatDate(final String date) {
        return !StringUtils.isBlank(date) && StringUtils.isNumeric(date.replace("-", ""))
                && validateFieldSize(date);
    }

    private static boolean validateFieldSize(final String date) {
        final String[] dateSplited = date.split("-");
        return dateSplited.length == 3 && dateSplited[0].length() == 4
                && dateSplited[1].length() == 2 && dateSplited[2].length() == 2;
    }

    public static boolean compareUniversalFormatDateWithParameterizedDate(
            final String universalDate, final String parameterDate) {
        final String[] arrayDataParametro = parameterDate.trim()
            .split("/");
        final String[] arrayDataUniversal = universalDate.trim()
            .split("-");

        return arrayDataParametro[1].equals(arrayDataUniversal[1])
                && arrayDataParametro[0].equals(arrayDataUniversal[2]);
    }

}
