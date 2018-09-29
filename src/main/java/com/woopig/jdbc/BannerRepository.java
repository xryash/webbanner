package com.woopig.jdbc;



import com.woopig.entity.Banner;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;


import java.sql.ResultSet;
import java.util.List;

public interface BannerRepository {

    RowMapper<Banner> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> {
         return new Banner(resultSet.getString("id"), resultSet.getString("imgSrc"),
                resultSet.getInt("width"), resultSet.getInt("height"),
                resultSet.getString("targetUrl"), resultSet.getString("langId"));
    };

    List<Banner> getAll();

    Banner getOne(String id);

    int save(Banner banner);

    int update(Banner banner);

    int delete(String id);

    @Component
    public final class BannerRepositoryImpl implements BannerRepository {

        private final Logger LOGGER = LoggerFactory.getLogger(BannerRepositoryImpl.class);

        private final JdbcTemplate jdbcTemplate;

        @Autowired
        public BannerRepositoryImpl(JdbcTemplate jdbcTemplate) {
            this.jdbcTemplate = jdbcTemplate;
        }


        private final static String GET_ALL_QUERY = "SELECT * FROM banners WHERE isDeleted = 0";
        private final static String GET_ONE_QUERY = "SELECT * FROM banners WHERE id = ? AND isDeleted = 0";
        private final static String SAVE_ONE_QUERY = "INSERT INTO banners (id, imgSrc, width, height, targetUrl, langId, isDeleted ) VALUES (?, ?, ?, ?, ?, ?, 0 )";
        private final static String UPDATE_ONE_QUERY = "UPDATE banners SET imgSrc = ?, width = ?, height = ?, targetUrl = ?, langId = ? WHERE id = ? AND isDeleted = 0";
        private final static String DELETE_ONE_QUERY =  "UPDATE banners SET isDeleted = 1 WHERE id = ?";
        //private final static String DELETE_ONE_QUERY =  "DELETE FROM banners WHERE id = ?";

        @Override
        public List<Banner> getAll() {
            try {
                return jdbcTemplate.query(GET_ALL_QUERY, ROW_MAPPER);
            } catch (Exception ex) {
                LOGGER.error("BannerRepositoryException: " + ex.getMessage());
                throw new BannerRepositoryException("Ошибка. Не удалось получить список баннеров.");
            }
        }

        @Override
        public Banner getOne(String id) {
            try {
                return jdbcTemplate.queryForObject(GET_ONE_QUERY, ROW_MAPPER, id);
            } catch (Exception ex) {
                ex.printStackTrace();
                LOGGER.error("BannerRepositoryException: " + ex.getMessage());
                throw new BannerRepositoryException("Ошибка. Не удалось получить баннер.");
            }
        }

        @Override
        public int save(Banner banner) {
            try {
                return jdbcTemplate.update(SAVE_ONE_QUERY,banner.getId(), banner.getImgSrc(),
                                                          banner.getWidth(), banner.getHeight(),
                                                          banner.getTargetUrl(), banner.getLangId());
            } catch (Exception ex) {
                LOGGER.error("BannerRepositoryException: " + ex.getMessage());
                throw new BannerRepositoryException("Ошибка. Не удалось сохранить баннер.");
            }
        }

        @Override
        public int update(Banner banner) {
            try {
                return jdbcTemplate.update(UPDATE_ONE_QUERY, banner.getImgSrc(), banner.getWidth(),
                                                             banner.getHeight(),banner.getTargetUrl(),
                                                             banner.getLangId(), banner.getId());
            } catch (Exception ex) {
                LOGGER.error("BannerRepositoryException: " + ex.getMessage());
                throw new BannerRepositoryException("Ошибка. Не удалось обновить данные баннера.");
            }
        }

        @Override
        public int delete(String id) {
            try {
                return jdbcTemplate.update(DELETE_ONE_QUERY, id);
            } catch (Exception ex) {
                LOGGER.error("BannerRepositoryException: " + ex.getMessage());
                throw new BannerRepositoryException("Ошибка. Не удалось удалить баннер.");
            }
        }
    }

}

