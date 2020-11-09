package extract;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.util.Collections.emptyList;

public class FeatureExtractor {

    /**
     * Method extracts columns from source file
     * @param source file
     * @param columns - indices of columns
     * @return list of columns
     */
    public static List<String[]> extract(File source, int... columns){
        List<String[]> extractedLines = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(source);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                extractedLines.add(extractFeatures(line, columns));
            }
            scanner.close();
            return extractedLines;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return emptyList();
    }

    /**
     * Method extracts features by columns indices.
     */
    private static String[] extractFeatures(String line, int[] columns){
        String[] strings = line.split("\\s+");
        List<String> result = new ArrayList<>();
        for(int index : columns){
            result.add(strings[index]);
        }
        return result.toArray(new String[columns.length]);
    }

}
