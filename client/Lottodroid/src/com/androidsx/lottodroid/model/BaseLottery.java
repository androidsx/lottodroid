package com.androidsx.lottodroid.model;


/**
 * Base implementation for {@link Lottery}, that holds some common information.
 */
abstract class BaseLottery implements Lottery {
	private final String htmlLink;
	
	protected BaseLottery(String htmlLink) {
		this.htmlLink = htmlLink;
	}

	@Override
	public String getHtmlLink() {
		return htmlLink;
	}
}
