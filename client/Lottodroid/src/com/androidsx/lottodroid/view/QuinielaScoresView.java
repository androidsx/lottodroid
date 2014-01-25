package com.androidsx.lottodroid.view;

import java.util.Date;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidsx.lottodroid.R;
import com.androidsx.lottodroid.model.FootballLotery;
import com.androidsx.lottodroid.model.Lottery;
import com.androidsx.lottodroid.model.LotteryId;
import com.androidsx.lottodroid.model.Quiniela;
import com.androidsx.lottodroid.model.Quinigol;
import com.androidsx.lottodroid.util.DateFormatter;

public class QuinielaScoresView {
  private static final String TAG = QuinielaScoresView.class.getSimpleName();

  public View createAndFillUpScoresView(Lottery lottery, Context context) {
	final FootballLotery footballLotery;
	if(lottery.getId() == LotteryId.QUINIELA)
		footballLotery = (Quiniela) lottery;
	else if(lottery.getId() == LotteryId.QUINIGOL)
		footballLotery = (Quinigol) lottery;
	else {
		footballLotery = new EmptyFootballLottery();
		Log.e(TAG, "Didn't expect quiniela scores of type " + lottery.getId());
	}
	  
    View layoutView = View.inflate(context, R.layout.main_layout_row, null);
    LinearLayout layoutContent = (LinearLayout) layoutView.findViewById(R.id.layoutContent);

    ((ImageView) layoutView.findViewById(R.id.icon)).setImageResource(R.drawable.quiniela);
    ((TextView) layoutView.findViewById(R.id.title)).setText(footballLotery.getName());
    ((TextView) layoutView.findViewById(R.id.date)).setText(DateFormatter.toSpanishString(footballLotery
        .getDate()));
    
    View premioRow;
    
    final int numMatches = footballLotery.getNumMatches();
    
    for (int i = 0; i < numMatches; i++) {
	  
		premioRow =  View.inflate(context, R.layout.scores_row, null);
				
	    ((TextView) premioRow.findViewById(R.id.txtLocal)).setText(footballLotery.getHomeTeam(i));
	    ((TextView) premioRow.findViewById(R.id.txtLocalResult)).setText(""+footballLotery.getHomeScore(i));
	    ((TextView) premioRow.findViewById(R.id.txtVisitant)).setText(footballLotery.getAwayTeam(i));
	    ((TextView) premioRow.findViewById(R.id.txtVisitantResult)).setText(""+footballLotery.getAwayScore(i));
	    
	    layoutContent.addView(premioRow);
    }
    
    return layoutView;
  }
  
  /**
   * Local <em>null object</em> for empty results for football lottery.
   */
  private static final class EmptyFootballLottery implements FootballLotery {
	@Override
	public LotteryId getId() {
		throw new IllegalStateException();
	}

	@Override
	public String getName() {
		return "Sin resultados";
	}

	@Override
	public Date getDate() {
		return new Date();
	}

	@Override
	public int getNumMatches() {
		return 0;
	}

	@Override
	public String getHomeTeam(int matchNumber) throws ArrayIndexOutOfBoundsException, NullPointerException {
		return "";
	}

	@Override
	public String getAwayTeam(int matchNumber) throws ArrayIndexOutOfBoundsException, NullPointerException {
		return "";
	}

	@Override
	public void setScore(int matchNumber, int homeScore, int awayScore) throws ArrayIndexOutOfBoundsException {
	}

	@Override
	public int getHomeScore(int machtNumber) throws ArrayIndexOutOfBoundsException, NullPointerException {
		return 0;
	}

	@Override
	public int getAwayScore(int machtNumber) throws ArrayIndexOutOfBoundsException, NullPointerException {
		return 0;
	}
  }
}
