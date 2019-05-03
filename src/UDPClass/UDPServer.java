package UDPClass;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPServer {
    public static void main(String[] args) throws IOException
    {
        /**
         * 1.new a scoket
         * 2.packet data
         * 3.transmit data
         * 4.close scoket
         * */
        final int port=8887;
        final int receivePort=8888;
        InetAddress address=InetAddress.getByName("localhost");
        byte[] receiveBytes=new byte[1024];
        while(true)
        {
            Scanner scan=new Scanner(System.in);
            String input=scan.nextLine();
            byte[] data=input.getBytes();
            DatagramSocket socket=new DatagramSocket();
            DatagramPacket datagramPacket=new DatagramPacket(data,data.length,address,port);
            socket.send(datagramPacket);
            socket.close();

            DatagramSocket receiveScoket=new DatagramSocket(receivePort);
            DatagramPacket receivePacket=new DatagramPacket(receiveBytes,receiveBytes.length);
            receiveScoket.receive(receivePacket);
            System.out.println("ClientSays-------"+new String(receivePacket.getData(),0,receivePacket.getLength()));

            receiveScoket.close();

        }

    }

}
