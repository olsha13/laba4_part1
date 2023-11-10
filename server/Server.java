package server;

import java.io.*;
import java.net.*;

public class Server 
{
    public static void main(String[] arg) 
    {
        ServerSocket serverSocket = null;
        Socket clientAccepted     = null;
        ObjectInputStream  sois   = null;//объявление байтового потока ввода
        ObjectOutputStream soos   = null;//объявление байтового потока вывода
        try {
            System.out.println("server starting....");
            serverSocket = new ServerSocket(2525);//создание сокета сервера для //заданного порта
            clientAccepted = serverSocket.accept();//выполнение метода, который //обеспечивает реальное подключение сервера к клиенту
            System.out.println("connection established....");
            //создание потока ввода soos = new 
            sois = new ObjectInputStream(clientAccepted.getInputStream()); 
            soos = new ObjectOutputStream(clientAccepted.getOutputStream());//создание потока 
            
            double mas[][] = new double[3][3];

            for(int i = 0;i<3;i++)
            {
                for(int j = 0;j<3;j++)
                {
                    mas[i][j] = (double)sois.readObject();
                }
            }

            getReduction(mas, 1);
            System.out.println("Определитель матрицы"+summ);

            soos.writeObject(summ);
        }
        catch(Exception e)	{
	    } 
        finally {
	        try {
                sois.close();//закрытие потока ввода
                soos.close();//закрытие потока вывода
                clientAccepted.close();//закрытие сокета, выделенного для клиента
                serverSocket.close();//закрытие сокета сервера
            } 
            catch(Exception e) {
                e.printStackTrace();//вызывается метод исключения е
            }
        }
    }

    private static double summ;

    public static void getReduction(double [][] subMinor, double elemParentMinor) {
        if (subMinor.length > 1){
            double [][] tmpMinor = new double[subMinor.length - 1][subMinor[0].length - 1];
            for (int c = 0; c < subMinor[0].length; c++) {
                for (int i = 1; i < subMinor.length; i++) {
                    for (int j = 0; j < subMinor[0].length; j++) {
                        if (j < c)
                            tmpMinor[i - 1][j] = subMinor[i][j];
                        else if (j > c)
                            tmpMinor[i - 1][j - 1] = subMinor[i][j];
                    }
                }
                double paramForSub = Math.pow(-1,c+2)*subMinor[0][c]*elemParentMinor;
                getReduction(tmpMinor, paramForSub);
            }
        }
        else
            summ += elemParentMinor * subMinor[0][0];
    }	
}
