package com.example.phoenix.drawingbook;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phoenix.drawingbook.models.DbHelper;
import com.example.phoenix.drawingbook.models.Drawing;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Context mContext;
    private Resources mResources;
    private RelativeLayout mRelativeLayout;
    private Button mButton;
    private ImageView mImageView;

    private TextView barTopText, barBotText, dashTopText, dashMidText, dashBotText, selectedTextView;
    private RadioButton barTopRadio, barBotRadio, dashTopRadio, dashMidRadio, dashBotRadio;
    private Button insertBtn, spaceBtn, deleteBtn;

    private ProgressBar textProgress;
    private String charForSelectedView;
    private int hideShowFlag = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the application context
        mContext = getApplicationContext();

        // Get the Resources
        mResources = getResources();

        // Get the widgets reference from XML layout
        mRelativeLayout = (RelativeLayout) findViewById(R.id.rl);

        barTopText = (TextView) findViewById(R.id.barTop);
        barBotText = (TextView) findViewById(R.id.barBot);
        dashTopText = (TextView) findViewById(R.id.dashTop);
        dashMidText = (TextView) findViewById(R.id.dashMid);
        dashBotText = (TextView) findViewById(R.id.dashBot);

        barTopRadio = (RadioButton) findViewById(R.id.barTopRadio);
        barBotRadio = (RadioButton) findViewById(R.id.barBotRadio);
        dashTopRadio = (RadioButton) findViewById(R.id.dashTopRadio);
        dashMidRadio = (RadioButton) findViewById(R.id.dashMidRadio);
        dashBotRadio = (RadioButton) findViewById(R.id.dashBotRadio);

        textProgress = (ProgressBar) findViewById(R.id.progressBar);
        textProgress.setProgress(0);
        textProgress.setMax(14);

        insertBtn = (Button) findViewById(R.id.insertButton);
        spaceBtn = (Button) findViewById(R.id.spaceButton);
        deleteBtn = (Button) findViewById(R.id.delButton);

        insertBtn.setEnabled(false);
        spaceBtn.setEnabled(false);
        deleteBtn.setEnabled(false);

        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedTextView.getText().toString().length() <= 13) {
                    selectedTextView.setText(selectedTextView.getText().toString() + charForSelectedView);
                    if (selectedTextView == barTopText || selectedTextView == barBotText) {
                        if (selectedTextView.getText().toString().length() <= 1) {
                            textProgress.setMax(100);
                            textProgress.setProgress(selectedTextView.getText().toString().length());
                        } else {
                            textProgress.setMax(14);
                            textProgress.setProgress(selectedTextView.getText().toString().length()-1);
                        }
                    } else {
                        textProgress.setMax(14);
                        textProgress.setProgress(selectedTextView.getText().toString().length());
                    }
                }
            }
        });

        spaceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedTextView.getText().toString().length() <= 13) {
                    selectedTextView.setText(selectedTextView.getText().toString() + " ");
                    if (selectedTextView == barTopText || selectedTextView == barBotText) {
                        if (selectedTextView.getText().toString().length() <= 1) {
                            textProgress.setMax(100);
                            textProgress.setProgress(selectedTextView.getText().toString().length());
                        } else {
                            textProgress.setMax(14);
                            textProgress.setProgress(selectedTextView.getText().toString().length()-1);
                        }
                    } else {
                        textProgress.setMax(14);
                        textProgress.setProgress(selectedTextView.getText().toString().length());
                    }
                }
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!selectedTextView.getText().toString().isEmpty()) {
                    String text = selectedTextView.getText().toString();
                    selectedTextView.setText(text.substring(0, text.length() - 1));
                    if (selectedTextView == barTopText || selectedTextView == barBotText) {
                        if (selectedTextView.getText().toString().length() <= 1) {
                            textProgress.setMax(100);
                            textProgress.setProgress(selectedTextView.getText().toString().length());
                        } else {
                            textProgress.setMax(14);
                            textProgress.setProgress(selectedTextView.getText().toString().length()-1);
                        }
                    } else {
                        textProgress.setMax(14);
                        textProgress.setProgress(selectedTextView.getText().toString().length());
                    }
                }
            }
        });

       // mButton = (Button) findViewById(R.id.btnRight);
       // mImageView = (ImageView) findViewById(R.id.iv);

        // Set a click listener for Button widget
//        mButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Initialize a new Bitmap object
//                imageWidth = imageWidth + 80;
//                if (xC.isEmpty() && yC.isEmpty()){
//                    imageHeight = 20;
//                    xC.add(0);
//                    yC.add(0);
//                    drawLine();
//                }
//                else {
//                    xC.add(xC.get(xC.size() - 1) + 80);
//                    yC.add(yC.get(yC.size() - 1));
//                    drawLine();
//                }
//            }
//        });
    }

//    private void drawLine() {
//        Bitmap bitmap = Bitmap.createBitmap(
//                imageWidth, // Width
//                imageHeight, // Height
//                Bitmap.Config.ARGB_8888 // Config
//        );
//
//        // Initialize a new Canvas instance
//        Canvas canvas = new Canvas(bitmap);
//
//        // Draw a solid color on the canvas as background
//        canvas.drawColor(Color.LTGRAY);
//
//        // Initialize a new Paint instance to draw the line
//        Paint paint = new Paint();
//        // Line color
//        paint.setColor(Color.RED);
//        paint.setStyle(Paint.Style.STROKE);
//        // Line width in pixels
//        paint.setStrokeWidth(10);
//        paint.setAntiAlias(true);
//
//        // Set a pixels value to offset the line from canvas edge
//        //int offset = 10;
//
//                /*
//                    public void drawLine (float startX, float startY, float stopX, float stopY, Paint paint)
//                        Draw a line segment with the specified start and stop x,y coordinates, using
//                        the specified paint.
//
//                        Note that since a line is always "framed", the Style is ignored in the paint.
//
//                        Degenerate lines (length is 0) will not be drawn.
//
//                    Parameters
//                        startX : The x-coordinate of the start point of the line
//                        startY : The y-coordinate of the start point of the line
//                        paint : The paint used to draw the line
//
//                */
//
//        // Draw a line on canvas at the center position
//
//        for (int i = 0; i < xC.size(); i++) {
//            canvas.drawLine(
//                    xC.get(i) + 3, // startX
//                    yC.get(i) + 10, // startY
//                    xC.get(i) + 76, // stopX
//                    yC.get(i) + 10, // stopY
//                    paint // Paint
//            );
//        }
//
//
//        // Display the newly created bitmap on app interface
//        mImageView.setImageBitmap(bitmap);
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menu_save:
                Drawing drawing = new Drawing();
                drawing.setTopBar(barTopText.getText().toString());
                drawing.setBotBar(barBotText.getText().toString());
                drawing.setTopDash(dashTopText.getText().toString());
                drawing.setMidDash(dashMidText.getText().toString());
                drawing.setBotDash(dashBotText.getText().toString());
                DbHelper drawingDB = new DbHelper(this);
                drawingDB.saveDrawing(drawing);
                drawingDB.close();
                Toast.makeText(this, "Drawing Saved!", Toast.LENGTH_SHORT).show();
                break;

            case R.id.menu_clear:
                barTopText.setText("");
                barBotText.setText("");
                dashTopText.setText("");
                dashMidText.setText("");
                dashBotText.setText("");
                resetInputs();
                break;

            case R.id.menu_load:
                drawingDB = new DbHelper(this);
                drawing = drawingDB.loadDrawing();
                barTopText.setText(drawing.getTopBar());
                barBotText.setText(drawing.getBotBar());
                dashTopText.setText(drawing.getTopDash());
                dashMidText.setText(drawing.getMidDash());
                dashBotText.setText(drawing.getBotDash());
                resetInputs();
                Toast.makeText(this, "Last saved drawing loaded", Toast.LENGTH_SHORT).show();
                break;

            case R.id.menu_hide_show:
                if (hideShowFlag == 0) {
                    textProgress.setVisibility(View.GONE);
                    hideShowFlag = 1;
                } else {
                    textProgress.setVisibility(View.VISIBLE);
                    hideShowFlag = 0;
                }
        }
        return super.onOptionsItemSelected(item);
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        insertBtn.setEnabled(true);
        spaceBtn.setEnabled(true);
        deleteBtn.setEnabled(true);

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.barTopRadio:
                if (checked) {
                    barBotRadio.setChecked(false);
                    dashTopRadio.setChecked(false);
                    dashMidRadio.setChecked(false);
                    dashBotRadio.setChecked(false);
                    selectedTextView = barTopText;
                    charForSelectedView = "|";
                    if (selectedTextView.getText().toString().length() <= 1) {
                        textProgress.setMax(100);
                        textProgress.setProgress(selectedTextView.getText().toString().length());
                    } else {
                        textProgress.setMax(14);
                        textProgress.setProgress(selectedTextView.getText().toString().length()-1);
                    }
                    //changeProgressMarginForBar();
                }
                break;
            case R.id.barBotRadio:
                if (checked) {
                    barTopRadio.setChecked(false);
                    dashTopRadio.setChecked(false);
                    dashMidRadio.setChecked(false);
                    dashBotRadio.setChecked(false);
                    selectedTextView = barBotText;
                    charForSelectedView = "|";
                    //changeProgressMarginForBar();
                    if (selectedTextView.getText().toString().length() <= 1) {
                        textProgress.setMax(100);
                        textProgress.setProgress(selectedTextView.getText().toString().length());
                    } else {
                        textProgress.setMax(14);
                        textProgress.setProgress(selectedTextView.getText().toString().length()-1);
                    }
                }
                break;
            case R.id.dashTopRadio:
                if (checked) {
                    barTopRadio.setChecked(false);
                    barBotRadio.setChecked(false);
                    dashMidRadio.setChecked(false);
                    dashBotRadio.setChecked(false);
                    selectedTextView = dashTopText;
                    charForSelectedView = "_";
                    //changeProgressMarginForDash();
                    textProgress.setMax(14);
                    textProgress.setProgress(selectedTextView.getText().toString().length());
                }
                break;
            case R.id.dashMidRadio:
                if (checked) {
                    barTopRadio.setChecked(false);
                    barBotRadio.setChecked(false);
                    dashTopRadio.setChecked(false);
                    dashBotRadio.setChecked(false);
                    selectedTextView = dashMidText;
                    charForSelectedView = "_";
                    //changeProgressMarginForDash();
                    textProgress.setMax(14);
                    textProgress.setProgress(selectedTextView.getText().toString().length());
                }
                break;
            case R.id.dashBotRadio:
                if (checked) {
                    barTopRadio.setChecked(false);
                    barBotRadio.setChecked(false);
                    dashTopRadio.setChecked(false);
                    dashMidRadio.setChecked(false);
                    selectedTextView = dashBotText;
                    charForSelectedView = "_";
                    //changeProgressMarginForDash();
                    textProgress.setMax(14);
                    textProgress.setProgress(selectedTextView.getText().toString().length());
                }
                break;
        }
    }

//    private void changeProgressMarginForBar() {
//        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)textProgress.getLayoutParams();
//        params.setMarginStart(0); //substitute parameters for left, top, right, bottom
//        textProgress.setLayoutParams(params);
//    }

//    private void changeProgressMarginForDash() {
//        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)textProgress.getLayoutParams();
//        params.setMarginStart(40); //substitute parameters for left, top, right, bottom
//        textProgress.setLayoutParams(params);
//    }
    
    private void resetInputs() {
        barTopRadio.setChecked(false);
        barBotRadio.setChecked(false);
        dashTopRadio.setChecked(false);
        dashMidRadio.setChecked(false);
        dashBotRadio.setChecked(false);
        textProgress.setProgress(0);
        insertBtn.setEnabled(false);
        spaceBtn.setEnabled(false);
        deleteBtn.setEnabled(false);
    }
}
