package UDPClass;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class UDPClient {
    final static int clientPort=8888;
    final static int serverPort=8887;
    static InetAddress address;
    static {
        try{
            address=InetAddress.getByName("localhost");
        }
        catch (IOException e) {}
    }


    public static void main(String[] args) throws IOException
    {
        while(true)
        {
            new Thread()
            {
                public void run()
                {
                    Scanner scan=new Scanner(System.in);
                    byte[] bytes=scan.nextLine().getBytes();
                    sendMessage(bytes,address,clientPort);
                }
            }.start();

            new Thread()
            {
                public void run()
                {
                    receiveMessage(serverPort);
                }
            }.start();
        }

    }


    public static void sendMessage(byte[] bytes,InetAddress address,int port)
    {
        try
        {
            DatagramPacket packet=new DatagramPacket(bytes,bytes.length,address,port);
            DatagramSocket scoket=new DatagramSocket();
            scoket.send(packet);
            scoket.close();
        }
        catch (IOException e)
        {
            System.out.println("fault trasmit");
        }
    }
    public static void receiveMessage(int port)
    {
        try
        {
            byte[] bytes=new byte[20];
            DatagramPacket packet=new DatagramPacket(bytes,bytes.length);
            DatagramSocket socket=new DatagramSocket(port);
            socket.receive(packet);
            System.out.println("Server Says-------"+new String(packet.getData(),0,packet.getLength()));
            socket.close();
        }
        catch (IOException e)
        {

        }

    }

}
