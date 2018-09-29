package com.woopig.controller;


import com.woopig.entity.Banner;
import com.woopig.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("banners")
public class BannerRestController {

    private AdminService service;



    private List<String> list = new ArrayList<String>(){{ add("private1"); add("private2"); add("private3"); add("private4"); add("private5"); }};
    /*
    @Autowired
    public BannerRestController(AdminService service) {
        this.service = service;
    }
    */

    @GetMapping
    public List<String> getAll() {
        return list;
    }




    @GetMapping("id")
    public Banner getOne() {

        //return service.getOne(langId) {
        return null;
    }
}



