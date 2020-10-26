package mankind;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        try {
            String[] studentInput = in.nextLine().split("\\s+");
            String studentFirstName = studentInput[0];
            String studentLastName = studentInput[1];
            String studentFacultyNumber = studentInput[2];

            Student student = new Student(studentFirstName, studentLastName, studentFacultyNumber);

            String[] workerInput = in.nextLine().split("\\s+");
            String workerFirstName = workerInput[0];
            String workerLastName = workerInput[1];
            double workerSalary = Double.valueOf(workerInput[2]);
            double workingHours = Double.valueOf(workerInput[3]);

            Worker worker = new Worker(workerFirstName, workerLastName, workerSalary, workingHours);

            System.out.println(student.toString());
            System.out.println(worker.toString());

        } catch (IllegalArgumentException error) {
            System.out.println(error.getMessage());
        }
    }
}
