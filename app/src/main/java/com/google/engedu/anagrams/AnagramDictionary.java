package com.google.engedu.anagrams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Arrays;
import android.util.Log;

public class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;
    private Random random = new Random();
    private HashSet<String> wordSet = new HashSet<String>( );
    private ArrayList<String> wordList = new ArrayList<String>( );
    private HashMap<String, ArrayList<String>> lettersToWord = new HashMap<String, ArrayList<String>>();

    public AnagramDictionary(InputStream wordListStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(wordListStream));
        String line;
        while((line = in.readLine()) != null) {
            String word = line.trim();
            wordSet.add(word);
            wordList.add(word);
            String sorted = helperFunction(word);
            if(lettersToWord.containsKey(sorted)){
                lettersToWord.get(sorted).add(word);
            }
            else{
                lettersToWord.put(sorted, new ArrayList<String>());
                lettersToWord.get(sorted).add(word);
            }
        }
    }

    public String helperFunction(String input){
        char[] chars = input.toCharArray();
        Arrays.sort(chars);
        return (new String(chars));
    }

    public boolean isGoodWord(String word, String base) {
        if(!wordSet.contains(word)) {return false;}
        else if(word.contains(base)) {return false;}
        return true;
    }


    public ArrayList<String> getAnagramsWithOneMoreLetter(String word) {
        ArrayList<String> result = new ArrayList<String>();

        for(char j = 'a'; j<='z'; j++){
            String given = word;
            given = given + j;
            given = helperFunction(given);
            Log.d("Tag Name", given);
            if(lettersToWord.containsKey(given)) {
                result.addAll(lettersToWord.get(given));
            }
        }

        return result;
    }

    public String pickGoodStarterWord() {
        return "foo";
    }
}
