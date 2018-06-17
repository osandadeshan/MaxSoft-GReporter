/*
 Developer      : Osanda Deshan
 Created Date   : 6/17/2018
 Project        : MaxSoft Email Client For Gauge
 */

package com.maxsoft.emailclient;

import com.maxsoft.emailclient.util.Email;
import com.maxsoft.emailclient.util.JsonReader;


public class EmailSender {

    public static void main(String[] args) {
        Email.send(JsonReader.getExecutionResults());
    }


}
