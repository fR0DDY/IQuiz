package com.iquiz.helpers;

import java.util.ArrayList;
import java.util.List;

import com.iquiz.entities.Question;

public class QuestionHelper {

	public static List<Question> getRandomQuestions(){
		List<Question> list = new ArrayList<Question>();
		for (int i = 0; i < 10; i++) {
			Question q = new Question();
			q.setQuestion("Who is the actor in Dhoom 3");
			q.getOptions().add("Salman Khan");
			q.getOptions().add("Shahrukh Khan");
			q.getOptions().add("Aamir Khan");
			q.getOptions().add("Sohail Khan");
//			q.setAnswer("Aamir Khan");
			list.add(q);
		}
		return list;
	}
}
