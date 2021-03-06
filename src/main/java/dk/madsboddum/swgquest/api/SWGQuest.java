package dk.madsboddum.swgquest.api;

import java.util.*;

public class SWGQuest {
	private SWGQuestTask task;
	private String journalEntryTitle;
	private String journalEntryDescription;
	private String journalEntryCompletionSummary;
	private Integer bankCredits;
	private boolean repeatable;
	private Integer level;
	private Integer factionAmount;
	private String factionName;
	private Integer experienceAmount;
	private String experienceType;
	private Integer tier;
	
	SWGQuest() {
	
	}
	
	public Integer getTier() {
		return tier;
	}
	
	void setTier(Integer tier) {
		this.tier = tier;
	}
	
	public SWGQuestTask getTask() {
		return task;
	}
	
	void setTask(SWGQuestTask task) {
		this.task = task;
	}
	
	public Integer getExperienceAmount() {
		return experienceAmount;
	}
	
	void setExperienceAmount(Integer experienceAmount) {
		this.experienceAmount = experienceAmount;
	}
	
	public String getExperienceType() {
		return experienceType;
	}
	
	void setExperienceType(String experienceType) {
		this.experienceType = experienceType;
	}
	
	public Integer getFactionAmount() {
		return factionAmount;
	}
	
	void setFactionAmount(Integer factionAmount) {
		this.factionAmount = factionAmount;
	}
	
	public String getFactionName() {
		return factionName;
	}
	
	void setFactionName(String factionName) {
		this.factionName = factionName;
	}
	
	public Integer getLevel() {
		return level;
	}
	
	void setLevel(Integer level) {
		this.level = level;
	}
	
	public boolean isRepeatable() {
		return repeatable;
	}
	
	void setRepeatable(boolean repeatable) {
		this.repeatable = repeatable;
	}
	
	public Integer getBankCredits() {
		return bankCredits;
	}
	
	void setBankCredits(Integer bankCredits) {
		this.bankCredits = bankCredits;
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
	
	public String getJournalEntryCompletionSummary() {
		return journalEntryCompletionSummary;
	}
	
	void setJournalEntryCompletionSummary(String journalEntryCompletionSummary) {
		this.journalEntryCompletionSummary = journalEntryCompletionSummary;
	}
	
	/**
	 * Retrieves tasks relevant at a given step of the quest.
	 * @param activeStep to find tasks for
	 * @return tasks
	 */
	public List<SWGQuestTask> getTasksForStep(int activeStep) {
		if (activeStep <= 1) {
			return Collections.singletonList(task);
		}
		
		return getTasksAtStep(task.getSubTasks(), activeStep, 2);
	}
	
	private List<SWGQuestTask> getTasksAtStep(List<SWGQuestTask> current, int activeStep, int depth) {
		if (depth >= activeStep) {
			return current;
		}
		
		List<SWGQuestTask> subSubTasks = new ArrayList<>();
		
		for (SWGQuestTask task : current) {
			subSubTasks.addAll(task.getSubTasks());
		}
		
		return getTasksAtStep(subSubTasks, activeStep, depth + 1);
	}
	
	public boolean isEveryTaskCompleted(int activeStep) {
		List<SWGQuestTask> tasksForStep = getTasksForStep(activeStep);
		
		boolean questComplete = true;
		
		for (SWGQuestTask currentTask : tasksForStep) {
			questComplete &= currentTask.getSubTasks().isEmpty();
		}
		
		return questComplete;
	}
}
