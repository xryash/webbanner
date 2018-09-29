package com.woopig.service;

import com.woopig.entity.Admin;
import com.woopig.entity.Audit;
import com.woopig.entity.Banner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public interface MainDataService {


    List<Audit> getAudits();

    List<Audit> getAuditsByBanner(Banner banner);

    List<Audit> getAuditsByAdmin(Admin admin);

    List<Banner> getBanners();

    Audit getAudit(String id);

    Banner getBanner(String id);

    int deleteBanner(String id);

    int saveBanner(Banner banner);

    int updateBanner(Banner banner);


    @Component
    public final class MainDataServiceImpl implements MainDataService {

        @Autowired
        AuditService auditService;

        @Autowired
        BannerService bannerService;

        private final String TEMPLATE_ADMIN_ID = "aaa09229-b872-4bd9-a4a9-34bdf09761cd";


        private final String UPDATE_OPERATION_TYPE = "UPDATE";

        private final String DELETE_OPERATION_TYPE = "DELETE";

        private final String CREATE_OPERATION_TYPE = "CREATE";

        @Override
        public List<Audit> getAudits() {
            List<Audit> audits = auditService.getAll();
            return audits;
        }

        @Override
        public List<Audit> getAuditsByBanner(Banner banner) {
            List<Audit> audits = auditService.getAllByBanner(banner);
            return audits;
        }

        @Override
        public List<Audit> getAuditsByAdmin(Admin admin) {
            List<Audit> audits = auditService.getAllByAdmin(admin);
            return audits;
        }

        @Override
        public List<Banner> getBanners() {
            List<Banner> banners = bannerService.getAll();
            return banners;
        }

        @Override
        public Audit getAudit(String id) {
            Audit audit = auditService.getOne(id);
            return audit;
        }

        @Override
        public Banner getBanner(String id) {
            Banner banner = bannerService.getOne(id);
            return banner;
        }

        @Override
        public int deleteBanner(String id) {
            int result = bannerService.delete(id);
            if (result == 1) {
                Audit audit = prepareSimpleAudit(id, DELETE_OPERATION_TYPE);
                auditService.save(audit);
            }
            return result;
        }

        @Override
        public int saveBanner(Banner banner) {
            int result = bannerService.save(banner);
            if (result == 1) {
                Audit audit = prepareSimpleAudit(banner.getId(), CREATE_OPERATION_TYPE);
                auditService.save(audit);
            }
            return result;
        }

        @Override
        public int updateBanner(Banner banner) {
            Banner oldBanner = bannerService.getOne(banner.getId());
            if (Objects.equals(oldBanner, banner)) return 1;
            int result = bannerService.update(banner);
            if (result == 1) {

                if (oldBanner.getImgSrc() != banner.getImgSrc()) {
                    Audit audit = prepareUpdateAudit(banner.getId(), UPDATE_OPERATION_TYPE,"imgSrc", oldBanner.getImgSrc(), banner.getImgSrc());
                    auditService.save(audit);
                }

                if (oldBanner.getHeight() != banner.getHeight() ) {
                    Audit audit = prepareUpdateAudit(banner.getId(), UPDATE_OPERATION_TYPE,"height", oldBanner.getHeight().toString(), banner.getHeight().toString());
                    auditService.save(audit);
                }

                if (oldBanner.getWidth() != banner.getWidth() ) {
                    Audit audit = prepareUpdateAudit(banner.getId(), UPDATE_OPERATION_TYPE,"width", oldBanner.getWidth().toString(), banner.getWidth().toString());
                    auditService.save(audit);
                }

                if (oldBanner.getTargetUrl() != banner.getTargetUrl() ) {
                    Audit audit = prepareUpdateAudit(banner.getId(), UPDATE_OPERATION_TYPE,"targetUrl", oldBanner.getTargetUrl(), banner.getTargetUrl());
                    auditService.save(audit);
                }

                if (oldBanner.getLangId() != banner.getLangId() ) {
                    Audit audit = prepareUpdateAudit(banner.getId(), UPDATE_OPERATION_TYPE,"langId", oldBanner.getLangId().toString(), banner.getLangId().toString());
                    auditService.save(audit);
                }
            }
            return result;
        }

        private Audit prepareUpdateAudit(String id, String operationType, String columnName, String oldValue, String newValue) {
            Audit audit = prepareSimpleAudit(id, operationType);
            audit.setColumnName(columnName);
            audit.setOldValue(oldValue);
            audit.setNewValue(newValue);
            return audit;
        }

        // admin id
        private Audit prepareSimpleAudit(String id, String operationType) {
            String newId = getUUID();
            String bannerId = id;
            String adminId = TEMPLATE_ADMIN_ID;
            String recordDate = getDateNow();
            return new Audit(newId, bannerId, adminId, operationType, recordDate);
        }

        private String getUUID() { return UUID.randomUUID().toString(); }

        private String getDateNow() {
            Date dateNow = new Date();
            SimpleDateFormat formatForDateNow = new SimpleDateFormat("hh:mm'/'dd.MM.yy");
            String formattedDate = formatForDateNow.format(dateNow);
            return formattedDate;
        }


    }

}


