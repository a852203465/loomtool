package cn.darkjrong.excel;

import cn.darkjrong.core.lang.constants.FileConstant;
import cn.darkjrong.excel.handler.CellWriteWidthStrategyHandler;
import cn.darkjrong.excel.handler.MergeCellStrategyHandler;
import cn.darkjrong.excel.reader.ExcelReadListener;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.enums.CellExtraTypeEnum;
import com.alibaba.excel.metadata.CellExtra;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * excel工具类
 *
 * @author Rong.Jia
 * @date 2022/08/09
 */
@Slf4j
public class ExcelUtils {

    /**
     * 写文件
     *
     * @param file        文件
     * @param exportClass 导出类
     * @param datas       数据
     * @param sheetNo     sheet号
     * @param sheetName   sheet名字
     */
    public static void write(File file, Class<?> exportClass, List<?> datas, Integer sheetNo, String sheetName) {
        if (CollectionUtil.isEmpty(datas)) {
            log.error("文件【{}】表格数据为空,跳过导出", file.getName());
            return;
        }
        try (ExcelWriter excelWriter = EasyExcel.write(file)
                .registerWriteHandler(new CellWriteWidthStrategyHandler())
                .autoCloseStream(true)
                .build()) {
            WriteSheet writeSheet = EasyExcel.writerSheet(sheetNo, sheetName).head(exportClass).build();
            excelWriter.write(datas, writeSheet);
            excelWriter.finish();
        }
    }

    /**
     * 写文件
     *
     * @param exportClass 导出类
     * @param datas       数据
     * @param sheetNo     sheet号
     * @param sheetName   sheet名字
     * @return {@link File}
     */
    public static File write(Class<?> exportClass, List<?> datas, Integer sheetNo, String sheetName) {
        if (CollectionUtil.isEmpty(datas)) return null;
        File file = new File(FileConstant.TMP_DIR + FileConstant.SEPARATOR
                + DateUtil.format(DateUtil.date(), DatePattern.PURE_DATETIME_MS_FORMAT) + FileConstant.EXCEL_SUFFIX);
        write(file, exportClass, datas, sheetNo, sheetName);
        return file;
    }

    /**
     * 写文件
     *
     * @param exportClass 导出类
     * @param datas       数据
     * @param sheetNo     sheet号
     * @param sheetName   sheet名字
     * @return {@link File}
     */
    public static File write(Class<?> exportClass, List<?> datas, Integer sheetNo, String sheetName, String fileName) {
        if (CollectionUtil.isEmpty(datas)) return null;
        File file = new File(FileConstant.TMP_DIR + FileConstant.SEPARATOR + fileName + FileConstant.EXCEL_SUFFIX);
        write(file, exportClass, datas, sheetNo, sheetName);
        return file;
    }

    /**
     * 写文件
     *
     * @param exportClass 导出类
     * @param datas       数据
     * @return {@link File}
     */
    public static File write(Class<?> exportClass, List<?> datas) {
        if (CollectionUtil.isEmpty(datas)) return null;
        return write(exportClass, datas, null, null);
    }

    /**
     * 动态导出
     * @param heads 头
     * @param exports 数据
     * @return 导出文件
     */
    public static File write(List<List<String>> heads, Collection<?> exports) {
        return write(heads, null, null, exports);
    }

    /**
     * 动态导出
     *
     * @param heads     头
     * @param exports   数据
     * @param file      文件
     * @param sheetNo   表序号
     * @param sheetName 表名字
     * @return 导出文件
     */
    public static File write(File file, List<List<String>> heads,
                             Integer sheetNo, String sheetName, Collection<?> exports) {

        ExcelWriterSheetBuilder builder = EasyExcel.write(file)
                .registerWriteHandler(new CellWriteWidthStrategyHandler())
                .head(heads).sheet();

        if (ObjectUtil.isNotEmpty(sheetNo)) {
            builder.sheetNo(sheetNo);
        }
        if (ObjectUtil.isNotEmpty(sheetName)) {
            builder.sheetName(sheetName);
        }

        builder.doWrite(exports);
        return file;
    }

    /**
     * 动态导出
     *
     * @param head     头
     * @param exports   数据
     * @param file      文件
     * @param sheetNo   表序号
     * @param sheetName 表名字
     * @return 导出文件
     */
    public static File write(File file, Class<?> head, Integer sheetNo, String sheetName, Collection<?> exports) {
        ExcelWriterSheetBuilder builder = EasyExcel.write(file)
                .registerWriteHandler(new CellWriteWidthStrategyHandler())
                .head(head).sheet();
        if (ObjectUtil.isNotEmpty(sheetNo)) {
            builder.sheetNo(sheetNo);
        }
        if (ObjectUtil.isNotEmpty(sheetName)) {
            builder.sheetName(sheetName);
        }

        builder.doWrite(exports);
        return file;
    }

    /**
     * 动态导出
     *
     * @param heads     头
     * @param exports   数据
     * @param file      文件
     * @param sheetNo   表序号
     * @param sheetName 表名字
     * @return 导出文件
     */
    public static File write(File file, List<List<String>> heads,
                             Integer sheetNo, String sheetName, Integer mergeStartRow,
                             Set<Integer> mergeColumn, Collection<?> exports) {

        ExcelWriterSheetBuilder builder = EasyExcel.write(file)
                // 开启相等值 列合并,行合并,从行索引1(excel中第二行)开始合并, 指定跨行合并的列索引
                .registerWriteHandler(new MergeCellStrategyHandler(true, true, mergeStartRow, mergeColumn))
                .registerWriteHandler(new CellWriteWidthStrategyHandler())
        // 开启相等值 列合并,从行索引1(excel中第二行)开始合并
        //.registerWriteHandler(new MergeCellStrategyHandler(true, 1))

        // 开启相等值 行合并,从行索引1(excel中第二行)开始合并, 指定跨行合并的列索引
//                    .registerWriteHandler(new MergeCellStrategyHandler(true, 1, Set.of(0, 2, 4, 5)))
                .head(heads).sheet();

        if (ObjectUtil.isNotEmpty(sheetNo)) {
            builder.sheetNo(sheetNo);
        }
        if (ObjectUtil.isNotEmpty(sheetName)) {
            builder.sheetName(sheetName);
        }

        builder.doWrite(exports);
        return file;
    }

    /**
     * 写
     * 动态导出
     *
     * @param heads     头
     * @param exports   数据
     * @param sheetNo   表序号
     * @param sheetName 表名字
     * @return 导出文件
     */
    public static File write(List<List<String>> heads, Integer sheetNo, String sheetName, Collection<?> exports) {
        File file = new File(FileConstant.TMP_DIR + FileConstant.SEPARATOR
                + DateUtil.format(DateUtil.date(), DatePattern.PURE_DATETIME_MS_FORMAT) + FileConstant.EXCEL_SUFFIX);
        return write(file, heads, sheetNo, sheetName, exports);
    }

    /**
     * 读取EXCEL
     *
     * @param bytes         EXCEL字节数组
     * @param sheetNo       sheet
     * @param headRowNumber 表头行号
     * @param tClass        解析结果对象Class对象
     * @return {@link List}<{@link T}> 解析结果
     */
    public static <T> List<T> readExcel(byte[] bytes, Integer sheetNo, Integer headRowNumber, Class<T> tClass) {
        ExcelReadListener<T> listener = new ExcelReadListener<>(headRowNumber);
        EasyExcelFactory.read(new ByteArrayInputStream(bytes), listener)
                .head(tClass)
                .extraRead(CellExtraTypeEnum.MERGE)
                .extraRead(CellExtraTypeEnum.COMMENT)
                .extraRead(CellExtraTypeEnum.HYPERLINK)
                .autoCloseStream(Boolean.TRUE)
                .sheet(sheetNo)
                .headRowNumber(headRowNumber)
                .doRead();

        List<T> excelDataList = listener.getData();
        List<CellExtra> cellExtraList = listener.getExtraMergeInfos();
        if (CollUtil.isNotEmpty(cellExtraList)) {
            mergeExcelData(excelDataList, cellExtraList, headRowNumber);
        }
        return excelDataList;
    }

    /**
     * 读取EXCEL
     *
     * @param bytes         EXCEL字节数组
     * @param sheetName       sheet
     * @param headRowNumber 表头行号
     * @param tClass        解析结果对象Class对象
     * @return {@link List}<{@link T}> 解析结果
     */
    public static <T> List<T> readExcel(byte[] bytes, String sheetName, Integer headRowNumber, Class<T> tClass) {
        ExcelReadListener<T> listener = new ExcelReadListener<>(headRowNumber);
        EasyExcelFactory.read(new ByteArrayInputStream(bytes), listener)
                .head(tClass)
                .extraRead(CellExtraTypeEnum.MERGE)
                .extraRead(CellExtraTypeEnum.COMMENT)
                .extraRead(CellExtraTypeEnum.HYPERLINK)
                .autoCloseStream(Boolean.TRUE)
                .sheet(sheetName)
                .headRowNumber(headRowNumber)
                .doRead();

        List<T> excelDataList = listener.getData();
        List<CellExtra> cellExtraList = listener.getExtraMergeInfos();
        if (CollUtil.isNotEmpty(cellExtraList)) {
            mergeExcelData(excelDataList, cellExtraList, headRowNumber);
        }
        return excelDataList;
    }

    /**
     * 读取EXCEL
     *
     * @param bytes         EXCEL字节数组
     * @param headRowNumber 表头行号
     * @param tClass        解析结果对象Class对象
     * @return {@link List}<{@link T}> 解析结果
     */
    public static <T> List<T> readExcel(byte[] bytes, Integer headRowNumber, Class<T> tClass) {
        return readExcel(bytes, 0, headRowNumber, tClass);
    }

    private static <T> void mergeExcelData(List<T> excelDataList, List<CellExtra> cellExtraList, int headRowNum) {
        for (CellExtra cellExtra : cellExtraList) {
            int firstRowIndex = cellExtra.getFirstRowIndex() - headRowNum;
            int lastRowIndex = cellExtra.getLastRowIndex() - headRowNum;
            int firstColumnIndex = cellExtra.getFirstColumnIndex();
            int lastColumnIndex = cellExtra.getLastColumnIndex();
            Object initValue = getInitValueFromList(firstRowIndex, firstColumnIndex, excelDataList);
            if (ObjectUtil.isNotEmpty(initValue)) {
                for (int i = firstRowIndex; i <= lastRowIndex; i++) {
                    for (int j = firstColumnIndex; j <= lastColumnIndex; j++) {
                        setInitValueToList(initValue, i, j, excelDataList);
                    }
                }
            }
        }
    }
    private static <T> void setInitValueToList(Object filedValue, Integer rowIndex, Integer columnIndex, List<T> data) {
        if (rowIndex <= data.size()) {
            Object object = data.get(rowIndex);
            for (Field field : object.getClass().getDeclaredFields()) {
                ExcelProperty annotation = field.getAnnotation(ExcelProperty.class);
                if (ObjectUtil.isNotNull(annotation) && annotation.index() == columnIndex) {
                    try {
                        field.setAccessible(true);
                        field.set(object, filedValue);
                        field.setAccessible(false);
                        break;
                    } catch (IllegalAccessException e) {
                        log.error(String.format("设置合并单元格的值异常：【%s】", e.getMessage()), e);
                    }
                }
            }
        }
    }
    private static <T> Object getInitValueFromList(Integer firstRowIndex, Integer firstColumnIndex, List<T> data) {
        Object filedValue = null;
        if (firstRowIndex <= data.size()) {
            Object object = data.get(firstRowIndex);
            for (Field field : object.getClass().getDeclaredFields()) {
                ExcelProperty annotation = field.getAnnotation(ExcelProperty.class);
                if (ObjectUtil.isNotNull(annotation) && annotation.index() == firstColumnIndex) {
                    try {
                        field.setAccessible(true);
                        filedValue = field.get(object);
                        field.setAccessible(false);
                        break;
                    } catch (IllegalAccessException e) {
                        log.error(String.format("获取合并单元格的初始值异常：【%s】", e.getMessage()), e);
                    }
                }
            }
        }
        return filedValue;
    }





}
