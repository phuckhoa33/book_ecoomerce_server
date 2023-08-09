package com.phuckhoa.book_ecommerce_server.service;

import java.util.List;
import java.util.Set;

public interface ExtraService {
    long createRandomId(int desiredLength);

    boolean checkListExistInASet(Set<Long> primaryObject, List<Long> neededConfirmedObject);
}
