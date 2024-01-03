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

    private Map<String, String> parent = new HashMap<>();

    /**
     * Метод find определяет, в каком подмножестве находится конкретный элемент,
     * возвращает представителя этого конкретного набора. Элемент из этого набора
     * обычно действует как “представитель” набора.
     */
    public String find(String item) {
        parent.putIfAbsent(item, item);
        if (!parent.get(item).equals(item)) {
            parent.put(item, find(parent.get(item)));
        }
        return parent.get(item);
    }

    /**
     * Метод union объединяет два разных подмножества в одно подмножество,
     * и представитель одного множества становится представителем другого.
     */
    public void union(String item1, String item2) {
        String parent1 = find(item1);
        String parent2 = find(item2);
        if (!parent1.equals(parent2)) {
            parent.put(parent1, parent2);
        }
    }
}
