package ve.needforock.contacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ve.needforock.contacts.models.Contact;

public class ContactDetailsActivity extends AppCompatActivity {

    TextView name;
    TextView phone;
    TextView mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);



        final long id = getIntent().getLongExtra("id",0);


        Contact retreivedContact = Contact.findById(Contact.class , id);

        //Toast.makeText(this, String.valueOf(id), Toast.LENGTH_SHORT).show();
        /*Toast.makeText(this, retreivedContact.getName(), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, retreivedContact.getPhone(), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, retreivedContact.getEmail(), Toast.LENGTH_SHORT).show();*/

        name = (TextView) findViewById(R.id.contactNameTv);
        phone = (TextView) findViewById(R.id.contactPhoneTv);
        mail = (TextView) findViewById(R.id.contactMailTv);
        Button deleteBtn = (Button) findViewById(R.id.deleteContactBtn);
        Button cancelBtn = (Button) findViewById(R.id.cancelBtn);


        getSupportActionBar().setTitle(retreivedContact.getName());
        name.setText(retreivedContact.getName());
        phone.setText(retreivedContact.getPhone());
        mail.setText(retreivedContact.getEmail());


        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("ID", id);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }


}
