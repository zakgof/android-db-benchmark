package com.zakgof.dbperf.lib;

import android.content.Context;

import com.zakgof.db.velvet.IVelvetEnvironment;
import com.zakgof.db.velvet.VelvetFactory;

import java.io.File;
import java.util.Arrays;

public class VelvetDBTest implements PerfTest {

    private final File velvetDir;
    private final IVelvetEnvironment velvetEnv;

    public VelvetDBTest(Context context) {
        velvetDir = new File(context.getFilesDir(), "velvet");
        String url = "velvetdb://xodus" + velvetDir;
        velvetEnv = VelvetFactory.open(url);
    }

    @Override
    public String name() {
        return "velvetdb";
    }

    @Override
    public void insertRecords() {
        for (Person person : Data.PERSONS) {
            velvetEnv.execute(velvet -> Defs.PERSON.put(velvet, person));
        }
    }

    @Override
    public void getByKey() {
        for (String key : Data.KEYS) {
            Person restoredPerson = velvetEnv.calculate(velvet -> Defs.PERSON.get(velvet, key));
            restoredPerson.toString();
        }
    }

    @Override
    public void clean() {
        velvetEnv.execute(velvet -> Defs.PERSON.batchDeleteKeys(velvet, Defs.PERSON.batchGetAllKeys(velvet)));
    }

    @Override
    public void close() {
        velvetEnv.close();
        Arrays.stream(velvetDir.listFiles()).forEach(File::delete);
    }
}
