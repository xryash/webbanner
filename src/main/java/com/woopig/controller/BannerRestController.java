package com.woopig.controller;


import com.woopig.entity.Banner;

import com.woopig.service.MainDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BannerRestController {


    private MainDataService service;

    @Autowired
    public BannerRestController(MainDataService service) {
        this.service = service;
    }




    @GetMapping("banners")
    public List<String> getAll() {
    return null;
    }

    @GetMapping("banners/{id}")
    public Banner getOne() {

        //return service.getOne(langId) {
        return null;
    }
}



