package com.ssafy.ws.exception;

/**
 * 1. Exception을 상속 받는 사용자 정의 Exception 정의
 * 수량 부족
 * 수량이 부족한 상황을 나타내는 예외클래스
 */
public class QuantityException extends Exception {

	public QuantityException() {
		super("수량이 부족합니다.");
	}
}
