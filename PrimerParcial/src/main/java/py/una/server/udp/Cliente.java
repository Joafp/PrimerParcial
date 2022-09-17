package py.una.server.udp;
import py.una.entidad.Datos;
import py.una.entidad.DatosJSON;
import java.io.*;
import java.net.*;
import java.io.BufferedReader;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;
public class Cliente {
	public static void main(String[] args){
        Scanner entrada=new Scanner(System.in);
        final int puerto=5000;
        try {
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress IPAddress = InetAddress.getByName("localhost");
            System.out.println("Intentando conectar a = " + IPAddress + ":" + puerto+  " via UDP...");
            byte[] sendData = new byte[1024];
            byte[] receiveData = new byte[1024];
            System.out.println("Ingrese 1 para enviar datos\nIngrese 2 para hacer una consulta de temperatura");
            String aux=entrada.nextLine();
            sendData = aux.getBytes();
            DatagramPacket sendPacket =new DatagramPacket(sendData, sendData.length, IPAddress, puerto);
            clientSocket.send(sendPacket);
            if (Integer.parseInt(aux)==1){
                System.out.print("Ingrese el ID(debe ser numérico): ");
                String strid = inFromUser.readLine();
                Long id=0l;
            	id=Long.parseLong(strid);
                System.out.print("Ingrese la ciudad: ");
                String ciudad = inFromUser.readLine();
                System.out.print("Ingrese el porcentaje de humedad(numerico): ");
                String porcentaje_aux = inFromUser.readLine();
                double porcentaje=Double.parseDouble(porcentaje_aux);
                System.out.print("Ingrese la temperatura(numerico): ");
                String temperatura_aux = inFromUser.readLine();
                double temperatura=Double.parseDouble(temperatura_aux);
                System.out.print("Ingrese la velocidad del viento(numerico): ");
                String velocidad_aux = inFromUser.readLine();
                double velocidad=Double.parseDouble(velocidad_aux);
                System.out.print("Ingrese la fecha(formato dd/mm/año): ");
                String fecha= inFromUser.readLine();
                System.out.print("Ingrese la hora(formato 00:00: ");
                String hora= inFromUser.readLine();
                Datos p = new Datos(id,ciudad,porcentaje,temperatura,velocidad,fecha,hora);
                String datoPaquete = DatosJSON.objetoString(p); 
                sendData = datoPaquete.getBytes();
                System.out.println("Enviar " + datoPaquete + " al servidor. ("+ sendData.length + " bytes)");
                sendPacket =new DatagramPacket(sendData, sendData.length, IPAddress, puerto);
                clientSocket.send(sendPacket);
            }else if(Integer.parseInt(aux)==2){
                System.out.print("Ingrese la ciudad que quiere saber la temperatura: ");
                String ciudad=entrada.nextLine();
                sendData=ciudad.getBytes();
                sendPacket =new DatagramPacket(sendData, sendData.length, IPAddress, puerto);
                clientSocket.send(sendPacket);
                DatagramPacket receivePacket =new DatagramPacket(receiveData, receiveData.length);
                System.out.println("Esperamos si viene la respuesta.");
                //Vamos a hacer una llamada BLOQUEANTE entonces establecemos un timeout maximo de espera
                clientSocket.setSoTimeout(10000);
                try {
                    // ESPERAMOS LA RESPUESTA, BLOQUENTE
                    clientSocket.receive(receivePacket);
                    String respuesta = new String(receivePacket.getData());
                    InetAddress returnIPAddress = receivePacket.getAddress();
                    int port = receivePacket.getPort();
                    System.out.println("Respuesta desde =  " + returnIPAddress + ":" + port);
                    System.out.println("Temperatura de la ciudad: "+respuesta);   
                } catch (SocketTimeoutException ste) {
                    System.out.println("TimeOut: El paquete udp se asume perdido.");
                }
            }
            clientSocket.close();
        } catch (UnknownHostException ex) {
            System.err.println(ex);
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
	
}
