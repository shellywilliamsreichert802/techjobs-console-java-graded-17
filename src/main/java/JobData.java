import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;

/**
 * Created by LaunchCode
 */
public class JobData {

    private static final String DATA_FILE = "src/main/resources/job_data.csv";
    private static boolean isDataLoaded = false;

    private static ArrayList<HashMap<String, String>> allJobs;

    /**
     * Fetch list of all values from loaded data,
     * without duplicates, for a given column.
     *
     * @param field The column to retrieve values from
     * @return List of all of the values of the given field
     */
    public static ArrayList<String> findAll(String field) {

        // load data, if not already loaded
        loadData();

        ArrayList<String> values = new ArrayList<>();

        for (HashMap<String, String> row : allJobs) {
            String aValue = row.get(field);

            if (!values.contains(aValue)) {
                values.add(aValue);
            }
        }
//Bonus: Sort alphabetically
        Collections.sort(values);
        return values;
    }

    public static ArrayList<HashMap<String, String>> findAll() {

        // load data, if not already loaded
        loadData();

        return allJobs;
    }

    /**
     * Returns results of search the jobs data by key/value, using
     * inclusion of the search term.
     *
     * For example, searching for employer "Enterprise" will include results
     * with "Enterprise Holdings, Inc".
     *
     * @param column   Column that should be searched.
     * @param value Value of teh field to search for
     * @return List of all jobs matching the criteria
     */
    public static ArrayList<HashMap<String, String>> findByColumnAndValue(String column, String value) {

        // load data, if not already loaded
        loadData();

        ArrayList<HashMap<String, String>> jobs = new ArrayList<>();

        for (HashMap<String, String> row : allJobs) {

            String aValue = row.get(column);

//            if(aValue.contains(value)) {
//                jobs.add(row);

//change to ignore case sensitivity, enhance search functionality
//            if (aValue.toLowerCase().contains(value.toLowerCase())) {
            if (aValue.equalsIgnoreCase(value)) {
                jobs.add(row);
            }
        }

        return jobs;
    }

    /**
     * Search all columns for the given term
     *
     * @param value The search term to look for
     * @return      List of all jobs with at least one field containing the value
     */

//    public static ArrayList<HashMap<String, String>> findByValue(String value) {
//
//        // load data, if not already loaded
//        loadData();
    // TODO - implement this method

//implement the findByValue method in the JobData class
    public static ArrayList<HashMap<String, String>> findByValue(String value) {
        loadData();//method first loads the data, then iterates over each job in allJobs.
        ArrayList<HashMap<String, String>> jobs = new ArrayList<>();

        for (HashMap<String, String> row : allJobs) {
            for (String column : row.keySet()) {
                String val = row.get(column);
                // If data found, adds the job to the list and breaks out of the column checking loop to avoid adding duplicate jobs, Search is case-insensitive.
                if (val.toLowerCase().contains(value.toLowerCase())) {
                    jobs.add(row);
                    break;  // Found a match, no need to check other columns in this row
                }
            }
        }
        return jobs;  // Return list of all jobs that contain the value
    }
//        for (HashMap<String, String> job : allJobs) { //For each job iterates over each value in the job's HashMap
//            boolean found = false; //flag if value found in any column
//            //Loop through each column in the job
//            for (Map.Entry<String, String> entry : job.entrySet()) {
//                //check if column value contains the search value, case insesitive
//                if (entry.getValue().toLowerCase().contains(value.toLowerCase())) { //if value has search term (case-insensitive), checks if job is already in jobs list
//                    foundJobs.add(job);
//                    break;//if job is not in jobs list it adds the job to jobs list and breaks the inner loop thus each job is added only once to jobs list
//                }
//            }
//        }
//        return foundJobs;
//    }
    // Implement findByValue method in JobData class allows users to search all columns of the data for a given string, without returning duplicate jobs.
//    public static ArrayList<HashMap<String, String>> findByValue(String value) {
//        // Load data, if not already loaded
//        loadData();
////method iterates over each job/row of data. For each job checks each column to see if data is found
//        ArrayList<HashMap<String, String>> jobs = new ArrayList<>();
//
//        for (HashMap<String, String> row : allJobs) {
//            boolean matchFound = false;
//            for (String column : row.keySet()) {
//                String val = row.get(column);
//                if (val.toLowerCase().contains(value.toLowerCase())) {
//                    matchFound = true;
//                    break;  // Found a match, no need to check other columns in this row
//                }
//            }
//            if (matchFound) {
//                jobs.add(row);
//            }
//        }
//
//        return jobs;
//    }

//        for (HashMap<String, String> row : allJobs) {
//            for (String column : row.keySet()) {
//                String val = row.get(column);
//                // If data found, adds the job to the list and breaks out of the column checking loop to avoid adding duplicate jobs, Search is case-insensitive.
//                if (val.toLowerCase().contains(value.toLowerCase())) {
//                    jobs.add(row);
//                    break;  // Found a match, no need to check other columns in this row
//                }
//            }
//        }
//
//        return jobs;  // Return list of all jobs that contain the value
//    }
// commented out may need to add or sub above for jobs       return null;
//    }

    /**
     * Read in data from a CSV file and store it in a list
     */
    private static void loadData() {

        // Only load data once
        if (isDataLoaded) {
            return;
        }

        try {
            // Open the CSV file and set up pull out column header info and records
            Reader in = new FileReader(DATA_FILE);
            CSVParser parser = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
            List<CSVRecord> records = parser.getRecords();
            Integer numberOfColumns = records.get(0).size();
            String[] headers = parser.getHeaderMap().keySet().toArray(new String[numberOfColumns]);

            allJobs = new ArrayList<>();

            // Put the records into a more friendly format
            for (CSVRecord record : records) {
                HashMap<String, String> newJob = new HashMap<>();

                for (String headerLabel : headers) {
                    newJob.put(headerLabel, record.get(headerLabel));
                }

                allJobs.add(newJob);
            }

            // flag the data as loaded, so we don't do it twice
            isDataLoaded = true;

        } catch (IOException e) {
            System.out.println("Failed to load job data");
            e.printStackTrace();
        }
    }
}