package com.njilc.common.snowflake;

import junit.framework.TestCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Set;

public class TestIdGenerator extends TestCase {
    private static final Logger log = LogManager.getLogger(TestIdGenerator.class);

    public void testGenerateId() {
        for (int i = 0; i < 10000; i++) {
            log.info("id: " + IdGenerator.getInstance().generateId());
        }
    }

    static class IdWorkerThread implements Runnable {
        private Set<Long> set;

        public IdWorkerThread(Set<Long> set) {
            this.set = set;
        }

        @Override
        public void run() {
            while (true) {
                long id = IdGenerator.getInstance().generateId();
                log.info("id: " + id);
                if (!set.add(id)) {
                    log.error("duplicate: " + id);
                }
            }
        }
    }

    public void testGenerateIdThread() {
        Set<Long> set = new HashSet<Long>(100000);
        for (int i = 0; i < 9; i++) {
            Thread thread1 = new Thread(new IdWorkerThread(set));
            thread1.setDaemon(true);
            thread1.start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}