package concurrency.dictionary;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;

public class DictionaryService {
    private final ConcurrentHashMap<String, String> dictionary = new ConcurrentHashMap<>();

    private final Executor readExecutor;
    private final Executor editExecutor;

    public DictionaryService(Executor readExecutor, Executor editExecutor) {
        this.readExecutor = readExecutor;
        this.editExecutor = editExecutor;
    }

    public void addUpdateWord(String word, String meaning) {
        editExecutor.execute(() -> {
            //if (dictionary.containsKey(word)) {
                dictionary.put(word, meaning);
            System.out.println("Added word " + word);
            //}
        });
    }

    public String getMeaning(String word) {
        if (dictionary.containsKey(word)) {
            return dictionary.get(word);
        }

        return String.format("%s not found", word);
    }
}
