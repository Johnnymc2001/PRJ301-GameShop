/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package johnny.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import johnny.shoppa.ShoppaDTO;

/**
 *
 * @author JohnnyMC
 */
public class CartObject implements Serializable {
    
    private Map<String, ShoppaDTO> items;
    
    public Map<String, ShoppaDTO> getItems() {
        return items;
    }
    
    public void addItemToCart(ShoppaDTO item) {
        //1. Checking existed cart container
        if (this.items == null) {
            this.items = new HashMap<String, ShoppaDTO>();
        }

        //2. Checking if item existed in Cart
        int quantity = 1;
        ShoppaDTO newItem = item;
        System.out.println(items);
        
        if (this.items.containsKey(item.getName())) {
            newItem = this.items.get(item.getName()); 
            newItem.setQuantity(newItem.getQuantity() + 1);
        }
        //3. Bỏ hàng vào giỏ
        
        this.items.put(item.getName(), newItem);
        System.out.println(items);
    }
    
    public void removeItemFromCart(String itemName) {
        //1. Checking existed cart container
        if (this.items == null) {
            return;
        }

        //2. Checking if item existed in Cart
        if (this.items.containsKey(itemName)) {
            this.items.remove(itemName);
            System.out.println("Removed " + itemName);
            if (this.items.isEmpty()) {
                this.items = null;
            }
        }
    }
    
}
