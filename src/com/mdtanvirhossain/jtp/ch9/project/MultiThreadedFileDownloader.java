package com.mdtanvirhossain.jtp.ch9.project;

public class MultiThreadedFileDownloader {
    public static void main(String[] args) {
        final String downloadDir = "YOUR_DIR";
        final String url = "YOUR_URL";

        System.out.println("File download is started.\nDownloading...");

        DownloadManager downloadManager = new DownloadManager();
        downloadManager.download(url, downloadDir);

        System.out.println("Download finished.");
    }
}
