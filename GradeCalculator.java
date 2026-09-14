package assignment1;

/*
 * Class: CMSC203 CRN 21410
 * Instructor: Huseyin Aygun
 * Description: Reads a course grading configuration and one student's scores,
 * calculates category averages and an overall weighted average, optionally
 * applies +/- grading, and writes the grade summary to grades_report.txt.
 * Due: 09/13/2026
 * Platform/compiler: Java / Eclipse compatible
 *
 * I pledge that I have completed the programming assignment independently.
 * I have not copied the code from a student or any source.
 * I have not given my code to any student.
 * Print your Name here: Jiawei Lyu
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class GradeCalculator {
    public static void main(String[] args) {
        final String CONFIG_FILE = "gradeconfig.txt";
        final String INPUT_FILE = "grades_input.txt";
        final String OUTPUT_FILE = "grades_report.txt";

        String courseName = "CMSC203 Computer Science I";
        int categoryCount = 3;
        boolean useDefaultConfig = false;
        boolean configValid = true;
        Scanner configScanner = null;

        System.out.println("========================================");
        System.out.println(" CMSC203 Project 1 - Grade Calculator");
        System.out.println("========================================");
        System.out.println("Loading configuration from " + CONFIG_FILE + " ...");

        // First pass: validate the configuration file and confirm that weights total 100.
        try {
            configScanner = new Scanner(new File(CONFIG_FILE));

            if (!configScanner.hasNextLine()) {
                configValid = false;
            } else {
                courseName = configScanner.nextLine().trim();
                if (courseName.length() == 0) {
                    configValid = false;
                }
            }

            if (configValid && configScanner.hasNextInt()) {
                categoryCount = configScanner.nextInt();
                if (categoryCount <= 0) {
                    configValid = false;
                }
            } else {
                configValid = false;
            }

            int weightTotal = 0;
            int configCategoryNumber = 0;

            while (configValid && configCategoryNumber < categoryCount) {
                if (!configScanner.hasNext()) {
                    configValid = false;
                    break;
                }
                configScanner.next(); // category name

                if (!configScanner.hasNextInt()) {
                    configValid = false;
                    break;
                }

                int weight = configScanner.nextInt();
                if (weight < 0 || weight > 100) {
                    configValid = false;
                    break;
                }

                weightTotal += weight;
                configCategoryNumber++;
            }

            if (configCategoryNumber != categoryCount || weightTotal != 100) {
                configValid = false;
            }

            configScanner.close();
            configScanner = null;
        } catch (FileNotFoundException e) {
            configValid = false;
        }

        if (!configValid) {
            useDefaultConfig = true;
            courseName = "CMSC203 Computer Science I";
            categoryCount = 3;
            System.out.println("Configuration missing or invalid.");
            System.out.println("Using default configuration: Projects 40%, Quizzes 30%, Exams 30%.");
        } else {
            System.out.println("Configuration loaded successfully.");
        }

        Scanner inputScanner;
        try {
            inputScanner = new Scanner(new File(INPUT_FILE));
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + INPUT_FILE + " is missing or cannot be read.");
            System.out.println("Program will exit gracefully.");
            System.out.println("Programmer: Jiawei Lyu");
            return;
        }

        System.out.println("Using input file: " + INPUT_FILE);
        System.out.println("Using output file: " + OUTPUT_FILE);
        System.out.println("Reading student scores...");

        if (!inputScanner.hasNextLine()) {
            System.out.println("Error: Student first name is missing.");
            inputScanner.close();
            return;
        }
        String firstName = inputScanner.nextLine().trim();

        if (!inputScanner.hasNextLine()) {
            System.out.println("Error: Student last name is missing.");
            inputScanner.close();
            return;
        }
        String lastName = inputScanner.nextLine().trim();
        String studentName = firstName + " " + lastName;

        // Reopen the valid configuration for the processing pass.
        if (!useDefaultConfig) {
            try {
                configScanner = new Scanner(new File(CONFIG_FILE));
                configScanner.nextLine(); // course name
                configScanner.nextInt();  // category count
            } catch (FileNotFoundException e) {
                useDefaultConfig = true;
                courseName = "CMSC203 Computer Science I";
                categoryCount = 3;
                configScanner = null;
            }
        }

        PrintWriter output;
        try {
            output = new PrintWriter(OUTPUT_FILE);
        } catch (FileNotFoundException e) {
            System.out.println("Error: Unable to create " + OUTPUT_FILE + ".");
            inputScanner.close();
            if (configScanner != null) {
                configScanner.close();
            }
            return;
        }

        System.out.println("Student: " + studentName);
        System.out.println("Course: " + courseName);
        System.out.println("Category Results:");

        output.println("========================================");
        output.println(" CMSC203 Project 1 - Grade Calculator");
        output.println("========================================");
        output.println("Course: " + courseName);
        output.println("Student: " + studentName);
        output.println("Category Results:");

        double overallAverage = 0.0;
        boolean scoreFileValid = true;
        int categoryNumber = 0;

        while (categoryNumber < categoryCount && scoreFileValid) {
            String expectedCategory;
            int categoryWeight;

            if (useDefaultConfig) {
                if (categoryNumber == 0) {
                    expectedCategory = "Projects";
                    categoryWeight = 40;
                } else if (categoryNumber == 1) {
                    expectedCategory = "Quizzes";
                    categoryWeight = 30;
                } else {
                    expectedCategory = "Exams";
                    categoryWeight = 30;
                }
            } else {
                if (configScanner == null || !configScanner.hasNext()) {
                    System.out.println("Error: Configuration data ended unexpectedly.");
                    output.println("Error: Configuration data ended unexpectedly.");
                    scoreFileValid = false;
                    break;
                }
                expectedCategory = configScanner.next();

                if (!configScanner.hasNextInt()) {
                    System.out.println("Error: Invalid category weight in configuration.");
                    output.println("Error: Invalid category weight in configuration.");
                    scoreFileValid = false;
                    break;
                }
                categoryWeight = configScanner.nextInt();
            }

            if (!inputScanner.hasNext()) {
                System.out.println("Error: Missing category name in " + INPUT_FILE + ".");
                output.println("Error: Missing category name in " + INPUT_FILE + ".");
                scoreFileValid = false;
                break;
            }
            String inputCategory = inputScanner.next();

            if (!inputScanner.hasNextInt()) {
                System.out.println("Error: Invalid score count for " + inputCategory + ".");
                output.println("Error: Invalid score count for " + inputCategory + ".");
                scoreFileValid = false;
                break;
            }
            int scoreCount = inputScanner.nextInt();

            if (scoreCount <= 0) {
                System.out.println("Error: Score count for " + inputCategory + " must be greater than 0.");
                output.println("Error: Score count for " + inputCategory + " must be greater than 0.");
                scoreFileValid = false;
                break;
            }

            double scoreTotal = 0.0;
            int scoreNumber = 0;
            boolean scoresValid = true;

            while (scoreNumber < scoreCount) {
                if (!inputScanner.hasNextDouble()) {
                    System.out.println("Error: Invalid numeric score in category " + inputCategory + ".");
                    output.println("Error: Invalid numeric score in category " + inputCategory + ".");
                    scoresValid = false;
                    scoreFileValid = false;
                    break;
                }

                double score = inputScanner.nextDouble();
                if (score < 0.0 || score > 100.0) {
                    System.out.println("Error: Score " + score + " is outside the valid range 0-100.");
                    output.println("Error: Score " + score + " is outside the valid range 0-100.");
                    scoresValid = false;
                    scoreFileValid = false;
                    break;
                }

                scoreTotal += score;
                scoreNumber++;
            }

            if (!scoresValid) {
                break;
            }

            if (!inputCategory.equalsIgnoreCase(expectedCategory)) {
                System.out.println("Error: Expected category " + expectedCategory + " but found " + inputCategory + ". Category skipped.");
                output.println("Error: Expected category " + expectedCategory + " but found " + inputCategory + ". Category skipped.");
            } else {
                double categoryAverage = scoreTotal / scoreCount;
                overallAverage += categoryAverage * categoryWeight / 100.0;

                System.out.printf("  %s (%d%%): average = %.2f%n", expectedCategory, categoryWeight, categoryAverage);
                output.printf("  %s (%d%%): average = %.2f%n", expectedCategory, categoryWeight, categoryAverage);
            }

            categoryNumber++;
        }

        inputScanner.close();
        if (configScanner != null) {
            configScanner.close();
        }

        if (!scoreFileValid) {
            output.println("Program ended because the score input was invalid.");
            output.println("Programmer: Jiawei Lyu");
            output.close();
            System.out.println("Program ended because the score input was invalid.");
            System.out.println("Programmer: Jiawei Lyu");
            return;
        }

        // Round the numeric average to two decimal places for consistent reporting.
        overallAverage = Math.round((overallAverage + 0.000000001) * 100.0) / 100.0;

        String baseGrade;
        if (overallAverage >= 90.0) {
            baseGrade = "A";
        } else if (overallAverage >= 80.0) {
            baseGrade = "B";
        } else if (overallAverage >= 70.0) {
            baseGrade = "C";
        } else if (overallAverage >= 60.0) {
            baseGrade = "D";
        } else {
            baseGrade = "F";
        }

        Scanner keyboard = new Scanner(System.in);
        String plusMinusChoice;

        System.out.print("Apply +/- grading? (Y/N): ");
        plusMinusChoice = keyboard.nextLine().trim();

        while (!plusMinusChoice.equalsIgnoreCase("Y") && !plusMinusChoice.equalsIgnoreCase("N")) {
            System.out.print("Invalid input. Please enter Y or N: ");
            plusMinusChoice = keyboard.nextLine().trim();
        }

        String finalGrade = baseGrade;
        if (plusMinusChoice.equalsIgnoreCase("Y") && !baseGrade.equals("F")) {
            if (baseGrade.equals("A")) {
                if (overallAverage >= 98.0) {
                    finalGrade = "A+";
                } else if (overallAverage < 92.0) {
                    finalGrade = "A-";
                }
            } else if (baseGrade.equals("B")) {
                if (overallAverage >= 88.0) {
                    finalGrade = "B+";
                } else if (overallAverage < 82.0) {
                    finalGrade = "B-";
                }
            } else if (baseGrade.equals("C")) {
                if (overallAverage >= 78.0) {
                    finalGrade = "C+";
                } else if (overallAverage < 72.0) {
                    finalGrade = "C-";
                }
            } else if (baseGrade.equals("D")) {
                if (overallAverage >= 68.0) {
                    finalGrade = "D+";
                } else if (overallAverage < 62.0) {
                    finalGrade = "D-";
                }
            }
        }

        System.out.printf("Overall numeric average: %.2f%n", overallAverage);
        System.out.println("Base letter grade: " + baseGrade);
        System.out.println("Final letter grade: " + finalGrade);
        System.out.println("Default configuration used: " + (useDefaultConfig ? "Yes" : "No"));
        System.out.println("Summary written to " + OUTPUT_FILE);
        System.out.println("Programmer: Jiawei Lyu");
        System.out.println("Program complete. Goodbye!");

        output.printf("Overall numeric average: %.2f%n", overallAverage);
        output.println("Base letter grade: " + baseGrade);
        output.println("Final letter grade: " + finalGrade);
        output.println("+/- grading applied: " + (plusMinusChoice.equalsIgnoreCase("Y") ? "Yes" : "No"));
        output.println("Default configuration used: " + (useDefaultConfig ? "Yes" : "No"));
        output.println("Programmer: Jiawei Lyu");
        output.close();

        keyboard.close();
    }
}