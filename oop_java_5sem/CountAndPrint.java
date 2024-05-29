
package com.mycompany.oop_l2;

import java.lang.reflect.*;
import java.util.Arrays;
import java.io.*;
import java.util.Scanner;


public class CountAndPrint {
    public static double averageValue(Transportable tran) {
        double ans = 0;
        int[] prices = tran.getPriceArr();
        for (int i = 0; i < prices.length; i++) {
            ans += prices[i];

        }
        ans = ans / prices.length;
        return ans;
    }

    public static final double totalAverage(Transportable[] cars) {
        double ans = 0;
        int count = 0;
        for (int j = 0; j < cars.length; j++) {
            int[] prices = cars[j].getPriceArr();
            for (int i = 0; i < prices.length; i++) {
                ans += prices[i];
            }
            count += prices.length;
        }
        return ans / count;
    }

    public static final Transportable reflexTran(String mark, int size, Transportable tran) {
        Class c = tran.getClass();
        try {
            Constructor constr = c.getConstructor(int.class, String.class);
            Transportable newtran = (Transportable) constr.newInstance(size, mark);
            return newtran;
        } catch (NoSuchMethodException e) {
            return null;
        } catch (Exception i) {
            System.out.println("Error!");
            return null;
        }
    }

    public static void reflexModification(Transportable tran) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter a class name:");
        String classname = scan.nextLine();
        Class c;
        try {
            c = Class.forName(classname);
        } catch (ClassNotFoundException e1) {
            System.out.println("Wrong class name!!!");
            return;
        }

        System.out.println("Enter a method name:");
        String methodname = scan.nextLine();
        try {
            Method method = c.getMethod(methodname, String.class, int.class);

            System.out.println("Enter first parameter:");
            String fp = scan.nextLine();
            System.out.println("Enter second parameter:");
            int sp = scan.nextInt();

            System.out.println(method.invoke(tran, fp, sp));
            System.out.println("Result:");
            System.out.println(tran.toString());

        } catch (NoSuchMethodException e1) {
            System.out.println("Error! No founded method");
        } catch (IllegalAccessException | InvocationTargetException e2) {
            System.out.println("Wrong parameters!");
        }

    }

    public static final void printModelAndPrices(Transportable tran) {
        int[] prices = tran.getPriceArr();
        String[] models = tran.getModelNameArr();
        for (int i = 0; i < prices.length; i++) {
            System.out.println(models[i] + ", price:" + prices[i]);
        }
    }
    //byte stream writing (L3)
    public static void outputTran(Transportable tran, OutputStream out) throws IOException {
        DataOutputStream dos = new DataOutputStream(out);
        byte[] tranClass = tran.getClass().getName().getBytes();
        dos.writeInt(tranClass.length);
        for (byte vc : tranClass)
            dos.writeByte(vc);
        byte[] mark = tran.getMark().getBytes();
        dos.writeInt(mark.length);
        for (byte b : mark)
            dos.writeByte(b);
        int[] prices = tran.getPriceArr();
        String[] models = tran.getModelNameArr();
        dos.writeInt(tran.getLength());
        for (int i = 0; i < tran.getLength(); i++) {
            byte[] name = models[i].getBytes();
            dos.writeInt(name.length);
            for (byte symb : name) {
                dos.writeByte(symb);
            }
            dos.writeInt(prices[i]);
        }

    }
    //byte stream reading
    public static Transportable inputTran(InputStream in) throws IOException, DuplicateModelNameException, ModelPriceOutOfBoundsException {
        DataInputStream dis = new DataInputStream(in);
        byte[] byteClass = new byte[dis.readInt()];
        for (int i = 0; i < byteClass.length; i++)
            byteClass[i] = dis.readByte();
        byte[] byteMark = new byte[dis.readInt()];
        for (int i = 0; i < byteMark.length; i++)
            byteMark[i] = dis.readByte();
        String tranClass = new String(byteClass);
        Transportable tran = null;
        switch (tranClass) {
                case ("class com.mycompany.oop_l2.Car"):
                    tran = new Car();
                    tran.modMark(tranMark);
                    break;
                case ("class com.mycompany.oop_l2.Motobike"):
                    tran = new Motobike();
                    tran.modMark(tranMark);
                    break;
                case ("class com.mycompany.oop_l2.Scooter"):
                    tran = new Scooter();
                    tran.modMark(tranMark);
                    break;
                case ("class com.mycompany.oop_l2.Moped"):
                    tran = new Moped();
                    tran.modMark(tranMark);
                    break;
                case ("class com.mycompany.oop_l2.Quadbike"):
                    tran = new Quadbike();
                    tran.modMark(tranMark);
                    break;
        }
        tran.modMark(new String(byteMark));
        int numModels = dis.readInt();
        if (tran != null) {
            for (int i = 0; i < numModels; i++) {
                int sizeModel = dis.readInt();
                byte[] byteModel = new byte[sizeModel];
                for (int j = 0; j < sizeModel; j++)
                    byteModel[j] = dis.readByte();
                String tranModel = new String(byteModel);
                tran.addNewModel(tranModel, dis.readInt());
            }
        }

        return tran;
    }
    //text streams (L5 ? idk)
    public static void writeViaPrintf(Transportable tran, Writer out) {
        try (PrintWriter writer = new PrintWriter(out)) {


            writer.printf("Class: %s%n", tran.getClass());
            writer.printf("Mark: %s%n", tran.getMark());
            writer.printf("Models Count: %d%n", tran.getLength());

            for (int i = 0; i < tran.getLength(); i++) {
                writer.printf("Model: %s, Price: %d%n", tran.getModelNameArr()[i], tran.getPriceArr()[i]);
            }
        }

    }

    public static Transportable readViaScanner(Reader in) throws DuplicateModelNameException {
        Transportable tran = null;
        try (Scanner scan = new Scanner(in)) {

            String tranClass = scan.nextLine().split(":")[1].trim();
            String tranMark = scan.nextLine().split(":")[1].trim();
            int tranLen = Integer.parseInt(scan.nextLine().split(":")[1].trim());

            switch (tranClass) {
                case ("class com.mycompany.oop_l2.Car"):
                    tran = new Car();
                    tran.modMark(tranMark);
                    break;
                case ("class com.mycompany.oop_l2.Motobike"):
                    tran = new Motobike();
                    tran.modMark(tranMark);
                    break;
                case ("class com.mycompany.oop_l2.Scooter"):
                    tran = new Scooter();
                    tran.modMark(tranMark);
                    break;
                case ("class com.mycompany.oop_l2.Moped"):
                    tran = new Moped();
                    tran.modMark(tranMark);
                    break;
                case ("class com.mycompany.oop_l2.Quadbike"):
                    tran = new Quadbike();
                    tran.modMark(tranMark);
                    break;

            }
            for (int i = 0; i < tranLen; i++) {
                String[] aboutmodel = scan.nextLine().split(",");
                String newname = aboutmodel[0].split(":")[1].trim();
                int newprice = Integer.parseInt(aboutmodel[1].split(":")[1].trim());
                tran.addNewModel(newname, newprice);
            }
            return tran;
        }
    }
    
/*
    public static void writeTransportable (Transportable tran, Writer out)
    {
        PrintWriter printWriter = new PrintWriter(out);

        printWriter.println(tran.getClass().getName());
        printWriter.println(tran.getMark());
        printWriter.println(tran.getLength());

        String[] mnames = tran.getModelNameArr();
        int[] prices = tran.getPriceArr();
        for (int i = 0; i < tran.getLength(); i++){
            printWriter.println(mnames[i]);
            printWriter.println(prices[i]);
        }
        printWriter.flush();
    }
    
    public static Transportable readTransportable (Reader in) throws IOException, DuplicateModelNameException {
        BufferedReader bufferedReader = new BufferedReader(in);

        Transportable tran = null;

        String tranClass = bufferedReader.readLine();

        String mark = bufferedReader.readLine();
        if (tranClass.equals("com.mycompany.oop_l2.Car"))
            tran = new Car();
        else if (tranClass.equals("com.mycompany.oop_l2.Motobike"))
            tran = new Motobike();
        tran.modMark(mark);

        int len = Integer.parseInt(bufferedReader.readLine());
 
        for (int i = 0; i < len; i++) {
            String name = bufferedReader.readLine();
            int price = Integer.parseInt(bufferedReader.readLine());
            tran.addNewModel(name, price);
        }
        
        return tran;
    }
*/
}
