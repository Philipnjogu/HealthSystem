package com.example.healthsystem;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.healthsystem.R;
import com.example.healthsystem.adapters.AdminNotRecyclerViewAdapter;
import com.example.healthsystem.fragments.dialogs.AddNotificationDialog;
import com.example.healthsystem.models.Notification;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class NotificationsFragment extends Fragment {
    private static final String TAG = "NotificationsFragment";
    private RecyclerView notificationsRV;

    private FirebaseFirestore mDb;

    private List<Notification> notificationList;
    private AdminNotRecyclerViewAdapter adapter;

    public NotificationsFragment() {
        mDb = FirebaseFirestore.getInstance();
        notificationList = new ArrayList<>();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.admin_fragment_notifications_for_users, container, false);

        notificationsRV = view.findViewById(R.id.notifications_rv);
        notificationsRV.setLayoutManager(new LinearLayoutManager(getActivity()));
        notificationsRV.setHasFixedSize(true);

        adapter = new AdminNotRecyclerViewAdapter(notificationList);
        notificationsRV.setAdapter(adapter);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        notificationList.clear();
        mDb.collection("notification")
                .addSnapshotListener(
                        (queryDocumentSnapshots, e) -> {

                            if (e != null) {
                                Log.e(TAG, "onViewCreated: Getting Notifications", e);
                                return;
                            }

                            if (queryDocumentSnapshots.isEmpty()) {
                                Toast.makeText(getActivity(), "No notifications", Toast.LENGTH_SHORT).show();
                            } else {
                                for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {
                                    Notification notification = snapshot.toObject(Notification.class);
                                    notification.setId(snapshot.getId());

                                    notificationList.add(notification);
                                    adapter.notifyDataSetChanged();
                                }
                            }

                        }
                );
    }
}
