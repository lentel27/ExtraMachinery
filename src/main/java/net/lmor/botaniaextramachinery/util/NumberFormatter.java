package net.lmor.botaniaextramachinery.util;

import net.lmor.botaniaextramachinery.config.LibXClientConfig;

import java.text.NumberFormat;
import java.util.Locale;

public class NumberFormatter {
    public static String formatInteger(int number) {
        if (!LibXClientConfig.formattedNumberSuffix){
            NumberFormat formatter = NumberFormat.getInstance(Locale.US);
            return formatter.format(number).replace(",", ".");
        }
        else {
            return formatIntegerWithSuffix(number).replace(",", ".");
        }
    }

    private static String formatIntegerWithSuffix(int number) {
        if (number >= 1_000_000_000) {
            return String.format("%.2fB", number / 1_000_000_000.0);
        } else if (number >= 1_000_000) {
            return String.format("%.1fM", number / 1_000_000.0);
        } else if (number >= 1_000) {
            return String.format("%.1fK", number / 1_000.0);
        } else {
            return String.valueOf(number);
        }
    }

    public static String formatDouble(double number) {
        if (!LibXClientConfig.formattedNumberSuffix){
            NumberFormat formatter = NumberFormat.getInstance(Locale.US);
            return formatter.format(number).replace(",", ".");
        }
        else {
            return formatDoubleWithSuffix(number).replace(",", ".");
        }
    }

    private static String formatDoubleWithSuffix(double number) {
        if (number >= 1_000_000_000_000_000_000L) {
            return String.format("%.3fQ", number / 1_000_000_000_000_000_000.0);
        } else if (number >= 1_000_000_000_000L) {
            return String.format("%.3fT", number / 1_000_000_000_000.0);
        } else if (number >= 1_000_000_000) {
            return String.format("%.2fB", number / 1_000_000_000.0);
        } else if (number >= 1_000_000) {
            return String.format("%.1fM", number / 1_000_000.0);
        } else if (number >= 1_000) {
            return String.format("%.1fK", number / 1_000.0);
        } else {
            return String.format("%.2f", number);
        }
    }

    public static String formatIntegerWater(int number) {
        if (!LibXClientConfig.formattedNumberSuffix){
            NumberFormat formatter = NumberFormat.getInstance(Locale.US);
            return formatter.format(number).replace(",", ".") + "mB";
        }
        else {
            return formatIntegerWithWaterSuffix(number).replace(",", ".");
        }
    }

    private static String formatIntegerWithWaterSuffix(int number) {
        if (number >= 1_000_000_000) {
            return String.format("%dMB", number / 1_000_000_000);
        } else if (number >= 1_000_000) {
            return String.format("%dkB", number / 1_000_000);
        } else if (number >= 1_000) {
            return String.format("%dB", number / 1000);
        } else {
            return String.valueOf(number) + "mB";
        }
    }
}
