package cn.darkjrong.core.exceptions;

import cn.darkjrong.core.enums.ExceptionEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 项目自定义异常
 *
 * @author Rong.Jia
 * @date 2023/06/27
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LoomWebException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -1501020198729282518L;

    /**
     * 异常code 码
     */
    private Integer code;

    /**
     * 异常详细信息
     */
    private String message;

    public LoomWebException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public LoomWebException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMessage());
        this.code = exceptionEnum.getCode();
        this.message = exceptionEnum.getMessage();
    }

    public LoomWebException(ExceptionEnum exceptionEnum, String message) {
        super(message);
        this.code = exceptionEnum.getCode();
        this.message = message;
    }

    public LoomWebException(Integer code, String message, Throwable t) {
        super(message, t);
        this.code = code;
        this.message = message;
    }

    public LoomWebException(ExceptionEnum exceptionEnum, Throwable t) {
        super(exceptionEnum.getMessage(), t);
        this.code = exceptionEnum.getCode();
        this.message = exceptionEnum.getMessage();
    }


}
