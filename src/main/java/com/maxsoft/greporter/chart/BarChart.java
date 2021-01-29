package com.maxsoft.greporter.chart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.AbstractRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import static com.maxsoft.greporter.util.PropertyReader.read;
import static com.maxsoft.greporter.Constants.BAR_CHART_PROPERTY_FILE_PATH;
import static com.maxsoft.greporter.Constants.FILE_SEPARATOR;
import static com.maxsoft.greporter.JsonReportReader.*;

/*
  Project Name : MaxSoft GReporter
  Developer    : Osanda Deshan
  Version      : 1.0.0
  Date         : 6/23/2018
  Time         : 2:56 PM
  Description  :
 */

public class BarChart {

    private static final String CURRENT_DIRECTORY = System.getProperty("user.dir");
    private static final Color LIGHT_GRAY = new Color(223, 223, 223);
    private static final Color DARK_GRAY = new Color(96, 96, 96);
    private static final Color GREEN = new Color(76, 153, 0);
    private static final Color RED = new Color(205, 0, 0);
    private static final String Y_AXIS_LABEL = "Specification Name/s";
    private static final String X_AXIS_LABEL = "Count";
    private static final String PASSED = "Passed";
    private static final String FAILED = "Failed";
    private static final String SKIPPED = "Skipped";
    private static String BAR_CHART_IMAGE_PATH;
    private static String BAR_CHART_IMAGE_NAME;

    public static void save() {
        // Get the property values
        BAR_CHART_IMAGE_PATH = read(BAR_CHART_PROPERTY_FILE_PATH, "bar_chart_image_path");
        BAR_CHART_IMAGE_NAME = read(BAR_CHART_PROPERTY_FILE_PATH, "bar_chart_image_name");

        String barChartDir = CURRENT_DIRECTORY + FILE_SEPARATOR + BAR_CHART_IMAGE_PATH;

        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        int iterator = getSpecHeadingList().size();

        for (int i = 0; i < iterator; i++) {
            dataset.addValue(Integer.valueOf(getFailedScenarioCountList().get(i)), FAILED, getSpecHeadingList().get(i));
            dataset.addValue(Integer.valueOf(getPassedScenarioCountList().get(i)), PASSED, getSpecHeadingList().get(i));
            dataset.addValue(Integer.valueOf(getSkippedScenarioCountList().get(i)), SKIPPED, getSpecHeadingList().get(i));
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                read(BAR_CHART_PROPERTY_FILE_PATH, "bar_chart_title"),
                Y_AXIS_LABEL, X_AXIS_LABEL,
                dataset, PlotOrientation.HORIZONTAL,
                true, true, false);

        CategoryPlot plot = barChart.getCategoryPlot();
        plot.getRangeAxis().setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        plot.getRenderer().setSeriesPaint(0, RED);
        plot.getRenderer().setSeriesPaint(1, GREEN);
        plot.getRenderer().setSeriesPaint(2, DARK_GRAY);

        ChartPanel chartPanel = new ChartPanel(barChart, false);

        chartPanel.setBackground(Color.WHITE);
        barChart.getPlot().setBackgroundPaint(LIGHT_GRAY);
//        barChart.setBorderVisible(true);
//        barChart.setBorderPaint(Color.BLACK);
        ((AbstractRenderer) plot.getRenderer()).setBaseLegendShape(new Rectangle(20, 20));
        LegendTitle legend = barChart.getLegend();
        Font labelFont = new Font("SansSerif", Font.BOLD, 14);
        legend.setItemFont(labelFont);

        Font categoryAxisFont = new Font("SansSerif", Font.BOLD, 14);
        CategoryAxis categoryAxis = plot.getDomainAxis();
        categoryAxis.setTickLabelFont(categoryAxisFont);
        categoryAxis.setLabelFont(new Font("Dialog", Font.PLAIN, 14));

        Font rangeAxisFont = new Font("SansSerif", Font.BOLD, 16);
        ValueAxis rangeAxis = plot.getRangeAxis();
        rangeAxis.setTickLabelFont(rangeAxisFont);
        rangeAxis.setLabelFont(new Font("Dialog", Font.PLAIN, 14));

        // Value on the bars
        plot.getRenderer().setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        plot.getRenderer().setBaseItemLabelsVisible(true);
        plot.getRenderer().setItemLabelFont(new Font("Dialog", Font.BOLD, 12));
        ItemLabelPosition position = new ItemLabelPosition(ItemLabelAnchor.INSIDE12,
                TextAnchor.TOP_CENTER);
        plot.getRenderer().setBasePositiveItemLabelPosition(position);

        int width = Integer.parseInt(read(BAR_CHART_PROPERTY_FILE_PATH, "bar_chart_image_width"));
        int height = iterator * Integer.parseInt(read(BAR_CHART_PROPERTY_FILE_PATH,
                "bar_chart_height_for_one_spec"));

        // Save it, if the pi-chart directory is not there create it
        File directory = new File(barChartDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        File BarChart = new File(getSavedBarChartImagePath());
        try {
            ChartUtilities.saveChartAsPNG(BarChart, barChart, width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getSavedBarChartImageName() {
        return BAR_CHART_IMAGE_NAME;
    }

    public static String getSavedBarChartImagePath() {
        return CURRENT_DIRECTORY + FILE_SEPARATOR + BAR_CHART_IMAGE_PATH + FILE_SEPARATOR + BAR_CHART_IMAGE_NAME + ".PNG";
    }
}
