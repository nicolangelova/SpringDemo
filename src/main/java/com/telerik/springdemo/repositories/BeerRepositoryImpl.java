package com.telerik.springdemo.repositories;

import com.telerik.springdemo.exceptions.EntityNotFoundException;
import com.telerik.springdemo.models.Beer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Component
public class BeerRepositoryImpl implements BeerRepository {
    private final List<Beer> beers;

    public BeerRepositoryImpl() {
        beers = new ArrayList<>();

        beers.add(new Beer(1,"Glarus English Ale", 4.6));
        beers.add(new Beer(2,"Rhombus Porter", 5));
        beers.add(new Beer(3, "Opasen Char", 6.6));
    }

    @Override
    public List<Beer> getAll() {
        return beers;
    }

    @Override
    public Beer getById(int id) {
        return beers.stream()
                .filter(beer-> beer.getId()==id)
                .findFirst()
                .orElseThrow(()-> new EntityNotFoundException("Beer", id));
    }

    @Override
    public Beer getByName(String name) {
        return beers.stream()
                .filter(beer -> beer.getName().equals(name))
                .findFirst()
                .orElseThrow(()->new EntityNotFoundException("Beer","name",name));
    }

    @Override
    public void crete(Beer beer) {
        beers.add(beer);
    }

    @Override
    public void  update(Beer beer) {
        Beer beerToUpdate = getById(beer.getId());
        beerToUpdate.setName(beer.getName());
        beerToUpdate.setAbv(beer.getAbv());
    }

    @Override
    public void delete(int id) {
        Beer beerToDelete = getById(id);
        beers.remove(beerToDelete);
    }
}
