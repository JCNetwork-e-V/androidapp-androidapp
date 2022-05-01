package com.jcnetwork.android.app1.roomdb;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.jcnetwork.android.app1.models.ProgramPoint;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This is the room database (layer on top of SQLite Db)
 * The @ProgramPointDao is used to interact with this database
 */

// false as we don't want other apps to gain access to this db
@Database(entities = {ProgramPoint.class}, version = 1, exportSchema = false)
public abstract class ProgramDatabase extends RoomDatabase {

    // Set up DAO (data access object)
    public abstract ProgramPointDao myDao();

    // This instance of the db ensures we don't keep creating new ones
    private static volatile ProgramDatabase INSTANCE;

    /** This sets up the async. threads to run in the background for db operations **/
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    /** This method returns the db **/
    static ProgramDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ProgramDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ProgramDatabase.class, "program") // this is the name of the db
                            .addCallback(mRoomDatabaseCallback) // adds the callback below
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    /** Method to populate db **/
    private static RoomDatabase.Callback mRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

//            // If you want to keep data through app restarts,
//            // comment out the following block
//            databaseWriteExecutor.execute(() -> {
//                // Populate the database in the background.
//                // If you want to start with more words, just add them.
//                ProgramPointDao dao = INSTANCE.myDao();
//                dao.deleteAll();
//
//                /** Here we can set example data for testing **/
//
////                Word word = new Word("Hello");
////                dao.insert(word);
////                word = new Word("World");
////                dao.insert(word);
//            });
        }
    };
}
