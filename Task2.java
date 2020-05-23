package datamining;

import java.io.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Task2 {

    public static void main(String[] args) throws IOException {
        /* В данном задании мы будем считать скалько раз какой товар попадает в корзину к польщователям
        * На основе этого мы получим рейгинг товаров от самой большей до самой меньшей потребтительской способности
        * Результат выведем в файл transactinsResult.csv график можно увидеть в файле transactinsResult.xlsx
        * */
        Scanner scanner = new Scanner(new File("src\\datamining\\transactions.csv"));
        HashMap<String, Integer> map = new HashMap<>();
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("src\\datamining" +
                "\\transactinsResult" +
                ".csv")));

        scanner.nextLine();
        while (scanner.hasNextLine()) {
            String[] in = scanner.nextLine().split(";");
            String id = in[0];
            String bucket = in[1];
            if (!map.containsKey(id)) {
                map.put(id, 1);
            } else {
                map.put(id, map.get(id) + 1);
            }
        }
        System.out.println(map.size());
        AtomicInteger count = new AtomicInteger();
        map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(x -> {
                    int c = x.getValue();
                    count.addAndGet(c);
                    try {
                        bufferedWriter.write(x.getKey() + ";" + x.getValue() + "\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
        System.out.println(count.get());
    }
}
