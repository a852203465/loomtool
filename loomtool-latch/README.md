# 简化Java异步任务同步模块

## 核心思想
多次提交，一次等待
 - 任务注册: 在主流程代码中，可以先通过 LatchUtils.submitTask() 提交Runnable任务和其对应的Executor（该线程池用来执行这个Runnable）。
 - 执行并等待: 当并行任务都提交完毕后，你只需调用一次 LatchUtils.waitFor()。该方法会立即触发所有已注册任务的执行，并阻塞等待所有任务执行完成或超时。

## 使用方式
### 1.引入依赖
```xml
<dependency>
    <groupId>cn.darkjrong</groupId>
    <artifactId>loomtool-latch</artifactId>
</dependency>
```

## 2.API
### submitTask()
提交一个异步任务
```java
// executor:java.util.concurrent.Executor - 指定执行此任务的线程池
// runnable:java.lang.Runnable - 需要异步执行的具体业务逻辑
public static void submitTask(Executor executor, Runnable runnable)
```
### waitFor()
触发所有已提交任务的执行，并同步等待它们全部完成
```java
// timeout:long - 最长等待时间
// timeUnit:java.util.concurrent.TimeUnit - 等待时间单位
public static boolean waitFor(long timeout, TimeUnit timeUnit)
```

## 3.实例
```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        // 1. 准备一个线程池
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        System.out.println("主流程开始，准备分发异步任务...");

        // 2. 提交多个异步任务
        // 任务一：获取用户信息
        LatchUtils.submitTask(executorService, () -> {
            try {
                System.out.println("开始获取用户信息...");
                Thread.sleep(1000); // 模拟耗时
                System.out.println("获取用户信息成功！");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // 任务二：获取订单信息
        LatchUtils.submitTask(executorService, () -> {
            try {
                System.out.println("开始获取订单信息...");
                Thread.sleep(1500); // 模拟耗时
                System.out.println("获取订单信息成功！");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // 任务三：获取商品信息
        LatchUtils.submitTask(executorService, () -> {
            try {
                System.out.println("开始获取商品信息...");
                Thread.sleep(500); // 模拟耗时
                System.out.println("获取商品信息成功！");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        
        System.out.println("所有异步任务已提交，主线程开始等待...");

        // 3. 等待所有任务完成，最长等待5秒
        boolean allTasksCompleted = LatchUtils.waitFor(5, TimeUnit.SECONDS);

        // 4. 根据等待结果继续主流程
        if (allTasksCompleted) {
            System.out.println("所有异步任务执行成功，主流程继续...");
        } else {
            System.err.println("有任务执行超时，主流程中断！");
        }

        // 5. 关闭线程池
        executorService.shutdown();
    }
}
```
