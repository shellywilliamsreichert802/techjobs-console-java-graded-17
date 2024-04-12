import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by LaunchCode
 */
public class TechJobs {

    static Scanner in = new Scanner(System.in);

    public static void main (String[] args) {

        // Initialize our field map with key/name pairs
        HashMap<String, String> columnChoices = new HashMap<>();
        columnChoices.put("core competency", "Skill");
        columnChoices.put("employer", "Employer");
        columnChoices.put("location", "Location");
        columnChoices.put("position type", "Position Type");
        columnChoices.put("all", "All");

        // Top-level menu options
        HashMap<String, String> actionChoices = new HashMap<>();
        actionChoices.put("search", "Search");
        actionChoices.put("list", "List");

        System.out.println("Welcome to LaunchCode's TechJobs App!");

        // Allow the user to search until they manually quit
        while (true) {

            String actionChoice = getUserSelection("View jobs by (type 'x' to quit):", actionChoices);

            if (actionChoice == null) {
                break;
            } else if (actionChoice.equals("list")) {

                String columnChoice = getUserSelection("List", columnChoices);

                if (columnChoice.equals("all")) {
                    printJobs(JobData.findAll());
                } else {

                    ArrayList<String> results = JobData.findAll(columnChoice);

                    System.out.println("\n*** All " + columnChoices.get(columnChoice) + " Values ***");

                    // Print list of skills, employers, etc
                    for (String item : results) {
                        System.out.println(item);
                    }
                }
            } else { // choice is "search"

                // How does the user want to search (e.g. by skill or employer)
                String searchField = getUserSelection("Search by:", columnChoices);

                // What is their search term?
                System.out.println("\nSearch term:\n");
                String searchTerm = in.nextLine();

                if (searchField.equals("all")) {
                    // Call findByValue when the user wants to search all columns
                    printJobs(JobData.findByValue(searchTerm));
                } else {
                    printJobs(JobData.findByColumnAndValue(searchField, searchTerm));
                }
            }
        }
    }

    // ﻿Returns the key of the selected item from the choices Dictionary
    private static String getUserSelection(String menuHeader, HashMap<String, String> choices) {

        int choiceIdx = -1;
        Boolean validChoice = false;
        String[] choiceKeys = new String[choices.size()];

        // Put the choices in an ordered structure so we can
        // associate an integer with each one
        int i = 0;
        for (String choiceKey : choices.keySet()) {
            choiceKeys[i] = choiceKey;
            i++;
        }

        do {

            System.out.println("\n" + menuHeader);

            // Print available choices
            for (int j = 0; j < choiceKeys.length; j++) {
                System.out.println("" + j + " - " + choices.get(choiceKeys[j]));
            }

            if (in.hasNextInt()) {
                choiceIdx = in.nextInt();
                in.nextLine();
            } else {
                String line = in.nextLine();
                boolean shouldQuit = line.equals("x");
                if (shouldQuit) {
                    return null;
                }
            }

            // Validate user's input
            if (choiceIdx < 0 || choiceIdx >= choiceKeys.length) {
                System.out.println("Invalid choice. Try again.");
            } else {
                validChoice = true;
            }

        } while(!validChoice);

        return choiceKeys[choiceIdx];
    }

    // Print a list of jobs
//    private static void printJobs(ArrayList<HashMap<String, String>> someJobs) {
//
//        System.out.println("printJobs is not implemented yet");
//    }
//}
//    //Implement printJobs method
    public static void printJobs(ArrayList<HashMap<String, String>> jobs) {
        if (jobs.isEmpty()) { //check if jobs list is empty and print "No Results"
            System.out.print("No Results");//This will print the message without the trailing newline.
            return;
        }
        for (HashMap<String, String> job : jobs) { //If not empty iterate over each hob in jobs list. For each job print astericks line then iterate over each entry in the job's HashMap and print the key and value
            System.out.println("*****");
            for (Map.Entry<String, String> entry : job.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
            System.out.println("*****"); //After printing all fields of job prints astericks line and newline to separate this job from next one. If new field added to job records, prints new field without updates to printJobs
                break;
        }
    }

    // ... other methods ...
    //If the line is being printed as part of a loop or conditional statement, you might need to adjust the logic of your program. For example, if you’re printing all elements of a list and you want to skip one, you could add a condition to skip that specific element.
    //
    //Here’s a simple example:
    //
    //Java
    //
    //List<String> lines = Arrays.asList("line 1", "line 2", "line 3");
    //for (String line : lines) {
    //    if (!line.equals("line 2")) {
    //        System.out.println(line);
    //    }
    //}
    //In this example, line 2 will not be printed because of the condition in the if statement.
    //
    //If you need more specific help, please provide the part of your code that generates the output you want to modify. 😊
}