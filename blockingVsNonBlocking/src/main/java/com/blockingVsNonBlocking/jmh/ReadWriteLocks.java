package com.blockingVsNonBlocking.jmh;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReadWriteLocks {
    static int readers,writers,waitingReaders,waitingWriters;
    static final ReentrantLock lock = new ReentrantLock();
    static final Condition readable = lock.newCondition();
    static final Condition writable = lock.newCondition();

   static void lockRead(){
        lock.lock();
        try {
          while (writers != 0 || waitingWriters != 0){
              ++ waitingReaders;
              readable.awaitUninterruptibly();
              --waitingWriters;
          }++readers;
        }finally {
        lock.unlock();
        }
    }

    static void writeLock(){
        lock.lock();
        try {
           while (writers!=0 || readers!=0){
               ++waitingWriters;
               writable.awaitUninterruptibly();
               --waitingWriters;
           }
           writers = 0;
        }finally {
        lock.unlock();
        }
    }

    static void unlockRead(){
      lock.lock();
      try {
          if(--readers == 0){
              if(waitingWriters!=0){
                  readable.signal();
              }else {
                  readable.signalAll();
              }
          }
      }finally {
        lock.unlock();
      }
    }

    static void unlockWrite(){
        lock.lock();
        try {
          if (waitingWriters != 0) {
              writable.signal();
          }else {
              readable.signalAll();
          }
        }finally {
            lock.unlock();
        }
    }


}
