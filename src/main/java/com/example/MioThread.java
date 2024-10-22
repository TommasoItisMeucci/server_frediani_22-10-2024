package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class MioThread extends Thread{
    private Socket s;
    private int num;

    public MioThread(Socket s, int n) {
        this.s = s; 
        this.num = n;
        
    }
    
    public void run() {

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());   

            int numeroRicevuto = 0;
            int tentativi = 0;
            String risposta = new String();

            do {

                //ricevo numero
                String s = new String();
                s = in.readLine();
                numeroRicevuto = Integer.parseInt(s);

                //controllo
                if(numeroRicevuto > 100 || numeroRicevuto < 0){
                    risposta = "!";
                    out.writeBytes(risposta + "\n");
                }

                if(numeroRicevuto > num) {
                    risposta = ">";
                    tentativi ++;
                    out.writeBytes(risposta + "\n");   

                }else if (numeroRicevuto < num) {
                    risposta = "<";
                    tentativi ++;
                    out.writeBytes(risposta + "\n");   

                }else{
                    risposta = "=";
                    System.out.println(tentativi);
                    out.writeBytes(risposta + "\n");
                    String tent = Integer.toString(tentativi);
                    out.writeBytes(tent + "\n");
                    break;
                }



            } while (true);
        } catch (Exception e) {
            e.printStackTrace();
        }
     
    }

}

    
