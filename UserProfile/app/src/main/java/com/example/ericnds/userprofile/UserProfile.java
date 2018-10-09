package com.example.ericnds.userprofile;

import android.graphics.Movie;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class UserProfile extends AppCompatActivity {

    private com.example.ericnds.userprofile.User userprofile;
    private Gson gson;
    private ImageView bk;
    private ImageView userphoto;
    private TextView name, user, following, followers, about;
    private RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        gson = new Gson();


        name = findViewById(R.id.Nombre);
        user = findViewById(R.id.Usuario);
        following = findViewById(R.id.numerodefollowing);
        followers = findViewById(R.id.numerodefollowers);
        about = findViewById(R.id.Aboutmetext);

      try {
          InputStream stream=getAssets().open("EricNavarro.json");
          InputStreamReader reader = new InputStreamReader(stream);
          userprofile=gson.fromJson(reader,User.class);
      }catch (IOException e){
          Toast.makeText(this,"Json error", Toast.LENGTH_SHORT).show();
      }

        bk = (ImageView) findViewById(R.id.background);
        userphoto = (ImageView) findViewById(R.id.fotoperfil);
        UpdateUser();
        Glide.with(this)
                .load("file:///android_asset/Background.jpg")
                .into(bk);
        Glide.with(this)
                .load("file:///android_asset/user.jpg")
                .apply(RequestOptions.circleCropTransform())
                .into(userphoto);
    }
    private void UpdateUser() {
            name.setText(userprofile.getName());
            user.setText(userprofile.getUser());
            followers.setText(userprofile.getFollowers());
            following.setText(userprofile.getFollowing());
            about.setText(userprofile.getAbout().replace(",", "\n"));
        }

}
