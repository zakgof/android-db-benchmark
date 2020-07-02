package com.zakgof.dbperf.lib;

import android.content.Context;

import com.zakgof.db.velvet.IVelvetEnvironment;
import com.zakgof.db.velvet.VelvetFactory;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class VelvetDBBatchTest implements PerfTest {

    private final File velvetDir;
    private final IVelvetEnvironment velvetEnv;

    public VelvetDBBatchTest(Context context) {
        velvetDir = new File(context.getFilesDir(), "velvet");
        String url = "velvetdb://xodus" + velvetDir;
        velvetEnv = VelvetFactory.open(url);
    }

    @Override
    public String name() {
        return "velvetdb-batch";
    }

    @Override
    public void insertRecords() {
        velvetEnv.execute(velvet -> Defs.PERSON.batchPut(velvet, Data.PERSONS));
    }

    @Override
    public void getByKey() {
        List<Person> restoredPersons = velvetEnv.calculate(velvet -> Defs.PERSON.batchGetList(velvet, Data.KEYS));
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
