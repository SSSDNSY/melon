package fun.sssdnsy.service.impl;

import fun.sssdnsy.service.ISysDataSyncService;
import fun.sssdnsy.service.ISysMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.DataSource;
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
    ISysMenuService sysMenuService;

    @Override
    public Map listDataSource(String name) {
        return getDynamicRoutingDataSource();
    }

    @Override
    public Map addDataSource() {
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
