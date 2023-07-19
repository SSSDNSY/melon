package fun.sssdnsy.service;

import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import fun.sssdnsy.domain.SysHome;

import java.util.Map;

/**
 * 数据同步服务层
 *
 * @author sssdnsy
 */
public interface ISysDataSyncService {

    /**
     * 查询数据源
     */
    Map listDataSource(String name);


    /**
     * 新增数据源
     */
    Map addDataSource(DataSourceProperty property);

    /**
     * 删除数据源
     */
    void removeDataSource(String name);


}
