package word.creator;

import word.suffix.Suffix;
import word.suffix.SuffixesBuilder;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.singletonList;

public class AdjectiveFormsCreator {

    private static List<Suffix> suffixes = SuffixesBuilder.getBuilder()
            .add("y", "a", "e", "ego", "ej", "ych", "emu", "ym", "ą")
            .add("i", "a", "e", "ego", "ch", "m", "mi", "ą", "ej")
            .build();

    public static List<String> create(String adjective){
        List<String> result = new ArrayList<>(singletonList(adjective));
        for(Suffix suffix : suffixes){
            result.addAll(suffix.createAllForms(adjective, false));
        }
        return result;
    }
}
