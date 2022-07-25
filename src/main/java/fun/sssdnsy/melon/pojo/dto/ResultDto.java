package fun.sssdnsy.melon.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author fun.pengzh
 * @class fun.sssdnsy.melon.pojo.dto.ResultDTO
 * @desc
 * @since 2022-07-25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultDto<T> implements Serializable {


    /**
     * response code, 200 -> OK.
     */
    private Integer status;

    /**
     * response message.
     */
    private String message;

    /**
     * response data.
     */
    private T data;

    /**
     * response timestamp.
     */
    private long timestamp;

}
