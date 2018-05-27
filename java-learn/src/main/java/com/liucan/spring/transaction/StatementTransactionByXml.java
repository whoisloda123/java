package com.liucan.spring.transaction;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @author liucan
 * @date 2018/5/27
 * @brief spring声明式事务管理，可通过配合aop来，建议用此配置，通过xml方式
 */
public class StatementTransactionByXml {
    private JdbcTemplate jdbcTemplateObject;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    public void example() {
        try {
            String sql1 = "insert into user_order(user_id, order_id, order_stat, price, create_time, update_time)" +
                    "values(3131312, 'order3233', 0, 12, now(), now())";
            jdbcTemplateObject.update(sql1);

            String sql2 = "insert into Marks(sid, marks, year) " + "values (?, ?, ?)";
            jdbcTemplateObject.update(sql2);
        } catch (Exception e) {
            System.out.println("Error operator, rolling back");
            throw e; //此处需要抛出异常，让aop能捕获到
        }
    }
}
