package com.example.btvn_b6.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ProductCartSQLite extends SQLiteOpenHelper {
    public static final String DB_PRODUCTSCART = "ProductsCart";
    public static final int DB_VERSION = 1;
    public static final String TABLE_PRODUCT = "PRODUCT";
    public static final String ID_PRODUCT = "_id";
    public static final String NAME_PRODUCT = "nameProduct";
    public static final String PRICE_PRODUCT = "priceProduct";
    public static final String URL_IMAGE_PRODUCT ="urlImgProduct";
    public static final String RATING_PRODUCT = "ratingProduct";
    public static final String COUNT_PRODUCT = "countProduct";

    public ProductCartSQLite(@Nullable Context context) {
        super(context, DB_PRODUCTSCART, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTB = "CREATE TABLE "+TABLE_PRODUCT+" ("+ID_PRODUCT+" INTEGER, "+NAME_PRODUCT+" TEXT, "+PRICE_PRODUCT+" TEXT, "+URL_IMAGE_PRODUCT+" TEXT, "+RATING_PRODUCT+" TEXT,"+COUNT_PRODUCT+" INTEGER);";
        db.execSQL(createTB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_PRODUCT);
        onCreate(db);
    }
}
