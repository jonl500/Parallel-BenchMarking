package main;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class BlockingDataStructure {
    private final HashMap<Integer, CollegeClass> classRegistration;
    private ReadWriteLocks rwl = new ReadWriteLocks();
    public BlockingDataStructure(){

        classRegistration = new HashMap<Integer, CollegeClass>();
    }

    public HashMap<Integer, CollegeClass> getClassRegistration() {
        return classRegistration;
    }

    public void setUpClasses(){
        //ReadWriteLocks.writeLock();
        for (int i = 0; i < 20; i++) {
            CollegeClass cc = new CollegeClass("class "+ i, i);
            classRegistration.put(cc.getClassId(),cc);
        }
//        for (int key:classRegistration.keySet()) {
//            System.out.println(classRegistration.get(key).toString());
//        }
       // ReadWriteLocks.unlockWrite();
    }

    public CollegeClass lookupCollegeClass(int classID) throws Exception {
        rwl.lockRead();
        CollegeClass cc;
        if (classRegistration.containsKey(classID)){
            cc = classRegistration.get(classID);
           // System.out.println(cc.toString());
            rwl.unlockRead();
            return cc;
        }else {
         //   System.out.println("this class doesn't exist");
            rwl.unlockRead();
            throw new Exception("no class for this ID " + classID);
        }

    }

    public ArrayList<BlockingStudent> readClasses(int key){
        rwl.lockRead();
        ArrayList<BlockingStudent> studentList = classRegistration.get(key).getRegisteredBlockingStudents();
        for (BlockingStudent student : studentList) {
           // System.out.println(student);
        }
       rwl.unlockRead();
        return studentList;
    }



    public void puttClassRegistration(int key, CollegeClass classy){
        rwl.writeLock();
        classRegistration.put(key, classy);
        rwl.unlockWrite();
    }

    public void addStudentsToClass(BlockingStudent student) throws Exception {
        rwl.writeLock();
        int classID = ThreadLocalRandom.current().nextInt(0,20);
        CollegeClass cc = lookupCollegeClass(classID);
//        System.out.println("ClassID: " +classID);
//        System.out.println("gave class: " +cc);
//        System.out.println(" which has size: " +cc.getRegisteredBlockingStudents().size());
        cc.getRegisteredBlockingStudents().size();
        if (classRegistration.get(classID).getRegisteredBlockingStudents().size() <= 40){
        classRegistration.get(classID).addRegisteredBlockingStudents(student);
        }else {
          //  System.out.println("class is full");
        }
        rwl.unlockWrite();
    }

}
