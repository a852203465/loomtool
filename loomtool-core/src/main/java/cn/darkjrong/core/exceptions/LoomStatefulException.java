package cn.darkjrong.core.exceptions;

import cn.hutool.core.util.StrUtil;
import lombok.Getter;

import java.io.Serializable;

/**
 * 工具类全局有状态异常
 *
 * @author Rong.Jia
 * @date 2023/06/27
 */
@Getter
public class LoomStatefulException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 5125726279105828472L;

    /**
     * 异常状态码
     */
    private Integer status;

    public LoomStatefulException() {
    }

    public LoomStatefulException(String msg) {
        super(msg);
    }

    public LoomStatefulException(String messageTemplate, Object... params) {
        super(StrUtil.format(messageTemplate, params));
    }

    public LoomStatefulException(Throwable throwable) {
        super(throwable);
    }

    public LoomStatefulException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

    public LoomStatefulException(String message, Throwable throwable, boolean enableSuppression, boolean writableStackTrace) {
        super(message, throwable, enableSuppression, writableStackTrace);
    }

    public LoomStatefulException(int status, String msg) {
        super(msg);
        this.status = status;
    }

    public LoomStatefulException(int status, Throwable throwable) {
        super(throwable);
        this.status = status;
    }

    public LoomStatefulException(int status, String msg, Throwable throwable) {
        super(msg, throwable);
        this.status = status;
    }

}
