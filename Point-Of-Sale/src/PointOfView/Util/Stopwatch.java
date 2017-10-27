package PointOfView.Util;

/** 
 * Author unknown, but I think this is from a book
 * named "Java Platform Performance".
 */
public class Stopwatch {
  private long startTime = -1;
  private long stopTime = -1;
  private boolean running = false;

  public Stopwatch start() {
    startTime = System.currentTimeMillis();
    running = true;
    return this;
  }

  public Stopwatch stop() {
    stopTime = System.currentTimeMillis();
    running = false;
    return this;
  }
  
  public boolean isRunning() {
      return running;
  }

  public long getElapsedTime() {
    if (startTime == -1) {
      return 0;
    }
    if (running) {
      return System.currentTimeMillis() - startTime;
    }
    else {
      return stopTime - startTime;
    }
  }

  public Stopwatch reset() {
    startTime = -1;
    stopTime = -1;
    running = false;
    return this;
  }

}