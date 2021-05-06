import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        JSONParser parser = new JSONParser();
        String path = "src/jsonFile/data.json";
        try (FileReader reader = new FileReader(path)) {
            Object obj = parser.parse(reader);
            JSONArray employeeList = (JSONArray) obj;

            List<Employee> employees = new ArrayList<>();

            for (Object o : employeeList) {

                JSONObject employeeObject = (JSONObject) o;

                //Reading the employee data from the file
                String name = (String) getJSONArray(employeeObject, "name");
                Long totalSales = (Long) getJSONArray(employeeObject, "totalSales");
                Long salesPeriod = (Long) getJSONArray(employeeObject, "salesPeriod");
                Double experienceMultiplier = (Double) getJSONArray(employeeObject, "experienceMultiplier");

                employees.add(new Employee(name, totalSales, salesPeriod, experienceMultiplier));
            }

            FileReader reportReader = new FileReader("src/jsonFile/reportDefinition.json");
            Object reportObj = parser.parse(reportReader);
            JSONObject dataCompare = (JSONObject) reportObj;

            //Reading report definition data file
            Long topPerformersThreshold = (Long) getJSONData(dataCompare, "topPerformersThreshold");
            boolean useExprienceMultiplier = (boolean) getJSONData(dataCompare, "useExprienceMultiplier");
            Long periodLimit = (Long) getJSONData(dataCompare, "periodLimit");

            //Writing to the result file + implementing some of the logic and condition checks
            List<Employee> bestEmployees = new LinkedList<>();

            path = "src/jsonFile/result";
            FileWriter file = new FileWriter(path);
            file.write("Name , Score");
            file.write(System.lineSeparator());

            for (Employee employee : employees) {

                if (employee.getSalesPeriod() <= periodLimit && useExprienceMultiplier) {
                    Double score =
                            employee.getTotalSales() / employee.getSalesPeriod() * employee.getExperienceMultiplier();
                    if (score >= topPerformersThreshold) {
                        file.write(employee.getName() + " ," + score);
                        file.write(System.lineSeparator());
                    }
                } else if (employee.getSalesPeriod() <= periodLimit) {
                    long score = employee.getTotalSales() / employee.getSalesPeriod();
                    file.write(employee.getName() + " ," + score);
                    file.write(System.lineSeparator());
                }
            }
            file.flush();
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }

    private static Object getJSONData(JSONObject dataCompare, String predicate) {
        return dataCompare.get(predicate);
    }

    private static Object getJSONArray(JSONObject employeeObject, String predicate) {
        return employeeObject.get(predicate);
    }
}
