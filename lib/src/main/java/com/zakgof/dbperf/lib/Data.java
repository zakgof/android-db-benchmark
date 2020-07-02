package com.zakgof.dbperf.lib;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Data {
    public static List<Person> PERSONS = IntStream.range(0, 1000)
            .mapToObj(i -> new Person(i * 1000))
            .collect(Collectors.toList());
    public static List<String> KEYS = IntStream.range(0, 1000)
            .mapToObj(i -> new Random(i).nextInt(1000))
            .map(PERSONS::get).map(Person::getLastName).collect(Collectors.toList());
}
