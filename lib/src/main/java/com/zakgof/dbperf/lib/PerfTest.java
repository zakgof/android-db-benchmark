package com.zakgof.dbperf.lib;

import java.io.Closeable;
import java.io.IOException;

public interface PerfTest extends Closeable {
    String name();
    void insertRecords();
    void getByKey();
    void clean();
    void close();
}
