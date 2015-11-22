package emiastoteam.emiasto;

import android.hardware.SensorManager;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.Toast;

import com.wikitude.architect.ArchitectView;
import com.wikitude.architect.StartupConfiguration;

public class wikitude extends AbstractArchitectCamActivity {
    private static String WIKITUDE_SDK_KEY = "xtxePCeZ5Y7SXNQuy2EqOE5xJF8SEhFVGjXx1VBxgkFfCW9gMrI/pOv/X/7fQQNx2Dyvji4iv30eXJ6aeQ/rIvqPu50r6vzwHqFsye3/CDj4IHiIuld0onBO2x5IHerTi/2XdqRcLkdl1TlqARN+Zuf1kt2Z5SOhZTOXNz+CMmVTYWx0ZWRfX6r/HAHziyMSONHEL/hNbpnCdrlgV64EFw8QXzXHglIOJjIQw2N7X1OQYmq6tRpvROzVJetm0T00ZBkRkJj7+OF3SSV6r2dPXBPqfcbb68mNnVueNeMlN4L/KHlA/u88W9NNT9fY3TOSI46SDveYYNYhmvRm8qTROIfH080B7kLzpFQw8Jl6stEaAafuafuJmp0ZvbSMFO1b8kBSHoaOAMDi1+Qn2YnjJd30RCbhtUsHecgEtqrPCMIUtCDHEApQMEpC5pOe3wMCjAvh9H7SxmCH1QPb7gPsoMXuauRy5DPuorhcsjTz4JWcTOkk2qOeJaj3/ccMjN8GEvPQ5U52ALz6ZO8VfK369sCk1Am2QVO/zUI53e3XX9pkTnoh1x5nCkrDeNeEJ64pHLrY3XMj1MTlcBOUzzjFuLFgXl4S+jcJceGWDShyqWzZUY409SaTLVmI7kkn4CuzzOic+nN2tBmBnA9WaLT9F6OTXwYnl7hjtwWXKJy8BRr6Vp8R/pu5xC6F+bAxOUxz";
    GPSTracker gps;
    @Override
    protected void onPostCreate( final Bundle savedInstanceState ) {
        super.onPostCreate( savedInstanceState );
        this.injectData();
    }



    /**
     * last time the calibration toast was shown, this avoids too many toast shown when compass needs calibration
     */
    private long lastCalibrationToastShownTimeMillis = System.currentTimeMillis();

    @Override
    public String getARchitectWorldPath() {
        return "index.html";
    }

    @Override
    public String getActivityTitle() {
        return "Test-World";
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_wikitude;
    }

    @Override
    public int getArchitectViewId() {
        return R.id.architectView;
    }

    @Override
    public String getWikitudeSDKLicenseKey() {
        return wikitude.this.WIKITUDE_SDK_KEY;
    }

    @Override
    public ArchitectView.SensorAccuracyChangeListener getSensorAccuracyListener() {
        return new ArchitectView.SensorAccuracyChangeListener() {
            @Override
            public void onCompassAccuracyChanged( int accuracy ) {
				/* UNRELIABLE = 0, LOW = 1, MEDIUM = 2, HIGH = 3 */
                if ( accuracy < SensorManager.SENSOR_STATUS_ACCURACY_MEDIUM && wikitude.this != null && !wikitude.this.isFinishing() && System.currentTimeMillis() - wikitude.this.lastCalibrationToastShownTimeMillis > 5 * 1000) {
                    Toast.makeText(wikitude.this, R.string.compass_accuracy_low, Toast.LENGTH_LONG).show();
                    wikitude.this.lastCalibrationToastShownTimeMillis = System.currentTimeMillis();
                }
            }
        };
    }

    @Override
    public ArchitectView.ArchitectUrlListener getUrlListener() {
        return new ArchitectView.ArchitectUrlListener() {

            @Override
            public boolean urlWasInvoked(String uriString) {
//                Uri invokedUri = Uri.parse(uriString);
//
//                // pressed "More" button on POI-detail panel
//                if ("markerselected".equalsIgnoreCase(invokedUri.getHost())) {
//                    final Intent poiDetailIntent = new Intent(wikitude.this, SamplePoiDetailActivity.class);
//                    poiDetailIntent.putExtra(SamplePoiDetailActivity.EXTRAS_KEY_POI_ID, String.valueOf(invokedUri.getQueryParameter("id")) );
//                    poiDetailIntent.putExtra(SamplePoiDetailActivity.EXTRAS_KEY_POI_TITILE, String.valueOf(invokedUri.getQueryParameter("title")) );
//                    poiDetailIntent.putExtra(SamplePoiDetailActivity.EXTRAS_KEY_POI_DESCR, String.valueOf(invokedUri.getQueryParameter("description")) );
//                    wikitude.this.startActivity(poiDetailIntent);
//                    return true;
//                }
//
//                // pressed snapshot button. check if host is button to fetch e.g. 'architectsdk://button?action=captureScreen', you may add more checks if more buttons are used inside AR scene
//                else if ("button".equalsIgnoreCase(invokedUri.getHost())) {
//                    wikitude.this.architectView.captureScreen(ArchitectView.CaptureScreenCallback.CAPTURE_MODE_CAM_AND_WEBVIEW, new ArchitectView.CaptureScreenCallback() {
//
//                        @Override
//                        public void onScreenCaptured(final Bitmap screenCapture) {
//                            // store screenCapture into external cache directory
//                            final File screenCaptureFile = new File(Environment.getExternalStorageDirectory().toString(), "screenCapture_" + System.currentTimeMillis() + ".jpg");
//
//                            // 1. Save bitmap to file & compress to jpeg. You may use PNG too
//                            try {
//                                final FileOutputStream out = new FileOutputStream(screenCaptureFile);
//                                screenCapture.compress(Bitmap.CompressFormat.JPEG, 90, out);
//                                out.flush();
//                                out.close();
//
//                                // 2. create send intent
//                                final Intent share = new Intent(Intent.ACTION_SEND);
//                                share.setType("image/jpg");
//                                share.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(screenCaptureFile));
//
//                                // 3. launch intent-chooser
//                                final String chooserTitle = "Share Snaphot";
//                                wikitude.this.startActivity(Intent.createChooser(share, chooserTitle));
//
//                            } catch (final Exception e) {
//                                // should not occur when all permissions are set
//                                wikitude.this.runOnUiThread(new Runnable() {
//
//                                    @Override
//                                    public void run() {
//                                        // show toast message in case something went wrong
//                                        Toast.makeText(wikitude.this, "Unexpected error, " + e, Toast.LENGTH_LONG).show();
//                                    }
//                                });
//                            }
//                        }
//                    });
//                }
                return true;
            }
        };
    }

    @Override
    public ILocationProvider getLocationProvider(final LocationListener locationListener) {
        return new LocationProvider(this, locationListener);
    }

    @Override
    public float getInitialCullingDistanceMeters() {
        // you need to adjust this in case your POIs are more than 50km away from user here while loading or in JS code (compare 'AR.context.scene.cullingDistance')
        return ArchitectViewHolderInterface.CULLING_DISTANCE_DEFAULT_METERS;
    }

    @Override
    protected boolean hasGeo() {
        return true;
    }

    @Override
    protected boolean hasIR() {
        return true;
    }

    @Override
    protected StartupConfiguration.CameraPosition getCameraPosition() {
        return StartupConfiguration.CameraPosition.DEFAULT;
    }

}
