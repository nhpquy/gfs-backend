package com.gfs.domain.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileUtils {

    public static String getTemplate(String templateName) {
        ApplicationContext appContext = new ClassPathXmlApplicationContext();
        try {
            InputStream emailResourceStream = appContext.getResource(templateName).getInputStream();
            return getStringFromInputStream(emailResourceStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ((ConfigurableApplicationContext) appContext).close();
        return null;
    }

    // convert InputStream to String
    private static String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();
    }
}
