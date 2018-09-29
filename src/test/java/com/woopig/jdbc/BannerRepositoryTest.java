package com.woopig.jdbc;

import com.woopig.entity.Banner;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;


public class BannerRepositoryTest {

    private EmbeddedDatabase embeddedDatabase;

    private JdbcTemplate jdbcTemplate;

    private BannerRepository bannerRepository;

    @Before
    public void connect() {
        embeddedDatabase = new EmbeddedDatabaseBuilder()
                    .addScripts("scheme.sql", "data.sql")
                    .setType(EmbeddedDatabaseType.H2)
                    .build();
        jdbcTemplate = new JdbcTemplate(embeddedDatabase);
        bannerRepository = new BannerRepository.BannerRepositoryImpl(jdbcTemplate);
    }

    @After
    public void disconnect() {
        embeddedDatabase.shutdown();
    }

    @Test
    public void getAll() {
        Assert.assertNotNull(bannerRepository.getAll());
        Assert.assertEquals(4, bannerRepository.getAll().size());
    }

    @Test
    public void getOne() {
        Banner banner = bannerRepository.getOne("bbecccc6-381a-4b65-bc04-5fcd1bb3383d");
        Assert.assertNotNull(banner);
        Assert.assertNotNull(banner.getImgSrc());
    }

    @Test(expected = BannerRepositoryException.class)
    public void getNonexistentOne() {
        bannerRepository.getOne("nonexistentId");
    }

    @Test
    public void save() {
        Banner banner  = new Banner("388e63e8-ab7f-4a7f-8350-1afa901f4376", "http://lyalya.ru/test.jpg", 450, 450, "http://test", "ru-RU");
        Assert.assertEquals(1, bannerRepository.save(banner));
    }

    @Test(expected = BannerRepositoryException.class)
    public void saveInvalidData() {
        Banner banner  = new Banner("388e63e8-ab7f-4a7f-8350-1afa901f4376", null, 450, 450, "http://test", null);
        bannerRepository.save(banner);
    }

    @Test(expected = BannerRepositoryException.class)
    public void saveSameIdObjects() {
        Banner banner1  = new Banner("388e63e8-ab7f-4a7f-8350-1afa901f4376", "http://lyalya.ru/test.jpg", 450, 450, "http://test", "ru-RU");
        Banner banner2  = new Banner("388e63e8-ab7f-4a7f-8350-1afa901f4376", "http://lyalya.ru/test.jpg", 450, 450, "http://test", "ru-RU");
        bannerRepository.save(banner1);
        bannerRepository.save(banner2);
    }

    @Test
    public void update() {
        Banner banner = bannerRepository.getOne("004bffc2-1469-4548-8489-95e3af6b7af8");
        banner.setHeight(70000);
        bannerRepository.update(banner);
        Assert.assertNotNull(banner);
        Assert.assertNotNull(banner.getLangId());
        Assert.assertNotNull(banner.getImgSrc());
        banner = bannerRepository.getOne("004bffc2-1469-4548-8489-95e3af6b7af8");
        Assert.assertEquals(70000, (long) banner.getHeight());
    }


    @Test
    public void updateSameBanners() {
        Banner banner = bannerRepository.getOne("004bffc2-1469-4548-8489-95e3af6b7af8");
        Assert.assertEquals(1, bannerRepository.update(banner));
    }

    @Test(expected = BannerRepositoryException.class)
    public void updateInvalidData() {
        Banner banner = bannerRepository.getOne("004bffc2-1469-4548-8489-95e3af6b7af8");
        banner.setImgSrc(null);
        bannerRepository.update(banner);
    }

    @Test
    public void delete() {
        Assert.assertEquals(1, bannerRepository.delete("bbecccc6-381a-4b65-bc04-5fcd1bb3383d"));
        Assert.assertEquals(0, bannerRepository.delete("nonexistentId"));
        Assert.assertEquals(0, bannerRepository.delete(null));
    }

}