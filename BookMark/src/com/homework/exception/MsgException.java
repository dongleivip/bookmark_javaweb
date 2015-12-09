package com.homework.exception;

public class MsgException extends Exception {

	private static final long serialVersionUID = -6026611416524989355L;

	public MsgException() {
	}

	public MsgException(String msg) {
		super(msg);
	}
}
