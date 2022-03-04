package com.blockingVsNonBlocking.jmh;
import java.util.ArrayList;

public class CollegeClass {
    private final String className;
    private final int classId;
    private final ArrayList<BlockingStudent> registeredBlockingStudents;
    private final ArrayList<nonBlockingStudent> registeredNonBlockingStudents;


    public CollegeClass(String className, int classId) {
        this.className = className;

        this.classId = classId;
        //peak at class 30 should suffice when each
        this.registeredBlockingStudents = new ArrayList<BlockingStudent>();
        this.registeredNonBlockingStudents = new ArrayList<nonBlockingStudent>();
    }

    public int getClassId() {
        return classId;
    }

    public ArrayList<BlockingStudent> getRegisteredBlockingStudents() {
        return registeredBlockingStudents;
    }


    public String getClassName() {
        return className;
    }

    public ArrayList<nonBlockingStudent> getRegisteredNonBlockingStudents() {
        return registeredNonBlockingStudents;
    }

    public void addRegisteredBlockingStudents(BlockingStudent student) {
        registeredBlockingStudents.add(student);
    }

    public void addRegisteredStudentNonBlocking(nonBlockingStudent student){
        registeredNonBlockingStudents.add(student);
    }

    @Override
    public String toString(){
        String classInfo = " Class name: " + className + " Class ID: " + classId;
        return classInfo;
    }

//    public String toStringNBD(){
//
//    }
}
