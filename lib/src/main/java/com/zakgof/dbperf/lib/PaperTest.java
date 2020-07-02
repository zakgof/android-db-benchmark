package com.zakgof.dbperf.lib;

import android.content.Context;

import com.orhanobut.hawk.Hawk;
import com.zakgof.db.velvet.IVelvetEnvironment;
import com.zakgof.db.velvet.VelvetFactory;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import io.paperdb.Paper;

public class PaperTest implements PerfTest {

    public PaperTest(Context context) {
        Paper.init(context);
    }

    public String name() {
        return "paper";
    }

    public void insertRecords() {
        for (Person person : Data.PERSONS) {
            Paper.book().write(person.getLastName(), person);
        }
    }

    @Override
    public void getByKey() {
        for (String key : Data.KEYS) {
            Person person = Paper.book().read(key);
            person.toString();
        }
    }

    @Override
    public void clean() {
        Paper.book().destroy();
    }

    @Override
    public void close() {
        Paper.book().destroy();
    }
}
