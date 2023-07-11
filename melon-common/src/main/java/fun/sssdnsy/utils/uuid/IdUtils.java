package fun.sssdnsy.utils.uuid;

import java.net.UnknownHostException;

/**
 * ID生成器工具类
 *
 * @author sssdnsy
 */
public class IdUtils {


    private static IdGenerator idGenerator;

    static {
        initIdGenerator();
    }

    /**
     * 获取随机UUID
     *
     * @return 随机UUID
     */
    @Deprecated
    public static String randomUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 简化的UUID，去掉了横线
     *
     * @return 简化的UUID，去掉了横线
     */
    @Deprecated
    public static String simpleUUID() {
        return UUID.randomUUID().toString(true);
    }

    /**
     * 获取随机UUID，使用性能更好的ThreadLocalRandom生成UUID
     *
     * @return 随机UUID
     */
    @Deprecated
    public static String fastUUID() {
        return UUID.fastUUID().toString();
    }

    /**
     * 简化的UUID，去掉了横线，使用性能更好的ThreadLocalRandom生成UUID
     *
     * @return 简化的UUID，去掉了横线
     */
    @Deprecated
    public static String fastSimpleUUID() {
        return UUID.fastUUID().toString(true);
    }


    /**
     * 初始化ID生成器
     */
    private static void initIdGenerator() {
        try {
            idGenerator = new IdGenerator();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取long型的ID.
     *
     * @return Long
     * @throws
     * @since 1.0.0
     */
    public static Long getUId() {
        return idGenerator.nextId();
    }

    /**
     * 获取字符型的ID
     *
     * @return
     */
    public static String getSuid() {
        return getUId().toString();
    }

}
