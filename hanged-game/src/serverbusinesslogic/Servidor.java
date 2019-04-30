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
  private Socket socket; 
  private String input; 
  
  public Servidor(int puerto, int tamanoCola) throws IOException {
     serverSocket = new ServerSocket(puerto, tamanoCola);
  }
  
  public void run() {
    while(true) {
      try {
        boolean over = true;           
        while(over) {
          System.out.println("Esperando comando");
          socket = serverSocket.accept();       
          DataInputStream in = new DataInputStream(socket.getInputStream());
          input = in.readUTF();
          System.out.println(input);
          DataOutputStream out = new DataOutputStream(socket.getOutputStream());
          out.writeUTF("Éxito\n");
          over = ejecutarComando(input);
        }
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
  
  private boolean ejecutarComando(String comando) {
    if(comando.equals("initGame")) {
      System.out.println("Iniciar");
      iniciarPartida();
    }else if(comando.equals("checkletter")) {
      if(game.equals(null)) {   
        try {
          socket = serverSocket.accept();
          DataOutputStream out = new DataOutputStream(socket.getOutputStream());
          out.writeUTF("No hay una partida iniciada\n");
          socket.close();
          return false;
        } catch (IOException e) {
          e.printStackTrace();
        }
      }else {
        ingresarLetra();
        if(game.getHits() == game.getCurrentWord().length()) {
          try {
            socket = serverSocket.accept();
            DataInputStream in = new DataInputStream(socket.getInputStream());
            input = in.readUTF();
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());         
            out.writeUTF(game.gameWin());
            socket.close();
            game = null;
            return false;
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
        if(game.getFails() == 5) {
          try {
            socket = serverSocket.accept();
            DataInputStream in = new DataInputStream(socket.getInputStream());
            input = in.readUTF();
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());         
            out.writeUTF(game.gameOver());
            socket.close();
            game = null;
            return false;
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      }
    }else {
      System.out.println("Inválido");
      return true;
    }
    return true;
  }

  private void iniciarPartida() {
    try {  
      System.out.println("Username");
      socket = serverSocket.accept();
      DataInputStream in = new DataInputStream(socket.getInputStream());
      input = in.readUTF();
      System.out.println(input);
      game = new HangedGame(input);
      DataOutputStream out = new DataOutputStream(socket.getOutputStream());
      out.writeUTF(game.startGame());
      socket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }   
  }

  private char ingresarLetra() {
    try {
      System.out.println("Esperando Letra");
      socket = serverSocket.accept();
      DataInputStream in = new DataInputStream(socket.getInputStream());
      input = in.readUTF();
      System.out.println(input);  
      DataOutputStream out = new DataOutputStream(socket.getOutputStream());
      out.writeUTF(game.checkLetter(input.charAt(0)));
      socket.close();
      return input.charAt(0);
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
