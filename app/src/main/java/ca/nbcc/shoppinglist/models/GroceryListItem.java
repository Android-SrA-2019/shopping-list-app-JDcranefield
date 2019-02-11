package ca.nbcc.shoppinglist.models;

import java.security.InvalidParameterException;

public class GroceryListItem {
    private String itemName;
    private int count;

    public String getItemName(){
        return itemName;
    }

    public int getCount(){
        return count;
    }

    public void addOne(){
        count++;
    }

    public void addAmount(int amount){

        if(count + amount < 0){
            throw new InvalidParameterException("Grocery item count cannot be negative");
        }

        count += amount;
    }

    private GroceryListItem(){}

    public GroceryListItem(String itemName, int count){

        if(count < 0){
            throw new InvalidParameterException("Grocery item count cannot be negative");
        }

        this.itemName = itemName;
        this.count = count;
    }
}
