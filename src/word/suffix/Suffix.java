package word.suffix;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.singletonList;

public class Suffix {
    private String suffix;
    private boolean cutSuffix;
    private List<String> otherSuffixes;

    public Suffix(String suffix, List<String> otherSuffixes) {
        this.suffix = suffix;
        this.cutSuffix = true;
        this.otherSuffixes = otherSuffixes;
    }

    public Suffix(String suffix, boolean cutSuffix, List<String> otherSuffixes) {
        this.suffix = suffix;
        this.cutSuffix = cutSuffix;
        this.otherSuffixes = otherSuffixes;
    }

    public List<String> createAllForms(String word){
        return createAllForms(word, true);
    }

    /**
     * Creates all forms of words (based on its internal state)
     * @param word
     * @param includeInputWord
     * @return
     */
    public List<String> createAllForms(String word, boolean includeInputWord){
        List<String> result = includeInputWord ? new ArrayList<>(singletonList(word)) : new ArrayList<>();
        for(String suffix : otherSuffixes){
            if(word.endsWith(this.suffix)){
                    result.add(replaceSuffix(word, cutSuffix ? this.suffix : "", suffix));
            }
        }
        return result;
    }

    public String get() {
        return suffix;
    }

    public boolean cutSuffix() {
        return cutSuffix;
    }

    public List<String> getOtherSuffixes() {
        return otherSuffixes;
    }

    private String replaceSuffix (String word, String suffix, String replacement) {
        int endIndex = word.length() - suffix.length();
        String prefix = word.substring(0, Math.max(endIndex, 0));
        return prefix + replacement;
    }
}
