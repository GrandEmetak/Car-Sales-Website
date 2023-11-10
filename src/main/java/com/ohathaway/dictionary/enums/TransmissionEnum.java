package com.ohathaway.dictionary.enums;

public enum TransmissionEnum {

    MANUAL(1L, "Ручная"),
    AUTOMATIC(2L, "Автоматическая"),
    CVT(3L, "вариатор");

    private final Long id;
    private final String value;

    TransmissionEnum(Long id, String value) {
        this.id = id;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public String getValue() {
        return value;
    }
}
