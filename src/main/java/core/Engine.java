package core;

import io.OutputWriter;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.util.List;

public class Engine implements Runnable {
    @Override
    public void run() {
        //Parsing the JSON file.
        JSONParser parser = new JSONParser();

        ParseJsonArray.parseArray(parser);
        try {
            List<Employee> employees = ParseJsonArray.getEmployees();

            //Writing to the result file + implementing some of the logic and condition checks
            ParseJsonObj.parseData(parser);
            String path = "src/jsonFile/result";

            OutputWriter file = new OutputWriter(path);
            file.write("Name , Score");
            file.write(System.lineSeparator());

            for (Employee employee : employees) {

                String name = employee.getName();
                long salesPeriod = employee.getSalesPeriod();
                long totalSales = employee.getTotalSales();
                double expMultiplier = employee.getExperienceMultiplier();

                long periodLimit = ParseJsonObj.getPeriodLimit();
                boolean useExprienceMultiplier = ParseJsonObj.isUseExprienceMultiplier();
                long topPerformersThreshold = ParseJsonObj.getTopPerformersThreshold();

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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
