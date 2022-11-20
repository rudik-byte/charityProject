package com.takebook.demo.service.impl;

import com.takebook.demo.exception.NotFoundException;
import com.takebook.demo.model.Book;
import com.takebook.demo.model.Rent;
import com.takebook.demo.model.User;
import com.takebook.demo.repository.BookRepository;
import com.takebook.demo.repository.RentRepository;
import com.takebook.demo.repository.UserRepository;
import com.takebook.demo.service.RentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RentServiceImpl implements RentService {

    private final int FIXED_PRICE_PER_DAY = 5;

    private final RentRepository rentRepository;

    private final BookRepository bookRepository;

    private final UserRepository userRepository;

    private final UserServiceImpl userService;

    @Override
    public Optional<Rent> rentBook(long bookId, long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User with this " + userId + " not found"));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new NotFoundException("Book with this " + bookId + " not found"));
        if (userService.isBookLimitReached(user)) {
            System.out.println("Book limit reached!" + user.getBookLimit() + " rented!");
            return Optional.empty();
        } else if (book.isRented()) {
            System.out.println("Book is rented at this moment. Sorry!");
            return Optional.empty();
        } else {
            book.setRented(true);
            user.setBooksRented(user.getBooksRented() + 1);
            Rent rent = rentRepository.save(new Rent(book, user));
            System.out.println("Book " + book.getName() + " rented successfully");
            return Optional.of(rent);
        }
    }

    @Override
    public List<Rent> selectRentBookByUserId(Long userId) {
        return userRepository.findAll().stream()
                .filter(user -> user.getId().equals(userId))
                .findAny()
                .map(User::getRentedBooks)
                .orElseThrow(()->new NotFoundException("User with this "+userId+" don`t have a book list"));
    }

    @Override
    public List<Rent> selectRentBookByBookId(Long bookId) {
        return bookRepository.findAll().stream()
                .filter(book -> book.getId().equals(bookId))
                .findAny()
                .map(Book::getRentedBooks)
                .orElseThrow(()->new NotFoundException("Book with this"+bookId+" is absent"));
    }

    @Override
    public BigDecimal calculateTotal(Long createDate, Long endDate) {
        return BigDecimal.valueOf((endDate - createDate) * FIXED_PRICE_PER_DAY);
    }

    @Override
    public List<Rent> findOverdueBooks() {
        List<Rent> rentBookList = rentRepository.findAll();
        return rentBookList.stream()
                .filter(rent -> rent.getDateDue().before(Date.from(Instant.now())))
                .collect(Collectors.toList());
    }
}
