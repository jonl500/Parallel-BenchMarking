package main;
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
        for (int i = 0; i < 20; i++) {
        CollegeClass cc = new CollegeClass("class "+ i, i);
        classListings.put(cc.getClassId(),cc);

       }
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
