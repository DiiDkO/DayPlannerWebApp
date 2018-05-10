package com.timetable.renderer;

public class PairHeadersData<H,D> {

	private H headers;
	private D data;
	
	public PairHeadersData(H headers,D data) {
		this.headers=headers;
		this.data=data;
	}
	public PairHeadersData(PairHeadersData<H,D> pair) {
		this.headers=pair.getHeaders();
		this.data=pair.getData();
	}
	public H getHeaders() {
		return headers;
	}

	public D getData() {
		return data;
	}

}
