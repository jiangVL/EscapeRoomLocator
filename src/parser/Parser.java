package parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    public static final String FILE_RELATIVE_PATH = "/../../bin/";
    public static final String FILE_NAME = "terpeca-2021-round1.txt";
    public static final String LOCATION_REGEX = "[(][\\w\\s]+[[,][\\w\\s]]+[)]";

    public static final Map<String, String> roomMap = new HashMap<>();

    public static void main(String args[]) {
        BufferedReader reader;
        Pattern locationPattern = Pattern.compile(LOCATION_REGEX);

        String filePath = new File("").getAbsolutePath();
        try {
            System.out.printf("Reading file: %s\n", FILE_NAME);
            reader = new BufferedReader(new FileReader(filePath + FILE_RELATIVE_PATH + FILE_NAME));
            
            String roomDetails = reader.readLine();

            Matcher locationMatcher = locationPattern.matcher(roomDetails);
            System.out.println(locationMatcher.group());
            reader.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
}