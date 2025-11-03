package dev.mineland.smoothpickup;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Optional;
import java.util.Scanner;

public class SmoothPickupConfig {
    private boolean freeGridEnabled = true;
    private boolean itemTransition = false;
    private double lerpThing = 0.5;
    public final Path defaultConfigPath = Path.of("config", "smooth_pickup.cfg");


    public static SmoothPickupConfig DEFAULT = new SmoothPickupConfig();
//    private SmoothPickupConfig backup;


    public SmoothPickupConfig() {
        this.freeGridEnabled = true;
        this.itemTransition = false;
        this.lerpThing = 0.5;
    }

    public SmoothPickupConfig(SmoothPickupConfig settings) {
        copyFrom(settings);
    }


    public void loadSettings() {
        loadSettingsFromFile(defaultConfigPath);
    }


    public void loadSettingsFromFile(Path filePath) {
        File file = filePath.toFile();


        try {
            if (!file.exists()) {
                file.createNewFile();
                writeConfig(filePath);
                SmoothPickup.logger.debug("Created new config file");
                return;
            }

            Scanner scanner = new Scanner(file);

            int currentLine = 0;

            HashMap<Integer, String> errors = new HashMap<>();



            while (scanner.hasNext()) {
                currentLine++;

                String line = scanner.nextLine();

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] equalSplitted = line.split("=");

                int equalsSplit = equalSplitted.length;

                if (equalsSplit != 2) {
                    errors.put(currentLine, String.format("Line has incorret = amounts! [%s]", line));
//                    currentLine++;
                    continue;
                }

                var key = equalSplitted[0].trim();
                var value = equalSplitted[1].trim();

                switch (key) {
                        case "free_grid_enabled" -> {
                            var option = optionsParser.parseBoolean(value);
                            if (option.isEmpty()) {
                                errors.put(currentLine, String.format("free_grid_enabled was incorrect! [%s]", line));
                                freeGridEnabled = DEFAULT.freeGridEnabled;
                            } else {
                                freeGridEnabled = option.get();
                            }
                        }

                        case "item_transition" -> {
                            var option = optionsParser.parseBoolean(value);
                            if (option.isEmpty()) {
                                errors.put(currentLine, String.format("item_transition was incorrect! [%s]", line));
                                itemTransition = DEFAULT.itemTransition;
                            } else {
                                itemTransition = option.get();
                            }
                        }


                    }

//                currentLine++;
            }

            SmoothPickup.logger.debug(String.format("Loaded config from %s", filePath));

            if (!errors.isEmpty()) {
                StringBuilder errorListString = new StringBuilder();
                for (var key : errors.keySet() ){
                    errorListString.append(String.format(":%d | %s\n", key, errors.get(key)));
                }
                SmoothPickup.logger.warn(String.format("Found %d errors when loading the config.\n%s", errors.size(), errorListString));
            }

        } catch (Exception e) {
            SmoothPickup.logger.error("Failed to read config! \n", e);
        }

    }

    public void loadSettingsFromString(String settings) {

    }

    public void writeConfig(Path configFilePath) {
        try(FileWriter fileWriter = new FileWriter(configFilePath.toFile())) {

//            Scanner scanner = new Scanner(configFile);
            String template = """
                    free_grid_enabled = %s
                    item_transition = %s""";


            String finalFileText = template.formatted(freeGridEnabled, itemTransition);
            fileWriter.write(finalFileText);

        } catch (Exception e) {
            SmoothPickup.logger.error("Failed to write config! \n{}", String.valueOf(e));
        }

    }


//    public void discard() {
//        copyFrom(backup);
//    }

    public void flushConfig() {
        writeConfig(defaultConfigPath);
    }

    public void copyFrom(SmoothPickupConfig config) {
        this.freeGridEnabled = config.freeGridEnabled;
        this.itemTransition = config.itemTransition;
        this.lerpThing = config.lerpThing;
//        this.backup = backup.copy();
    }


    public SmoothPickupConfig copy() {
        return new SmoothPickupConfig(this);
    }


    public boolean isFreeGridEnabled() {
        return freeGridEnabled;
    }

    public void setFreeGridEnabled(boolean freeGridEnabled) {
        this.freeGridEnabled = freeGridEnabled;
    }

    public void toggleFreeGridEnabled() {
        this.freeGridEnabled = !this.freeGridEnabled;
    }

    public boolean isItemTransition() {
        return itemTransition;
    }

    public void setItemTransition(boolean itemTransition) {
        this.itemTransition = itemTransition;
    }

    public double getLerpThing() {
        return lerpThing;
    }

    public void setLerpThing(double lerpThing) {
        this.lerpThing = lerpThing;
    }
}


class optionsParser {
    public static Optional<Boolean> parseBoolean(String string) {
        return Optional.of(Boolean.parseBoolean(string));
    }
}