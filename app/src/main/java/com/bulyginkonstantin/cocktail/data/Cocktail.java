package com.bulyginkonstantin.cocktail.data;

public class Cocktail {

    private int id;
    private String strDrink;
    private String strCategory;
    private String strAlcoholic;
    private String strGlass;
    private String strInstructions;
    private String strDrinkThumb;
    private String dateModified;

    public Cocktail(int id, String strDrink, String strCategory, String strAlcoholic, String strGlass, String strInstructions, String strDrinkThumb, String dateModified) {
        this.id = id;
        this.strDrink = strDrink;
        this.strCategory = strCategory;
        this.strAlcoholic = strAlcoholic;
        this.strGlass = strGlass;
        this.strInstructions = strInstructions;
        this.strDrinkThumb = strDrinkThumb;
        this.dateModified = dateModified;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStrDrink() {
        return strDrink;
    }

    public void setStrDrink(String strDrink) {
        this.strDrink = strDrink;
    }

    public String getStrCategory() {
        return strCategory;
    }

    public void setStrCategory(String strCategory) {
        this.strCategory = strCategory;
    }

    public String getStrAlcoholic() {
        return strAlcoholic;
    }

    public void setStrAlcoholic(String strAlcoholic) {
        this.strAlcoholic = strAlcoholic;
    }

    public String getStrGlass() {
        return strGlass;
    }

    public void setStrGlass(String strGlass) {
        this.strGlass = strGlass;
    }

    public String getStrInstructions() {
        return strInstructions;
    }

    public void setStrInstructions(String strInstructions) {
        this.strInstructions = strInstructions;
    }

    public String getStrDrinkThumb() {
        return strDrinkThumb;
    }

    public void setStrDrinkThumb(String strDrinkThumb) {
        this.strDrinkThumb = strDrinkThumb;
    }

    public String getDateModified() {
        return dateModified;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }
}
