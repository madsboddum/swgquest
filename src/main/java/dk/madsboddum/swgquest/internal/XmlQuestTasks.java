package dk.madsboddum.swgquest.internal;

import javax.xml.bind.annotation.XmlElement;
import java.util.Collection;

public class XmlQuestTasks {
	@XmlElement(name="task")
	private Collection<XmlQuestTask> tasks;
	
	public Collection<XmlQuestTask> getTasks() {
		return tasks;
	}
}
