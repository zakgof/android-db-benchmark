package com.zakgof.dbperf.lib;

import android.content.Context;

import androidx.room.Room;

import com.orhanobut.hawk.Hawk;

import java.io.File;

public class RoomTest implements PerfTest {


    private static final String DBNAME = "roomdb";
    private final PersonDao personDao;
    private final PersonDatabase db;
    private final Context context;

    public RoomTest(Context context) {
        this.context = context;
        context.deleteDatabase(DBNAME);
        db = Room.databaseBuilder(context, PersonDatabase.class, DBNAME).build();
        personDao = db.personDao();
    }

    @Override
    public String name() {
        return "room";
    }

    @Override
    public void insertRecords() {
        for (Person person : Data.PERSONS) {
            personDao.insert(person);
        }
    }

    @Override
    public void getByKey() {
        for (String key : Data.KEYS) {
            Person restoredperson = personDao.getByLastName(key);
            restoredperson.toString();
        }
    }

    @Override
    public void clean() {
        db.clearAllTables();
    }

    @Override
    public void close() {
        db.close();
        context.deleteDatabase(DBNAME);
    }
}
