package org.com.myapp.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.com.myapp.entity.Item;

@SuppressWarnings("serial")
public class ItemData implements java.io.Serializable {

	/**
	 * 
	 */

	private Integer idmatch;
	private Integer id;
	@NotNull
	private String question;
	@NotNull
	@Size(min = 2, max = 16)
	private String answer;

	// to check if user do right
	private boolean check;

	public ItemData() {

	}

	public ItemData(Integer _id, String _question, String _answer,
			boolean _check) {
		this.id = _id;
		this.question = _question;
		this.answer = _answer;
		this.check = _check;
	}

	public ItemData(Item item) {
		this.id = item.getIdItem();
		this.question = item.getQuestion();
		this.answer = item.getAnswer();
		this.check = false;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Integer getIdmatch() {
		return idmatch;
	}

	public void setIdmatch(Integer idmatch) {
		this.idmatch = idmatch;
	}

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

	public Item convertToItem() {

		Item item = new Item();

		item.setIdItem(id);
		item.setQuestion(question);
		item.setAnswer(answer);

		return item;
	}
}
