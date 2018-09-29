package com.woopig.service;

import com.woopig.entity.Admin;
import com.woopig.entity.Audit;
import com.woopig.entity.Banner;

import java.util.List;

public interface AuditService {

    List<Audit> getAll();

    Audit getOne(String id);

    List<Audit> getAllByBanner(Banner banner);

    List<Audit> getAllByAdmin(Admin admin);

    int save(Audit audit);

}
