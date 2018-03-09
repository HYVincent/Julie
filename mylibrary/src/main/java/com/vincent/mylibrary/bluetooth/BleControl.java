package com.vincent.mylibrary.bluetooth;

import android.annotation.TargetApi;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.Log;

import com.vincent.mylibrary.util.MainHandler;
import com.vincent.mylibrary.util.ThreadPoolManager;
import com.vincent.mylibrary.util.TimeCount;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;


/**
 * @author Vincent QQ:1032006226
 * @version v1.0 version
 * @name MyBluetoothDevice name
 * @page com.example.vincent.mybluetoothdevice.bluetooth page
 * @class describe 蓝牙控制类
 * @date 2018/1/23 11:45
 */

public class BleControl {

    private static final String TAG = BleControl.class.getSimpleName();
    private static BleControl instance;
    private Context mContext;
    //蓝牙适配器，操作蓝牙状态
    private BluetoothAdapter mBleAdapter;
    private BluetoothManager mBleManager;
    private BluetoothGatt mBleGatt;
    private BluetoothGattCharacteristic mBleGattCharacteristic;
//    private Handler mHandler = new Handler(Looper.getMainLooper());

    //获取到所有服务的集合
    private HashMap<String, Map<String, BluetoothGattCharacteristic>> servicesMap = new HashMap<>();

    //true 扫描 false 未扫描
    private boolean scanning = false;
    private boolean isPrintfBleStatus =false;
    //是否有连接结果，比如连接失败，连接成功等 有结果就不提示连接超时
    private boolean hasConnectResult = false;

    private BleStatusChangeNotificationListener statusChangeNotificationListener;
    //默认蓝牙扫描时间 10s 低功耗蓝牙不需要自动停止扫描，除非连接设备的时候才停止扫描
//    private static final long BLE_SCAN_TIME_OUT = 10 * 1000;
    /**
    * 连接超时时间
     */
    private static final long BLE_CONNECT_TIME_OUT = 10 * 1000;
    /**
     * 蓝牙状态，打开
     */
    public static final int BLE_STATUS_OPEN = 10001;
    /**
     * 蓝牙状态，关闭
     */
    public static final int BLE_STATUS_CLOSE = 10002;
    /**
     * 蓝牙状态，开始扫描蓝牙
     */
    public static final int BLE_STATUS_SCAN_START = 10003;
    /**
     * 蓝牙状态，停止扫描
     */
    public static final int BLE_STATUS_SCAN_STOP = 10004;
    /**
     * 蓝牙状态，开始连接蓝牙设备
     */
    public static final int BLE_STATUS_SCAN_CONNECTING = 10005;
    /**
     * 找不到service uuid
     */
    public static final int BLE_STATUS_CONNECT_NO_FOUND_SERVICE_UUID = 10006;
    /**
     * 蓝牙状态，开始连接设备
     */
    public static final int BLE_STATUS_START_CONNECTING = 10007;
    /**
     * 蓝牙状态，连接失败
     */
    public static final int BLE_STATUS_CONNECT_FAILE = 10008;
    /**
     * 蓝牙状态，连接超时
     */
    public static final int BLE_STATUS_CONNECT_TIME_OUT = 10009;
    /**
     * 蓝牙状态，连接成功
     */
    public static final int BLE_STATUS_CONNECT_SUCCESS = 10010;
    /**
     * 蓝牙状态，设备断开之后重新连接
     */
    public static final int BLE_STATUS_BREAK_RECONNECTION = 10012;
    /**
     * 数据发送失败
     */
    public static final int BLE_STATUS_SEND_DATE_FAILE = 10013;
    /**
     * 数据发送成功
     */
    public static final int BLE_STATUS_SEND_DATE_SUCCESS = 10014;
    /**
     * 设备收到数据改变
     */
    public static final int BLE_STATUS_DEVICE_ACCEPT_DATA = 10015;
    /**
     * 发送数据，但是蓝牙此时未连接
     */
    public static final int BLE_STATUS_NO_CONNECTED = 10016;
    /**
     * 蓝牙连接成功之后成功创建了可通信通道，表示可以向设备发送数据了
     */
    public static final int BLE_CONNECT_STATUS_UNBLOCKED = 10017;
    /**
     * 蓝牙连接成功之后创建了可通信通道失败 这时数据监听会失败，失败之后收不到设备数据
     */
    public static final int BLE_CONNECT_STATUS_ACCEPT_FAIL = 10018;
    /**
     * 设备断开连接
     */
    public static final int BLE_STATUS_DISCONNECT = 10019;
    /**
     * 搜索到的蓝牙地址
     */
    private List<String > address = new ArrayList<>();
    /**
     * 搜索到的蓝牙设备列表
     */
    private List<BluetoothDevice> bluetoothDevices = new ArrayList<>();
    private BleScanResultListener scanResultListener;
    private BleDataChangeNotificationListener dataChangeNotificationListener;
    private String currentConnectAddress;
    /**
     * 是否连接 true 连接 false 断开
     */
    private boolean isConnect = false;
    /**
     * 此属性一般不用修改
     */
    private static final String BLUETOOTH_NOTIFY_D = "00002902-0000-1000-8000-00805f9b34fb";

    /**
     * 蓝牙设备服务UUID
     */
    private static final String SERVICE_UUID = "6e400001-b5a3-f393-e0a9-e50e24dcca9e";
    /**
     * 通知UUID  读取数据
     */
    private static final String UUID_NOTIFY = "6e400003-b5a3-f393-e0a9-e50e24dcca9e";
    /**
     * 写数据UUID
     */
    private static final String UUID_WRITER = "6e400002-b5a3-f393-e0a9-e50e24dcca9e";

    /**
     * 返回蓝牙扫描状态
     * @return
     */
    public boolean isScanning() {
        return scanning;
    }

    public boolean isConnect() {
        return isConnect;
    }

    /**
     * 初始化蓝牙
     * @param mContext s
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public void initBle(Context mContext){
        if(this.mContext == null){
            this.mContext = mContext.getApplicationContext();
            mBleManager = (BluetoothManager) mContext.getSystemService(Context.BLUETOOTH_SERVICE);
            if(mBleManager == null){
                Log.e(TAG, "initBle: Bluetooth manager is null");
            }else {
                mBleAdapter = mBleManager.getAdapter();
            }
            if(mBleAdapter == null){
                Log.e(TAG, "initBle: Bluetooth adapter is null");
            }
            if(isEnable()){
                Log.d(TAG, "initBle: bluetooth status is open");
            }else {
                Log.d(TAG, "initBle: bluetooth status is close");
            }
        }
    }


    /**
     * 蓝牙连接结果回调
     * @param bluetoothDevice
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void connect(BluetoothDevice bluetoothDevice, boolean enable){
        if(bluetoothDevice != null){
            connect(bluetoothDevice.getAddress(),enable);
        }
    }

    /**
     * Return current connect device address
     * @return
     */
    public String getCurrentConnectAddress(){
        if(mBleAdapter != null && isConnect()){
            return currentConnectAddress;
        }
        return "";
    }

    /**
     * Return current connect bluetoothDevice
     * @return
     */
    public BluetoothDevice getCurrentConnectBluetoothDevice(){
        return mBleAdapter.getRemoteDevice(currentConnectAddress);
    }

    /**
     *
     *  /*
     * 通过使用if(gatt==null)来判断gatt是否被创建过，如果创建过就使用gatt.connect();重新建立连接。
     * 但是在这种情况下测试的结果是重新连接需要花费很长的时间。
     * 解决办法是通过gatt = device.connectGatt(this, false, gattCallback);建立一个新的连接对象，很明显这样的速度要比上一种方法快很多
     * 然而，多次创建gatt连接对象的直接结果是创建过6个以上gatt后就会再也连接不上任何设备，原因应该是android中对BLE限制了同时连接的数量为6个
     * 解决办法是在每一次重新连接时都执行一次gatt.close();关闭上一个连接。
     * 有人说为什么不在gatt.disconnect();后加一条gatt.close();呢，原因是如果立即执行gatt.close();会导致gattCallback无法收到STATE_DISCONNECTED的状态。
     * 当然，最好的办法是在gattCallback收到STATE_DISCONNECTED后再执行gatt.close();，这样逻辑上会更清析一些。
     * @param address
     * @param enable true 失败就重试 false 连接一次
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public boolean connect(String address, final boolean enable){
        if(mBleAdapter == null || TextUtils.isEmpty(address)){
            Log.e(TAG, "mBleAdapter is null or address is null.");
            if(hasStatusChangeNotificationListener()){
                statusChangeNotificationListener.onChangeStatus(BLE_STATUS_CONNECT_FAILE);
            }
            hasConnectResult = true;
            return false;
        }
        currentConnectAddress = address;
        final BluetoothDevice device = mBleAdapter.getRemoteDevice(address);
        /*if(mBleGatt != null){
            mBleGatt.disconnect();
            mBleGatt.close();
            //不重置为null会导致蓝牙有时候连接不上
            mBleGatt = null;
            Log.d(TAG, "connect: mBleGatt reset is null.");
        }else {
            Log.d(TAG, "connect: mBleGatt is null.");
        }*/
        MainHandler.getInstance().post(new Runnable() {
            @Override
            public void run() {
                /**
                 * 第二个参数表示是否需要自动连接。如果设置为 true, 表示如果设备断开了，会不断的尝试自动连接。设置为 false 表示只进行一次连接尝试。
                 第三个参数是连接后进行的一系列操作的回调，例如连接和断开连接的回调，发现服务的回调，成功写入数据，成功读取数据的回调等等
                 */
                mBleGatt = device.connectGatt(mContext, enable, mGattCallback); //该函数才是真正的去进行连接
                if(hasStatusChangeNotificationListener()){
                    statusChangeNotificationListener.onChangeStatus(BLE_STATUS_SCAN_CONNECTING);
                }
            }
        });
        delayConnectResponse();
        return true;
    }

    /**
     * 超时断开连接
     */
    private void delayConnectResponse(){
        TimeCount timeCount = new TimeCount(BLE_STATUS_CONNECT_TIME_OUT, new TimeCount.TimeOnListener() {
            @Override
            public void finishAction() {
                if(!hasConnectResult && !isConnect){
                    if(hasStatusChangeNotificationListener()){
                        statusChangeNotificationListener.onChangeStatus(BLE_STATUS_CONNECT_TIME_OUT);
                    }
                    disConnection(false);
                }
            }

            @Override
            public void everyAction(int s) {

            }
        });
        timeCount.start();
    }

    //发现设备断开之后是否自动重新连接设备默认自动重连，手动断开之后不自动重连
    private boolean isAutoConnect = true;

    /**
     * 断开连接
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public void disConnection(boolean isAutoConnect) {
        if (null == mBleAdapter || null == mBleGatt) {
            Log.e(TAG, "disconnection error maybe no init");
            return;
        }
        this.isAutoConnect = isAutoConnect;
        MainHandler.getInstance().post(new Runnable() {
            @Override
            public void run() {
                mBleGatt.disconnect();
//                mBleGatt.close();
                reset();
            }
        });
    }

    /**
     * 重置数据
     */
    private void reset() {
        isConnect = false;
        servicesMap.clear();
    }

    /**
     * 需要发送的数据列表
     */
    private static List<String> needSendData = new ArrayList<>();

    /**
     * 发送数据 按照添加的先后顺序发送，上一个命令发送成功之后才发送下一个命令
     *
     * @param value         指令
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public void writeBuffer(final String value) {
        if(TextUtils.isEmpty(value)){
            Log.d(TAG, "writeBuffer: the value is null,no send!");
            return;
        }
        needSendData.add(value);
        send();
    }

    private void send() {
        if(needSendData.size() == 0){
            return;
        }
        if (!isEnable()) {
            if(hasStatusChangeNotificationListener()){
                statusChangeNotificationListener.onChangeStatus(BLE_STATUS_SEND_DATE_FAILE);
            }
            Log.e(TAG, "date send fail 1，data = " + needSendData.get(0));
            return;
        }
        if(mBleGatt == null || !isConnect()){
            Log.e(TAG, "writeBuffer: send data fail,bluetooth status is disconnect.");
            if(hasStatusChangeNotificationListener()){
                statusChangeNotificationListener.onChangeStatus(BLE_STATUS_NO_CONNECTED);
            }
            return;
        }
        if (mBleGattCharacteristic == null) {
            mBleGattCharacteristic = getBluetoothGattCharacteristic(SERVICE_UUID, UUID_WRITER);
        }
        if (null == mBleGattCharacteristic) {
            if(hasStatusChangeNotificationListener()){
                statusChangeNotificationListener.onChangeStatus(BLE_STATUS_SEND_DATE_FAILE);
            }
            Log.e(TAG, "date send fail 2，data = " + needSendData.get(0));
            return;
        }
        //设置数组进去
        mBleGattCharacteristic.setValue(HexUtil.hexStringToBytes(needSendData.get(0)));
        //在主线程中写入数据
        MainHandler.getInstance().post(new Runnable() {
            @Override
            public void run() {
                //发送
                boolean b = mBleGatt.writeCharacteristic(mBleGattCharacteristic);
                if(b){
                    /*if(hasStatusChangeNotificationListener()){
                        statusChangeNotificationListener.onChangeStatus(BLE_STATUS_SEND_DATE_SUCCESS);
                    }*/
                }else {
                    if(hasStatusChangeNotificationListener()){
                        statusChangeNotificationListener.onChangeStatus(BLE_STATUS_SEND_DATE_FAILE);
                    }
                }
            }
        });
    }


    private BluetoothGattCallback mGattCallback = new BluetoothGattCallback() {

        @Override
        public void onPhyUpdate(BluetoothGatt gatt, int txPhy, int rxPhy, int status) {
            super.onPhyUpdate(gatt, txPhy, rxPhy, status);
            Log.d(TAG, "onPhyUpdate: ");
        }

        @Override
        public void onPhyRead(BluetoothGatt gatt, int txPhy, int rxPhy, int status) {
            super.onPhyRead(gatt, txPhy, rxPhy, status);
            Log.d(TAG, "onPhyRead: ");
        }

        //此方法连接成功之后才会调用或者断开连接
        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
        @Override
        public void onConnectionStateChange(final BluetoothGatt gatt, int status, int newState) {
            super.onConnectionStateChange(gatt, status, newState);
            //这一个方法有三个参数，第一个就蓝牙设备的 Gatt 服务连接类。
            //第二个参数代表是否成功执行了连接操作，如果为 BluetoothGatt.GATT_SUCCESS 表示成功执行连接操作，
            // 第三个参数才有效，否则说明这次连接尝试不成功。有时候，我们会遇到 status == 133 的情况，根据网上大部分人的说法，
            // 这是因为 Android 最多支持连接 6 到 7 个左右的蓝牙设备，如果超出了这个数量就无法再连接了。所以当我们断开蓝牙设备的连接时，
            // 还必须调用 BluetoothGatt#close 方法释放连接资源。否则，在多次尝试连接蓝牙设备之后很快就会超出这一个限制，导致出现这一个错误再也无法连接蓝牙设备。
            if(status == BluetoothGatt.GATT_SUCCESS){
                // 第三个参数代表当前设备的连接状态，如果 newState == BluetoothProfile.STATE_CONNECTED 说明设备已经连接，
                // 可以进行下一步的操作了（发现蓝牙服务，也就是 Service）。当蓝牙设备断开连接时，这一个方法也会被回调，
                // 其中的 newState == BluetoothProfile.STATE_DISCONNECTED。
                if (newState == BluetoothProfile.STATE_CONNECTED) {
                    MainHandler.getInstance().post(new Runnable() {
                        @Override
                        public void run() {
                            gatt.discoverServices(); //执行到这里其实蓝牙已经连接成功了
                            Log.d(TAG, "onConnectionStateChange: 蓝牙连接成功..");
                            isConnect = true;
                            hasConnectResult = true;
                            if(hasStatusChangeNotificationListener()){
                                statusChangeNotificationListener.onChangeStatus(BLE_STATUS_CONNECT_SUCCESS);
                            }
                        }
                    });
                    //连接成功，停止蓝牙扫描
                    stopBleScan();
                } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                    MainHandler.getInstance().post(new Runnable() {
                        @Override
                        public void run() {
                            //断开了
                            isConnect = false;
                            //掉线了就关闭连接释放资源
                            gatt.disconnect();
                            gatt.close();
                        }
                    });
                    if(hasStatusChangeNotificationListener()){
                        statusChangeNotificationListener.onChangeStatus(BLE_STATUS_DISCONNECT);
                        Log.d(TAG, "onConnectionStateChange: bluetooth device status is disconnect.");
                    }
                    if(!TextUtils.isEmpty(currentConnectAddress)&& isEnable()&& isAutoConnect){
                        Log.d(TAG, "onConnectionStateChange:it will attempt auto connect.");
                        connect(currentConnectAddress,false);
                        if(hasStatusChangeNotificationListener()){
                            statusChangeNotificationListener.onChangeStatus(BLE_STATUS_BREAK_RECONNECTION);
                        }
                    }else{
                        Log.d(TAG, "onConnectionStateChange: bluetooth device status is disconnect.");
                    }
                }else if (newState == BluetoothProfile.STATE_CONNECTED) {
                    //发现服务
                    gatt.discoverServices();
                }
            }else {
                isConnect = false;
                if(hasStatusChangeNotificationListener()){
                    statusChangeNotificationListener.onChangeStatus(BLE_STATUS_CONNECT_FAILE);
                }
                if(mBleGatt != null){
                    mBleGatt.disconnect();
                    mBleGatt.close();
                }
                hasConnectResult = true;
            }
        }

        /* 在成功连接到蓝牙设备之后才能进行这一个步骤，也就是说在 BluetoothGattCalbackl#onConnectionStateChang 方法被成功回调且表示成功
         连接之后调用 BluetoothGatt#discoverService 这一个方法。当这一个方法被调用之后，系统会异步执行发现服务的过程，直到 BluetoothGattCallback#onServicesDiscovered
         被系统回调之后，手机设备和蓝牙设备才算是真正建立了可通信的连接。*/
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
        @Override
        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
            super.onServicesDiscovered(gatt, status);
            if (null != mBleGatt && status == BluetoothGatt.GATT_SUCCESS) {
                List<BluetoothGattService> services = mBleGatt.getServices();
                for (int i = 0; i < services.size(); i++) {
                    HashMap<String, BluetoothGattCharacteristic> charMap = new HashMap<>();
                    BluetoothGattService bluetoothGattService = services.get(i);
                    String serviceUuid = bluetoothGattService.getUuid().toString();
                    List<BluetoothGattCharacteristic> characteristics = bluetoothGattService.getCharacteristics();
                    for (int j = 0; j < characteristics.size(); j++) {
                        charMap.put(characteristics.get(j).getUuid().toString(), characteristics.get(j));
                    }
                    servicesMap.put(serviceUuid, charMap);
                }
                BluetoothGattCharacteristic NotificationCharacteristic=getBluetoothGattCharacteristic(SERVICE_UUID,UUID_NOTIFY);
                if (NotificationCharacteristic==null) {
                    return;
                }
                //建立可通信通道
                boolean isCreateCommunicationAisle  =  enableNotification(true,NotificationCharacteristic);
                if(!isCreateCommunicationAisle){
                    if(hasStatusChangeNotificationListener()){
                        statusChangeNotificationListener.onChangeStatus(BLE_CONNECT_STATUS_ACCEPT_FAIL);
                    }
                }
            }
        }

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
        @Override
        public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            super.onCharacteristicRead(gatt, characteristic, status);
            Log.d(TAG, "callback characteristic read status " + status
                    + " in thread " + Thread.currentThread());
            if (status == BluetoothGatt.GATT_SUCCESS) {
                Log.d(TAG, "onCharacteristicRead--> read data:" + characteristic.getValue());

                byte[] data =  characteristic.getValue();
                Log.d(TAG, "onCharacteristicRead: data =" + HexUtil.bytesToHexString(data));
            }
        }

        //接受Characteristic被写的通知,收到蓝牙模块的数据后会触发onCharacteristicWrite
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
        @Override
        public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            super.onCharacteristicWrite(gatt, characteristic, status);
            //不管发送成功还是失败都要调用send()函数，因为并不知道是否还有待发送命令
            if (status == BluetoothGatt.GATT_SUCCESS) {
                Log.e(TAG, "Send data success! data ="+needSendData.get(0));
                //命令发送成功
                if(needSendData != null && needSendData.size() >0){
                    needSendData.remove(0);
                }
                send();
                if(hasStatusChangeNotificationListener()){
//                    statusChangeNotificationListener.onChangeStatus(BLE_STATUS_DEVICE_ACCEPT_DATA);
                    statusChangeNotificationListener.onChangeStatus(BLE_STATUS_SEND_DATE_SUCCESS);
                }
            } else {
                send();
                if(hasStatusChangeNotificationListener()){
                    statusChangeNotificationListener.onChangeStatus(BLE_STATUS_SEND_DATE_FAILE);
                }
                Log.e(TAG, "Send data failed!");
            }
        }

        //收到数据通知
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
        @Override
        public void onCharacteristicChanged(BluetoothGatt gatt, final BluetoothGattCharacteristic characteristic) {
            super.onCharacteristicChanged(gatt, characteristic);
            MainHandler.getInstance().post(new Runnable() {
                @Override
                public void run() {
                    if(dataChangeNotificationListener != null){
                        byte[] rec = characteristic.getValue();
                        dataChangeNotificationListener.onDatas(rec);
                    }
                }
            });
        }

        @Override
        public void onDescriptorRead(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
            super.onDescriptorRead(gatt, descriptor, status);
            //可读
            Log.d(TAG, "onDescriptorRead: ");
        }

        /**
         * 当向设备Descriptor中写数据时，会回调该函数
         * @param gatt
         * @param descriptor
         * @param status
         */
        @Override
        public void onDescriptorWrite(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
            super.onDescriptorWrite(gatt, descriptor, status);
            if(hasStatusChangeNotificationListener()){
                statusChangeNotificationListener.onChangeStatus(BLE_CONNECT_STATUS_UNBLOCKED);
            }
        }

        @Override
        public void onReliableWriteCompleted(BluetoothGatt gatt, int status) {
            super.onReliableWriteCompleted(gatt, status);
            Log.d(TAG, "onReliableWriteCompleted: ");
        }

        @Override
        public void onReadRemoteRssi(BluetoothGatt gatt, int rssi, int status) {
            super.onReadRemoteRssi(gatt, rssi, status);
            Log.d(TAG, "onReadRemoteRssi: ");
        }

        @Override
        public void onMtuChanged(BluetoothGatt gatt, int mtu, int status) {
            super.onMtuChanged(gatt, mtu, status);
            Log.d(TAG, "onMtuChanged: ");
        }
    };


    /**
     * 设置通知
     *
     * @param enable         true为开启false为关闭
     * @param characteristic 通知特征
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    private boolean enableNotification(boolean enable, BluetoothGattCharacteristic characteristic) {
        if (mBleGatt == null || characteristic == null) {
            return false;
        }
        if (!mBleGatt.setCharacteristicNotification(characteristic, enable)) {
            return false;
        }
        BluetoothGattDescriptor clientConfig = characteristic.getDescriptor(UUID.fromString(BLUETOOTH_NOTIFY_D));
        if (clientConfig == null) {
            return false;
        }
        if (enable) {
            clientConfig.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
        } else {
            clientConfig.setValue(BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE);
        }
        return mBleGatt.writeDescriptor(clientConfig);
    }






    /**
     * 根据服务UUID和特征UUID,获取一个特征{@link BluetoothGattCharacteristic}
     *
     * @param serviceUUID   服务UUID
     * @param characterUUID 特征UUID
     */
    private BluetoothGattCharacteristic getBluetoothGattCharacteristic(String serviceUUID, String characterUUID) {
        if (!isEnable()) {
            throw new IllegalArgumentException(" Bluetooth is no enable please call BluetoothAdapter.enable()");
        }
        if (null == mBleGatt) {
            Log.e(TAG, "mBluetoothGatt is null");
            return null;
        }

        //找服务
        Map<String, BluetoothGattCharacteristic> bluetoothGattCharacteristicMap = servicesMap.get(serviceUUID);
        if (null == bluetoothGattCharacteristicMap) {
            Log.e(TAG, "Not found the serviceUUID!");
            if(hasStatusChangeNotificationListener()){
                statusChangeNotificationListener.onChangeStatus(BLE_STATUS_CONNECT_NO_FOUND_SERVICE_UUID);
            }
            return null;
        }

        //找特征
        Set<Map.Entry<String, BluetoothGattCharacteristic>> entries = bluetoothGattCharacteristicMap.entrySet();
        BluetoothGattCharacteristic gattCharacteristic = null;
        for (Map.Entry<String, BluetoothGattCharacteristic> entry : entries) {
            if (characterUUID.equals(entry.getKey())) {
                gattCharacteristic = entry.getValue();
                break;
            }
        }
        return gattCharacteristic;
    }

    //------------------------------------蓝牙相关方法----------------------------------

    /**
     * sacn扫描回调 5.0以上用
     */
    private ScanCallback mScanCallback = new ScanCallback() {

        /**
         * // callbackType：确定这个回调是如何触发的
         // result：包括4.3版本的蓝牙信息，信号强度rssi，和广播数据scanRecord
         * @param callbackType
         * @param result
         */
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            BluetoothDevice device =result.getDevice();
//            result.getRssi()  信号信息。。。
            if (device != null){
                //过滤掉其他设备
                addBluetoothDevice(device);
            }
        }

        @Override
        public void onBatchScanResults(List<ScanResult> results) {

            Log.d(TAG,"onBatchScanResults");
        }

        @Override
        public void onScanFailed(int errorCode) {
            Log.d(TAG,"onScanFailed "+errorCode);
            scanning = false;
        }
    };

    //4.3以上
    private BluetoothAdapter.LeScanCallback mLeScanCallback = new BluetoothAdapter.LeScanCallback() {
        /**
         *
         * @param device  就是扫描到的蓝牙对象，里面各种跟蓝牙有关的信息
         * @param rssi rssi信号强度，这个值是个负数，范围一般为0到-100，负数越大，代表信号越弱，一般如果超过-90，连接会出现不理想的情况
         * @param bytes  scanRecord广播数据，里面的数据就是蓝牙设备希望手机在连接它之前，让手机知道的信息
         */
        @Override
        public void onLeScan(final BluetoothDevice device, int rssi,byte[] bytes) {
            if (device != null){
                addBluetoothDevice(device);
            }
        }
    };

    /**
     * 扫描蓝牙
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public void scanBle(final BleScanResultListener listener){
        this.scanResultListener = listener;
        if(mBleAdapter == null || isConnect){
            Log.e(TAG, "scanBle: mBleAdapter is null or bluetooth is connect.");
            return;
        }
        if(!isEnable()){
            //蓝牙暂未开启，去打开
            openBle();
            //并在3s之后自动开启扫描..
            TimeCount timeCount = new TimeCount(3, new TimeCount.TimeOnListener() {
                @Override
                public void finishAction() {
                    if(isEnable() && listener != null){
                        Log.d(TAG, "run: 自动开始扫描...");
                        scanBle(listener);
                    }
                }

                @Override
                public void everyAction(int s) {

                }
            });
            timeCount.start();
        }
        if(scanning){
            Log.d(TAG, "scanBle: it doesn't need to be scanned again.");
            return;
        }
        reset();
        ThreadPoolManager.getInstance().execute(new Runnable() {
            @Override
            public void run() {
                //扫描尽量不要放在主线程进行，可以放入子线程里。不然有些机型会出现 do too many work in main thread.
                //android 5.0 以前
                if (Build.VERSION.SDK_INT < 21){
                    mBleAdapter.stopLeScan(mLeScanCallback);
                    mBleAdapter.startLeScan(mLeScanCallback);
                    if(hasStatusChangeNotificationListener()){
                        statusChangeNotificationListener.onChangeStatus(BLE_STATUS_SCAN_START);
                    }
                    Log.d(TAG, "scanDevice: start scan bluetooth device ...");
                    scanning = true;
                } else {
                    BluetoothLeScanner scanner = mBleAdapter.getBluetoothLeScanner();
//            scanner.stopScan(mScanCallback);//java.lang.NullPointerException: Attempt to invoke virtual method 'void android.bluetooth.le.BluetoothLeScanner.stopScan(android.bluetooth.le.ScanCallback)' on a null object reference
                    if(scanner != null){
                        scanner.startScan(mScanCallback);
                        if(hasStatusChangeNotificationListener()){
                            statusChangeNotificationListener.onChangeStatus(BLE_STATUS_SCAN_START);
                        }
                        Log.d(TAG, "scanDevice: start scan bluetooth device success .");
                        scanning = true;
                    }else {
                        Log.d(TAG, "scanDevice: scan ble faile,because scanner is null");
                    }
                }
            }
        });
    }

    /**
     * stop scanning bluetooth
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void stopBleScan(){
        if(!scanning){
            Log.d(TAG, "stopBleScan: bluetooth status is no scanning,you don't have to stop.");
            return;
        }
        if(mBleAdapter != null && scanning){
            scanning = false;
            mBleAdapter.getBluetoothLeScanner().stopScan(mScanCallback);
            mBleAdapter.stopLeScan(mLeScanCallback);
            if(hasStatusChangeNotificationListener()){
                statusChangeNotificationListener.onChangeStatus(BLE_STATUS_SCAN_STOP);
            }
            Log.d(TAG, "stopBleScan:  stop scan bluetooth device ...");
        }else {
            Log.d(TAG, "stopBleScan: mBleAdapter is null or you don't have to stop.");
        }
    }



    public void registerNotification(Activity activity){
        if(mContext != null && mReceiver != null){
            activity.registerReceiver(mReceiver, makeFilter());
        }
    }

    /**
     * 取消注册
     */
    public void unRegister(Activity activity){
        if(activity != null && mReceiver != null){
            activity.unregisterReceiver(mReceiver);
        }
    }

    //注销蓝牙监听
    private IntentFilter makeFilter() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        return filter;
    }

    /**
     * 当前蓝牙是否打开
     * @return true 开 false 关闭
     */
    public boolean isEnable(){
        if(mBleAdapter != null && mBleAdapter.isEnabled()){
            return true;
        }
        return false;
    }

    /**
     * 打开蓝牙
     * @return
     */
    public BleControl openBle(){
        if(!isEnable()){
            //检测蓝牙是否打开
            if(mBleAdapter!= null){
                //打开蓝牙 打开方式有两种 1、隐式打开  2、显示打开
                //隐式打开蓝牙 这种方式不会弹出打开弹出框
                mBleAdapter.enable();
                if(hasStatusChangeNotificationListener()){
                    statusChangeNotificationListener.onChangeStatus(BLE_STATUS_OPEN);
                }
            }else {
                Log.e(TAG, "openBle: mBleAdapter is null" );
            }
        }
        return this;
    }

    /**
     * 关闭蓝牙
     */
    public void closeBle(){
        if(mBleAdapter != null){
            scanning = false;
            mBleAdapter.disable();
            if(hasStatusChangeNotificationListener()){
                statusChangeNotificationListener.onChangeStatus(BLE_STATUS_CLOSE);
            }
        }
    }

    /**
     * 判断是否设置了监听器
     * @return
     */
    private boolean hasStatusChangeNotificationListener(){
        if(statusChangeNotificationListener != null){
            return true;
        }else {
            Log.d(TAG, "hasStatusChangeNotificationListener: is null.");
            return false;
        }
    }

    public void setStatusChangeNotificationListener(BleStatusChangeNotificationListener statusChangeNotificationListener) {
        this.statusChangeNotificationListener = statusChangeNotificationListener;
    }

    public interface BleStatusChangeNotificationListener{
        /**
         * 蓝牙状态该表
         * @param status
         */
        void onChangeStatus(int status);

    }


    public interface BleDataChangeNotificationListener{

        void onDatas(byte[] datas);

    }


    public synchronized static  BleControl getInstance() {
        if(instance == null){
            instance = new BleControl();
        }
        return instance;
    }



    /**
     * 蓝牙状态改变的广播通知
     */
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case BluetoothAdapter.ACTION_STATE_CHANGED:
                    int blueState = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, 0);
                    if(hasStatusChangeNotificationListener()){
                        statusChangeNotificationListener.onChangeStatus(blueState);
                    }else {
                        Log.d(TAG, "onReceive: 请注册蓝牙广播。。");
                    }
                    if(isPrintfBleStatus){
                        switch (blueState) {
                            case BluetoothAdapter.SCAN_MODE_NONE:
                                Log.d(TAG, "onReceive: 没有扫描到设备");
                                break;
                            case BluetoothAdapter.SCAN_MODE_CONNECTABLE:
                                Log.d(TAG, "onReceive: 可连接。。。");
                                break;
                            case BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE:
                                Log.d(TAG, "onReceive: 可被发现...");
                                break;
                            case BluetoothAdapter.STATE_TURNING_ON:
                                Log.d(TAG, "onReceive: 正在打开蓝牙。。");
                                break;
                            case BluetoothAdapter.STATE_ON:
                                Log.d(TAG, "onReceive: 蓝牙打开");
                                if(hasStatusChangeNotificationListener()){
                                    statusChangeNotificationListener.onChangeStatus(BLE_STATUS_OPEN);
                                }else {
                                    Log.d(TAG, "onReceive: statusChangeNotificationListener is null.");
                                }
                                break;
                            case BluetoothAdapter.STATE_TURNING_OFF:
                                Log.d(TAG, "onReceive: 蓝牙正在关闭..");
                                break;
                            case BluetoothAdapter.STATE_OFF:
                                Log.d(TAG, "onReceive: 蓝牙关闭");
                                scanning = false;
                                isConnect = false;
                                if(hasStatusChangeNotificationListener()){
                                    statusChangeNotificationListener.onChangeStatus(BLE_STATUS_CONNECT_FAILE);
                                }
                                hasConnectResult = true;
                                break;
                            default:break;
                        }
                    }
                    break;
                default:break;
            }
        }
    };

    /**
     * 设置蓝牙可见性
     * @param activity
     * @param times
     */
    public void setBluetoothIsVisible(Activity activity,long times){
        //启动修改蓝牙可见性的Intent
        Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        //设置蓝牙可见性的时间，方法本身规定最多可见300秒
//        intent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
        intent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, times);
        activity.startActivity(intent);
    }

    /**
     * 设置蓝牙是可见的，听说这个方法可以实现永久可见
     */
    public void setBluetoothIsVisible (){
        //声明一个class类
        Class serviceManager = null;
        try {
            //得到这个class的类
            serviceManager = Class.forName("android.bluetooth.BluetoothAdapter");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //声明一个方法
        Method method = null;
        try {
            //得到指定的类中的方法
            method = serviceManager.getMethod("setDiscoverableTimeout", String.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        try {
            //调用这个方法
            method.invoke(serviceManager.newInstance(), 30);//根据测试，发现这一函数的参数无论传递什么值，都是永久可见的
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭永久可见性
     */
    public void closeDiscoverableTimeout() {
//        BluetoothAdapter adapter=BluetoothAdapter.getDefaultAdapter();
        try {
            if(mBleAdapter != null){
                Method setDiscoverableTimeout = BluetoothAdapter.class.getMethod("setDiscoverableTimeout", int.class);
                setDiscoverableTimeout.setAccessible(true);
                Method setScanMode =BluetoothAdapter.class.getMethod("setScanMode", int.class,int.class);
                setScanMode.setAccessible(true);

                setDiscoverableTimeout.invoke(mBleAdapter, 1);
                setScanMode.invoke(mBleAdapter, BluetoothAdapter.SCAN_MODE_CONNECTABLE,1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public interface BleScanResultListener{
        void onScanResult(List<BluetoothDevice> bluetoothDevices);
    }

    public void setDataChangeNotificationListener(BleDataChangeNotificationListener dataChangeNotificationListener) {
        this.dataChangeNotificationListener = dataChangeNotificationListener;
    }

    /**
     * 把扫描到的蓝牙设备添加到集合
     */
    private synchronized void addBluetoothDevice(BluetoothDevice device){
        if(!hasExist(device.getAddress())){
            address.add(device.getAddress());
            bluetoothDevices.add(device);
            scanResultListener.onScanResult(bluetoothDevices);
        }
    }

    /**
     * 去重判断
     * @param deviceAddress
     * @return
     */
    private boolean hasExist(String deviceAddress){
        for (String string : address){
            if(TextUtils.equals(string,deviceAddress)){
                return true;
            }
        }
        return false;
    }

}
