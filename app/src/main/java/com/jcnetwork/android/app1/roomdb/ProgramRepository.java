package com.jcnetwork.android.app1.roomdb;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.jcnetwork.android.app1.models.ProgramPoint;

import java.util.List;

/**
 * This is the repository
 * It abstracts access to multiple datasources and provides a clean API for data access
 * between Dao and Network
 * It manages queries and allows for multiple backends
 * e.g. Fetch data from a network or use cached results from the database
 */

public class ProgramRepository {

    private ProgramPointDao myDao;
    private LiveData<List<ProgramPoint>> mProgram;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    ProgramRepository(Application application) {
        ProgramDatabase db = ProgramDatabase.getDatabase(application);
        myDao = db.myDao();
        mProgram = myDao.getAllProgramPointsByIndex();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<ProgramPoint>> getProgram() {
        return mProgram;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(ProgramPoint programPoint) {
        ProgramDatabase.databaseWriteExecutor.execute(() -> {
            myDao.insert(programPoint);
        });
    }

}
