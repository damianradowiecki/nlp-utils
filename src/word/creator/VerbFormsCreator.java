package word.creator;

import word.suffix.Suffix;
import word.suffix.SuffixesBuilder;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.singletonList;

public class VerbFormsCreator {

    private static List<Suffix> suffixes = SuffixesBuilder.getBuilder()
            .add("ać", "am", "asz", "a", "amy", "acie", "ają")
            .add("pać", "ię", "iesz", "ie", "iemy", "iecie", "ią")
            .add("kać", "czę", "czesz", "cze", "czemy", "czecie", "czą")
            .add("ować", "uję", "ujesz", "uje", "ujemy", "ujecie", "ują")
            .add("powiedzieć", "mówię", "mówisz", "mówi", "mówimy", "mówicie", "mówią")
            .add("ić", "ę", "sz", "i", "imy", "icie", "ą")
            .add("ąć", "ę", "iesz", "ie", "iemy", "iecie", "ą")
            .add("yć", "ę", "ysz", "y", "ymy", "ycie", "ą")
            .add("ść", "m", "sz", "", "my", "cie")// TODO add special cases for "ść" suffix
            .build();

    public static List<String> create(String verb){
        List<String> result = new ArrayList<>(singletonList(verb));
        for(Suffix suffix : suffixes){
            result.addAll(suffix.createAllForms(verb, false));
        }
        return result;
    }

}
