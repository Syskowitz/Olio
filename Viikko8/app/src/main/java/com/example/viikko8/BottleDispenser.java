package com.example.viikko8;
import java.util.ArrayList;

public class BottleDispenser {
    private int bottles;
    private ArrayList<Bottle> bottle_array;
    private float money;
    private static BottleDispenser count = null;
    /*Bottle bottleInfo = new Bottle("Pepsi Max", "Pepsi", (float) 0.3,
            (float) 0.5, (float) 1.8);*/
    private BottleDispenser() {
        bottles = 6;
        bottle_array = new ArrayList(bottles);
        money = 0;
        bottle_array.add(new Bottle("Pepsi Max", "Pepsi", (float) 0.3,
                (float) 0.5, (float) 1.8));
        bottle_array.add(new Bottle("Pepsi Max", "Pepsi", (float) 0.3,
                (float) 1.5, (float) 2.2));
        bottle_array.add(new Bottle("Coca-Cola Zero", "Coca-Cola", (float) 0.3,
                (float) 0.5, (float) 2.0));
        bottle_array.add(new Bottle("Coca-Cola Zero", "Coca-Cola", (float) 0.3,
                (float) 1.5, (float) 2.5));
        bottle_array.add(new Bottle("Fanta Zero", "Fanta", (float) 0.3,
                (float) 0.5, (float) 1.95));
        bottle_array.add(new Bottle("Fanta Zero", "Fanta", (float) 0.3,
                (float) 1.5, (float) 2.5));
    }
    public static BottleDispenser getInstance() {
        if (count == null) {
            count = new BottleDispenser();
        }
            return count;
    }

    public float getMoney(){
        return money;
    }
    public void addMoney(float toAdd) {
        money += toAdd;
        //System.out.println("Klink! Added more money!");
    }
    public String buyBottle(int choice) {
        String outputStr;
        if (bottles == -1) {
            outputStr = "Bottles are out!";
            return outputStr;
        }
        if (money < bottle_array.get(choice).getPrice()) {
            outputStr = "Add money first!";
            return outputStr;
        }
        money -= bottle_array.get(choice).getPrice();
        bottles -= 1;
        outputStr = String.format("KACHUNK! "+bottle_array.get(choice).getName()+" came out of the dispenser!\nYou have %.02f€ left", money);
        bottle_array.remove(choice);
        return outputStr;
    }
    public void returnMoney() {
        System.out.println(String.format("Klink klink. Money came out! You got %.2f€ back", money));
        money = 0;
    }
    public void printBottles() {
        for (int i = 0;i<=bottles;i++ ) {
            System.out.println(i+1+". Name: "+bottle_array.get(i).getName()+"\n"+
                    "\tSize: "+bottle_array.get(i).getSize()+"\tPrice: "+
                    bottle_array.get(i).getPrice());
        }
    }
    public Bottle getBottle(int index){
        return bottle_array.get(index);
    }
    public int getArrayLength(){
        return bottles;
    }
}
