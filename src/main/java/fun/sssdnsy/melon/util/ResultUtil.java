package fun.sssdnsy.melon.util;

import fun.sssdnsy.melon.pojo.dto.ResultDto;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * @author fun.pengzh
 * @class fun.sssdnsy.melon.util.ResultUtil
 * @desc 前端统一响应工具类
 * @since 2022-07-25
 */
public final class ResultUtil<T> {

    /**
     * response success result wrapper.
     * 返回成功
     *
     * @param <T> type of data class
     * @return response result
     */
    public static <T> ResultDto<T> success() {
        return success(null);
    }

    /**
     * response success result wrapper.
     * 返回成功带数据
     *
     * @param data response data
     * @param <T>  type of data class
     * @return response result
     */
    public static <T> ResultDto<T> success(T data) {
        return ResultDto.<T>builder().data(data)
                .message(HttpStatus.OK.getReasonPhrase())
                .status(HttpStatus.OK.value())
                .timestamp(System.currentTimeMillis())
                .build();
    }

    /**
     * response error result wrapper.
     * 返回失败
     *
     * @param message error message
     * @param <T>     type of data class
     * @return response result
     */
    public static <T extends Serializable> ResultDto<T> fail(String message) {
        return fail(null, message);
    }

    /**
     * response error result wrapper.
     * 返回失败带数据
     *
     * @param data    response data
     * @param message error message
     * @param <T>     type of data class
     * @return response result
     */
    public static <T> ResultDto<T> fail(T data, String message) {
        return ResultDto.<T>builder().data(data)
                .message(message)
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .timestamp(System.currentTimeMillis())
                .build();
    }

    /**
     * 返回带数据、响应码、消息
     *
     * @param httpStatus httpStatus
     * @param message    message
     * @param data       response data
     * @param <T>        type of data class
     * @return
     */
    public static <T> ResultDto<T> fail(HttpStatus httpStatus, String message, T data) {
        return ResultDto.<T>builder().data(data)
                .message(message)
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .timestamp(System.currentTimeMillis())
                .build();
    }
}
