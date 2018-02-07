package com.example.boxup.bucks;

import android.util.Log;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.fail;
import static org.junit.Assume.assumeTrue;

public final class EdgeCases {

  private static final int SIGABRT = 6; // http://man7.org/linux/man-pages/man7/signal.7.html

  @Test @Ignore("Message!") public void ignored() {
    fail();
  }

  @Test public void assumptionFailure() {
    assumeTrue(false);
  }

  @Test @Ignore("It crashes JVM, intended only for local test runs.") public void nativeCrash() {
    Log.w("nativeCrash", "kill myself");
    android.os.Process.sendSignal(android.os.Process.myTid(), SIGABRT);
    try {
      Thread.sleep(Long.MAX_VALUE); // Block thread until signal become delivered
    } catch (InterruptedException e) {
      throw new IllegalStateException(e);
    }
  }
}
