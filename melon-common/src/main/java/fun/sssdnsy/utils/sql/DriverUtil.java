package fun.sssdnsy.utils.sql;

import org.apache.commons.compress.utils.Lists;

import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Desc 数据库驱动工具
 * @Author pengzh
 * @Since 2023-07-18
 */
public final class DriverUtil {

    public static List getAllDrivers() {
        Enumeration<Driver> driverEnumeration = DriverManager.getDrivers();
        List                drivers           = Lists.newArrayList();
        while (driverEnumeration.hasMoreElements()) {
            Driver              driver = driverEnumeration.nextElement();
            Map<String, Object> map    = new HashMap<>();
            map.put("driverName", driver.getClass());
            map.put("majorVersion", driver.getMajorVersion());
            map.put("minorVersion", driver.getMinorVersion());
            drivers.add(map);
        }
        return drivers;
    }

}
