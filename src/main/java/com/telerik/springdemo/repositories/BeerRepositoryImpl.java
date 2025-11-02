package com.telerik.springdemo.repositories;

import com.telerik.springdemo.exceptions.EntityNotFoundException;
import com.telerik.springdemo.models.Beer;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

public class BeerRepositoryImpl {

    private List<Beer> beers;

    public BeerRepositoryImpl() {
        beers = new ArrayList<>();

        beers.add(new Beer(1,"Glarus English Ale", 4.6));
        beers.add(new Beer(2,"Rhombus Porter", 5));
    }

    public List<Beer> getAll() {
        return beers;
    }

    public Beer getById(int id) {
        return beers.stream()
                .filter(beer-> beer.getId()==id)
                .findFirst()
                .orElseThrow(()-> new EntityNotFoundException("Beer", id));
    }

    public Beer getByName(String name) {
        return beers.stream()
                .filter(beer -> beer.getName().equals(name))
                .findFirst()
                .orElseThrow(()->new EntityNotFoundException("Beer","name",name));
    }

    public void crete(Beer beer) {
        beers.add(beer);
    }

    public void  update(Beer beer) {
        Beer beerToUpdate = getById(beer.getId());
        beerToUpdate.setName(beer.getName());
        beerToUpdate.setAbv(beer.getAbv());
    }

    public void delete(int id) {
        Beer beerToDelete = getById(id);
        beers.remove(beerToDelete);
    }
}
