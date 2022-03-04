package com.blockingVsNonBlocking.jmh;
import java.util.concurrent.ThreadLocalRandom;

public class nonBlockingStudent implements Runnable {
    private final String name;
    private final int studentId;
    NonBlockingDataStructure nbds;
    public nonBlockingStudent(String name, int studentId, NonBlockingDataStructure nbds){
        this.name = name;
        this.studentId = studentId;
        this.nbds = nbds;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public NonBlockingDataStructure getNbds(){
        return nbds;
    }


    @Override
    public void run() {
    int randomChance = ThreadLocalRandom.current().nextInt(0,6);
    //nbds.setUpClasses();
    if (randomChance == 5){
        nbds.register(this);
        //System.out.println("student " + this.name + " registered");
    }else {
       // System.out.println("hekjrher");
       CollegeClass collegeClass = nbds.lookupClass(randomChance);
      // System.out.println(collegeClass.getClassName());
    }
     //   System.out.println("wat??");
    }
}
