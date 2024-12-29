package com.ll.jap.standard.util;

import lombok.SneakyThrows;

public class Ut {
    public static class thread {

        @SneakyThrows
        public static void sleep(int millis) {
                Thread.sleep(millis);
//        Try {
//            Thread.sleep(millis);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        }
    }
}
