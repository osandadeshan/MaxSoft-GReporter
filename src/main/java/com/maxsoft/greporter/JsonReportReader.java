package com.maxsoft.greporter;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static com.maxsoft.greporter.Constants.*;

/*
  Project Name : MaxSoft GReporter
  Developer    : Osanda Deshan
  Version      : 1.0.0
  Date         : 6/23/2018
  Time         : 2:56 PM
  Description  :
 */

public class JsonReportReader {

    private static final DecimalFormat decimalFormat = new DecimalFormat(".##");

    public static String getProjectName() {
        return getJsonAttributeValueAsString("$.projectName");
    }

    public static String getTimestamp() {
        return getJsonAttributeValueAsString("$.timestamp");
    }

    public static String getEnvironment() {
        return getJsonAttributeValueAsString("$.environment").toUpperCase();
    }

    public static int getExecutionTime() {
        return Integer.parseInt(getJsonAttributeValueAsString("$.executionTime"));
    }

    public static String getExecutionStatus() {
        String executionStatus = getJsonAttributeValueAsString("$.executionStatus");
        return executionStatus.substring(0, 1).toUpperCase() + executionStatus.substring(1);
    }

    public static String getExecutionStatusColor() {
        switch (getExecutionStatus().toLowerCase()) {
            case "passed":
                return GREEN;
            case "failed":
                return RED;
            case "skipped":
                return GRAY;
            default:
                return "";
        }
    }

    public static int getPassedScenariosCount() {
        return Integer.parseInt(getJsonAttributeValueAsString("$.passedScenariosCount"));
    }

    public static int getFailedScenariosCount() {
        return Integer.parseInt(getJsonAttributeValueAsString("$.failedScenariosCount"));
    }

    public static int getSkippedScenariosCount() {
        return Integer.parseInt(getJsonAttributeValueAsString("$.skippedScenariosCount"));
    }

    public static int getTotalScenariosCount() {
        return getPassedScenariosCount() + getFailedScenariosCount() + getSkippedScenariosCount();
    }

    public static double getPassedScenariosPercentage() {
        return Double.parseDouble(decimalFormat
                .format((double) getPassedScenariosCount() * 100 / getTotalScenariosCount()));
    }

    public static double getFailedScenariosPercentage() {
        return Double.parseDouble(decimalFormat
                .format((double) getFailedScenariosCount() * 100 / getTotalScenariosCount()));
    }

    public static double getSkippedScenariosPercentage() {
        return Double.parseDouble(decimalFormat
                .format((double) getSkippedScenariosCount() * 100 / getTotalScenariosCount()));
    }

    public static int getPassedSpecsCount() {
        return Integer.parseInt(getJsonAttributeValueAsString("$.passedSpecsCount"));
    }

    public static int getFailedSpecsCount() {
        return Integer.parseInt(getJsonAttributeValueAsString("$.failedSpecsCount"));
    }

    public static int getSkippedSpecsCount() {
        return Integer.parseInt(getJsonAttributeValueAsString("$.skippedSpecsCount"));
    }

    public static int getTotalSpecsCount() {
        return getPassedSpecsCount() + getFailedSpecsCount() + getSkippedSpecsCount();
    }

    public static double getPassedSpecsPercentage() {
        return Double.parseDouble(decimalFormat.format((double) getPassedSpecsCount() * 100 / getTotalSpecsCount()));
    }

    public static double getFailedSpecsPercentage() {
        return Double.parseDouble(decimalFormat.format((double) getFailedSpecsCount() * 100 / getTotalSpecsCount()));
    }

    public static double getSkippedSpecsPercentage() {
        return Double.parseDouble(decimalFormat.format((double) getSkippedSpecsCount() * 100 / getTotalSpecsCount()));
    }

    public static List<String> getSpecHeadingList() {
        List<String> specHeadingList = new ArrayList<>();

        for (Object object : getSpecResultsAsJsonArray()) {
            JSONObject jsonObject1 = (JSONObject) object;
            String specHeading = jsonObject1.get("specHeading").toString();
            specHeadingList.add(specHeading);
        }
        return specHeadingList;
    }

    public static List<String> getPassedScenarioCountList() {
        List<String> passedScenarioCountList = new ArrayList<>();

        for (Object object : getSpecResultsAsJsonArray()) {
            JSONObject jsonObject1 = (JSONObject) object;
            String passedScenarioCount = jsonObject1.get("passedScenarioCount").toString();
            passedScenarioCountList.add(passedScenarioCount);
        }
        return passedScenarioCountList;
    }

    public static List<String> getPassedScenarioPercentageList() {
        List<String> passedScenariosPercentageList = new ArrayList<>();

        for (Object object : getSpecResultsAsJsonArray()) {
            JSONObject jsonObject1 = (JSONObject) object;
            int passedScenarioCount = Integer.parseInt(jsonObject1.get("passedScenarioCount").toString());
            int failedScenarioCount = Integer.parseInt(jsonObject1.get("failedScenarioCount").toString());
            int skippedScenarioCount = Integer.parseInt(jsonObject1.get("skippedScenarioCount").toString());
            int totalScenarioCount = passedScenarioCount + failedScenarioCount + skippedScenarioCount;
            double passedScenariosPercentage = 0;
            if (totalScenarioCount != 0) {
                passedScenariosPercentage = Double.parseDouble(decimalFormat
                        .format((double) (passedScenarioCount * 100) / (double) totalScenarioCount));
            }
            passedScenariosPercentageList.add(passedScenariosPercentage + "%");
        }
        return passedScenariosPercentageList;
    }

    public static List<String> getFailedScenarioCountList() {
        List<String> failedScenarioCountList = new ArrayList<>();

        for (Object object : getSpecResultsAsJsonArray()) {
            JSONObject jsonObject1 = (JSONObject) object;
            String failedScenarioCount = jsonObject1.get("failedScenarioCount").toString();
            failedScenarioCountList.add(failedScenarioCount);
        }
        return failedScenarioCountList;
    }

    public static List<String> getFailedScenarioPercentageList() {
        List<String> failedScenariosPercentageList = new ArrayList<>();

        for (Object object : getSpecResultsAsJsonArray()) {
            JSONObject jsonObject1 = (JSONObject) object;
            int passedScenarioCount = Integer.parseInt(jsonObject1.get("passedScenarioCount").toString());
            int failedScenarioCount = Integer.parseInt(jsonObject1.get("failedScenarioCount").toString());
            int skippedScenarioCount = Integer.parseInt(jsonObject1.get("skippedScenarioCount").toString());
            int totalScenarioCount = passedScenarioCount + failedScenarioCount + skippedScenarioCount;
            double failedScenariosPercentage = 0;
            if (totalScenarioCount != 0) {
                failedScenariosPercentage = Double.parseDouble(decimalFormat
                        .format((double) (failedScenarioCount * 100) / (double) totalScenarioCount));
            }
            failedScenariosPercentageList.add(failedScenariosPercentage + "%");
        }
        return failedScenariosPercentageList;
    }

    public static List<String> getSkippedScenarioCountList() {
        List<String> skippedScenarioCountList = new ArrayList<>();

        for (Object object : getSpecResultsAsJsonArray()) {
            JSONObject jsonObject1 = (JSONObject) object;
            String skippedScenarioCount = jsonObject1.get("skippedScenarioCount").toString();
            skippedScenarioCountList.add(skippedScenarioCount);
        }
        return skippedScenarioCountList;
    }

    public static List<String> getSkippedScenarioPercentageList() {
        List<String> skippedScenariosPercentageList = new ArrayList<>();

        for (Object object : getSpecResultsAsJsonArray()) {
            JSONObject jsonObject = (JSONObject) object;
            int passedScenarioCount = Integer.parseInt(jsonObject.get("passedScenarioCount").toString());
            int failedScenarioCount = Integer.parseInt(jsonObject.get("failedScenarioCount").toString());
            int skippedScenarioCount = Integer.parseInt(jsonObject.get("skippedScenarioCount").toString());
            int totalScenarioCount = passedScenarioCount + failedScenarioCount + skippedScenarioCount;
            double skippedScenariosPercentage = 0;
            if (totalScenarioCount != 0) {
                skippedScenariosPercentage = Double.parseDouble(decimalFormat
                        .format((double) (skippedScenarioCount * 100) / (double) totalScenarioCount));
            }
            skippedScenariosPercentageList.add(skippedScenariosPercentage + "%");
        }
        return skippedScenariosPercentageList;
    }

    private static String getJsonAttributeValueAsString(String jsonPath) {
        Object responseString = Configuration.defaultConfiguration().jsonProvider()
                .parse(readFile(Charset.defaultCharset()));
        return JsonPath.read(responseString, jsonPath).toString();
    }

    private static JSONArray getSpecResultsAsJsonArray() {
        JSONArray jsonArray = null;

        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(JSON_FILE_PATH));
            JSONObject jsonObject = (JSONObject) obj;
            String JsonArrayResults = JsonPath.read(jsonObject, "$.specResults").toString();
            jsonArray = (JSONArray) parser.parse(JsonArrayResults);
        } catch (IOException | ParseException ex) {
            ex.printStackTrace();
        }
        return jsonArray;
    }

    private static String readFile(Charset encoding) {
        byte[] encoded = new byte[0];

        try {
            encoded = Files.readAllBytes(Paths.get(JSON_FILE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(encoded, encoding);
    }
}
