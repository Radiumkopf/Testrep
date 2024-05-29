
package com.mycompany.oop_l2;


public class NoSuchModelNameException extends Exception {
    public String attrName; 
    public NoSuchModelNameException(String name) { 
        super ("Модель \"" + name +  "\" не найдена"); 
        attrName = name;
    }
}
