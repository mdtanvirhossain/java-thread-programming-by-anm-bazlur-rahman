package com.mdtanvirhossain.jtp.ch9.project;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

public class MergeTask implements Runnable {
    private String fileName;
    private String downloadDir;
    private CountDownLatch countDownLatch;
    private int totalParts;

    public MergeTask(String fileName, String downloadDir, CountDownLatch countDownLatch, int totalParts) {
        this.fileName = fileName;
        this.downloadDir = downloadDir;
        this.countDownLatch = countDownLatch;
        this.totalParts = totalParts;
    }

    private void deletePartials(File[] files) {
        for (File file : files) {
            file.delete();
        }
    }

    private File createMainFile() throws IOException {
        File destination = new File(getPathName());
        if (!destination.exists()) {
            destination.createNewFile();
        }
        return destination;
    }

    private String getPathName() {
        return downloadDir + File.separator + fileName;
    }

    private File[] findPartialFiles() {
        final File[] files = new File[totalParts];

        for (int i = 0; i < files.length; i++) {
            files[i] = new File(getDownloadPartName(i));
        }

        return files;
    }

    private String getDownloadPartName(int partNumber) {
        return downloadDir + File.separator + partNumber + Constants.PART_EXTENSION;
    }

    private void mergeFiles(File[] parts, File outputFilename) {
        try (FileChannel outputChannel = new FileOutputStream(outputFilename).getChannel()) {
            for (File fileLocation : parts) {
                try (FileChannel inputChannel = new FileInputStream(fileLocation).getChannel()) {
                    inputChannel.transferTo(0, inputChannel.size(), outputChannel);
                }
            }
        } catch (IOException e) {
            System.err.println("Couldn't merge file, because-" + e.getMessage());
            Thread.currentThread().interrupt();
            throw new AssertionError(e);
        }
    }

    @Override
    public void run() {
        try {
            countDownLatch.await();

            File[] files = findPartialFiles();
            Arrays.sort(files);
            File finalFile = createMainFile();
            mergeFiles(files, finalFile);
            deletePartials(files);
        } catch (InterruptedException | IOException e) {
            System.err.println("Failed to merge file: " + e.getMessage());
            Thread.currentThread().interrupt();
            throw new AssertionError(e);
        }

    }

}
