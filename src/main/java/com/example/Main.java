package com.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("server avviato!");
        ServerSocket s1 = new ServerSocket(3000);
        

        Random random = new Random();
        int numero = random.nextInt(100);
        System.out.println(numero);



        do {
            Socket s = s1.accept();
            System.out.println("collegato");
            

           MioThread t = new MioThread(s, numero);
           t.start();
        } while (true);
    }
}