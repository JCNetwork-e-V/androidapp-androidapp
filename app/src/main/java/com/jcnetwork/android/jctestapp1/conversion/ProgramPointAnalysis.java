package com.jcnetwork.android.jctestapp1.conversion;

import android.util.Log;

import com.jcnetwork.android.jctestapp1.models.ProgramPoint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * Class to make sense of the list of ProgramPoints received
 * e.g. how many days total? -> for layout considerations
 */

public class ProgramPointAnalysis {
    // For logging
    private static String LOG_TAG = "ProgramPointAnalysis";

    // To convert String to Date
    public static Date getDateFromString(String dateString) throws ParseException {
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return myFormat.parse(dateString);
    }

    // To convert Date to String
    private static String getStringFromDate(Date date) throws ParseException {
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
        String string = myFormat.format(date);
        Log.i(LOG_TAG, string);
        return string;
    }

    /**
     * Method to calculate number of days for the "Days"
     * @param list is the list of programpoints for these "Days"
     * @return integer number i.e. number of days
     * IMP: can be used directly as a count-down e.g. 1 day left the day before
     * HOWEVER: you need to add one day if you want the total duration
     */
    public static float getNumberOfDays(List<ProgramPoint> list) throws ParseException {
        // Get first element
        String beginDate = list.get(0).getBegin();
        Log.i(LOG_TAG, "begin" + beginDate);

        // Get last element
        String endDate = list.get(list.size() - 1).getEnd();
        Log.i(LOG_TAG, "end" + endDate);

        // Convert
        Date beginInDate = getDateFromString(beginDate);
        Date endInDate = getDateFromString(endDate);

        // Get difference
        long differenceInDate = endInDate.getTime() - beginInDate.getTime();

        // Convert difference to days
        float daysBetweenV1 = TimeUnit.DAYS.convert(differenceInDate, TimeUnit.MILLISECONDS);
        Log.i(LOG_TAG, "days" + daysBetweenV1);
        Log.i(LOG_TAG, "version 2 days" + Math.round((beginInDate.getTime() - endInDate.getTime()) / (double) 86400000));
        //((endInDate.getTime() - beginInDate.getTime()) / (1000 * 60 * 60 * 24));

        return daysBetweenV1;
    }

    /**
     * Method to return list of events for a specific day based on the position
     * @param fullList full list of events
     * @param position indexed 0
     * @return List of Daily Events
     */
    public static List<ProgramPoint> getProgramForDayY(List<ProgramPoint> fullList, int position) throws ParseException {
        // Initialize list as default full list
        List<ProgramPoint> dayProgramPoints = new ArrayList<>();

        // Get Date from first day as a String
        String firstDayString = fullList.get(0).getBegin();
        // Convert to Date
        Date firstDate = getDateFromString(firstDayString);

        // Based on position get the Date e.g. 0 -> stay with first Date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(firstDate);
        calendar.add(Calendar.DAY_OF_MONTH, position); // here we add the number of days e.g. 0 for position 0
        String positionDate = getStringFromDate(calendar.getTime()); // converted back into string to search the program for this date

        // Proceed by looping through all program and checking if date matches the day; if it does append to list
        for (int i = 0; i < fullList.size(); i++) {
            // Get program at position i
            if (fullList.get(i).getBegin().contains(positionDate)) {
                // add Program to list
                dayProgramPoints.add(fullList.get(i));
            }
        }

        // Return list of program points for the day
        return dayProgramPoints;
    }

    /**
     * Method to get day of Week e.g. "Friday" given these two parameters
     * @param fullList full program
     * @param position position of tab e.g. 0 -> Day 1 -> Thursday
     */
    public static String getDayOfWeek(List<ProgramPoint> fullList, int position) throws ParseException {
        // Get Date from first day as a String
        String firstDayString = fullList.get(0).getBegin();
        // Convert to Date
        Date firstDate = getDateFromString(firstDayString);

        // Based on position get the Date e.g. 0 -> stay with first Date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(firstDate);
        calendar.add(Calendar.DAY_OF_MONTH, position); // here we add the number of days e.g. 0 for position 0

        // Specify format you want; here full day e.g. "EEEE" for "Friday"; "EE" for "FR."
        SimpleDateFormat weekDayFormat = new SimpleDateFormat("EEEE", Locale.GERMANY);

        // Apply format to date
        return weekDayFormat.format(calendar.getTime());
    }

    /**
     * Method to return a nice string regarding time of the current ProgramPoint from beginning to end
     * @param begin is the String indicating the start of the ProgramPoint e.g. "2019-11-21 10:00:00"
     * @param end is the String indicating the end of the ProgramPoint e.g. "2019-11-21 16:00:00"
     * @return a nice String like this: "10:00 Uhr - 16:00 Uhr"
     */
    public static String getTimeStringFromBeginAndEnd(String begin, String end) throws ParseException {
        // Convert the strings to dates
        Date beginDate = getDateFromString(begin);
        Date endDate = getDateFromString(end);

        // Make format to return only time
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.GERMANY);

        //TODO Fix this to make more lean; for now make-do solution
        return timeFormat.format(beginDate) + " Uhr - " + timeFormat.format(endDate) + " Uhr";
    }

    //TODO return the position in the list of programs that makes the current time
    public static int getCurrentProgramPosition(List<ProgramPoint> fullList) throws ParseException {
        // Get calendar instance
        Calendar calendar = Calendar.getInstance();
        Log.i(LOG_TAG, "current time" + calendar.getTime().toString()); // e.g. Mon Oct 19 23:00:49 GMT+02:00 2020

        // Checking list
        Log.i(LOG_TAG, "size of full list" + String.valueOf(fullList.size()));
        Log.i(LOG_TAG, "first time of full list " + fullList.get(0).getBegin()); // e.g. Sun Nov 24 16:30:00 GMT+01:00 2019
        Log.i(LOG_TAG, "last time of full list " + fullList.get(fullList.size() - 1).getBegin()); // e.g. Sun Nov 24 16:30:00 GMT+01:00 2019

        // Initialize list
        List<Calendar> calendars = new ArrayList<>();
        // Make list of begin times
        for (int i = 0; i < fullList.size(); i++) {
            Calendar newCal = Calendar.getInstance();
            // Get beginning as string
            String beginString = fullList.get(i).getBegin();
            // Convert to Date
            Date beginDate = getDateFromString(beginString);
            // Set calendar to date
            newCal.setTime(beginDate);
            calendars.add(i, newCal);
        }
        Log.i(LOG_TAG, "first time of program point one " + calendars.get(0).getTime().toString()); // e.g. Thu Nov 21 10:00:00 GMT+01:00 2019
        Log.i(LOG_TAG, "first time of program point last " + calendars.get(calendars.size()-1).getTime().toString()); // e.g. Sun Nov 24 16:30:00 GMT+01:00 2019


        // TODO Delete later; this is for testing purposes only: set date to date that is within program
        Calendar currentCalendar = Calendar.getInstance();
//        currentCalendar.setTime(calendars.get(8).getTime()); // TODO Delete later
//        currentCalendar.add(Calendar.MINUTE, 96);
//        currentCalendar.setTime(calendars.get(0).getTime());
//        currentCalendar.add(Calendar.DAY_OF_WEEK, -1); // used to make the day before the event begins

        Log.i(LOG_TAG, "Current calendar time is" + currentCalendar.getTime().toString());

        Log.i(LOG_TAG, "size is" + String.valueOf(calendars.size())); //24 events OK!
        // Alt: System.currentTimeMillis() > calendars.get(i).getTime()

        // Position default
        int position = 0; // as default

//        // TODO Give back -1 if event is after last event and change layout to "thank you for participating in the events!"
//        Calendar endCal = Calendar.getInstance();
//        endCal.setTime(getDateFromString(fullList.get(fullList.size()-1).getEnd()));
//        if (currentCalendar.getTime().after(endCal.getTime())) {
//            // Send back -1 as position to show nothing in widget
//            Log.i(LOG_TAG, "current cal after last event time");
//            position = 5; //TODO Delete after implementing logic in ProgramWidget
//            //position = -1;
//        }



        // Return appropriate position --> //TODO Check during testing!
        for (int i = 0; i < calendars.size(); i++) {
            Log.i(LOG_TAG, "Looping");
            Calendar positionCal = Calendar.getInstance();
            positionCal.setTime(calendars.get(i).getTime());
            // CASE ONE: For case i = 0: you can't compare // WORKS!
            if (i == 0) {
                // Special case; see if time is before this event, if so return as position
                if (currentCalendar.getTime().before(positionCal.getTime())) {
                    // Return i as position
                    Log.i(LOG_TAG, "current time is before program begins");
                    position = i;
                }
            } else {
                // Check CASE THREE;
                // CASE THREE: last element // WORKS!
                if (i == calendars.size()-1) {
                    // Return this as position (even after it passed, last event will be visible)
                    if (currentCalendar.getTime().after(calendars.get(calendars.size() -1).getTime())) {
                        Log.i(LOG_TAG, "current time is after program ends or still after beginning time of last event");
                        position = i;
                    }
                } else {
                    // Check CASE TWO
                    // Create a next date calendar for the next event and add 30 min buffer
                    Calendar nextPositionCal = Calendar.getInstance();
                    nextPositionCal.setTime(calendars.get(i + 1).getTime());
                    nextPositionCal.add(Calendar.MINUTE, -30);
                    Log.i(LOG_TAG, "Next position event time is " + nextPositionCal.getTime().toString());

                    // CASE TWO: Comparisons are possible, so compare current time with positionCal and nextPositionCal
                    if (currentCalendar.getTime().after(positionCal.getTime()) && currentCalendar.getTime().before(nextPositionCal.getTime())) {
                        Log.i(LOG_TAG, "current time is within program");
                        position = i;
                    }
                    // EXTREME CASE: Calender time equals event time
                    if (currentCalendar.getTime().equals(positionCal.getTime())) {
                        Log.i(LOG_TAG, "current time is starting time of new program");
                        // Return the position
                        position = i;
                    }
                    // Case that next event is under 30 min away, return next position
                    if (currentCalendar.getTime().after(positionCal.getTime()) && currentCalendar.getTime().after(nextPositionCal.getTime())) {
                        // return position of next event
                        position = i + 1;
                    }
                }
            }
        }
        Log.i(LOG_TAG, "Returned position will be " + String.valueOf(position));
        // Return position
        return position;
    }

}
