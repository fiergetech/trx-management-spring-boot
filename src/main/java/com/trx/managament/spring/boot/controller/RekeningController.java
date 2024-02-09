package com.trx.managament.spring.boot.controller;

import org.springframework.web.bind.annotation.RestController;

import com.trx.dto.TransferRequest;
import com.trx.managament.spring.boot.entity.Rekening;
import com.trx.managament.spring.boot.service.RekeningService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@RequestMapping("/rekening")

public class RekeningController {
    
    @Autowired
    private RekeningService service;

    @PostMapping
    public Rekening create(@RequestBody Rekening rekening){
        return service.create(rekening);
    }

    @GetMapping
    public Iterable<Rekening> findAll(){
        return service.findAll();

    }

    @PostMapping("transfer")
    public void transfer(@RequestBody TransferRequest transfer){
        service.transfer(transfer.getNorek1(), transfer.getNorek2(), transfer.getAmount());
    }
}
