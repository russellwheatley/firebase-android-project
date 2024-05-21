package io.flutter.plugins.firebase.androidfirebaseproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.FirebaseApp;
import com.google.firebase.appcheck.FirebaseAppCheck;
import com.google.firebase.appcheck.debug.DebugAppCheckProviderFactory;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        FirebaseApp.initializeApp(this);
        FirebaseAppCheck.getInstance().installAppCheckProviderFactory(
                DebugAppCheckProviderFactory.getInstance()
        );

        // Find buttons by their IDs
        Button button1 = findViewById(R.id.id1);
        Button button2 = findViewById(R.id.id2);
        Button button3 = findViewById(R.id.id3);

        // activate()
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAppCheck.getInstance().installAppCheckProviderFactory(
                        DebugAppCheckProviderFactory.getInstance()
                        );
            }
        });
        // firestore request
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseFirestore.getInstance().collection("flutter-tests").document("5Ye3KJWViM4PShxiJtc6").get().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        System.out.println("Firestore get:" + task.getResult().getData());
                    } else {
                        System.out.println("Error getting documents: " + task.getException());
                    }
                });
            }
        });
        // getToken()
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAppCheck.getInstance().getToken(true).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        System.out.println("getToken:" + task.getResult().getToken());
                    } else {
                        System.out.println("Error getting token: " + task.getException());
                    }
                });
            }
        });
    }

}
