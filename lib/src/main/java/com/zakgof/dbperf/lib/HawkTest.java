package com.zakgof.dbperf.lib;

import android.content.Context;

import com.orhanobut.hawk.Hawk;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import io.paperdb.Paper;

public class HawkTest implements PerfTest {

    public HawkTest(Context context) {
        Hawk.init(context).build();
    }

    @Override
    public String name() {
        return "hawk";
    }

    @Override
    public void insertRecords() {
        for (Person person : Data.PERSONS) {
            Hawk.put(person.getLastName(), person);
        }
    }

    @Override
    public void getByKey() {
        for (String key : Data.KEYS) {
            Person restoredperson = Hawk.get(key);
            restoredperson.toString();
        }
    }

    @Override
    public void clean() {
        Hawk.deleteAll();
    }

    @Override
    public void close() {
        Hawk.deleteAll();
        Hawk.destroy();
    }
}
