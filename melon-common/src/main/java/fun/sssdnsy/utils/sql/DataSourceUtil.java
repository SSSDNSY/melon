package fun.sssdnsy.utils.sql;

import fun.sssdnsy.config.DynamicDataSource;
import fun.sssdnsy.utils.spring.SpringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.jdbc.DataSourceBuilder;

import javax.sql.DataSource;

/**
 * @Desc 数据源工具
 * @Author pengzh
 * @Since 2023-07-18
 */

public class DataSourceUtil {
    /**
     * 创建新的数据源
     */
    public static DataSource makeNewDataSource(String ip, Integer port, String driverName, String dbName, String username, String password) {
        String url = "jdbc:mysql://" + ip + ":" + port + "/" + dbName
                + "?useSSL=false&serverTimezone=GMT%2B8&characterEncoding=UTF-8";
        String driveClassName = StringUtils.isEmpty(driverName) ? "com.mysql.cj.jdbc.Driver" : driverName;
        return DataSourceBuilder.create().url(url)
                .driverClassName(driveClassName)
                .username(username)
                .password(password)
                .build();
    }

    /**
     * 添加数据源到动态源中
     */
    public static void addDataSourceToDynamic(String key, DataSource dataSource) {
        DynamicDataSource dynamicDataSource = SpringUtils.getBean(DynamicDataSource.class);
        dynamicDataSource.addDataSource(key, dataSource);
    }

    /**
     * 根据数据库连接信息添加数据源到动态源中
     */
    public static void addDataSourceToDynamic(String key, String ip, Integer port, String driverName, String dbName, String username, String password) {
        DataSource dataSource = makeNewDataSource(ip, port, driverName, dbName, username, password);
        addDataSourceToDynamic(key, dataSource);
    }
}

