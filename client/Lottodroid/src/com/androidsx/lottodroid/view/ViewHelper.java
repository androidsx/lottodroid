package com.androidsx.lottodroid.view;

import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.androidsx.lottodroid.R;
import com.androidsx.lottodroid.model.Lottery;

class ViewHelper {

  public static void setHtmlLink(Lottery lottery, View containter) {
	  TextView textView = (TextView) containter.findViewById(R.id.enlace_title);
		if (lottery.getHtmlLink() != null && !lottery.getHtmlLink().equals("")) {
			textView.setText(Html.fromHtml(lottery.getHtmlLink()));	
			textView.setMovementMethod(LinkMovementMethod.getInstance());
		}
  }
}
