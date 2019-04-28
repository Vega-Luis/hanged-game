package userinterface;

import clientbussineslogic.Client;
import java.util.Scanner;

/**
 * Clase de interfaz de usuario.
 * @author Luis
 * @version v4.28.19
 */
public class ConsoleUI {
  
  public Client client;
  
  /**
   * Opciones de consola.
   */
  public void showOptions() {
    System.out.println("Lista de comandos");
    System.out.println("1. initGame");
    System.out.println("2. checkLetter");
  }
  
  /**
   * inicializa el juego.
   */
  public void initGame() {
    System.out.println("Ingrese el nombre de usuario mas el comando:");
    Scanner scanner = new Scanner(System.in);
    System.out.println(client.conection(scanner.nextLine()));
  }
  
  /**
   * Mantiene el juego
   */
  public void play() {
    boolean gameOver = false;
    while (!gameOver) {
      System.out.println("Try");
      Scanner scanner = new Scanner(System.in);
      String out = client.conection(scanner.nextLine());
      System.out.println(out);
      if (out.equals("gameOver")) {
        gameOver = true;
      }
    }
  }
  
  public static void main(String[] args) {
    ConsoleUI ui = new ConsoleUI();
    ui.client = new Client(args[0],Integer.parseInt(args[1]));
    ui.showOptions();
    ui.initGame();
  }
}
