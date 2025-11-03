package com.telerik.springdemo.repositories;

import com.telerik.springdemo.models.Beer;

import java.util.List;

public interface BeerRepository {
    List<Beer> getAll();

    Beer getById(int id);

    Beer getByName(String name);

    void crete(Beer beer);

    void update(Beer beer);

    void delete(int id);
}
