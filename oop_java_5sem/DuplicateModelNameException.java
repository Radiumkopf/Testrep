
package com.mycompany.oop_l2;


public class DuplicateModelNameException extends Exception {
    public String attrName; 
    public DuplicateModelNameException(String name) { 
        super ("Модель \"" + name +  "\" уже присутствует в списке моделей"); 
        attrName = name;
    }
}
