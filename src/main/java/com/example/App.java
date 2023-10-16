package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class App 
{
    public static void main( String[] args )
    {
        try {
            System.out.println("il server è stato avviato");
            int rand = (int) (Math.random() * 100);
            System.out.println("il numero da indovinare è: " + rand);
            System.out.println("in attesa della connessione di un client...");
            ServerSocket server = new ServerSocket(6790);
            Socket s = server.accept();
            System.out.println("un client si è connesso");
        
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            String numeroRicevuto;
            int num = 0;
            int n = 0;
            do {
                n++;
                numeroRicevuto = in.readLine();
                num = Integer.parseInt(numeroRicevuto);
                System.out.println("Il client ha inviato " + numeroRicevuto);
                if(num > rand && num != 0)
                {
                    System.out.println("1");
                    out.writeBytes("1" + '\n');
                }
                else if(num < rand && num != 0)
                {
                    System.out.println("2");
                    out.writeBytes("2" + '\n');
                }
                else if(num > 99 || num < 0)
                {
                    System.out.println("4");
                    out.writeBytes("4" + '\n');
                }
            } while (num != rand);
                System.out.println("3");
                out.writeBytes("3" + '\n');
                System.out.println(n);
                server.close();
                s.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del server !");
            System.exit(1);
        }
    }
}
