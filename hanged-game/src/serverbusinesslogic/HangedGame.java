package serverbusinesslogic;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner; 

public class HangedGame {
  private ArrayList<String> words;
  private int rand;
  private Random random;
  private String currentWord;
  private int fails = 0;
  private int hits = 0;
  
  public HangedGame() {
    words = new ArrayList<String>();
    addWords();
    random = new Random();
  }
  
  private void setFails() {
    fails++;
  }
  
  public int getFails() {
    return fails;
  }
  
  private void setHits() {
    hits++;
  }
  
  public int getHits() {
    return hits;
  }
  
  public String getCurrentWord() {
    return currentWord;
  }
  
  private void addWords() {
    words.add("teacher");
    words.add("poo");
    words.add("dinosaurio");
    words.add("tecnologico");
    words.add("edificio");
  }
  
  public void checkLetter(char letter, String word) {
    char wordLetter;
    for(int i = 0; i < word.length(); i++) {
      wordLetter = word.charAt(i);
      if(letter != wordLetter && letter != wordLetter-32) {
        setFails();
      }
    }
    setHits();
  }
  
  private void hint(String word) {
    if(word.equals("teacher")) {
      System.out.println("Educador en inglés\n");
    }
    if(word.equals("poo")) {
      System.out.println("Paradigma de programación\n");
    }
    if(word.equals("dinosaurio")) {
      System.out.println("Animal prehistorico\n");
    }
    if(word.equals("tecnologico")) {
      System.out.println("Universidad ubicada en cartago\n");
    }
    if(word.equals("edificio")) {
      System.out.println("Compuesto por paredes, techo, suelo, etc\n");
    }
  }
  
  public void startGame() {
    rand = random.nextInt(words.size());
    currentWord = words.get(rand);
    hint(currentWord);
    System.out.println("Partida iniciada!\n");
  }
  
  public void gameOver() {
    System.out.println("Ha perdido!, la palabra era: "+currentWord+"\n");
  } 
}
