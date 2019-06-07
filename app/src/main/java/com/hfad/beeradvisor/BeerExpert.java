package com.hfad.beeradvisor;

import java.util.LinkedList;
import java.util.List;

public class BeerExpert {
    public List<String> getBrands(String color) {
        List<String> brands =  new LinkedList<>();
        if (color.equals("amber")) {
            brands.add("Jack Amber");
            brands.add("Red Moose");
        } else {
            brands.add("Jail Pale Ale");
            brands.add("Gout Stout");
        }
        return brands;
    }
}
