/*
Copyright (c) 2016-2023 VMware Inc. or its affiliates, All Rights Reserved Halsyon.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */
package com.ohathaway.model.entity;

import javax.persistence.GenerationType;
import javax.persistence.*;

import java.util.Objects;

/**
 * Модель данных описывающая Автомобиль
 */
@Entity
@Table(name = "auto", schema = "develop")
public class Auto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String mark;

    private String bodyType;

    private String engine;

    private String transmission;

    private String color;

    private String drive;

    private String mileage;

    public Auto() {
    }

    public static Auto of(String mark, String bodyType, String engine,
                          String transmission, String color,
                          String drive, String mileage) {
        Auto auto = new Auto();
        auto.mark = mark;
        auto.bodyType = bodyType;
        auto.engine = engine;
        auto.transmission = transmission;
        auto.color = color;
        auto.drive = drive;
        auto.mileage = mileage;
        return auto;
    }

    public Auto of(String mark, String bodyType) {
        Auto auto = new Auto();
        this.mark = mark;
        this.bodyType = bodyType;
        return auto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDrive() {
        return drive;
    }

    public void setDrive(String drive) {
        this.drive = drive;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Auto auto = (Auto) o;
        return Objects.equals(id, auto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("Car: id=%s, mark=%s, bodyType=%s, engine=%s,"
                        + " transmission=%s, color=%s, drive=%s, mileage=%s",
                id, mark, bodyType, engine, transmission, color, drive, mileage);
    }
}
