package com.blockingVsNonBlocking.jmh;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class BlockingDataStructure {
    private final HashMap<Integer, CollegeClass> classRegistration;
    
    public BlockingDataStructure(){

        classRegistration = new HashMap<Integer, CollegeClass>();
    }

    public HashMap<Integer, CollegeClass> getClassRegistration() {
        return classRegistration;
    }

    public void setUpClasses(){
        //ReadWriteLocks.writeLock();
        //for (int i = 0; i < 20; i++) {
            CollegeClass cc = new CollegeClass("class "+ 1, 1);
            classRegistration.put(cc.getClassId(),cc);
        CollegeClass cc1 = new CollegeClass("class "+ 2, 2);
        classRegistration.put(cc.getClassId(),cc);
        CollegeClass cc2 = new CollegeClass("class "+ 3, 3);
        classRegistration.put(cc.getClassId(),cc);
        CollegeClass cc3 = new CollegeClass("class "+ 4, 4);
        classRegistration.put(cc.getClassId(),cc);
        CollegeClass cc4 = new CollegeClass("class "+ 5, 5);
        classRegistration.put(cc.getClassId(),cc);
        CollegeClass cc5 = new CollegeClass("class "+ 6, 6);
        classRegistration.put(cc.getClassId(),cc);
        CollegeClass cc6 = new CollegeClass("class "+ 7, 7);
        classRegistration.put(cc.getClassId(),cc);
        CollegeClass cc7 = new CollegeClass("class "+ 8, 8);
        classRegistration.put(cc.getClassId(),cc);
        CollegeClass cc8 = new CollegeClass("class "+ 9, 9);
        classRegistration.put(cc.getClassId(),cc);
        CollegeClass cc9 = new CollegeClass("class "+ 10, 10);
        classRegistration.put(cc.getClassId(),cc);
        CollegeClass cc10 = new CollegeClass("class "+ 11, 11);
        classRegistration.put(cc.getClassId(),cc);

       // }
       // ReadWriteLocks.unlockWrite();
    }

    public CollegeClass lookupCollegeClass(int classID){
        ReadWriteLocks.lockRead();
        CollegeClass cc;
        if (classRegistration.containsKey(classID)){
            cc = classRegistration.get(classID);
           // System.out.println(cc.toString());
            ReadWriteLocks.unlockRead();
            return cc;
        }else {
         //   System.out.println("this class doesn't exist");
            ReadWriteLocks.unlockRead();
            return null;
        }

    }

    public ArrayList<BlockingStudent> readClasses(int key){
        ReadWriteLocks.lockRead();
        ArrayList<BlockingStudent> studentList = classRegistration.get(key).getRegisteredBlockingStudents();
        for (BlockingStudent student : studentList) {
           // System.out.println(student);
        }
        ReadWriteLocks.unlockRead();
        return studentList;
    }



    public void puttClassRegistration(int key, CollegeClass classy){
        ReadWriteLocks.writeLock();
        classRegistration.put(key, classy);
        ReadWriteLocks.unlockWrite();
    }

    public void addStudentsToClass(BlockingStudent student){
        ReadWriteLocks.writeLock();
        int classID = ThreadLocalRandom.current().nextInt(0,20);
        if (classRegistration.get(classID).getRegisteredNonBlockingStudents().size() <= 40){
        classRegistration.get(classID).addRegisteredBlockingStudents(student);
        }else {
          //  System.out.println("class is full");
        }
        ReadWriteLocks.unlockWrite();
    }

}
