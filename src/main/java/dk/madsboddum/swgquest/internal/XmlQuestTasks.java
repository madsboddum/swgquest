package dk.madsboddum.swgquest.internal;

import javax.xml.bind.annotation.XmlElement;

public class XmlQuestTasks {
	
	@XmlElement(name="task")
	private XmlQuestTask task;
	
	public XmlQuestTask getTask() {
		return task;
	}
}
