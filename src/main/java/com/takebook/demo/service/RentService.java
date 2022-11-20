package com.takebook.demo.service;

import com.takebook.demo.model.Rent;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface RentService {

    Optional<Rent> rentBook(long bookId, long userId);

    List<Rent> selectRentBookByUserId(Long userId);

    List<Rent> selectRentBookByBookId(Long bookId);

    BigDecimal calculateTotal(Long createDate, Long endDate);

    List<Rent> findOverdueBooks();
}
