import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by LaunchCode
 */
public class TechJobs {

    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {

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
            // Exit if the user chooses to quit
            if (actionChoice == null) {
                break;
            } else if (actionChoice.equals("list")) {
                // If the user chooses to list, prompt for the column choice
                String columnChoice = getUserSelection("List", columnChoices);
                // If the user chooses to list all, print all jobs
                if (columnChoice.equals("all")) {
                    printJobs(JobData.findAll());
                } else {
                    // If the user chooses a specific column, print values for that column
                    ArrayList<String> results = JobData.findAll(columnChoice);

                    System.out.print("\n*** All " + columnChoices.get(columnChoice) + " Values ***");

                    // Print list of values for chose column; skills, employers, etc
                    for (String item : results) {
                        System.out.println(item);
                    }
                }
            } else { // choice is "search"

                // How does the user want to search (e.g. by skill or employer)
                String searchField = getUserSelection("Search by:", columnChoices);

                // What is their search term?
                System.out.println("\nSearch term:");
                String searchTerm = in.nextLine();

                if (searchField.equals("all")) {
                    // If the user chooses to search all, print jobs matching the search term
                    printJobs(JobData.findByValue(searchTerm));

                } else {
//                  // If the user chooses a specific column to search, print matching jobs
                    printJobs(JobData.findByColumnAndValue(searchField, searchTerm));
                }
            }
        }
    }

    // ﻿Returns the key of the selected item from the choices Dictionary
    public static String getUserSelection(String menuHeader, HashMap<String, String> choices) {

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

            // Get user input
            //This line checks if the input from the user is an integer (a whole number).
            // If the input is an integer, it means the user made a choice from a menu of options.
            if (in.hasNextInt()) {
                // Reads the user's integer and stores it in the variable choice index.
                choiceIdx = in.nextInt();
                in.nextLine();
            } else {
                //if user input is not an integer, it stores the input in a string called "line."
                String line = in.nextLine();
                //If user input equals "x", then user quits.
                if (line.equals("x")) {
                    return null;
                }
            }
            /*boolean shouldQuit = line.equals("x"); if (shouldQuit) {*/
            // Validate user's input
            //If the index is less than 0 or greater than or equal to the length of the available choices,
            // it prints an error message
            if (choiceIdx < 0 || choiceIdx >= choiceKeys.length) {
                System.out.println("Invalid choice. Try again.");
            } else {
                validChoice = true;
            }

            //while loop continues until valid choice = true.
        } while (!validChoice);
        // Return the key corresponding to the user's choice
        return choiceKeys[choiceIdx];
    }

    // Print a list of jobs
    public static void printJobs(ArrayList<HashMap<String, String>> someJobs) {
        if (someJobs.isEmpty()) { //check if jobs list is empty and print "No Results"
            System.out.print("No Results");//Remove ln  to print the message without the trailing newline.
//            return;
        } else {
            for (HashMap<String, String> job : someJobs) {
                System.out.println("\n*****");
                for (Map.Entry<String, String> entry : job.entrySet()) {
                    System.out.println(entry.getKey() + ": " + entry.getValue());
                }
                System.out.println("*****");
////                System.out.println();
            }
        }
    }
}


//                String jobTitle = job.get("name");
//                String employer = job.get("employer");
//                String location = job.get("location");
//                String positionType = job.get("position type");
//                String coreCompetency = job.get("core competency");
//
//                System.out.println("\n*****");
//                System.out.println("position type: " + positionType);
//                System.out.println("name: " + jobTitle);
//                System.out.println("employer: " + employer);
//                System.out.println("location: " + location);
//                System.out.println("core competency: " + coreCompetency);
//                System.out.println("*****");
//            }
//
//        }
//    }
//}
//}
//            for (HashMap<String, String> job : someJobs) { //If not empty iterate over each job in jobs list. For each job print astericks line then iterate over each entry in the job's HashMap and print the key and value
//                System.out.println("*****");
                // Iterate over each key-value pair in the job HashMap
//        for (String key : job.keySet()) {
//            System.out.println(key + ": " + job.get(key));
//        }
//                for (Map.Entry<String, String> entry : job.entrySet()) {
//                    System.out.println(entry.getKey() + ": " + entry.getValue());
//                }
//                System.out.println("*****"); //After printing all fields of job prints astericks line and newline to separate this job from next one. If new field added to job records, prints new field without updates to printJobs
//            }//out.println
//        }
//    }
//}

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