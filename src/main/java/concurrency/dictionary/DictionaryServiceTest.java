package concurrency.dictionary;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DictionaryServiceTest {
    public static void main(String[] args) throws InterruptedException {
        Executor readExecutor = Executors.newFixedThreadPool(5);
        Executor editExecutor = Executors.newFixedThreadPool(5);
        DictionaryService dictionaryService = new DictionaryService(readExecutor, editExecutor);


        Runnable addWordWorker1 = () -> {
            dictionaryService.addUpdateWord("A", "Apple");
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
        };

        Runnable addWordWorker2 = () -> {
//             try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
            dictionaryService.addUpdateWord("A", "Amigo");
            /*try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }*/
        };

        Runnable getMeaningWorker = () -> {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Meaning for A=" + dictionaryService.getMeaning("A"));
        };

        Thread editThread = new Thread(addWordWorker1);
        Thread readThread = new Thread(getMeaningWorker);
        Thread editThread2 = new Thread(addWordWorker2);

        editThread.start();
        readThread.start();
        editThread2.start();
    }
}
