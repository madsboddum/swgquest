package dk.madsboddum.swgquest.api;

import dk.madsboddum.swgquest.internal.XmlQuest;
import dk.madsboddum.swgquest.internal.XmlQuestData;
import dk.madsboddum.swgquest.internal.XmlQuestTask;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.util.Collection;

public class SWGQuestFactory {
	
	public static SWGQuest create(InputStream inputStream) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(XmlQuest.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			XmlQuest xmlQuest = (XmlQuest) unmarshaller.unmarshal(inputStream);
			
			return convertToApiModel(xmlQuest);
		} catch (Throwable t) {
			throw new RuntimeException(t);
		}
	}
	
	// TODO method could be prettier
	private static SWGQuest convertToApiModel(XmlQuest xmlQuest) {
		SWGQuest swgQuest = new SWGQuest();
		
		Collection<XmlQuestData> listDataCollection = xmlQuest.getList().getDataCollection();
		
		for (XmlQuestData xmlQuestData : listDataCollection) {
			String name = xmlQuestData.getName();
			String value = xmlQuestData.getValue();
			
			switch (name) {
				case "journalEntryTitle": swgQuest.setJournalEntryTitle(value); break;
				case "journalEntryDescription": swgQuest.setJournalEntryDescription(value); break;
				case "journalEntryCompletionSummary": swgQuest.setJournalEntryCompletionSummary(value); break;
				case "Bank Credits": swgQuest.setBankCredits(Integer.parseInt(value)); break;
				case "allowRepeats": swgQuest.setRepeatable(Boolean.getBoolean(value)); break;
				case "Level": swgQuest.setLevel(Integer.parseInt(value)); break;
				case "Faction Amount": swgQuest.setFactionAmount(Integer.parseInt(value)); break;
				case "Faction Name": swgQuest.setFactionName(value); break;
				case "Experience Amount": swgQuest.setExperienceAmount(Integer.parseInt(value)); break;
				case "Experience Type": swgQuest.setExperienceType(value); break;
			}
		}
		
		Collection<XmlQuestTask> tasks = xmlQuest.getTasks().getTasks();
		
		for (XmlQuestTask task : tasks) {
			String type = task.getType();
			SWGQuestTask swgQuestTask = new SWGQuestTask();
			
			switch (type) {
				case "Destroy Multiple": swgQuestTask.setType(SWGQuestTaskType.DESTROY_MULTIPLE); break;
			}
			
			Collection<XmlQuestData> dataCollection = task.getDataCollection();
			
			for (XmlQuestData xmlQuestData : dataCollection) {
				String name = xmlQuestData.getName();
				String value = xmlQuestData.getValue();
				
				switch (name) {
					case "journalEntryTitle": swgQuestTask.setJournalEntryTitle(value); break;
					case "journalEntryDescription": swgQuestTask.setJournalEntryDescription(value); break;
					case "grantQuestOnComplete": swgQuestTask.setGrantQuestOnComplete(value); break;
				}
			}
			
			swgQuestTask.setTaskOnFail(task.isTaskOnFail());
			
			swgQuest.addTask(swgQuestTask);
		}
		
		return swgQuest;
	}
}
