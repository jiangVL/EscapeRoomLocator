package main.parser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import main.parser.EscapeRoom.EscapeRoomBuilder;

public class Parser {

    public static final String FILE_RELATIVE_PATH = "/resources/";
    public static final String FILE_NAME = "terpeca-2021-round1.txt";

    public static final String LOCATION_REGEX = "[(][^,)]+([,][^,)]+)+[)]";
    public static final String COMPANY_REGEX = "-(?:.(?! - ))+$";
    public static final String TRANSLATION_REGEX = "\\[(?:.(?!\\[.\\]))+$";

    public static void main(String args[]) {

        List<EscapeRoom> roomList = new ArrayList<>();

        try {
            String filePath = new File("").getAbsolutePath();
            System.out.printf("Reading file: %s\n", FILE_NAME);
            
            File terpecaFile = new File(filePath + FILE_RELATIVE_PATH + FILE_NAME);
            Files.lines(Paths.get(terpecaFile.toURI())).forEach(room -> {

                String[] locationInfo = parseRoomLocation(room);
                String location = locationInfo[0];

                String[] companyInfo = parseCompany(locationInfo[1]);
                String company = companyInfo[0];

                String[] translationInfo = parseTranslation(companyInfo[1]);
                String translation = translationInfo[0];
                
                String name = translationInfo[1];
                
                EscapeRoomBuilder escapeRoomBuilder = EscapeRoom.EscapeRoomBuilder.newInstance()
                                            .setName(name)
                                            .setCompany(company)
                                            .setLocation(location);
                if (translation != null) {
                    escapeRoomBuilder.setTranslation(translation);
                }
                roomList.add(escapeRoomBuilder.build());
            });
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

    /**
     * Parse the room location and return it in comma-separated format.
     * 
     * @param room
     * @return res[0] - location
     *         res[1] - remaining substring
     */
    public static String[] parseRoomLocation(String room) {
        String[] res = new String[2];
        
        Pattern locationPattern = Pattern.compile(LOCATION_REGEX);
        Matcher locationMatcher = locationPattern.matcher(room);
        if (locationMatcher.find()) {
            // Trim leading and ending paranthesis
            res[0] = room.substring(locationMatcher.start() + 1, locationMatcher.end() - 1);
            res[1] = room.substring(0, locationMatcher.start());
        } else {
            throw new IllegalArgumentException(String.format("Escape room details contain an invalid location: %s", room));
        }

        return res;
    }

    /**
     * Parse the company and return it.
     * 
     * @param room
     * @return res[0] - company
     *         res[1] - remaining substring
     */
    public static String[] parseCompany(String room) {
        String[] res = new String[2];
        
        Pattern companyPattern = Pattern.compile(COMPANY_REGEX);
        Matcher companyMatcher = companyPattern.matcher(room);
        if (companyMatcher.find()) {
            // Trim leading dash
            res[0] = room.substring(companyMatcher.start() + 1);
            res[1] = room.substring(0, companyMatcher.start());
        } else {
            throw new IllegalArgumentException(String.format("Escape room details contain an invalid company: %s", room));
        }

        return res;
    }

    /**
     * Parse the room translation if it exists and return it.
     * 
     * @param room
     * @return res[0] - translation
     *         res[1] - remaining substring
     */
    public static String[] parseTranslation(String room) {
        String[] res = new String[2];
        res[0] = null;
        res[1] = room;
        
        Pattern translationPattern = Pattern.compile(TRANSLATION_REGEX);
        Matcher translationMatcher = translationPattern.matcher(room);
        if (translationMatcher.find()) {
            res[0] = room.substring(translationMatcher.start());
            res[1] = room.substring(0, translationMatcher.start());
        } else {
            // Room doesn't have a translation, ignore and continue.
        }

        return res;
    }

}