package core;

import enums.infoData;
import io.InputReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParseJsonArray {
    private static List<Employee> employees;

    public static void parseArray(JSONParser parser) {
        //Reading the employee data from the file
        String path = "src/jsonFile/data.json";

        InputReader reader = new InputReader(path);
        try {
            Object obj = parser.parse(reader.readFile());
            JSONArray employeeList = (JSONArray) obj;

            employees = addEmployeeObj(employeeList);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private static List<Employee> addEmployeeObj(JSONArray employeeList) {
        List<Employee> employees = new ArrayList<>();
        for (Object element : employeeList) {

            JSONObject employeeObject = (JSONObject) element;

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

    private static Object getJSONArray(JSONObject employeeObject, String predicate) {
        return employeeObject.get(predicate);
    }

    public static List<Employee> getEmployees() {
        return employees;
    }
}
