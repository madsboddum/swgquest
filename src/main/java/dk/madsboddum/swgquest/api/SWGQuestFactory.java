package dk.madsboddum.swgquest.api;

import dk.madsboddum.swgquest.internal.XmlQuest;
import dk.madsboddum.swgquest.internal.XmlQuestData;
import dk.madsboddum.swgquest.internal.XmlQuestTask;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

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
				case "Tier": swgQuest.setTier(Integer.parseInt(value)); break;
			}
		}
		
		Collection<XmlQuestTask> xmlQuestTasks = xmlQuest.getTasks().getTasks();
		
		List<SWGQuestTask> swgQuestTasks = convertTasks(xmlQuestTasks);
		Collections.reverse(swgQuestTasks);
		
		for (SWGQuestTask swgQuestTask : swgQuestTasks) {
			swgQuest.addTask(swgQuestTask);
		}
		
		return swgQuest;
	}
	
	private static List<SWGQuestTask> convertTasks(Collection<XmlQuestTask> xmlQuestTasks) {
		List<SWGQuestTask> swgQuestTasks = new ArrayList<>();
		
		for (XmlQuestTask xmlQuestTask : xmlQuestTasks) {
			SWGQuestTask swgQuestTask = convertTask(xmlQuestTask);
			Collection<XmlQuestTask> subTasks = xmlQuestTask.getSubTasks();
			
			if (subTasks != null) {
				Collection<SWGQuestTask> subSwgQuestTasks = convertTasks(subTasks);
				swgQuestTasks.addAll(subSwgQuestTasks);
			}
			
			swgQuestTasks.add(swgQuestTask);
		}
		
		return swgQuestTasks;
	}
	
	private static SWGQuestTask convertTask(XmlQuestTask xmlQuestTask) {
		SWGQuestTask swgQuestTask = new SWGQuestTask();
		String type = xmlQuestTask.getType();
		
		switch (type) {
			case "Comm Player": swgQuestTask.setType(SWGQuestTaskType.COMM_PLAYER); break;
			case "Destroy Multiple": swgQuestTask.setType(SWGQuestTaskType.DESTROY_MULTIPLE); break;
		}
		
		Collection<XmlQuestData> dataCollection = xmlQuestTask.getDataCollection();
		
		for (XmlQuestData xmlQuestData : dataCollection) {
			String name = xmlQuestData.getName();
			String value = xmlQuestData.getValue();
			
			switch (name) {
				case "journalEntryTitle": swgQuestTask.setJournalEntryTitle(value); break;
				case "journalEntryDescription": swgQuestTask.setJournalEntryDescription(value); break;
				case "grantQuestOnComplete": swgQuestTask.setGrantQuestOnComplete(value); break;
				case "Target Server Template": swgQuestTask.setTargetServerTemplate(value); break;
				case "Count": swgQuestTask.setCount(Integer.valueOf(value)); break;
				case "Comm Message Text": swgQuestTask.setCommMessageText(value); break;
				case "NPC Appearance Server Template": swgQuestTask.setNpcAppearanceServerTemplate(value); break;
				case "isVisible": swgQuestTask.setVisible(Boolean.valueOf(value)); break;
				case "taskName": swgQuestTask.setName(value); break;
			}
		}
		
		swgQuestTask.setTaskOnFail(xmlQuestTask.isTaskOnFail());
		
		return swgQuestTask;
	}
}
