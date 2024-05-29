
package com.mycompany.oop_l2;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;
import java.util.ArrayList;

public class Quadbike implements Transportable, Serializable, Cloneable{
    private String mark;
    private ArrayList<QuadModel> models;
    
    public Quadbike(){
        models = new ArrayList<>();
    }
    
    public Quadbike( int arraysize, String mark){
        this.mark = mark;
        models = new ArrayList<>();
        for (int i = 0; i < arraysize; i++) {
            
            Random r = new Random();
            char modelletter = (char)('A' + r.nextInt(26) );
            String modelname = "Q" + String.valueOf(modelletter) + " - " + String.valueOf(r.nextInt(99));

            int price = r.nextInt(10000)+1;
            QuadModel model = new QuadModel(modelname, price);
            models.add(model);
        }
    }
    public class QuadModel implements Serializable {         //task4
    
        String modelname;
        int price;
    
        public QuadModel(String modelname, int price){
            this.modelname=modelname;
            this.price=price;
        
        }

    }
    
    
    @Override
    public String toString(){
        StringBuffer str = new StringBuffer("Class: Quadbike\n");
        str.append(mark);
        for(QuadModel model : models){
            str.append("\n Model: " + model.modelname+ " Price: " + model.price);
        }
        return str.toString();
    }
    @Override
    public boolean equals(Object obj){                     //4th lab task 2
        
        if(obj instanceof Quadbike){
            if(this.mark.equals(((Quadbike) obj).getMark())){
                if(this.models.size() == ((Quadbike) obj).getLength()){
                    int[] verifyingprises = ((Quadbike) obj).getPriceArr();
                    String[] verifyingnames = ((Quadbike) obj).getModelNameArr();
                    for (int i = 0; i < models.size(); i++) {
                        if(!(models.get(i).price==verifyingprises[i] && models.get(i).modelname.equals(verifyingnames[i]))){
                            return false;
                        }
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

        if(models.isEmpty()){
            code =29*code +models.size();
            
            for(QuadModel model :models){
                code = 29*code + model.price;
                code = 29*code + model.modelname.hashCode();
            }
        }
        return code;
    }
    @Override
    public Object clone()throws CloneNotSupportedException
    {
        Quadbike clone;

        try {clone = (Quadbike) super.clone();}
        catch(CloneNotSupportedException ex){return null;}

        for (int i = 0; i < this.models.size(); ++i)
            clone.models.add( new QuadModel(models.get(i).modelname, models.get(i).price));
        return clone;


    }
    
    
    
    public void modModelName(String oldname, String newname)throws NoSuchModelNameException, DuplicateModelNameException{
        for (QuadModel model: models) {
            if(newname.equals(model.modelname)){
                throw new DuplicateModelNameException(newname);
            }
        }
        boolean isFounded = false;
        for (QuadModel model: models) {
            if(oldname.equals(model.modelname)){
                model.modelname = newname;
                isFounded=true;
                break;
            }
        }
        if(!isFounded){
            throw new NoSuchModelNameException(oldname);
        }
    }
    public String[] getModelNameArr(){              //task6
        String[] modelnamearr = new String[models.size()];
        for (int i = 0; i < models.size(); i++) {
            modelnamearr[i] = models.get(i).modelname;
        }
        return modelnamearr;
    }

    public int getPriceViaName(String name)throws NoSuchModelNameException{            //task7
        int ans = 0;
        for (QuadModel model: models) {
            if(name.equals(model.modelname)){
                ans = model.price;
                break;
            }
        }
        if(ans==0){
            throw new NoSuchModelNameException(name);
        }
        return ans;
    }

    public void modPriceViaName(String name, int newprice)throws NoSuchModelNameException {   //task8
        if(newprice<=0){
            throw new ModelPriceOutOfBoundsException();
        }
        boolean isFounded = false;
        for (QuadModel model: models) {
            if(name.equals(model.modelname)){
                model.price = newprice;
                isFounded = true;
                break;
            }
        }
        if(!isFounded){
            throw new NoSuchModelNameException(name);
        }
        
    }
    public int[] getPriceArr(){              //task9
        int[] pricearr = new int[models.size()];
        for (int i = 0; i < models.size(); i++) {
            pricearr[i] = models.get(i).price;
        }
        return pricearr;
    }
    public void addNewModel(String name, int price) throws DuplicateModelNameException, ModelPriceOutOfBoundsException{        //task10
        if(price<=0){
            throw new ModelPriceOutOfBoundsException();
        }
        for (QuadModel model: models) {
            if(name.equals(model.modelname)){
                 throw new DuplicateModelNameException(name);
                 
            }
        }
        QuadModel newmodel = new QuadModel(name, price);
        models.add(newmodel);
    }
    
    public void deleteModel(String name) throws NoSuchModelNameException{                   //task11
        
        for (int i = 0; i < models.size(); i++) {
            if(name.equals(models.get(i).modelname)){
                models.remove(i);
                break;
            }
            else if(i==models.size()-1){
                throw new NoSuchModelNameException(name);
            }
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
