package edu.zjut.calculator;

import android.content.Context;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.os.VibratorManager;

public class VibrateProvider {
    private static VibratorManager vibrator;

    public static void vibrateOnClick(Context context){
        vibrator= (VibratorManager) context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE);
        vibrator.getDefaultVibrator().vibrate(VibrationEffect.createPredefined(VibrationEffect.EFFECT_CLICK));
    }
}
