package com.example.btvn_4.RecycleViewAdapterCart;

public class ProductCart {
    int imgProductCart,countProductCart;
    String nameProductCart,priceProductCart;

    public ProductCart() {
    }

    public ProductCart(int imgProductCart, int countProductCart, String nameProductCart, String priceProductCart) {
        this.imgProductCart = imgProductCart;
        this.countProductCart = countProductCart;
        this.nameProductCart = nameProductCart;
        this.priceProductCart = priceProductCart;
    }

    public int getImgProductCart() {
        return imgProductCart;
    }

    public void setImgProductCart(int imgProductCart) {
        this.imgProductCart = imgProductCart;
    }

    public int getCountProductCart() {
        return countProductCart;
    }

    public void setCountProductCart(int countProductCart) {
        this.countProductCart = countProductCart;
    }

    public String getNameProductCart() {
        return nameProductCart;
    }

    public void setNameProductCart(String nameProductCart) {
        this.nameProductCart = nameProductCart;
    }

    public String getPriceProductCart() {
        return priceProductCart;
    }

    public void setPriceProductCart(String priceProductCart) {
        this.priceProductCart = priceProductCart;
    }
}
