package com.takebook.demo.controller;

import com.takebook.demo.model.Rent;
import com.takebook.demo.service.RentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rents")
@RequiredArgsConstructor
public class RentController {

    private final RentService rentService;


    @PostMapping("/user/{userId}/book/{bookId}")
    public Optional<Rent> rentBook(@PathVariable(name = "userId") @NotNull long userId,
                                   @PathVariable(name = "bookId") @NotNull long bookId) {
        return rentService.rentBook(userId, bookId);
    }

    @GetMapping("user/{userId}")
    public List<Rent> getRentBookByUserId(@PathVariable(name = "userId") @NotNull long id) {
        return rentService.selectRentBookByUserId(id);
    }

    @GetMapping("book/{bookId}")
    public List<Rent> getRentBookByBookId(@PathVariable(name = "bookId") @NotNull long id) {
        return rentService.selectRentBookByBookId(id);
    }

    @GetMapping
    public List<Rent> rentBooks(){
        return rentService.findOverdueBooks();
    }
}
