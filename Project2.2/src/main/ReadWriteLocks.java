package main;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReadWriteLocks {
     int readers,writers,waitingReaders,waitingWriters;
    final ReentrantLock lock = new ReentrantLock();
     final Condition readable = lock.newCondition();
     final Condition writable = lock.newCondition();
    private volatile Thread writeLockHolder;
   void lockRead(){
        lock.lock();
        try {
          while ((writers != 0 && writeLockHolder != Thread.currentThread()) /*|| waitingWriters != 0*/){
              ++ waitingReaders;
              readable.awaitUninterruptibly();
              --waitingReaders;
          }++readers;
        }finally {
        lock.unlock();
        }
    }

    void writeLock(){
        lock.lock();
        try {
           while ((writers != 0 && writeLockHolder != Thread.currentThread())  || readers !=0){
               ++waitingWriters;
               writable.awaitUninterruptibly();
               --waitingWriters;
           }
           writers = 1;
           writeLockHolder = Thread.currentThread();
        }finally {
        lock.unlock();
        }
    }

    void unlockRead(){
      lock.lock();
      try {
          if(--readers == 0){
              if(waitingWriters!=0){
                  writable.signal();
              }else {
                  readable.signalAll();
              }
          }
      }finally {
        lock.unlock();
      }
    }

    void unlockWrite(){
        lock.lock();
        try {
            writers = 0;
            writeLockHolder = null;
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
