/*
/**
 * Project Name : MaxSoft Email Client For Gauge
 * Developer    : Osanda Deshan
 * Version      : 1.0.0
 * Date         : 6/23/2018
 * Time         : 2:56 PM
 * Description  :
 **/

package com.maxsoft.emailclient;


public class EmailSender {

    public static void main(String[] args) {
        Email.send(JsonReportReader.getExecutionResults());
    }


}
