package me.choi.catalogservice.service;

import me.choi.catalogservice.jpa.CatalogEntity;

public interface CatalogService {

    Iterable<CatalogEntity> getAllCatalogs();

}
