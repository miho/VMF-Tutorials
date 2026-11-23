package eu.mihosoft.vmf.tutorial17;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.mihosoft.vmf.jackson.VMFJacksonModule;
import eu.mihosoft.vmf.jackson.VMFJournalCodec;
import eu.mihosoft.vmf.jackson.VMFJournalCodec.Format;
import eu.mihosoft.vmf.runtime.core.VObject;
import eu.mihosoft.vmf.runtime.core.journal.ChangeJournal;
import eu.mihosoft.vmf.runtime.core.journal.JournalBinding;
import eu.mihosoft.vmf.runtime.core.journal.JournalRecorder;
import eu.mihosoft.vmf.runtime.core.journal.JournalReplayer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Locale;

public class Main {
    private static final String CHECKPOINT_ID = "draft-v1";
    private static final JournalBinding BINDING = new JournalBinding() {
        @Override
        public String modelId() {
            return "tutorial17.document";
        }

        @Override
        public int modelVersion() {
            return 1;
        }

        @Override
        public String id(VObject object) {
            return ((Document) object).getId();
        }

        @Override
        public String type(VObject object) {
            if (object instanceof Document) {
                return "document";
            }
            throw new IllegalArgumentException("Unknown model type: " + object.getClass().getName());
        }

        @Override
        public VObject create(String type, String id) {
            if ("document".equals(type)) {
                return Document.newBuilder().withId(id).build();
            }
            throw new IllegalArgumentException("Unknown journal type: " + type);
        }
    };

    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper().registerModule(
                VMFJacksonModule.newInstance(VMFJacksonModule.RUNTIME_TYPE.EXPERIMENTAL));
        Path directory = Files.createDirectories(Path.of("build", "tutorial-output"));
        Path checkpointFile = directory.resolve("checkpoint.json");

        Document working = Document.newBuilder().withId("doc-1").withTitle("Draft").build();
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
        for (var frame : journal.transactions()) {
            System.out.printf("Frame %d: %d operations%n", frame.sequence(), frame.operations().size());
        }

        for (Format format : Format.values()) {
            String extension = format.name().toLowerCase(Locale.ROOT);
            Path journalFile = directory.resolve("journal." + extension);
            Files.writeString(journalFile, VMFJournalCodec.write(journal, format));

            Document checkpoint = mapper.readValue(checkpointFile.toFile(), Document.class);
            ChangeJournal decoded = VMFJournalCodec.read(Files.readString(journalFile), format);
            Document result = JournalReplayer.replay(checkpoint, BINDING, CHECKPOINT_ID, decoded);

            if (checkpoint == result || !"Draft".equals(checkpoint.getTitle()) || !checkpoint.getTags().isEmpty()) {
                throw new IllegalStateException("Replay must leave the loaded checkpoint intact");
            }
            if (!working.getTitle().equals(result.getTitle())
                    || !List.copyOf(working.getTags()).equals(result.getTags())) {
                throw new IllegalStateException("Replay differs from the recorded edit");
            }
            System.out.printf("%s replay: %s %s%n", format, result.getTitle(), result.getTags());
        }
    }
}
