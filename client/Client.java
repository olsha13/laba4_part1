package client;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] arg) { 
        try {
            System.out.println("server connecting....");
            Socket clientSocket = new Socket("127.0.0.1",2525);//установление //соединения между локальной машиной и указанным портом узла сети
            System.out.println("connection established....");
            BufferedReader stdin = 
            new BufferedReader(new InputStreamReader(System.in));//создание 
            //буферизированного символьного потока ввода
            ObjectOutputStream coos = 
            new ObjectOutputStream(clientSocket.getOutputStream());//создание 
            //потока вывода
            ObjectInputStream  cois = 
            new ObjectInputStream(clientSocket.getInputStream());//создание 
            //потока ввода

            Scanner in = new Scanner(System.in);;

            double mas[][] = new double[3][3];
            System.out.println("Введите числа в матрицу 3 на 3");

            for(int i = 0;i<3;i++)
            {
                for(int j = 0;j<3;j++)
                {
                    System.out.println(i+1+") строки "+1+j+") столбца");
                    mas[i][j] = in.nextDouble();
                    coos.writeObject(mas[i][j]);
                }
            }
            System.out.println("Обределитель матрицы: "+cois.readObject());

            coos.close();//закрытие потока вывода
            cois.close();//закрытие потока ввода
            clientSocket.close();//закрытие сокета
        }
        catch(Exception e)	{
            e.printStackTrace();//выполнение метода исключения е
        }
    }
}
