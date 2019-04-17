package com.phlogiston.findtransportany.Api.PrimaryData;

/**
 * Created by Alexandr on 03.04.2018.
 */

public class MarshRaceList {

    /**
    Типы рейсов в подсистеме информационного взаимодействия идентифицируются
    латинскими буквами от "A" до "Z". Нечетные буквы обозначают рейсы прямого
    направления, а четные обозначают рейсы обратного направления. Тип рейса "A" -
    основной прямой рейс. Тип рейса "B" - основной обратный. Типы рейсов "C", "D", "E" и
    т.д. - измененные рейсы. В случае кольцевого маршрута используются только нечетные
    буквы “A”, “C”, “E” и т.д.
     */

    public String rl_racetype;
    public int rl_firststation_id;
    public int rl_laststation_id;
}
