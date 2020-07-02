package com.zakgof.androidperf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.zakgof.dbperf.lib.HawkTest;
import com.zakgof.dbperf.lib.NitriteTest;
import com.zakgof.dbperf.lib.PaperTest;
import com.zakgof.dbperf.lib.PerfTest;
import com.zakgof.dbperf.lib.RoomTest;
import com.zakgof.dbperf.lib.VelvetDBBatchTest;
import com.zakgof.dbperf.lib.VelvetDBTest;

import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private ExecutorService executorService;
    private TextView report;
    private TextView log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        executorService = Executors.newFixedThreadPool(1);
        report = findViewById(R.id.report);
        log = findViewById(R.id.log);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        executorService.shutdownNow();
        super.onDestroy();
    }

    public void runVelvetdbTest(View view) {
        measuredRun(new VelvetDBTest(this));
    }

    public void runVelvetdbBatchTest(View view) {
        measuredRun(new VelvetDBBatchTest(this));
    }

    public void runPaperdbTest(View view) {
        measuredRun(new PaperTest(this));
    }

    public void runHawkTest(View view) {
        measuredRun(new HawkTest(this));
    }

    public void runNitriteTest(View view) {
        measuredRun(new NitriteTest(this));
    }

    public void runRoomTest(View view) {
        measuredRun(new RoomTest(this));
    }

    private void measuredRun(PerfTest test) {
        executorService.submit(() -> runInThread(test));
    }

    private void runInThread(PerfTest test) {
        runOnUiThread(() -> report(test.name() + " running..."));
        try {
            long t1 = System.currentTimeMillis();
            test.insertRecords();
            long t2 = System.currentTimeMillis();
            test.getByKey();
            long t3 = System.currentTimeMillis();
            String rep = String.format(Locale.ENGLISH, "%s / insert \t: %d ms \n%s / get    \t: %d ms",
                    test.name(), (t2-t1),
                    test.name(), (t3-t2));
            runOnUiThread(() -> log(rep));
        } catch (Exception e) {
            runOnUiThread(() -> log("Error : " + e));
            Log.e("dbperf", "Error in test", e);
        } finally {
            test.close();
        }
    }

    private void report(String result) {
        report.setText(result);
    }
    private void log(String result) {
        log.setText(log.getText() + "\n" + result);
        report("");
    }

}