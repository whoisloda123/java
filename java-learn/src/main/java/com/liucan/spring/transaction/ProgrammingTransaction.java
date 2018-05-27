package com.liucan.spring.transaction;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;

/**
 * @author liucan
 * @date 2018/5/27
 * @brief 编程式事务管理，一般不建议用
 */
public class ProgrammingTransaction {
    private PlatformTransactionManager transactionManager;
    private JdbcTemplate jdbcTemplateObject;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public void example() {
        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);

        try {
            String sql1 = "insert into user_order(user_id, order_id, order_stat, price, create_time, update_time)" +
                    "values(3131312, 'order3233', 0, 12, now(), now())";
            jdbcTemplateObject.update(sql1);

            String sql2 = "insert into Marks(sid, marks, year) " + "values (?, ?, ?)";
            jdbcTemplateObject.update(sql2);

            transactionManager.commit(status);
        } catch (Exception e) {
            System.out.println("Error operator, rolling back");
            transactionManager.rollback(status);
        }
    }
}
