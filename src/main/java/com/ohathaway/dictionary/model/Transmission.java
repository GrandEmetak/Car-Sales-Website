package com.ohathaway.dictionary.model;

import com.ohathaway.dictionary.AbstractDictionary;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "transmission")
@Table(schema = "dictionary")
public class Transmission extends AbstractDictionary {
}
