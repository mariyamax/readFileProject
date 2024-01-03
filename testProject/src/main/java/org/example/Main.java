package org.example;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.zip.GZIPInputStream;

public class Main {

    public static void main(String[] args) throws RuntimeException {

        Date date = new Date();
        //Получаем ссылку на на файл, создаем паттерн для правильных строк, инициализируем массив со строками
        URL filePath;
        try {
            filePath = new URL(StringConstants.URL);
        } catch (MalformedURLException e) {
            throw new RuntimeException(StringConstants.URL_ERROR_MESSAGE);
        }
        List<String> lines = new ArrayList<>();
        Pattern pattern = Pattern.compile(StringConstants.PATTERN);
        StringBuilder sb;

        //Используем try-with-resources, чтобы достать строки из архива через stream и гарантировать его закрытие
        System.out.println(StringConstants.READ_FILE_MESSAGE);
        try (
                GZIPInputStream gis = new GZIPInputStream(filePath.openStream());
                InputStreamReader isr = new InputStreamReader(gis);
                BufferedReader br = new BufferedReader(isr)
        ) {
            //Фильтруем на паттерн и учитываем уникальность
            lines = br.lines().filter(line -> pattern.matcher(line).matches()).distinct().collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(StringConstants.EXTERNAL_FILE_ERROR_MESSAGE);
        }

        //Группируем строки в union-find структуру
        System.out.println(StringConstants.UNION_FIND_MESSAGE);
        UnionFind uf = new UnionFind();
        makeUnionFindGroup(uf, lines);

        //Группироем строки в основные группы
        System.out.println(StringConstants.GROUP_MESSAGE);
        Map<String, Set<String>> groups = new HashMap<>();
        makeListGroup(groups, lines, uf);


        //Сортируем, подсчитываем и выводим группы
        List<Set<String>> sortedGroups = new ArrayList<>(groups.values());
        sortedGroups.sort((a, b) -> b.size() - a.size());

        int groupCount = 0;
        for (Set<String> group : sortedGroups) {
            if (group.size() > 1) {
                groupCount++;
            }
        }

        try (FileOutputStream fis = args.length == 0 || args[0] == null ? new FileOutputStream(StringConstants.DEFAULT_OUTPUT) :
                new FileOutputStream(args[0])) {
            sb = new StringBuilder();
            System.out.println(StringConstants.GROUP_OUTPUT_MESSAGE);
            fis.write(sb.append(StringConstants.FINAL_TITLE).append(groupCount).toString().getBytes(StandardCharsets.UTF_8));
            int groupNumber = 1;
            for (Set<String> group : sortedGroups) {
                if (group.size() > 1) {
                    sb = new StringBuilder();
                    fis.write(sb.append("\nГруппа ").append(groupNumber++).append("\n").toString().getBytes(StandardCharsets.UTF_8));
                    for (String item : group) {
                        fis.write((item + "\n").getBytes(StandardCharsets.UTF_8));
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(StringConstants.NOT_FOUND_EXCEPTION_MESSAGE);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println(StringConstants.IOE_EXCEPTION_MESSAGE);
            e.printStackTrace();
        }

        System.out.println(String.format("Program execution time - %d s",((new Date().getTime() - date.getTime())/1000)));
    }

    public static void makeUnionFindGroup(UnionFind unionFind, List<String> values) {
        StringBuilder sb;
        for (String line : values) {
            String[] elements = line.split(";");
            String firstElement = null;
            for (int i = 0; i < elements.length; i++) {
                if (!elements[i].equals("\"\"")) {
                    sb = new StringBuilder();
                    String elementKey = sb.append(elements[i]).append("-").append(i).toString();
                    if (firstElement == null) {
                        firstElement = elementKey;
                    } else {
                        unionFind.union(firstElement, elementKey);
                    }
                }
            }
        }
    }

    public static void makeListGroup(Map<String, Set<String>> groups, List<String> values, UnionFind unionFind) {

        for (String line : values) {
            String[] elements = line.split(";");
            for (int i =0; i < elements.length;) {
                while (elements[i].equals("\"\"") && i < elements.length-1) {
                    i++;
                }
                if (!elements[i].equals("\"\"")) {
                    groups.computeIfAbsent(unionFind.find(elements[i]+"-"+i), k -> new HashSet<>()).add(line);
                }
                break;
           }
        }
    }

}

