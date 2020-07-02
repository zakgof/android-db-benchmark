package com.zakgof.dbperf.lib;

import com.zakgof.db.velvet.entity.Entities;
import com.zakgof.db.velvet.entity.IEntityDef;

public class Defs {
    public static IEntityDef<String, Person> PERSON = Entities.from(Person.class).make(String.class, Person::getLastName);
}
