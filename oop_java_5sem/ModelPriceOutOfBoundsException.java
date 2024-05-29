
package com.mycompany.oop_l2;

public class ModelPriceOutOfBoundsException extends ArithmeticException {
    public ModelPriceOutOfBoundsException(){
        super("Цена модели должна быть хотя бы больше нуля");
    }
}
