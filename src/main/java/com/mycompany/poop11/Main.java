/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.poop11;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ariana Álvarez, Angel Jimenez, Melissa Matias, Daniel Medrano
 */
public class Main {
    public static void main(String[]args){
        /**
         * Creación de un nuevo archivo en formato .txt
         */
        File archivo = new File("archivo.txt");//objeto de File
        System.out.println(archivo.exists());//existencia de archivo
        
        /**
         * Comprobación de la existencia del archivo creado
         */
        if(!archivo.exists()){
            try {
                boolean seCreo = archivo.createNewFile();//Crear el archivo
                System.out.println(seCreo);
                System.out.println(archivo.exists());
            } catch (IOException ex) {
                ex.getMessage();
                ex.getStackTrace();
            }
        }
        
        /**
         * Parte del código que permite pedirle al usuario un título para el archivo nuevo
         */
        System.out.println("##########FileOutputStream##########");//bytes
        FileOutputStream fos = null;//instancia
        byte[] buffer = new byte[81];//buffer
        int nBytes;
        try {
            System.out.println("Escribir el título del texto a guardar en archivo: ");    
            nBytes = System.in.read(buffer);
            fos = new FileOutputStream("fos.txt");
            fos.write(buffer,0,nBytes);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }finally{
            try {
                if( fos != null)            
                    fos.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        
        /**
         * Parte del código  que imprime en pantalla en título dado por el usuario para el archivo
         */
        System.out.println("##########FileInputStream##########");
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("fos.txt");
            nBytes = fis.read(buffer,0,81);
            String texto = new String(buffer,0,nBytes);
            System.out.println(texto);
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }finally{
            try {
                if (fis!=null)
                    fis.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        
        /**
         * Parte del código que permite pedirle al usuario texto de relleno para el archivo nuevo
         */
        System.out.println("##########FileWriter##########");//Flujo de caracteres
        try {
            BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));//buffer directo para acaracteres
            System.out.println("Escriba texto para el archivo");
            String texto2 = br.readLine();
            
            FileWriter fw = new FileWriter("fw.csv");
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter salida = new PrintWriter(bw);
            
            salida.println(texto2);
            for (int i =0; i<10;i++){
                salida.println(i+" Linea del for");
            }
            for(int i=0; i<5;i++){
                salida.println("Melissa, Matias, Zavala,318070135,18,50");
            }
            salida.close();
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        /**
         * Parte del código que muestra al usuario el contenido del archivo que acaba de crear
         */
        System.out.println("##########FileReader##########");//Flujo de caracteres
        try {
            BufferedReader br ;
            FileReader fr = new FileReader("fw.csv");
            br = new BufferedReader(fr);
            System.out.println("El texto del archivo es: ");
            String linea = br.readLine();
            while(linea != null){
                System.out.println(linea);
                linea = br.readLine();
            }
            br.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        /**
         * Parte del código que con el uso de comas ',' da un salto de linea
         */
        System.out.println("##########StringTokenizer##########");//Flujo de caracteres
        String linea = "Ramiro,Juarez,Perez,3190765215,21,22";
        StringTokenizer tokenizer = new StringTokenizer(linea, ",");
        while(tokenizer.hasMoreTokens()){
            System.out.println(tokenizer.nextToken());
        }
        
        /**
         * Parte del código que permite la lectura de la fecha y hora del día
         */
        System.out.println("##########SerializaciÃ³n##########");//Flujo de caracteres
        
        Date date = new Date();
        System.out.println(date);
        
        try {
            FileOutputStream f = new FileOutputStream("fecha.ser");
            ObjectOutputStream oos = new ObjectOutputStream(f);
            oos.writeObject(date);
            oos.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        /**
         * Parte del código que permite la lectura de la fecha y hora del día partiendo después de
         * una pausa
         */
        System.out.println("##########DeserializaciÃ³n##########");//Flujo de caracteres
        
        try {
            TimeUnit.SECONDS.sleep(10);
            
            Date date2 = new Date();
            System.out.println("La fecha actualizada es: "+date2);
            
            FileInputStream f = new FileInputStream("fecha.ser");
            ObjectInputStream ois = new ObjectInputStream(f);
            Date date3 = (Date) ois.readObject();
            ois.close();
            System.out.println("Objeto deserializado "+date2);
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
        
        /**
         * Parte del código que permite la lectura de un archivo ya existente en nuestra
         * computadora, en este caso un archivo separado por comas ".csv"
         */
        System.out.println("\n••••••• FileReader & Tokenizer •••••••");
        try {
            BufferedReader br;
            FileReader fr = new FileReader("nombresPOO.csv");
            br = new BufferedReader(fr);
            
            System.out.println("El texto del archivo es: ");
            String linea = br.readLine();
            
            while(linea != null){
                StringTokenizer token = new StringTokenizer(linea, ",");
                while(token.hasMoreTokens()){
                    System.out.println(token.nextToken());
                }
                System.out.println(" ");
                linea = br.readLine();
            }
            br.close(); 
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}