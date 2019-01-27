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
import com.example.android.networkdevicesdatabase.FullScreenActivity;
import com.example.android.networkdevicesdatabase.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class TopologyFragment extends Fragment {

    private RecyclerView topologyRV;
    private FirebaseRecyclerAdapter adapter;
    private GridLayoutManager layoutManager;
    private Parcelable listState;


    public static TopologyFragment newInstance() {
        TopologyFragment fragment = new TopologyFragment();
        return fragment;
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
        outState.putParcelable("ListState", topologyRV.getLayoutManager().onSaveInstanceState());

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            listState = savedInstanceState.getParcelable("ListState");
            topologyRV.getLayoutManager().onRestoreInstanceState(listState);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_topology, container, false);
        /*if (savedInstanceState != null) {
            mPosition = savedInstanceState.getInt(SOME_VALUE_KEY);
            // Do something with value if needed
        }*/

        layoutManager = new GridLayoutManager(getActivity(), 1);
        topologyRV = (RecyclerView) view.findViewById(R.id.rv_topology);
        //RecyclerView.LayoutManager mTopology = new GridLayoutManager(getActivity(), 2);
        topologyRV.setLayoutManager(layoutManager);

        getData();

        return view;
    }


    private void getData() {

        //FirebaseFirestore rootRef = FirebaseFirestore.getInstance();


        final com.google.firebase.database.Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("topology")
                .limitToLast(50);

        final FirebaseRecyclerOptions<Device> options = new FirebaseRecyclerOptions.Builder<Device>()
                .setQuery(query, Device.class)
                .build();

        class DevicesViewHolder extends RecyclerView.ViewHolder {

            private View view;
            private ImageView imageView;


            public DevicesViewHolder(View itemView) {
                super(itemView);
                view = itemView;

                imageView = (ImageView) itemView.findViewById(R.id.thumb_image_view);
            }

            void setTopologyName(String topologyName) {

                TextView name;
                name = (TextView) itemView.findViewById(R.id.thumb_text_view);
                name.setText(topologyName);
            }
        }


        adapter = new FirebaseRecyclerAdapter<Device, DevicesViewHolder>(options) {

            @Override
            public void onBindViewHolder(@NonNull final DevicesViewHolder holder, final int position,
                                         @NonNull final Device device) {

                holder.setTopologyName(device.getTopologyName());
                Picasso.with(getActivity()).load(device.getTopologyImage()).into(holder.imageView);
                //holder.view.setId(position);

                holder.view.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        Context context = getActivity();
                        Intent intent = new Intent(context, FullScreenActivity.class);

                        intent.putExtra("topologyName", device.getTopologyName());
                        intent.putExtra("topologyImage", device.getTopologyImage());
                        intent.putExtra("position", position);

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
        topologyRV.setAdapter(adapter);


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

