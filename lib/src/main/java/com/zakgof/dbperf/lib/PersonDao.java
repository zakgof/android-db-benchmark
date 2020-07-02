package com.zakgof.dbperf.lib;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface PersonDao {
    @Query("SELECT * FROM person WHERE lastName = :lastName")
    Person getByLastName(String lastName);

    @Insert
    void insert(Person employee);
}