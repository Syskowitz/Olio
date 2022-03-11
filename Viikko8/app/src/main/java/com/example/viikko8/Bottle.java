package com.example.viikko8;

public class Bottle {
    private String name = "Pepsi Max";
    private String manufacturer = "Pepsi";
    private float energy = (float) 0.3;
    private float price = (float) 1.8;
    private float size = (float) 0.5;
    public Bottle(String input_name, String input_manufacturer,
                  float input_energy, float input_size, float input_price) {
        name = input_name;
        manufacturer = input_manufacturer;
        energy = input_energy;
        price = input_price;
        size = input_size;
    }
    public String getName() {
        return name;
    }
    public String getManufacturer() {
        return manufacturer;
    }
    public float getEnergy() {
        return energy;
    }
    public float getPrice() {
        return price;
    }
    public float getSize() {
        return size;
    }
    @Override
    public String toString(){
        String tempStr = String.format("%s %.02fL %.02f€",name, size, price);
        return tempStr;
    }
}
