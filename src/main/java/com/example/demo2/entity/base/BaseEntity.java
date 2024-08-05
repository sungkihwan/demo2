package com.example.demo2.entity.base;

import jakarta.persistence.MappedSuperclass;
//import org.hibernate.annotations.TypeDef;
//import org.hibernate.annotations.TypeDefs;

import java.io.Serializable;

//@TypeDefs({
//        @TypeDef(name = "json", typeClass = JsonString.class),
////        @TypeDef(name = "json", typeClass = JsonStringType.class),
////        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
//})
@MappedSuperclass
public abstract class BaseEntity implements Serializable {
}
