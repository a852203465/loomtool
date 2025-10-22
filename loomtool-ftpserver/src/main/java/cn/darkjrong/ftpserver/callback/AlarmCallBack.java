package cn.darkjrong.ftpserver.callback;

import java.io.File;

/**
 * 报警回调函数
 *
 * @author Rong.Jia
 * @date 2025/10/22
 */
public interface AlarmCallBack {

    /**
     * 回调函数
     * @param file 文件
     * @param hostAddress 地址
     */
    void invoke(File file, String hostAddress);






}
