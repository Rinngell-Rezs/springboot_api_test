package com.project.springboot_api_test.helper;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateHelper {
    public static DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    // Madagascar uses East Africa Time (EAT), which has the id "Africa/Addis_Ababa"
    public static ZoneId MADAGASCAR_TZ = ZoneId.of("Africa/Addis_Ababa");
}
