import extract.FeatureExtractor;
import word.creator.AdjectiveFormsCreator;
import word.creator.NounFormsCreator;
import word.creator.VerbFormsCreator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import static java.util.stream.Collectors.toList;

public class FeatureHelper {

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.##", DecimalFormatSymbols.getInstance( Locale.ENGLISH ));
    public static final double NAWL_VALANCE_MIN = -3.0;
    public static final double NAWL_VALENCE_MAX = 3.0;



    public static void main(String[] args) throws IOException {
        File source = new File("C:\\Users\\Damian\\IdeaProjects\\vader-sentiment-analysis\\src\\main\\resources\\net\\nunoachenriques\\vader\\lexicon\\pl\\nawl.txt");
        File target = new File("C:\\Users\\Damian\\IdeaProjects\\vader-sentiment-analysis\\src\\main\\resources\\net\\nunoachenriques\\vader\\lexicon\\pl\\polish.txt");
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
                        .map(array -> scaleTo(array, -4.0, 4.0))
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

    /**
     * @see https://stats.stackexchange.com/questions/281162/scale-a-number-between-a-range/281164
     */
    private static String[] scaleTo(String[] array, double min, double max) {
        float value = Float.parseFloat(array[2]);
        double scaled = (value - NAWL_VALANCE_MIN)/(NAWL_VALENCE_MAX - NAWL_VALANCE_MIN)*(max - min) + min;
        array[2] = DECIMAL_FORMAT.format(scaled);
        return array;
    }
}
