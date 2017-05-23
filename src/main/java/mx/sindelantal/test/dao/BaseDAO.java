package mx.sindelantal.test.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class BaseDAO {
    public DataSource dataSource;
    public JdbcTemplate jdbcTemplate;
    
    @Autowired
    private ApplicationContext appContext;

    @Autowired
    public void setApplicationContext(ApplicationContext appContext) {
        this.appContext = appContext;
    }
    
    @Autowired
    @Qualifier("dataSource")
    private void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.dataSource = dataSource;
    }
    protected JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
}
