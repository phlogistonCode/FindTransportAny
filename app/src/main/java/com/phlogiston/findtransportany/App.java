package com.phlogiston.findtransportany;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.util.Log;

import com.phlogiston.findtransportany.Api.PrimaryData.Marsh;
import com.phlogiston.findtransportany.Api.PrimaryData.MarshOKATOLink;
import com.phlogiston.findtransportany.Api.PrimaryData.MarshRaceCard;
import com.phlogiston.findtransportany.Api.PrimaryData.MarshRaceList;
import com.phlogiston.findtransportany.Api.PrimaryData.Okato;
import com.phlogiston.findtransportany.Api.PrimaryData.Stop;
import com.phlogiston.findtransportany.Api.PrimaryData.TransportType;


import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Alexandr on 26.03.2018.
 */

public class App extends Application {

    final String LOG_TAG = "myLogs";
    final String DB_NAME = "myDB";
    private static IApi api;
    public DBHelper dbHelper;
    public static SQLiteDatabase db;
    List<Class> primaryData;


    @Override
    public void onCreate() {
        super.onCreate();

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().addInterceptor(chain -> {
            Request originalRequest = chain.request();
            Request.Builder builder = originalRequest.newBuilder().header("Accept-Encoding", "gzip").header("Authorization", Credentials.basic("asipguest", "asipguest"));
            Request newRequest = builder.build();
            return chain.proceed(newRequest);
        }).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(IApi.baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        api = retrofit.create(IApi.class);

        createDB();
    }

    public void createDB () {
        primaryData = new ArrayList<Class>(){};
        primaryData.add(Marsh.class);
        primaryData.add(MarshOKATOLink.class);
        primaryData.add(MarshRaceCard.class);
        primaryData.add(MarshRaceList.class);
        primaryData.add(Okato.class);
        primaryData.add(Stop.class);
        primaryData.add(TransportType.class);

        dbHelper = new DBHelper(this);
        db = dbHelper.getWritableDatabase();
    }

    class DBHelper extends SQLiteOpenHelper {

        DBHelper(Context context) {
            // конструктор суперкласса
            super(context, DB_NAME, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d(LOG_TAG, "--- onCreate database ---");
            // создаем таблицы с полями
            for (Class c : primaryData) {
                StringBuilder tabCol= new StringBuilder();
                int count = c.getFields().length;
                for (Field f : c.getFields()) {
                    if (!((f.getName().equals("$change"))||(f.getName().equals("serialVersionUID")))) {
                        tabCol.append(f.getName()).append(" ");
                        switch (f.getType().toString()) {
                            case "class java.lang.String":
                                tabCol.append("text");
                                break;
                            case "int":
                                tabCol.append("integer");
                                break;
                            case "double":
                                tabCol.append("real");
                                break;
                            case "boolean":
                                tabCol.append("numeric");
                                break;
                        }
                        count--;
                        if (count!=2)
                            tabCol.append(",");
                    }
                }
                db.execSQL("create table " + c.getSimpleName() + " ("
                        + tabCol
                        + ");");
                Log.d(LOG_TAG, c.getSimpleName() + " table created.");
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
    }

    public static IApi getApi() {
        return api;
    }
}
