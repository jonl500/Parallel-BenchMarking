package main;
import java.util.concurrent.ThreadLocalRandom;

public class BlockingStudent implements Runnable{
    private final String name;
    private final int studentId;
    BlockingDataStructure blockingDS;
    public BlockingStudent(String name, int studentId, BlockingDataStructure blockingDS){
     this.name = name;
     this.studentId = studentId;
     this.blockingDS = blockingDS;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }


    @Override
    public void run() {
        int randomChance = ThreadLocalRandom.current().nextInt(0,6);
       //randomChance = 5;
        // blockingDS.setUpClasses();
        if (randomChance == 5){
            try {
                blockingDS.addStudentsToClass(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //System.out.println("registered " + this.name);
        }else {
            CollegeClass collegeClass = null;
            try {
                collegeClass = blockingDS.lookupCollegeClass(randomChance);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //System.out.println(collegeClass.toString());
        }

    }
}
