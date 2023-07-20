package fun.sssdnsy.service;

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
    Map addDataSource();

    /**
     * 删除数据源
     */
    void removeDataSource(String name);


}
