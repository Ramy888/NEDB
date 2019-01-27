package com.example.android.networkdevicesdatabase.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.networkdevicesdatabase.Data.Device;
import com.example.android.networkdevicesdatabase.DetailActivity;
import com.example.android.networkdevicesdatabase.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class HuaweiFragment extends Fragment {

    private RecyclerView huaweiRV;
    private FirebaseRecyclerAdapter adapter;
    private Parcelable mPosition;
    private GridLayoutManager layoutManager;
    private final String KEY_RECYCLER_STATE = "recycler_state";
    private Bundle mSavedInstanceState;
    private Parcelable listState;


    public static HuaweiFragment newInstance() {
        HuaweiFragment fragment = new HuaweiFragment();
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_huawei_fragment, container, false);

        layoutManager = new GridLayoutManager(getActivity(), 1);
        huaweiRV = (RecyclerView) view.findViewById(R.id.rv_huawei);
        //RecyclerView.LayoutManager mHuawei = new GridLayoutManager(getActivity(), 2);
        huaweiRV.setLayoutManager(layoutManager);

        getData();

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        // Save list state
        //mPosition = layoutManager.findFirstCompletelyVisibleItemPosition();
        //outState.putInt(SOME_VALUE_KEY, mPosition);
        // Save list state
        //mPosition = layoutManager.onSaveInstanceState();
        //outState.putParcelable(SOME_VALUE_KEY, mPosition);
        super.onSaveInstanceState(outState);
        outState.putParcelable("ListState", huaweiRV.getLayoutManager().onSaveInstanceState());

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            listState = savedInstanceState.getParcelable("ListState");
            huaweiRV.getLayoutManager().onRestoreInstanceState(listState);
        }
    }


    private void getData() {

        //FirebaseFirebase rootRef = FirebaseFirebase.getInstance();

        final com.google.firebase.database.Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("Huawei")
                .limitToLast(50);

        final FirebaseRecyclerOptions<Device> options = new FirebaseRecyclerOptions.Builder<Device>()
                .setQuery(query, Device.class)
                .build();


        class DevicesViewHolder extends RecyclerView.ViewHolder {

            private Context mContext;
            private View view;
            private ImageView imageView;


            public DevicesViewHolder(View itemView) {
                super(itemView);
                view = itemView;

                imageView = (ImageView) itemView.findViewById(R.id.thumb_image_view);
            }

            void setDeviceName(String deviceName) {

                TextView name;
                name = (TextView) itemView.findViewById(R.id.thumb_text_view);
                name.setText(deviceName);
            }
        }


        adapter = new FirebaseRecyclerAdapter<Device, DevicesViewHolder>(options) {

            @Override
            public void onBindViewHolder(@NonNull DevicesViewHolder holder, int position,
                                         @NonNull final Device device) {

                holder.setDeviceName(device.getDeviceName());
                Picasso.with(getActivity()).load(device.getDeviceImage()).into(holder.imageView);

                holder.view.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        Context context = getActivity();
                        Intent intent = new Intent(context, DetailActivity.class);

                        intent.putExtra("deviceName", device.getDeviceName());
                        intent.putExtra("deviceImage", device.getDeviceImage());
                        intent.putExtra("deviceInfo_1", device.getDeviceInfo_1());
                        intent.putExtra("deviceInfo_2", device.getDeviceInfo_2());
                        intent.putExtra("deviceInfo_3", device.getDeviceInfo_3());
                        intent.putExtra("deviceInfo_4", device.getDeviceInfo_4());
                        intent.putExtra("deviceType", device.getDeviceType());
                        intent.putExtra("videoUrl", device.getVideoURL());
                        intent.putExtra("pdfUrl", device.getPdfUrl());
                        startActivity(intent);
                    }
                });

            }


            @NonNull
            @Override
            public DevicesViewHolder onCreateViewHolder(@NonNull ViewGroup
                                                                parent,
                                                        int viewType) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                View view = inflater.inflate(R.layout.card_layout, parent, false);
                return new DevicesViewHolder(view);
            }
        };

        adapter.notifyDataSetChanged();
        huaweiRV.setAdapter(adapter);

            /* SwipeController swipeController = new SwipeController();
        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeController);
        itemTouchhelper.attachToRecyclerView(ciscoRV); */
    }

    @Override
    public void onStart() {
        super.onStart();
        if (isNetworkConnected())
            adapter.startListening();
        else
            Toast.makeText(getContext(), getString(R.string.no_internet), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }


    private boolean isNetworkConnected() {
        ConnectivityManager cm =
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();

    }

}

