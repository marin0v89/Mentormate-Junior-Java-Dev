import enums.infoData;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args) throws IOException {
        JSONParser parser = new JSONParser();
        String path = "src/jsonFile/data.json";

        try (FileReader reader = new FileReader(path)) {
            Object obj = parser.parse(reader);
            JSONArray employeeList = (JSONArray) obj;

            List<Employee> employees = addEmployeeObj(employeeList);


            FileReader reportReader = new FileReader("src/jsonFile/reportDefinition.json");
            Object reportObj = parser.parse(reportReader);
            JSONObject dataCompare = (JSONObject) reportObj;

            //Reading report definition data file
            Long topPerformersThreshold =
                    (Long) getJSONData(dataCompare, String.valueOf(infoData.topPerformersThreshold));
            boolean useExprienceMultiplier =
                    (boolean) getJSONData(dataCompare, String.valueOf(infoData.useExprienceMultiplier));
            Long periodLimit =
                    (Long) getJSONData(dataCompare, String.valueOf(infoData.periodLimit));

            //Writing to the result file + implementing some of the logic and condition checks

            path = "src/jsonFile/result";
            FileWriter file = new FileWriter(path);
            file.write("Name , Score");
            file.write(System.lineSeparator());

            for (Employee employee : employees) {

                String name = employee.getName();
                long salesPeriod = employee.getSalesPeriod();
                long totalSales = employee.getTotalSales();
                double expMultiplier = employee.getExperienceMultiplier();

                if (salesPeriod <= periodLimit && useExprienceMultiplier) {
                    double score =
                            (totalSales * 1.0 / salesPeriod) * expMultiplier;
                    if (score >= topPerformersThreshold) {
                        file.write(name + ", " + score);
                        file.write(System.lineSeparator());
                    }

                } else if (salesPeriod <= periodLimit) {
                    long score = totalSales / salesPeriod;
                    file.write(name + " ," + score);
                    file.write(System.lineSeparator());
                }
            }
            file.flush();
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Employee> addEmployeeObj(JSONArray employeeList) {
        List<Employee> employees = new ArrayList<>();
        for (Object element : employeeList) {

            JSONObject employeeObject = (JSONObject) element;

            //Reading the employee data from the file
            String name =
                    (String) getJSONArray(employeeObject, String.valueOf(infoData.name));
            Long totalSales =
                    (Long) getJSONArray(employeeObject, String.valueOf(infoData.totalSales));
            Long salesPeriod =
                    (Long) getJSONArray(employeeObject, String.valueOf(infoData.salesPeriod));
            Double experienceMultiplier =
                    (Double) getJSONArray(employeeObject, String.valueOf(infoData.experienceMultiplier));


            employees.add(new Employee(name, totalSales, salesPeriod, experienceMultiplier));
        }
        return employees;
    }

    private static Object getJSONData(JSONObject dataCompare, String predicate) {
        return dataCompare.get(predicate);
    }

    private static Object getJSONArray(JSONObject employeeObject, String predicate) {
        return employeeObject.get(predicate);
    }
}
