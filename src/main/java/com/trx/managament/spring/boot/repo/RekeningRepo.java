package com.trx.managament.spring.boot.repo;

import org.springframework.data.repository.CrudRepository;

import com.trx.managament.spring.boot.entity.Rekening;

public interface RekeningRepo extends CrudRepository<Rekening, Long>{
    public Rekening findByNorek(String noRekening);
}
