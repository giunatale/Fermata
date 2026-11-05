package me.aap.fermata.auto;

import com.google.android.apps.auto.sdk.CarActivity;
import com.google.android.apps.auto.sdk.CarActivityService;

import me.aap.fermata.media.service.FermataMediaServiceConnection;
import me.aap.fermata.media.service.MediaSessionCallback;
import me.aap.utils.log.Log;

/**
 * @author Andrey Pavlenko
 */
public class CarService extends CarActivityService {

	@Override
	public Class<? extends CarActivity> getCarActivity() {
		return MainCarActivity.class;
	}

	@Override
	public void onCreate() {
		Log.i("Car service created");
		super.onCreate();
	}

	@Override
	public void onDestroy() {
		Log.d("Destroying CarService: " + this);
		FermataMediaServiceConnection s = MainCarActivity.service;
		Log.i("Car service destroyed, media service connected: ", s != null,
				", playing: ", (s != null) && (s.getMediaSessionCallback() != null) && s.getMediaSessionCallback().isPlaying());
		if (s == null) return;
		MainCarActivity.service = null;
		MediaSessionCallback cb = s.getMediaSessionCallback();
		if ((cb != null) && cb.isPlaying()) cb.onPause();
		s.disconnect();
		super.onDestroy();
	}
}
