package com.hj.o2o.util;

import com.hj.o2o.dto.ImageHolder;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static com.hj.o2o.util.FileUtil.getRandomFileName;

/**
 * @Author: HUJING
 * @Date: 2019/3/5 21:11
 * @Version 1.0
 * @Description:
 */
public class ImageUtil {
    private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    private static Logger logger = LoggerFactory.getLogger(ImageUtil.class);

    public static String generateThumbnail(ImageHolder thumbnail, String targetAddr) {
        //获取不重复的随机名
        String realFileName = getRandomFileName();
        //获取文件的扩展名如png,jpg等
        String extension = getFileExtension(thumbnail.getImageName());
        //如果目标路径不存在，则自动创建
        makeDirPath(targetAddr);
        //获取文件存储的相对路径(带文件名)
        String relativeAddr = targetAddr + realFileName + extension;
        logger.debug("current relativeAddr is:" + relativeAddr);
        //获取文件要保存到的目标路径
        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
        logger.debug("current complete addr is:" + PathUtil.getImgBasePath() + realFileName);
        logger.debug("basePath is:" + basePath);
        //调用Thumbnails生成带有水印的图片
        try {
            System.out.println(basePath);
            //            basePath = URLDecoder.decode(basePath,"utf-8");
            Thumbnails.of(thumbnail.getImage()).size(200, 200)
                    .watermark(Positions.BOTTOM_RIGHT,
                            ImageIO.read(new File(basePath + "/watermark.jpg")), 0.25f)
                    .outputQuality(0.8f).toFile(dest);
        } catch (IOException e) {
            logger.error(e.toString());
            e.printStackTrace();
        }
        //返回图片相对路径地址
        return relativeAddr;
    }

    public static String generateNormalImg(ImageHolder thumbnail, String targetAddr) {
        //获取不重复的随机名
        String realFileName = getRandomFileName();
        //获取文件的扩展名如png,jpg等
        String extension = getFileExtension(thumbnail.getImageName());
        //如果目标路径不存在，则自动创建
        makeDirPath(targetAddr);
        //获取文件存储的相对路径(带文件名)
        String relativeAddr = targetAddr + realFileName + extension;
        logger.debug("current relativeAddr is:" + relativeAddr);
        //获取文件要保存到的目标路径
        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
        logger.debug("current complete addr is:" + PathUtil.getImgBasePath() + realFileName);
        logger.debug("basePath is:" + basePath);
        //调用Thumbnails生成带有水印的图片
        try {
            System.out.println(basePath);
            //            basePath = URLDecoder.decode(basePath,"utf-8");
            Thumbnails.of(thumbnail.getImage()).size(337, 640)
                    .watermark(Positions.BOTTOM_RIGHT,
                            ImageIO.read(new File(basePath + "/watermark.jpg")), 0.25f)
                    .outputQuality(0.9f).toFile(dest);
        } catch (IOException e) {
            logger.error(e.toString());
            e.printStackTrace();
        }
        //返回图片相对路径地址
        return relativeAddr;
    }

    /**
     * 创建目标路径所涉及的目录,即/home/work/hujing/xxx.jpg,
     * 那么 home work hujing 这三个文件夹都得自动创建
     *
     * @param targetAddr
     */
    private static void makeDirPath(String targetAddr) {
        String realFileParentPath = PathUtil.getImgBasePath() + targetAddr;
        File dirPath = new File(realFileParentPath);
        if (!dirPath.exists()) {
            dirPath.mkdirs();
        }
    }

    /**
     * 获取输入文件流的扩展名
     *
     * @param fileName
     * @return
     */
    private static String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * storePath是文件的路径还是目录的路径，
     * 如果storePath是文件路径则删除该文件，
     * 如果storePath是目录路径则删除该目录下的所有文件
     *
     * @param storePath
     */
    public static void deleteFileOrPath(String storePath) {
        File fileOrPath = new File(PathUtil.getImgBasePath() + storePath);
        if (fileOrPath.exists()) {
            if (fileOrPath.isDirectory()) {
                File files[] = fileOrPath.listFiles();
                for (int i = 0; i < files.length; i++) {
                    files[i].delete();
                }
            }
            fileOrPath.delete();
        }
    }

    public static void main(String[] args) throws IOException {
        Thumbnails.of(new File("D:\\学习文档\\类\\hj.jpg"))
                .size(200, 200).watermark(Positions.BOTTOM_RIGHT,
                ImageIO.read(new File(basePath + "/water.jpg")), 0.25f).outputQuality(0.8f)
                .toFile("D:\\学习文档\\类\\hjnew.jpg");

    }


}
