package com.maxsoft.greporter.email;

import static com.maxsoft.greporter.Constants.*;
import static com.maxsoft.greporter.JsonReportReader.*;

/*
 * Project Name : MaxSoft GReporter
 * Developer    : Osanda Deshan
 * Version      : 1.0.0
 * Date         : 6/23/2018
 * Time         : 2:56 PM
 * Description  :
 */

public class EmailTemplate {

    public static String getTemplate() {
        String template = "<!DOCTYPE html>" +
                "            <html>" +
                "            <head>" +
                "            </head>" +
                "            <body>" +
                "            <table style=\"width:70%; border:1px solid black; border-collapse:collapse;\">" +
                "              <col width=45%>" +
                "              <tr>" +
                "                <th colspan=3 style=\"text-align: left; padding: 8px; border: 1px solid black; background-color: #068bac; color: white;\"><b>Test Execution Summary</b></th>" +
                "              </tr>" +
                "              <tr>" +
                "                <td style=\"text-align: left; padding: 8px; border: 1px solid black; background-color: #f2f2f2\"><b>Project Name</b></td>" +
                "                <td style=\"text-align: left; padding: 8px; border: 1px solid black; background-color: #f2f2f2\"><b>#projectName</b></td>" +
                "              </tr>" +
                "              <tr>" +
                "                <td style=\"text-align: left; padding: 8px; border: 1px solid black;\"><b>Timestamp</b></td>" +
                "                <td style=\"text-align: left; padding: 8px; border: 1px solid black;\"><b>#timestamp</b></td>" +
                "              </tr>" +
                "              <tr>" +
                "                <td style=\"text-align: left; padding: 8px; border: 1px solid black; background-color: #f2f2f2\"><b>Environment</b></td>" +
                "                <td style=\"text-align: left; padding: 8px; border: 1px solid black; background-color: #f2f2f2\"><b>#environment</b></td>" +
                "              </tr>" +
                "              <tr>" +
                "                <td style=\"text-align: left; padding: 8px; border: 1px solid black;\"><b>Execution Time</b></td>" +
                "                <td style=\"text-align: left; padding: 8px; border: 1px solid black;\"><b>#executionTime</b></td>" +
                "              </tr>" +
                "              <tr>" +
                "                <td style=\"text-align: left; padding: 8px; border: 1px solid black; background-color: #f2f2f2\"><b>Execution Status</b></td>" +
                "                <td style=\"text-align: left; padding: 8px; border: 1px solid black; background-color: #f2f2f2; color: #executionColor\"><b>#executionStatus</b></td>" +
                "              </tr>" +
                "              <tr>" +
                "                <td style=\"text-align: left; padding: 8px; border: 1px solid black;\"><b>Success Rate</b></td>" +
                "                <td style=\"text-align: left; padding: 8px; border: 1px solid black;\"><b>#successRate</b></td>" +
                "              </tr>" +
                "              <tr>" +
                "                <td style=\"text-align: left; padding: 8px; border: 1px solid black; background-color: #f2f2f2\"><b>Fail Rate</b></td>" +
                "                <td style=\"text-align: left; padding: 8px; border: 1px solid black; background-color: #f2f2f2\"><b>#failRate</b></td>" +
                "              </tr>" +
                "            </table>" +
                "            <br><br>" +
                "           <table style=\"width:70%; border:1px solid black; border-collapse:collapse;\">" +
                "               <col width=45%>" +
                "               <tr>" +
                "                   <th colspan=3 style=\"text-align: left; padding: 8px; border: 1px solid black; background-color: #068bac; color: white;\"><b>Scenario Information</b></th>" +
                "               </tr>" +
                "               <tr>" +
                "                   <th style=\"text-align: left; padding: 8px; border: 1px solid black; background-color: #08A7CE; color: white;\"><b></b></th>" +
                "                   <th style=\"text-align: left; padding: 8px; border: 1px solid black; background-color: #08A7CE; color: white;\"><b>Scenario Count</b></th>" +
                "                   <th style=\"text-align: left; padding: 8px; border: 1px solid black; background-color: #08A7CE; color: white;\"><b>Percentage (%)</b></th>" +
                "               </tr>" +
                "               <tr>" +
                "                   <td style=\"text-align: left; padding: 8px; border: 1px solid black; background-color: #f2f2f2\"><b>Total Scenarios</b></td>" +
                "                   <td style=\"text-align: left; padding: 8px; border: 1px solid black; background-color: #f2f2f2\"><b>#totalScenariosCount</b></td>" +
                "                   <td style=\"text-align: left; padding: 8px; border: 1px solid black; background-color: #f2f2f2\"></td>" +
                "               </tr>" +
                "               <tr>" +
                "                   <td style=\"text-align: left; padding: 8px; border: 1px solid black; color: " + GREEN + ";\"><b>Passed Scenarios</b></td>" +
                "                   <td style=\"text-align: left; padding: 8px; border: 1px solid black; color: " + GREEN + ";\"><b>#passedScenariosCount</b></td>" +
                "                   <td style=\"text-align: left; padding: 8px; border: 1px solid black; color: " + GREEN + ";\"><b>#passedScenarioPercentage</b></td>" +
                "               </tr>" +
                "               <tr>" +
                "                   <td style=\"text-align: left; padding: 8px; border: 1px solid black; color: " + RED + "; background-color: #f2f2f2\"><b>Failed Scenarios</b></td>" +
                "                   <td style=\"text-align: left; padding: 8px; border: 1px solid black; color: " + RED + "; background-color: #f2f2f2\"><b>#failedScenariosCount</b></td>" +
                "                   <td style=\"text-align: left; padding: 8px; border: 1px solid black; color: " + RED + "; background-color: #f2f2f2\"><b>#failedScenarioPercentage</b></td>" +
                "               </tr>" +
                "               <tr>" +
                "                   <td style=\"text-align: left; padding: 8px; border: 1px solid black; color: " + GRAY + ";\"><b>Skipped Scenarios</b></td>" +
                "                   <td style=\"text-align: left; padding: 8px; border: 1px solid black; color: " + GRAY + ";\"><b>#skippedScenariosCount</b></td>" +
                "                   <td style=\"text-align: left; padding: 8px; border: 1px solid black; color: " + GRAY + ";\"><b>#skippedScenarioPercentage</b></td>" +
                "               </tr>" +
                "           </table>" +
                "            <img src=\"cid:pie-chart\" alt=\"Pie Chart For Test Execution Results\" align=\"left\" style=\"padding-top: 30px; padding-bottom: 35px;\"> <br><br><br>" +
                "            <br><br><br><br>" +
                "            <table style=\"width:70%; border:1px solid black; border-collapse:collapse;\">" +
                "              <col width=45%>" +
                "              <tr>" +
                "                <th colspan=3 style=\"text-align: left; padding: 8px; border: 1px solid black; background-color: #068bac; color: white;\"><b>Specification Information</b></th>" +
                "              </tr>" +
                "              <tr>" +
                "                <th style=\"text-align: left; padding: 8px; border: 1px solid black; background-color: #08A7CE; color: white;\"><b></b></th>" +
                "                <th style=\"text-align: left; padding: 8px; border: 1px solid black; background-color: #08A7CE; color: white;\"><b>Specification Count</b></th>" +
                "                <th style=\"text-align: left; padding: 8px; border: 1px solid black; background-color: #08A7CE; color: white;\"><b>Percentage (%)</b></th>" +
                "              </tr>" +
                "              <tr>" +
                "                <td style=\"text-align: left; padding: 8px; border: 1px solid black; background-color: #f2f2f2\"><b>Total Specifications</b></td>" +
                "                <td style=\"text-align: left; padding: 8px; border: 1px solid black; background-color: #f2f2f2\"><b>#totalSpecsCount</b></td>" +
                "                <td style=\"text-align: left; padding: 8px; border: 1px solid black; background-color: #f2f2f2\"></td>" +
                "              </tr>" +
                "              <tr>" +
                "                <td style=\"text-align: left; padding: 8px; border: 1px solid black; color: " + GREEN + ";\"><b>Passed Specifications</b></td>" +
                "                <td style=\"text-align: left; padding: 8px; border: 1px solid black; color: " + GREEN + ";\"><b>#passedSpecsCount</b></td>" +
                "                <td style=\"text-align: left; padding: 8px; border: 1px solid black; color: " + GREEN + ";\"><b>#passedSpecsPercentage</b></td>" +
                "              </tr>" +
                "              <tr>" +
                "                <td style=\"text-align: left; padding: 8px; border: 1px solid black; color: " + RED + "; background-color: #f2f2f2\"><b>Failed Specifications</b></td>" +
                "                <td style=\"text-align: left; padding: 8px; border: 1px solid black; color: " + RED + "; background-color: #f2f2f2\"><b>#failedSpecsCount</b></td>" +
                "                <td style=\"text-align: left; padding: 8px; border: 1px solid black; color: " + RED + "; background-color: #f2f2f2\"><b>#failedSpecsPercentage</b></td>" +
                "              </tr>" +
                "              <tr>" +
                "                <td style=\"text-align: left; padding: 8px; border: 1px solid black; color: " + GRAY + ";\"><b>Skipped Specifications</b></td>" +
                "                <td style=\"text-align: left; padding: 8px; border: 1px solid black; color: " + GRAY + ";\"><b>#skippedSpecsCount</b></td>" +
                "                <td style=\"text-align: left; padding: 8px; border: 1px solid black; color: " + GRAY + ";\"><b>#skippedSpecsPercentage</b></td>" +
                "              </tr>" +
                "            </table>" +
                "            <br><br>";
        return template + getRegressionTestSummary();
    }

    public static String getRegressionTestSummary() {
        String appendHtml = "<style>" +
                "       table#regression_table tr:nth-child(odd){background-color: #f2f2f2}" +
                "   </style>" +
                "            <table id=\"regression_table\" style=\"width:70%; border:1px solid black; border-collapse:collapse;\">" +
                "               <col width=\"45%\">" +
                "                   <tr>" +
                "                       <th colspan=\"8\" style=\"text-align: left; padding: 8px; border: 1px solid black; background-color: #068bac; color: white;\">" +
                "                           <b>Regression Testing Summary</b></th>" +
                "                   </tr>" +
                "                   <tr>" +
                "                       <th style=\"text-align: left; padding: 8px; border: 1px solid black; background-color: #08A7CE; color: white;\">" +
                "                           <b>Specification</b></th>" +
                "                       <th style=\"text-align: left; padding: 8px; border: 1px solid black; background-color: #08A7CE; color: white;\">" +
                "                           <b>Total Scenarios</b></th>" +
                "                       <th style=\"text-align: left; padding: 8px; border: 1px solid black; background-color: #08A7CE; color: white;\">" +
                "                           <b>Passed Scenario Count</b></th>" +
                "                       <th style=\"text-align: left; padding: 8px; border: 1px solid black; background-color: #08A7CE; color: white;\">" +
                "                           <b>Passed Scenario Percentage (%)</b></th>" +
                "                       <th style=\"text-align: left; padding: 8px; border: 1px solid black; background-color: #08A7CE; color: white;\">" +
                "                           <b>Failed Scenario Count</b></th>" +
                "                       <th style=\"text-align: left; padding: 8px; border: 1px solid black; background-color: #08A7CE; color: white;\">" +
                "                           <b>Failed Scenario Percentage (%)</b></th>" +
                "                       <th style=\"text-align: left; padding: 8px; border: 1px solid black; background-color: #08A7CE; color: white;\">" +
                "                           <b>Skipped Scenario Count</b></th>" +
                "                       <th style=\"text-align: left; padding: 8px; border: 1px solid black; background-color: #08A7CE; color: white;\">" +
                "                           <b>Skipped Scenario Percentage (%)</b></th>" +
                "                   </tr>";

        int iterator = getSpecHeadingList().size();
        setScenarioExecutionStatusAsCounts();
        setScenarioExecutionStatusAsPercentages();

        String specHeadingColor;
        String totalScenariosColor;
        String passedScenariosColor;
        String failedScenariosColor;
        String skippedScenariosColor;

        for (int i = 0; i < iterator; i++) {

            int totalScenariosOfSpec = Integer.parseInt(getPassedScenarioCountList().get(i))
                    + Integer.parseInt(getFailedScenarioCountList().get(i))
                    + Integer.parseInt(getSkippedScenarioCountList().get(i));

            if (totalScenariosOfSpec == Integer.parseInt(getPassedScenarioCountList().get(i))) {
                specHeadingColor = GREEN;
                totalScenariosColor = GREEN;
                passedScenariosColor = GREEN;
                failedScenariosColor = GREEN;
                skippedScenariosColor = GREEN;

            } else if (totalScenariosOfSpec == Integer.parseInt(getFailedScenarioCountList().get(i))) {
                specHeadingColor = RED;
                totalScenariosColor = RED;
                passedScenariosColor = RED;
                failedScenariosColor = RED;
                skippedScenariosColor = RED;

            } else if (totalScenariosOfSpec == Integer.parseInt(getSkippedScenarioCountList().get(i))) {
                specHeadingColor = GRAY;
                totalScenariosColor = GRAY;
                passedScenariosColor = GRAY;
                failedScenariosColor = GRAY;
                skippedScenariosColor = GRAY;

            } else {
                specHeadingColor = BLACK;
                totalScenariosColor = BLACK;
                passedScenariosColor = GREEN;
                failedScenariosColor = RED;
                skippedScenariosColor = GRAY;
            }

            appendHtml = appendHtml.concat("<tr>" +
                    "<td style=\"text-align: left; padding: 8px; border: 1px solid black; color: " + specHeadingColor
                    + ";\"><b>" + getSpecHeadingList().get(i) + "</b></td>" +
                    "<td style=\"text-align: left; padding: 8px; border: 1px solid black; color: " + totalScenariosColor
                    + ";\"><b>" + totalScenariosOfSpec + "</b></td>" +
                    "<td style=\"text-align: left; padding: 8px; border: 1px solid black; color: " + passedScenariosColor
                    + ";\"><b>" + getPassedScenarioCountList().get(i) + "</b></td>" +
                    "<td style=\"text-align: left; padding: 8px; border: 1px solid black; color: " + passedScenariosColor
                    + ";\"><b>" + getPassedScenarioPercentageList().get(i) + "</b></td>" +
                    "<td style=\"text-align: left; padding: 8px; border: 1px solid black; color: " + failedScenariosColor
                    + ";\"><b>" + getFailedScenarioCountList().get(i) + "</b></td>" +
                    "<td style=\"text-align: left; padding: 8px; border: 1px solid black; color: " + failedScenariosColor
                    + ";\"><b>" + getFailedScenarioPercentageList().get(i) + "</b></td>" +
                    "<td style=\"text-align: left; padding: 8px; border: 1px solid black; color: " + skippedScenariosColor
                    + ";\"><b>" + getSkippedScenarioCountList().get(i) + "</b></td>" +
                    "<td style=\"text-align: left; padding: 8px; border: 1px solid black; color: " + skippedScenariosColor
                    + ";\"><b>" + getSkippedScenarioPercentageList().get(i) + "</b></td>" +
                    "</tr>");
        }

        String lastAppend = "</table>" +
                "<br><br>" +
                "<img src=\"cid:bar-chart\" alt=\"Bar Chart For Test Execution Results\" align=\"left\" style=\"border: 1px solid black;\">" +
                "</body>" +
                "</html>";
        return appendHtml + lastAppend;
    }
}
