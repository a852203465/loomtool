package cn.darkjrong.excel.reader;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.enums.CellExtraTypeEnum;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.CellExtra;
import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * excel读取监听器
 *
 * @author Rong.Jia
 * @date 2025/12/09
 */
@Slf4j
public class ExcelReadListener<T> extends AnalysisEventListener<T> {

    /**
     * 数据
     */
    List<T> dataList = new ArrayList<>();

    /**
     * 正文起始行
     */
    private final Integer headRowNumber;

    /**
     * 合并单元格
     * -- GETTER --
     * 返回解析出来的合并单元格List
     */
    @Getter
    private final List<CellExtra> extraMergeInfos = new ArrayList<>();

    public ExcelReadListener(Integer headRowNumber) {
        this.headRowNumber = headRowNumber;
    }

    @Override
    public void invoke(T data, AnalysisContext context) {
        log.debug("解析到一条数据:{}", JSON.toJSONString(data));
        dataList.add(data);
    }

    /**
     * 读取额外信息：合并单元格
     */
    @Override
    public void extra(CellExtra extra, AnalysisContext context) {
        log.debug("读取到了一条额外信息:{}", JSON.toJSONString(extra));
        if (extra.getType() == CellExtraTypeEnum.MERGE) {
            if (extra.getRowIndex() >= headRowNumber) {
                extraMergeInfos.add(extra);
            }
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.debug("所有数据解析完成！");
    }

    public List<T> getData() {
        return dataList;
    }
}


