
package com.example.restaurantreservations;

import static android.os.Build.VERSION_CODES.R;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

public class ReservationActivity extends AppCompatActivity {

    private CalendarView calendarView;
    private TextView selectedDateText;
    private TextView peopleCountText;
    private String selectedTime = "";
    private int peopleCount = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendarView = findViewById(R.id.calendarView);
        selectedDateText = findViewById(R.id.textView_selected_date);
        peopleCountText = findViewById(R.id.textView_people_count);

        // 날짜 선택 리스너
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            String date = year + "/" + (month + 1) + "/" + dayOfMonth;
            selectedDateText.setText("선택하신 날짜: " + date);
        });

        // 시간 버튼 설정
        findViewById(R.id.button_time_12).setOnClickListener(v -> selectTime("12:00"));
        findViewById(R.id.button_time_13).setOnClickListener(v -> selectTime("13:00"));
        findViewById(R.id.button_time_14).setOnClickListener(v -> selectTime("14:00"));
        findViewById(R.id.button_time_17).setOnClickListener(v -> selectTime("17:00"));
        findViewById(R.id.button_time_18).setOnClickListener(v -> selectTime("18:00"));
        findViewById(R.id.button_time_19).setOnClickListener(v -> selectTime("19:00"));
        findViewById(R.id.button_time_20).setOnClickListener(v -> selectTime("20:00"));

        // 인원 수 증감 버튼 설정
        findViewById(R.id.button_decrease).setOnClickListener(v -> changePeopleCount(-1));
        findViewById(R.id.button_increase).setOnClickListener(v -> changePeopleCount(1));

        // 예약 확인 버튼 설정
        findViewById(R.id.button_confirm).setOnClickListener(v -> confirmReservation());
    }

    // 시간 선택 함수
    private void selectTime(String time) {
        selectedTime = time;
        Toast.makeText(this, "선택한 시간: " + time, Toast.LENGTH_SHORT).show();
    }

    // 인원 수 변경 함수
    private void changePeopleCount(int delta) {
        if ((peopleCount + delta) > 0) {
            peopleCount += delta;
            peopleCountText.setText(String.valueOf(peopleCount));
        }
    }

    // 예약 확인 함수
    private void confirmReservation() {
        String date = selectedDateText.getText().toString();
        if (date.isEmpty() || selectedTime.isEmpty()) {
            Toast.makeText(this, "날짜와 시간을 모두 선택해 주세요.", Toast.LENGTH_SHORT).show();
        } else {
            String reservationInfo = "예약 정보\n날짜: " + date + "\n시간: " + selectedTime + "\n인원 수: " + peopleCount;
            Toast.makeText(this, reservationInfo, Toast.LENGTH_LONG).show();
        }
    }
}
