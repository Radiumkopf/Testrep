package com.mycompany.oop_l2;
import java.util.Random;
import java.util.Arrays;
import java.io.Serializable;

public class Car implements Transportable, Serializable, Cloneable{
    
    private String mark;
    private CarModel[] models;
    
    public Car(){
        models = new CarModel[0];
    }
    
    public Car( int arraysize, String mark){
        this.mark = mark;
        models = new CarModel[arraysize];
        for (int i = 0; i < arraysize; i++) {
            
            Random r = new Random();
            char modelletter = (char)('A' + r.nextInt(26) );
            String modelname = String.valueOf(modelletter) + " - " + String.valueOf(r.nextInt(99));

            int price = r.nextInt(10000)+1;
            CarModel model = new CarModel(modelname, price);
            models[i] = model;
        }
    }
    public class CarModel implements Serializable {         //task4
    
        String modelname;
        int price;
    
        public CarModel(String modelname, int price){
            this.modelname=modelname;
            this.price=price;
        
        }
        public String getValues(){
            String ans = '(' + modelname + ", " + price+ ')';
            return ans;
        }

    }
    
    
    @Override
    public String toString(){
        StringBuffer str = new StringBuffer("Class: Car\n");
        str.append(mark);
        for(CarModel model : models){
            str.append("\n Model: " + model.modelname+ " Price: " + model.price);
        }
        return str.toString();
    }
    @Override
    public boolean equals(Object obj){                     //4th lab task 2
        
        if(obj instanceof Car){
            if(this.mark.equals(((Car) obj).getMark())){
                if(this.models.length == ((Car) obj).getLength()){
                    int[] verifyingprises = ((Car) obj).getPriceArr();
                    String[] verifyingnames = ((Car) obj).getModelNameArr();
                    for (int i = 0; i < models.length; i++) {
                        if(!(models[i].price==verifyingprises[i] && models[i].modelname.equals(verifyingnames[i]))){
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

        if(models.length!=0){
            code =29*code +models.length;
            
            for(CarModel model :models){
                code = 29*code + model.price;
                code = 29*code + model.modelname.hashCode();
            }
        }
        return code;
    }
    @Override
    public Object clone() throws CloneNotSupportedException
    {
        Car clone;

        try {clone = (Car) super.clone();}
        catch(CloneNotSupportedException ex){return null;}
        clone.mark = this.mark;
        clone.models = new CarModel[this.models.length];
        for (int i = 0; i < this.models.length; ++i)
            clone.models[i] = new CarModel(models[i].modelname, models[i].price);
        return clone;


    }
    
    
    
    public void modModelName(String oldname, String newname)throws NoSuchModelNameException, DuplicateModelNameException{
        for (CarModel model: models) {
            if(newname.equals(model.modelname)){
                throw new DuplicateModelNameException(newname);
            }
        }
        boolean isFounded = false;
        for (CarModel model: models) {
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
        String[] modelnamearr = new String[models.length];
        for (int i = 0; i < models.length; i++) {
            modelnamearr[i] = models[i].modelname;
        }
        return modelnamearr;
    }

    public int getPriceViaName(String name)throws NoSuchModelNameException{            //task7
        int ans = 0;
        for (CarModel model: models) {
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
        for (CarModel model: models) {
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
        int[] pricearr = new int[models.length];
        for (int i = 0; i < models.length; i++) {
            pricearr[i] = models[i].price;
        }
        return pricearr;
    }
    public void addNewModel(String name, int price) throws DuplicateModelNameException, ModelPriceOutOfBoundsException{        //task10
        if(price<=0){
            throw new ModelPriceOutOfBoundsException();
        }
        for (CarModel model: models) {
            if(name.equals(model.modelname)){
                 throw new DuplicateModelNameException(name);
                 
            }
        }
        CarModel[] copy = Arrays.copyOf(models, models.length+1);
        copy[models.length] = new CarModel(name, price);
        this.models = copy;
    }
    
    public void deleteModel(String name) throws NoSuchModelNameException{                   //task11
        
        for (int i = 0; i < models.length; i++) {
            if(name.equals(models[i].modelname)){
                CarModel[] newarr = new CarModel[models.length-1];
                System.arraycopy(models, 0, newarr, 0, i);
                System.arraycopy(models, i+1, newarr, i, models.length - i-1);
                this.models = newarr;
                break;
            }
            else if(i==models.length-1){
                throw new NoSuchModelNameException(name);
            }
        }
    }
   
    
    public int getLength(){
        return models.length;
    }
    
    public void printModels(){
        for (CarModel model: models) {
            System.out.println(model.getValues());
        }
    }
    
    public String getMark(){            //task2
        return mark;
    }
    
    public void modMark(String newmark){    //task3
        mark=newmark;
    }
    
    
}
