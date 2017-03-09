package com.sudao.basemodule.common.util;

import android.content.Context;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Build;

/**
 * Created by Samuel on 16/7/27 15:46
 * Email:xuzhou40@gmail.com
 * desc:权限检查工具类
 */

public class PermUtil {
    public static final String XIAOMI = "xiaomi";
    public static final String MEIZU = "meizu";
    public static final String HONOR = "honor";

    private static final int STATE_NO_PERMISSION = -2;
    private static final int STATE_RECORDING = -1;
    private static final int STATE_ERROR = 1;
    private static final int STATE_SUCCESS = 2;

    //检查录音权限
    public static int getRecordState(Context context) {
        int minBufferSize = AudioRecord.getMinBufferSize(44100, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT);
        AudioRecord audioRecord = new AudioRecord(MediaRecorder.AudioSource.DEFAULT, 44100, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT, (minBufferSize * 100));
        short[] point = new short[minBufferSize];
        int readSize = 0;
        try {
            audioRecord.startRecording();
        } catch (Exception e) {
            if (audioRecord != null) {
                audioRecord.release();
                audioRecord = null;
            }
            LogUtil.d("无法进入录音初始状态");
            return STATE_ERROR;
        }
        if (audioRecord.getRecordingState() != AudioRecord.RECORDSTATE_RECORDING) {
            if (audioRecord != null) {
                audioRecord.stop();
                audioRecord.release();
                audioRecord = null;
            }
            LogUtil.d("录音机被占用");
            return STATE_RECORDING;
        } else {
            readSize = audioRecord.read(point, 0, point.length);
            if (readSize <= 0) {
                if (audioRecord != null) {
                    audioRecord.stop();
                    audioRecord.release();
                    audioRecord = null;
                }
                LogUtil.d("录音的结果为空");
                return STATE_NO_PERMISSION;
            } else {
                if (audioRecord != null) {
                    audioRecord.stop();
                    audioRecord.release();
                    audioRecord = null;
                }
                LogUtil.d("录音状态正常");
                return STATE_SUCCESS;
            }
        }
    }

    //判断是否是魅族
    public static boolean isMeizu() {
        String brand = Build.BRAND.toLowerCase();
        return MEIZU.equals(brand);
    }

    //判断是否是小米
    public static boolean isXiaomi() {
        String brand = Build.BRAND.toLowerCase();
        return XIAOMI.equals(brand);
    }

    //判断是否是华为荣耀
    public static boolean isHonor() {
        String brand = Build.BRAND.toLowerCase();
        return HONOR.equals(brand);
    }

}
