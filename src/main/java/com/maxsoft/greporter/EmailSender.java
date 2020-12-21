package com.maxsoft.greporter;

/*
 * Project Name : MaxSoft GReporter
 * Developer    : Osanda Deshan
 * Version      : 1.0.0
 * Date         : 6/23/2018
 * Time         : 2:56 PM
 * Description  :
 */

public class EmailSender {

    public static void main(String[] args) {
        Email.send(JsonReportReader.getExecutionResults());
    }
}
