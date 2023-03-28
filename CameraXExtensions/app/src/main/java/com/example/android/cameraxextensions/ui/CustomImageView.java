package com.example.android.cameraxextensions.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;

import java.util.ArrayList;

public class CustomImageView extends androidx.appcompat.widget.AppCompatImageView {
    private ArrayList<float[]> mPointsList = new ArrayList<>();
    private RectF mRect = new RectF();
    private Path mPath = new Path();
    private Paint mPaint = new Paint();

    public CustomImageView(Context context) {
        super(context);
        init();
    }

    public CustomImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (float[] points : mPointsList) {
            if (points.length == 8) {
                mPath.reset();
                mPath.moveTo(points[0], points[1]);
                mPath.lineTo(points[2], points[3]);
                mPath.lineTo(points[4], points[5]);
                mPath.lineTo(points[6], points[7]);
                mPath.close();

                mPath.computeBounds(mRect, true);
                mPaint.setColor(Color.RED);
                canvas.drawRect(mRect, mPaint);
            }
        }
    }

    public void setPointsList(ArrayList<float[]> pointsList) {
        mPointsList = pointsList;
        invalidate();
    }

    public void setImageResId(Context context, int resId, int x, int y) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize =3;
        Bitmap sourceBitmap = BitmapFactory.decodeResource(context.getResources(), resId, options);
        Bitmap bitmap = Bitmap.createBitmap(200, 200, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawBitmap(sourceBitmap, x, y, mPaint);
        setImageBitmap(bitmap);
    }

}

