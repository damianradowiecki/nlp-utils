package word.suffix;

import java.util.*;

public class SuffixesBuilder {

    private List<Suffix> suffixes;

    private SuffixesBuilder(){
        suffixes = new ArrayList<>();
    }

    public static SuffixesBuilder getBuilder(){
        return new SuffixesBuilder();
    }

    public SuffixesBuilder add(String suffix, String otherSuffix, String... otherSuffixes){
        suffixes.add(new Suffix(suffix, otherSuffixes(otherSuffix, otherSuffixes)));
        return this;
    }

    public SuffixesBuilder add(String suffix, boolean cutSuffix, String otherSuffix, String... otherSuffixes){
        suffixes.add(new Suffix(suffix, cutSuffix, otherSuffixes(otherSuffix, otherSuffixes)));
        return this;
    }

    private List<String> otherSuffixes(String otherSuffix, String[] otherSuffixes) {
        List<String> result = new ArrayList<>(Arrays.asList(otherSuffixes));
        result.add(otherSuffix);
        return result;
    }

    public List<Suffix> build(){
        return suffixes;
    }
}
