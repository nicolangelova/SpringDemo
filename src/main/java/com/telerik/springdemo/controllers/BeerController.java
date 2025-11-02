package com.telerik.springdemo.controllers;

import com.telerik.springdemo.exceptions.DuplicateEntityException;
import com.telerik.springdemo.exceptions.EntityNotFoundException;
import com.telerik.springdemo.models.Beer;
import com.telerik.springdemo.services.BeerServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/beers")
public class BeerController {

    private BeerServiceImpl service;

    public BeerController() {

        this.service = new BeerServiceImpl();
    }

    @GetMapping
    public List<Beer> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Beer getById(@PathVariable int id) {
        try {
            return service.getById(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Beer with id %d not found", id));
        }
    }

    @PostMapping
    public Beer create(@Valid @RequestBody Beer beer){
        try {
            service.create(beer);
        } catch (DuplicateEntityException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
        return beer;
    }

    @PutMapping("/{id}")
    public Beer update(@PathVariable int id, @Valid @RequestBody Beer beer) {
        try {
            service.update(beer);
        }catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }catch (DuplicateEntityException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
        return beer;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        try {
            service.delete(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }





}
