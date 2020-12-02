import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

public class TestCompletableFuture {
    public static void main(String[] args) {
        final  CountDownLatch countDownLatch = new CountDownLatch(1);
        try {
        CompletableFuture<String> future = new CompletableFuture<>();
        CompletableFuture<Void> cf = CompletableFuture.runAsync(new Task(countDownLatch));
        //future.thenRunAsync(new Task(countDownLatch));
       // future
        cf.thenAccept((result) -> {
            System.out.println("price: " + result);
        });
        // 如果执行异常:
        cf.exceptionally((e) -> {
            e.printStackTrace();
            System.out.println("抓取了异常");
            return null;
        });

            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println("结束");
        }
    }

    static class  Task implements  Runnable{
        CountDownLatch latch;

        public Task(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                int k = 1 / 0;
                System.out.println("子线程......");

            }catch(Exception e){
                e.printStackTrace();
                throw new RuntimeException("》》》》》");
            }finally {
                 latch.countDown();
        }

        }
    }
}
