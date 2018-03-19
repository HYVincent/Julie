package com.vincent.mylibrary.util;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Toast;

import com.vincent.mylibrary.MyLibrary;
import com.vincent.mylibrary.entity.EventMsg;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Locale;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.mylibrary.util
 * @class describe
 * @date 2018/2/7 1:03
 */

public class SystemUtil {

    private static final String TAG = SystemUtil.class.getSimpleName();


    /**
     * 获取手机型号
     * @return
     */
    public static String getPhoneMdel() {
        String s = Build.MODEL;
        return s;

    }

    /**
     * 获取手机厂商
     * @return
     */
    public static String getPhoneManufacturer() {
        String phoneManufacturer = Build.MANUFACTURER;
        return phoneManufacturer;
    }

    /**
     * 返回系统版本号
     * @return
     */
    public static int getAndroidSDKVersion() {
        int version = 0;
        try {
            version = Integer.parseInt(Build.VERSION.SDK);
        } catch (NumberFormatException e) {
            Log.d(TAG, "getAndroidSDKVersion: " + e.toString());
        }
        return version;
    }

    /**
     * 获取手机的IMEI号码
     * @param context
     * @return
     */
    public static String getIMEINumber(Context context) {
        @SuppressLint("MissingPermission") String imei = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
        Log.d(TAG, "getIMEINumber: imei-->"+imei);
//        不过纯APP开发SystemProperties，TelephonyProperties汇报错误，因为android.os.SystemProperties在SDK的库中是没有的，
//        需要把Android SDK 目录下data下的layoutlib.jar文件加到当前工程的附加库路径中，就可以Import。
//        如果Android Pad没有IMEI,用此方法获取设备ANDROID_ID：
//        String IMEI =SystemProperties.get(android.telephony.TelephonyProperties.PROPERTY_IMEI);
        return imei;
    }
    /**
     * 判断应用是在前台还是后台
     */

    public static boolean isBackground(Context context) {
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager
                .getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(context.getPackageName())) {
                if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_BACKGROUND) {
                    Log.d(TAG, "isBackground: 后台-->"+appProcess.processName);
                    return true;
                } else {
                    Log.d(TAG, "isBackground: 前台-->"+appProcess.processName);
                    return false;
                }
            }
        }
        return false;
    }
    /**
     * 判断某个服务是否正在运行的Method
     *
     * @param mContext
     * @param serviceName
     * 是包�?+服务的类名（例如：net.loonggg.testbackstage.TestService�?
     * @return true 在运行 false 不在运行
     */
    public static boolean isServiceWork(Context mContext, String serviceName) {
        boolean isWork = false;
        ActivityManager myAM = (ActivityManager) mContext
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> myList = myAM.getRunningServices(40);
        if (myList.size() <= 0) {
            return false;
        }
        for (int i = 0; i < myList.size(); i++) {
            String mName = myList.get(i).service.getClassName().toString();
            if (mName.equals(serviceName)) {
                isWork = true;
                break;
            }
        }
        return isWork;
    }

    /**
     * 开启权限
     * @param context
     */
    public static void goSetting(Context context){
        try {
            Intent intent = new Intent("com.shangyi.sayimo");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ComponentName comp = new ComponentName("com.coloros.safecenter",
                    "com.coloros.safecenter.permission.floatwindow.FloatWindowListActivity");
            intent.setComponent(comp);
            context.startActivity(intent);
        } catch (Exception e) {
            //抛出异常时提示信息
            Toast.makeText(context, "进入失败手动进入", Toast.LENGTH_LONG).show();
        }
    }
    public static boolean isAppInstalled(Context context,String packageName) {
        PackageManager pm = context.getPackageManager();
        boolean installed =false;
        try {
            pm.getPackageInfo(packageName,PackageManager.GET_ACTIVITIES);
            installed =true;
        } catch(PackageManager.NameNotFoundException e) {
            installed =false;
        }
        return installed;
    }
    public static File getSystemDir(Context context, String name){
        return context.getDir(name, Context.MODE_PRIVATE);
    }

    public static File getSystemFile(File dir, String fileName) throws IllegalArgumentException {
        if(dir == null || !dir.exists()){
            throw new IllegalArgumentException(" dir no exists!");
        }
        File file = new File(dir, fileName);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                file = null;
                Log.d(TAG, "getSystemFile:create new file error! "+ e);
            }
        }
        return file;
    }

    public static boolean saveSystemFile(String content, File file){
        if(file != null && file.exists() && file.isFile()){
            if(content == null){
                content = "";
            }
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(file);
                fos.write(content.getBytes("UTF-8"));
                return true;
            } catch (FileNotFoundException e) {
                Log.e(TAG, " no found error! "+ e);
            } catch (UnsupportedEncodingException e) {
                Log.e(TAG, " encoding error! "+ e);
            } catch (IOException e) {
                Log.e(TAG, " io error! "+ e);
            } catch (Exception e) {
                Log.e(TAG, " other error! "+ e);
            }finally {
                closeCloseable(fos);
            }
        }
        return false;
    }

    public static String readSystemFile(File file){
        String result = null;
        if(file != null && file.exists() && file.isFile()){
            FileInputStream fis = null;
            ByteArrayOutputStream bos = null;
            try {
                fis = new FileInputStream(file);
                bos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len = -1;
                while ((len = fis.read(buffer)) != -1) {
                    bos.write(buffer, 0, len);
                }
                result = bos.toString("UTF-8");
            } catch (FileNotFoundException e) {
                Log.e(TAG, " no found error! "+e);
            } catch (IOException e) {
                Log.e(TAG, " io error! "+ e);
            } catch (Exception e) {
                Log.e(TAG, " other error! "+ e);
            } finally {
                closeCloseable(bos);
                closeCloseable(fis);
            }
        }
        return result;
    }

    public static void deleteSystemDir(Context context, String dirName){
        File dir = getSystemDir(context, dirName);
        deleteDir(dir);
    }

    public static void deleteSystemFile(Context context, String dirName, String fileName){
        File dir = getSystemDir(context, dirName);
        if(dir != null){
            File file = new File(dir, fileName);
            deleteFile(file);
        }
    }

    public static boolean deleteDir(File dir){
        if(dir == null || !dir.exists()){
            return false ;
        }
        try {
            deleteAllFile(dir);
            dir.delete();
            return true;
        } catch (Exception e) {
            Log.e(TAG, " delete dir error! "+ e);
            return false;
        }
    }

    public static void deleteAllFile(File dir){
        if (dir != null && dir.exists() && dir.isDirectory()) {
            String[] filesList = dir.list();
            File tempFile = null;
            int length = filesList.length;
            try {
                for (int i = 0; i < length; i++) {
                    tempFile = new File(dir, filesList[i]);
                    if (tempFile != null) {
                        if (tempFile.isFile()) {
                            tempFile.delete();
                        } else if (tempFile.isDirectory()) {
                            deleteDir(tempFile);
                        }
                    }
                }
            } catch (Exception e) {
                Log.e(TAG, " delete all file error! "+ e);
            }
        }
    }

    public static void deleteFile(File file){
        if (file != null && file.exists() && file.isFile()) {
            file.delete();
        }
    }

    private static void closeCloseable(Closeable closeable){
        if(closeable != null){
            try {
                closeable.close();
            } catch (IOException e) {
            }
        }
    }
    /**
     * 通过反射获取类对象
     * @param context
     * @param className 包含包名
     * @return
     */
    public static Object getReflectInstance(Context context, String className){
        try {
            //获取Student的Class对象
            Class<?> clazz = Class.forName(className);
            //获取该类中所有的属性
            Field[] fields = clazz.getDeclaredFields();
            //遍历所有的属性
            for (Field field : fields) {
                //打印属性信息，包括访问控制修饰符，类型及属性名
                System.out.println(field);
//                System.out.println("修饰符：" + Modifier.toString(field.getModifiers()));
                Log.d(SystemUtil.class.getSimpleName(),"修饰符：" + Modifier.toString(field.getModifiers()));
                Log.d(SystemUtil.class.getSimpleName(),"类型：" + field.getType());
                Log.d(SystemUtil.class.getSimpleName(),"属性名：" + field.getName());
            }
            //获取该类中的所有方法
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                //打印方法签名
                System.out.println(method);
                Log.d(SystemUtil.class.getSimpleName(),method.toString());
                Log.d(SystemUtil.class.getSimpleName(),"修饰符：" + Modifier.toString(method.getModifiers()));
                Log.d(SystemUtil.class.getSimpleName(),"方法名：" + method.getName());
                Log.d(SystemUtil.class.getSimpleName(),"返回类型：" + method.getReturnType());
                //获取方法的参数对象
                Class<?>[] clazzes = method.getParameterTypes();
                for (Class<?> class1 : clazzes) {
                    Log.d(SystemUtil.class.getSimpleName(),"参数类型：" + class1);
                }
            }
            //通过Class对象创建实例
//            Student student = (Student)clazz.newInstance();
//            //获取属性名为studentName的字段(Field)对象，以便下边重新设置它的值
//            Field studentName = clazz.getField("studentName");
//            //设置studentName的值为”张三“
//            studentName.set(student, "张三");
//
//            //通过Class对象获取名为”finishTask“，参数类型为String的方法(Method)对象
//            Method finishTask = clazz.getMethod("finishTask", String.class);
//            //调用finishTask方法
//            finishTask.invoke(student, "数学");
            return clazz.newInstance();
        } catch (RuntimeException e) {
            throw e;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    public static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 改变当前语言
     * @param locale
     */
    public static void changeLanguage(Context mContext,Locale locale) {
        Resources resources = mContext.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        Configuration config = resources.getConfiguration();
        // 应用用户选择语言
        config.locale = locale;
        resources.updateConfiguration(config, dm);
    }
    /**
     * 设置当前语言
     *
     * @param language
     */
    public static void setAppLanguage(Context mContext,String language) {
        //获取当前资源对象
        Resources resources = mContext.getResources();
        //获取设置对象
        Configuration configuration = resources.getConfiguration();
        //获取屏幕参数
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        //设置本地语言
        switch (language) {
            case "zh":
                configuration.locale = new Locale("zh");
                break;
            case "en":
                configuration.locale = new Locale("en");
                break;
         default:break;
        }
        resources.updateConfiguration(configuration, displayMetrics);
    }


}
