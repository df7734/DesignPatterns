package com.epam.rd.autocode.iterator;

import java.util.*;

class Iterators {

    public static Iterator<Integer> universalIterator(int[] array, int times){
        int[] modifiedArray = new int[array.length*times];
        for(int i=0; i<array.length; i++){
            for(int j=0; j<times; j++){
                modifiedArray[i*times+j] = array[i];
            }
        }
        return new Iterator<>() {
            private int i = 0;

            @Override
            public boolean hasNext() {
                return modifiedArray.length > i;
            }

            @Override
            public Integer next() {
                if (hasNext()) {
                    return modifiedArray[i++];
                } else throw new NoSuchElementException();
            }
        };
    }

    public static Iterator<Integer> intArrayTwoTimesIterator(int[] array){
            return universalIterator(array, 2);
    }

    public static Iterator<Integer> intArrayThreeTimesIterator(int[] array) {
        return universalIterator(array, 3);
    }

    public static Iterator<Integer> intArrayFiveTimesIterator(int[] array) {
        return universalIterator(array, 5);
        }

    public static Iterable<String> table(String[] columns, int[] rows){
        String[] matrix = new String[columns.length*rows.length];
        int counter = 0;
        for(int i=0; i<columns.length; i++){
            for(int j=0; j<rows.length; j++){
                matrix[counter] = columns[i] + rows[j];
                counter++;
            }
        }

        return () -> new Iterator<>() {
            private int i = 0;
            @Override
            public boolean hasNext() {
                return columns.length*rows.length > i;
            }

            @Override
            public String next() {
                if (hasNext()) {
                    return matrix[i++];
                } else throw new NoSuchElementException();
            }
        };
    }
}
