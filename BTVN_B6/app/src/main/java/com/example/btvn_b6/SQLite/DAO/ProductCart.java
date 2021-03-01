package com.example.btvn_b6.SQLite.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.btvn_b6.RecycleView.Home.Product;
import com.example.btvn_b6.SQLite.ProductCartSQLite;

import java.util.ArrayList;
import java.util.List;

public class ProductCart {
    ProductCartSQLite productCartSQLite;
    SQLiteDatabase database;

    public ProductCart(Context context){
        productCartSQLite = new ProductCartSQLite(context);
    }

    public void Open(){
        database = productCartSQLite.getWritableDatabase();
    }

    public void Close(){
        productCartSQLite.close();
    }
    public void AddProductCart(Product product){
        ContentValues contentValues = new ContentValues();
        contentValues.put(productCartSQLite.ID_PRODUCT,product.getId());
        contentValues.put(productCartSQLite.NAME_PRODUCT,product.getNameProduct());
        contentValues.put(productCartSQLite.PRICE_PRODUCT,product.getPriceProduct());
        contentValues.put(productCartSQLite.URL_IMAGE_PRODUCT,product.getUrlImgProduct());
        contentValues.put(productCartSQLite.RATING_PRODUCT,String.valueOf(product.getRatingProduct()));
        contentValues.put(productCartSQLite.COUNT_PRODUCT,product.getCountProduct());

        database.insert(productCartSQLite.TABLE_PRODUCT,null,contentValues);
    }
    public List<Product> GetProductsCart(){
        List<Product> products = new ArrayList<>();
        Cursor cursor = database.query(ProductCartSQLite.TABLE_PRODUCT,null,null,null,null,null,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int id = cursor.getInt(cursor.getColumnIndex(ProductCartSQLite.ID_PRODUCT));
            String name = cursor.getString(cursor.getColumnIndex(ProductCartSQLite.NAME_PRODUCT));
            String price = cursor.getString(cursor.getColumnIndex(ProductCartSQLite.PRICE_PRODUCT));
            String url = cursor.getString(cursor.getColumnIndex(ProductCartSQLite.URL_IMAGE_PRODUCT));
            float ratting = Float.parseFloat(cursor.getString(cursor.getColumnIndex(ProductCartSQLite.RATING_PRODUCT)));
            int count = cursor.getInt(cursor.getColumnIndex(ProductCartSQLite.COUNT_PRODUCT));
            Product product = new Product(id,name,price,url,ratting,count);
            products.add(product);
            cursor.moveToNext();
        }
        return products;
    }
    public void DelProductCart(int a){
        database.delete(ProductCartSQLite.TABLE_PRODUCT,ProductCartSQLite.ID_PRODUCT + " = " + a,null);
    }
    public void DelAllProductCart(){
        database.delete(ProductCartSQLite.TABLE_PRODUCT,null,null);
    }
}
