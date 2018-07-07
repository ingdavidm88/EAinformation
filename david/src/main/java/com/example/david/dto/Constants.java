package com.example.david.dto;

public enum Constants {
	
	//PAGE
	BASEURL("https://www.amazon.com/"),
    ALPHABET("#,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z"),
    REPLACE("[\\|\\*\\/\\?\\\\(\\)\\,\\\\.\\:]"),

	PERCENTAGE_GAIN("30"),
	CURRENCYFORMAT("###,###.##"),
	
	ES_US("?language=es_US"),
	EN_US("?language=en_US"),
	
	//STATUS
	SUCCESS("S"),				
	ERROR("E"), 				
	FAILURE("F"),				 
	PENDING("P"),				
	STARTING("Starting"),		
	FINISHED("Finished"),		
	PROCESSING("Processing..."),	
	
	//VALIDATE
	EMAIL_PATTERN("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"),
	STRING_PATTERN("[a-zA-Z]+"),  
	MOBILE_PATTERN("[0-9]{10}"),
	NUMBER_PATTERN("[0-9]+"),
	NAME_PATTERN("^[A-Za-zÁáÉéÍíÓóÚúÑñüÜ_\\s]+$"),
	
	//GENERAL
	MESSAGESRESPONSE("messagesResponse"),
	INTERNALERROR("internal_error");
	
	private String val;

	Constants(String val) {
        this.val = val;
    }

    public String val() {
        return val;
    }
}
