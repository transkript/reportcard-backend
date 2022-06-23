package com.transkript.reportcard.business.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.transkript.reportcard.api.dto.SchoolSettingsDto;
import com.transkript.reportcard.config.constants.AppConstants;
import lombok.Getter;
import lombok.Setter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@Getter
@Setter
public class SettingsUtil {
    public static ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper =  new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        return objectMapper;
    }

    public static void writeSettings(SchoolSettingsDto settingsDto) {
        ObjectMapper om = getObjectMapper();
        try (FileOutputStream fos = new FileOutputStream(AppConstants.settingsFile)){
            om.writeValue(fos, settingsDto);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static SchoolSettingsDto readSettings() {
        ObjectMapper om = getObjectMapper();
        try (FileInputStream fis = new FileInputStream(AppConstants.settingsFile)) {
            return om.readValue(fis, SchoolSettingsDto.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
