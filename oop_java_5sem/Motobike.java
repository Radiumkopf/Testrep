
package com.mycompany.oop_l2;

import java.util.Random;
import java.io.Serializable;

public class Motobike implements Transportable, Serializable, Cloneable {
        private class BikeModel implements Serializable{            //Node
            String bmodelname = null;
            int bprice = 0;
            BikeModel prev = null;
            BikeModel next = null;
        } 
    private int size = 0;
    private final BikeModel head;
    private transient long lastModified;
    private String mark;
    {    
        lastModified = System.currentTimeMillis();
    }
    
    public Motobike(){
        head = new BikeModel();
        head.next = head;
        head.prev = head;
    }

    public Motobike(int _size, String mark){
        this.mark = mark;
        head = new BikeModel();
        head.next = head;
        head.prev = head;
        this.size=_size;
        BikeModel bm;
        for (int i = 0; i < size; i++) {          
            
            Random r = new Random();
            char modelletter = (char)('A' + r.nextInt(26) );
            String modelname = "M" + String.valueOf(modelletter) + " - " + String.valueOf(r.nextInt(99));
            int price = r.nextInt(10000)+1;
            bm = new BikeModel();
            NodeCreateHelp(price, modelname, bm);
     
        }
    }
    private void NodeCreateHelp(int price, String name, BikeModel p){
        p.bmodelname=name; p.bprice = price;
        p.next = head;
        p.prev = head.prev;
        head.prev.next = p;
        head.prev = p;
    }
    @Override
    public String toString(){
        StringBuffer str = new StringBuffer("Class: Motobike\n");
        str.append(mark);
        BikeModel p = head.next;
        while(p!=head){
            str.append("\n Model: " + p.bmodelname+ " Price: " + p.bprice);
            p=p.next;
        }
        return str.toString();
    }
    @Override
    public boolean equals(Object obj){
        if(obj instanceof Motobike){
            if(this.mark.equals(((Motobike) obj).getMark())){
                if(this.size == ((Motobike) obj).getLength()){
                    int[] verifyingprises = ((Motobike) obj).getPriceArr();
                    String[] verifyingnames = ((Motobike) obj).getModelNameArr();
                    BikeModel p = head.next;
                    for (int i = 0; i < size; i++) {
                        if(!(p.bprice==verifyingprises[i] && p.bmodelname.equals(verifyingnames[i]))){
                            return false;
                        }
                        p=p.next;
                    }
                    return true;
                }
                else return false;
            }
            else return false;
        }
        else return false;
    }
    @Override
    public int hashCode(){
        int code=0;
        
        if(mark!=null){
            code =mark.hashCode();
        }
        if(getLength()!=0){
            code =29*code +getLength();
            BikeModel p = head.next;
            while(p!=head){
                code=29*code+ p.bprice;
                code = 29*code+p.bmodelname.hashCode();
            }
        }
        return code;
    }
    @Override
    public Object clone()throws CloneNotSupportedException
    {
        Motobike clone;

        try {clone = (Motobike) super.clone();}
        catch(CloneNotSupportedException ex){return null;}
        clone.mark = this.mark;
        BikeModel sourcemodels = head.next;

            clone.head.prev = clone.head;
            clone.head.next = clone.head;

            while (sourcemodels != head)
            {
                BikeModel newclonemodel = new BikeModel();
                newclonemodel.bmodelname = sourcemodels.bmodelname; newclonemodel.bprice = sourcemodels.bprice;
                newclonemodel.next = clone.head;
                newclonemodel.prev = clone.head.prev;
                clone.head.prev.next = newclonemodel;
                clone.head.prev = newclonemodel;
                sourcemodels = sourcemodels.next;
            }
        return clone;


    }
    
    
    
    public void modModelName(String oldname, String newname)throws NoSuchModelNameException, DuplicateModelNameException{            //task7
        BikeModel p = head.next;
        while (p != head )
        {
            if(newname.equals(p.bmodelname))
            {throw new DuplicateModelNameException(newname);}
            p = p.next;
        }
        p=head.next;
        while (p != head && !oldname.equals(p.bmodelname))
        {p = p.next;}
        if(p==head){
            throw new NoSuchModelNameException(oldname);
        }
        else{p.bmodelname=newname; lastModified = System.currentTimeMillis();}
        
    }
    public String[] getModelNameArr(){              
        String[] modelnamearr = new String[size];
        BikeModel p = head.next;
        int i =0;
        while (p != head)
        {
            modelnamearr[i]=p.bmodelname;
            i+=1;
            p = p.next;
        }
        return modelnamearr;
    }
    public int getPriceViaName(String name)throws NoSuchModelNameException{            //task7
        int ans = 0;
        BikeModel p = head.next;
        while (p != head && !name.equals(p.bmodelname))
        {p = p.next;}
        if(p==head){
            throw new NoSuchModelNameException(name);
        }
        else{ans=p.bprice;}
        return ans;
    }
    public void modPriceViaName(String name, int newprice)throws NoSuchModelNameException{            //task8
        if(newprice<=0){
           throw new ModelPriceOutOfBoundsException();
        }
        BikeModel p = head.next;
        while (p != head && !name.equals(p.bmodelname))
        {p = p.next;}
        if(p==head){
            throw new NoSuchModelNameException(name);
        }
        else{p.bprice=newprice;lastModified = System.currentTimeMillis();}
    }
    public int[] getPriceArr(){              
        int[] pricearr = new int[size];
        BikeModel p = head.next;
        int i =0;
        while (p != head)
        {
            pricearr[i]=p.bprice;
            i+=1;
            p = p.next;
        }
        return pricearr;
    }
    public void addNewModel(String name, int price )throws DuplicateModelNameException, ModelPriceOutOfBoundsException     
        {
            if(price<=0){
                throw new ModelPriceOutOfBoundsException();
            }
            BikeModel p = head.next;
            while (p != head )
            {
                p = p.next;
                if(name.equals(p.bmodelname)){
                    throw new DuplicateModelNameException(name);
                }    
            }
            p=new BikeModel();
            NodeCreateHelp(price, name, p);
            this.size+=1;
            lastModified = System.currentTimeMillis();
        }
    public void deleteModel(String name) throws NoSuchModelNameException{
        BikeModel p = head.next;
        while (p != head && !name.equals(p.bmodelname))
        {p = p.next;}
        if(p==head){
            throw new NoSuchModelNameException(name);
        }
        else{
            p.prev.next = p.next;
            p.next.prev = p.prev;
            this.size-=1;
            lastModified = System.currentTimeMillis();
        }
    }
        
    
    public void printModels()
        {
                BikeModel p = head.next;
                while (p != head)
                {
                    System.out.println( '(' + p.bmodelname + ", " + p.bprice+ ')');
                    
                    p = p.next;
                }
        }
    public String getMark(){            
        return mark;
    }
    public void modMark(String newmark){    
        mark=newmark;
    }
    public int getLength(){
        return size;
    }
    public long getTimeLastMod(){
        return lastModified;
    }
    
    

}
