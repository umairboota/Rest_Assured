package api.utilities;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataProviderUtil {

    @org.testng.annotations.DataProvider(name = "userIds")
    public static Object[][] getUserIdData() throws IOException, CsvValidationException {
        String csvFilePath = "src/test/resources/userIds.csv";
        List<String[]> data = readCSVData(csvFilePath);

        // Convert List<String[]> to Object[][]
        Object[][] dataArray = new Object[data.size()][];
        for (int i = 0; i < data.size(); i++) {
            dataArray[i] = data.get(i);
        }

        return dataArray;
    }

    public static List<String[]> readCSVData(String filePath) throws IOException, CsvValidationException {
        List<String[]> data = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                data.add(line);
            }
        }
        return data;
    }
}
