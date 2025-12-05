package cn.darkjrong.core.utils;

import cn.hutool.core.img.ImgUtil;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Slf4j
public class ThumbImageUtils {

    //以下是常量,按照阿里代码开发规范,不允许代码中出现魔法值
    private static final Integer ZERO = 0;
    private static final Integer ONE_ZERO_TWO_FOUR = 1024;
    private static final Integer NINE_ZERO_ZERO = 900;
    private static final Integer THREE_TWO_SEVEN_FIVE = 3275;
    private static final Integer TWO_ZERO_FOUR_SEVEN = 2047;
    private static final Double ZERO_EIGHT_FIVE = 0.85;
    private static final Double ZERO_SIX = 0.6;
    private static final Double ZERO_FOUR_FOUR = 0.44;
    private static final Double ZERO_FOUR = 0.4;

    /**
     * 根据指定大小压缩图片
     *
     * @param imageBytes  源图片字节数组
     * @param desFileSize 指定图片大小，单位kb
     * @return 压缩质量后的图片字节数组
     */
    public static byte[] compress(byte[] imageBytes, long desFileSize) {
        if (imageBytes == null
                || imageBytes.length <= ZERO
                || imageBytes.length < desFileSize * ONE_ZERO_TWO_FOUR) {
            return imageBytes;
        }
        long srcSize = imageBytes.length;
        double accuracy = getAccuracy(srcSize / ONE_ZERO_TWO_FOUR);
        try {
            while (imageBytes.length > desFileSize * ONE_ZERO_TWO_FOUR) {
                ByteArrayInputStream inputStream = new ByteArrayInputStream(imageBytes);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream(imageBytes.length);
                Thumbnails.of(inputStream)
                        .scale(accuracy)
                        .outputQuality(accuracy)
                        .toOutputStream(outputStream);
                imageBytes = outputStream.toByteArray();
            }
            log.debug("图片原大小={}kb | 压缩后大小={}kb",
                    srcSize / ONE_ZERO_TWO_FOUR, imageBytes.length / ONE_ZERO_TWO_FOUR);
        } catch (Exception e) {
            log.error(String.format("图片压缩异常,【%s】", e.getMessage()), e);
            throw new RuntimeException(e);
        }
        return imageBytes;
    }

    public static byte[] compress(byte[] imageBytes) {
        if (imageBytes == null || imageBytes.length <= ZERO) {
            return imageBytes;
        }
        try {
            BufferedImage bufImg = ImgUtil.toImage(imageBytes);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            //防止图片变红
            BufferedImage bufferedImage = new BufferedImage(bufImg.getWidth(),
                    bufImg.getHeight(), BufferedImage.TYPE_INT_RGB);
            bufferedImage.createGraphics().drawImage(bufImg,0,0, Color.WHITE,null);
            ImageIO.write(bufferedImage,"jpg",bos);
            byte[] targetByte = bos.toByteArray();
            log.debug("图片原大小={}kb | 压缩后大小={}kb",
                    imageBytes.length / ONE_ZERO_TWO_FOUR, targetByte.length / ONE_ZERO_TWO_FOUR);
            return targetByte;
        } catch (IOException e) {
            log.error("【图片压缩】msg=图片压缩失败!", e);
        }
        return imageBytes;
    }

    /**
     * 自动调节精度(经验数值)
     *
     * @param size 源图片大小
     * @return {@link Double } 图片压缩质量比
     */
    private static Double getAccuracy(Long size) {
        Double accuracy;
        if (size < NINE_ZERO_ZERO) {
            accuracy = ZERO_EIGHT_FIVE;
        } else if (size < TWO_ZERO_FOUR_SEVEN) {
            accuracy = ZERO_SIX;
        } else if (size < THREE_TWO_SEVEN_FIVE) {
            accuracy = ZERO_FOUR_FOUR;
        } else {
            accuracy = ZERO_FOUR;
        }
        return accuracy;
    }


}
