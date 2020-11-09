package word.creator;

import word.suffix.Suffix;
import word.suffix.SuffixesBuilder;

import java.util.*;

import static java.util.Collections.singletonList;

public class NounFormsCreator{

    private static List<Suffix> suffixes =
            SuffixesBuilder.getBuilder()
                    .add("um", "a", "ów", "om", "ami", "ach")
                    .add("a", "y", "e", "", "ę", "ą","o","owie", "ów", "om", "ami", "ach", "owie", "i", "a", "ego", "emu", "", "oma", "om", "ach")
                    .add("o", "y", "ów", "u", "om", "ami", "yma", "ach", "y")
                    .add("el", false, "a",  "owi", "em", "u", "e", "om", "ami", "ach")
                    .add("el", true, "ół",  "ółmi", "ołom", "ołach")
            .build();

    public static List<String> create(String noun){
        List<String> result = new ArrayList<>(singletonList(noun));
        for(Suffix suffix : suffixes){
            result.addAll(suffix.createAllForms(noun, false));
        }
        return result;
    }

    //TEST
    public static void main(String[] args) {
        for(String f : create("muzeum")){
            System.out.println(f);
        }
        for(String f : create("przyjaciel")){
            System.out.println(f);
        }
        for(String f : create("marzyciel")){
            System.out.println(f);
        }
    }

}
