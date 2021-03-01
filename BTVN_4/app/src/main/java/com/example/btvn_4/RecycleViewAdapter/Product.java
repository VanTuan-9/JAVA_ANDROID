package com.example.btvn_4.RecycleViewAdapter;

public class Product {
    int imgProduct;
    String inforProduct,priceProduct;

    public Product() {
    }

    public Product(int imgProduct, String inforProduct, String priceProduct) {
        this.imgProduct = imgProduct;
        this.inforProduct = inforProduct;
        this.priceProduct = priceProduct;
    }

    public int getImgProduct() {
        return imgProduct;
    }

    public void setImgProduct(int imgProduct) {
        this.imgProduct = imgProduct;
    }

    public String getInforProduct() {
        return inforProduct;
    }

    public void setInforProduct(String inforProduct) {
        this.inforProduct = inforProduct;
    }

    public String getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(String priceProduct) {
        this.priceProduct = priceProduct;
    }
}
