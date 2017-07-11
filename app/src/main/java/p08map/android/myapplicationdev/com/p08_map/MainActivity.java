package p08map.android.myapplicationdev.com.p08_map;

import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {

    Button btnN, btnC, btnE;
    Spinner spinner;
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnN = (Button)findViewById(R. id. btnN);
        btnC = (Button)findViewById(R. id. btnC);
        btnE = (Button)findViewById(R. id. btnE);
        spinner = (Spinner)findViewById(R. id. spinner);

        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment)
                fm.findFragmentById(R.id.map);

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;
                UiSettings ui = map.getUiSettings();
                ui.setZoomControlsEnabled(true);

                int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION);

                if (permissionCheck == PermissionChecker.PERMISSION_GRANTED) {
                    map.setMyLocationEnabled(true);
                } else {
                    Log.e("GMap - Permission", "GPS access has not been granted");
                }

                LatLng poi_sg = new LatLng(1.3521, 103.8198);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_sg,
                        15));

                final LatLng poi_North = new LatLng(1.4491, 103.8185);
                Marker north = map.addMarker(new
                        MarkerOptions()
                        .position(poi_North)
                        .title("HQ - North")
                        .snippet("Block 333, Admiralty Ave 3, 765654 Operating hours: 10am-5pm\n" +
                                "Tel:65433456\n")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                btnN.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_North,
                                15));
                    }
                });

                final LatLng poi_Central = new LatLng(1.3048, 103.8318);
                Marker central = map.addMarker(new
                        MarkerOptions()
                        .position(poi_Central)
                        .title("Central")
                        .snippet("Block 3A, Orchard Ave 3, 134542 \n" +
                                "Operating hours: 11am-8pm\n" +
                                "Tel:67788652\n")
                        .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher)));

                btnC.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_Central,
                                15));
                    }
                });

                final LatLng poi_East = new LatLng(1.3496, 103.9568);
                final Marker east = map.addMarker(new
                        MarkerOptions()
                        .position(poi_East)
                        .title("East")
                        .snippet("Block 555, Tampines Ave 3, 287788 \n" +
                                "Operating hours: 9am-5pm\n" +
                                "Tel:66776677\"\n")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.pika)));

                btnE.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_East,
                                15));
                    }
                });

                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        Toast toast = Toast.makeText(getApplicationContext(), marker.getTitle().toString(), Toast.LENGTH_SHORT);
                        toast.show();
                        return false;
                    }
                });
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent,View view,int position,long id) {
                        if (spinner.getSelectedItemPosition() == 0) {
                            map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_North,
                                    15));
                        } else if (spinner.getSelectedItemPosition() == 1) {
                            map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_Central,
                                    15));
                        } else {
                            map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_East,
                                    15));
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
        });
    }
}

