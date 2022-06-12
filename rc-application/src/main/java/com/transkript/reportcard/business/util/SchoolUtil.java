package com.transkript.reportcard.business.util;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;

/*
Fire ship - Java for haters
 */
public class SchoolUtil {
    public static String generateRegNo(@NotNull Long studentId, String schoolName) {
        String yearPart = getYearPart();
        String schoolPart = getSchoolPart(schoolName);
        String studentIdPart = getStudentIdPart(studentId);

        return schoolPart.concat(yearPart).concat(studentIdPart);
    }

    private static String getStudentIdPart(Long id) {
        StringBuilder idStr = new StringBuilder(String.valueOf(id));
        int maxLength = 8;
        for (int i = 0; i < maxLength - idStr.length(); i++) {
            idStr.insert(0, "0");
        }
        return idStr.toString();
    }

    private static String getSchoolPart(String schoolName) {
        StringBuilder schoolPart = new StringBuilder();
        if(schoolName.length() < 2) {
            return schoolPart.toString();
        }
        if(schoolName.split(" ").length < 2) {
            schoolPart.append(schoolName.substring(0, 2).toUpperCase());
        } else {
            String[] schoolNameSplit = schoolName.split(" ");
            for(String split: schoolNameSplit) {
                schoolPart.append(split.charAt(0));
            }
        }
        return schoolPart.toString();
    }

    private static String getYearPart() {
        int y = LocalDateTime.now().getYear();
        char[] yearArr = String.valueOf(y).toCharArray();
        return String.valueOf(yearArr[yearArr.length - 2]) + yearArr[yearArr.length - 1];
    }
}
