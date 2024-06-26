package com.example.arbubble;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;

import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.view.MotionEvent;
import android.view.PixelCopy;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.ArSceneView;
import com.google.ar.sceneform.Node;
import com.google.ar.sceneform.Scene;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.Renderable;
import com.google.ar.sceneform.rendering.ViewRenderable;
import com.google.ar.sceneform.ux.ArFragment;

import com.google.ar.sceneform.ux.BaseArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ARActivity extends AppCompatActivity {
    ArFragment arFragment;
    String str,bubble;
    Button btn_photo;
    ImageView iv_flower,iv_bee,iv_boom,iv_cool,iv_dinasour,iv_haha,iv_just_be_happy,iv_leaf,iv_music,iv_nice,iv_ok,iv_okie_dokie,iv_omg,iv_oops,iv_price_tag,iv_say_no,iv_ufo,iv_well_done,iv_wow;
    int control = 0,selected_item;
    boolean selected = false;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aractivity);
        btn_photo = findViewById(R.id.btn_photo);
        iv_flower = findViewById(R.id.img_flower);
        iv_bee = findViewById(R.id.img_bee);
        iv_boom = findViewById(R.id.img_boom);
        iv_cool = findViewById(R.id.img_cool);
        iv_dinasour = findViewById(R.id.img_dinasour);
        iv_haha = findViewById(R.id.img_haha);
        iv_just_be_happy = findViewById(R.id.img_just_be_happy);
        iv_leaf = findViewById(R.id.img_leaf);
        iv_music = findViewById(R.id.img_music);
        iv_nice = findViewById(R.id.img_nice);
        iv_ok = findViewById(R.id.img_ok);
        iv_okie_dokie = findViewById(R.id.img_okie_dokie);
        iv_omg = findViewById(R.id.img_omg);
        iv_oops = findViewById(R.id.img_oops);
        iv_price_tag = findViewById(R.id.img_omg);
        iv_say_no = findViewById(R.id.img_say_no);
        iv_ufo = findViewById(R.id.img_ufo);
        iv_wow = findViewById(R.id.img_wow);
        iv_well_done = findViewById(R.id.img_well_done);

        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.kamera);
        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        if(b!=null) {
            str =(String) b.get("yazi");
            bubble =(String) b.get("bubble");
            if(bubble.equals("no_bubble")){
                control = 1;
            }
        } else {
            str = "Merhaba";
            bubble = "speech";
        }

        if(control==0){
            Toast toast = Toast.makeText(ARActivity.this,
                    "Konuşma balonunu koymak için ekrana dokunun", Toast.LENGTH_SHORT);
            toast.show();
        }


        arFragment.setOnTapArPlaneListener(new BaseArFragment.OnTapArPlaneListener() {
            @Override
            public void onTapPlane(HitResult hitResult, Plane plane, MotionEvent motionEvent) {
                Anchor anchor = hitResult.createAnchor();
                AnchorNode anchorNode = new AnchorNode(anchor);
                anchorNode.setParent(arFragment.getArSceneView().getScene());
                if(control == 0){
                    placeObject(arFragment, anchorNode);
                    control++;
                }
                if(selected){
                    createModel(arFragment, anchorNode, selected_item);
                }

            }
        });

        btn_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePhoto();
            }
        });

        iv_flower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selected){
                    iv_flower.setBackgroundColor(Color.TRANSPARENT);
                    selected = false;
                    selected_item = 0;
                }else{
                    selected_item = 1;
                    iv_flower.setBackgroundColor(Color.parseColor("#F38181"));
                    selected = true;
                }
            }
        });
        iv_bee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selected){
                    iv_bee.setBackgroundColor(Color.TRANSPARENT);
                    selected = false;
                    selected_item = 0;
                }else{
                    selected_item = 2;
                    iv_bee.setBackgroundColor(Color.parseColor("#F38181"));
                    selected = true;
                }
            }
        });
        iv_boom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selected){
                    iv_boom.setBackgroundColor(Color.TRANSPARENT);
                    selected = false;
                    selected_item = 0;
                }else{
                    selected_item = 3;
                    iv_boom.setBackgroundColor(Color.parseColor("#F38181"));
                    selected = true;
                }
            }
        });
        iv_cool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selected){
                    iv_cool.setBackgroundColor(Color.TRANSPARENT);
                    selected = false;
                    selected_item = 0;
                }else{
                    selected_item = 4;
                    iv_cool.setBackgroundColor(Color.parseColor("#F38181"));
                    selected = true;
                }
            }
        });
        iv_dinasour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selected){
                    iv_dinasour.setBackgroundColor(Color.TRANSPARENT);
                    selected = false;
                    selected_item = 0;
                }else{
                    selected_item = 5;
                    iv_dinasour.setBackgroundColor(Color.parseColor("#F38181"));
                    selected = true;
                }
            }
        });
        iv_haha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selected){
                    iv_haha.setBackgroundColor(Color.TRANSPARENT);
                    selected = false;
                    selected_item = 0;
                }else{
                    selected_item = 6;
                    iv_haha.setBackgroundColor(Color.parseColor("#F38181"));
                    selected = true;
                }
            }
        });
        iv_just_be_happy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selected){
                    iv_just_be_happy.setBackgroundColor(Color.TRANSPARENT);
                    selected = false;
                    selected_item = 0;
                }else{
                    selected_item = 7;
                    iv_just_be_happy.setBackgroundColor(Color.parseColor("#F38181"));
                    selected = true;
                }
            }
        });
        iv_leaf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selected){
                    iv_leaf.setBackgroundColor(Color.TRANSPARENT);
                    selected = false;
                    selected_item = 0;
                }else{
                    selected_item = 8;
                    iv_leaf.setBackgroundColor(Color.parseColor("#F38181"));
                    selected = true;
                }
            }
        });
        iv_music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selected){
                    iv_music.setBackgroundColor(Color.TRANSPARENT);
                    selected = false;
                    selected_item = 0;
                }else{
                    selected_item = 9;
                    iv_music.setBackgroundColor(Color.parseColor("#F38181"));
                    selected = true;
                }
            }
        });
        iv_nice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selected){
                    iv_nice.setBackgroundColor(Color.TRANSPARENT);
                    selected = false;
                    selected_item = 0;
                }else{
                    selected_item = 10;
                    iv_nice.setBackgroundColor(Color.parseColor("#F38181"));
                    selected = true;
                }
            }
        });
        iv_okie_dokie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selected){
                    iv_okie_dokie.setBackgroundColor(Color.TRANSPARENT);
                    selected = false;
                    selected_item = 0;
                }else{
                    selected_item = 11;
                    iv_okie_dokie.setBackgroundColor(Color.parseColor("#F38181"));
                    selected = true;
                }
            }
        });
        iv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selected){
                    iv_ok.setBackgroundColor(Color.TRANSPARENT);
                    selected = false;
                    selected_item = 0;
                }else{
                    selected_item = 12;
                    iv_ok.setBackgroundColor(Color.parseColor("#F38181"));
                    selected = true;
                }
            }
        });
        iv_omg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selected){
                    iv_omg.setBackgroundColor(Color.TRANSPARENT);
                    selected = false;
                    selected_item = 0;
                }else{
                    selected_item = 13;
                    iv_omg.setBackgroundColor(Color.parseColor("#F38181"));
                    selected = true;
                }
            }
        });
        iv_oops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selected){
                    iv_oops.setBackgroundColor(Color.TRANSPARENT);
                    selected = false;
                    selected_item = 0;
                }else{
                    selected_item = 14;
                    iv_oops.setBackgroundColor(Color.parseColor("#F38181"));
                    selected = true;
                }
            }
        });
        iv_price_tag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selected){
                    iv_price_tag.setBackgroundColor(Color.TRANSPARENT);
                    selected = false;
                    selected_item = 0;
                }else{
                    selected_item = 15;
                    iv_price_tag.setBackgroundColor(Color.parseColor("#F38181"));
                    selected = true;
                }
            }
        });
        iv_say_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selected){
                    iv_say_no.setBackgroundColor(Color.TRANSPARENT);
                    selected = false;
                    selected_item = 0;
                }else{
                    selected_item = 16;
                    iv_say_no.setBackgroundColor(Color.parseColor("#F38181"));
                    selected = true;
                }
            }
        });
        iv_ufo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selected){
                    iv_ufo.setBackgroundColor(Color.TRANSPARENT);
                    selected = false;
                    selected_item = 0;
                }else{
                    selected_item = 17;
                    iv_ufo.setBackgroundColor(Color.parseColor("#F38181"));
                    selected = true;
                }
            }
        });
        iv_well_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selected){
                    iv_well_done.setBackgroundColor(Color.TRANSPARENT);
                    selected = false;
                    selected_item = 0;
                }else{
                    selected_item = 18;
                    iv_well_done.setBackgroundColor(Color.parseColor("#F38181"));
                    selected = true;
                }
            }
        });
        iv_wow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selected){
                    iv_wow.setBackgroundColor(Color.TRANSPARENT);
                    selected = false;
                    selected_item = 0;
                }else{
                    selected_item = 19;
                    iv_wow.setBackgroundColor(Color.parseColor("#F38181"));
                    selected = true;
                }
            }
        });
    }

    private void createModel(ArFragment fragment, AnchorNode anchor, int i){
        switch (i){
            case 1:
                ViewRenderable.builder()
                        .setView(fragment.getContext(), R.layout.ar_image)
                        .build()
                        .thenAccept(it -> {
                            it.setShadowCaster(false);
                            it.setShadowReceiver(false);
                            ImageView iv = it.getView().findViewById(R.id.iv_image);
                            iv.setImageResource(R.drawable.flower);
                            addControlsToScene(fragment, it, anchor);
                        });
                break;
            case 2:
                ViewRenderable.builder()
                        .setView(fragment.getContext(), R.layout.ar_image)
                        .build()
                        .thenAccept(it -> {
                            it.setShadowCaster(false);
                            it.setShadowReceiver(false);
                            ImageView iv = it.getView().findViewById(R.id.iv_image);
                            iv.setImageResource(R.drawable.bee);
                            addControlsToScene(fragment, it, anchor);
                        });
                break;
            case 3:
                ViewRenderable.builder()
                        .setView(fragment.getContext(), R.layout.ar_image)
                        .build()
                        .thenAccept(it -> {
                            it.setShadowCaster(false);
                            it.setShadowReceiver(false);
                            ImageView iv = it.getView().findViewById(R.id.iv_image);
                            iv.setImageResource(R.drawable.boom);
                            addControlsToScene(fragment, it, anchor);
                        });
                break;
            case 4:
                    ViewRenderable.builder()
                            .setView(fragment.getContext(), R.layout.ar_image)
                            .build()
                            .thenAccept(it -> {
                                it.setShadowCaster(false);
                                it.setShadowReceiver(false);
                                ImageView iv = it.getView().findViewById(R.id.iv_image);
                                iv.setImageResource(R.drawable.cool);
                                addControlsToScene(fragment, it, anchor);
                            });
                    break;
                case 5:
                    ViewRenderable.builder()
                            .setView(fragment.getContext(), R.layout.ar_image)
                            .build()
                            .thenAccept(it -> {
                                it.setShadowCaster(false);
                                it.setShadowReceiver(false);
                                ImageView iv = it.getView().findViewById(R.id.iv_image);
                                iv.setImageResource(R.drawable.dinasour);
                                addControlsToScene(fragment, it, anchor);
                            });
                    break;
                case 6:
                    ViewRenderable.builder()
                            .setView(fragment.getContext(), R.layout.ar_image)
                            .build()
                            .thenAccept(it -> {
                                it.setShadowCaster(false);
                                it.setShadowReceiver(false);
                                ImageView iv = it.getView().findViewById(R.id.iv_image);
                                iv.setImageResource(R.drawable.haha);
                                addControlsToScene(fragment, it, anchor);
                            });
                    break;
                case 7:
                    ViewRenderable.builder()
                        .setView(fragment.getContext(), R.layout.ar_image)
                        .build()
                        .thenAccept(it -> {
                            it.setShadowCaster(false);
                            it.setShadowReceiver(false);
                            ImageView iv = it.getView().findViewById(R.id.iv_image);
                            iv.setImageResource(R.drawable.just_be_happy);
                            addControlsToScene(fragment, it, anchor);
                        });
                    break;
                case 8:
                    ViewRenderable.builder()
                            .setView(fragment.getContext(), R.layout.ar_image)
                            .build()
                            .thenAccept(it -> {
                                it.setShadowCaster(false);
                                it.setShadowReceiver(false);
                                ImageView iv = it.getView().findViewById(R.id.iv_image);
                                iv.setImageResource(R.drawable.leaf);
                                addControlsToScene(fragment, it, anchor);
                            });
                    break;
                case 9:
                    ViewRenderable.builder()
                            .setView(fragment.getContext(), R.layout.ar_image)
                            .build()
                            .thenAccept(it -> {
                                it.setShadowCaster(false);
                                it.setShadowReceiver(false);
                                ImageView iv = it.getView().findViewById(R.id.iv_image);
                                iv.setImageResource(R.drawable.music);
                                addControlsToScene(fragment, it, anchor);
                            });
                    break;
                case 10:
                    ViewRenderable.builder()
                            .setView(fragment.getContext(), R.layout.ar_image)
                            .build()
                            .thenAccept(it -> {
                                it.setShadowCaster(false);
                                it.setShadowReceiver(false);
                                ImageView iv = it.getView().findViewById(R.id.iv_image);
                                iv.setImageResource(R.drawable.nice);
                                addControlsToScene(fragment, it, anchor);
                            });
                    break;
                case 11:
                    ViewRenderable.builder()
                            .setView(fragment.getContext(), R.layout.ar_image)
                            .build()
                            .thenAccept(it -> {
                                it.setShadowCaster(false);
                                it.setShadowReceiver(false);
                                ImageView iv = it.getView().findViewById(R.id.iv_image);
                                iv.setImageResource(R.drawable.okie_dokie);
                                addControlsToScene(fragment, it, anchor);
                            });
                    break;
                case 12:
                    ViewRenderable.builder()
                            .setView(fragment.getContext(), R.layout.ar_image)
                            .build()
                            .thenAccept(it -> {
                                it.setShadowCaster(false);
                                it.setShadowReceiver(false);
                                ImageView iv = it.getView().findViewById(R.id.iv_image);
                                iv.setImageResource(R.drawable.ok);
                                addControlsToScene(fragment, it, anchor);
                            });
                    break;
                case 13:
                    ViewRenderable.builder()
                            .setView(fragment.getContext(), R.layout.ar_image)
                            .build()
                            .thenAccept(it -> {
                                it.setShadowCaster(false);
                                it.setShadowReceiver(false);
                                ImageView iv = it.getView().findViewById(R.id.iv_image);
                                iv.setImageResource(R.drawable.omg);
                                addControlsToScene(fragment, it, anchor);
                            });
                    break;
            case 14:
                ViewRenderable.builder()
                        .setView(fragment.getContext(), R.layout.ar_image)
                        .build()
                        .thenAccept(it -> {
                            it.setShadowCaster(false);
                            it.setShadowReceiver(false);
                            ImageView iv = it.getView().findViewById(R.id.iv_image);
                            iv.setImageResource(R.drawable.oops);
                            addControlsToScene(fragment, it, anchor);
                        });
                break;
            case 15:
                ViewRenderable.builder()
                        .setView(fragment.getContext(), R.layout.ar_image)
                        .build()
                        .thenAccept(it -> {
                            it.setShadowCaster(false);
                            it.setShadowReceiver(false);
                            ImageView iv = it.getView().findViewById(R.id.iv_image);
                            iv.setImageResource(R.drawable.price_tag);
                            addControlsToScene(fragment, it, anchor);
                        });
                break;
            case 16:
                ViewRenderable.builder()
                        .setView(fragment.getContext(), R.layout.ar_image)
                        .build()
                        .thenAccept(it -> {
                            it.setShadowCaster(false);
                            it.setShadowReceiver(false);
                            ImageView iv = it.getView().findViewById(R.id.iv_image);
                            iv.setImageResource(R.drawable.say_no);
                            addControlsToScene(fragment, it, anchor);
                        });
                break;
            case 17:
                ViewRenderable.builder()
                        .setView(fragment.getContext(), R.layout.ar_image)
                        .build()
                        .thenAccept(it -> {
                            it.setShadowCaster(false);
                            it.setShadowReceiver(false);
                            ImageView iv = it.getView().findViewById(R.id.iv_image);
                            iv.setImageResource(R.drawable.ufo);
                            addControlsToScene(fragment, it, anchor);
                        });
                break;
            case 18:
                ViewRenderable.builder()
                        .setView(fragment.getContext(), R.layout.ar_image)
                        .build()
                        .thenAccept(it -> {
                            it.setShadowCaster(false);
                            it.setShadowReceiver(false);
                            ImageView iv = it.getView().findViewById(R.id.iv_image);
                            iv.setImageResource(R.drawable.well_done);
                            addControlsToScene(fragment, it, anchor);
                        });
                break;
            case 19:
                ViewRenderable.builder()
                        .setView(fragment.getContext(), R.layout.ar_image)
                        .build()
                        .thenAccept(it -> {
                            it.setShadowCaster(false);
                            it.setShadowReceiver(false);
                            ImageView iv = it.getView().findViewById(R.id.iv_image);
                            iv.setImageResource(R.drawable.wow);
                            addControlsToScene(fragment, it, anchor);
                        });
                break;
        }
    }

    private void placeObject(ArFragment fragment, AnchorNode anchor) {
        switch(bubble){
            case "speech":
                ViewRenderable.builder()
                        .setView(fragment.getContext(), R.layout.speech_bubble)
                        .build()
                        .thenAccept(it -> {
                            it.setShadowCaster(false);
                            it.setShadowReceiver(false);
                            TextView tv = it.getView().findViewById(R.id.tv_text);
                            tv.setText(str);
                            addControlsToScene(fragment, it, anchor);
                        });
                break;
            case "thought":
                ViewRenderable.builder()
                        .setView(fragment.getContext(), R.layout.thought_bubble)
                        .build()
                        .thenAccept(it -> {
                            it.setShadowCaster(false);
                            it.setShadowReceiver(false);
                            TextView tv = it.getView().findViewById(R.id.tv_text);
                            tv.setText(str);
                            addControlsToScene(fragment, it, anchor);
                        });
                break;
        }
    }

    private void addControlsToScene(ArFragment arFragment, Renderable renderable, AnchorNode anchor) {
        Vector3 location = new Vector3(0,0,-3);
        TransformableNode node = new TransformableNode(arFragment.getTransformationSystem());
        node.setRenderable(renderable);
        node.setParent(anchor);
        node.setWorldPosition(location);
        arFragment.getArSceneView().getScene().addChild(node);
    }


    private String generateFilename() {
        String date =
                new SimpleDateFormat("yyyyMMddHHmmss", java.util.Locale.getDefault()).format(new Date());
        return Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES) + File.separator + "Sceneform/" + date + "_screenshot.jpg";
    }

    private void saveBitmapToDisk(Bitmap bitmap, String filename) throws IOException {
        File out = new File(filename);
        if (!out.getParentFile().exists()) {
            out.getParentFile().mkdirs();
        }
        try (FileOutputStream outputStream = new FileOutputStream(filename);
             ByteArrayOutputStream outputData = new ByteArrayOutputStream()) {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputData);
            outputData.writeTo(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException ex) {
            throw new IOException("Failed to save bitmap to disk", ex);
        }
    }

    private void takePhoto() {
        final String filename = generateFilename();
        final Bitmap bitmap = Bitmap.createBitmap(arFragment.getArSceneView().getWidth(), arFragment.getArSceneView().getHeight(),
                Bitmap.Config.ARGB_8888);
        final HandlerThread handlerThread = new HandlerThread("PixelCopier");
        handlerThread.start();
        PixelCopy.request(arFragment.getArSceneView(), bitmap, new PixelCopy.OnPixelCopyFinishedListener() {
            @Override
            public void onPixelCopyFinished(int i) {
                if (i == PixelCopy.SUCCESS) {
                    try {
                        saveBitmapToDisk(bitmap, filename);
                        Toast toast = Toast.makeText(ARActivity.this,
                                "Resim kaydedildi", Toast.LENGTH_SHORT);
                        toast.show();
                    } catch (IOException e) {
                        Toast toast = Toast.makeText(ARActivity.this, e.toString(),
                                Toast.LENGTH_LONG);
                        toast.show();
                        return;
                    }
                } else {
                    Log.d("ARActivity", "Failed to copyPixels");
                    Toast toast = Toast.makeText(ARActivity.this,
                            "Failed to copyPixels, error:" + i, Toast.LENGTH_LONG);
                    toast.show();
                }
                handlerThread.quitSafely();
            }
        }, new Handler(handlerThread.getLooper()));
    }

}