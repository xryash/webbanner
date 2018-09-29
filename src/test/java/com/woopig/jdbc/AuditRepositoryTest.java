package com.woopig.jdbc;

import com.woopig.entity.Admin;
import com.woopig.entity.Audit;
import com.woopig.entity.Banner;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import static org.junit.Assert.*;

public class AuditRepositoryTest {


    private EmbeddedDatabase embeddedDatabase;

    private JdbcTemplate jdbcTemplate;

    private AuditRepository auditRepository;

    @Before
    public void connect() {
        embeddedDatabase = new EmbeddedDatabaseBuilder()
                .addScripts("scheme.sql", "data.sql")
                .setType(EmbeddedDatabaseType.H2)
                .build();
        jdbcTemplate = new JdbcTemplate(embeddedDatabase);
        auditRepository = new AuditRepository.AuditRepositoryImpl(jdbcTemplate);
    }

    @After
    public void disconnect() {
        embeddedDatabase.shutdown();
    }

    @Test
    public void getAll() {
        Assert.assertNotNull(auditRepository.getAll());
        Assert.assertEquals(4, auditRepository.getAll().size());
    }

    @Test
    public void getOne() {
        Audit audit = auditRepository.getOne("a964432c-a044-471c-ae19-10a1734e4951");
        Assert.assertNotNull(audit);
        Assert.assertNull(audit.getColumnName());
    }

    @Test(expected = BannerRepositoryException.class)
    public void getNonexistentOne() {
        auditRepository.getOne("nonexistentId");
    }

    @Test
    public void getAllByBanner() {
        Banner banner = new Banner("nonexistentId", "http://lyalya.ru/logo1.jpg", 450, 450, "http://target1", "en-US");
        Assert.assertEquals(0, auditRepository.getAllByBanner(banner).size());
        banner.setId("0d1de3f1-d66b-48db-ab32-d8b31db0cb6c");
        Assert.assertEquals(3, auditRepository.getAllByBanner(banner).size());
        banner.setId(null);
        Assert.assertTrue(auditRepository.getAllByBanner(banner).isEmpty());
    }

    @Test(expected = BannerRepositoryException.class)
    public void getAllByInvalidBanner() {
        auditRepository.getAllByBanner(null);
    }

    @Test
    public void getAllByAdmin() {
        Admin admin = new Admin("aaa09229-b872-4bd9-a4a9-34bdf09761cd", "igoresha", "hash12345");
        Assert.assertEquals(3, auditRepository.getAllByAdmin(admin).size());
        admin.setId("nonexistentId");
        Assert.assertEquals(0, auditRepository.getAllByAdmin(admin).size());
        admin.setId(null);
        Assert.assertTrue(auditRepository.getAllByAdmin(admin).isEmpty());

    }

    @Test(expected = BannerRepositoryException.class)
    public void getAllByInvalidAdmin() {
        auditRepository.getAllByAdmin(null);
    }

    @Test
    public void save() {
        Audit audit = new Audit("c4e0812a-5012-4bd7-a718-b81c01fd7024", "92f85411-e8d7-4cc1-b378-8385733a307b",
                "4f7ef9ff-4aff-4488-9098-821ef267cd40", "CREATE",
                null, null, null, "06:49/25.09.18");
        Assert.assertEquals(1, auditRepository.save(audit));
        audit = new Audit("010fe6a0-86ab-4a66-9e79-7566685930ea", "92f85411-e8d7-4cc1-b378-8385733a307b",
                "4f7ef9ff-4aff-4488-9098-821ef267cd40", "UPDATE",
                "height", "300", "350", "06:49/25.09.18");
        Assert.assertEquals(1, auditRepository.save(audit));

    }

    @Test(expected = BannerRepositoryException.class)
    public void saveInvalidData() {
        Audit audit = new Audit("c4e0812a-5012-4bd7-a718-b81c01fd7024", "92f85411-e8d7-4cc1-b378-8385733a307b",
                "4f7ef9ff-4aff-4488-9098-821ef267cd40", null,
                null, null, null, "06:49/25.09.18");
        auditRepository.save(audit);
    }

    @Test(expected = BannerRepositoryException.class)
    public void saveSameIdObjects() {
        Audit audit1 = new Audit("c4e0812a-5012-4bd7-a718-b81c01fd7024", "92f85411-e8d7-4cc1-b378-8385733a307b",
                "4f7ef9ff-4aff-4488-9098-821ef267cd40", "CREATE",
                null, null, null, "06:49/25.09.18");
        Audit audit2 = new Audit("c4e0812a-5012-4bd7-a718-b81c01fd7024", "92f85411-e8d7-4cc1-b378-8385733a307b",
                "4f7ef9ff-4aff-4488-9098-821ef267cd40", "CREATE",
                null, null, null, "06:49/25.09.18");
        auditRepository.save(audit1);
        auditRepository.save(audit2);
    }
}