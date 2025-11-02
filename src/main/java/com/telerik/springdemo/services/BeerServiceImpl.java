package com.telerik.springdemo.services;

import com.telerik.springdemo.exceptions.DuplicateEntityException;
import com.telerik.springdemo.exceptions.EntityNotFoundException;
import com.telerik.springdemo.models.Beer;
import com.telerik.springdemo.repositories.BeerRepositoryImpl;

import java.util.List;

public class BeerServiceImpl {

    private BeerRepositoryImpl repository;

    public BeerServiceImpl() {
        this.repository = new BeerRepositoryImpl();
    }

    public List<Beer> getAll() {
        return repository.getAll();
    }

    public Beer getById(int id) {
        return repository.getById(id);
    }

    public void create(Beer beer) {
        boolean duplicateExists = true;

        try {
            repository.getByName(beer.getName());
        }catch (EntityNotFoundException e) {
            duplicateExists = false;
        }

        if (duplicateExists) {
            throw new DuplicateEntityException("Beer","name",beer.getName());
        }

        repository.crete(beer);
    }

    public void update(Beer beer) {
        boolean duplicateExists = true;
        try {
            Beer existingBeer = repository.getByName(beer.getName());
            if(existingBeer.getId()==beer.getId()) {
                duplicateExists=false;
            }
        } catch (EntityNotFoundException e){
            duplicateExists = false;
        }
        if (duplicateExists) {
            throw new DuplicateEntityException("Beer","name",beer.getName());
        }

        repository.update(beer);
    }

    public void delete(int id) {
        repository.delete(id);
    }

}
