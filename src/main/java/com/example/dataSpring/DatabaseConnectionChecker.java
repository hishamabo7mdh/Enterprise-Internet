package com.example.dataSpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.JdbcTemplate;

@Component
public class DatabaseConnectionChecker implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        try {
            // قم بتنفيذ استعلام بسيط للتحقق من الاتصال بقاعدة البيانات
            jdbcTemplate.queryForList("SELECT 1");
            System.out.println("تم الاتصال بنجاح بقاعدة البيانات!");
        } catch (Exception e) {
            System.err.println("فشل الاتصال بقاعدة البيانات: " + e.getMessage());
        }
    }
}
