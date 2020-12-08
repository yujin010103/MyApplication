package com.example.lab08;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    TextView nameText;
    TextView brandText;
    TextView typeText;
    TextView scentText;
    ImageView photoImage;
    perfume perfume;
    String test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        photoImage = (ImageView)findViewById(R.id.iv_photo);
        nameText = (TextView)findViewById(R.id.tv_name);
        brandText = (TextView)findViewById(R.id.tv_brand);
        typeText = (TextView)findViewById(R.id.tv_type);
        scentText = (TextView)findViewById(R.id.tv_scent);

        database = FirebaseDatabase.getInstance(); // 파이어베이스 데이터베이스 연동

        databaseReference = database.getReference("향수"); // DB 테이블 연결

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // 파이어베이스 데이터베이스의 데이터를 받아오는 곳
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) { // 반복문으로 데이터 List를 추출해냄
                    perfume info = snapshot.getValue(perfume.class); // 만들어뒀던 User 객체에 데이터를 담는다.
                    test = info.getName();
                    if(test.equals("길티")){
                        photoImage.setImageResource(info.getPhoto());
                        nameText.setText(test);
                        brandText.setText(info.getBrand());
                        typeText.setText(info.getType());
                        scentText.setText(info.getScent());
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // 디비를 가져오던중 에러 발생 시
                Log.e("MainActivity", String.valueOf(databaseError.toException())); // 에러문 출력
            }
        });


    }
}