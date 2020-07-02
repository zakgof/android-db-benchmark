package com.zakgof.dbperf.benchmark;

import android.content.Context;
import android.util.Log;

import androidx.benchmark.junit4.BenchmarkRule;
import androidx.benchmark.BenchmarkState;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.zakgof.dbperf.lib.HawkTest;
import com.zakgof.dbperf.lib.NitriteTest;
import com.zakgof.dbperf.lib.PaperTest;
import com.zakgof.dbperf.lib.PerfTest;
import com.zakgof.dbperf.lib.RoomTest;
import com.zakgof.dbperf.lib.VelvetDBBatchTest;
import com.zakgof.dbperf.lib.VelvetDBTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.function.Function;

@RunWith(AndroidJUnit4.class)
public class ExampleBenchmark {

    @Rule
    public BenchmarkRule mBenchmarkRule = new BenchmarkRule();

    @Test
    public void sleep5() {
        final BenchmarkState state = mBenchmarkRule.getState();
        while (state.keepRunning()) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void sleep2() {
        final BenchmarkState state = mBenchmarkRule.getState();
        while (state.keepRunning()) {
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void roomInsert() {
        testInsert(RoomTest::new);
    }

    @Test
    public void rootGet() {
        testGet(RoomTest::new);
    }

    @Test
    public void paperInsert() {
        testInsert(PaperTest::new);
    }

    @Test
    public void paperGet() {
        testGet(PaperTest::new);
    }

    @Test
    public void hawkInsert() {
        testInsert(HawkTest::new);
    }

    @Test
    public void hawkGet() {
        testGet(HawkTest::new);
    }

    @Test
    public void NO2Insert() {
        testInsert(NitriteTest::new);
    }

    @Test
    public void NO2Get() {
        testGet(NitriteTest::new);
    }

    @Test
    public void velvetdbInsert() {
        testInsert(VelvetDBTest::new);
    }

    @Test
    public void velvetdbGet() {
        testGet(VelvetDBTest::new);
    }

    @Test
    public void velvetdbBatchInsert() {
        testInsert(VelvetDBBatchTest::new);
    }

    @Test
    public void velvetdbBatchGet() {
        testGet(VelvetDBBatchTest::new);
    }

    private void testInsert(Function<Context, ? extends  PerfTest> testCreator) {
        Context context = ApplicationProvider.getApplicationContext();
        final BenchmarkState state = mBenchmarkRule.getState();
        try (PerfTest test = testCreator.apply(context)) {
            while (state.keepRunning()) {
                test.insertRecords();
                state.pauseTiming();
                test.clean();
                state.resumeTiming();
            }
        }
    }

    private void testGet(Function<Context, ? extends  PerfTest> testCreator) {
        Context context = ApplicationProvider.getApplicationContext();
        final BenchmarkState state = mBenchmarkRule.getState();
        try (PerfTest test = testCreator.apply(context)) {
            test.insertRecords();
            while (state.keepRunning()) {
                test.getByKey();
            }
        }
    }


}