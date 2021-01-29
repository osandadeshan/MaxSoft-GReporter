package com.maxsoft.greporter.email;

import com.maxsoft.greporter.JsonReportReader;
import com.maxsoft.greporter.chart.BarChart;
import com.maxsoft.greporter.chart.PieChart;
import org.json.simple.parser.ParseException;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.Properties;

import static com.maxsoft.greporter.util.PropertyReader.read;
import static com.maxsoft.greporter.Constants.EMAIL_PROPERTY_FILE_PATH;
import static com.maxsoft.greporter.JsonReportReader.*;

/*
 * Project Name : MaxSoft GReporter
 * Developer    : Osanda Deshan
 * Version      : 1.0.0
 * Date         : 6/23/2018
 * Time         : 2:56 PM
 * Description  :
 */

public class EmailProcessor {

    private static final String DEV = "dev";
    private static final String QA = "qa";
    private static final String UAT = "uat";
    private static final String PROD = "prod";

    private static String isEmailNeeded;
    private static String senderEmailAddress;
    private static String senderEmailPassword;
    private static String senderEmailSmtpHost;
    private static String senderEmailSmtpPort;
    private static String recipientsEmailAddresses;
    private static String emailSubject;
    private static String emailBodyTitleHeadingSize;
    private static String emailBodyTitle;
    private static String emailBody;

    public static void triggerEmail() {
        setEmailConfigurations();

        if (isEmailNeeded.equalsIgnoreCase("true") || isEmailNeeded.equalsIgnoreCase("yes")
                || isEmailNeeded.equalsIgnoreCase("y")) {
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", senderEmailSmtpHost);
            props.put("mail.smtp.port", senderEmailSmtpPort);

            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(senderEmailAddress, senderEmailPassword);
                        }
                    });

            try {
                // Create a default MimeMessage object.
                Message message = new MimeMessage(session);

                // Set From: header field of the header.
                message.setFrom(new InternetAddress(senderEmailAddress));

                // Set To: header field of the header.
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(recipientsEmailAddresses));

                // Set Subject: header field
                message.setSubject(emailSubject);

                // This mail has 2 parts, the BODY and the embedded image
                MimeMultipart multipart = new MimeMultipart("related");

                // first part (the html)
                BodyPart messageBodyPart = new MimeBodyPart();
                String htmlText = "<h2 style=\"color:black;\"> Test Execution Status: " + "<span "
                        + JsonReportReader.getExecutionStatusColor() + ">" + JsonReportReader.getExecutionStatus()
                        + "</span></h2><br />" + "<h" + emailBodyTitleHeadingSize + ">" + emailBodyTitle + "</h"
                        + emailBodyTitleHeadingSize + ">" + "<br />" + emailBody + "<br /><br /><br />"
                        + getExecutionResults();
                messageBodyPart.setContent(htmlText, "text/html");
                // add it
                multipart.addBodyPart(messageBodyPart);

                // second part (the pie chart)
                messageBodyPart = new MimeBodyPart();
                PieChart.save(JsonReportReader.getPassedScenariosCount(), JsonReportReader.getFailedScenariosCount(),
                        JsonReportReader.getSkippedScenariosCount());
                DataSource fds = new FileDataSource(
                        PieChart.getSavedPieChartImagePath());

                messageBodyPart.setDataHandler(new DataHandler(fds));
                messageBodyPart.setHeader("Content-ID", "<pie-chart>");
                messageBodyPart.setFileName(PieChart.getSavedPieChartImageName());

                // add pie chart to the multipart
                multipart.addBodyPart(messageBodyPart);

                // third part (the bar chart)
                messageBodyPart = new MimeBodyPart();
                BarChart.save();
                DataSource fds2 = new FileDataSource(
                        BarChart.getSavedBarChartImagePath());

                messageBodyPart.setDataHandler(new DataHandler(fds2));
                messageBodyPart.setHeader("Content-ID", "<bar-chart>");
                messageBodyPart.setFileName(BarChart.getSavedBarChartImageName());

                // add bar chart to the multipart
                multipart.addBodyPart(messageBodyPart);

                // put everything together
                message.setContent(multipart);
                // Send message
                Transport.send(message);

                System.out.println("Sent message successfully....");

            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }

        } else {
            throw new InvalidParameterException("Email notifications are currently turned off. " +
                    "To turn on, go to <project_dir>/env/email/email.properties");
        }
    }

    private static String getExecutionResults() {
        String executionResults = null;
        try {
            executionResults = EmailTemplate.get()
                    .replaceAll("#projectName", getProjectName())
                    .replaceAll("#timestamp", getTimestamp())
                    .replaceAll("#environment", getEnvironment())
                    .replaceAll("#executionTime", milliSecondsToTime(getExecutionTime()))
                    .replaceAll("#executionStatus", getExecutionStatus())
                    .replaceAll("#successRate", getPassedScenariosPercentage() + "%")
                    .replaceAll("#failRate", getFailedScenariosPercentage() + "%")

                    .replaceAll("#totalScenariosCount", String.valueOf(getTotalScenariosCount()))
                    .replaceAll("#passedScenariosCount", String.valueOf(getPassedScenariosCount()))
                    .replaceAll("#passedScenarioPercentage", getPassedScenariosPercentage() + "%")
                    .replaceAll("#failedScenariosCount", String.valueOf(getFailedScenariosCount()))
                    .replaceAll("#failedScenarioPercentage", getFailedScenariosPercentage() + "%")
                    .replaceAll("#skippedScenariosCount", String.valueOf(getSkippedScenariosCount()))
                    .replaceAll("#skippedScenarioPercentage", getSkippedScenariosPercentage() + "%")

                    .replaceAll("#totalSpecsCount", String.valueOf(getTotalSpecsCount()))
                    .replaceAll("#passedSpecsCount", String.valueOf(getPassedSpecsCount()))
                    .replaceAll("#passedSpecsPercentage", getPassedSpecsPercentage() + "%")
                    .replaceAll("#failedSpecsCount", String.valueOf(getFailedSpecsCount()))
                    .replaceAll("#failedSpecsPercentage", getFailedSpecsPercentage() + "%")
                    .replaceAll("#skippedSpecsCount", String.valueOf(getSkippedSpecsCount()))
                    .replaceAll("#skippedSpecsPercentage", getSkippedSpecsPercentage() + "%");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return executionResults;
    }

    private static void setEmailConfigurations() {
        String environment;
        if (System.getProperty("emailConfigEnv") == null) {
            environment = DEV;
        } else {
            environment = System.getProperty("emailConfigEnv");
        }

        switch (environment.toLowerCase()) {
            case DEV:
                isEmailNeeded = read(EMAIL_PROPERTY_FILE_PATH, "dev_is_email_notifications_needed");
                senderEmailAddress = read(EMAIL_PROPERTY_FILE_PATH, "dev_sender_email_address");
                senderEmailPassword = read(EMAIL_PROPERTY_FILE_PATH, "dev_sender_email_password");
                senderEmailSmtpHost = read(EMAIL_PROPERTY_FILE_PATH, "dev_sender_email_smtp_host");
                senderEmailSmtpPort = read(EMAIL_PROPERTY_FILE_PATH, "dev_sender_email_smtp_port");
                recipientsEmailAddresses = read(EMAIL_PROPERTY_FILE_PATH, "dev_recipients_email_addresses");
                emailSubject = read(EMAIL_PROPERTY_FILE_PATH, "dev_email_subject");
                emailBodyTitleHeadingSize = read(EMAIL_PROPERTY_FILE_PATH, "dev_email_body_title_heading_size");
                emailBodyTitle = read(EMAIL_PROPERTY_FILE_PATH, "dev_email_body_title");
                emailBody = read(EMAIL_PROPERTY_FILE_PATH, "dev_email_body");
                break;
            case QA:
                isEmailNeeded = read(EMAIL_PROPERTY_FILE_PATH, "qa_is_email_notifications_needed");
                senderEmailAddress = read(EMAIL_PROPERTY_FILE_PATH, "qa_sender_email_address");
                senderEmailPassword = read(EMAIL_PROPERTY_FILE_PATH, "qa_sender_email_password");
                senderEmailSmtpHost = read(EMAIL_PROPERTY_FILE_PATH, "qa_sender_email_smtp_host");
                senderEmailSmtpPort = read(EMAIL_PROPERTY_FILE_PATH, "qa_sender_email_smtp_port");
                recipientsEmailAddresses = read(EMAIL_PROPERTY_FILE_PATH, "qa_recipients_email_addresses");
                emailSubject = read(EMAIL_PROPERTY_FILE_PATH, "qa_email_subject");
                emailBodyTitleHeadingSize = read(EMAIL_PROPERTY_FILE_PATH, "qa_email_body_title_heading_size");
                emailBodyTitle = read(EMAIL_PROPERTY_FILE_PATH, "qa_email_body_title");
                emailBody = read(EMAIL_PROPERTY_FILE_PATH, "qa_email_body");
                break;
            case UAT:
                isEmailNeeded = read(EMAIL_PROPERTY_FILE_PATH, "uat_is_email_notifications_needed");
                senderEmailAddress = read(EMAIL_PROPERTY_FILE_PATH, "uat_sender_email_address");
                senderEmailPassword = read(EMAIL_PROPERTY_FILE_PATH, "uat_sender_email_password");
                senderEmailSmtpHost = read(EMAIL_PROPERTY_FILE_PATH, "uat_sender_email_smtp_host");
                senderEmailSmtpPort = read(EMAIL_PROPERTY_FILE_PATH, "uat_sender_email_smtp_port");
                recipientsEmailAddresses = read(EMAIL_PROPERTY_FILE_PATH, "uat_recipients_email_addresses");
                emailSubject = read(EMAIL_PROPERTY_FILE_PATH, "uat_email_subject");
                emailBodyTitleHeadingSize = read(EMAIL_PROPERTY_FILE_PATH, "uat_email_body_title_heading_size");
                emailBodyTitle = read(EMAIL_PROPERTY_FILE_PATH, "uat_email_body_title");
                emailBody = read(EMAIL_PROPERTY_FILE_PATH, "uat_email_body");
                break;
            case PROD:
                isEmailNeeded = read(EMAIL_PROPERTY_FILE_PATH, "prod_is_email_notifications_needed");
                senderEmailAddress = read(EMAIL_PROPERTY_FILE_PATH, "prod_sender_email_address");
                senderEmailPassword = read(EMAIL_PROPERTY_FILE_PATH, "prod_sender_email_password");
                senderEmailSmtpHost = read(EMAIL_PROPERTY_FILE_PATH, "prod_sender_email_smtp_host");
                senderEmailSmtpPort = read(EMAIL_PROPERTY_FILE_PATH, "prod_sender_email_smtp_port");
                recipientsEmailAddresses = read(EMAIL_PROPERTY_FILE_PATH, "prod_recipients_email_addresses");
                emailSubject = read(EMAIL_PROPERTY_FILE_PATH, "prod_email_subject");
                emailBodyTitleHeadingSize = read(EMAIL_PROPERTY_FILE_PATH, "prod_email_body_title_heading_size");
                emailBodyTitle = read(EMAIL_PROPERTY_FILE_PATH, "prod_email_body_title");
                emailBody = read(EMAIL_PROPERTY_FILE_PATH, "prod_email_body");
                break;
            default:
                throw new InvalidParameterException("Please enter an valid environment dev|qa|uat|prod " +
                        "into the \"emailConfigEnv\" property in <project_dir>/pom.xml");
        }
    }

    private static String milliSecondsToTime(int milliseconds) {
        int milli = milliseconds % 1000;
        int sec = milliseconds / 1000;
        int second = sec % 60;
        int minute = sec / 60;
        if (minute >= 60) {
            int hour = minute / 60;
            minute %= 60;
            return hour + "h " + (minute < 10 ? "0" + minute : minute) + "m "
                    + (second < 10 ? "0" + second : second) + "s " + milli + "ms";
        } else {
            return minute + "m " + (second < 10 ? "0" + second : second) + "s " + milli + "ms";
        }
    }
}
