package ru.adonixis.geometricloader;

import android.graphics.drawable.Drawable;
import android.support.graphics.drawable.Animatable2Compat;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private boolean shouldStop = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView imageLoader = findViewById(R.id.image_loader);
        final Button btnStart = findViewById(R.id.btn_start);
        final Button btnStopImmediately = findViewById(R.id.btn_stop_immediately);
        final Button btnStopSmoothly = findViewById(R.id.btn_stop_smoothly);

        final AnimatedVectorDrawableCompat animatedGeometricLoader = AnimatedVectorDrawableCompat.create(this, R.drawable.animated_geometric_loader);
        imageLoader.setImageDrawable(animatedGeometricLoader);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shouldStop) {
                    shouldStop = false;
                    btnStart.setEnabled(false);
                    btnStopImmediately.setEnabled(true);
                    btnStopSmoothly.setEnabled(true);

                    if (animatedGeometricLoader != null) {
                        animatedGeometricLoader.registerAnimationCallback(new Animatable2Compat.AnimationCallback() {
                            @Override
                            public void onAnimationEnd(Drawable drawable) {
                                if (!shouldStop) {
                                    imageLoader.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            animatedGeometricLoader.start();
                                        }
                                    });
                                }
                            }
                        });
                        animatedGeometricLoader.start();
                    }
                }
            }
        });

        btnStopImmediately.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!shouldStop) {
                    shouldStop = true;
                    btnStart.setEnabled(true);
                    btnStopImmediately.setEnabled(false);
                    btnStopSmoothly.setEnabled(false);

                    if (animatedGeometricLoader != null) {
                        animatedGeometricLoader.stop();
                    }
                }
            }
        });

        btnStopSmoothly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!shouldStop) {
                    shouldStop = true;
                    btnStart.setEnabled(true);
                    btnStopImmediately.setEnabled(false);
                    btnStopSmoothly.setEnabled(false);
                }
            }
        });
    }
}
