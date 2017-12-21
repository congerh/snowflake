package com.njilc.common.snowflake;

public class IdGenerator {
    private volatile static IdGenerator instance;
    private volatile static IdWorker idWorker;

    private IdGenerator() {
        idWorker = new IdWorker(getWorkerId(), getDatacenterId());
    }

    public static IdGenerator getInstance() {
        if (instance == null) {
            synchronized (IdGenerator.class) {
                if (instance == null) {
                    instance = new IdGenerator();
                }
            }
        }
        return instance;
    }

    public long generateId() {
        return idWorker.nextId();
    }

    public static long getWorkerId() {
        return 0L;
    }

    public static long getDatacenterId() {
        return 0L;
    }
}