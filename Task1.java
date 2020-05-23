package datamining;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Task1 {
    public static void main(String[] args) throws IOException {
        /* в данной задаче будем считать что данные о пользователях у нас уже есть
        * все данные поступают в файле VKinput.txt. Результат будем выводить в файл vkresult.txt
        * */
        Scanner scanner = new Scanner(new File("src\\datamining\\VKinput.txt"));
        HashMap<String, String> map = new HashMap<>();
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("src\\datamining" +
                "\\vkresult.txt")));
        String userId = "id11111111";
        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split("-");
            String id = line[0];
            String[] groups = line[1].split(" ");
            TreeMap<String, Integer> mapGroup = new TreeMap<>();
            for (String group : groups) {
                String[] groupValue = group.replaceAll("\"","").split(":");
                if(!groupValue[1].equals("close")) {
                    if (mapGroup.containsKey(groupValue[1])) {
                        mapGroup.put(groupValue[1],mapGroup.get(groupValue[1]) + 1);
                    } else {
                        mapGroup.put(groupValue[1], 1);
                    }
                }
            }
            int max = 0;
            String result = "";
            for (Map.Entry<String,Integer> a : mapGroup.entrySet()) {
                if (a.getValue() == max) {
                    result += " " + a.getKey();
                } else if(a.getValue() > max) {
                    result = a.getKey();
                    max = a.getValue();
                }
            }
            map.put(id, result);
            if(result.equals(map.get(userId))) {
                bufferedWriter.write(id + " " + result + "\n");
                bufferedWriter.flush();
            }
        }
        bufferedWriter.close();
    }
}
