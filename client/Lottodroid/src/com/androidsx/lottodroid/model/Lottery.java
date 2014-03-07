package com.androidsx.lottodroid.model;

import java.util.Date;

/**
 * Represents the results for a lottery draw.
 */
public interface Lottery {
  
  public LotteryId getId();
  public String getName();
  public Date getDate();
  
  /**
   * HTML for a link to more information about this lottery draw.
   * 
   * @return HTML for a link with more info
   */
  public String getHtmlLink();
}
