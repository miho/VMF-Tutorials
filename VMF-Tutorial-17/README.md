# Tutorial 17 · Record and replay portable changes

[Tutorial index](../README.md) · [Previous](../VMF-Tutorial-16/README.md)

[Live undo](../VMF-Tutorial-04/README.md) operates on the working model. A portable journal stores the edits separately and replays them against a freshly loaded checkpoint. This example edits a document's title and tags. [Model definition](src/main/vmf/eu/mihosoft/vmf/tutorial17/vmfmodel/Document.java) · [Java example](src/main/java/eu/mihosoft/vmf/tutorial17/Main.java)

## Save the checkpoint and record one edit

Give objects persistent IDs so the same objects can be identified after loading a separate graph. The example's `JournalBinding` reads each document's `id`, gives its model type a stable wire name and identifies the model version.

First, use the VMF Jackson module to save the starting document as `checkpoint.json`. Then record the two edits together with `JournalRecorder.recordFrame`. The checkpoint ID and base sequence identify the starting point used by the recorder. The recorder verifies that the captured edit can be replayed.

```java
mapper.writeValue(checkpointFile.toFile(), working);
ChangeJournal journal = JournalRecorder.recordFrame(working, BINDING, CHECKPOINT_ID, 0, model -> {
    model.setTitle("Published");
    model.getTags().add("reviewed");
});
```

## Replay the files

`VMFJournalCodec.Format` in VMF 0.4.0 selects JSON, YAML or XML for the journal. Each file describes the same recorded changes. Load a fresh checkpoint for each replay.

```java
Files.writeString(journalFile, VMFJournalCodec.write(journal, format));
Document checkpoint = mapper.readValue(checkpointFile.toFile(), Document.class);
ChangeJournal decoded = VMFJournalCodec.read(Files.readString(journalFile), format);
Document result = JournalReplayer.replay(checkpoint, BINDING, CHECKPOINT_ID, decoded);
```

The returned model contains the new title and tag. The loaded checkpoint keeps its starting title and empty tag list. Store the original checkpoint alongside whichever journal format you use.

## Run the lesson

With JDK 21, run `./gradlew run` from this directory. On Windows, use `.\gradlew.bat run`. Look in `build/tutorial-output` for the JSON checkpoint and the three journal files.

```text
JSON replay: Published [reviewed]
YAML replay: Published [reviewed]
XML replay: Published [reviewed]
```
