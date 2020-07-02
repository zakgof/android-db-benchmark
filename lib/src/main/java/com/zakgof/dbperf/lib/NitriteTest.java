package com.zakgof.dbperf.lib;

import android.content.Context;
import android.util.Log;

import com.orhanobut.hawk.Hawk;

import org.dizitart.no2.FindOptions;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.NitriteCollection;
import org.dizitart.no2.NitriteId;
import org.dizitart.no2.objects.ObjectFilter;
import org.dizitart.no2.objects.ObjectRepository;
import org.dizitart.no2.objects.filters.ObjectFilters;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class NitriteTest implements PerfTest {

    private final Nitrite nitrite;
    private final ObjectRepository<Person> repository;
    private final String path;

    public NitriteTest(Context context) {
        path = context.getFilesDir().getPath() + "/nitrite.db";
        new File(path).delete();
        nitrite = Nitrite.builder()
                .filePath(path)
                .openOrCreate();
        repository = nitrite.getRepository(Person.class);

        Person person = new Person(1L);
        repository.insert(person);
        Person restoredperson = repository.find(ObjectFilters.eq("lastName", person.getLastName())).firstOrDefault();
        Log.e("TESTTEST", "" + restoredperson);
    }

    @Override
    public String name() {
        return "nitrite";
    }

    @Override
    public void insertRecords() {
        for (Person person : Data.PERSONS) {
            repository.insert(person);
        }
    }

    @Override
    public void getByKey() {
        for (String key : Data.KEYS) {
            Person restoredperson = repository.find(ObjectFilters.eq("lastName", key)).firstOrDefault();
            Log.e("RESTORED", "" + restoredperson);
        }
    }

    @Override
    public void clean() {
        repository.remove(ObjectFilters.ALL);
    }

    @Override
    public void close() {
        nitrite.close();
        new File(path).delete();
    }
}
