package serverbusinesslogic;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner; 

public class HangedGame {
  private ArrayList<String> words;
  private int rand;
  private Random random;
  private Scanner inputSource;
  
  public HangedGame() {
    words = new ArrayList<String>();
    addWords();
    random = new Random();
    inputSource = new Scanner(System.in);
  }
  
  private void addWords() {
    words.add("teacher");
    words.add("poo");
    words.add("dinosaurio");
    words.add("tecnologico");
    words.add("edificio");
  }
  
  private boolean checkLetter(char letter, String word) {
    char wordLetter;
    for(int i = 0; i < word.length(); i++) {
      wordLetter = word.charAt(i);
      if(letter != wordLetter && letter != wordLetter-32) {
        return false;
      }
    }
    return true;
  }
  
  private void hint(String word) {
    if(word.equals("teacher")) {
      System.out.println("Educador en inglés");
    }
    if(word.equals("poo")) {
      System.out.println("Paradigma de programación");
    }
    if(word.equals("dinosaurio")) {
      System.out.println("Animal prehistorico");
    }
    if(word.equals("tecnologico")) {
      System.out.println("Universidad ubicada en cartago");
    }
    if(word.equals("edificio")) {
      System.out.println("Compuesto por paredes, techo, suelo, etc.");
    }
  }
  
  public boolean startGame() {
    rand = random.nextInt(words.size());
    int fails = 0;
    int aciertos = 0;
    String word = words.get(rand);
    char letter;
    hint(word);
    while(fails < 5) {
      System.out.println("Ingrese una Letra");
      letter = inputSource.next().charAt(0);
      if(checkLetter(letter,word)) {
        System.out.println("Letra acertada!");
        if(aciertos == word.length()) {
          return true;
        }
      }else {
        fails++;
        System.out.println("Fallo, intentos restantes :"+fails);
      }
    }
    return false;
  }
}
