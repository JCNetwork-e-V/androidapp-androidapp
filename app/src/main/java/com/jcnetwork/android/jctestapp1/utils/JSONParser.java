package com.jcnetwork.android.jctestapp1.utils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Class to parse Default JSON for now
 * TODO Delete later when you replace with Retrofit + GSON library
 */

public class JSONParser {

    // TODO Create models e.g. city, hotel, program, etc.

    // Default JSON String
    public final static String JSON_DEFAULT_STRING = "{ 'uname': 'Mein Name'," +
            "'jcnetwork_description': 'Beschreibung JCNetwork'," +
            "'jcnetwork_img': 'Jcnetwork Image'," +
            "'city': {" +
            "'name': 'BeispielStadt'," +
            "'description': 'Beschreibung der Stadt'," +
            "'image': 'Stadt Bild'} }";


    // JSON Keys
    private final static String USER_NAME_KEY = "uname";
    private final static String JCNETWORK_DESCRIPTION_KEY = "jcnetwork_description";
    private final static String JCNETWORK_IMAGE = "jcnetwork_img";
    private final static String JCNETWORK_URL = "jcnetwork_url";


    // City
    private final static String CITY = "city";
    private final static String CITY_NAME_KEY = "name";
    private final static String CITY_DESCRIPTION_KEY = "description";
    private final static String CITY_IMAGE_KEY = "image";

    // Organizer
    private final static String ORGANIZER = "organizer";
    private final static String ORGANIZER_NAME = "name";
    private final static String ORGANIZER_DESCRIPTION = "description";
    private final static String ORGANIZER_IMAGE = "image";
    private final static String ORGANIZER_MAIL = "mail";
    private final static String ORGANIZER_WEBSITE = "website";

    // Hotel
    private final static String HOTEL = "hotel";
    private final static String HOTEL_NAME = "name";
    private final static String HOTEL_ADDRESS = "address";
    private final static String HOTEL_PHONE = "phone";
    private final static String HOTEL_MAIL = "mail";

    // User
    private final static String USER = "user";
    private final static String USER_CLUB = "club";
    private final static String USER_QR_ID = "qr_id";
    private final static String USER_ID = "userId";
    private final static String USER_TOTAL_POINTS = "total_points";
    private final static String USER_CASE_STUDY_POINTS = "case_study_points";
    private final static String USER_EXPERIENCE_POINTS = "experience_points";

    // Event
    private final static String EVENT = "event";
    private final static String EVENT_NAME = "name";
    private final static String EVENT_TYPE = "type";
    private final static String EVENT_DATE_FROM = "date_from";
    private final static String EVENT_DATE_TO = "date_to";

    // Program/Event
    private final static String PROGRAM = "program";
    private final static String PROGRAM_ID = "id";
    private final static String PROGRAM_INDEX = "indx";
    private final static String PROGRAM_DAYS_ID = "days_id";
    private final static String PROGRAM_DESCRIPTION = "description";
    private final static String PROGRAM_ADDRESS = "address";
    private final static String PROGRAM_START = "beginn";
    private final static String PROGRAM_END = "end";
    private final static String PROGRAM_COLOR = "color";
    private final static String PROGRAM_TITLE = "title";
    private final static String PROGRAM_SUBTITLE = "undertitle";
    private final static String PROGRAM_IMAGE = "img";



    // Getter methods
    private static JSONObject getJSONObject(String json) throws JSONException {
        return new JSONObject(json);
    }

    public static String getUserName(String json) throws JSONException {
        return getJSONObject(json).getString(USER_NAME_KEY);
    }

    // Get city info
    public static String getCityName(String json) throws JSONException {
        return getJSONObject(json).getJSONObject(CITY).getString(CITY_NAME_KEY);
    }

}
