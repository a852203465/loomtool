package cn.darkjrong.latch;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/**
 * latch工具类
 *
 * @author Rong.Jia
 * @date 2025/10/23
 */
@Slf4j
public class LatchTemplate {

    private static final ThreadLocal<List<TaskInfo>> THREADLOCAL = ThreadLocal.withInitial(LinkedList::new);

    public static void submitTask(Executor executor, Runnable runnable) {
        THREADLOCAL.get().add(new TaskInfo(executor, runnable));
    }

    public static boolean waitFor(long timeout, TimeUnit timeUnit) {
        List<TaskInfo> taskInfos = popTask();
        if (taskInfos.isEmpty()) {
            return true;
        }
        CountDownLatch latch = new CountDownLatch(taskInfos.size());
        for (TaskInfo taskInfo : taskInfos) {
            Executor executor = taskInfo.executor;
            Runnable runnable = taskInfo.runnable;
            executor.execute(() -> {
                try {
                    runnable.run();
                } finally {
                    latch.countDown();
                }
            });
        }
        boolean await = false;
        try {
            await = latch.await(timeout, timeUnit);
        } catch (Exception e) {
            log.error(String.format("Failed to wait for latch, 【%s】", e.getMessage()), e);
        }
        return await;
    }

    private static List<TaskInfo> popTask() {
        List<TaskInfo> taskInfos = THREADLOCAL.get();
        THREADLOCAL.remove();
        return taskInfos;
    }

    private static final class TaskInfo {
        private final Executor executor;
        private final Runnable runnable;

        public TaskInfo(Executor executor, Runnable runnable) {
            this.executor = executor;
            this.runnable = runnable;
        }
    }
}
