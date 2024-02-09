package com.trx.managament.spring.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trx.managament.spring.boot.entity.Rekening;
import com.trx.managament.spring.boot.repo.RekeningRepo;

import jakarta.transaction.Transactional;

@Service
public class RekeningService {

    @Autowired
    private RekeningRepo rekeningRepo;

    public Rekening create(Rekening rekening){
        return rekeningRepo.save(rekening);
    }

    public Iterable<Rekening> findAll(){
        return rekeningRepo.findAll();
    }

    @Transactional
    public void transfer(String norek1, String norek2, double amount){
        Rekening rekening1 = rekeningRepo.findByNorek(norek1);
        //cek validasi rekening 1
        if(rekening1==null){
            throw new RuntimeException("Norek1 tidak valid");
        }
        //cek saldo rekening 1
        if(rekening1.getSaldo()<amount){
             throw new RuntimeException("Saldo tidak cukup");
        }
        //jika valid maka saldo akan dikurangi amount trx
        rekening1.setSaldo(rekening1.getSaldo()-amount);
        rekeningRepo.save(rekening1);

        Rekening rekening2 = rekeningRepo.findByNorek(norek2);
        //cek validasi rekening 2
        if(rekening2==null){
            throw new RuntimeException("Norek2 tidak valid");
        }
        //jika valid maka saldo akan ditambah amount trx
        rekening2.setSaldo(rekening2.getSaldo()+amount);
        //update tambah saldo rekening 2
        rekeningRepo.save(rekening2);
    }
}
