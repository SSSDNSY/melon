package fun.sssdnsy.service.impl;

import fun.sssdnsy.service.ISysDataSyncService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

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
    public Map addDataSource() {
//        DynamicRoutingDataSource dataSource = new DynamicRoutingDataSource();
//
//        dataSourceProperty.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        dataSourceProperty.setUrl("jdbc:mysql://localhost:3306/melon?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8&nullCatalogMeansCurrent=true");
//        dataSourceProperty.setPoolName("sssdnsy-dataSource-pool");
//        dataSourceProperty.setUsername("root");
//        dataSourceProperty.setPassword("imi123");
//        dataSource = (DynamicRoutingDataSource) dataSourceCreator.createDataSource(dataSourceProperty);
//        dataSource.addDataSource("sssdnsy-dataSource", dataSource);
        return null;
    }

    @Override
    public void removeDataSource(String name) {
        if (Objects.nonNull(getDynamicRoutingDataSource().get(name))) {
            getDynamicRoutingDataSource().remove(name);
        }
    }

    public Map<Object, DataSource> getDynamicRoutingDataSource() {
        return null;
    }


}
