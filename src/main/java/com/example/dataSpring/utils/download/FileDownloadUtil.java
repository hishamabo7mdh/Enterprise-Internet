package com.example.dataSpring.utils.download;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileDownloadUtil {
    private Path fileToDownloadUtil;

    public Resource getFileAsResource(String fileName) throws IOException {
        Path uploadDirectory= Paths.get("/Uploaded-Files");

        DirectoryStream<Path> directoryStream = Files.newDirectoryStream(uploadDirectory);
        for (Path file : directoryStream) {
            if (file.getFileName().toString().equals(fileName)) {
                fileToDownloadUtil = file;
                break; // توقف عند العثور على الملف
            }
        }
        directoryStream.close();
        if (fileToDownloadUtil!=null)
            return new UrlResource(fileToDownloadUtil.toUri());
    return null;
    }

}
