//package com.eljebo.customer.custom_video_call;
//
//import android.Manifest;
//import android.app.AlertDialog;
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.SharedPreferences;
//import android.content.pm.PackageManager;
//import android.media.AudioAttributes;
//import android.media.AudioFocusRequest;
//import android.media.AudioManager;
//import android.os.AsyncTask;
//import android.os.Build;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
//import android.support.v4.app.ActivityCompat;
//import android.support.v4.content.ContextCompat;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.android.volley.Request;
//import com.android.volley.Response;
//import com.android.volley.RetryPolicy;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
//import com.eljebo.R;
//import com.eljebo.common.utils.Const;
//import com.eljebo.serviceprovider.video_service_call.CameraCapturerCompat;
//import com.eljebo.serviceprovider.video_service_call.Connections;
//import com.eljebo.serviceprovider.video_service_call.Constant;
//import com.eljebo.serviceprovider.video_service_call.Dialog;
//import com.gdacciaro.iOSDialog.iOSDialog;
//import com.google.gson.JsonObject;
//import com.koushikdutta.async.future.FutureCallback;
//import com.koushikdutta.ion.Ion;
//import com.twilio.video.AudioTrack;
//import com.twilio.video.CameraCapturer;
//import com.twilio.video.ConnectOptions;
//import com.twilio.video.LocalAudioTrack;
//import com.twilio.video.LocalParticipant;
//import com.twilio.video.LocalVideoTrack;
//import com.twilio.video.Participant;
//import com.twilio.video.Room;
//import com.twilio.video.RoomState;
//import com.twilio.video.TwilioException;
//import com.twilio.video.Video;
//import com.twilio.video.VideoRenderer;
//import com.twilio.video.VideoTrack;
//import com.twilio.video.VideoView;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Timer;
//import java.util.TimerTask;
//
///**
// * Created by user1 on 3/7/18.
// */
//
//public class VideoCallCustomerActivity extends AppCompatActivity {
//
//
//        private static final int CAMERA_MIC_PERMISSION_REQUEST_CODE = 1;
//        private static final String TAG = "VideoActivity";
//        /*
//         * You must provide a Twilio Access Token to connect to the Video service
//         */
//       // public static String TWILIO_ACCESS_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCIsImN0eSI6InR3aWxpby1mcGE7dj0xIn0.eyJqdGkiOiJTS2JlOGRmZjBjZTNhZmUxZjdlZTgxMDM4NzBlNmMzMDBjLTE1MDkxMDUyNjYiLCJncmFudHMiOnsiaWRlbnRpdHkiOiIyNSIsInZpZGVvIjp7InJvb20iOiJhbmRyb2lkIn19LCJpYXQiOjE1MDkxMDUyNjYsImV4cCI6MTUwOTEwODg2NiwiaXNzIjoiU0tiZThkZmYwY2UzYWZlMWY3ZWU4MTAzODcwZTZjMzAwYyIsInN1YiI6IkFDOTE4ODEzM2E4OTgyOWVkMjI0ODJlMjQ0OGQ1MDBjYzcifQ.xv7CFAZrWKTAewr6Xg4N5NGFvJxwNSPfeLCSwa2ftkA";
//
//
//    //public static String TWILIO_ACCESS_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCIsImN0eSI6InR3aWxpby1mcGE7dj0xIn0.eyJqdGkiOiJTS2JlOGRmZjBjZTNhZmUxZjdlZTgxMDM4NzBlNmMzMDBjLTE1MDkxMDUyNjYiLCJncmFudHMiOnsiaWRlbnRpdHkiOiIyNSIsInZpZGVvIjp7InJvb20iOiJhbmRyb2lkIn19LCJpYXQiOjE1MDkxMDUyNjYsImV4cCI6MTUwOTEwODg2NiwiaXNzIjoiU0tiZThkZmYwY2UzYWZlMWY3ZWU4MTAzODcwZTZjMzAwYyIsInN1YiI6IkFDOTE4ODEzM2E4OTgyOWVkMjI0ODJlMjQ0OGQ1MDBjYzcifQ.xv7CFAZrWKTAewr6Xg4N5NGFvJxwNSPfeLCSwa2ftkA";
//    public static String TWILIO_ACCESS_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiIsImN0eSI6InR3aWxpby1mcGE7dj0xIn0.eyJqdGkiOiJTS2NkZjIzYjBmNGUxYWIyNjJmYTE1YTBlMzM0NzU2MmIyLTE1MzA2MTk4NDkiLCJpc3MiOiJTS2NkZjIzYjBmNGUxYWIyNjJmYTE1YTBlMzM0NzU2MmIyIiwic3ViIjoiQUNlMjExMjA2OTM1OTJmMDI3ZTRkNmRkMDY5OGQzYjZjZCIsImV4cCI6MTUzMDYyMzQ0OSwiZ3JhbnRzIjp7ImlkZW50aXR5IjoiZWxqZWJvLXVzZXIiLCJ2aWRlbyI6eyJyb29tIjoiMTIzLTEwMSJ9fX0.IO2Wzd5hX5O8EwiRVBuARPFEZwta8McXrkzYvVvvq4U";
//
//
//    /*
//         * Access token used to connect. This field will be set either from the console generated token
//         * or the request to the token server.
//         */
//        private String accessToken;
//
//        /*
//         * A Room represents communication between a local participant and one or more participants.
//         */
//        private Room room;
//        private LocalParticipant localParticipant;
//
//        /*
//         * A VideoView receives frames from a local or remote video track and renders them
//         * to an associated view.
//         */
//        private VideoView primaryVideoView;
//        private VideoView thumbnailVideoView;
//
//        /*
//         * Android application UI elements
//         */
//        private TextView videoStatusTextView;
//        private CameraCapturerCompat cameraCapturerCompat;
//        private LocalAudioTrack localAudioTrack;
//        private LocalVideoTrack localVideoTrack;
//        private FloatingActionButton connectActionFab;
//        private FloatingActionButton switchCameraActionFab;
//        private FloatingActionButton localVideoActionFab;
//        private FloatingActionButton muteActionFab;
//        private android.support.v7.app.AlertDialog alertDialog;
//        private AudioManager audioManager;
//        private String participantIdentity;
//
//        private int previousAudioMode;
//        private boolean previousMicrophoneMute;
//        private VideoRenderer localVideoView;
//        private boolean disconnectedFromOnDestroy;
//
//        String[] ids;
//
//        String roomId, userID, id, Auth, userType, vdo_id;
//        Timer newTimerTask;
//        int seconds = 60, minute = 2;
//        TextView videoTime;
//
//        private String getServiceProviderUserId = "", loginUserId = "", roomIds = "";
//
//        @Override
//        protected void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            setContentView(R.layout.activity_video);
//
//        /*params.put("user_id", Const.loadData(getActivity(), "loginUserId"));
//        params.put("token", Const.loadData(getActivity(), "loginUserToken"));*/
//
//        /*roomId = getIntent().getStringExtra("roomID");
//        userID = getIntent().getStringExtra("id");
//        vdo_id = getIntent().getStringExtra("vdo_id");*/
//
//            getServiceProviderUserId = Const.loadData(VideoCallCustomerActivity.this, "getServiceProviderUserId");
//            loginUserId = Const.loadData(VideoCallCustomerActivity.this, "loginUserId");
//
//            if (Const.ROLE_PROVIDER == 2){
//                getServiceProviderUserId = "172";
//            } else {
//
//            }
//            Log.e("serviceIds==>", getServiceProviderUserId
//                    +"loginUserId==> " +loginUserId);
//
//            roomIds = loginUserId+"-"+getServiceProviderUserId;
//
//            SharedPreferences prefs = getSharedPreferences(Constant.MY_PREFS_NAME, MODE_PRIVATE);
//            id =  prefs.getString("GetID","");
//            Auth =  prefs.getString("Auth","");
//            userType =  prefs.getString("userType","");
//            // ids =  roomId.split("_");
//
//            videoTime       = (TextView)findViewById(R.id.videoTime);
//
//            primaryVideoView = (VideoView) findViewById(R.id.primary_video_view);
//            thumbnailVideoView = (VideoView) findViewById(R.id.thumbnail_video_view);
//            videoStatusTextView = (TextView) findViewById(R.id.video_status_textview);
//
//            connectActionFab = (FloatingActionButton) findViewById(R.id.connect_action_fab);
//            switchCameraActionFab = (FloatingActionButton) findViewById(R.id.switch_camera_action_fab);
//            localVideoActionFab = (FloatingActionButton) findViewById(R.id.local_video_action_fab);
//            muteActionFab = (FloatingActionButton) findViewById(R.id.mute_action_fab);
//
//        /*
//         * Enable changing the volume using the up/down keys during a conversation
//         */
//            setVolumeControlStream(AudioManager.STREAM_VOICE_CALL);
//
//        /*
//         * Needed for setting/abandoning audio focus during call
//         */
//            audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
//
//        /*
//         * Check camera and microphone permissions. Needed in Android M.
//         */
//
//
//            if (!checkPermissionForCameraAndMicrophone()) {
//                requestPermissionForCameraAndMicrophone();
//            } else {
//                createAudioAndVideoTracks();
//                setAccessToken();
//            }
//
//        /*
//         * Set the initial state of the UI
//         */
//            intializeUI();
//
//
//            getAccessToken();
//
//            Log.e("id>>>",id+"");
//
//       /* if(id.equals(ids[1].toString())){
//
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//
//                    if(room!=null){
//                        if(room.getParticipants().size()>=1){
//                            // Participate Connected
//                            Log.e("EventHappend","SaveVideo");
//                           // new SendVideoDateUpdate().execute();
//                        }else{
//                            Log.e("SaveVideoDate","CancleVideo");
//                            // Participate not connected
//                            //new CancleVideoCall().execute();
//                        }
//                    }
//                }
//            }, 3000);
//        }*/
//
//
//
//
//            newTimerTask = new Timer();
//
//            newTimerTask.scheduleAtFixedRate(new TimerTask() {
//                @Override
//                public void run() {
//
//                    // 30:0
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//
//                            seconds = seconds - 1;
//                            if(seconds==0){
//                                seconds = 60;
//                                minute = minute - 1;
//                            }
//
//                            if(minute<0){
//
//                            }else{
//
//                                String t = System.out.format("%02d\n", minute)+":"+ System.out.format("%02d\n", seconds)+" s";
//                                videoTime.setText(minute+":"+seconds+" s");
//                            }
//
//
//                        }
//                    });
//                }
//            }, 0, 1000);
//
//
//        /*new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//               if(id.equals(ids[0].toString())){
//                    if(room!=null){
//                      //  new UpdateWalletData().execute();
//                    }
//                }else{
//                    if(room!=null){
//
//                        room.disconnect();
//                        newTimerTask.cancel();
//                       *//* Intent i = new Intent(VideoActivity.this, promtSaveVideo.class);
//                        i.putExtra("type","2");
//                        i.putExtra("vdo_id",vdo_id);
//                        i.putExtra("celebNAME",celebNAME);
//                        finish();
//                        startActivity(i);*//*
//                    }
//                }
//            }
//        }, 180000);*/
//
//
//        }
//
//        @Override
//        public void onRequestPermissionsResult(int requestCode,
//                                               @NonNull String[] permissions,
//                                               @NonNull int[] grantResults) {
//            if (requestCode == CAMERA_MIC_PERMISSION_REQUEST_CODE) {
//                boolean cameraAndMicPermissionGranted = true;
//
//                for (int grantResult : grantResults) {
//                    cameraAndMicPermissionGranted &= grantResult == PackageManager.PERMISSION_GRANTED;
//                }
//
//                if (cameraAndMicPermissionGranted) {
//                    createAudioAndVideoTracks();
//                    setAccessToken();
//                } else {
//                    Toast.makeText(this,
//                            R.string.permissions_needed,
//                            Toast.LENGTH_LONG).show();
//                }
//            }
//        }
//
//        @Override
//        protected  void onResume() {
//            super.onResume();
//        /*
//         * If the local video track was released when the app was put in the background, recreate.
//         */
//            if (localVideoTrack == null && checkPermissionForCameraAndMicrophone()) {
//                localVideoTrack = LocalVideoTrack.create(this, true, cameraCapturerCompat.getVideoCapturer());
//                localVideoTrack.addRenderer(localVideoView);
//
//            /*
//             * If connected to a Room then share the local video track.
//             */
//                if (localParticipant != null) {
//                    localParticipant.addVideoTrack(localVideoTrack);
//                }
//            }
//        }
//
//        @Override
//        protected void onPause() {
//        /*
//         * Release the local video track before going in the background. This ensures that the
//         * camera can be used by other applications while this app is in the background.
//         */
//            if (localVideoTrack != null) {
//            /*
//             * If this local video track is being shared in a Room, remove from local
//             * participant before releasing the video track. Participants will be notified that
//             * the track has been removed.
//             */
//                if (localParticipant != null) {
//                    localParticipant.removeVideoTrack(localVideoTrack);
//                }
//
//                localVideoTrack.release();
//                localVideoTrack = null;
//            }
//            super.onPause();
//        }
//
//        @Override
//        protected void onDestroy() {
//        /*
//         * Always disconnect from the room before leaving the Activity to
//         * ensure any memory allocated to the Room resource is freed.
//         */
//            if (room != null && room.getState() != RoomState.DISCONNECTED) {
//                room.disconnect();
//                disconnectedFromOnDestroy = true;
//            }
//
//
//        /*
//         * Release the local audio and video tracks ensuring any memory allocated to audio
//         * or video is freed.
//         */
//            if (localAudioTrack != null) {
//                localAudioTrack.release();
//                localAudioTrack = null;
//            }
//            if (localVideoTrack != null) {
//                localVideoTrack.release();
//                localVideoTrack = null;
//            }
//
//            super.onDestroy();
//        }
//
//        private boolean checkPermissionForCameraAndMicrophone(){
//            int resultCamera = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
//            int resultMic = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO);
//            return resultCamera == PackageManager.PERMISSION_GRANTED &&
//                    resultMic == PackageManager.PERMISSION_GRANTED;
//        }
//
//        private void requestPermissionForCameraAndMicrophone(){
//            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA) ||
//                    ActivityCompat.shouldShowRequestPermissionRationale(this,
//                            Manifest.permission.RECORD_AUDIO)) {
//                Toast.makeText(this,
//                        R.string.permissions_needed,
//                        Toast.LENGTH_LONG).show();
//            } else {
//                ActivityCompat.requestPermissions(
//                        this,
//                        new String[]{Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO},
//                        CAMERA_MIC_PERMISSION_REQUEST_CODE);
//            }
//        }
//
//        private void createAudioAndVideoTracks() {
//            // Share your microphone
//            localAudioTrack = LocalAudioTrack.create(this, true);
//
//            // Share your camera
//            cameraCapturerCompat = new CameraCapturerCompat(this,
//                    getAvailableCameraSource());
//            localVideoTrack = LocalVideoTrack.create(this, true,
//                    cameraCapturerCompat.getVideoCapturer());
//            primaryVideoView.setMirror(true);
//            localVideoTrack.addRenderer(primaryVideoView);
//            localVideoView = primaryVideoView;
//        }
//
//        private CameraCapturer.CameraSource getAvailableCameraSource() {
//            return (CameraCapturer.isSourceAvailable(CameraCapturer.CameraSource.FRONT_CAMERA)) ?
//                    (CameraCapturer.CameraSource.FRONT_CAMERA) :
//                    (CameraCapturer.CameraSource.BACK_CAMERA);
//        }
//
//        private void setAccessToken() {
//            // OPTION 1- Generate an access token from the getting started portal
//            // https://www.twilio.com/console/video/dev-tools/testing-tools
//            this.accessToken = TWILIO_ACCESS_TOKEN;
//
//            // OPTION 2- Retrieve an access token from your own web app
//            // retrieveAccessTokenfromServer();
//        }
//
//        private void connectToRoom(String roomName) {
//
//            Log.e("connectToRoom", "connectToRoom==> " +roomName);
//
//            configureAudio(true);
//            ConnectOptions.Builder connectOptionsBuilder = new
//                    ConnectOptions.Builder(accessToken)
//                    .roomName(roomName);
//        /*
//         * Add local audio track to connect options to share with participants.
//         */
//            if (localAudioTrack != null) {
//                connectOptionsBuilder
//                        .audioTracks(Collections.singletonList(localAudioTrack));
//            }
//        /*
//         * Add local video track to connect options to share with participants.
//         */
//            if (localVideoTrack != null) {
//                connectOptionsBuilder.videoTracks(Collections.singletonList(localVideoTrack));
//            }
//            room = Video.connect(this, connectOptionsBuilder.build(), roomListener());
//            setDisconnectAction();
//        }
//
//        /*
//         * The initial state when there is no active room.
//         */
//        private void intializeUI() {
//
//        /*connectActionFab.setImageDrawable(ContextCompat.getDrawable(this,
//                R.drawable.ic_call_white_24px));
//        connectActionFab.show();
//        connectActionFab.setOnClickListener(connectActionClickListener());*/
//
//            switchCameraActionFab.show();
//            switchCameraActionFab.setOnClickListener(switchCameraClickListener());
//            localVideoActionFab.show();
//            localVideoActionFab.setOnClickListener(localVideoClickListener());
//            muteActionFab.show();
//            muteActionFab.setOnClickListener(muteClickListener());
//
//
//
//        }
//
//        /*
//         * The actions performed during disconnect.
//         */
//        private void setDisconnectAction() {
//            connectActionFab.setImageDrawable(ContextCompat.getDrawable(this,
//                    R.drawable.ic_call_end_white_24px));
//            connectActionFab.hide();
//            connectActionFab.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                }
//            });
//
//            ///connectActionFab.setOnClickListener(disconnectClickListener());
//        }
//
//        /*
//         * Creates an connect UI dialog
//         */
//        private void showConnectDialog() {
//            EditText roomEditText = new EditText(this);
//            alertDialog = Dialog.createConnectDialog(roomEditText,
//                    connectClickListener(roomEditText), cancelConnectDialogClickListener(), this);
//            alertDialog.show();
//        }
//
//        /*
//         * Called when participant joins the room
//         */
//        private void addParticipant(Participant participant) {
//        /*
//         * This app only displays video for one additional participant per Room
//         */
//            if (thumbnailVideoView.getVisibility() == View.VISIBLE) {
//                Snackbar.make(connectActionFab,
//                        "Multiple participants are not currently support in this UI",
//                        Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//                return;
//            }
//            participantIdentity = participant.getIdentity();
//            //participant.getSid();
//            videoStatusTextView.setText("Participant "+ participantIdentity + " joined");
//
//        /*
//         * Add participant renderer
//         */
//            if (participant.getVideoTracks().size() > 0) {
//                addParticipantVideo(participant.getVideoTracks().get(0));
//            }
//
//        /*
//         * Start listening for participant events
//         */
//            participant.setListener(participantListener());
//        }
//
//        /*
//         * Set primary view as renderer for participant video track
//         */
//        private void addParticipantVideo(VideoTrack videoTrack) {
//            moveLocalVideoToThumbnailView();
//            primaryVideoView.setMirror(false);
//            videoTrack.addRenderer(primaryVideoView);
//        }
//
//        private void moveLocalVideoToThumbnailView() {
//            if (thumbnailVideoView.getVisibility() == View.GONE) {
//                thumbnailVideoView.setVisibility(View.VISIBLE);
//                if(localVideoTrack!=null){
//
//                    localVideoTrack.removeRenderer(primaryVideoView);
//                    localVideoTrack.addRenderer(thumbnailVideoView);
//                    localVideoView = thumbnailVideoView;
//                    thumbnailVideoView.setMirror(cameraCapturerCompat.getCameraSource() ==
//                            CameraCapturer.CameraSource.FRONT_CAMERA);
//                }
//
//            }
//        }
//
//        /*
//         * Called when participant leaves the room
//         */
//        private void removeParticipant(Participant participant) {
//            videoStatusTextView.setText("Participant "+participant.getIdentity()+ " left.");
//            if (!participant.getIdentity().equals(participantIdentity)) {
//                return;
//            }
//
//        /*
//         * Remove participant renderer
//         */
//            if (participant.getVideoTracks().size() > 0) {
//                removeParticipantVideo(participant.getVideoTracks().get(0));
//            }
//            moveLocalVideoToPrimaryView();
//        }
//
//        private void removeParticipantVideo(VideoTrack videoTrack) {
//            videoTrack.removeRenderer(primaryVideoView);
//        }
//
//        private void moveLocalVideoToPrimaryView() {
//            if (thumbnailVideoView.getVisibility() == View.VISIBLE) {
//                thumbnailVideoView.setVisibility(View.GONE);
//                if (localVideoTrack != null) {
//                    localVideoTrack.removeRenderer(thumbnailVideoView);
//                    localVideoTrack.addRenderer(primaryVideoView);
//                }
//                localVideoView = primaryVideoView;
//                primaryVideoView.setMirror(cameraCapturerCompat.getCameraSource() ==
//                        CameraCapturer.CameraSource.FRONT_CAMERA);
//            }
//        }
//
//        /*
//         * Room events listener
//         */
//        private Room.Listener roomListener() {
//            return new Room.Listener() {
//                @Override
//                public void onConnected(Room room) {
//                    localParticipant = room.getLocalParticipant();
//                    videoStatusTextView.setText("Connected to " + room.getName());
//
//                    for (Participant participant : room.getParticipants()) {
//                        addParticipant(participant);
//                        break;
//                    }
//                }
//
//                @Override
//                public void onConnectFailure(Room room, TwilioException e) {
//                    videoStatusTextView.setText("Failed to connect");
//                    configureAudio(false);
//                }
//
//                @Override
//                public void onDisconnected(Room room, TwilioException e) {
//                    localParticipant = null;
//                    videoStatusTextView.setText("Disconnected from " + room.getName());
//                    VideoCallCustomerActivity.this.room = null;
//                    // Only reinitialize the UI if disconnect was not called from onDestroy()
//                    if (!disconnectedFromOnDestroy) {
//                        configureAudio(false);
//                        intializeUI();
//                        moveLocalVideoToPrimaryView();
//                    }
//                }
//
//                @Override
//                public void onParticipantConnected(Room room, Participant participant) {
//                    addParticipant(participant);
//
//
//                }
//
//                @Override
//                public void onParticipantDisconnected(Room room, Participant participant) {
//                    removeParticipant(participant);
//                /*room.*/
//                }
//
//                @Override
//                public void onRecordingStarted(Room room) {
//                /*
//                 * Indicates when media shared to a Room is being recorded. Note that
//                 * recording is only available in our Group Rooms developer preview.
//                 */
//
//                    Log.d(TAG, "onRecordingStarted");
//                }
//
//                @Override
//                public void onRecordingStopped(Room room) {
//                /*
//                 * Indicates when media shared to a Room is no longer being recorded. Note that
//                 * recording is only available in our Group Rooms developer preview.
//                 */
//                    Log.d(TAG, "onRecordingStopped");
//                }
//            };
//        }
//
//        private Participant.Listener participantListener() {
//            return new Participant.Listener() {
//                @Override
//                public void onAudioTrackAdded(Participant participant, AudioTrack audioTrack) {
//                    videoStatusTextView.setText("onAudioTrackAdded");
//                }
//
//                @Override
//                public void onAudioTrackRemoved(Participant participant, AudioTrack audioTrack) {
//                    videoStatusTextView.setText("onAudioTrackRemoved");
//                }
//
//                @Override
//                public void onVideoTrackAdded(Participant participant, VideoTrack videoTrack) {
//                    videoStatusTextView.setText("onVideoTrackAdded");
//                    addParticipantVideo(videoTrack);
//                }
//
//                @Override
//                public void onVideoTrackRemoved(Participant participant, VideoTrack videoTrack) {
//                    videoStatusTextView.setText("onVideoTrackRemoved");
//                    removeParticipantVideo(videoTrack);
//                }
//
//                @Override
//                public void onAudioTrackEnabled(Participant participant, AudioTrack audioTrack) {
//
//                }
//
//                @Override
//                public void onAudioTrackDisabled(Participant participant, AudioTrack audioTrack) {
//
//                }
//
//                @Override
//                public void onVideoTrackEnabled(Participant participant, VideoTrack videoTrack) {
//
//                }
//
//                @Override
//                public void onVideoTrackDisabled(Participant participant, VideoTrack videoTrack) {
//
//                }
//            };
//        }
//
//        private DialogInterface.OnClickListener connectClickListener(final EditText roomEditText) {
//            return new DialogInterface.OnClickListener() {
//
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                /*
//                 * Connect to room
//                 */
//
//
//
//                /* Call it after chat ends */
//                    //connectToRoom(roomEditText.getText().toString());
//
//
//
//                }
//            };
//        }
//
//        private View.OnClickListener disconnectClickListener() {
//            return new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                /*
//                 * Disconnect from room
//                 */
//                    if (room != null) {
//                        room.disconnect();
//                    }
//                    intializeUI();
//                }
//            };
//        }
//
//        private View.OnClickListener connectActionClickListener() {
//            return new View.OnClickListener(){
//                @Override
//                public void onClick(View v) {
//                    showConnectDialog();
//                }
//            };
//        }
//
//        private DialogInterface.OnClickListener cancelConnectDialogClickListener() {
//            return new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    intializeUI();
//                    alertDialog.dismiss();
//                }
//            };
//        }
//
//        private View.OnClickListener switchCameraClickListener() {
//            return new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (cameraCapturerCompat != null) {
//                        CameraCapturer.CameraSource cameraSource = cameraCapturerCompat.getCameraSource();
//                        cameraCapturerCompat.switchCamera();
//                        if (thumbnailVideoView.getVisibility() == View.VISIBLE) {
//                            thumbnailVideoView.setMirror(cameraSource == CameraCapturer.CameraSource.BACK_CAMERA);
//                        } else {
//                            primaryVideoView.setMirror(cameraSource == CameraCapturer.CameraSource.BACK_CAMERA);
//                        }
//                    }
//                }
//            };
//        }
//
//        private View.OnClickListener localVideoClickListener() {
//            return new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                /*
//                 * Enable/disable the local video track
//                 */
//                    if (localVideoTrack != null) {
//                        boolean enable = !localVideoTrack.isEnabled();
//                        localVideoTrack.enable(enable);
//                        int icon;
//                        if (enable) {
//                            icon = R.drawable.ic_videocam_green_24px;
//                            switchCameraActionFab.show();
//                        } else {
//                            icon = R.drawable.ic_videocam_off_red_24px;
//                            switchCameraActionFab.hide();
//                        }
//                        localVideoActionFab.setImageDrawable(
//                                ContextCompat.getDrawable(VideoCallCustomerActivity.this, icon));
//                    }
//                }
//            };
//        }
//
//        private View.OnClickListener muteClickListener() {
//            return new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                /*
//                 * Enable/disable the local audio track. The results of this operation are
//                 * signaled to other Participants in the same Room. When an audio track is
//                 * disabled, the audio is muted.
//                 */
//                    if (localAudioTrack != null) {
//                        boolean enable = !localAudioTrack.isEnabled();
//                        localAudioTrack.enable(enable);
//                        int icon = enable ?
//                                R.drawable.ic_mic_green_24px : R.drawable.ic_mic_off_red_24px;
//                        muteActionFab.setImageDrawable(ContextCompat.getDrawable(
//                                VideoCallCustomerActivity.this, icon));
//                    }
//                }
//            };
//        }
//
//        private void retrieveAccessTokenfromServer() {
//            Ion.with(this)
//                    .load("http://localhost:8000/token.php")
//                    .asJsonObject()
//                    .setCallback(new FutureCallback<JsonObject>() {
//                        @Override
//                        public void onCompleted(Exception e, JsonObject result) {
//                            if (e == null) {
//                                VideoCallCustomerActivity.this.accessToken = result.get("token").getAsString();
//                            } else {
//                                Toast.makeText(VideoCallCustomerActivity.this,
//                                        R.string.error_retrieving_access_token, Toast.LENGTH_LONG)
//                                        .show();
//                            }
//                        }
//                    });
//        }
//
//        private void configureAudio(boolean enable) {
//            if (enable) {
//                previousAudioMode = audioManager.getMode();
//                // Request audio focus before making any device switch
//                requestAudioFocus();
//            /*
//             * Use MODE_IN_COMMUNICATION as the default audio mode. It is required
//             * to be in this mode when playout and/or recording starts for the best
//             * possible VoIP performance. Some devices have difficulties with
//             * speaker mode if this is not set.
//             */
//                audioManager.setMode(AudioManager.MODE_IN_COMMUNICATION);
//            /*
//             * Always disable microphone mute during a WebRTC call.
//             */
//                previousMicrophoneMute = audioManager.isMicrophoneMute();
//                audioManager.setMicrophoneMute(false);
//            } else {
//                audioManager.setMode(previousAudioMode);
//                audioManager.abandonAudioFocus(null);
//                audioManager.setMicrophoneMute(previousMicrophoneMute);
//            }
//        }
//
//        private void requestAudioFocus() {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                AudioAttributes playbackAttributes = new AudioAttributes.Builder()
//                        .setUsage(AudioAttributes.USAGE_VOICE_COMMUNICATION)
//                        .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
//                        .build();
//                AudioFocusRequest focusRequest =
//                        new AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN_TRANSIENT)
//                                .setAudioAttributes(playbackAttributes)
//                                .setAcceptsDelayedFocusGain(true)
//                                .setOnAudioFocusChangeListener(
//                                        new AudioManager.OnAudioFocusChangeListener() {
//                                            @Override
//                                            public void onAudioFocusChange(int i) { }
//                                        })
//                                .build();
//                audioManager.requestAudioFocus(focusRequest);
//            } else {
//                audioManager.requestAudioFocus(null, AudioManager.STREAM_VOICE_CALL,
//                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
//            }
//        }
//
//
//
//
//
//        private void getAccessToken() {
//            StringRequest strRequest = new StringRequest(Request.Method.POST,
//                    Const.NEW_BASE_URL+ Const.GetTwilioToken,
//                    new Response.Listener<String>()
//                    {
//                        @Override
//                        public void onResponse(String response)
//                        {
//                            Log.e("getAccessToken::??",response);
//                            //Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
//
//                            try {
//                                JSONObject JSONresponse = new JSONObject(response);
//                                if(JSONresponse.getString("status").equals("1")) {
//                                    TWILIO_ACCESS_TOKEN = JSONresponse.getString("access_token");
//                                    accessToken = TWILIO_ACCESS_TOKEN;
//
//                                    if (!checkPermissionForCameraAndMicrophone()) {
//                                        requestPermissionForCameraAndMicrophone();
//                                    } else {
//                                        //createAudioAndVideoTracks();
//                                        setAccessToken();
//                                    }
//                                    connectToRoom(roomIds);
//
//                                }
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    },
//                    new Response.ErrorListener()
//                    {
//                        @Override
//                        public void onErrorResponse(VolleyError error)
//                        {
//                            Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
//                        }
//                    })
//            {
//                @Override
//                protected Map<String, String> getParams() {
//                    //user_id, token, room_id
//
//                    Map<String, String> params = new HashMap<String, String>();
//                    params.put("user_id", loginUserId);//loginUserId
//                    params.put("token", Const.loadData(VideoCallCustomerActivity.this,
//                            "loginUserToken"));
//                    params.put("room_id", "123-101");//roomIds
//                    Log.e("params", params.toString());
//                    return params;
//                }
//            };
//
//            //queue.add(strRequest);
//
//
//            Volley.newRequestQueue(getApplicationContext()).add(strRequest);
//            Log.e("LOGIN", strRequest.toString());
//            strRequest.setRetryPolicy(new RetryPolicy() {
//                @Override
//                public int getCurrentTimeout() {
//                    return 50000;
//                }
//
//                @Override
//                public int getCurrentRetryCount() {
//                    return 50000;
//                }
//
//                @Override
//                public void retry(VolleyError error) throws VolleyError {
//
//                }
//            });
//        }
//
//
//        class SendVideoDateUpdate extends AsyncTask<Void, Void, Void> {
//            private String response;
//            String status = "error";
//            String message = "Invalid data";
//            private ProgressDialog pd;
//            private boolean isAllNotification = false;
//
//
//            @Override
//            protected void onCancelled() {
//                Log.e("CANCELLED","TAB3");
//                super.onCancelled();
//            }
//
//            @Override
//            protected void onPreExecute() {
//                super.onPreExecute();
//
//            }
//
//            @Override
//            protected Void doInBackground(Void... params) {
//                JSONObject data = new JSONObject();
//                JSONObject data_objects = new JSONObject();
//
//                try {
//                    data.put("user_id", id);
//                    data.put("token", Auth);
//                    data.put("user_type","2");
//                    data.put("groupingSid", room.getSid());
//                    data.put("promo_video_id",vdo_id);
//                    data.put("room_id",roomId);
//                    data.put("sid", localParticipant.getSid());
//                    data.put("participant_sid", room.getParticipants().get(0).getSid());
//                    Log.e("SendSidVideo",data.toString());
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//                response = Connections.requestPostUrl(Constant.BaseURL+Constant.
//                        SaveVideoChat, data, true);
//
//                Log.e("SendVideoDateUpdate:::", response+"");
//
//                if (response != null){
//
//
//                }
//
//
//                return null;
//            }
//
//            @Override
//            protected void onPostExecute(Void aVoid) {
//                super.onPostExecute(aVoid);
//
//                if (response != null) {
//
//
//                } else {
//
//
//                }
//            }
//        }
//
//
//
//
//        class UpdateWalletData extends AsyncTask<Void, Void, Void> {
//            private String response;
//            String status = "error";
//            String message = "Invalid data";
//            private ProgressDialog pd;
//            private boolean isAllNotification = false;
//
//
//            @Override
//            protected void onCancelled() {
//                Log.e("CANCELLED","TAB3");
//                super.onCancelled();
//            }
//
//            @Override
//            protected void onPreExecute() {
//                super.onPreExecute();
//
//            }
//
//            @Override
//            protected Void doInBackground(Void... params) {
//                JSONObject data = new JSONObject();
//                JSONObject data_objects = new JSONObject();
//
//                try {
//                    data.put("user_id", id);
//                    data.put("token", Auth);
//                    data.put("promo_video_id",vdo_id);
//                    Log.e("SaveWalletData",data.toString());
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//                response = Connections.requestPostUrl(Constant.BaseURL+Constant.updateCelebrityWallet, data, true);
//
//                Log.e("SendVideoDateUpdate:::", response+"");
//
//                if (response != null){
//
//
//                }
//
//
//                return null;
//            }
//
//            @Override
//            protected void onPostExecute(Void aVoid) {
//                super.onPostExecute(aVoid);
//
//                if (response != null) {
//
//                    room.disconnect();
//                    newTimerTask.cancel();
//                /*Intent i = new Intent(VideoActivity.this, promtSaveVideo.class);
//                i.putExtra("type","1");
//                i.putExtra("vdo_id",vdo_id);
//                i.putExtra("celebNAME",celebNAME);
//                finish();
//                startActivity(i);*/
//
//                } else {
//
//
//
//                }
//            }
//        }
//
//
//        class CancleVideoCall extends AsyncTask<Void, Void, Void> {
//            private String response;
//            String status = "error";
//            String message = "Invalid data";
//            private ProgressDialog pd;
//            private boolean isAllNotification = false;
//            String getID = id;
//
//            @Override
//            protected void onCancelled() {
//                Log.e("CANCELLED","TAB3");
//                super.onCancelled();
//            }
//
//            @Override
//            protected void onPreExecute() {
//                super.onPreExecute();
//
//            }
//
//            @Override
//            protected Void doInBackground(Void... params) {
//                JSONObject data = new JSONObject();
//                JSONObject data_objects = new JSONObject();
//
//                try {
//                    data.put("user_id", id);
//                    data.put("token", Auth);
//                    data.put("user_type","2");
//                    data.put("promo_video_id",vdo_id);
//                    data.put("availability_status","0");
//                    Log.e("SendSidVideo",data.toString());
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//                response = Connections.requestPostUrl(Constant.BaseURL+
//                                Constant.setVideo_AvailabilityStatus, data,
//                        true);
//                Log.e("SendVideoDateUpdate:::", response+"");
//
//                if (response != null){
//
//                    try {
//                        JSONObject returnData = new JSONObject(response);
//                        status = returnData.getString("status");
//                        message = returnData.getString("message");
//
//
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//
//
//                }
//
//
//                return null;
//            }
//
//            @Override
//            protected void onPostExecute(Void aVoid) {
//                super.onPostExecute(aVoid);
//
//                if (response != null) {
//
//                    if(status.equals("0")){
//
//
//                        AlertDialog.Builder builder = new AlertDialog.Builder(VideoCallCustomerActivity.this);
//                        builder.setMessage(message)
//                                .setCancelable(false)
//                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int id) {
//                                        //do things
//                                        dialog.dismiss();
//                                        room.disconnect();
//
//                                        if(getID.equals(ids[0].toString())){
//                                            if(room!=null){
//
//                                                room.disconnect();
//                                            /*Intent i = new Intent(VideoActivity.this,
//                                                    HomeFrameActivity.class);
//                                            i.putExtra("type","1");
//                                            i.putExtra("vdo_id",vdo_id);
//                                            finish();
//                                            startActivity(i);*/
//                                            }
//
//
//                                        }else{
//                                            if(room!=null){
//
//                                                room.disconnect();
//                                           /* Intent i = new Intent(VideoActivity.this,
//                                                    FanHome.class);
//                                            i.putExtra("type","2");
//                                            i.putExtra("vdo_id",vdo_id);
//                                            finish();
//                                            startActivity(i);*/
//                                            }
//
//                                        }
//
//
//                                    }
//                                });
//                        AlertDialog alert = builder.create();
//                        alert.show();
//
//
//                    } else if(message.equals("token and user id mis-match")){
//
//                        final iOSDialog iOSDialog = new iOSDialog(VideoCallCustomerActivity.this);
//                        iOSDialog.setTitle("Token Mis-matched");
//                        iOSDialog.setSubtitle(message);
//                        iOSDialog.setPositiveLabel("OK");
//                        iOSDialog.setBoldPositiveLabel(true);
//                        iOSDialog.setPositiveListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                iOSDialog.dismiss();
//
//                                SharedPreferences pre = getSharedPreferences(Constant.MY_PREFS_NAME, MODE_PRIVATE);
//                                String deviceTok = pre.getString("Device_Token","");
//                                SharedPreferences.Editor editor = getSharedPreferences(Constant.MY_PREFS_NAME, MODE_PRIVATE).edit();
//                                editor.clear();
//                                editor.commit();
//                                editor.putString("Device_Token","");
//                                editor.commit();
//
//                           /* Intent i = new Intent(VideoActivity.this, MainActivity.class);
//                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                            finish();
//                            startActivity(i);*/
//                            }
//                        });
//                        iOSDialog.show();
//
//                    }
//
//
//                } else {
//
//
//                }
//            }
//        }
//
//
//
//    }
