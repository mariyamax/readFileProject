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
    private final Map<String, Integer> rank = new HashMap<>();

    // Находим представителя множества, к которому принадлежит элемент
    public String find(String item) {
        if (!parent.containsKey(item)) {
            parent.put(item, item);
            rank.put(item, 0);
        } else if (!parent.get(item).equals(item)) {
            parent.put(item, find(parent.get(item))); // Path compression
        }
        return parent.get(item);
    }

    // Объединяем два множества
    public void union(String a, String b) {
        String rootA = find(a);
        String rootB = find(b);

        if (rootA.equals(rootB)) {
            return;
        }

        // Объединяем деревья с учетом ранга
        if (rank.get(rootA) < rank.get(rootB)) {
            parent.put(rootA, rootB);
        } else if (rank.get(rootA) > rank.get(rootB)) {
            parent.put(rootB, rootA);
        } else {
            parent.put(rootB, rootA);
            rank.put(rootA, rank.get(rootA) + 1);
        }
    }
}
