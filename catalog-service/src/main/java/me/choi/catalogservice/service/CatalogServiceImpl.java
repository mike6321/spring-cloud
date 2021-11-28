package me.choi.catalogservice.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import me.choi.catalogservice.jpa.CatalogEntity;
import me.choi.catalogservice.jpa.CatalogRepository;
import org.springframework.stereotype.Service;

@Data
@Slf4j
@AllArgsConstructor
@Service
public class CatalogServiceImpl implements CatalogService {

    private final CatalogRepository catalogRepository;

    @Override
    public Iterable<CatalogEntity> getAllCatalogs() {
        return catalogRepository.findAll();
    }

}
