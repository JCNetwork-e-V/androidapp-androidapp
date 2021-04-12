package com.jcnetwork.android.jctestapp1.roomdb;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.jcnetwork.android.jctestapp1.models.ProgramPoint;

import java.util.List;

/**
 * This is the interface to interact with the room db
 * DAO = data access object
 * It validates SQL at compile time
 * The annotations e.g. insert are used to make common db operations
 * This is basically a clean API for what we do in our code
 *
 * Created on 12.10.2020 by Layla Rohkohl
 */

@Dao
public interface ProgramPointDao {

    /**
     * Available annotations for row operations
     * Insert
     * Delete
     * Update
     */

    // TODO Check what conflict strategy we want e.g. replace or ignore i.e. allow for duplication
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ProgramPoint programPoint);

    // This is used to delete everything from the database e.g. upon logout
    @Query("DELETE FROM program")
    void deleteAll();

    // This is used to get all programpoints in the db order by index
    @Query("SELECT * FROM program ORDER BY mIndex ASC")
    LiveData<List<ProgramPoint>> getAllProgramPointsByIndex();

}
