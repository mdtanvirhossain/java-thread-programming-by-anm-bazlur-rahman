package com.mdtanvirhossain.jtp.ch2.creatingandusingthread;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class ThreadJoinExample {
    public static void main(String[] args) throws InterruptedException {

        FileDownloader fileDownloader1 = new FileDownloader("YOUR_FILE_URL", "file1.pdf");
        FileDownloader fileDownloader2 = new FileDownloader("YOUR_FILE_URL", "file2.pdf");

        DownloadingHeartBeat downloadingHeartBeat = new DownloadingHeartBeat();

        fileDownloader1.start();
        fileDownloader2.start();
        downloadingHeartBeat.start();

        try {
            fileDownloader1.join();
            fileDownloader2.join();

            downloadingHeartBeat.shutdown();
            downloadingHeartBeat.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Download is completed!");
    }
}

class DownloadingHeartBeat extends Thread {
    private volatile boolean beating = true;

    @Override
    public void run() {
        String[] dots = {
                ".",
                "..",
                "...",
                "...."
        };

        while (beating) {
            for (String dot : dots) {
                System.out.println(dot);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void shutdown() {
        this.beating = false;
    }
}

class FileDownloader extends Thread {
    private String url;
    private String fileName;

    public FileDownloader(String url, String fileName) {
        this.url = url;
        this.fileName = "YOUR_PATH" + fileName;
    }

    @Override
    public void run() {
        try {
            System.out.println("Started to download: " + fileName);
            URL resourceToDownload = new URL(url);
            URLConnection connection = resourceToDownload.openConnection();
            InputStream input = connection.getInputStream();

            File fileToSave = new File(fileName);
            Files.copy(input, fileToSave.toPath(), StandardCopyOption.REPLACE_EXISTING);
            input.close();
        } catch (IOException e) {
            System.out.println("Failed to download the file: " + e.getMessage());
        }
    }
}