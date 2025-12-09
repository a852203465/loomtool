package cn.darkjrong.excel.handler;

import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;

/**
 * 自定义模板导出sheet拦截器
 *
 * @author Rong.Jia
 * @date 2025/09/08
 */
public class CustomTemplateSheetHandler implements SheetWriteHandler {

    private Integer sheetNo;
    private String sheetName;

    public CustomTemplateSheetHandler(String sheetName) {
        this.sheetNo = 0;
        this.sheetName = sheetName;
    }

    public CustomTemplateSheetHandler(Integer sheetNo, String sheetName) {
        this.sheetNo = sheetNo;
        this.sheetName = sheetName;
    }

    /**
     * 功能：动态修改模板中sheet的名称
     * sheet创建完成后调用
     */
    @Override
    public void afterSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {
        if (sheetName == null) {
            return;
        }
        if (sheetNo == null) {
            sheetNo = 0;
        }
        writeWorkbookHolder.getCachedWorkbook().setSheetName(sheetNo, sheetName);
    }
}
