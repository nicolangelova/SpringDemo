package com.telerik.springdemo.services;

import com.telerik.springdemo.models.Beer;

import java.util.List;

public interface BeerService {
    List<Beer> getAll();

    Beer getById(int id);

    void create(Beer beer);

    void update(Beer beer);

    void delete(int id);
}
