package tms.webapp.taskboard.core.store;

import tms.webapp.taskboard.core.interfaces.store.LocalizationStore;
import tms.webapp.taskboard.core.utils.JsonConverter;
import tms.webapp.taskboard.core.utils.PathUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class LocalizationStoreImpl implements LocalizationStore {

    private static LocalizationStore instance;
    private final Map<String, String>  translations;
    private static final String keyFormat ="%s_%s";
    private LocalizationStoreImpl(Map<String, String> translations) {
        this.translations = translations;
    }

    public String getTranslate(String locale, String word){
        if(Optional.ofNullable(locale).isEmpty())
            return word;
        String localKey = keyFormat.formatted(locale,word);
        return translations.getOrDefault(localKey, word);
    }

    public static LocalizationStore getInstance(){
        return instance;
    }

    public static void configurate(String diffRealPath) throws IOException {

        if(Optional.ofNullable(instance).isPresent()){
            return;
        }
        Map<String, String> translates = new HashMap<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(diffRealPath))) {
            for (Path path : stream) {
                if (!Files.isDirectory(path)
                        && path.getFileName().toString().endsWith(".json"))
                {
                    try (InputStream in = Files.newInputStream(path)) {
                        byte[] bytes = in.readAllBytes();
                        String json  = new String(bytes);
                        Map<String,String> map = JsonConverter.deserialize(json, Map.class);
                        for(Map.Entry<String, String> entry : map.entrySet())
                        {
                            String key = entry.getKey();
                            String value = entry.getValue();
                            String locale = PathUtils.getFileName(path.getFileName().toString(), true);//..toString();
                            translates.put(keyFormat.formatted(locale, key), value);
                        }
                    }
                }
            }
        }

        instance = new LocalizationStoreImpl(translates);
    }
}
