package com.androidsx.lottodroid.view;

import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidsx.lottodroid.R;
import com.androidsx.lottodroid.model.Trio;
import com.androidsx.lottodroid.model.LotteryId;
import com.androidsx.lottodroid.util.DateFormatter;

class TrioViewController implements LotteryViewController<Trio> {

  private static final long serialVersionUID = 4189136947988075144L;

  private final String title;

  public TrioViewController(String title) {
    this.title = title;
  }

  @Override
  public View createAndFillUpMainView(Trio trio, Context context) {
    View layoutView = View.inflate(context, R.layout.main_layout_row, null);
    LinearLayout layoutContent = (LinearLayout) layoutView.findViewById(R.id.layoutContent);
    layoutContent.addView(View.inflate(context, R.layout.trio_content_row, null));

    ((ImageView) layoutView.findViewById(R.id.icon)).setImageResource(getIconResource());
    ((TextView) layoutView.findViewById(R.id.title)).setText(trio.getName());
    ((TextView) layoutView.findViewById(R.id.date)).setText(DateFormatter.toSpanishString(trio
        .getDate()));

    fillUpView(layoutView, trio);

    return layoutView;
  }

  @Override
  public View createAndFillUpOrderView(LotteryId lotteryId, Context context) {
    View layoutView = View.inflate(context, R.layout.main_layout_row, null);

    ((ImageView) layoutView.findViewById(R.id.icon)).setImageResource(getIconResource());
    ((TextView) layoutView.findViewById(R.id.title)).setText(lotteryId.getName());
    ((TextView) layoutView.findViewById(R.id.date)).setText("");

    return layoutView;
  }

  @Override
  public View createAndFillUpDetailsView(Trio trio, Context context) {
    View convertView = View.inflate(context, R.layout.trio_content_row, null);

    convertView.setPadding(20, 5, 0, 5);
    convertView.setBackgroundColor(Color.parseColor("#323232"));

    fillUpView(convertView, trio);

    return convertView;
  }

  private void fillUpView(View view, Trio trio) {
    String numbers = trio.getNum1() + " " + trio.getNum2() + " " + trio.getNum3();

    ((TextView) view.findViewById(R.id.txtNumbers)).setText(numbers);
  }

  @Override
  public int getIconResource() {
    return R.drawable.trio;
  }

  @Override
  public String getTitle() {
    return title;
  }

  @Override
  public LotteryId getId() {
    return LotteryId.TRIO;
  }

  @Override
  public View createAndFillUpPrizeView(Trio lottery, Context context) {
	  View awards =  View.inflate(context, R.layout.premio_layout, null);
		LinearLayout rows = (LinearLayout) awards.findViewById(R.id.premio_list_row);
		View layoutView;
		
		ViewHelper.setHtmlLink(lottery, awards);
	    
		for(int index = 0; index < lottery.getNumPremios(); index++) {
		
		    layoutView = View.inflate(context, R.layout.premio_row, null);
		    
		    ((TextView) layoutView.findViewById(R.id.txtAwardCategory)).setText(lottery.getCategoria(index));
		    ((TextView) layoutView.findViewById(R.id.txtImporteEuros)).setText(lottery.getImporteEuros(index) + " \u20AC");
		    
		    rows.addView(layoutView);
		
		}
		return awards;
  }

}
