package com.liucan.reference;

import com.liucan.pojo.Person;
import org.springframework.stereotype.Component;

import java.lang.ref.*;
import java.util.HashMap;

/**
 * @author liucan
 * @version 18-11-19
 */
@Component
public class Reference1 {

    public void example() {
        ReferenceQueue<Person> referenceQueue = new ReferenceQueue<>();

        //StrongReference
        Person person = new Person();

        //SoftReference
        SoftReference<Person> softReference = new SoftReference<>(new Person(), referenceQueue);

        //WeakReference
        WeakReference<Person> weakReference = new WeakReference<>(new Person(), referenceQueue);

        //PhantomReference
        PhantomReference<Person> personPhantomReference = new PhantomReference<>(new Person(), referenceQueue);

        referenceQueueTest();
    }

    private void referenceQueueTest() {
        HashMap<WeakReference<Integer>, Integer> weakHashMap = new HashMap<>();
        ReferenceQueue<Integer> referenceQueue = new ReferenceQueue<>();

        new Thread(() -> {
            try {
                Reference reference;
                while ((reference = referenceQueue.remove()) != null) {
                    weakHashMap.remove(reference);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        for (int i = 0; i < 100; i++) {
            weakHashMap.put(new WeakReference<>(i, referenceQueue), i);
        }
        System.gc(); //help gc
    }
}
