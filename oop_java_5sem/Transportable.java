
package com.mycompany.oop_l2;

public interface Transportable{
    
    public void modModelName(String oldname, String newname)throws NoSuchModelNameException, DuplicateModelNameException;
    
    String[] getModelNameArr();
    
    int getPriceViaName(String name) throws NoSuchModelNameException;
    
    void modPriceViaName(String name, int newprice)throws NoSuchModelNameException, ModelPriceOutOfBoundsException;
    
    int[] getPriceArr();
    
    void addNewModel(String name, int price)throws DuplicateModelNameException, ModelPriceOutOfBoundsException;
    
    void deleteModel(String name)throws NoSuchModelNameException;
    
    public int getLength();

    String getMark();
    
    void modMark(String newmark);
    @Override
    String toString();
    @Override
    boolean equals(Object obj);
    @Override
    int hashCode();
    
    Object clone()throws CloneNotSupportedException;
    
}
