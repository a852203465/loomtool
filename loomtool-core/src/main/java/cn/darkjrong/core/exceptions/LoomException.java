package cn.darkjrong.core.exceptions;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.StrUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 工具类全局异常
 *
 * @author Rong.Jia
 * @date 2023/06/27
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LoomException extends Exception implements Serializable {

    private static final long serialVersionUID = -1501020198729282518L;

    public LoomException(Throwable e) {
        super(ExceptionUtil.getMessage(e), e);
    }

    public LoomException(String message) {
        super(message);
    }

    public LoomException(String messageTemplate, Object... params) {
        super(StrUtil.format(messageTemplate, params));
    }

    public LoomException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public LoomException(String message, Throwable throwable, boolean enableSuppression, boolean writableStackTrace) {
        super(message, throwable, enableSuppression, writableStackTrace);
    }

    public LoomException(Throwable throwable, String messageTemplate, Object... params) {
        super(StrUtil.format(messageTemplate, params), throwable);
    }


}
