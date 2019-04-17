package com.phlogiston.findtransportany;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.phlogiston.findtransportany.Api.PrimaryData.Marsh;
import com.phlogiston.findtransportany.Api.PrimaryData.MarshOKATOLink;
import com.phlogiston.findtransportany.Api.PrimaryData.MarshRaceCard;
import com.phlogiston.findtransportany.Api.PrimaryData.MarshRaceList;
import com.phlogiston.findtransportany.Api.PrimaryData.Okato;
import com.phlogiston.findtransportany.Api.PrimaryData.Stop;
import com.phlogiston.findtransportany.Api.PrimaryData.TransportType;
import com.phlogiston.findtransportany.Api.Services.FactPointInfo;
import com.phlogiston.findtransportany.Api.Services.MarshBenefit;
import com.phlogiston.findtransportany.Api.Services.MarshCoord;
import com.phlogiston.findtransportany.Api.Services.MarshRate;
import com.phlogiston.findtransportany.Api.Services.ParkInfo;
import com.phlogiston.findtransportany.Api.Services.PassTrafficInfo;
import com.phlogiston.findtransportany.Api.Services.Raspisanie;
import com.phlogiston.findtransportany.Api.Services.StopByCoord;
import com.phlogiston.findtransportany.Api.Services.TableCur;
import com.phlogiston.findtransportany.Api.Services.TransportInfo;
import com.phlogiston.findtransportany.Api.Services.WorkInfo;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function3;
import io.reactivex.schedulers.Schedulers;
import kotlin.Triple;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView tv;

    final public static String JSON = "json";
    final String LOG_TAG = "myLogs";

    IApi api;
    SQLiteDatabase db;
    List<List> primaryDataList;

    List<TransportType> transportTypes;
    List<Marsh> marshes;
    List<Stop> stops;
    List<Okato> OKATO;
    List<MarshOKATOLink> marshOkatoLink;
    List<MarshRaceList> marshRaceList;
    List<MarshRaceCard> marshRaceCard;

    public int dt = 6600;
    public int mr_id = 1;
    public String rl_racetype = "A";
    public int st_id = 4;
    public String rc_kkp = "B";
    public double lat = 48.815476;
    public double _long = 44.624435;
    public int radius = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.textView);

        tv.setText("SOSI");

        api = App.getApi();
        db = App.db;
        primaryDataList = new ArrayList<>();

        // region Services
        api.getRaspisanie(dt, mr_id, rl_racetype, st_id, rc_kkp, JSON).enqueue(new Callback<List<Raspisanie>>() {
            @Override
            public void onResponse(Call<List<Raspisanie>> call, Response<List<Raspisanie>> response) {
                List<Raspisanie> raspisanie = response.body();
            }

            @Override
            public void onFailure(Call<List<Raspisanie>> call, Throwable t) {

            }
        });
        api.getTableCur(st_id, JSON).enqueue(new Callback<List<TableCur>>() {
            @Override
            public void onResponse(Call<List<TableCur>> call, Response<List<TableCur>> response) {
                List<TableCur> tableCur = response.body();
            }

            @Override
            public void onFailure(Call<List<TableCur>> call, Throwable t) {

            }
        });
        api.getStopByCoord(lat, _long, radius, JSON).enqueue(new Callback<List<StopByCoord>>() {
            @Override
            public void onResponse(Call<List<StopByCoord>> call, Response<List<StopByCoord>> response) {
                List<StopByCoord> stopByCoord = response.body();
            }

            @Override
            public void onFailure(Call<List<StopByCoord>> call, Throwable t) {

            }
        });
        // endregion

        // region Non-functional services
        // 404
        api.getMarshRate(mr_id, JSON).enqueue(new Callback<List<MarshRate>>() {
            @Override
            public void onResponse(Call<List<MarshRate>> call, Response<List<MarshRate>> response) {
                List<MarshRate> marshRate = response.body();
            }

            @Override
            public void onFailure(Call<List<MarshRate>> call, Throwable t) {

            }
        });
        api.getMarshBenefit(mr_id, JSON).enqueue(new Callback<List<MarshBenefit>>() {
            @Override
            public void onResponse(Call<List<MarshBenefit>> call, Response<List<MarshBenefit>> response) {
                List<MarshBenefit> marshBenefit = response.body();
            }

            @Override
            public void onFailure(Call<List<MarshBenefit>> call, Throwable t) {

            }
        });
        api.getParkInfo(mr_id, JSON).enqueue(new Callback<List<ParkInfo>>() {
            @Override
            public void onResponse(Call<List<ParkInfo>> call, Response<List<ParkInfo>> response) {
                List<ParkInfo> parkInfo = response.body();
            }

            @Override
            public void onFailure(Call<List<ParkInfo>> call, Throwable t) {

            }
        });
        api.getTransportInfo(mr_id, JSON).enqueue(new Callback<List<TransportInfo>>() {
            @Override
            public void onResponse(Call<List<TransportInfo>> call, Response<List<TransportInfo>> response) {
                List<TransportInfo> transportInfo = response.body();
            }

            @Override
            public void onFailure(Call<List<TransportInfo>> call, Throwable t) {

            }
        });
        api.getPassTrafficInfo(dt, mr_id, JSON).enqueue(new Callback<List<PassTrafficInfo>>() {
            @Override
            public void onResponse(Call<List<PassTrafficInfo>> call, Response<List<PassTrafficInfo>> response) {
                List<PassTrafficInfo> passTrafficInfo = response.body();
            }

            @Override
            public void onFailure(Call<List<PassTrafficInfo>> call, Throwable t) {

            }
        });

        // No information
        api.getMarshCoord(mr_id, JSON).enqueue(new Callback<List<MarshCoord>>() {
            @Override
            public void onResponse(Call<List<MarshCoord>> call, Response<List<MarshCoord>> response) {
                List<MarshCoord> marshCoord = response.body();
            }

            @Override
            public void onFailure(Call<List<MarshCoord>> call, Throwable t) {

            }
        });
        api.getWorkInfo(dt, mr_id, JSON).enqueue(new Callback<List<WorkInfo>>() {
            @Override
            public void onResponse(Call<List<WorkInfo>> call, Response<List<WorkInfo>> response) {
                List<WorkInfo> workInfo = response.body();
            }

            @Override
            public void onFailure(Call<List<WorkInfo>> call, Throwable t) {

            }
        });
        api.getFactPointInfo(dt, mr_id, rl_racetype, st_id, rc_kkp, JSON).enqueue(new Callback<List<FactPointInfo>>() {
            @Override
            public void onResponse(Call<List<FactPointInfo>> call, Response<List<FactPointInfo>> response) {
                List<FactPointInfo> factPointInfo = response.body();
            }

            @Override
            public void onFailure(Call<List<FactPointInfo>> call, Throwable t) {

            }
        });
        //endregion
    }

    @SuppressLint("CheckResult")
    public void btnUpdPrimData_Click(View view) {
        ContentValues cv;
        CompositeDisposable compositeDisposable = new CompositeDisposable();


        // region Download data into Lists.
        Flowable.zip(api.getTransportTypes(JSON), api.getMarshes(JSON), api.getStops(JSON),
                     api.getOKATO(JSON), api.getMarshOKATOLink(JSON), api.getMarshRaceList(dt, mr_id, JSON),
                     api.getMarshRaceCard(dt, mr_id, JSON),
                this::requestsToArray)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::showVagina);
        // endregion
    }

    public ArrayList<List> requestsToArray(List<TransportType> transportTypes, List<Marsh> marshes, List<Stop> stops, List<Okato> OKATO, List<MarshOKATOLink> marshOkatoLink, List<MarshRaceList> marshRaceList, List<MarshRaceCard> marshRaceCard){
        ArrayList<List> l = new ArrayList<>();
        l.add(transportTypes);
        l.add(marshes);
        l.add(stops);
        l.add(OKATO);
        l.add(marshOkatoLink);
        l.add(marshRaceList);
        l.add(marshRaceCard);
        return l;
    }

    public void showVagina(ArrayList<List> primaryDataList) {
        ContentValues cv = new ContentValues();
        int i = 0;
        for (List concretePrimaryData : primaryDataList) {
            Log.d("MainActivity",Integer.toString(i));
            //TODO: Тут извлечь название таблицы
            Log.d("MainActivity",concretePrimaryData.get(0).getClass().getSimpleName());
            for (Object o : concretePrimaryData) {
                Log.d("MainActivity",o.getClass().getSimpleName());
                //cv.put("ИМЯ ЯЧЕЙКИ", данные);
                // ...
                //TODO: Тут написать логику сортировки данных по ячейкам в зависимости от названия переменной
            }
            //TODO: Вставить Content Value в таблицу по имени /tablename
            //long rowID = db.insert("/tablename", null, cv);
            //Log.d(LOG_TAG, "row inserted, ID = " + rowID);
            i++;
        }
        Toast toast = Toast.makeText(MainActivity.this, "Data from Api downloaded.", Toast.LENGTH_LONG);
        toast.show();
        Log.d("MainActivity", "Data from Api downloaded.");
    }

//
//             .. OLD Retrofit DOWNLOAD ..
//
// api.getTransportTypes(JSON).enqueue(new Callback<List<TransportType>>() {
//            @Override
//            public void onResponse(Call<List<TransportType>> call, Response<List<TransportType>> response) {
//                if (response.isSuccessful()) {
//                    transportTypes = response.body();
//                    primaryDataList.add(transportTypes);
//                    Log.d("API", "getTransportTypes");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<TransportType>> call, Throwable t) {
//                Log.d("MainActivity", "ohhhh Faaaaail!");
//            }
//        });
//        api.getMarshes(JSON).enqueue(new Callback<List<Marsh>>() {
//            @Override
//            public void onResponse(Call<List<Marsh>> call, Response<List<Marsh>> response) {
//                if (response.isSuccessful()) {
//                    marshes = response.body();
//                    primaryDataList.add(marshes);
//                    Log.d("API", "getMarshes");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Marsh>> call, Throwable t) {
//
//            }
//        });
//        api.getStops(JSON).enqueue(new Callback<List<Stop>>() {
//            @Override
//            public void onResponse(Call<List<Stop>> call, Response<List<Stop>> response) {
//                if (response.isSuccessful()) {
//                    stops = response.body();
//                    primaryDataList.add(stops);
//                    Log.d("API", "getStops");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Stop>> call, Throwable t) {
//
//            }
//        });
//        api.getOKATO(JSON).enqueue(new Callback<List<Okato>>() {
//            @Override
//            public void onResponse(Call<List<Okato>> call, Response<List<Okato>> response) {
//                if (response.isSuccessful()) {
//                    OKATO = response.body();
//                    primaryDataList.add(OKATO);
//                    Log.d("API", "getOKATO");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Okato>> call, Throwable t) {
//
//            }
//        });
//        api.getMarshOKATOLink(JSON).enqueue(new Callback<List<MarshOKATOLink>>() {
//            @Override
//            public void onResponse(Call<List<MarshOKATOLink>> call, Response<List<MarshOKATOLink>> response) {
//                if (response.isSuccessful()) {
//                    marshOkatoLink = response.body();
//                    primaryDataList.add(marshOkatoLink);
//                    Log.d("API", "getMarshOKATOLink");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<MarshOKATOLink>> call, Throwable t) {
//
//            }
//        });
//        api.getMarshRaceList(dt, mr_id, JSON).enqueue(new Callback<List<MarshRaceList>>() {
//            @Override
//            public void onResponse(Call<List<MarshRaceList>> call, Response<List<MarshRaceList>> response) {
//                if (response.isSuccessful()) {
//                    marshRaceList = response.body();
//                    primaryDataList.add(marshRaceList);
//                    Log.d("API", "getMarshRaceList");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<MarshRaceList>> call, Throwable t) {
//
//            }
//        });
//        api.getMarshRaceCard(dt, mr_id, JSON).enqueue(new Callback<List<MarshRaceCard>>() {
//            @Override
//            public void onResponse(Call<List<MarshRaceCard>> call, Response<List<MarshRaceCard>> response) {
//                if (response.isSuccessful()) {
//                    marshRaceCard = response.body();
//                    primaryDataList.add(marshRaceCard);
//                    Log.d("API", "getMarshRaceCard");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<MarshRaceCard>> call, Throwable t) {
//
//            }
//        });

}
