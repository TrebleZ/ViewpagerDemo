package com.example.model;

public class Questions {

	private String questionId;
	private String test_cotent;

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getTest_cotent() {
		return test_cotent;
	}

	public void setTest_cotent(String test_cotent) {
		this.test_cotent = test_cotent;
	}
	public Questions(String questionId, String test_cotent) {
		super();
		this.questionId = questionId;
		this.test_cotent = test_cotent;

	}

}
