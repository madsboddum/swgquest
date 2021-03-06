package dk.madsboddum.swgquest.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class SWGQuestTask {
	
	private SWGQuestTaskType type;
	private Boolean taskOnFail;
	private String journalEntryTitle;
	private String journalEntryDescription;
	private String grantQuestOnComplete;
	private String targetServerTemplate;
	private Integer count;
	private String commMessageText;
	private String npcAppearanceServerTemplate;
	private Boolean visible;
	private String name;
	private final List<SWGQuestTask> subTasks;
	
	SWGQuestTask() {
		subTasks = new ArrayList<>();
	}
	
	public List<SWGQuestTask> getSubTasks() {
		return new ArrayList<>(subTasks);
	}
	
	void addSubTask(SWGQuestTask subTask) {
		subTasks.add(subTask);
	}
	
	public String getName() {
		return name;
	}
	
	void setName(String name) {
		this.name = name;
	}
	
	public Boolean getVisible() {
		return visible;
	}
	
	void setVisible(Boolean visible) {
		this.visible = visible;
	}
	
	public String getNpcAppearanceServerTemplate() {
		return npcAppearanceServerTemplate;
	}
	
	void setNpcAppearanceServerTemplate(String npcAppearanceServerTemplate) {
		this.npcAppearanceServerTemplate = npcAppearanceServerTemplate;
	}
	
	public String getCommMessageText() {
		return commMessageText;
	}
	
	void setCommMessageText(String commMessageText) {
		this.commMessageText = commMessageText;
	}
	
	public Integer getCount() {
		return count;
	}
	
	void setCount(Integer count) {
		this.count = count;
	}
	
	public String getTargetServerTemplate() {
		return targetServerTemplate;
	}
	
	void setTargetServerTemplate(String targetServerTemplate) {
		this.targetServerTemplate = targetServerTemplate;
	}
	
	public Boolean getTaskOnFail() {
		return taskOnFail;
	}
	
	void setTaskOnFail(Boolean taskOnFail) {
		this.taskOnFail = taskOnFail;
	}
	
	public String getGrantQuestOnComplete() {
		return grantQuestOnComplete;
	}
	
	void setGrantQuestOnComplete(String grantQuestOnComplete) {
		this.grantQuestOnComplete = grantQuestOnComplete;
	}
	
	public SWGQuestTaskType getType() {
		return type;
	}
	
	void setType(SWGQuestTaskType type) {
		this.type = type;
	}
	
	public String getJournalEntryTitle() {
		return journalEntryTitle;
	}
	
	void setJournalEntryTitle(String journalEntryTitle) {
		this.journalEntryTitle = journalEntryTitle;
	}
	
	public String getJournalEntryDescription() {
		return journalEntryDescription;
	}
	
	void setJournalEntryDescription(String journalEntryDescription) {
		this.journalEntryDescription = journalEntryDescription;
	}
}
