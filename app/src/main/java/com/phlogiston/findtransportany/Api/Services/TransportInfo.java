package com.phlogiston.findtransportany.Api.Services;

/**
 * Created by Alexandr on 07.04.2018.
 */

public class TransportInfo {
    public int tr_id; // Уникальный код транспортного средства
    public int pk_id; // Уникальный код перевозчика
    public int tt_id; // Уникальный код типа транспорта
    public String tr_model; // Марка (модель) транспортного средства
    public String tr_category; // Категория транспортного средства
    public String tr_StateNumber; // Государственный номер транспортного средства
    public int tr_GarageNumber; // Гаражный номер транспортного средства
    public int tr_division; // Колонна
    //Габаритные размеры:
    public double tr_width; // Ширина (метры)
    public double tr_height; // Высота (метры)
    public double tr_length; // Длина (метры)
    //Навесное оборудование и др.:
    public boolean tr_IsLowFloor; // Признак «низкопольного» салона
    public boolean tr_IsAirConditioning; // Наличие кондиционера
    public boolean tr_IsBikeMount; // Наличие специальных креплений для перевозки велосипедов
    //Последнее техническое обслуживание:
    public String tr_ServiceDate; // Дата
    public String tr_ServicePlace; // Место проведения
    public String tr_ServiceDesc; // Отметки

}
