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
  private String username;
  private StringBuilder letterSpaces;
  
  private String hanged = "";
  private final char Rightleg = 92;
  private final String  man1 = "____";
  private final String  man2 = "|  |";
  private final String  man3 = "|  0";
  private final String  man4 = "| /|"+Rightleg;
  private final String  man5 = "| / "+Rightleg;
  private final ArrayList<String> hangedParts;
  
  /*____
  |  |
  |  0
  | /|\
  | / \*/
     
  
  public HangedGame(String username) {
    words = new ArrayList<String>();
    addWords();
    random = new Random();
    setUsername(username);
    letterSpaces = new StringBuilder();
    hangedParts = new ArrayList<String>();
    setHangedParts();
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
  
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  private void addWords() {
    words.add("tea");
    words.add("poo");
    words.add("dino");
    words.add("tecn");
    words.add("edif");
  }
  
  private void setHangedParts() {
    hangedParts.add(man1);
    hangedParts.add(man2);
    hangedParts.add(man3);
    hangedParts.add(man4);
    hangedParts.add(man5);
  }
  
  public String checkLetter(char letter) {
    char wordLetter;
    boolean fail = true;
    for(int i = 0; i < currentWord.length(); i++) {
      wordLetter = currentWord.charAt(i);
      if(wordLetter == letter) {
        if(i != 0) {
          letterSpaces.setCharAt(i+i, wordLetter);
        }else {
          letterSpaces.setCharAt(i, wordLetter);
        }
        setHits();
        fail = false;
      }
    }
    if(fail) {
      setFails();
      String part = "";
      for(int i = 0; i < fails; i++) {
        part = hangedParts.get(i);
      }
      hanged += part + "\n";
    }
    return letterSpaces.toString() + "\n\n" + hanged +"\n";
  }
  
  private String hint(String word) {
    if(word.equals("teacher")) {
      return "Educador en inglés\n";
    }
    if(word.equals("poo")) {
      return "Paradigma de programación\n";
    }
    if(word.equals("dinosaurio")) {
      return "Animal prehistorico\n";
    }
    if(word.equals("tecnologico")) {
      return "Universidad ubicada en cartago\n";
    }
    if(word.equals("edificio")) {
      return "Compuesto por paredes, techo, suelo, etc\n";
    }else {
      return "";
    }
  }
  public String startGame() {
    rand = random.nextInt(words.size());
    String mensaje = "Partida iniciada!\n";
    currentWord = words.get(rand);
    mensaje += hint(currentWord);
    for(int i = 0; i < getCurrentWord().length(); i++) {
      letterSpaces.append("_");
      letterSpaces.append(" ");      
    }
    mensaje += "\n"+letterSpaces;
    return mensaje;
  }
  
  public String gameOver() {
    return "Ha perdido! "+ getUsername()+ " la palabra era: "+currentWord+"\n";
  } 
  
  public String gameWin() {
    return "Ha ganado! "+getUsername()+"\n";
  }
}
