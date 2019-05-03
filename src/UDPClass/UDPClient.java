package UDPClass;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPClient {
    public static void main(String[] args) throws IOException
    {
        /**
         * 1.new scoket
         * 2.receive packet
         * 3.receive
         * 4.close?
         * */
        byte[] data=new byte[1024];
        DatagramPacket datagramPacket=new DatagramPacket(data,data.length);

        while(true)
        {
            DatagramSocket datagramSocket=new DatagramSocket(8887);
            datagramSocket.receive(datagramPacket);
            InetAddress serverAddress=datagramPacket.getAddress();
            System.out.println("Server Says:"+"-----"+new String(datagramPacket.getData(),0, datagramPacket.getLength()));
            datagramSocket.close();

            DatagramSocket sendScoket=new DatagramSocket();
            Scanner scan=new Scanner(System.in);
            String input=scan.nextLine();
            byte[] sendBytes=input.getBytes();
            DatagramPacket sendPacket=new DatagramPacket(sendBytes,sendBytes.length,serverAddress,8888);
            sendScoket.send(sendPacket);

            sendScoket.close();

        }

    }
}
