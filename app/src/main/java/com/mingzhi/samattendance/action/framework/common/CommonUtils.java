package com.mingzhi.samattendance.action.framework.common;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.DateTypeAdapter;
import com.mingzhi.samattendance.action.framework.XxbConstants;
import com.mingzhi.samattendance.action.mvp.bean.ScanInfoModel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import a_vcard.android.syncml.pim.PropertyNode;
import a_vcard.android.syncml.pim.VDataBuilder;
import a_vcard.android.syncml.pim.VNode;
import a_vcard.android.syncml.pim.vcard.VCardException;
import a_vcard.android.syncml.pim.vcard.VCardParser;


/**
 * Created by Chen on 2015/8/21.
 */
public class CommonUtils {
    public static Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
            .registerTypeAdapter(Date.class, new DateTypeAdapter())
            .create();


    public static String getFileByte(String filePath) {
        File file = new File(filePath);
        long len = file.length();
        if (file.exists()) {
            FileInputStream fis = null;
            StringBuffer sb = new StringBuffer();
            try {
                fis = new FileInputStream(file);
                byte[] buffer = new byte[(int) len];
                while ((fis.read(buffer)) != -1) {
                    sb.append(Base64.encodeToString(buffer, Base64.DEFAULT));
                }
                return sb.toString();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    fis.close();
                } catch (IOException e) {

                    e.printStackTrace();
                }
            }
        }
        return "";
    }

    /**
     * Card parser
     * @param vcf
     * @return
     * @throws IOException
     * @throws VCardException
     */
    public static  ScanInfoModel parser(String vcf) throws IOException, VCardException {

        ScanInfoModel model=new ScanInfoModel();
        VCardParser parser = new VCardParser();
        VDataBuilder builder = new VDataBuilder();
        // parse the string
        boolean parsed = parser.parse(vcf, "UTF-8", builder);
        // get all parsed contacts
        List<VNode> pimContacts = builder.vNodeList;
        // do something for all the contacts
        for (VNode contact : pimContacts) {
            ArrayList<PropertyNode> props = contact.propList;
            // contact name - FN property
            for (PropertyNode prop : props) {
                if ("FN".equals(prop.propName)) {
                    model.setName(prop.propValue);
                } else if ("LABEL".equals(prop.propName)) {
                    model.setAddress(prop.propValue);
                } else if ("TEL".equals(prop.propName)) {

                    if(!TextUtils.isEmpty(prop.propValue)&&prop.propValue.startsWith("1")){
                        model.setPhone(prop.propValue);
                    }else{
                        model.setTel(prop.propValue);
                    }

                } else if ("EMAIL".equals(prop.propName)) {
                    model.setEmail(prop.propValue);
                } else if ("ORG".equals(prop.propName)) {
                    model.setCompany(prop.propValue);
                } else if ("TITLE".equals(prop.propName)) {
                    model.setPosition(prop.propValue);
                }
            }
        }
        return model;
    }

    /**
     *
     * @param activity
     * @return
     */
    public static String getCameraCapturePath(Activity activity) {
        String path = getImageSavePath(activity) + "/" + getTime("yyyyMMddHHmmss") + ".jpg";
        File file = new File(path);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        activity.startActivityForResult(intent, XxbConstants.CAMERA_REQUEST_CODE);
        return path;
    }

    public static String getTime(String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(new Date());
    }

    /**
     * 方法名:获取图片文件存取路径
     * <p>
     * 功能说明：获取图片文件存取路径
     * </p>
     *
     * @return
     */
    public static String getImageSavePath(Activity activity) {
        if (TextUtils.isEmpty(getSDPath())) {
            return activity.getFilesDir().getPath();
        }
        File file = new File(getSDPath() + "/XXB/profession/dcim");
        if (!file.exists()) {
            if (file.mkdirs()) {
                return file.getPath();
            }
            return "";
        }
        return file.getPath();
    }

    public static String getSDPath() {
        boolean sdCardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
        if (sdCardExist) {
            File sdDir = Environment.getExternalStorageDirectory();
            return sdDir.toString();
        }
        return "";
    }



    public static Bitmap getImage(String srcPath) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        // 开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);// 此时返回bm为空
        newOpts.inJustDecodeBounds = false;

        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        // 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        float hh = 1280f;// 这里设置高度为800f
        float ww = 720f;// 这里设置宽度为480f
        // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;// be=1表示不缩放
        if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {// 如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;// 设置缩放比例
        // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        // bitmap= rotateBitmap(bitmap,90);
        if (getDegress(srcPath) != 0) {
            return compressImage(rotateBitmap(bitmap, getDegress(srcPath)),srcPath);
        }

        return compressImage(bitmap,srcPath);// 压缩好比例大小后再进行质量压缩
    }

    /**
     * get the orientation of the bitmap {@link android.media.ExifInterface}
     *
     * @param path
     * @return
     */
    public static int getDegress(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    /**
     * 质量压缩
     * 并保存
     * @param image
     * @return
     */
    public static Bitmap compressImage(Bitmap image, String path) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 60, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 60;
        while (baos.toByteArray().length / 1024 > 60) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();// 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;// 每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
        FileOutputStream stream;
        try {
            stream = new FileOutputStream(path);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return bitmap;
    }

    /**
     * rotate the bitmap
     *
     * @param bitmap
     * @param degress
     * @return
     */
    public static Bitmap rotateBitmap(Bitmap bitmap, int degress) {
        if (bitmap != null) {
            Matrix m = new Matrix();
            m.postRotate(degress);
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                    bitmap.getHeight(), m, true);
            return bitmap;
        }
        return bitmap;
    }
}
