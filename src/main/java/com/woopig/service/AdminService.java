package com.woopig.service;

import com.woopig.entity.Banner;

import java.util.List;

public interface AdminService {

    List<Banner> getAll();

    Banner getOne(int id);

    int save(Banner banner);

    Banner update(Banner banner);

    int delete(int langId);



}
