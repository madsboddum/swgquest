package dk.madsboddum.swgquest.api;

public class SWGQuestTask {
	
	private SWGQuestTaskType type;
	private Boolean taskOnFail;
	private String journalEntryTitle;
	private String journalEntryDescription;
	private String grantQuestOnComplete;
	
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
