package com.deepsingh44.foodapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.deepsingh44.foodapp.model.Product;
import com.deepsingh44.foodapp.model.User;

import java.util.ArrayList;
import java.util.List;

public class MyDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "foodapp";
    private static final int VERSION = 1;
    //user table details here
    private static final String TABLE_NAME = "user";
    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "pass";
    private static final String USER_QUERY = "create table " + TABLE_NAME + " (" + NAME + " varchar(30)," + EMAIL + " varchar(35)primary key," + PASSWORD + " varchar(20));";

    //product detail here
    private static final String PRODUCT_TABLE_NAME = "product";
    private static final String PRODUCT_NAME = "name";
    private static final String PRODUCT_PRICE = "price";
    private static final String PRODUCT_QUANTITY = "quantity";
    private static final String PRODUCT_DATE = "date";
    private static final String PRODUCT_QUERY = "create table " + PRODUCT_TABLE_NAME + " (" + PRODUCT_NAME + " varchar(30)," + PRODUCT_PRICE + " float," + PRODUCT_QUANTITY + " int," + PRODUCT_DATE + " varchar(20));";

    public MyDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(USER_QUERY);
        db.execSQL(PRODUCT_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_NAME + "");
        db.execSQL("drop table if exists " + PRODUCT_NAME + "");

        onCreate(db);
    }

    //user query here
    public long insert(User user) {
        ContentValues cv = new ContentValues();
        cv.put(NAME, user.getName());
        cv.put(EMAIL, user.getEmail());
        cv.put(PASSWORD, user.getPass());
        return getWritableDatabase().insert(TABLE_NAME, null, cv);
    }

    public User login(String email, String pass) {
        User user = null;
        Cursor cursor = getReadableDatabase().query(TABLE_NAME, null, "" + EMAIL + "=? and " + PASSWORD + "=?", new String[]{email, pass}, null, null, null, null);
        if (cursor.moveToNext()) {
            user = new User();
            user.setName(cursor.getString(0));
            user.setEmail(cursor.getString(1));
            user.setPass(cursor.getString(2));
        }
        return user;
    }

    //product query here
    public long insert(Product product) {
        ContentValues cv = new ContentValues();
        cv.put(PRODUCT_NAME, product.getName());
        cv.put(PRODUCT_PRICE, product.getPrice());
        cv.put(PRODUCT_QUANTITY, product.getQuantity());
        cv.put(PRODUCT_DATE, product.getDate());
        return getWritableDatabase().insert(PRODUCT_TABLE_NAME, null, cv);
    }

    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        Cursor cursor = getReadableDatabase().query(PRODUCT_TABLE_NAME, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            Product product = new Product();
            product.setName(cursor.getString(0));
            product.setPrice(cursor.getFloat(1));
            product.setQuantity(cursor.getInt(2));
            product.setDate(cursor.getString(3));
            productList.add(product);
        }
        return productList;
    }

    public int deleteItem(Product product) {
        return getWritableDatabase().delete(PRODUCT_TABLE_NAME, "" + PRODUCT_NAME + "=?", new String[]{product.getName()});
    }
}
