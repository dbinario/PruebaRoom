package mx.fenrir.pruebaroom.ui;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import mx.fenrir.pruebaroom.R;

public class MainActivity extends AppCompatActivity {

    Button btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        btnlogin=findViewById(R.id.buttonlogin);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(MainActivity.this, DashboardActivity.class);
                startActivity(i);

            }
        });





    }
}
