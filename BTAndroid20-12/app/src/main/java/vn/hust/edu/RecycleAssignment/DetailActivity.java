package vn.hust.edu.RecycleAssignment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import org.json.JSONObject;

public class DetailActivity extends AppCompatActivity {
    private ImageView avatar;
    private TextView username;
    private TextView name;
    private TextView email;
    private TextView address;
    private TextView company;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);
        intent=getIntent();
        avatar=findViewById(R.id.avt);
        name=findViewById(R.id.name_de);
        username=findViewById(R.id.username_de);
        email=findViewById(R.id.email_de);
        address=findViewById(R.id.address);
        company=findViewById(R.id.company);
        try{
            JSONObject jsonObject=new JSONObject(intent.getStringExtra("data"));
            Picasso.get().load("https://lebavui.github.io"+jsonObject.getJSONObject("avatar").getString("photo")).into(avatar);
            name.setText(jsonObject.getString("name"));
            username.setText(jsonObject.getString("username"));
            email.setText(jsonObject.getString("email"));
            JSONObject add=jsonObject.getJSONObject("address");
            address.setText(
                    add.getString("zipcode")+" - "+
                            add.getString("street")+" - "+
                            add.getString("suite")+" - "+
                            add.getString("city")+"\n"+
                            add.getJSONObject("geo").getString("lat")+" - "+add.getJSONObject("geo").getString("lng")
            );
            JSONObject com=jsonObject.getJSONObject("company");
            company.setText(
                    com.getString("name")+"\n"
                    +com.getString("catchPhrase")+"\n"
                    +com.getString("bs")
            );
        }catch (Exception e){

        }
    }

}
