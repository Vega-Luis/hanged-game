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
     game = new HangedGame();
  }
  
  private void ejecutarComando(String comando) {
    if(comando.equals("initGame")) {
      game.startGame();
    }else if(comando.equals("checkletter")) {
      
    }else {
      System.out.println("Comando inválido");
    }
  }

  public void run() {
     Socket socket; 
     while(true) {
        try {
           System.out.println("Esperando cliente en puerto: " + serverSocket.getLocalPort() + "...");
           // Esperar conexiones
           socket = serverSocket.accept();
           
           System.out.println("Se acaba de conectar: " + socket.getRemoteSocketAddress());
           DataInputStream in = new DataInputStream(socket.getInputStream());
           
           System.out.println(in.readUTF());
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
