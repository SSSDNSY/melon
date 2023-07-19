package fun.sssdnsy.service.impl;

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.dynamic.datasource.creator.DataSourceCreator;
import com.baomidou.dynamic.datasource.creator.DruidDataSourceCreator;
import com.baomidou.dynamic.datasource.provider.DynamicDataSourceProvider;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import fun.sssdnsy.service.ISysDataSyncService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Desc 数据同步 服务层实现
 * @Author pengzh
 * @Since 2023-07-18
 */
@Service
public class SysDataSyncServiceImpl implements ISysDataSyncService {

    @Resource
    private DataSource                dataSource;

    @Resource
    private DynamicDataSourceProvider ymlDynamicDataSourceProvider;

    @Override
    public Map listDataSource(String name) {

        if (StringUtils.isNotBlank(name)) {
            HashMap map = new HashMap(1);
            map.put(name, getDynamicRoutingDataSource().getDataSources().get(name));
            return map;
        } else {
            return getDynamicRoutingDataSource().getDataSources();
        }
    }

    @Override
    public Map addDataSource(DataSourceProperty dataSourceProperty) {
        DynamicRoutingDataSource dataSource = new DynamicRoutingDataSource();

        dataSourceProperty.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSourceProperty.setUrl("jdbc:mysql://localhost:3306/melon?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8&nullCatalogMeansCurrent=true");
        dataSourceProperty.setPoolName("sssdnsy-dataSource-pool");
        dataSourceProperty.setUsername("root");
        dataSourceProperty.setPassword("AQa(l23-.-32L)");
        DataSourceCreator dataSourceCreator = new DruidDataSourceCreator();
        dataSource = (DynamicRoutingDataSource) dataSourceCreator.createDataSource(dataSourceProperty);
        dataSource.addDataSource("sssdnsy-dataSource", dataSource);
        Map<String, DataSource> dataSources = dataSource.getDataSources();
        return dataSources;
    }

    @Override
    public void removeDataSource(String name) {
        Map<String, DataSource> dataSources = getDynamicRoutingDataSource().getDataSources();
        if (Objects.nonNull(dataSources.get(name))) {
            dataSources.remove(name);
        }
    }

    public DynamicRoutingDataSource getDynamicRoutingDataSource() {
        return (DynamicRoutingDataSource) dataSource;
    }


}
