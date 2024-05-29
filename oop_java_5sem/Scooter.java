
package com.mycompany.oop_l2;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;
import java.util.HashMap;


public class Scooter implements Transportable, Serializable, Cloneable{
    private String mark;
    private HashMap<String, ScModel> models;
    
    public Scooter(){
        models = new HashMap<>();
    }
    
    public Scooter( int mapsize, String mark){
        this.mark = mark;
        models = new HashMap<>();
        for (int i = 0; i < mapsize; i++) {
            
            Random r = new Random();
            char modelletter = (char)('A' + r.nextInt(26) );
            String modelname = String.valueOf(modelletter) + " - " + String.valueOf(r.nextInt(99));

            int price = r.nextInt(10000)+1;
            ScModel model = new ScModel(modelname, price);
            models.put(modelname, model);
            
        }
    }
    public class ScModel implements Serializable {         //task4
    
        String modelname;
        int price;
        
        public ScModel(String modelname, int price){
            this.modelname=modelname;
            this.price=price;
        }
        
    }
    
    
    @Override
    public String toString(){
        StringBuffer str = new StringBuffer("Class: Scooter\n");
        str.append(mark);
        models.forEach((k,value) ->{
      
            str.append("\n Model: " + value.modelname+ " Price: " + value.price);
        });
        return str.toString();
    }
    @Override
    public boolean equals(Object obj){                     //4th lab task 2
        
        if(obj instanceof Scooter){
            if(this.mark.equals(((Scooter) obj).getMark())){
                if(this.models.size() == ((Scooter) obj).getLength()){
                    int[] verifyingprises = ((Scooter) obj).getPriceArr();
                    String[] verifyingnames = ((Scooter) obj).getModelNameArr();
                    Object[] keys = models.keySet().toArray();
                    for(int i =0; i<models.size();i++){
                        if(!(models.get(keys[i]).modelname.equals(verifyingnames[i])&&verifyingprises[i] == models.get(keys[i]).price))
                        {return false;}
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
        int code =29;
        
        if(mark!=null){
            code =mark.hashCode();
        }

        if(models.size()!=0){
            code =29*code +models.size();
            Object[] keys = models.keySet().toArray();
            for(int i =0; i<models.size();i++){
                code += models.get(keys[i]).modelname.hashCode();
                code += models.get(keys[i]).price;
            }    
            
        }
        return code;
    }
    @Override
    public Object clone()throws CloneNotSupportedException
    {
        Scooter clone;

        try {clone = (Scooter) super.clone();}
        catch(CloneNotSupportedException ex){return null;}
        models.forEach((key,value) ->{
            clone.models.put(key, value);
        });

        return clone;
    }
    
    
    
    public void modModelName(String oldname, String newname)throws NoSuchModelNameException, DuplicateModelNameException{

        if(models.containsKey(newname)){
            throw new DuplicateModelNameException(newname);
        }            

        if(models.containsKey(oldname)){
            models.get(oldname).modelname = newname;
        }    
        else{
            throw new NoSuchModelNameException(oldname);
        }
    }
    public String[] getModelNameArr(){              //task6
        String[] values = new String[models.size()];

        Object[] keys = models.keySet().toArray();
        for(int i =0; i<models.size();i++){
            values[i] = models.get(keys[i]).modelname;
        }
       
        return values;
    }

    public int getPriceViaName(String name)throws NoSuchModelNameException{            //task7

        if(models.containsKey(name)){
            return models.get(name).price;
        }
        else{
            throw new NoSuchModelNameException(name);
        }

    }

    public void modPriceViaName(String name, int newprice)throws NoSuchModelNameException, ModelPriceOutOfBoundsException{   //task8
        if(newprice<0){
            throw new ModelPriceOutOfBoundsException();
        }
        if(models.containsKey(name)){
            models.get(name).price=newprice;
        }
        else{
            throw new NoSuchModelNameException(name);
        }
        
    }
    public int[] getPriceArr(){              //task9
        int[] values = new int[models.size()];

        Object[] keys = models.keySet().toArray();
        for(int i =0; i<models.size();i++){
            values[i] = models.get(keys[i]).price;
        }
       
        return values;
    }
    public void addNewModel(String name, int price) throws DuplicateModelNameException, ModelPriceOutOfBoundsException{        //task10
        if(price<=0){
            throw new ModelPriceOutOfBoundsException();
        }
        if(models.containsKey(name)){
            throw new DuplicateModelNameException(name);
        }
        ScModel newsc = new ScModel(name, price);
        models.put(name, newsc );
    }
    
    public void deleteModel(String name) throws NoSuchModelNameException{                   //task11

        if(!models.containsKey(name)){
            throw new NoSuchModelNameException(name);
        }
        else{
            models.remove(name);
        }
        
    }
   
    
    public int getLength(){
        return models.size();
    }
   
    public String getMark(){            //task2
        return mark;
    }
    
    public void modMark(String newmark){    //task3
        mark=newmark;
    }
}
