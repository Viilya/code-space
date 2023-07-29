package com.ssafy.trip.util;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import com.ssafy.trip.model.dto.TripDto;

public class TouristDestinationSAXHandler extends TripDto{
	private int num;
	private List<TripDto> trips = new ArrayList<>();
	private TripDto tripDto;
	private String temp;
	
	public TouristDestinationSAXHandler () {
		System.out.println("SAX Handler Initialized.");
	}
	
	public void startElement(String uri, String localName, String qNAme, Attributes att) throws SAXException{
		
	}
	
	public void endElement(String uri, String localName, String qName) throws SAXException {
		
	}
	
	public void character(char[] ch, int start, int length) throws SAXException {
		
	}
	
	public List<TripDto> getTrips(){
		return null;
	}
}
