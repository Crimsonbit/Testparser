package at.crimsonbit.testparser.parser.dto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import at.crimsonbit.testparser.api.sheetinterface.IKeyData;
import at.crimsonbit.testparser.api.sheetinterface.IQuestionData;
import at.crimsonbit.testparser.api.sheetinterface.ISolutionData;

/**
 * Data Transfer Object for Questions. Used in json serialization
 * 
 * @author Alexander Daum
 *
 */
public class QuestionDTO implements IQuestionData {
	private String name;
	private String subject;
	private TaskDTO[] task;
	private String[] hints;
	private SolutionDTO[] solution;
	private KeyDTO[] keys;
	private int difficulty;
	private boolean fixedUID;

	transient List<String> hintList;
	transient List<String> taskList;
	transient List<ISolutionData> solutionList;
	transient List<IKeyData> keyList;

	public boolean isFixedUID() {
		return fixedUID;
	}

	public QuestionDTO() {
	}

	public String getName() {
		return name;
	}

	void setName(String name) {
		this.name = name;
	}

	void setSubject(String subject) {
		this.subject = subject;
	}

	void setTask(TaskDTO[] task) {
		this.task = task;
	}

	void setHints(String[] hints) {
		this.hints = hints;
	}

	void setSolution(SolutionDTO[] solution) {
		this.solution = solution;
	}

	void setKeys(KeyDTO[] keys) {
		this.keys = keys;
	}

	void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	void setFixedUID(boolean fixedUID) {
		this.fixedUID = fixedUID;
	}

	public String getSubject() {
		return subject;
	}

	public List<String> getHints() {
		if (hintList == null) {
			hintList = Arrays.asList(hints);
		}
		return hintList;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public List<ISolutionData> getSolutions() {
		if (solutionList == null) {
			solutionList = Arrays.asList(solution);
		}
		return solutionList;
	}

	public List<? extends IKeyData> getKeys() {
		if (keyList == null) {
			keyList = Arrays.asList(keys);
		}
		return keyList;
	}

	public TaskDTO[] getTask() {
		return task;
	}

	@Override
	public List<String> getTasks() {
		if (taskList == null) {
			taskList = Arrays.stream(task).map(TaskDTO::getText).collect(Collectors.toList());
		}
		return taskList;
	}

	@Override
	public String toString() {
		return "QuestionDTO [name=" + name + ", subject=" + subject + ", task=" + Arrays.toString(task) + ", hints="
				+ Arrays.toString(hints) + ", solution=" + Arrays.toString(solution) + ", keys=" + Arrays.toString(keys)
				+ ", difficulty=" + difficulty + ", fixedUID=" + fixedUID + "]";
	}

}
