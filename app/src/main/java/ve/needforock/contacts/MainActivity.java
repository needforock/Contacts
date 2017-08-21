package ve.needforock.contacts;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ve.needforock.contacts.models.Contact;

public class MainActivity extends AppCompatActivity {

    private MainActivityFragment mainActivityFragment;
    public static final String FLAG = "package ve.needforock.contacts.KEY.FLAG";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Toast.makeText(this, "OnCreate", Toast.LENGTH_SHORT).show();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mainActivityFragment = (MainActivityFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.new_contact_dialog);

                Button saveBtn = (Button) dialog.findViewById(R.id.addButton);
                saveBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText nameEt = (EditText) dialog.findViewById(R.id.nameEt);
                        String contactName = nameEt.getText().toString();
                        EditText phoneEt = (EditText) dialog.findViewById(R.id.phoneEt);
                        String contactPhone = phoneEt.getText().toString();
                        EditText emailEt = (EditText) dialog.findViewById(R.id.emailEt);
                        String contactEmail = emailEt.getText().toString();

                        if (contactName.trim().length() > 0 && contactPhone.trim().length() > 0 && contactEmail.trim().length() > 0 && contactEmail.contains("@")) {
                            Contact contact = new Contact();
                            contact.setName(contactName);
                            contact.setPhone(contactPhone);
                            contact.setEmail(contactEmail);
                            contact.save();

                            mainActivityFragment.updateList(contact);
                            dialog.dismiss();
                            Toast.makeText(MainActivity.this, "Contacto Agregado", Toast.LENGTH_SHORT).show();
                        } else if (contactName.trim().length() == 0 || contactPhone.trim().length() == 0 || contactEmail.trim().length() == 0) {
                            Toast.makeText(MainActivity.this, "Debe Introducir Los datos", Toast.LENGTH_SHORT).show();
                        } else if (contactEmail.contains("@")==false){
                            Toast.makeText(MainActivity.this, "Correo Invalido", Toast.LENGTH_SHORT).show();
                        }


                    }
                });
                dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                dialog.show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.settings) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}