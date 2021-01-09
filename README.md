# The swgquest project
Reads information from Star Wars Galaxies .qst files.

Example usage:

```java
    // Load data
    InputStream inputStream = new FileInputStream("purvis_kill_zealots.qst");
    SWGQuest swgQuest = SWGQuestFactory.create(inputStream);

    // Use data
    Integer creditReward = swgQuest.getBankCredits()
```