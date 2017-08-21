package ve.needforock.contacts;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import ve.needforock.contacts.adapters.ContactAdapter;
import ve.needforock.contacts.models.Contact;

import static android.app.Activity.RESULT_OK;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements ContactClickListener{

    public static final int RC_REVIEW = 343;
    private ContactAdapter contactAdapter;
    private RecyclerView recyclerView;
    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.contactRv);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

     /*   for (int i = 0; i < 20; i++) {
            Contact contactItem = new Contact();
            contactItem.setName(String.valueOf(i));

            contactItem.save();

        }
*/
        contactAdapter = new ContactAdapter(this);
        recyclerView.setAdapter(contactAdapter);



    }

    public void updateList(Contact contact){
        contactAdapter.update(contact);

    }



    @Override
    public void clickedId(final long id) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getContext(), ContactDetailsActivity.class);
                intent.putExtra("id", id);
                startActivityForResult(intent, RC_REVIEW);
            }
        },200);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RC_REVIEW == requestCode) {
            if (RESULT_OK == resultCode) {
                long id = data.getLongExtra("ID", 0);
                updateIdList(id);
                Toast.makeText(getContext(), "Contacto Eliminado", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void updateIdList(long id){
        contactAdapter.updateIdDeleted(id);
    }


}
