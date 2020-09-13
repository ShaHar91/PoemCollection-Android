package com.shahar91.poems;

import java.util.regex.Pattern;

public class Constants {
    public final static int CURRENT_USER_ID = 0;

    public final static int REQUEST_CODE_NEW_USER = 100;
    public final static int REQUEST_CODE_ADD_POEM = 101;

    public final static long DEFAULT_REFRESH_LAYOUT_DURATION = 1500; // 1.5 seconds

    public final static Pattern PASSWORD_PATTERN = Pattern.compile("[a-zA-Z0-9\\!\\@\\#\\$]{6,24}");
}
