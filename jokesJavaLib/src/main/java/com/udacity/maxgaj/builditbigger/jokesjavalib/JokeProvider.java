package com.udacity.maxgaj.builditbigger.jokesjavalib;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JokeProvider {
    public String tellAJoke(){
        List<String> jokesData = getJokesData();
        int size = jokesData.size();
        Random random = new Random();
        int n = random.nextInt(size);
        return jokesData.get(n);
    }

    private List<String> getJokesData(){
        List<String> jokesData = new ArrayList<>();
        // https://www.rd.com/funny-stuff/corny-math-jokes-pi-day/
        jokesData.add("Why should you never talk to pi?\n Because he’ll just go on forever.");
        jokesData.add("Why do teenagers travel in groups of three?\n Because they can’t even.");
        jokesData.add("Did you hear about the mathematician who’s afraid of negative numbers?\n He’ll stop at nothing to avoid them.");
        return jokesData;
    }
}
