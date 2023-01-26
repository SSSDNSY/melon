package fun.sssdnsy.mapper;

import fun.sssdnsy.domain.XxlConfNodeMsg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by xuxueli on 16/10/8.
 */
@Mapper
public interface XxlConfNodeMsgDao {

    void add(XxlConfNodeMsg xxlConfNode);

    List<XxlConfNodeMsg> findMsg(@Param("readedMsgIds") List<Integer> readedMsgIds);

    int cleanMessage(@Param("messageTimeout") int messageTimeout);

}
