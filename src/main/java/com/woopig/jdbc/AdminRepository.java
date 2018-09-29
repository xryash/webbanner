package com.woopig.jdbc;

import com.woopig.entity.Admin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;


import java.sql.ResultSet;
import java.util.List;

public interface AdminRepository {

    RowMapper<Admin> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> {
        return new Admin(resultSet.getString("id"), resultSet.getString("name"), resultSet.getString("hash"));
    };

    List<Admin> getAll();

    Admin getOne(String id);

    int save(Admin admin);

    int update(Admin admin);

    int delete (String id);

    @Component
    public final class AdminRepositoryImpl implements AdminRepository {

        private final Logger LOGGER = LoggerFactory.getLogger(AdminRepositoryImpl.class);

        private final JdbcTemplate jdbcTemplate;

        @Autowired
        public AdminRepositoryImpl(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}

        private final static String GET_ALL_QUERY = "SELECT * FROM ADMINS WHERE isDeleted = 0";
        private final static String GET_ONE_QUERY = "SELECT *   FROM ADMINS WHERE id = ? AND isDeleted = 0";
        private final static String SAVE_ONE_QUERY = "INSERT INTO ADMINS (id, name, hash, isDeleted) VALUES (?, ?, ?,  0 )";
        private final static String UPDATE_ONE_QUERY = "UPDATE ADMINS SET hash = ? WHERE id = ? AND isDeleted = 0";
        private final static String DELETE_ONE_QUERY =  "UPDATE ADMINS SET isDeleted = 1 WHERE id = ?";


        @Override
        public List<Admin> getAll() {
            try {
                return jdbcTemplate.query(GET_ALL_QUERY, ROW_MAPPER);
            } catch (Exception ex) {
                LOGGER.error(AdminRepositoryException.class.getName() + ": " + ex.getMessage());
                throw new AdminRepositoryException("Ошибка. Не удалось получить список администраторов.");
            }
        }

        @Override
        public Admin getOne(String id) {
            try {
                return jdbcTemplate.queryForObject(GET_ONE_QUERY, ROW_MAPPER, id);
            } catch (Exception ex) {
                LOGGER.error(AdminRepositoryException.class.getName() + ": " + ex.getMessage());
                throw new AdminRepositoryException("Ошибка. Не удалось получить список администраторов.");
            }
        }

        @Override
        public int save(Admin admin) {
            try {
                return jdbcTemplate.update(SAVE_ONE_QUERY, admin.getId(), admin.getName(), admin.getHash());
            } catch (Exception ex) {
                LOGGER.error(AdminRepositoryException.class.getName() + ": " + ex.getMessage());
                throw new AdminRepositoryException("Ошибка. Не удалось получить список администраторов.");
            }
        }

        @Override
        public int update(Admin admin) {
            try {
                return jdbcTemplate.update(UPDATE_ONE_QUERY,admin.getHash(), admin.getId());
            } catch (Exception ex) {
                LOGGER.error(AdminRepositoryException.class.getName() + ": " + ex.getMessage());
                throw new AdminRepositoryException("Ошибка. Не удалось получить список администраторов.");
            }
        }

        @Override
        public int delete(String id) {
            try {
                return jdbcTemplate.update(DELETE_ONE_QUERY, id);
            } catch (Exception ex) {
                LOGGER.error(AdminRepositoryException.class.getName() + ": " + ex.getMessage());
                throw new AdminRepositoryException("Ошибка. Не удалось получить список администраторов.");
            }
        }
    }

}
