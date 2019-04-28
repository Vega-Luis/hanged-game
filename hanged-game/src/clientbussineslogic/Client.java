package clientbussineslogic;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Clase permite establecer conexion con un servidor.
 * @author Luis
 * @version v4.28.19
 */
public class Client {
  private Socket socket;
  private String serverName;
  private int port;
  
  /**
   * Constructor de objetos de clase Cliente.
   * @param serverName Direccion ip del servidor.
   * @param port Puerto en el que se establecera la conexion.
   */
  public Client(String serverName, int port) {
    this.serverName = serverName;
    this.port = port;
  }
  
  /**
   * Establece una conexion con el servidor.
   * Permite el intercambio de strings con el servidor.
   * @param outMensaje Mensaje que sera enviado al servidor.
   * @return Retorna el mensaje recibido del servidor.
   * @throws UnknownHostException
   * @throws IOException
   */
  public String conection(String outMensaje) {
    try {
      this.socket = new Socket(this.serverName, this.port);

      OutputStream outToServer = socket.getOutputStream();
      DataOutputStream out = new DataOutputStream(outToServer);
      out.writeUTF(outMensaje);

      InputStream inFromServer = socket.getInputStream();
      DataInputStream in = new DataInputStream(inFromServer);

      this.socket.close();
      return in.readUTF();
    }
    catch (UnknownHostException e) {
      return "No es posible encontrar el servidor";
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    return "";
  }
}
