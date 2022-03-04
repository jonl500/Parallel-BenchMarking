package com.blockingVsNonBlocking.jmh;
import java.util.Collection;
import java.util.concurrent.*;
public class NonBlockingDataStructure {
    private final ConcurrentHashMap<Integer, CollegeClass>  classListings;

    public NonBlockingDataStructure(){
        classListings = new ConcurrentHashMap<>();
    }

    public ConcurrentHashMap<Integer, CollegeClass> getClassListings() {
        return classListings;
    }

    public void setUpClasses(){
       // for (int i = 0; i < 20; i++) {
        CollegeClass cc = new CollegeClass("class "+ 1, 1);
        classListings.put(cc.getClassId(),cc);
        CollegeClass cc1 = new CollegeClass("class "+ 2, 2);
        classListings.put(cc.getClassId(),cc);
        CollegeClass cc2 = new CollegeClass("class "+ 3, 3);
        classListings.put(cc.getClassId(),cc);
        CollegeClass cc3 = new CollegeClass("class "+ 4, 4);
        classListings.put(cc.getClassId(),cc);
        CollegeClass cc4 = new CollegeClass("class "+ 5, 5);
        classListings.put(cc.getClassId(),cc);
        CollegeClass cc5 = new CollegeClass("class "+ 6, 6);
        classListings.put(cc.getClassId(),cc);
        CollegeClass cc6 = new CollegeClass("class "+ 7, 7);
        classListings.put(cc.getClassId(),cc);
        CollegeClass cc7 = new CollegeClass("class "+ 8, 8);
        classListings.put(cc.getClassId(),cc);
        CollegeClass cc8 = new CollegeClass("class "+ 9, 9);
        classListings.put(cc.getClassId(),cc);
        CollegeClass cc9 = new CollegeClass("class "+ 10, 10);
        classListings.put(cc.getClassId(),cc);
        CollegeClass cc10 = new CollegeClass("class "+ 11, 11);
        classListings.put(cc.getClassId(),cc);
      //  }
    }

    public void register(nonBlockingStudent student){
        int classID = ThreadLocalRandom.current().nextInt(0,20);
        if (classListings.get(classID).getRegisteredNonBlockingStudents().size() <= 40){
        classListings.get(classID).addRegisteredStudentNonBlocking(student);
        }else {
          //  System.out.println("class is full");
        }
    }

    public CollegeClass lookupClass(int classID){
        CollegeClass cc;
        if (classListings.containsKey(classID)){
            cc = classListings.get(classID);
           // System.out.println(cc.toString());
            return cc;
        }else {
           // System.out.println("this class doesn't exist");
            return null;
        }

    }
//    public static void deregisterNonBlockingStudent(CollegeClass cc,nonBlockingStudent s){
//
//    }

}
