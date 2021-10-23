/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package johnny.shoppa;

import java.io.Serializable;

/**
 *
 * @author JohnnyMC
 */
public class ShoppaDTO implements Serializable {

    private String id;
    private String name;
    private int quantity;
    private double price;

    public ShoppaDTO(String id, String name, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public ShoppaDTO(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public ShoppaDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ShoppaDTO{" + "id=" + id + ", name=" + name + ", quantity=" + quantity + ", price=" + price + '}';
    }

}
