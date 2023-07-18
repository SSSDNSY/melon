package fun.sssdnsy.utils.sql;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;

/**
 * @Desc  数据源工具
 * @Author pengzh
 * @Since 2023-07-18
 */
public class DataSourceUtil {

    public void addDataSource(){
        DruidDataSourceBuilder druidDataSourceBuilder = DruidDataSourceBuilder.create();
        druidDataSourceBuilder.build();
    }

}
