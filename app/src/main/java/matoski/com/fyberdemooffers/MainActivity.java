package matoski.com.fyberdemooffers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText etAppId;
    private EditText etUID;
    private EditText etApiKey;
    private EditText etPub0;
    private Button buttonLoad;

    private View.OnClickListener listenerLoad = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final Intent intent = new Intent(MainActivity.this, ListOffersActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("appId", etAppId.getText().toString());
            bundle.putString("uid", etUID.getText().toString());
            bundle.putString("apiKey", etApiKey.getText().toString());
            if (etPub0.getText().toString().length() > 0) {
                bundle.putString("pub0", etPub0.getText().toString());
            }
            intent.putExtras(bundle);
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonLoad = (Button) findViewById(R.id.button_load);
        etAppId = (EditText) findViewById(R.id.app_id);
        etUID = (EditText) findViewById(R.id.app_uid);
        etApiKey = (EditText) findViewById(R.id.app_apiKey);
        etPub0 = (EditText) findViewById(R.id.app_pub0);

        etAppId.setText(Constants.DEFAULT_APP_ID.toString());
        etApiKey.setText(Constants.DEFAULT_API_KEY.toString());
        etUID.setText(Constants.DEFAULT_UID.toString());

        buttonLoad.setOnClickListener(listenerLoad);
    }
}
