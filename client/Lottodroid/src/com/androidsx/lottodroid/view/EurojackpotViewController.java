package com.androidsx.lottodroid.view;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidsx.lottodroid.R;
import com.androidsx.lottodroid.model.Eurojackpot;
import com.androidsx.lottodroid.model.LotteryId;
import com.androidsx.lottodroid.util.DateFormatter;

class EurojackpotViewController implements LotteryViewController<Eurojackpot> {

  private static final long serialVersionUID = 4189136947988075144L;

  private final String title;

  public EurojackpotViewController(String title) {
    this.title = title;
  }

  @Override
  public View createAndFillUpMainView(Eurojackpot eurojackpot, Context context) {
    View layoutView = View.inflate(context, R.layout.main_layout_row, null);
    LinearLayout layoutContent = (LinearLayout) layoutView.findViewById(R.id.layoutContent);
    layoutContent.addView(View.inflate(context, R.layout.eurojackpot_content_row, null));

    ((ImageView) layoutView.findViewById(R.id.icon)).setImageResource(getIconResource());
    ((TextView) layoutView.findViewById(R.id.title)).setText(eurojackpot.getName());
    ((TextView) layoutView.findViewById(R.id.date)).setText(DateFormatter.toSpanishString(eurojackpot
        .getDate()));

    fillUpView(layoutView, eurojackpot);

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
  public View createAndFillUpDetailsView(Eurojackpot eurojackpot, Context context) {
    View convertView = View.inflate(context, R.layout.eurojackpot_content_row, null);

    convertView.setPadding(20, 5, 0, 5);
    convertView.setBackgroundColor(Color.parseColor("#323232"));

    fillUpView(convertView, eurojackpot);

    return convertView;
  }

  private void fillUpView(View view, Eurojackpot eurojackpot) {
    String numbers = eurojackpot.getNum1() + " " + eurojackpot.getNum2() + " " + eurojackpot.getNum3() + " "
        + eurojackpot.getNum4() + " " + eurojackpot.getNum5();

    ((TextView) view.findViewById(R.id.txtNumbers)).setText(numbers);
    ((TextView)
     view.findViewById(R.id.txtS1)).setText(Integer.toString(eurojackpot.getS1()));
     ((TextView)
     view.findViewById(R.id.txtS2)).setText(Integer.toString(eurojackpot.getS2()));
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
    return LotteryId.EURO_JACKPOT;
  }

  @Override
  public View createAndFillUpPrizeView(Eurojackpot lottery, Context context) {
	  View awards =  View.inflate(context, R.layout.premio_layout, null);
		LinearLayout rows = (LinearLayout) awards.findViewById(R.id.premio_list_row);
		View layoutView;
		awards.findViewById(R.id.premios_title).setVisibility(View.GONE);
		
		ViewHelper.setHtmlLink(lottery, awards);
		
		for(int index = 0; index < lottery.getNumPremios(); index++) {
		
		    layoutView = View.inflate(context, R.layout.premio_row, null);
		    
		    ((TextView) layoutView.findViewById(R.id.txtNumAcertantes)).setText("" + lottery.getAcetantes(index));
		    ((TextView) layoutView.findViewById(R.id.txtAwardCategory)).setText(lottery.getCategoria(index));
		    ((TextView) layoutView.findViewById(R.id.txtImporteEuros)).setText(lottery.getImporteEuros(index) + " \u20AC");
		    
		    rows.addView(layoutView);
		
		}
		return awards;
  }

}
