# Tutorial 17 · Record and replay portable changes

[Tutorial index](../README.md) · [Previous](../VMF-Tutorial-16/README.md)

[Live undo](../VMF-Tutorial-04/README.md) operates on the working model. A portable journal stores the edits separately and replays them against a freshly loaded checkpoint. This example edits a document's title and tags. [Model definition](src/main/vmf/eu/mihosoft/vmf/tutorial17/vmfmodel/Document.java) · [Java example](src/main/java/eu/mihosoft/vmf/tutorial17/Main.java)

## Record transitions from a checkpoint

Give objects persistent IDs so the same objects can be identified after loading a separate graph. The example's `JournalBinding` reads each document's `id`, gives its model type a stable wire name and identifies the model version.

Save the starting document as `checkpoint.json` and open one recorder while the working model is still at that checkpoint. `JournalRecorder.start()` starts the underlying VMF change recording. VMF applies each model edit immediately, and the recorder captures the native changes as they happen, including changes made by ordinary transition methods called while it is open. Call `publishFrame()` after each transition to group its pending edits into one verified frame.

```java
mapper.writeValue(checkpointFile.toFile(), working);
ChangeJournal journal;
try (JournalRecorder recorder = JournalRecorder.start(working, BINDING, CHECKPOINT_ID, 0)) {
    working.setTitle("Ready to publish");
    working.getTags().add("reviewed");
    recorder.publishFrame();

    working.setTitle("Published");
    recorder.publishFrame();

    journal = recorder.finish();
}
```

The first frame contains the rename and the tag insertion. The second contains the next rename. `finish()` publishes any pending group and returns the verified journal. For a single transition, `JournalRecorder.recordFrame()` opens the recorder, runs the callback and returns the journal in one call.

If your transitions already call `Changes.startTransaction()` and `Changes.publishTransaction()`, a recorder that is active during those edits can use the native transaction boundaries. Use that approach or `publishFrame()` for the session. The live `Changes` log alone holds edits tied to the original model. The portable journal comes from the active recorder. [VMF journal guide](https://github.com/miho/VMF/blob/v0.4.0/docs/change-journal.md)

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

With JDK 21, run `./gradlew run` from this directory. On Windows, use `.\gradlew.bat run`. Look in `build/tutorial-output` for the JSON checkpoint and the three journal files. Each journal contains the same two frames.

```text
Frame 1: 2 operations
Frame 2: 1 operations
JSON replay: Published [reviewed]
YAML replay: Published [reviewed]
XML replay: Published [reviewed]
```
