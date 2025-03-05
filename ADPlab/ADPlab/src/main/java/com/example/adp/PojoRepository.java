package com.example.adp;

import org.springframework.data.repository.ListCrudRepository;

public interface PojoRepository extends ListCrudRepository<MyPOJO,Long> {}