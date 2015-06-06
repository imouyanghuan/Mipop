package com.way.mipop.widget;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

public class Until {
	public static int BOTTOM_LINE;
	public static int EXPEND_LINE;
	public static int EXPEND_LINE_RIGHT;
	public static int EXPEND_OFFSET;
	public static int IMAGE_WIDTH;
	public static int MID_LINE;
	public static int MOVE_MAX_SIZE;
	public static int PARKING_LINE;
	public static int PARKING_LINE_RIGHT;
	public static int SCREEM_HEIGHT;
	public static int SCREEM_WIDTH;
	public static int SHRINK_LINE;
	public static int STATUS_HEIGHT;
	private static int expend_offset_factor;
	private static int image_height_factor = 6;
	private static int mid_line_factor;
	private static int move_max_factor = 5;
	private static float parking_factor;
	private static int shrink_factor;

	static {
		mid_line_factor = 2;
		expend_offset_factor = 10;
		shrink_factor = 2;
		parking_factor = 0.707F;
		SCREEM_WIDTH = 480;
		SCREEM_HEIGHT = 840;
		STATUS_HEIGHT = 10;
		IMAGE_WIDTH = SCREEM_WIDTH / image_height_factor;
		MID_LINE = (SCREEM_WIDTH - IMAGE_WIDTH) / mid_line_factor;
		MOVE_MAX_SIZE = IMAGE_WIDTH / move_max_factor;
		EXPEND_OFFSET = IMAGE_WIDTH / expend_offset_factor;
		EXPEND_LINE = (int) (1.414D * IMAGE_WIDTH) + EXPEND_OFFSET;
		SHRINK_LINE = IMAGE_WIDTH / shrink_factor;
		PARKING_LINE = (int) (parking_factor * IMAGE_WIDTH);
		EXPEND_LINE_RIGHT = SCREEM_WIDTH - IMAGE_WIDTH - EXPEND_LINE;
		PARKING_LINE_RIGHT = SCREEM_WIDTH - IMAGE_WIDTH - PARKING_LINE;
	}

	public static void init(Activity activity) {
		DisplayMetrics displayMetrics = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay()
				.getMetrics(displayMetrics);
		SCREEM_WIDTH = displayMetrics.widthPixels;
		SCREEM_HEIGHT = displayMetrics.heightPixels;
		IMAGE_WIDTH = 125 * Math.min(SCREEM_WIDTH, SCREEM_HEIGHT) / 720;
	}

	public static void initialPop(Context context) {
		DisplayMetrics displayMetrics = new DisplayMetrics();
		((WindowManager) context.getSystemService(Context.WINDOW_SERVICE))
				.getDefaultDisplay().getMetrics(displayMetrics);
		SCREEM_WIDTH = displayMetrics.widthPixels;
		SCREEM_HEIGHT = displayMetrics.heightPixels;
		setStatusBarHeight(context);
		IMAGE_WIDTH = Math.min(SCREEM_WIDTH, SCREEM_HEIGHT)
				/ image_height_factor;
		MID_LINE = (SCREEM_WIDTH - IMAGE_WIDTH) / mid_line_factor;
		MOVE_MAX_SIZE = IMAGE_WIDTH / move_max_factor;
		EXPEND_OFFSET = IMAGE_WIDTH / expend_offset_factor;
		EXPEND_LINE = (int) (1.414D * IMAGE_WIDTH) + EXPEND_OFFSET;
		SHRINK_LINE = IMAGE_WIDTH / shrink_factor;
		PARKING_LINE = (int) (parking_factor * IMAGE_WIDTH);
		BOTTOM_LINE = SCREEM_HEIGHT - STATUS_HEIGHT - IMAGE_WIDTH - EXPEND_LINE
				/ 2;
		EXPEND_LINE_RIGHT = SCREEM_WIDTH - IMAGE_WIDTH - EXPEND_LINE;
		PARKING_LINE_RIGHT = SCREEM_WIDTH - IMAGE_WIDTH - PARKING_LINE;
	}

	private static void setStatusBarHeight(Context context) {
		try {
			Class clasz = Class.forName("com.android.internal.R$dimen");
			Object object = clasz.newInstance();
			int i = Integer.parseInt(clasz.getField("status_bar_height")
					.get(object).toString());
			STATUS_HEIGHT = context.getResources().getDimensionPixelSize(i);
			Log.i("BAR", "sbar = " + STATUS_HEIGHT);
		} catch (Exception e) {
			Log.i("BAR", "get status bar height fail");
			e.printStackTrace();
		}
	}
}