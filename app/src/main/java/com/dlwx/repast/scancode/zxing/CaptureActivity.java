package com.dlwx.repast.scancode.zxing;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.widget.Toolbar;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.dlwx.repast.R;
import com.dlwx.repast.base.BaseActivity;
import com.dlwx.repast.base.MyApplication;
import com.dlwx.repast.model.bean.VerSuccBean;
import com.dlwx.repast.presenter.Presenter;
import com.dlwx.repast.scancode.camera.CameraManager;
import com.dlwx.repast.scancode.decoding.CaptureActivityHandler;
import com.dlwx.repast.scancode.decoding.InactivityTimer;
import com.dlwx.repast.utiles.HttpUtiles;
import com.dlwx.repast.view.activity.InputActivity;
import com.dlwx.repast.view.activity.VerifySuccessActivity;
import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;


/**
 * Initial the camera
 *
 * @author Ryan.Tang
 */
public class CaptureActivity extends BaseActivity implements Callback {

    private CaptureActivityHandler handler;
    private ViewfinderView viewfinderView;
    private boolean hasSurface;
    private Vector<BarcodeFormat> decodeFormats;
    private String characterSet;
    private InactivityTimer inactivityTimer;
    private MediaPlayer mediaPlayer;
    private boolean playBeep;
    private static final float BEEP_VOLUME = 0.10f;
    private boolean vibrate;
    private TextView title;
    private Toolbar toolbar;
    private ImageButton imageView;
    private Camera camera;
    private boolean isOpen = true;
    private Camera.Parameters parameters;
    private TextView textTiele;
    private TextView tvRight;
    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    @Override
    public void initView() {
        setContentView(R.layout.camera);
        CameraManager.init(getApplication());
        viewfinderView = (ViewfinderView) findViewById(R.id.viewfinder_view);
//        title = (TextView) findViewById(R.id.tv_title);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        imageView = (ImageButton) findViewById(R.id.imageView2);
        textTiele = (TextView) findViewById(R.id.title_txt);
        tvRight = (TextView) findViewById(R.id.tv_right);
        hasSurface = false;
        inactivityTimer = new InactivityTimer(this);
        initTabBar(toolbar);
    }

    @Override
    public void initData() {
            textTiele.setText("餐券验证");
        tvRight.setText("手动输入");
    }

    @Override
    public void initListener() {

        //开启闪光灯
        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                camera = CameraManager.getCamera();
                parameters = camera.getParameters();
                if (isOpen) {
                    //开启闪光灯
                    imageView.setBackgroundResource(R.mipmap.icon_shanguang);
                    parameters.setFlashMode(parameters.FLASH_MODE_TORCH);
                    camera.setParameters(parameters);
                    isOpen = false;
                } else {
                    //关闭闪光灯
                    imageView.setBackgroundResource(R.mipmap.icon_shanguangbu);
                    parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                    camera.setParameters(parameters);
                    isOpen = true;
                }


            }
        });
        tvRight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ctx, InputActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        SurfaceView surfaceView = (SurfaceView) findViewById(R.id.preview_view);



        SurfaceHolder surfaceHolder = surfaceView.getHolder();

        if (hasSurface) {
            initCamera(surfaceHolder);
        } else {
            surfaceHolder.addCallback(this);
            surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }
        decodeFormats = null;
        characterSet = null;

        playBeep = true;
        AudioManager audioService = (AudioManager) getSystemService(AUDIO_SERVICE);
        if (audioService.getRingerMode() != AudioManager.RINGER_MODE_NORMAL) {
            playBeep = false;
        }
        initBeepSound();
        vibrate = true;

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (handler != null) {
            handler.quitSynchronously();
            handler = null;
        }
        CameraManager.get().closeDriver();
    }

    @Override
    protected void onDestroy() {
        inactivityTimer.shutdown();
        super.onDestroy();
    }

    /**
     * Handler scan result
     *
     * @param result
     * @param barcode
     */
    public void handleDecode(Result result, Bitmap barcode) {
        inactivityTimer.onActivity();
        playBeepSoundAndVibrate();
        String order_id = result.getText();
        //FIXME
        if (order_id.equals("")) {
            Toast.makeText(CaptureActivity.this, "扫描失败", Toast.LENGTH_SHORT).show();
        } else {
//			System.out.println("Result:"+resultString);
//            Intent resultIntent = new Intent();
//            Bundle bundle = new Bundle();
//            bundle.putString("result", resultString);
//            resultIntent.putExtras(bundle);
//            this.setResult(RESULT_OK, resultIntent);
            Map<String, String> map = new HashMap<>();
            map.put("token", MyApplication.User_Token);
            map.put("order_id", order_id);
            mPreenter.fetch(map, false, HttpUtiles.Scan_Code, "");
        }

    }

    @Override
    public void showData(String s) {
        loading.dismiss();
        Gson gson = new Gson();
        jsonData(s,gson);
    }

    private void jsonData(String s, Gson gson) {
        VerSuccBean verSuccBean = gson.fromJson(s, VerSuccBean.class);
        if (verSuccBean.getCode() == 200) {
            VerSuccBean.BodyBean body = verSuccBean.getBody();
            Intent intent = new Intent(ctx, VerifySuccessActivity.class);
            intent.putExtra("body",body);
            startActivity(intent);
        } else {
            Toast.makeText(ctx, verSuccBean.getResult(), Toast.LENGTH_SHORT).show();
        }
    }
    private void initCamera(SurfaceHolder surfaceHolder) {
        try {
            CameraManager.get().openDriver(surfaceHolder);
        } catch (IOException ioe) {
            return;
        } catch (RuntimeException e) {
            return;
        }
        if (handler == null) {
            handler = new CaptureActivityHandler(this, decodeFormats, characterSet);
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (!hasSurface) {
            hasSurface = true;
            initCamera(holder);
        }

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        hasSurface = false;
        if (camera != null) {
            CameraManager.stopPreview();
        }
    }

    public ViewfinderView getViewfinderView() {
        return viewfinderView;
    }

    public Handler getHandler() {
        return handler;
    }

    public void drawViewfinder() {
        viewfinderView.drawViewfinder();

    }

    private void initBeepSound() {
        if (playBeep && mediaPlayer == null) {
            // The volume on STREAM_SYSTEM is not adjustable, and users found it
            // too loud,
            // so we now play on the music stream.
            setVolumeControlStream(AudioManager.STREAM_MUSIC);
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnCompletionListener(beepListener);

            AssetFileDescriptor file = getResources().openRawResourceFd(
                    R.raw.beep);
            try {
                mediaPlayer.setDataSource(file.getFileDescriptor(),
                        file.getStartOffset(), file.getLength());
                file.close();
                mediaPlayer.setVolume(BEEP_VOLUME, BEEP_VOLUME);
                mediaPlayer.prepare();
            } catch (IOException e) {
                mediaPlayer = null;
            }
        }
    }

    private static final long VIBRATE_DURATION = 200L;

    private void playBeepSoundAndVibrate() {
        if (playBeep && mediaPlayer != null) {
            mediaPlayer.start();
        }
        if (vibrate) {
            Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
            vibrator.vibrate(VIBRATE_DURATION);
        }
    }

    /**
     * When the beep has finished playing, rewind to queue up another one.
     */
    private final OnCompletionListener beepListener = new OnCompletionListener() {
        public void onCompletion(MediaPlayer mediaPlayer) {
            mediaPlayer.seekTo(0);
        }
    };

}