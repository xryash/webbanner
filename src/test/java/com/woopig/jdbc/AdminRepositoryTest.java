package com.woopig.jdbc;

import com.woopig.entity.Admin;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import static org.junit.Assert.*;

public class AdminRepositoryTest {

    private EmbeddedDatabase embeddedDatabase;

    private JdbcTemplate jdbcTemplate;

    private AdminRepository adminRepository;


    @Before
    public void connect() {
        embeddedDatabase = new EmbeddedDatabaseBuilder()
                .addScripts("scheme.sql", "data.sql")
                .setType(EmbeddedDatabaseType.H2)
                .build();
        jdbcTemplate = new JdbcTemplate(embeddedDatabase);
        adminRepository = new AdminRepository.AdminRepositoryImpl(jdbcTemplate);
    }

    @After
    public void disconnect() {
        embeddedDatabase.shutdown();
    }



    @Test
    public void getAll() {
        Assert.assertNotNull(adminRepository.getAll());
        Assert.assertEquals(2, adminRepository.getAll().size());
    }

    @Test
    public void getOne() {
        Admin admin = adminRepository.getOne("92f85411-e8d7-4cc1-b378-8385733a307b");
        Assert.assertNotNull(admin);
        Assert.assertNotNull(admin.getName());
    }


    @Test(expected = AdminRepositoryException.class)
    public void getNonexistentOne() {
        adminRepository.getOne("nonexistentId");
    }

    @Test
    public void save() {
        Admin admin = new Admin("8c4eb06c-05ca-4dfe-87aa-f6c320e86e3e", "vanyaaa", "mynewhash");
        Assert.assertEquals(1, adminRepository.save(admin));
    }

    @Test(expected = AdminRepositoryException.class)
    public void saveInvalidData()  {
        Admin admin = new Admin("8c4eb06c-05ca-4dfe-87aa-f6c320e86e3e", "vanyaaa", null);
        adminRepository.save(admin);
    }

    @Test(expected = AdminRepositoryException.class)
    public void saveSameIdObjects() {
        Admin admin1 = new Admin("8c4eb06c-05ca-4dfe-87aa-f6c320e86e3e", "vanyaaa34", "mynewhash123");
        Admin admin2 = new Admin("8c4eb06c-05ca-4dfe-87aa-f6c320e86e3e", "vanyaaa12", "mynewhash456");
        adminRepository.save(admin1);
        adminRepository.save(admin2);
    }

    @Test
    public void update() {
        Admin admin = adminRepository.getOne("92f85411-e8d7-4cc1-b378-8385733a307b");
        admin.setHash("mynewhash");
        adminRepository.update(admin);
        Assert.assertNotNull(admin);
        admin = adminRepository.getOne("92f85411-e8d7-4cc1-b378-8385733a307b");
        Assert.assertEquals("mynewhash", admin.getHash());
    }

    @Test
    public void delete() {
        Assert.assertEquals(1, adminRepository.delete("92f85411-e8d7-4cc1-b378-8385733a307b"));
        Assert.assertEquals(0, adminRepository.delete("nonexistentId"));
        Assert.assertEquals(0, adminRepository.delete(null));
    }
}