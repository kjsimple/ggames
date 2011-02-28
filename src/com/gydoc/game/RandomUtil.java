package com.gydoc.game;

import java.util.List;
import java.util.Random;
import java.util.Collections;
import java.util.ArrayList;

public class RandomUtil {
    
    private RandomUtil() {
        
    }
    
    public static <T> List<T> getRandomSequence(List<T> cards) {
        return getRandomSequence(cards, null);
    }
    
    public static <T> List<T> getRandomSequence(List<T> cards, Integer seed) {
        if (cards == null || cards.size() == 0) {
            return Collections.emptyList();
        }
        
        List<T> result = new ArrayList<T>();
        List<Integer> indexes = new ArrayList<Integer>();
        for (int i = 0; i < cards.size(); i++) {
            indexes.add(i);
        }
        Random r = seed == null ? new Random() : new Random(seed);
        for ( ; !indexes.isEmpty(); ) {
            int index = r.nextInt(indexes.size());
            result.add(cards.get(index));
            indexes.remove(index);
        }
        return result;
    }
    
}
