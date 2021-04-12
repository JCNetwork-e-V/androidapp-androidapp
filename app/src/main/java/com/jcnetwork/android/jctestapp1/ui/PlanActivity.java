package com.jcnetwork.android.jctestapp1.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.jcnetwork.android.jctestapp1.R;
import com.jcnetwork.android.jctestapp1.adapters.ScheduleAdapter;
import com.jcnetwork.android.jctestapp1.conversion.ProgramPointAnalysis;
import com.jcnetwork.android.jctestapp1.models.ProgramPoint;
import com.jcnetwork.android.jctestapp1.utils.Constants;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.ParseException;
import java.util.List;

import static com.jcnetwork.android.jctestapp1.conversion.ProgramPointAnalysis.getNumberOfDays;

public class PlanActivity extends AppCompatActivity {

    // For loggin
    private final String LOG_TAG = this.getClass().getSimpleName();

    // Views
    ViewPager2 mViewPager2;
    TabLayout mTabLayout;
    ScheduleAdapter mViewAdapter;

    // Data
    List<ProgramPoint> mProgram;
    private int DurationInDays;
    // TODO Check
//    private ProgramViewModel mProgramViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO Check if there is data; if not; if there is a connection
        // TODO Think about this...the logic may be reverse: if there is no connection, then we want data, and if there isn't data...you know..
            setContentView(R.layout.activity_plan);

        // Set up support bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.color_gradient));
        }

        // Get data from shared preferences
        SharedPreferences userPreferences = getSharedPreferences(Constants.SHARED_PREFERENCE_FILE_NAME, MODE_PRIVATE);
        String programString = userPreferences.getString(Constants.ABLAUFSPLAN_JSON_RESULT_KEY, Constants.EMPTY_STRING_DEFAULT);
        // Convert to what we need
        Gson gson = new Gson();
        mProgram = gson.fromJson(programString, new TypeToken<List<ProgramPoint>>(){}.getType());

        // Get how many days we need
        assert mProgram != null;
        if (mProgram.size()>0) {
            try {
                DurationInDays = (int) getNumberOfDays(mProgram);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Log.i(LOG_TAG, "Number of days" + String.valueOf(DurationInDays));
        }


//        // Check data from opened intent
//            Intent openedIntent = getIntent();
//            if (openedIntent != null) {
//                Log.i(LOG_TAG, "opened Intent not null");
//                // get data if available
//                if (openedIntent.getParcelableArrayListExtra("SCHEDULE_KEY") != null) {
//                    // retrieve data
//                    mProgram = openedIntent.getParcelableArrayListExtra("SCHEDULE_KEY");
//                    if (mProgram != null) {
//                        Log.i(LOG_TAG, "mProgram not null");
//                        try {
//                            DurationInDays = (int) getNumberOfDays(mProgram);
//                            Log.i(LOG_TAG, "Number of days" + String.valueOf(DurationInDays));
//                        } catch (ParseException e) {
//                            e.printStackTrace();
//                        }
//
////                    Log.i(LOG_TAG, "first title of event" + mProgram.get(0).getmTitle());
////                    try {
////                        float Days = ProgramPointAnalysis.getNumberOfDays(mProgram);
////                        Log.i(LOG_TAG, "days " + Days);
////                    } catch (ParseException e) {
////                        e.printStackTrace();
////                    }
//                    }
//                }
//            }


            // Find views
            mViewPager2 = (ViewPager2) findViewById(R.id.view_pager);
            mTabLayout = (TabLayout) findViewById(R.id.tab_layout);

            //TODO Test to get only one day program
//        List<ProgramPoint> dayOneProgram = new ArrayList<>();
//        try {
//            dayOneProgram =  getProgramForDayX(mProgram, 1, "2019-11-21");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        mViewAdapter = new ScheduleAdapter(this, dayOneProgram, DurationInDays);

            // Set up ViewModel Provider //TODO Check
            // TODO Evtl. owner -> mainActivity
//        mProgramViewModel = new ViewModelProvider(this).get(ProgramViewModel.class);
//        mProgramViewModel.getFullProgram().observe(this, new Observer<List<ProgramPoint>>() {
//            @Override
//            public void onChanged(@Nullable final List<ProgramPoint> program) {
//                // Update the cached copy of the words in the adapter.
//                mViewAdapter = new ScheduleAdapter(PlanActivity.this,
//                        program,
//                        DurationInDays);
//                mProgram = (List<ProgramPoint>) mProgramViewModel.getFullProgram();
//                mViewPager2.setAdapter(mViewAdapter);
//
//            }
//        });

            // Set up viewpager
            mViewAdapter = new ScheduleAdapter(this, mProgram, DurationInDays);
            mViewPager2.setAdapter(mViewAdapter);

            //TODO Current error SQLiteConstraintException: UNIQUE constraint failed
            // Set up tablayout
            new TabLayoutMediator(mTabLayout, mViewPager2,
                    new TabLayoutMediator.TabConfigurationStrategy() {
                        @Override
                        public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                            // Set default day
                            String dayOfWeek = "Some day";
                            try {
                                // Get day of the week based on position e.g. 0 -> Day 1 -> Thursday
                                dayOfWeek = ProgramPointAnalysis.getDayOfWeek(mProgram, position);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            tab.setText(dayOfWeek);
                        }
                    }).attach();



    }
}

// TODO Set up Event Class
//    var id:Int = 0
//    var indx:Int = 0
//    var programmpunkt: String = ""
//    var ort: String = ""
//    var uhrzeit: String = ""
//    var arrow: Bool = true
//    var accentcolor: Color = Color.init(red: 51/255, green: 102/255, blue: 153/255)
//    var show: Bool = true
//    var img:String = "plenumlz"
//    var description:String = ""
//    var modal = false
//    var address = ""
//    var datetimeend:Date = Date.init()
//    var datetimebeginn:Date = Date.init()


//TODO Set up date logic and conversions
//    func getweekday(myDate:Date) -> String{
//        let formatter = DateFormatter()
//        formatter.dateFormat = "dd.MM.yyyy"
//        let str = formatter.string(from: myDate)
//
//        let weekday = ["Tag 0", "Sonntag", "Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag"]
//        let ret = weekday[Calendar.current.component(.weekday, from: myDate)] + " - " + str
//        return ret
//    }
//
//    func checkDate(date1:Date, date2:Date) -> Bool {
//        let weekday1 = getweekday(myDate: date1)
//        let weekday2 = getweekday(myDate: date2)
//
//        if weekday1 == weekday2{
//            return false
//        }else{
//            return true
//        }

// TODO Read in JSON
//         guard let jsonArray = defaults.object(forKey: "JSONResponse") as? [String : Any] else { return }
//
//        self.defaults.set(String(jsonArray["uname"] as? String ?? ""), forKey: "Name")
//
//        guard let title = jsonArray["programm"] as? [[String: Any]] else { return }
//
//
//
//        for point in title{
//            let uhrzeit:String = (point["beginn"] as! String).substring(from: 11).substring(to: 5) + " Uhr - " + (point["end"] as! String).substring(from: 11).substring(to: 5) + " Uhr"
//            let formatter = DateFormatter()
//            formatter.dateFormat = "yyyy-MM-dd HH:mm:ss"
//            let dte = formatter.date(from: point["end"] as? String ?? "1000-10-10 10:10:10") ?? Date.init()
//            let dtb = formatter.date(from: point["beginn"] as? String ?? "1000-10-10 10:10:10") ?? Date.init()
//
//            let currentBox = BoxStruct(id: Int(point["id"] as! String) ?? 1, indx: point["indx"] as! Int , programmpunkt: point["title"] as! String, ort: point["undertitle"] as! String, uhrzeit: uhrzeit, arrow: true, accentcolor: colorgetter(col: point["color"] as? String ?? "white"), img: point["img"] as? String ?? "plenumlz", description: point["description"] as! String, address: point["address"] as? String ?? "123", datetimeend: dte, datetimebeginn: dtb)

