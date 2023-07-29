package com.ssafy.trip.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.ssafy.trip.model.dto.TripDto;

public class TouristDestinationSAXParser extends TripDto{
	private List<TripDto> tripInfo = new ArrayList<>();
	private int size ;
	public int num;
	
	public TouristDestinationSAXParser() {
		System.out.println("SAX Parser init");
	}
	
	private void loadData() {
		
	}
	
}
