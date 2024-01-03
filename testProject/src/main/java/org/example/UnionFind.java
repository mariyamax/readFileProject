package org.example;

import java.util.HashMap;
import java.util.Map;

/**
 * Непересекающийся набор — это структура данных,
 * которая отслеживает набор элементов, разделенных на несколько непересекающихся
 * <p>
 * Можно определить, находятся ли два элемента в одном и том же подмножестве,
 * сравнив результат find(). Если два элемента находятся в одном наборе, они имеют одного представителя.
 * В случае объединения двух элементов, объеденятся два подмножества, которым принадлежат элементы
 */
public class UnionFind {

    private final Map<String, String> parent = new HashMap<>();

    // Находим представителя множества, к которому принадлежит элемент
    public String find(String item) {
        parent.putIfAbsent(item, item);
        if (!parent.get(item).equals(item)) {
            parent.put(item, find(parent.get(item)));
        }
        return parent.get(item);
    }

    // Объединяем два множества
    public void union(String a, String b) {
        String parent1 = find(a);
        String parent2 = find(b);
        if (!parent1.equals(parent2)) {
            parent.put(parent1, parent2);
        }
    }
}
