package com.maxsoft.greporter.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Project Name    : MaxSoft-GReporter
 * Developer       : Osanda Deshan
 * Version         : 1.0.0
 * Date            : 1/28/2021
 * Time            : 11:37 PM
 * Description     :
 **/

public class PropertyReader {

    public static String read(String filePath, String propertyName) {
        FileInputStream fis = null;
        Properties prop = null;
        try {
            fis = new FileInputStream(filePath);
            prop = new Properties();
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        assert prop != null;
        return prop.getProperty(propertyName);
    }
}
