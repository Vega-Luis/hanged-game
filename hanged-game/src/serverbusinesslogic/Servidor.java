package serverbusinesslogic;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import serverbusinesslogic.HangedGame;

public class Servidor {
  private ServerSocket serverSocket;
  private HangedGame game;
  
  public Servidor(int puerto, int tamanoCola) throws IOException {
     serverSocket = new ServerSocket(puerto, tamanoCola);
  }
  
  private boolean ejecutarComando(String comando) {
    if(comando.equals("initGame")) {
      game = new HangedGame();
      game.startGame();
    }else if(comando.equals("checkletter")) {
      if(game.equals(null)) {
        System.out.println("No hay una partida iniciada\n");
        return false;
      }else {
        game.checkLetter(ingresarLetra(), game.getCurrentWord());
        if(game.getHits() == game.getCurrentWord().length()) {
          game = null;
          return false;
        }
        if(game.getFails() == 5) {
          game.gameOver();
          game = null;
          return false;
        }
      }
    }else {
      System.out.println("Comando inválido");
      return true;
    }
    return true;
  }

  public void run() {
     Socket socket; 
     while(true) {
       try {
         System.out.println("Esperando cliente en puerto: " + serverSocket.getLocalPort() + "...");
         // Esperar conexiones
         socket = serverSocket.accept();
         System.out.println("Se acaba de conectar: " + socket.getRemoteSocketAddress());
         boolean over = true;
         while(over) {
           System.out.println("Ingrese un comando: ");
           DataInputStream in = new DataInputStream(socket.getInputStream());
           System.out.println();
           over = ejecutarComando(in.readUTF());
         }
     
         //System.out.println(in.readUTF());
         DataOutputStream out = new DataOutputStream(socket.getOutputStream());
         out.writeUTF("Thank you for connecting to " + socket.getLocalSocketAddress()
         + "\nGoodbye!");
         socket.close(); 
        } 
        catch (SocketTimeoutException s) {
          System.out.println("Socket timed out!");
          break;
        } 
       catch (IOException e) {
         e.printStackTrace();
         break;
        }
     }
  }
  
  private char ingresarLetra() {
    Socket socket = null;
    try {
      socket = serverSocket.accept();
    } catch (IOException e) {
      e.printStackTrace();
    }
    DataInputStream in = null;
    try {
      in = new DataInputStream(socket.getInputStream());
    } catch (IOException e) {
      e.printStackTrace();
    }
    try {
      return in.readChar();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return 0; 
  }
  public static void main(String[] args) {
     int puerto = Integer.parseInt(args[0]);
     int cola = Integer.parseInt(args[1]);
     
     try {
        Servidor s = new Servidor(puerto, cola);
        s.run();
     } catch (IOException e) {
        e.printStackTrace();
     }
  }
}
