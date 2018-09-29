package com.woopig.jdbc;

import com.woopig.entity.Admin;
import com.woopig.entity.Audit;
import com.woopig.entity.Banner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.List;

public interface AuditRepository {

    RowMapper<Audit> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> {
        return new Audit(resultSet.getString("id"), resultSet.getString("idAdmin"),
                resultSet.getString("idBanner"), resultSet.getString("operationType"),
                resultSet.getString("columnName"), resultSet.getString("oldValue"),
                resultSet.getString("newValue"), resultSet.getString("recordDate"));
    };

    List<Audit> getAll();

    Audit getOne(String id);

    List<Audit> getAllByBanner(Banner banner);

    List<Audit> getAllByAdmin(Admin admin);

    int save(Audit audit);

    @Component
    public final class AuditRepositoryImpl implements AuditRepository {

        private final Logger LOGGER = LoggerFactory.getLogger(AuditRepositoryImpl.class);

        private final JdbcTemplate jdbcTemplate;

        @Autowired
        public AuditRepositoryImpl(JdbcTemplate jdbcTemplate) {
            this.jdbcTemplate = jdbcTemplate;
        }

        private final static String GET_ALL_QUERY = "SELECT * FROM AUDIT";
        private final static String GET_ONE_QUERY = "SELECT * FROM AUDIT WHERE id = ?";
        private final static String GET_ALL_BY_BANNER_QUERY = "SELECT * FROM AUDIT WHERE idBanner = ?";
        private final static String GET_ALL_BY_ADMIN_QUERY = "SELECT * FROM AUDIT WHERE idAdmin = ?";
        private final static String SAVE_ONE_QUERY = "INSERT INTO AUDIT (id, idAdmin, idBanner," +
                "                                                        operationType, columnName, oldValue," +
                "                                                        newValue, recordDate ) " +
                "                                                        VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        @Override
        public List<Audit> getAll() {
            try {
                return jdbcTemplate.query(GET_ALL_QUERY, ROW_MAPPER);
            } catch (Exception ex) {
                LOGGER.error("AuditRepositoryException: " + ex.getMessage());
                throw new AuditRepositoryException("Ошибка. Не удалось получить список записей аудита.");
            }
        }

        @Override
        public Audit getOne(String id) {
            try {
                return jdbcTemplate.queryForObject(GET_ONE_QUERY, ROW_MAPPER, id);
            } catch (Exception ex) {
                LOGGER.error("BannerRepositoryException: " + ex.getMessage());
                throw new BannerRepositoryException("Ошибка. Не удалось получить запись аудита.");
            }
        }

        @Override
        public List<Audit> getAllByBanner(Banner banner) {
            try {
                return jdbcTemplate.query(GET_ALL_BY_BANNER_QUERY, ROW_MAPPER, banner.getId());
            } catch (Exception ex) {
                LOGGER.error("BannerRepositoryException: " + ex.getMessage());
                throw new BannerRepositoryException("Ошибка. Не удалось получить список записей аудита с данным банером.");
            }
        }

        @Override
        public List<Audit> getAllByAdmin(Admin admin) {
            try {
                return jdbcTemplate.query(GET_ALL_BY_ADMIN_QUERY, ROW_MAPPER, admin.getId());
            } catch (Exception ex) {
                LOGGER.error("BannerRepositoryException: " + ex.getMessage());
                throw new BannerRepositoryException("Ошибка. Не удалось получить список записей аудита с данным администратором.");
            }
        }

        @Override
        public int save(Audit audit) {
            try {
                return jdbcTemplate.update(SAVE_ONE_QUERY, audit.getId(), audit.getAdminId(),
                                                           audit.getBannerId(), audit.getOperationType(),
                                                           audit.getColumnName(), audit.getOldValue(),
                                                           audit.getNewValue(), audit.getRecordDate());
            } catch (Exception ex) {
                LOGGER.error("BannerRepositoryException: " + ex.getMessage());
                ex.printStackTrace();
                throw new BannerRepositoryException("Ошибка. Не удалось сохранить запись аудита.");
            }
        }
    }
}
