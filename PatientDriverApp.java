import java.util.Scanner;

/**
 * Class: CMSC203 CRN 21410
 * Program: Assignment 2 - Patient Application
 * Instructor: Huseyin Aygun
 * Student: Jiawei Lyu
 * Due Date: Not specified in the assignment description
 * Platform/Compiler: Windows 10 / Java javac
 *
 * Class Description: Driver program that reads patient information, creates three
 * procedures, displays formatted output, and calculates charge statistics.
 *
 * Integrity Pledge: I pledge that I have completed the programming assignment independently.
 * I have not copied the code from a student or any source.
 */
public class PatientDriverApp {

    /** Reads all patient information from the keyboard and returns a Patient object. */
    public static Patient inputPatient(Scanner input) {
        System.out.print("Enter first name: ");
        String firstName = input.nextLine();
        System.out.print("Enter middle name: ");
        String middleName = input.nextLine();
        System.out.print("Enter last name: ");
        String lastName = input.nextLine();
        System.out.print("Enter street address: ");
        String street = input.nextLine();
        System.out.print("Enter city: ");
        String city = input.nextLine();
        System.out.print("Enter state: ");
        String state = input.nextLine();
        System.out.print("Enter zip: ");
        String zip = input.nextLine();
        System.out.print("Enter phone number (###-###-####): ");
        String phone = input.nextLine();
        System.out.print("Enter emergency contact name: ");
        String emergencyName = input.nextLine();
        System.out.print("Enter emergency contact phone (###-###-####): ");
        String emergencyPhone = input.nextLine();

        return new Patient(firstName, middleName, lastName, street, city, state,
                zip, phone, emergencyName, emergencyPhone);
    }

    /** Creates procedure 1 by using the no-argument constructor and setters. */
    public static Procedure createProcedure1() {
        Procedure procedure = new Procedure();
        procedure.setProcedureName("Physical Exam");
        procedure.setProcedureDate("07/20/2026");
        procedure.setPractitionerName("Dr. Irvine");
        procedure.setCharges(250.00);
        return procedure;
    }

    /** Creates procedure 2 by using the name-and-date constructor and setters. */
    public static Procedure createProcedure2() {
        Procedure procedure = new Procedure("X-ray", "07/20/2026");
        procedure.setPractitionerName("Dr. Jamison");
        procedure.setCharges(550.43);
        return procedure;
    }

    /** Creates procedure 3 by using the constructor containing every attribute. */
    public static Procedure createProcedure3() {
        return new Procedure("Blood Test", "07/20/2026", "Dr. Smith", 1400.75);
    }

    /** Displays the patient information and phone-validation results. */
    public static void displayPatient(Patient patient) {
        System.out.println();
        System.out.println("Patient Information");
        System.out.println("-------------------");
        System.out.println(patient);
        System.out.println("Phone Valid: " + patient.isValidPhoneNumber());
        System.out.println("Emergency Phone Valid: " + patient.isValidEmergencyPhoneNumber());
    }

    /** Displays one procedure as an aligned table row. */
    public static void displayProcedure(Procedure procedure) {
        System.out.printf("%-20s %-12s %-18s %-12s %-10s%n",
                procedure.getProcedureName(),
                procedure.getProcedureDate(),
                procedure.getPractitionerName(),
                procedure.getFormattedCharge(),
                procedure.getChargeCategory());
    }

    /** Displays all three procedures in an aligned table. */
    public static void displayProcedureTable(Procedure p1, Procedure p2, Procedure p3) {
        System.out.println();
        System.out.printf("%-20s %-12s %-18s %-12s %-10s%n",
                "Procedure", "Date", "Practitioner", "Charge", "Category");
        System.out.println("------------------------------------------------------------------------");
        displayProcedure(p1);
        displayProcedure(p2);
        displayProcedure(p3);
    }

    /** Returns the total charge of the three procedures. */
    public static double calculateTotalCharges(Procedure p1, Procedure p2, Procedure p3) {
        return p1.getCharges() + p2.getCharges() + p3.getCharges();
    }

    /** Returns the average charge of the three procedures. */
    public static double calculateAverageCharge(Procedure p1, Procedure p2, Procedure p3) {
        return calculateTotalCharges(p1, p2, p3) / 3.0;
    }

    /** Returns the procedure with the highest charge. */
    public static Procedure findHighestChargeProcedure(Procedure p1, Procedure p2, Procedure p3) {
        Procedure highest = p1;
        if (p2.getCharges() > highest.getCharges()) {
            highest = p2;
        }
        if (p3.getCharges() > highest.getCharges()) {
            highest = p3;
        }
        return highest;
    }

    /** Counts the number of procedures with charges of at least $1,000.00. */
    public static int countExpensiveProcedures(Procedure p1, Procedure p2, Procedure p3) {
        int count = 0;
        if (p1.isExpensiveProcedure()) count++;
        if (p2.isExpensiveProcedure()) count++;
        if (p3.isExpensiveProcedure()) count++;
        return count;
    }

    /** Displays total, average, highest procedure, and expensive-procedure count. */
    public static void displaySummary(Procedure p1, Procedure p2, Procedure p3) {
        System.out.printf("%nTotal Charges: $%,.2f%n", calculateTotalCharges(p1, p2, p3));
        System.out.printf("Average Charge: $%,.2f%n", calculateAverageCharge(p1, p2, p3));
        System.out.println("Highest Charge Procedure: "
                + findHighestChargeProcedure(p1, p2, p3).getProcedureName());
        System.out.println("Number of Expensive Procedures: "
                + countExpensiveProcedures(p1, p2, p3));
    }

    /** Runs the Patient Application. */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Patient patient = inputPatient(input);
        Procedure p1 = createProcedure1();
        Procedure p2 = createProcedure2();
        Procedure p3 = createProcedure3();

        displayPatient(patient);
        displayProcedureTable(p1, p2, p3);
        displaySummary(p1, p2, p3);

        System.out.println();
        System.out.println("The program was developed by a Student: Jiawei Lyu 09/27/26");

        input.close();
    }
}
