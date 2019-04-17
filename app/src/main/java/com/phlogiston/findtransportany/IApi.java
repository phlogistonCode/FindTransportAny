package com.phlogiston.findtransportany;

import com.phlogiston.findtransportany.Api.PrimaryData.Marsh;
import com.phlogiston.findtransportany.Api.PrimaryData.MarshOKATOLink;
import com.phlogiston.findtransportany.Api.PrimaryData.MarshRaceCard;
import com.phlogiston.findtransportany.Api.PrimaryData.MarshRaceList;
import com.phlogiston.findtransportany.Api.PrimaryData.Okato;
import com.phlogiston.findtransportany.Api.Services.FactPointInfo;
import com.phlogiston.findtransportany.Api.Services.MarshBenefit;
import com.phlogiston.findtransportany.Api.Services.MarshCoord;
import com.phlogiston.findtransportany.Api.Services.MarshRate;
import com.phlogiston.findtransportany.Api.Services.ParkInfo;
import com.phlogiston.findtransportany.Api.Services.PassTrafficInfo;
import com.phlogiston.findtransportany.Api.Services.Raspisanie;
import com.phlogiston.findtransportany.Api.PrimaryData.Stop;
import com.phlogiston.findtransportany.Api.PrimaryData.TransportType;
import com.phlogiston.findtransportany.Api.Services.StopByCoord;
import com.phlogiston.findtransportany.Api.Services.TableCur;
import com.phlogiston.findtransportany.Api.Services.TransportInfo;
import com.phlogiston.findtransportany.Api.Services.WorkInfo;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Alexandr on 26.03.2018.
 */

public interface IApi {

    String baseUrl = "http://asunav-ws.volganet.ru/asip/";

    // region PrimaryData
    @GET("getTransportTypes.php")
    Flowable<List<TransportType>> getTransportTypes(@Query("fmt") String fmt);

    @GET("getMarshes.php")
    Flowable<List<Marsh>> getMarshes(@Query("fmt") String fmt);

    @GET("getStops.php")
    Flowable<List<Stop>> getStops(@Query("fmt") String fmt);

    @GET("getOKATO.php")
    Flowable<List<Okato>> getOKATO(@Query("fmt") String fmt);

    @GET("getMarshOKATOLink.php")
    Flowable<List<MarshOKATOLink>> getMarshOKATOLink(@Query("fmt") String fmt);

    @GET("getMarshRaceList.php")
    Flowable<List<MarshRaceList>> getMarshRaceList(@Query("dt") int dt,
                                               @Query("mr_id") int mr_id,
                                               @Query("fmt") String fmt);

    @GET("getMarshRaceCard.php")
    Flowable<List<MarshRaceCard>> getMarshRaceCard(@Query("dt") int dt,
                                               @Query("mr_id") int mr_id,
                                               @Query("fmt") String fmt);
    // endregion


    // region Service timetable
    @GET("getRaspisanie.php")
    Call<List<Raspisanie>> getRaspisanie(@Query("dt") int dt,
                                         @Query("mr_id") int mr_id,
                                         @Query("rl_racetype") String rl_racetype,
                                         @Query("st_id") int st_id,
                                         @Query("rc_kkp") String rc_kkp,
                                         @Query("fmt") String fmt);
    // endregion


    // region Service tariffs and routes
    @GET("getMarshCoord.php")
    Call<List<MarshCoord>> getMarshCoord(@Query("mr_id") int mr_id,
                                         @Query("fmt") String fmt);

    @GET("getMarshRate.php")
    Call<List<MarshRate>> getMarshRate(@Query("mr_id") int mr_id,
                                       @Query("fmt") String fmt);

    @GET("getMarshBenefit.php")
    Call<List<MarshBenefit>> getMarshBenefit(@Query("mr_id") int mr_id,
                                             @Query("fmt") String fmt);
    // endregion


    // region Service enterprises and transport
    @GET("getParkInfo.php")
    Call<List<ParkInfo>> getParkInfo(@Query("mr_id") int mr_id,
                                     @Query("fmt") String fmt);

    @GET("getTransportInfo.php")
    Call<List<TransportInfo>> getTransportInfo(@Query("mr_id") int mr_id,
                                               @Query("fmt") String fmt);
    // endregion


    // region Service evaluation services
    @GET("getWorkInfo.php")
    Call<List<WorkInfo>> getWorkInfo(@Query("dt") int dt,
                                     @Query("mr_id") int mr_id,
                                     @Query("fmt") String fmt);

    @GET("getPassTrafficInfo.php")
    Call<List<PassTrafficInfo>> getPassTrafficInfo(@Query("dt") int dt,
                                                   @Query("mr_id") int mr_id,
                                                   @Query("fmt") String fmt);

    @GET("getFactPointInfo.php")
    Call<List<FactPointInfo>> getFactPointInfo(@Query("dt") int dt,
                                               @Query("mr_id") int mr_id,
                                               @Query("rl_racetype") String rl_racetype,
                                               @Query("st_id") int st_id,
                                               @Query("rc_kkp") String rc_kkp,
                                               @Query("fmt") String fmt);
    // endregion


    // region Service of an estimation of expected time of arrival of transport
    @GET("getTableCur.php")
    Call<List<TableCur>> getTableCur(@Query("st_id") int st_id,
                                     @Query("fmt") String fmt);

    @GET("getStopByCoord.php")
    Call<List<StopByCoord>> getStopByCoord(@Query("lat") double lat,
                                           @Query("long") double _long,
                                           @Query("radius") int radius,
                                           @Query("fmt") String fmt);
    // endregion
}
