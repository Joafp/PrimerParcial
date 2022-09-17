package py.una.server.udp;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import py.una.entidad.Datos;
import py.una.entidad.DatosJSON;
public class Servidor {
	public static void main(String []args) {
		int puerto=5000;
        try {
            //1) Creamos el socket Servidor de Datagramas (UDP)
            List<Datos> lista = new ArrayList<Datos>();
            DatagramSocket serverSocket = new DatagramSocket(puerto);
			System.out.println("Servidor Datos de temperatura");
            //2) buffer de datos a enviar y recibir
            byte[] receiveData = new byte[1024];
            byte[] sendData = new byte[1024];
            //3) Servidor siempre esperando
            while (true) {
                receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                System.out.println("Esperando a algun cliente...\nOpcion 1 ingresar datos\nOpcion 2 enviar temperatura ");
                // 4) Receive LLAMADA BLOQUEANTE
                serverSocket.receive(receivePacket);
				System.out.println("________________________________________________");
                System.out.println("Aceptamos un paquete");
                // Datos recibidos e Identificamos quien nos envio
                String datoRecibido = new String(receivePacket.getData());
                datoRecibido = datoRecibido.trim();
                System.out.println("Opcion ingresada " + datoRecibido );
                int opcion=Integer.parseInt(datoRecibido);
                if(opcion==1){
                    serverSocket.receive(receivePacket);
                    InetAddress IPAddress=receivePacket.getAddress();
                    int port=receivePacket.getPort();
                    datoRecibido = new String(receivePacket.getData());
                    datoRecibido = datoRecibido.trim();
                    Datos p=DatosJSON.stringObjeto(datoRecibido);
                    System.out.println("De: "+IPAddress+":"+port);
                    System.out.println("Datos recibidos :"+p.getid()+","+p.getciudad()+","+p.getporcentaje()+","+p.gettemperatura()+","+p.getvelocidad()+","+p.getfecha()+","+p.gethora());
                    lista.add(p);
                    System.out.println("Persona agregada a la lista" );
                    sendData = DatosJSON.objetoString(p).getBytes();
                    DatagramPacket sendPacket =new DatagramPacket(sendData, sendData.length, IPAddress,port);
                    serverSocket.send(sendPacket);
                }else if (opcion==2){
                    serverSocket.receive(receivePacket);
                    boolean encontrado=false;
                    datoRecibido = new String(receivePacket.getData());
                    datoRecibido = datoRecibido.trim();
                    int pos=0;
                    for(int x=0;x<lista.size();x++){
                        if(lista.get(x).getciudad().equals(datoRecibido)){
                            encontrado=true;
                            pos=x;
                            break;
                        }
                    }     
                    if (encontrado==true){
                        String temp=String.valueOf(lista.get(pos).gettemperatura());
                        sendData=temp.getBytes();
                        InetAddress IPAddress=receivePacket.getAddress();
                        int port=receivePacket.getPort();
                        DatagramPacket sendPacket =new DatagramPacket(sendData, sendData.length, IPAddress,port);
                        serverSocket.send(sendPacket);
                    }
                }
            }

        } catch (Exception ex) {
        	ex.printStackTrace();
            System.exit(1);
        }

    }
}