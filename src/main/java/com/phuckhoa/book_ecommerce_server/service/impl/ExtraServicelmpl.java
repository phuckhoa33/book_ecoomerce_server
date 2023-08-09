package com.phuckhoa.book_ecommerce_server.service.impl;

import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.phuckhoa.book_ecommerce_server.service.ExtraService;

@Service
public class ExtraServicelmpl implements ExtraService {

    @Override
    public long createRandomId(int desiredLength) {
        if (desiredLength <= 0) {
            throw new IllegalArgumentException("Desired length must be a positive integer.");
        }

        Random random = new Random();
        long min = (long) Math.pow(10, desiredLength - 1);
        long max = (long) Math.pow(10, desiredLength) - 1;

        return min + random.nextLong() % (max - min + 1);
    }

    @Override
    public boolean checkListExistInASet(Set<Long> primaryObject, List<Long> neededConfirmedObject) {
        for (Long bookCategoryId : neededConfirmedObject) {
            if (!primaryObject.contains(bookCategoryId)) {
                return false;
            }
        }

        return true;
    }

}
