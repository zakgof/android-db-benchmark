package com.zakgof.dbperf.lib;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.dizitart.no2.objects.Id;
import org.jetbrains.annotations.NotNull;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Person {

    public Person() {
    }

    public Person(@NotNull String firstName, @NotNull String lastName, int birthYear) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
    }

    public Person(long seed) {
        firstName = random(7, seed);
        lastName = random(10, seed + 100);
        birthYear = new Random(seed +200).nextInt(100) + 1900;
    }
    @NonNull private String firstName;
    @Id @PrimaryKey @NonNull private String lastName;
    private int birthYear;

    private static String random(int length, long seed) {
        Random r = new Random(seed);
        return IntStream.range(0, length)
            .mapToObj(i -> "" + (char)(r.nextInt(26) + 'a'))
            .collect(Collectors.joining());
    }
}
