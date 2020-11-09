import extract.FeatureExtractor;
import word.creator.AdjectiveFormsCreator;
import word.creator.NounFormsCreator;
import word.creator.VerbFormsCreator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class FeatureHelper {

    public static void main(String[] args) throws IOException {
        File source = new File("src\\nawl.txt");
        File target = new File("src\\target.txt");
        extractWordValenceTuplesWithAllForms(source, target, "\t");
        System.out.println("SUCCESS");
    }

    /**
     * Extracts (word, valence) tuples as strings and saves it in target file.
     * @param source file
     * @param target file
     * @param separator target file columns separator
     * @throws IOException
     */
    private static void extractWordValenceTuplesWithAllForms(File source, File target, String separator) throws IOException {
        List<String> result =
                FeatureExtractor.extract(source, 0, 3, 5)
                        .stream()
                        .map(FeatureHelper::allForms)
                        .flatMap(Collection::stream)
                        .map(a -> String.join(separator, a))
                        .collect(toList());

        saveInFile(result, target);
    }

    /**
     * Converts columns to list of columns in all forms.
     * @param columns
     * @return columns in all forms
     */
    private static List<String[]> allForms(String[] columns){
        List<String> allForms = new ArrayList<>();
        switch (columns[1]) {
            case "adjective":
                allForms = AdjectiveFormsCreator.create(columns[0]);
                break;
            case "verb":
                allForms = VerbFormsCreator.create(columns[0]);
                break;
            case "noun":
                allForms = NounFormsCreator.create(columns[0]);
                break;
        }
        return allForms
                .stream()
                .map(af -> new String[]{af, columns[2]})
                .collect(toList());
    }

    /**
     * Saves list of lines to file
     */
    private static void saveInFile(List<String> lines, File file) throws IOException {
        FileWriter writer = new FileWriter(file);
        for(String line: lines) {
            writer.write(line + System.lineSeparator());
        }
        writer.close();
    }
}
