package core;

import enums.infoData;
import io.InputReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class ParseJsonObj {
    private static long topPerformersThreshold;
    private static boolean useExprienceMultiplier;
    private static long periodLimit;

    public static void parseData(JSONParser parser) {
        String path = "src/jsonFile/reportDefinition.json";
        InputReader reportReader = new InputReader(path);


        Object reportObj = null;
        try {
            reportObj = parser.parse(reportReader.readFile());
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        JSONObject dataCompare = (JSONObject) reportObj;

         topPerformersThreshold =
                (Long) getJSONData(dataCompare, String.valueOf(infoData.topPerformersThreshold));
        useExprienceMultiplier =
                (boolean) getJSONData(dataCompare, String.valueOf(infoData.useExprienceMultiplier));
        periodLimit =
                (Long) getJSONData(dataCompare, String.valueOf(infoData.periodLimit));
    }

    private static Object getJSONData(JSONObject dataCompare, String predicate) {
        return dataCompare.get(predicate);
    }

    public static long getTopPerformersThreshold() {
        return topPerformersThreshold;
    }

    public static boolean isUseExprienceMultiplier() {
        return useExprienceMultiplier;
    }

    public static long getPeriodLimit() {
        return periodLimit;
    }
}
