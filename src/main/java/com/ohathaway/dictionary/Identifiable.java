package com.ohathaway.dictionary;

import java.io.Serializable;

public interface Identifiable extends Serializable {

    Long getId();

    void setId(Long id);

    boolean isArchive();

    void setArchive(boolean isArchive);

    String getName();

    boolean isReadOnly();
}
