package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class MioThread {
    private Socket s;
    private int number = 0;

    public MioThread(Socket s, int num) {
        this.s = s;
        this.number = num;
    }
    
    public void start() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        DataOutputStream out = new DataOutputStream(s.getOutputStream());   
        System.out.println("client collegato");

        int numeroRicevuto = 0;
        int tentativi = 0;
        String risposta = new String();



        do {
            //ricevo numero
            numeroRicevuto = in.read();
            
            //controllo
            if(numeroRicevuto > 100 && numeroRicevuto < 0){
                risposta = "!";
                out.writeBytes(risposta + "\n");
            }

            if (numeroRicevuto > number) {
                risposta = ">";
                System.out.println(numeroRicevuto + number);
                tentativi ++;
                out.writeBytes(risposta + "\n");    
            }else if (numeroRicevuto < number) {
                risposta = "<";
                System.out.println(numeroRicevuto + number);
                tentativi ++;
                out.writeBytes(risposta + "\n");   
            }else{
                risposta = "=";
                System.out.println(numeroRicevuto + number);
                out.writeBytes(risposta + "\n");
            }



        } while (true);


    }
}

    
