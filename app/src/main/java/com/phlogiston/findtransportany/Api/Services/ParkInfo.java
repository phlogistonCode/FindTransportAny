package com.phlogiston.findtransportany.Api.Services;

/**
 * Created by Alexandr on 04.04.2018.
 */

public class ParkInfo {
    public int pk_id; // Уникальный код перевозчика
    public String pk_title; // Полное наименование перевозчика
    //Лицензия
    public String pk_LicenseNumber; // Номер
    public String pk_LicenseName; // Наименование
    public String pk_LicenseDate; // Дата выдачи
    //Договор
    public String pk_ContractBasis; // Основание
    public String pk_ContractName; // Наименование
    public String pk_ContractDate; // Дата заключения
    //Юридическое лицо
    public String pk_LegalEntityName; // Наименование
    public String pk_LegalEntityPlace; // Местоположение
    //Физическое лицо
    public String pk_IndividualLastName; // Фамилия
    public String pk_IndividualFirstName; // Имя
    public String pk_IndividualMiddleName; // Отчество
    //Адреса
    public String pk_LegalAdress; // Юридический адрес
    public String pk_PhysicalAdress; // Физический адрес
    public String pk_MailingAddress; // Почтовый адрес
    //Реквизиты
    public String pk_TaxpayerNumber; // ИНН
    public String pk_CodeOfRegistrationReason; // КПП
    public String pk_PrimaryStateRegistrationNumber; // ОГРН (ИП)
    //Банковские реквизиты
    public String pk_CheckingAccount; // Расчетный счет (р/с)
    public String pk_BankName; // Название банка
    public String pk_BankPlace; // Местоположение
    public String pk_CorrespondentAccount; // Корреспондентский счет (к/с)
    public String pk_BankIdentificationCode; // Банковский идентификационный код (БИК)
    //Связь
    public String pk_PhoneNumber1; // Номер телефона 1
    public String pk_PhoneNumber2; // Номер телефона 2
    public String pk_PhoneNumber3; // Номер телефона 3
    public String pk_FaxNumber; // Номер факса
    public String pk_Email; // Электронная почта
}
