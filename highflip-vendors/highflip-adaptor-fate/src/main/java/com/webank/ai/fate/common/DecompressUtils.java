package com.webank.ai.fate.common;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.springframework.lang.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.zip.GZIPInputStream;

public class DecompressUtils {

    public static Map<String,String> decompressTarGzToStringMap(InputStream tarGzInputStream, @Nullable Function<String, Boolean> fileNameFilter) {
        if (fileNameFilter == null) {
            fileNameFilter = s -> true;
        }
        Map<String, String> result = new HashMap<>();
        try (GZIPInputStream gis = new GZIPInputStream(tarGzInputStream);TarArchiveInputStream tais = new TarArchiveInputStream(gis)) {
            int i = 0;
            TarArchiveEntry entry;
            while ((entry = tais.getNextTarEntry()) != null) {
                if (fileNameFilter.apply(entry.getName())) {
                    String text = new BufferedReader(new InputStreamReader(tais, StandardCharsets.UTF_8)).lines().collect(Collectors.joining("\n"));
                    result.put(entry.getName(), text);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
