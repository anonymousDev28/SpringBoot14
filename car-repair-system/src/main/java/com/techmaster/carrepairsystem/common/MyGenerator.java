package com.techmaster.carrepairsystem.common;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.util.Random;

public class MyGenerator implements IdentifierGenerator {
    public static final String generatorName = "myGenerator";
    @Override
    public Object generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        long min = 1000000000000L; //13 digits inclusive
        long max = 10000000000000L; //14 digits exclusive
        Random random = new Random();
        long number = min+((long)(random.nextDouble()*(max-min)));
        return number;
    }
}
