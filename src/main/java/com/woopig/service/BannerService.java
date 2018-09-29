package com.woopig.service;

import com.woopig.entity.Banner;
import java.util.List;


public interface BannerService {

    List<Banner> getAll();

    Banner getOne(String id);

    int save(Banner banner);

    int update(Banner banner);

    int delete(String id);


}
