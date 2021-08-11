package fpoly.andoid.extest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TimePicker;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    public static ViewPager viewPager;
    Toolbar toolbar;
    PagerAdapter pagerAdapter;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                progressDialog.dismiss();
            }}, 3500);

        initView();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        pagerAdapter = new PagerAdapter(getSupportFragmentManager(),1);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);


    }

    private void initView() {
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager2);
        toolbar = findViewById(R.id.toolbar);

    }

//    private void ChonGio(){
//        Calendar calendar = Calendar.getInstance();
//        int gio = calendar.get(Calendar.HOUR_OF_DAY);
//        int phut = calendar.get(Calendar.MINUTE);
//        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
//            @Override
//            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
//                calendar.set(0, 0, 0, hourOfDay, minute);
//                tvShowTime.setText(simpleDateFormat.format(calendar.getTime()));
//            }
//        }, gio, phut, true);
//        timePickerDialog.show();
//    }
}