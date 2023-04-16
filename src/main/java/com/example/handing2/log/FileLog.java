package com.example.handing2.log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileLog {
  private final CurrentTime currentTime;
  private final File file;
  private static FileLog defaultLog;

  private FileLog() {
    file = new File("FileLog.txt");
    currentTime = CurrentTime.getInstance();
  }

  private File getLogFile() {
    return file;
  }

  public void log(String message) throws IOException {
    try (FileWriter fileWriter = new FileWriter(getLogFile(), true);
        PrintWriter writer = new PrintWriter(fileWriter)) {
      String logLine = currentTime.getFormattedTime() + " - " + message;
      writer.println(logLine);
    }
  }

  public static synchronized FileLog getInstance()
  {
    if (defaultLog == null)
    {
      defaultLog = new FileLog();
    }
    return defaultLog;
  }
}
