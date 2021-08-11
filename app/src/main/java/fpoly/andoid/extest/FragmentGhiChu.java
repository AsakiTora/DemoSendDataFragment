package fpoly.andoid.extest;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FragmentGhiChu extends Fragment {

    View view;
    EditText edtContent, edtTime, edtTitle;
    ImageView imgDate;
    Calendar calendar;
    SimpleDateFormat simpleDateFormat;
    Button btnAdd;
    DAO dao;
    Adapter adapter;
    List<DTO> list;
    DTO dto;

    public FragmentGhiChu() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_ghi_chu, container, false);

        initView();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dao = new DAO(getActivity());
                dao.open();
                list = new ArrayList<>();
                dto = new DTO();
                adapter = new Adapter((MainActivity)getContext(), list);
                String title = edtTitle.getText().toString().trim();
                String content = edtContent.getText().toString().trim();
                String time = edtTime.getText().toString().trim();

                dto.setTitle(title);
                dto.setTime(time);
                dto.setContent(content);

                if (content.equals("") || time.equals("") || title.equals("")){
                    Toast.makeText(getActivity(), "Không được để trống!", Toast.LENGTH_SHORT).show();
                } else {
                    long res = dao.Add(dto);
                    if (res > 0){
                        edtTitle.setText("");
                        edtTime.setText("");
                        edtContent.setText("");

                        list.addAll(dao.GetAll());

                        Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();

                        MainActivity.viewPager.invalidate();
                        MainActivity.viewPager.getAdapter().notifyDataSetChanged();
                        MainActivity.viewPager.setCurrentItem(0);
                    } else {
                        Toast.makeText(getActivity(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        imgDate.setOnClickListener(new View.OnClickListener() {
            int gio = calendar.get(Calendar.HOUR_OF_DAY);
            int phut = calendar.get(Calendar.MINUTE);
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        simpleDateFormat = new SimpleDateFormat("HH:mm a");
                        calendar.set(0, 0, 0, hourOfDay, minute);
                        edtTime.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                }, gio, phut, true);
                timePickerDialog.show();
            }
        });
        return view;
    }

    private void initView() {
        edtContent = view.findViewById(R.id.edtContent);
        edtTime = view.findViewById(R.id.edtTime);
        edtTitle = view.findViewById(R.id.edtTitle);
        imgDate = view.findViewById(R.id.imgDate);
        btnAdd = view.findViewById(R.id.btnAdd);

        calendar = Calendar.getInstance();

        simpleDateFormat = new SimpleDateFormat("HH:mm a");
        edtTime.setText(simpleDateFormat.format(calendar.getTime()));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dao.close();
    }
}