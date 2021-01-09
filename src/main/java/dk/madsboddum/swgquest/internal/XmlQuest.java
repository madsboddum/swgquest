package dk.madsboddum.swgquest.internal;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="quest")
public class XmlQuest {
	
	@XmlElement(name="tasks")
	private XmlQuestTasks tasks;
	
	@XmlElement(name="list")
	private XmlQuestList list;
	
	public XmlQuestTasks getTasks() {
		return tasks;
	}
	
	public XmlQuestList getList() {
		return list;
	}
}
