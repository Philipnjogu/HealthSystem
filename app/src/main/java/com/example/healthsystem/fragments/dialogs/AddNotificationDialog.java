package com.example.healthsystem.fragments.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.healthsystem.R;
import com.example.healthsystem.models.Notification;

public class AddNotificationDialog extends AppCompatDialogFragment {

    private AddNotificationListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_add_notification, null);
        Spinner typeSpinner = view.findViewById(R.id.not_type_spinner);
        EditText notDescET = view.findViewById(R.id.not_desc_et);

        builder.setView(view)
                .setTitle("Add Notification")
                .setPositiveButton("Add Notification", (dialog, which) -> {

                    String type = typeSpinner.getSelectedItem().toString().trim();
                    String desc = notDescET.getText().toString().trim();

                    if(type.equalsIgnoreCase("-- Type --")){
                        Toast.makeText(getActivity(), "Select type", Toast.LENGTH_SHORT).show();
                        return;
                    }else if(desc.isEmpty()){
                        notDescET.setError("Description Required");
                        notDescET.requestFocus();
                        return;
                    }

                    Notification notification = new Notification(type, desc);

                    listener.addNotification(notification);


                })
                .setNegativeButton("Cancel", (dialog, which) -> {
                    Toast.makeText(getActivity(), "Operation Cancelled", Toast.LENGTH_SHORT).show();
                });


        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof AddNotificationListener)
            listener = (AddNotificationListener) context;
        else
            throw new ClassCastException(context.toString() + " must implement AddNotificationListener");
    }

    public interface AddNotificationListener {
        void addNotification(Notification notification);
    }
}
