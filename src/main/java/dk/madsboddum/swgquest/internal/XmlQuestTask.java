package dk.madsboddum.swgquest.internal;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.Collection;

public class XmlQuestTask {
	@XmlAttribute
	private String type;
	
	@XmlAttribute
	private Boolean taskOnFail;
	
	@XmlElement(name="data")
	private Collection<XmlQuestData> dataCollection;
	
	public String getType() {
		return type;
	}
	
	public boolean isTaskOnFail() {
		return taskOnFail;
	}
	
	public Collection<XmlQuestData> getDataCollection() {
		return dataCollection;
	}
}
