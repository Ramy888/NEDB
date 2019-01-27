package com.example.android.networkdevicesdatabase.Data;

import android.provider.BaseColumns;

/**
 * Created by Rami.magdy on 25/03/2018.
 */

public class DevicesContract {

    public static final class NetDevices implements BaseColumns {
        public static final String TABLE_NAME_1 = "ciscoR";
        public static final String COLUMN_DEVICE_IMAGE_1 = "deviceImage_1";
        public static final String COLUMN_DEVICE_NAME_1 = "deviceName_1";
        public static final String COLUMN_TECH_INFO_1 = "techInfo_1";
        public static final String COLUMN_DEVICE_BRIEF_1 = "deviceBrief_1";
        public static final String COLUMN_TIMESTAMP_1 = "timestamp_1";

        public static final String TABLE_NAME_2 = "juniperR";
        public static final String COLUMN_DEVICE_IMAGE_2 = "deviceImage_2";
        public static final String COLUMN_DEVICE_NAME_2 = "deviceName_2";
        public static final String COLUMN_TECH_INFO_2 = "techInfo_2";
        public static final String COLUMN_DEVICE_BRIEF_2 = "deviceBrief_2";
        public static final String COLUMN_TIMESTAMP_2 = "timestamp_2";

        public static final String TABLE_NAME_3 = "huaweiR";
        public static final String COLUMN_DEVICE_IMAGE_3 = "deviceImage_3";
        public static final String COLUMN_DEVICE_NAME_3 = "deviceName_3";
        public static final String COLUMN_TECH_INFO_3 = "techInfo_3";
        public static final String COLUMN_DEVICE_BRIEF_3 = "deviceBrief_3";
        public static final String COLUMN_TIMESTAMP_3 = "timestamp_3";
    }
}