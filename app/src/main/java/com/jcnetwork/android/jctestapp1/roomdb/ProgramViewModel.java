package com.jcnetwork.android.jctestapp1.roomdb;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.jcnetwork.android.jctestapp1.models.ProgramPoint;

import java.util.List;

/**
 * View Model
 */

public class ProgramViewModel extends AndroidViewModel {

    // Reference to repository
    private ProgramRepository mRepository;

    // Storing the full list of programpoints
    private LiveData<List<ProgramPoint>> mProgram;

    /** Constructor **/
    public ProgramViewModel (Application application) {
        super(application);
        mRepository = new ProgramRepository(application);
        mProgram = mRepository.getProgram();
    }

    /** Method to return cached list of words **/
    public LiveData<List<ProgramPoint>> getFullProgram() { return mProgram; }

    public void insert(ProgramPoint programPoint) { mRepository.insert(programPoint); }
}
