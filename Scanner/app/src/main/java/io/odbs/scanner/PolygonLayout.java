package io.odbs.scanner;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PolygonLayout extends FrameLayout {

    protected Context context;
    private Paint paint;
    private ImageView pointer1;
    private ImageView pointer2;
    private ImageView pointer3;
    private ImageView pointer4;
    private PolygonLayout polygonLayout;

    public PolygonLayout(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public PolygonLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public PolygonLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init() {
        polygonLayout = this;
        pointer1 = getImageView(0, 0);
        pointer2 = getImageView(getWidth(), 0);
        pointer3 = getImageView(0, getHeight());
        pointer4 = getImageView(getWidth(), getHeight());

        addView(pointer1);
        addView(pointer2);
        addView(pointer3);
        addView(pointer4);
        initPaint();
    }

    @Override
    protected void attachViewToParent(View child, int index, ViewGroup.LayoutParams params) {
        super.attachViewToParent(child, index, params);
    }

    private void initPaint() {
        paint = new Paint();
        paint.setColor(getResources().getColor(R.color.blue));
        paint.setStrokeWidth(2);
        paint.setAntiAlias(true);
    }

    public Map<Integer, PointF> getPoints() {

        List<PointF> points = new ArrayList<>();
        points.add(new PointF(pointer1.getX(), pointer1.getY()));
        points.add(new PointF(pointer2.getX(), pointer2.getY()));
        points.add(new PointF(pointer3.getX(), pointer3.getY()));
        points.add(new PointF(pointer4.getX(), pointer4.getY()));

        return getOrderedPoints(points);
    }

    public Map<Integer, PointF> getOrderedPoints(List<PointF> points) {

        PointF centerPoint = new PointF();
        int size = points.size();
        for (PointF pointF : points) {
            centerPoint.x += pointF.x / size;
            centerPoint.y += pointF.y / size;
        }
        Map<Integer, PointF> orderedPoints = new HashMap<>();
        for (PointF pointF : points) {
            int index = -1;
            if (pointF.x < centerPoint.x && pointF.y < centerPoint.y) {
                index = 0;
            } else if (pointF.x > centerPoint.x && pointF.y < centerPoint.y) {
                index = 1;
            } else if (pointF.x < centerPoint.x && pointF.y > centerPoint.y) {
                index = 2;
            } else if (pointF.x > centerPoint.x && pointF.y > centerPoint.y) {
                index = 3;
            }
            orderedPoints.put(index, pointF);
        }
        return orderedPoints;
    }

    public void setPoints(Map<Integer, PointF> pointFMap) {
        if (pointFMap.size() == 4) {
            setPointsCoordinates(pointFMap);
        }
    }

    private void setPointsCoordinates(Map<Integer, PointF> pointFMap) {
        pointer1.setX(pointFMap.get(0).x);
        pointer1.setY(pointFMap.get(0).y);

        pointer2.setX(pointFMap.get(1).x);
        pointer2.setY(pointFMap.get(1).y);

        pointer3.setX(pointFMap.get(2).x);
        pointer3.setY(pointFMap.get(2).y);

        pointer4.setX(pointFMap.get(3).x);
        pointer4.setY(pointFMap.get(3).y);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        canvas.drawLine(pointer1.getX() + (pointer1.getWidth() / 2), pointer1.getY() + (pointer1.getHeight() / 2), pointer3.getX() + (pointer3.getWidth() / 2), pointer3.getY() + (pointer3.getHeight() / 2), paint);
        canvas.drawLine(pointer1.getX() + (pointer1.getWidth() / 2), pointer1.getY() + (pointer1.getHeight() / 2), pointer2.getX() + (pointer2.getWidth() / 2), pointer2.getY() + (pointer2.getHeight() / 2), paint);
        canvas.drawLine(pointer2.getX() + (pointer2.getWidth() / 2), pointer2.getY() + (pointer2.getHeight() / 2), pointer4.getX() + (pointer4.getWidth() / 2), pointer4.getY() + (pointer4.getHeight() / 2), paint);
        canvas.drawLine(pointer3.getX() + (pointer3.getWidth() / 2), pointer3.getY() + (pointer3.getHeight() / 2), pointer4.getX() + (pointer4.getWidth() / 2), pointer4.getY() + (pointer4.getHeight() / 2), paint);
    }

    private ImageView getImageView(int x, int y) {
        ImageView imageView = new ImageView(context);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        imageView.setLayoutParams(layoutParams);
        imageView.setImageResource(R.drawable.circle);
        imageView.setX(x);
        imageView.setY(y);
        imageView.setOnTouchListener(new TouchListener());
        return imageView;
    }

    public boolean isValidShape(Map<Integer, PointF> pointFMap) {
        return pointFMap.size() == 4;
    }

    private class TouchListener implements OnTouchListener {

        PointF downPoint = new PointF();
        PointF startPoint = new PointF();

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int eventAction = event.getAction();
            switch (eventAction) {
                case MotionEvent.ACTION_MOVE:
                    PointF mv = new PointF(event.getX() - downPoint.x, event.getY() - downPoint.y);
                    if (((startPoint.x + mv.x + v.getWidth()) < polygonLayout.getWidth() && (startPoint.y + mv.y + v.getHeight() < polygonLayout.getHeight())) && ((startPoint.x + mv.x) > 0 && startPoint.y + mv.y > 0)) {
                        v.setX((int) (startPoint.x + mv.x));
                        v.setY((int) (startPoint.y + mv.y));
                        startPoint = new PointF(v.getX(), v.getY());
                    }
                    break;
                case MotionEvent.ACTION_DOWN:
                    downPoint.x = event.getX();
                    downPoint.y = event.getY();
                    startPoint = new PointF(v.getX(), v.getY());
                    break;
                case MotionEvent.ACTION_UP:
                    int color = 0;
                    if (isValidShape(getPoints())) {
                        color = getResources().getColor(R.color.blue);
                    } else {
                        color = getResources().getColor(R.color.orange);
                    }
                    paint.setColor(color);
                    break;
                default:
                    break;
            }
            polygonLayout.invalidate();
            return true;
        }

    }
}

