
package com.mycompany.oop_l2;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Random;


public class Moped implements Transportable, Serializable, Cloneable{
     private String mark;
    private LinkedList<MopedModel> models;
    
    public Moped(){
        models = new LinkedList<>();
    }
    
    public Moped( int arraysize, String mark){
        this.mark = mark;
        models = new LinkedList<>();
        for (int i = 0; i < arraysize; i++) {
            
            Random r = new Random();
            char modelletter = (char)('A' + r.nextInt(26) );
            String modelname = "B" + String.valueOf(modelletter) + " - " + String.valueOf(r.nextInt(99));

            int price = r.nextInt(10000)+1;
            MopedModel model = new MopedModel(modelname, price);
            models.add(model);
        }
    }
    public class MopedModel implements Serializable {         //task4
    
        String modelname;
        int price;
    
        public MopedModel(String modelname, int price){
            this.modelname=modelname;
            this.price=price;
        
        }

    }
    
    
    @Override
    public String toString(){
        StringBuffer str = new StringBuffer("Class: Moped\n");
        str.append(mark);
        for(MopedModel model : models){
            str.append("\n Model: " + model.modelname+ " Price: " + model.price);
        }
        return str.toString();
    }
    @Override
    public boolean equals(Object obj){                     //4th lab task 2
        
        if(obj instanceof Moped){
            if(this.mark.equals(((Moped) obj).getMark())){
                if(this.models.size() == ((Moped) obj).getLength()){
                    int[] verifyingprises = ((Moped) obj).getPriceArr();
                    String[] verifyingnames = ((Moped) obj).getModelNameArr();
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
            
            for(MopedModel model :models){
                code = 29*code + model.price;
                code = 29*code + model.modelname.hashCode();
            }
        }
        return code;
    }
    @Override
    public Object clone() throws CloneNotSupportedException
    {
        Moped clone;

        try {clone = (Moped) super.clone();}
        catch(CloneNotSupportedException ex){return null;}

        for (int i = 0; i < this.models.size(); i++)
            clone.models.add(new MopedModel(this.models.get(i).modelname, this.models.get(i).price));
        return clone;


    }
    
    
    
    public void modModelName(String oldname, String newname)throws NoSuchModelNameException, DuplicateModelNameException{
        for (MopedModel model: models) {
            if(newname.equals(model.modelname)){
                throw new DuplicateModelNameException(newname);
            }
        }
        boolean isFounded = false;
        for (MopedModel model: models) {
            if(oldname.equals(model.modelname)){
                model.modelname = newname;
                isFounded=true;
                break;
            }
        }
        if(isFounded==false){
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
        for (MopedModel model: models) {
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

    public void modPriceViaName(String name, int newprice)throws NoSuchModelNameException, ModelPriceOutOfBoundsException{   //task8
        if(newprice<=0){
            throw new ModelPriceOutOfBoundsException();
        }
        boolean isFounded = false;
        for (MopedModel model: models) {
            if(name.equals(model.modelname)){
                model.price = newprice;
                isFounded = true;
                break;
            }
        }
        if(isFounded==false){
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
        for (MopedModel model: models) {
            if(name.equals(model.modelname)){
                 throw new DuplicateModelNameException(name);
                 
            }
        }
        MopedModel newmodel = new MopedModel(name, price);
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
