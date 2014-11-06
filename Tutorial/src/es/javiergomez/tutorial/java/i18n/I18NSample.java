package es.javiergomez.tutorial.java.i18n;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.*;

public class I18NSample {

	static public void displayCurrency( Locale currentLocale) {

	    Double currencyAmount = new Double(9876543.21);
	    Currency currentCurrency = Currency.getInstance(currentLocale);
	    NumberFormat currencyFormatter = 
	        NumberFormat.getCurrencyInstance(currentLocale);

	    System.out.println(
	        currentLocale.getDisplayName() + ", " +
	        currentCurrency.getDisplayName() + ": " +
	        currencyFormatter.format(currencyAmount));
	}
	
	static public void displayDateTime( Locale currentLocale) {

		String dateOut;
		DateFormat formatter = DateFormat.getDateTimeInstance(
                DateFormat.LONG, 
                DateFormat.LONG, 
                currentLocale);
		
		dateOut = formatter.format(new Date());
	    System.out.println(dateOut);
	}	
	
    static public void main(String[] args) {

        String language;
        String country;

        if (args.length != 2) {
            language = new String("en");
            country = new String("US");
        } else {
            language = new String(args[0]);
            country = new String(args[1]);
        }

        Locale currentLocale;
        ResourceBundle messages;

        currentLocale = new Locale(language, country);

        messages = ResourceBundle.getBundle("MessagesBundle", currentLocale);
        System.out.println(messages.getString("greetings"));
        System.out.println(messages.getString("inquiry"));
        System.out.println(messages.getString("farewell"));
        
        Object[] messageArguments = {
        	    messages.getString("planet"),
        	    new Integer(7),
        	    new Date()
        	};
        MessageFormat formatter = new MessageFormat("");
        formatter.setLocale(currentLocale);
        formatter.applyPattern(messages.getString("template"));
        String output = formatter.format(messageArguments);
        System.out.println(output);
        
        displayCurrency(currentLocale);
        
        displayDateTime(currentLocale);
    }
}
