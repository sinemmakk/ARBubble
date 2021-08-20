package com.example.arbubble;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;

import android.widget.TextView;

import com.google.ar.sceneform.ArSceneView;
import com.google.ar.sceneform.Scene;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.Renderable;
import com.google.ar.sceneform.rendering.ViewRenderable;
import com.google.ar.sceneform.ux.ArFragment;

import com.google.ar.sceneform.ux.TransformableNode;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Locale;

public class ARActivity extends AppCompatActivity {
    ArFragment arFragment;
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aractivity);
        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.kamera);
        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        if(b!=null) {
            str =(String) b.get("yazi");

        } else {
            str = "Merhaba";
        }

        placeObject(arFragment);


    }
    private void placeObject(ArFragment fragment) {
        ViewRenderable.builder()
                .setView(fragment.getContext(), R.layout.bubble)
                .build()
                .thenAccept(it -> {
                    it.setShadowCaster(false);
                    it.setShadowReceiver(false);
                    TextView tv = it.getView().findViewById(R.id.tv_text);
                    tv.setText(str);

                    addControlsToScene(it);
                });
    }

    private void addControlsToScene(Renderable renderable) {
        ArSceneView arSceneView = arFragment.getArSceneView();
        Scene scene = arSceneView.getScene();
        Vector3 location = new Vector3(0,0,-7);
        //AnchorNode anchorNode = new AnchorNode(anchor);
        TransformableNode node = new TransformableNode(arFragment.getTransformationSystem());
        node.setRenderable(renderable);
        node.setParent(scene);
        node.setWorldPosition(location);
        arFragment.getArSceneView().getScene().addChild(node);
    }
}