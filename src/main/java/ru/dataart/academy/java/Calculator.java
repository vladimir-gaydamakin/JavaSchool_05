package ru.dataart.academy.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class Calculator {

    /**
     * @param zipFilePath -  path to zip archive with text files
     * @param character   - character to find
     * @return - how many times character is in files
     */
    public Integer getNumberOfChar(String zipFilePath, char character) {
        int result = 0;

        try (ZipFile input = new ZipFile(zipFilePath)) {
            Enumeration<? extends ZipEntry> entries = input.entries();
            while(entries.hasMoreElements()){
                ZipEntry entry = entries.nextElement();
                try (BufferedReader br = new BufferedReader(new InputStreamReader(input.getInputStream(entry)))) {
                    int ch;
                    while ((ch = br.read()) != -1) {
                        if ((char) ch == character) {
                            result++;
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * @param zipFilePath - path to zip archive with text files
     * @return - max length
     */

    public Integer getMaxWordLength(String zipFilePath) {
        int max = 0;
        try (ZipFile input = new ZipFile(zipFilePath)) {
            Enumeration<? extends ZipEntry> entries = input.entries();
            while(entries.hasMoreElements()){
                ZipEntry entry = entries.nextElement();
                try (Scanner sc = new Scanner(new InputStreamReader(input.getInputStream(entry)))) {
                    while (sc.hasNext()) {
                        int length = sc.next().length();
                        if (length > max) {
                            max = length;
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return max;
    }

}
