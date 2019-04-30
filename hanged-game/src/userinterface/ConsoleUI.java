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
    System.out.println("2. checkletter");
    System.out.println("Instrucciones");
    System.out.println("1. ejecute comando initGame");
    System.out.println("2. Envie el nombre de su usuario");
    System.out.println("3. Ejecute comando checkletter");
    System.out.println("4. Escriba la letra y presione enter");
    System.out.println("5. Repita los ultimos dos pasos");
    System.out.println("6. Enter para comprobar el juego");
  }
  
  /**
   * inicializa el juego.
   */
  public void initGame() {
    System.out.println(client.conection("Solicito conexion"));
  }
  
  /**
   * Mantiene el juego
   */
  public void play() {
    boolean gameOver = false;
    while (!gameOver) {
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
    //ui.initGame();
    ui.play();
  }
}
