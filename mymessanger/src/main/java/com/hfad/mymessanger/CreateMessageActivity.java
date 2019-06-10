package com.hfad.mymessanger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateMessageActivity extends AppCompatActivity implements View.OnClickListener {
    Button send;
    EditText message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_message);
        send = findViewById(R.id.send);
        send.setOnClickListener(this);
        message = findViewById(R.id.message);
    }

    @Override
    public void onClick(View v) {
        String textMessage = message.getText().toString();

        /*Intent intent = new Intent(this, ReceiveMessageActivity.class);
        intent.putExtra(ReceiveMessageActivity.EXTRA_MESSAGE, textMessage);*/
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, textMessage);
        //startActivity(intent); // exception ActivityNotFoundException

        //chooser intent
        String chooserTitle = getString(R.string.chooser);
        Intent chooserIntent = Intent.createChooser(intent, chooserTitle);
        startActivity(chooserIntent);
    }
}
