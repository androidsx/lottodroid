package com.androidsx.lottodroid.communication;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.util.Log;

import com.androidsx.lottodroid.model.Bonoloto;
import com.androidsx.lottodroid.model.CuponExtraordinario;
import com.androidsx.lottodroid.model.CuponazoOnce;
import com.androidsx.lottodroid.model.Eurojackpot;
import com.androidsx.lottodroid.model.Euromillon;
import com.androidsx.lottodroid.model.GordoPrimitiva;
import com.androidsx.lottodroid.model.Loteria7_39;
import com.androidsx.lottodroid.model.LoteriaNacional;
import com.androidsx.lottodroid.model.Lototurf;
import com.androidsx.lottodroid.model.Lottery;
import com.androidsx.lottodroid.model.Lotto6_49;
import com.androidsx.lottodroid.model.Once;
import com.androidsx.lottodroid.model.OnceFinde;
import com.androidsx.lottodroid.model.Primitiva;
import com.androidsx.lottodroid.model.Quiniela;
import com.androidsx.lottodroid.model.Quinigol;
import com.androidsx.lottodroid.model.QuintuplePlus;
import com.androidsx.lottodroid.model.Super10;
import com.androidsx.lottodroid.model.SuperOnce;
import com.androidsx.lottodroid.model.Trio;

/**
 * Parser for the all the lottery draws ( bonoloto, quiniela, etc. ) retrieved
 * from the Lotoluck.
 * 
 * Convert the information into {@link Lottery} value objects
 */
class LotteryXMLParser {
    private static final String TAG = "LotteryXMLParser";

	public static final int SORTEOS = 0;
	public static final int PRIMITIVA = 1;
	public static final int BONOLOTO = 2;
	public static final int GORDO_PRIMITIVA = 3;
	public static final int LOTTO6_49 = 4;
	public static final int LOTERIA_NACIONAL = 9;
	public static final int ONCE = 10;
	public static final int CUPONAZO_ONCE = 11;
	public static final int ONCE_FINDE = 12;
	public static final int QUINIELA = 13;
	public static final int EUROMILLONES = 14;
	public static final int COMBO = 15;
	public static final int LOTOTURF = 16;
	public static final int TRIO = 20;
	public static final int QUINTUPLE_PLUS = 17;
	public static final int QUINIGOL = 18;
	public static final int LOTERIA_7_39 = 19;
	public static final int CUPON_EXTRAORDINARIO = 23;
	public static final int EURO_JACKPOT = 27;
	public static final int SUPER_ONCE = 22;
	public static final int SUPER_10 = 21;

	/**
	 * Field inside the {@code Juego} tag of every item that contains an HTML
	 * link that corresponds to {@link Lottery#getHtmlLink()}.
	 */
	private static final String FIELD_HTML_LINK = "enlace";

	private static final DateFormat dfm = new SimpleDateFormat("yyyy-MM-dd");

	private LotteryXMLParser() {
		// Instantiation is not allowed for this class
	}

	/**
	 * Parse the string containing the information of one or more bonolotos and
	 * converts them into a list of {@link Bonoloto} value objects
	 * 
	 * @param response
	 *            String that represents the response from lotoluck to a lottery
	 *            request
	 * @return List<Lottery> A list of lottery objects representing the data
	 * @throws LotteryParseException
	 */
	public static List<Bonoloto> parseBonoloto(String response)
			throws LotteryParseException {

		try {
			return parseBonolotoData(parseContentTypeAndGetData(response,
					BONOLOTO));

		} catch (DOMException e) {
			throw new LotteryParseException("Error parsing content");
		} catch (ParseException e) {
			throw new LotteryParseException("Error parsing data");
		}
	}

	/**
	 * Analogous to {@link #parseBonoloto}
	 */
	public static List<Quiniela> parseQuiniela(String response)
			throws LotteryParseException {

		try {
			return parseQuinielaData(parseContentTypeAndGetData(response,
					QUINIELA));

		} catch (DOMException e) {
			throw new LotteryParseException("Error parsing content");
		} catch (ParseException e) {
			throw new LotteryParseException("Error parsing data");
		}
	}

	/**
	 * Analogous to {@link #parseBonoloto}
	 */
	public static List<GordoPrimitiva> parseGordoPrimitiva(String response)
			throws LotteryParseException {

		try {
			return parseGordoPrimitivaData(parseContentTypeAndGetData(response,
					GORDO_PRIMITIVA));

		} catch (DOMException e) {
			throw new LotteryParseException("Error parsing content");
		} catch (ParseException e) {
			throw new LotteryParseException("Error parsing data");
		}
	}

	/**
	 * Analogous to {@link #parseBonoloto}
	 */
	public static List<Primitiva> parsePrimitiva(String response)
			throws LotteryParseException {

		try {
			return parsePrimitivaData(parseContentTypeAndGetData(response,
					PRIMITIVA));

		} catch (DOMException e) {
			throw new LotteryParseException("Error parsing content");
		} catch (ParseException e) {
			throw new LotteryParseException("Error parsing data");
		}
	}

	/**
	 * Analogous to {@link #parseBonoloto}
	 */
	public static List<Lototurf> parseLototurf(String response)
			throws LotteryParseException {

		try {
			return parseLototurfData(parseContentTypeAndGetData(response,
					LOTOTURF));

		} catch (DOMException e) {
			throw new LotteryParseException("Error parsing content");
		} catch (ParseException e) {
			throw new LotteryParseException("Error parsing data");
		}
	}

	/**
	 * Analogous to {@link #parseBonoloto}
	 */
	public static List<Euromillon> parseEuromillon(String response)
			throws LotteryParseException {

		try {
			return parseEuromillonData(parseContentTypeAndGetData(response,
					EUROMILLONES));

		} catch (DOMException e) {
			throw new LotteryParseException("Error parsing content");
		} catch (ParseException e) {
			throw new LotteryParseException("Error parsing data");
		}
	}

	/**
	 * Analogous to {@link #parseBonoloto}
	 */
	public static List<LoteriaNacional> parseLoteriaNacional(String response)
			throws LotteryParseException {

		try {
			return parseLoteriaNacionalData(parseContentTypeAndGetData(
					response, LOTERIA_NACIONAL));

		} catch (DOMException e) {
			throw new LotteryParseException("Error parsing content");
		} catch (ParseException e) {
			throw new LotteryParseException("Error parsing data");
		}

	}

	/**
	 * Analogous to {@link #parseBonoloto}
	 */
	public static List<Quinigol> parseQuinigol(String response)
			throws LotteryParseException {

		try {
			return parseQuinigolData(parseContentTypeAndGetData(response,
					QUINIGOL));

		} catch (DOMException e) {
			throw new LotteryParseException("Error parsing content");
		} catch (ParseException e) {
			throw new LotteryParseException("Error parsing data");
		}
	}
	
	/**
	 * Analogous to {@link #parseBonoloto}
	 */
	public static List<CuponazoOnce> parseCuponazoOnce(String response)
			throws LotteryParseException {

		try {
			return parseCuponazoOnceData(parseContentTypeAndGetData(response,
					CUPONAZO_ONCE));

		} catch (DOMException e) {
			throw new LotteryParseException("Error parsing content");
		} catch (ParseException e) {
			throw new LotteryParseException("Error parsing data");
		}
	}
	
	/**
	 * Analogous to {@link #parseBonoloto}
	 */
	public static List<Loteria7_39> parseLoteria7_39(String response)
			throws LotteryParseException {

		try {
			return parseLoteria7_39Data(parseContentTypeAndGetData(response,
					LOTERIA_7_39));

		} catch (DOMException e) {
			throw new LotteryParseException("Error parsing content");
		} catch (ParseException e) {
			throw new LotteryParseException("Error parsing data");
		}
	}
	
	/**
	 * Analogous to {@link #parseBonoloto}
	 */
	public static List<Lotto6_49> parseLotto6_49(String response)
			throws LotteryParseException {

		try {
			return parseLotto6_49Data(parseContentTypeAndGetData(response,
					LOTTO6_49));

		} catch (DOMException e) {
			throw new LotteryParseException("Error parsing content");
		} catch (ParseException e) {
			throw new LotteryParseException("Error parsing data");
		}
	}	

	/**
	 * Analogous to {@link #parseBonoloto}
	 */
	public static List<Once> parseOnce(String response)
			throws LotteryParseException {

		try {
			return parseOnceData(parseContentTypeAndGetData(response,
					ONCE));

		} catch (DOMException e) {
			throw new LotteryParseException("Error parsing content");
		} catch (ParseException e) {
			throw new LotteryParseException("Error parsing data");
		}
	}	
	
	/**
	 * Analogous to {@link #parseBonoloto}
	 */
	public static List<OnceFinde> parseOnceFinde(String response)
			throws LotteryParseException {

		try {
			return parseOnceFindeData(parseContentTypeAndGetData(response,
					ONCE_FINDE));

		} catch (DOMException e) {
			throw new LotteryParseException("Error parsing content");
		} catch (ParseException e) {
			throw new LotteryParseException("Error parsing data");
		}
	}	
	
	/**
	 * Analogous to {@link #parseBonoloto}
	 */
	public static List<QuintuplePlus> parseQuintuplePlus(String response)
			throws LotteryParseException {

		try {
			return parseQuintuplePlusData(parseContentTypeAndGetData(response,
					QUINTUPLE_PLUS));

		} catch (DOMException e) {
			throw new LotteryParseException("Error parsing content");
		} catch (ParseException e) {
			throw new LotteryParseException("Error parsing data");
		}
	}	
	
	public static List<Trio> parseTrio(String response)
			throws LotteryParseException {

		try {
			return parseTrioData(parseContentTypeAndGetData(response,
					TRIO));

		} catch (DOMException e) {
			throw new LotteryParseException("Error parsing content");
		} catch (ParseException e) {
			throw new LotteryParseException("Error parsing data");
		}
	}	
	

	public static List<CuponExtraordinario> parseCuponExtraordinario(String response)
			throws LotteryParseException {

		try {
			return parseCuponExtraordinarioData(parseContentTypeAndGetData(response,
					CUPON_EXTRAORDINARIO));

		} catch (DOMException e) {
			throw new LotteryParseException("Error parsing content");
		} catch (ParseException e) {
			throw new LotteryParseException("Error parsing data");
		}
	}	
	
	public static List<Eurojackpot> parseEurojackpot(String response)
			throws LotteryParseException {

		try {
			return parseEurojackpotData(parseContentTypeAndGetData(response,
					EURO_JACKPOT));

		} catch (DOMException e) {
			throw new LotteryParseException("Error parsing content");
		} catch (ParseException e) {
			throw new LotteryParseException("Error parsing data");
		}
	}	
	
	public static List<SuperOnce> parseSuperOnce(String response)
			throws LotteryParseException {

		try {
			return parseSuperOnceData(parseContentTypeAndGetData(response,
					SUPER_ONCE));

		} catch (DOMException e) {
			throw new LotteryParseException("Error parsing content");
		} catch (ParseException e) {
			throw new LotteryParseException("Error parsing data");
		}
	}	
	
	public static List<Super10> parseSuper10(String response)
			throws LotteryParseException {

		try {
			return parseSuper10Data(parseContentTypeAndGetData(response,
					SUPER_10));

		} catch (DOMException e) {
			throw new LotteryParseException("Error parsing content");
		} catch (ParseException e) {
			throw new LotteryParseException("Error parsing data");
		}
	}	
	
	/**
	 * Parse the string containing the information of the last results of every
	 * lottery draw and converts them into a list of {@link Lottery} value
	 * objects
	 * 
	 * @param url
	 *            String that represents the response from lotoluck to a lottery
	 *            request
	 * @return List<Lottery> A list of lottery objects representing the data
	 * @throws LotteryParseException
	 */
	public static List<Lottery> parseAllLotteries(String url)
			throws LotteryParseException {

	    String postParameters = null;
		try {
		    // HACK: remove the "?" and send a POST to lotoluck
		    int startQueryString = url.indexOf("?");
		    if (startQueryString != -1) {
		        postParameters = url.substring(startQueryString + 1, url.length());    
		        url = url.substring(0, startQueryString);
		    }
		    
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			
			HttpURLConnection http = (HttpURLConnection) new URL(url).openConnection();
			http.setRequestMethod("POST");			
			http.setDoInput(true);
			http.setDoOutput(true);
			
            if (postParameters != null) {
                http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                http.setFixedLengthStreamingMode(postParameters.getBytes().length);

                // send the POST out
                PrintWriter out = new PrintWriter(http.getOutputStream());
                out.print(postParameters);
                out.close();
            }
            
            int statusCode = http.getResponseCode();
            if (statusCode != HttpURLConnection.HTTP_OK) {
                // throw some exception
                Log.v(TAG, "" + statusCode);
            }
			
			Document doc = dBuilder.parse(http.getInputStream());
			doc.getDocumentElement().normalize();
			String rootElement = doc.getDocumentElement().getNodeName();
			if (rootElement.matches("error"))
				throw new LotteryParseException("No elements found");
			Element game;
			int id = 0;

			List<Lottery> listLottery = new LinkedList<Lottery>();
			NodeList games = doc.getElementsByTagName("Juego");
			
			for (int i = 0; i < games.getLength(); i++) {
				game = (Element) games.item(i);
				
				id = Integer.parseInt(game.getElementsByTagName("IdJuego")
						.item(0).getFirstChild().getNodeValue().trim());
				
				try {

					switch (id) {
					
					case BONOLOTO:
						listLottery.add(parseBonolotoData(game).get(0));
						break;
					case CUPONAZO_ONCE:
						listLottery.add(parseCuponazoOnceData(game).get(0));
						break;
					case EUROMILLONES:
						listLottery.add(parseEuromillonData(game).get(0));
						break;
					case GORDO_PRIMITIVA:
						listLottery.add(parseGordoPrimitivaData(game).get(0));
						break;
					case LOTERIA_NACIONAL:
						listLottery.add(parseLoteriaNacionalData(game).get(0));
						break;
					case LOTERIA_7_39:
						listLottery.add(parseLoteria7_39Data(game).get(0));
						break;
					case LOTTO6_49:
						listLottery.add(parseLotto6_49Data(game).get(0));
						break;
					case LOTOTURF:
						listLottery.add(parseLototurfData(game).get(0));
						break;
					case ONCE:
						listLottery.add(parseOnceData(game).get(0));
						break;
					case ONCE_FINDE:
						listLottery.add(parseOnceFindeData(game).get(0));
						break;
					case PRIMITIVA:
						listLottery.add(parsePrimitivaData(game).get(0));
						break;
					case QUINTUPLE_PLUS:
						listLottery.add(parseQuintuplePlusData(game).get(0));
						break;
					case QUINIELA:
						listLottery.add(parseQuinielaData(game).get(0));
						break;
					case QUINIGOL:
						listLottery.add(parseQuinigolData(game).get(0));
						break;
					case TRIO:
						listLottery.add(parseTrioData(game).get(0));
						break;
					case CUPON_EXTRAORDINARIO: 
						listLottery.add(parseCuponExtraordinarioData(game).get(0));
						break;
					case EURO_JACKPOT:
						listLottery.add(parseEurojackpotData(game).get(0));
						break;
					case SUPER_ONCE:
						listLottery.add(parseSuperOnceData(game).get(0));
						break;
					case SUPER_10:
						listLottery.add(parseSuper10Data(game).get(0));
						break;
	
					}
					
				} catch(Exception ex) {
				    Log.w(TAG, "Cannot parse lottery id " + id);
                    ex.printStackTrace();
					continue;
				}
			}

			return listLottery;

		} catch (ParserConfigurationException e) {
		    e.printStackTrace();
			throw new LotteryParseException("Error in configurating the parser", e);
		} catch (SAXException e) {
		    e.printStackTrace();
			throw new LotteryParseException("Error parsing content", e);
		} catch (IOException e) {
		    e.printStackTrace();
			throw new LotteryParseException("Error connecting to the url", e);
		} catch (DOMException e) {
		    e.printStackTrace();
			throw new LotteryParseException("Error parsing content", e);
		}

	}

	/**
	 * From an unformatted date (YYYYMMDD) returns a formatted one (YYYY-MM-DD)
	 * 
	 * @param unformattedDate
	 * @return formattedDate
	 */
	private static String formatDate(String unformatedDate) {
		StringBuilder formatedDate = new StringBuilder();
		return formatedDate.append(unformatedDate.subSequence(0, 4))
				.append("-").append(unformatedDate.subSequence(4, 6))
				.append("-").append(unformatedDate.subSequence(6, 8))
				.toString();
	}

	/**
	 * Parse the string containing the information of a lottery draw checking
	 * that the data retrieved is the same type as the one requested
	 * 
	 * @param url
	 *            Represents the response from the server to a lottery request
	 * @param contentType
	 *            Lottery type requested
	 * @return Data retrieved for a lottery draw
	 * @throws LotteryParseException
	 */
	private static Element parseContentTypeAndGetData(String url,
			int contentType) throws LotteryParseException {

	    String postParameters = null;
		try {
	          // HACK: remove the "?" and send a POST to lotoluck
            int startQueryString = url.indexOf("?");
            if (startQueryString != -1) {
                postParameters = url.substring(startQueryString + 1, url.length());    
                url = url.substring(0, startQueryString);
            }
		    
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		     
            HttpURLConnection http = (HttpURLConnection) new URL(url).openConnection();
            http.setRequestMethod("POST");
            http.setDoInput(true);
            http.setDoOutput(true);
            
            if (postParameters != null) {
                http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                http.setFixedLengthStreamingMode(postParameters.getBytes().length);

                // send the POST out
                PrintWriter out = new PrintWriter(http.getOutputStream());
                out.print(postParameters);
                out.close();
                
            }
            int statusCode = http.getResponseCode();
            if (statusCode != HttpURLConnection.HTTP_OK) {
                // throw some exception
                Log.v(TAG, "" + statusCode);
            }

            Document doc = dBuilder.parse(http.getInputStream());

			doc.getDocumentElement().normalize();

			String rootElement = doc.getDocumentElement().getNodeName();
			if (rootElement.matches("error"))
				throw new LotteryParseException("No elements found");

			Element game;
			int id = 0;

			NodeList games = doc.getElementsByTagName("Juego");
			game = (Element) games.item(0);
			id = Integer.parseInt((game.getElementsByTagName("IdJuego")
					.item(0).getFirstChild().getNodeValue().trim()));

			if (id == contentType)
				return game;

			throw new LotteryParseException("Wrong controller selected");

		} catch (Exception e) {
		    e.printStackTrace();
			throw new LotteryParseException("Error parsing data", e);
		}
	}

	private static List<Bonoloto> parseBonolotoData(Element game)
			throws DOMException, ParseException {
		
		Date date = dfm.parse(formatDate(game.getElementsByTagName("Fecha").item(0).getFirstChild().getNodeValue()));
		System.out.println("\n\nBonoloto:  " + date);

		// 6 results + reintegro + complementario
		int num[] = new int[8];
		NodeList results = game.getElementsByTagName("Resultado");
		NamedNodeMap values; Element result;
		
		for (int i = 0; i < results.getLength(); i++) {
			result = (Element) results.item(i);
			values = result.getAttributes();
			num[i] = Integer.parseInt(formatNumber(values.getNamedItem("Valor").getNodeValue()));
			System.out.print(num[i] + " ");
		}
		
		Bonoloto bonoloto = new Bonoloto(date, extractHtmlLink(game), num[0], num[1], num[2], num[3],
				num[4], num[5], num[7], num[6]);
		
		results = game.getElementsByTagName("Premio");

		for (int i = 0; i < results.getLength(); i++) {
			result = (Element) results.item(i);
			values = result.getAttributes();
			
			try {
    			bonoloto.addPremio(
    					Integer.parseInt(formatNumber(values.getNamedItem("Acertantes").getNodeValue())),
    					values.getNamedItem("Categoria").getNodeValue(),
    					Float.parseFloat(formatNumber(values.getNamedItem("ImporteEuros").getNodeValue())),
    					0);
    			System.out.println(bonoloto.getPremio(i));
			} catch (Exception ex) {
			    Log.w(TAG, "Error during parsing premio of the bonotolo", ex);
			}
		}
		List<Bonoloto> lotteryList = new LinkedList<Bonoloto>();
		lotteryList.add(bonoloto);

		return lotteryList;
		
	}
	
	private static List<CuponazoOnce> parseCuponazoOnceData(Element game)
			throws DOMException, ParseException {

		Date date = dfm.parse(formatDate(game.getElementsByTagName("Fecha").item(0).getFirstChild().getNodeValue()));
				
				
		/*
		 * game.getElementsByTagName("Fecha") .item(0).getTextContent()));
		 */
		System.out.println("\n\nCuponazo Once:  " + date);

		String num = "", serie = "", series_adicio = "";
		NodeList results = game.getElementsByTagName("Resultado");
		NamedNodeMap values; Element result;
		
		for (int i = 0; i < results.getLength(); i++) {
			result = (Element) results.item(i);
			values = result.getAttributes();
			if(i == 0)
				num = values.getNamedItem("Valor").getNodeValue();
			if(i == 1)
				serie = values.getNamedItem("Valor").getNodeValue();
			if(i == 2)
				series_adicio = values.getNamedItem("Valor").getNodeValue();
		}
		
		System.out.print("Num: " + num + "Serie: " + serie + "Series adicionales: " + series_adicio);
		
		CuponazoOnce cuponazoOnce = new CuponazoOnce(date, extractHtmlLink(game), num, serie, series_adicio);
		
		results = game.getElementsByTagName("Premio");

		for (int i = 0; i < results.getLength(); i++) {
			result = (Element) results.item(i);
			values = result.getAttributes();
			if(i < 6 && values.getNamedItem("Categoria") != null) {
			cuponazoOnce.addPremio(
					values.getNamedItem("Categoria").getNodeValue(),
					Float.parseFloat(values.getNamedItem("ImporteEuros").getNodeValue().trim().replace(".", "").replace(",", ".")),
					0);
			} else if(i == 7) { 
				// TODO: Manual from here because do not know how to parse from UTF-8
				cuponazoOnce.addPremio(
						"A las cuatro \u00FAltimas cifras",
						Float.parseFloat(values.getNamedItem("ImporteEuros").getNodeValue().trim().replace(".", "").replace(",", ".")),
						0);
			} else if(i == 8) {
				cuponazoOnce.addPremio(
						"A las tres \u00FAltimas cifras",
						Float.parseFloat(values.getNamedItem("ImporteEuros").getNodeValue().trim().replace(".", "").replace(",", ".")),
						0);
			} else if(i == 9) {
				cuponazoOnce.addPremio(
						"A las dos \u00FAltimas cifras",
						Float.parseFloat(values.getNamedItem("ImporteEuros").getNodeValue().trim().replace(".", "").replace(",", ".")),
						0);
			} else if(i == 10) {
				cuponazoOnce.addPremio(
						"A la \u00FAltima cifra",
						Float.parseFloat(values.getNamedItem("ImporteEuros").getNodeValue().trim().replace(".", "").replace(",", ".")),
						0);
			} 
		}
		
		List<CuponazoOnce> lotteryList = new LinkedList<CuponazoOnce>();
		lotteryList.add(cuponazoOnce);

		return lotteryList;
	}

	private static List<GordoPrimitiva> parseGordoPrimitivaData(Element game)
			throws DOMException, ParseException {

		Date date = dfm.parse(formatDate(game.getElementsByTagName("Fecha").item(0).getFirstChild().getNodeValue()));
		System.out.println("\n\nGordoPrimitiva:  " + date);

		// 5 resultados + reintegro
		int num[] = new int[6];
		NodeList results = game.getElementsByTagName("Resultado");
		NamedNodeMap values; Element result;
		
		for (int i = 0; i < results.getLength(); i++) {
			result = (Element) results.item(i);
			values = result.getAttributes();
			num[i] = Integer.parseInt(formatNumber(values.getNamedItem("Valor").getNodeValue()));
			System.out.print(num[i] + " ");
		}
		
		GordoPrimitiva gordoPrimitiva = new GordoPrimitiva(date, extractHtmlLink(game), num[0],
				num[1], num[2], num[3], num[4], num[5]);
		
		results = game.getElementsByTagName("Premio");

		for (int i = 0; i < results.getLength(); i++) {
			result = (Element) results.item(i);
			values = result.getAttributes();
			gordoPrimitiva.addPremio(
					Integer.parseInt(formatNumber(values.getNamedItem("Acertantes").getNodeValue())),
					values.getNamedItem("Categoria").getNodeValue(),
					Float.parseFloat(values.getNamedItem("ImporteEuros").getNodeValue().trim().replace(".", "").replace(",", ".")),
					0);
			System.out.println(gordoPrimitiva.getPremio(i));
		}
		List<GordoPrimitiva> lotteryList = new LinkedList<GordoPrimitiva>();
		lotteryList.add(gordoPrimitiva);

		return lotteryList;
	}
	
	private static List<Loteria7_39> parseLoteria7_39Data(Element game) 
			throws DOMException, ParseException {

		Date date = dfm.parse(formatDate(game.getElementsByTagName("Fecha").item(0).getFirstChild().getNodeValue()));
		System.out.println("\n\n7/39 de la ONCE:  " + date);

		// 7 resultados + reintegro
		int num[] = new int[8];
		NodeList results = game.getElementsByTagName("Resultado");
		NamedNodeMap values; Element result;
		
		for (int i = 0; i < results.getLength(); i++) {
			result = (Element) results.item(i);
			values = result.getAttributes();
			num[i] = Integer.parseInt(formatNumber(values.getNamedItem("Valor").getNodeValue()));
			System.out.print(num[i] + " ");
		}
		
		Loteria7_39 loteria7_39 = new Loteria7_39(date, extractHtmlLink(game), num[0], num[1], num[2], num[3],
				num[4], num[5], num[6], num[7]);
		
		results = game.getElementsByTagName("Premio");

		for (int i = 0; i < results.getLength(); i++) {
			result = (Element) results.item(i);
			values = result.getAttributes();
			loteria7_39.addPremio(
					Integer.parseInt(formatNumber(values.getNamedItem("Acertantes").getNodeValue())),
					values.getNamedItem("Categoria").getNodeValue(),
					Float.parseFloat(values.getNamedItem("ImporteEuros").getNodeValue().trim().replace(".", "").replace(",", ".")),
					0);
			System.out.println(loteria7_39.getPremio(i));
		}
		
		List<Loteria7_39> lotteryList = new LinkedList<Loteria7_39>();
		lotteryList.add(loteria7_39);

		return lotteryList;
	}
	
	private static List<Lotto6_49> parseLotto6_49Data(Element game) 
			throws DOMException, ParseException {

		Date date = dfm.parse(formatDate(game.getElementsByTagName("Fecha").item(0).getFirstChild().getNodeValue()));
		System.out.println("\n\nLotto Catalunya 6/49:  " + date);

		// 6 resultados + complementario + reintegro
		int num[] = new int[8];
		String joker = "";
		NodeList results = game.getElementsByTagName("Resultado");
		NamedNodeMap values; Element result;
		
		for (int i = 0; i < results.getLength(); i++) {
			result = (Element) results.item(i);
			values = result.getAttributes();
			if(i < num.length) {
				num[i] = Integer.parseInt(formatNumber(values.getNamedItem("Valor").getNodeValue()));
				System.out.print(num[i] + " ");
			} else {
				joker = values.getNamedItem("Valor").getNodeValue().trim();
				System.out.print(joker + " ");
			}
		}
		
		Lotto6_49 lotto6_49 = new Lotto6_49(date, extractHtmlLink(game), num[0], num[1], num[2], num[3],
				num[4], num[5], num[6], num[7], joker);
		
		results = game.getElementsByTagName("Premio");

		for (int i = 0; i < results.getLength(); i++) {
			result = (Element) results.item(i);
			values = result.getAttributes();
			lotto6_49.addPremio(
					Integer.parseInt(formatNumber(values.getNamedItem("Acertantes").getNodeValue())),
					values.getNamedItem("Categoria").getNodeValue(),
					Float.parseFloat(values.getNamedItem("ImporteEuros").getNodeValue().trim().replace(".", "").replace(",", ".")),
					0);
			System.out.println(lotto6_49.getPremio(i));
		}
		
		List<Lotto6_49> lotteryList = new LinkedList<Lotto6_49>();
		lotteryList.add(lotto6_49);

		return lotteryList;
	}
	
	private static List<Once> parseOnceData(Element game) 
			throws DOMException, ParseException {
	
		Date date = dfm.parse(formatDate(game.getElementsByTagName("Fecha").item(0).getFirstChild().getNodeValue()));
		System.out.println("\n\nOnce:  " + date);

		String num = "", serie = "";
		NodeList results = game.getElementsByTagName("Resultado");
		NamedNodeMap values; Element result;
		
		for (int i = 0; i < results.getLength(); i++) {
			result = (Element) results.item(i);
			values = result.getAttributes();
			if(i == 0)
				num = values.getNamedItem("Valor").getNodeValue();
			if(i == 1)
				serie = values.getNamedItem("Valor").getNodeValue();
		}
		
		System.out.print("Num: " + num + "  Serie: " + serie);
		
		Once once = new Once(date, extractHtmlLink(game), num, serie);
		
		results = game.getElementsByTagName("Premio");
     
        String euros;
		for (int i = 0; i < results.getLength(); i++) {
			result = (Element) results.item(i);
			values = result.getAttributes();
			
            euros = values.getNamedItem("ImporteEuros").getNodeValue();
            if (euros.contains("durante")) {
                String[] junks = euros.split(" ");
                euros = junks[0];
            }
			
			once.addPremio(
					values.getNamedItem("Categoria").getNodeValue(),
					Float.parseFloat(euros.trim().replace(".", "").replace(",", ".")),
					0);
			System.out.println(once.getPremio(i));
		}
		
		List<Once> lotteryList = new LinkedList<Once>();
		lotteryList.add(once);

		return lotteryList;
	}
	
	private static List<OnceFinde> parseOnceFindeData(Element game) 
			throws DOMException, ParseException {
	
		Date date = dfm.parse(formatDate(game.getElementsByTagName("Fecha").item(0).getFirstChild().getNodeValue()));
		System.out.println("\n\nOnce fin de semana:  " + date);

		String num = "", serie = "";
		NodeList results = game.getElementsByTagName("Resultado");
		NamedNodeMap values; Element result;
		
		for (int i = 0; i < results.getLength(); i++) {
			result = (Element) results.item(i);
			values = result.getAttributes();
			if(i == 0)
				num = values.getNamedItem("Valor").getNodeValue();
			if(i == 1)
				serie = values.getNamedItem("Valor").getNodeValue();
		}
		
		System.out.print("Num: " + num + "  Serie: " + serie);
		
		OnceFinde onceFinde = new OnceFinde(date, extractHtmlLink(game), num, serie);
		
		results = game.getElementsByTagName("Premio");
		String categorias[] = { "A las cinco cifras y serie (durante 25 a\u00F1os)",
				"A las cinco cifras", "A las cuatro \u00FAltimas cifras",
				"A las tres \u00FAltimas cifras", "A las dos  \u00FAltimas cifras", 
				"A la \u00FAltima cifra" };

        String euros;
        for (int i = 0; i < results.getLength(); i++) {
            result = (Element) results.item(i);
            values = result.getAttributes();
            
            euros = values.getNamedItem("ImporteEuros").getNodeValue();
            if (euros.contains("durante")) {
                String[] junks = euros.split(" ");
                euros = junks[0];
            }
            
			onceFinde.addPremio(
					categorias[i],
					Float.parseFloat(euros.trim().replace(".", "").replace(",", ".")),
					0);
			System.out.println(onceFinde.getPremio(i));
		}
		
		List<OnceFinde> lotteryList = new LinkedList<OnceFinde>();
		lotteryList.add(onceFinde);

		return lotteryList;
	}
	
	private static List<Quiniela> parseQuinielaData(Element game)
			throws DOMException, ParseException {

		Date date = dfm.parse(formatDate(game.getElementsByTagName("Fecha").item(0).getFirstChild().getNodeValue()));

		Quiniela quiniela = new Quiniela(date, extractHtmlLink(game));
		System.out.println("\n\nQuiniela:  " + date);

		NodeList results = game.getElementsByTagName("Resultado");
		Element result; NamedNodeMap values;

		for (int i = 0; i < results.getLength(); i++) {
			result = (Element) results.item(i);
			values = result.getAttributes();

			quiniela.setMatch(i, values.getNamedItem("Equipo1").getNodeValue(),
					values.getNamedItem("Equipo2").getNodeValue(), values
							.getNamedItem("Valor").getNodeValue());
			
			quiniela.setScore(i, 
					Integer.parseInt(values.getNamedItem("GolesEquipo1").getNodeValue().trim()),
					Integer.parseInt(values.getNamedItem("GolesEquipo2").getNodeValue().trim()));

			System.out.println(values.getNamedItem("Equipo1").getNodeValue()
					+ " - " + values.getNamedItem("Equipo2").getNodeValue()
					+ " -- " + values.getNamedItem("Valor").getNodeValue()
					+ "(" + values.getNamedItem("GolesEquipo1").getNodeValue() + "-"
					+ values.getNamedItem("GolesEquipo2").getNodeValue() + ")");
		}
		
		results = game.getElementsByTagName("Premio");

		String euros;
		for (int i = 0; i < results.getLength(); i++) {
			result = (Element) results.item(i);
			values = result.getAttributes();
			
            euros = values.getNamedItem("ImporteEuros").getNodeValue().trim().replace(".", "")
                    .replace(",", ".");
            if (euros.length() == 0) {
                euros = "0";
            }
    
			quiniela.addPremio(
					0,
					values.getNamedItem("Categoria").getNodeValue(),
					Float.parseFloat(euros),
					0);
			System.out.println(quiniela.getPremio(i));
		}

		List<Quiniela> lotteryList = new LinkedList<Quiniela>();
		lotteryList.add(quiniela);

		return lotteryList;
	}

	private static List<Primitiva> parsePrimitivaData(Element game)
			throws DOMException, ParseException {

		Date date = dfm.parse(formatDate(game.getElementsByTagName("Fecha").item(0).getFirstChild().getNodeValue()));
		System.out.println("\n\nPrimitiva:  " + date);

		// 8 resultados: 6 nums + complementario + reintegro
		
		NodeList results = game.getElementsByTagName("Resultado");
		NamedNodeMap values; 
		Element result;
		
		int num[] = new int[results.getLength()];
		for (int i = 0; i < results.getLength(); i++) {
			result = (Element) results.item(i);
			values = result.getAttributes();
			
			num[i] = Integer.parseInt(formatNumber(values.getNamedItem("Valor").getNodeValue()));
		}
		
		Primitiva primitiva = new Primitiva(date, extractHtmlLink(game), num[0], num[1], num[2],
				num[3], num[4], num[5], num[8], num[6]);
		
		results = game.getElementsByTagName("Premio");

		for (int i = 0; i < results.getLength(); i++) {
			result = (Element) results.item(i);
			values = result.getAttributes();
			
			int acertantes = 0;
			if (!values.getNamedItem("Acertantes").getNodeValue().equals("-")) {
				acertantes = Integer.parseInt(formatNumber(values.getNamedItem("Acertantes").getNodeValue()));
			}
			primitiva.addPremio(
					acertantes,
					values.getNamedItem("Categoria").getNodeValue(),
					Float.parseFloat(values.getNamedItem("ImporteEuros").getNodeValue().trim().replace(".", "").replace(",", ".")),
					0);
			System.out.println(primitiva.getPremio(i));
		}

		List<Primitiva> lotteryList = new LinkedList<Primitiva>();
		lotteryList.add(primitiva);

		return lotteryList;
	}

	private static List<Lototurf> parseLototurfData(Element game)
			throws DOMException, ParseException {

		Date date = dfm.parse(formatDate(game.getElementsByTagName("Fecha").item(0).getFirstChild().getNodeValue()));
		System.out.println("\n\nLototurf:  " + date);

		// 8 results: 6 nums + caballoGanador + reintegro
		int num[] = new int[8];
		NodeList results = game.getElementsByTagName("Resultado");
		NamedNodeMap values; Element result;
		
		for (int i = 0; i < results.getLength(); i++) {
			result = (Element) results.item(i);
			values = result.getAttributes();
			num[i] = Integer.parseInt(formatNumber(values.getNamedItem("Valor").getNodeValue()));
			System.out.print(num[i] + " ");
		}
		
		Lototurf lototurf = new Lototurf(date, extractHtmlLink(game), num[0], num[1], num[2], num[3],
				num[4], num[5], num[6], num[7]);
		
		results = game.getElementsByTagName("Premio");

		for (int i = 0; i < results.getLength(); i++) {
			result = (Element) results.item(i);
			values = result.getAttributes();
			lototurf.addPremio(
					Integer.parseInt(formatNumber(values.getNamedItem("Acertantes").getNodeValue())),
					values.getNamedItem("Categoria").getNodeValue(),
					Float.parseFloat(values.getNamedItem("ImporteEuros").getNodeValue().trim().replace(".", "").replace(",", ".")),
					0);
			System.out.println(lototurf.getPremio(i));
		}

		List<Lototurf> lotteryList = new LinkedList<Lototurf>();
		lotteryList.add(lototurf);

		return lotteryList;
	}
	
	private static List<Trio> parseTrioData(Element game)
			throws DOMException, ParseException {

		Date date = dfm.parse(formatDate(game.getElementsByTagName("Fecha").item(0).getFirstChild().getNodeValue()));
		
		int num[] = new int[3];
		NodeList results = game.getElementsByTagName("Resultado");
		NamedNodeMap values; Element result;
		
		for (int i = 0; i < results.getLength(); i++) {
			result = (Element) results.item(i);
			values = result.getAttributes();
			num[i] = Integer.parseInt(formatNumber(values.getNamedItem("Valor").getNodeValue()));
		}
		
		Trio trio = new Trio(date, extractHtmlLink(game), num[0], num[1], num[2]);
		
		results = game.getElementsByTagName("Premio");

		for (int i = 0; i < results.getLength(); i++) {
			result = (Element) results.item(i);
			values = result.getAttributes();
			trio.addPremio(
					values.getNamedItem("Categoria").getNodeValue(),
					values.getNamedItem("ImporteEuros").getNodeValue());
		}

		List<Trio> lotteryList = new LinkedList<Trio>();
		lotteryList.add(trio);

		return lotteryList;
	}

	private static List<Euromillon> parseEuromillonData(Element game)
			throws DOMException, ParseException {

		Date date = dfm.parse(formatDate(game.getElementsByTagName("Fecha").item(0).getFirstChild().getNodeValue()));
		System.out.println("\n\nEuroMillon:  " + date);

		// 5 results + estrella1 + estrella2
		int num[] = new int[7];
		NodeList results = game.getElementsByTagName("Resultado");
		NamedNodeMap values; Element result;
		
		for (int i = 0; i < results.getLength(); i++) {
			result = (Element) results.item(i);
			values = result.getAttributes();
			num[i] = Integer.parseInt(formatNumber(values.getNamedItem("Valor").getNodeValue()));
			System.out.print(num[i] + " ");
		}
		
		Euromillon euromillon = new Euromillon(date, extractHtmlLink(game), num[0], num[1], num[2],
				num[3], num[4], num[5], num[6]);
		
		results = game.getElementsByTagName("Premio");

		for (int i = 0; i < results.getLength(); i++) {
			result = (Element) results.item(i);
			values = result.getAttributes();
			euromillon.addPremio(
					Integer.parseInt(formatNumber(values.getNamedItem("Acertantes").getNodeValue())),
					Integer.parseInt(formatNumber(values.getNamedItem("AcertantesESP").getNodeValue())),
					values.getNamedItem("Categoria").getNodeValue(),
					Float.parseFloat(values.getNamedItem("ImporteEuros").getNodeValue().trim().replace(".", "").replace(",", ".")),
					0);
			System.out.println(euromillon.getPremio(i));
		}

		List<Euromillon> lotteryList = new LinkedList<Euromillon>();
		lotteryList.add(euromillon);

		return lotteryList;
	}

	private static List<LoteriaNacional> parseLoteriaNacionalData(Element game)
			throws DOMException, ParseException {

		Date date = dfm.parse(formatDate(game.getElementsByTagName("Fecha").item(0).getFirstChild().getNodeValue()));
		System.out.println("\n\nLoteriaNacional:  " + date);


		// The second award is in Premio instead of Resultado!
		NodeList results = game.getElementsByTagName("Premio");
		Element result = (Element) results.item(3);
		NamedNodeMap values = result.getAttributes();
		String premio2 = values.getNamedItem("NumeroPremiado").getNodeValue();
		
		// 6 resultados: premio1, fraccion, serie, reintegro1,
		// reintegro2, reintegro3
		int num[] = new int[6];
		results = game.getElementsByTagName("Resultado");
	
		for (int i = 0; i < results.getLength(); i++) {

			result = (Element) results.item(i);
			values = result.getAttributes();

			// We receive Reintegros like this: R1 - R2 - R3
			if (i == 3) {
				num[i] = Integer.parseInt(values.getNamedItem("Valor").getNodeValue()
						.subSequence(0, 1).toString().trim());
				num[i + 1] = Integer.parseInt(values.getNamedItem("Valor").getNodeValue()
						.subSequence(4, 5).toString().trim());
				num[i + 2] = Integer.parseInt(values.getNamedItem("Valor").getNodeValue()
						.subSequence(8, 9).toString().trim());
				System.out.print(premio2 + "  " + num[i] + " " + num[i + 1] + " " + num[i + 2]);
				break;
			}

			num[i] = Integer.parseInt(values.getNamedItem("Valor").getNodeValue().trim().replace(".", ""));
			System.out.print(num[i] + " ");
		}

		LoteriaNacional loteriaNacional = new LoteriaNacional(date, extractHtmlLink(game), num[0],
				num[1], num[2], premio2, num[3], num[4], num[5]);
		
		results = game.getElementsByTagName("Premio");

		try {
    		for (int i = 0; i < results.getLength(); i++) {
    			result = (Element) results.item(i);
    			values = result.getAttributes();
    			
    			if(values.getNamedItem("Categoria").getNodeValue().matches("Terminaciones"))
    				continue;
    			
    			loteriaNacional.addPremio(
    					values.getNamedItem("Categoria").getNodeValue(),
    					Float.parseFloat(values.getNamedItem("ImporteEuros").getNodeValue().trim().replace(".", "").replace(",", ".")),
    					0);
    			if(i < 3)
    				System.out.println(loteriaNacional.getPremio(i));
    			else
    				System.out.println(loteriaNacional.getPremio(2));
    		}
        } catch (Exception ex) {
            Log.w(TAG, "Error during parsing premio of the loteria nacional", ex);
        }

		List<LoteriaNacional> lotteryList = new LinkedList<LoteriaNacional>();
		lotteryList.add(loteriaNacional);

		return lotteryList;
	}

	private static List<Quinigol> parseQuinigolData(Element game)
			throws DOMException, ParseException {

		Date date = dfm.parse(formatDate(game.getElementsByTagName("Fecha").item(0).getFirstChild().getNodeValue()));
		Quinigol quinigol = new Quinigol(date, extractHtmlLink(game));
		System.out.println("\n\nQuinigol:  " + date);

		NodeList results = game.getElementsByTagName("Resultado");
		Element result; NamedNodeMap values;

		for (int i = 0; i < results.getLength(); i++) {
			result = (Element) results.item(i);
			values = result.getAttributes();
			
			String value = values.getNamedItem("Valor").getNodeValue();
			String parsedValues[] = {" ", " "};
			if(value.length() >= 3) {
				parsedValues[0] = value.split("-")[0];
				parsedValues[1] = value.split("-")[1];
			}
			
			if("".equals(parsedValues[0]))
				parsedValues[0] = " ";
			if("".equals(parsedValues[1]))
				parsedValues[1] = " ";

			quinigol.setMatch(i, values.getNamedItem("Equipo1").getNodeValue(),
					values.getNamedItem("Equipo2").getNodeValue(),
					parsedValues[0],
					parsedValues[1]);

			quinigol.setScore(i, 
					Integer.parseInt(values.getNamedItem("GolesEquipo1").getNodeValue().trim()),
					Integer.parseInt(values.getNamedItem("GolesEquipo2").getNodeValue().trim()));

			System.out.println(values.getNamedItem("Equipo1").getNodeValue()
					+ " - "
					+ values.getNamedItem("Equipo2").getNodeValue()
					+ " -- "
					+ parsedValues[0]
					+ "-"
					+ parsedValues[1]
					+ "(" + values.getNamedItem("GolesEquipo1").getNodeValue() + "-"
					+ values.getNamedItem("GolesEquipo2").getNodeValue() + ")");

		}
		
		results = game.getElementsByTagName("Premio");

        String euros;
        for (int i = 0; i < results.getLength(); i++) {
            result = (Element) results.item(i);
            values = result.getAttributes();
            
            euros = values.getNamedItem("ImporteEuros").getNodeValue().trim().replace(".", "")
                    .replace(",", ".");
            if (euros.length() == 0) {
                euros = "0";
            }
			quinigol.addPremio(
					0,
					values.getNamedItem("Categoria").getNodeValue(),
					Float.parseFloat(euros),
					0);
			System.out.println(quinigol.getPremio(i));
		}

		List<Quinigol> lotteryList = new LinkedList<Quinigol>();
		lotteryList.add(quinigol);

		return lotteryList;
	}
	
	private static List<QuintuplePlus> parseQuintuplePlusData(Element game)
			throws DOMException, ParseException {
		
		Date date = dfm.parse(formatDate(game.getElementsByTagName("Fecha").item(0).getFirstChild().getNodeValue()));
		System.out.println("\n\nQu\u00EDntuple Plus:  " + date);

		// 6 resultados: 6 races
		int races[] = new int[8];
		NodeList results = game.getElementsByTagName("Resultado");
		NamedNodeMap values; Element result;
		
		for (int i = 0; i < results.getLength(); i++) {
			result = (Element) results.item(i);
			values = result.getAttributes();
			races[i] = Integer.parseInt(values.getNamedItem("Valor").getNodeValue().trim());
			System.out.print(races[i] + " ");
		}
		
		QuintuplePlus quintuplePlus = new QuintuplePlus(date, extractHtmlLink(game), races[0], races[1], races[2],
				races[3], races[4], races[5]);
		
		results = game.getElementsByTagName("Premio");

		for (int i = 0; i < results.getLength(); i++) {
			result = (Element) results.item(i);
			values = result.getAttributes();
			quintuplePlus.addPremio(
					Integer.parseInt(formatNumber(values.getNamedItem("Acertantes").getNodeValue())),
					values.getNamedItem("Categoria").getNodeValue(),
					Float.parseFloat(values.getNamedItem("ImporteEuros").getNodeValue().trim().replace(".", "").replace(",", ".")),
					0);
			System.out.println(quintuplePlus.getPremio(i));
		}
	
		List<QuintuplePlus> lotteryList = new LinkedList<QuintuplePlus>();
		lotteryList.add(quintuplePlus);
	
		return lotteryList;
	}

	/** @see #FIELD_HTML_LINK */
	private static String extractHtmlLink(Element game) {
		final NodeList htmlLinkNode = game.getElementsByTagName(FIELD_HTML_LINK);
		final String htmlLink;
		if (htmlLinkNode.getLength() >= 1) {
			htmlLink = htmlLinkNode.item(0).getFirstChild().getNodeValue();
		} else {
			htmlLink = "";
			Log.w(TAG, "Can't find the tag \"" + FIELD_HTML_LINK + "\"");
		}
		return htmlLink;
	}

	
	private static List<CuponExtraordinario> parseCuponExtraordinarioData(Element game)
			throws DOMException, ParseException {

		Date date = dfm.parse(formatDate(game.getElementsByTagName("Fecha").item(0).getFirstChild().getNodeValue()));
		
		int num[] = new int[3];
		NodeList results = game.getElementsByTagName("Resultado");
		NamedNodeMap values; Element result;
		
		for (int i = 0; i < results.getLength(); i++) {
			result = (Element) results.item(i);
			values = result.getAttributes();
			num[i] = Integer.parseInt(formatNumber(values.getNamedItem("Valor").getNodeValue()));
		}
		
		CuponExtraordinario trio = new CuponExtraordinario(date, extractHtmlLink(game), num[0], "" + num[1]);
		
		List<CuponExtraordinario> lotteryList = new LinkedList<CuponExtraordinario>();
		lotteryList.add(trio);

		return lotteryList;
	}
	
	private static List<Eurojackpot> parseEurojackpotData(Element game)
			throws DOMException, ParseException {

		Date date = dfm.parse(formatDate(game.getElementsByTagName("Fecha").item(0).getFirstChild().getNodeValue()));
		System.out.println("\n\nEurojackpot:  " + date);

		// 8 results: 6 nums + caballoGanador + reintegro
		int num[] = new int[8];
		NodeList results = game.getElementsByTagName("Resultado");
		NamedNodeMap values; Element result;
		
		for (int i = 0; i < results.getLength(); i++) {
			result = (Element) results.item(i);
			values = result.getAttributes();
			num[i] = Integer.parseInt(formatNumber(values.getNamedItem("Valor").getNodeValue()));
		}
		
		Eurojackpot lototurf = new Eurojackpot(date, extractHtmlLink(game), num[0], num[1], num[2], num[3],
				num[4], num[5], num[6]);
		
		List<Eurojackpot> lotteryList = new LinkedList<Eurojackpot>();
		lotteryList.add(lototurf);

		return lotteryList;
	}
	
	private static List<SuperOnce> parseSuperOnceData(Element game)
			throws DOMException, ParseException {

		Date date = dfm.parse(formatDate(game.getElementsByTagName("Fecha").item(0).getFirstChild().getNodeValue()));
		System.out.println("\n\nSuperOnce:  " + date);

		NodeList results = game.getElementsByTagName("Resultado");
		NamedNodeMap values; Element result;
		
		StringBuilder numbers = new StringBuilder();
		for (int i = 0; i < results.getLength(); i++) {
			result = (Element) results.item(i);
			values = result.getAttributes();
			numbers.append(values.getNamedItem("Valor").getNodeValue() + " ");
			
			if ((i + 1) % 5 == 0) {
				numbers.append("\n");
			}
		}
		
		SuperOnce lototurf = new SuperOnce(date, extractHtmlLink(game), numbers.toString());
		
		List<SuperOnce> lotteryList = new LinkedList<SuperOnce>();
		lotteryList.add(lototurf);

		return lotteryList;
	}
	
	private static List<Super10> parseSuper10Data(Element game)
			throws DOMException, ParseException {

		Date date = dfm.parse(formatDate(game.getElementsByTagName("Fecha").item(0).getFirstChild().getNodeValue()));
		System.out.println("\n\nSuper10:  " + date);

		NodeList results = game.getElementsByTagName("Resultado");
		NamedNodeMap values; Element result;
		
		StringBuilder numbers = new StringBuilder();
		for (int i = 0; i < results.getLength(); i++) {
			result = (Element) results.item(i);
			values = result.getAttributes();
			numbers.append(values.getNamedItem("Valor").getNodeValue() + " ");
			
			if ((i + 1) % 5 == 0) {
				numbers.append("\n");
			}
		}
		
		Super10 lototurf = new Super10(date, extractHtmlLink(game), numbers.toString());
		
		List<Super10> lotteryList = new LinkedList<Super10>();
		lotteryList.add(lototurf);

		return lotteryList;
	}
	
	/** Parse strings which contains numbers such as 10.343 and 10,233 */
	private static String formatNumber(String number) {
		return number.trim().replace(".", "").replace(",", ".").replace("-", "0");
	}
}
