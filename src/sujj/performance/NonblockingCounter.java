package sujj.performance;

import java.util.concurrent.atomic.AtomicInteger;

public class NonblockingCounter {
    private AtomicInteger value = new AtomicInteger(0) ;
 
    public int getValue() {
        return value.get();
    }
 
    public int increment() {
        int v;
        do {
            v = value.get();
        }
         while (!value.compareAndSet(v, v + 1));
        Util.cal1();
        return v + 1;
    }
}
