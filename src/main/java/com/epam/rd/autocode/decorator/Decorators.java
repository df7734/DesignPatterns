package com.epam.rd.autocode.decorator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Decorators {
    public static List<String> evenIndexElementsSubList(List<String> sourceList) {
        return IntStream
                .range(0, sourceList.size())
                .filter(i -> i % 2 == 0)
                .mapToObj(sourceList::get)
                .collect(Collectors.toList());
    }
}
