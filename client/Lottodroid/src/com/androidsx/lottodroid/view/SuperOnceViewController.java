package com.androidsx.lottodroid.view;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidsx.lottodroid.R;
import com.androidsx.lottodroid.model.SuperOnce;
import com.androidsx.lottodroid.model.LotteryId;
import com.androidsx.lottodroid.util.DateFormatter;

class SuperOnceViewController implements LotteryViewController<SuperOnce> {

  private static final long serialVersionUID = 3726644726024636635L;

  private final String title;

  public SuperOnceViewController(String title) {
    this.title = title;
  }

  @Override
  public View createAndFillUpMainView(SuperOnce superOnce, Context context) {
    View layoutView = View.inflate(context, R.layout.main_layout_row, null);
    LinearLayout layoutContent = (LinearLayout) layoutView.findViewById(R.id.layoutContent);
    layoutContent.addView(View.inflate(context, R.layout.super_once_content_row, null));

    ((ImageView) layoutView.findViewById(R.id.icon)).setImageResource(getIconResource());
    ((TextView) layoutView.findViewById(R.id.title)).setText(superOnce.getName());
    ((TextView) layoutView.findViewById(R.id.date)).setText(DateFormatter.toSpanishString(superOnce
        .getDate()));

    fillUpView(layoutView, superOnce);

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
  public View createAndFillUpDetailsView(SuperOnce superOnce, Context context) {
    View convertView = View.inflate(context, R.layout.super_once_content_row, null);

    convertView.setPadding(20, 5, 0, 5);
    convertView.setBackgroundColor(Color.parseColor("#323232"));

    fillUpView(convertView, superOnce);

    return convertView;
  }

  private void fillUpView(View view, SuperOnce superOnce) {
    ((TextView) view.findViewById(R.id.txtNumbers)).setText(superOnce.getResult());
  }

  @Override
  public int getIconResource() {
    return R.drawable.once;
  }

  @Override
  public String getTitle() {
    return title;
  }

  @Override
  public LotteryId getId() {
    return LotteryId.SUPER_ONCE;
  }

  @Override
  public View createAndFillUpPrizeView(SuperOnce lottery, Context context) {

	View awards =  View.inflate(context, R.layout.premio_layout, null);
	LinearLayout rows = (LinearLayout) awards.findViewById(R.id.premio_list_row);
	View layoutView;
	
	awards.findViewById(R.id.premios_title).setVisibility(View.GONE);
	ViewHelper.setHtmlLink(lottery, awards);
	for(int index = 0; index < lottery.getNumPremios(); index++) {
	
	    layoutView = View.inflate(context, R.layout.premio_once_row, null);
	     
	    // Capitalize the the first letter.
	    ((TextView) layoutView.findViewById(R.id.txtAwardCategory)).setText(lottery.getCategoria(index)
	    		.substring(0, 1).toUpperCase() + lottery.getCategoria(index)
	    		.substring(1, lottery.getCategoria(index).length()));
	    ((TextView) layoutView.findViewById(R.id.txtImporteEuros)).setText(lottery.getImporteEuros(index) + " \u20AC");
	    
	    rows.addView(layoutView);
	
	}
	return awards;
  }

}
