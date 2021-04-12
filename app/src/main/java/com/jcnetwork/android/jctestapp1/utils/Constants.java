package com.jcnetwork.android.jctestapp1.utils;

/**
 * Class to store constants in
 */

public class Constants {

    /** For shared preferences **/
    // Filename for shared preferences to hold ALL USER DATA!!!
    public final static String SHARED_PREFERENCE_FILE_NAME = "userDataStorageFile";
    // Keys for storing userdata in shared preferences
    public final static String USER_NAME_KEY = "UserNameKey";

    // Keys for ids incl. ablauf_id, cert_id, lebenslauf_id
    public static final String ABLAUF_ID = "UserProgramKey";
    public final static String USER_EMAIL = "UserEmailKey";
    public final static String USER_CERTIFICATION_ID = "UserCertificationKey";
    public final static String LEBENSLAUF_ID_KEY = "LebenslaufKey";

    // Keys for storing certification points
    public final static String GESAMT_POINTS_KEY = "GesamtPtsKey";
    public final static String CASE_POINTS_KEY = "CasePointsKey";
    public final static String EXPERIENCE_POINTS_KEY = "ExperiencePointsKey";

    // Storing keys for accessing user data
    public final static String USER_ID_KEY = "UserIdKey";
    // Storing program json
    public final static String ABLAUFSPLAN_JSON_RESULT_KEY = "AblaufsplanJSONKey";

    // Default strings for keys is empty
    public final static String EMPTY_STRING_DEFAULT = "";

    // Filename for shared preferences to hold the boolean whether the user is logged in or not
    public final static String LOGGED_IN_KEY = "SHARED_PREFERENCE_KEY_LOGGED_IN_BOOLEAN";

    // Use this to tell login activity whether the user wants to login (for first time) or logout
    public static final String OPEN_LOGIN_INTENTION = "OPEN_LOGIN_INTENTION";
    public static final String LOGOUT = "PLEASE_LOG_ME_OUT";
    public static final String LOGIN = "PLEASE_LOG_ME_IN";

}
