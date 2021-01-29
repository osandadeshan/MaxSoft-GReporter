package com.maxsoft.greporter;

import java.io.File;

/**
 * Project Name    : MaxSoft-GReporter
 * Developer       : Osanda Deshan
 * Version         : 1.0.0
 * Date            : 1/28/2021
 * Time            : 11:54 PM
 * Description     :
 **/

public class Constants {

    public static final String CURRENT_DIRECTORY = System.getProperty("user.dir");
    public static final String FILE_SEPARATOR = File.separator;
    public static final String PIE_CHART_PROPERTY_FILE_PATH = CURRENT_DIRECTORY + FILE_SEPARATOR + "env"
            + FILE_SEPARATOR + "chart" + FILE_SEPARATOR + "piechart.properties";
    public static final String BAR_CHART_PROPERTY_FILE_PATH = CURRENT_DIRECTORY + FILE_SEPARATOR + "env"
            + FILE_SEPARATOR + "chart" + FILE_SEPARATOR + "barchart.properties";
    public static final String EMAIL_PROPERTY_FILE_PATH = CURRENT_DIRECTORY + FILE_SEPARATOR + "env"
            + FILE_SEPARATOR + "email" + FILE_SEPARATOR + "email.properties";
    public static final String JSON_FILE_PATH = System.getProperty("user.dir") + FILE_SEPARATOR + "reports"
            + FILE_SEPARATOR + "json-report" + FILE_SEPARATOR + "result.json";
    public static final String GREEN = "green";
    public static final String RED = "red";
    public static final String GRAY = "#43433D";
    public static final String BLACK = "black";
}
