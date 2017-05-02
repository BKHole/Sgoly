package com.libt.sgoly.util;

import java.io.IOException;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;

public final class Accompaniment {
	public MediaPlayer mBarcodeMediaPlayer = null;
	Context ownersContext = null;
	private final int myResSoundId;

	public Accompaniment(Context context, int resId) {
		ownersContext = context;
		myResSoundId = resId;
	}

	public boolean unint() {
		mBarcodeMediaPlayer.release();
		mBarcodeMediaPlayer = null;
		return true;
	}

	public boolean init() {
		if (mBarcodeMediaPlayer != null) {
			return true;
		}
		mBarcodeMediaPlayer = MediaPlayer.create(ownersContext, myResSoundId);
		if (mBarcodeMediaPlayer == null) {
			mBarcodeMediaPlayer = MediaPlayer.create(ownersContext, myResSoundId);
		}

		if (mBarcodeMediaPlayer == null) {
			return false;
		}

		try {
			if (mBarcodeMediaPlayer != null) {

				mBarcodeMediaPlayer.stop();

			}
			mBarcodeMediaPlayer.prepare();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return true;
	}

	public boolean isSilence() {
		AudioManager mAudioManager = (AudioManager) ownersContext.getSystemService(Context.AUDIO_SERVICE);
		int statusFlag = (mAudioManager.getRingerMode() == AudioManager.RINGER_MODE_SILENT) ? 1 : 0;
		int int1 = 1;
		if (statusFlag == int1) {
			return true;
		}
		return false;
	}

	public boolean start() {
		return start(false);
	}

	public boolean start(boolean forceInSilence) {
		if (forceInSilence == false && isSilence() == true) {
			return false;
		}
		boolean bRet = false;
		if (mBarcodeMediaPlayer != null) {
			mBarcodeMediaPlayer.start();
			bRet = true;
		}
		return bRet;
	}

}
