package com.liucan.reference;

import com.liucan.pojo.Person;
import org.springframework.stereotype.Component;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * @author liucan
 * @version 18-11-19
 */
@Component
public class Reference {

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
        WeakReference wr;
        try {
            while ((wr = (WeakReference) referenceQueue.remove()) != null) {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
