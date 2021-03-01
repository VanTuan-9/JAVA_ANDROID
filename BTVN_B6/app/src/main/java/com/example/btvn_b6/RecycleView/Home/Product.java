package com.example.btvn_b6.RecycleView.Home;

import java.io.Serializable;

public class Product implements Serializable {
    private Integer id;
    private String nameProduct;
    private String priceProduct;
    private String urlImgProduct;
    private float ratingProduct;
    private Integer countProduct;
    public Product() {
    }

    public Product(Integer id, String nameProduct, String priceProduct, String urlImgProduct, float ratingProduct,Integer countProduct) {
        this.id = id;
        this.nameProduct = nameProduct;
        this.priceProduct = priceProduct;
        this.urlImgProduct = urlImgProduct;
        this.ratingProduct = ratingProduct;
        this.countProduct = countProduct;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(String priceProduct) {
        this.priceProduct = priceProduct;
    }

    public String getUrlImgProduct() {
        return urlImgProduct;
    }

    public void setUrlImgProduct(String urlImgProduct) {
        this.urlImgProduct = urlImgProduct;
    }

    public float getRatingProduct() {
        return ratingProduct;
    }

    public void setRatingProduct(float ratingProduct) {
        this.ratingProduct = ratingProduct;
    }

    public Integer getCountProduct() {
        return countProduct;
    }

    public void setCountProduct(Integer countProduct) {
        this.countProduct = countProduct;
    }
}
