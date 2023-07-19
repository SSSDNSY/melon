package fun.sssdnsy.service.impl;

import com.baomidou.dynamic.datasource.creator.DataSourceCreator;
import com.baomidou.dynamic.datasource.provider.DynamicDataSourceProvider;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import fun.sssdnsy.config.DynamicDataSource;
import fun.sssdnsy.service.ISysDataSyncService;
import fun.sssdnsy.utils.spring.SpringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static fun.sssdnsy.utils.sql.DataSourceUtil.addDataSourceToDynamic;

/**
 * @Desc 数据同步 服务层实现
 * @Author pengzh
 * @Since 2023-07-18
 */
@Service
public class SysDataSyncServiceImpl implements ISysDataSyncService {


    @Override
    public Map listDataSource(String name) {

        if (StringUtils.isNotBlank(name)) {
            HashMap map = new HashMap(1);
            map.put(name, getDynamicRoutingDataSource().get(name));
            return map;
        } else {
            return getDynamicRoutingDataSource();
        }
    }

    @Override
    public Map addDataSource(DataSourceProperty dataSourceProperty) {
//        DynamicRoutingDataSource dataSource = new DynamicRoutingDataSource();
//
//        dataSourceProperty.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        dataSourceProperty.setUrl("jdbc:mysql://localhost:3306/melon?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8&nullCatalogMeansCurrent=true");
//        dataSourceProperty.setPoolName("sssdnsy-dataSource-pool");
//        dataSourceProperty.setUsername("root");
//        dataSourceProperty.setPassword("imi123");
//        dataSource = (DynamicRoutingDataSource) dataSourceCreator.createDataSource(dataSourceProperty);
//        dataSource.addDataSource("sssdnsy-dataSource", dataSource);
        addDataSourceToDynamic("sssdnsy-dataSource", "localhost", 3306, null, "melon", "root", "imi123");
        return null;
    }

    @Override
    public void removeDataSource(String name) {
        if (Objects.nonNull(getDynamicRoutingDataSource().get(name))) {
            getDynamicRoutingDataSource().remove(name);
        }
    }

    public Map<Object, DataSource> getDynamicRoutingDataSource() {
        return SpringUtils.getBean(DynamicDataSource.class).getResolvedDataSources();
    }


}
