package com.maxsoft.greporter.chart;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.style.PieStyler;
import org.knowm.xchart.style.Styler;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import static com.maxsoft.greporter.util.PropertyReader.read;
import static com.maxsoft.greporter.Constants.*;

/*
 * Project Name : MaxSoft GReporter
 * Developer    : Osanda Deshan
 * Version      : 1.0.0
 * Date         : 6/23/2018
 * Time         : 2:56 PM
 * Description  :
 */

public class PieChart {

    private static final Color DARK_GRAY = new Color(96, 96, 96);
    private static final Color GREEN = new Color(76, 153, 0);
    private static final Color RED = new Color(205, 0, 0);
    private static String PIE_CHART_IMAGE_PATH;
    private static String PIE_CHART_IMAGE_NAME;

    public static void save(int passedCount, int failedCount, int skippedCount) {
        // Get the property values
        PIE_CHART_IMAGE_PATH = read(PIE_CHART_PROPERTY_FILE_PATH, "pie_chart_image_path");
        PIE_CHART_IMAGE_NAME = read(PIE_CHART_PROPERTY_FILE_PATH, "pie_chart_image_name");

        String pieChartDir = CURRENT_DIRECTORY + FILE_SEPARATOR + PIE_CHART_IMAGE_PATH;
        String imageDir = pieChartDir + FILE_SEPARATOR + PIE_CHART_IMAGE_NAME;

        // Create PieChart
        org.knowm.xchart.PieChart chart = new PieChartBuilder().width(640).height(480)
                .title(read(PIE_CHART_PROPERTY_FILE_PATH, "pie_chart_title"))
                .theme(Styler.ChartTheme.GGPlot2).build();

        // Customize PieChart
        chart.getStyler().setLegendVisible(true);
        chart.getStyler().setLegendLayout(Styler.LegendLayout.Horizontal);
        chart.getStyler().setLegendBorderColor(Color.BLACK);
        chart.getStyler().setLegendPosition(Styler.LegendPosition.OutsideS);
        chart.getStyler().setAnnotationType(PieStyler.AnnotationType.LabelAndPercentage);
        chart.getStyler().setAnnotationDistance(1.15);
        chart.getStyler().setPlotContentSize(.6);
        chart.getStyler().setStartAngleInDegrees(90);
        chart.getStyler().setPlotBorderVisible(true);
        chart.getStyler().setPlotBorderColor(Color.BLACK);
        chart.getStyler().setChartTitleBoxBorderColor(Color.BLACK);

        // Series
        chart.addSeries("Failed", failedCount).setFillColor(RED);
        chart.addSeries("Passed", passedCount).setFillColor(GREEN);
        chart.addSeries("Skipped", skippedCount).setFillColor(DARK_GRAY);

        // Show it
        //new SwingWrapper(chart).displayChart();

        // Save it, if the pi-chart directory is not there create it
        File directory = new File(pieChartDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        try {
            BitmapEncoder.saveBitmap(chart, imageDir, BitmapEncoder.BitmapFormat.PNG);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // or save it in high-res
        //BitmapEncoder.saveBitmapWithDPI(chart, filePath, BitmapEncoder.BitmapFormat.PNG, 300);
    }

    public static String getSavedPieChartImageName() {
        return PIE_CHART_IMAGE_NAME;
    }

    public static String getSavedPieChartImagePath() {
        return CURRENT_DIRECTORY + FILE_SEPARATOR + PIE_CHART_IMAGE_PATH + FILE_SEPARATOR + PIE_CHART_IMAGE_NAME + ".png";
    }
}
