package ss;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadTest {

	public static void main(String[] args) {

		Callable<List<String>> myCallable = new MyCallable(); // 创建MyCallable对象
		FutureTask<List<String>> ft = new FutureTask<List<String>>(myCallable); // 使用FutureTask来包装MyCallable对象

		for (int i = 0; i < 100; i++) {
			System.out.println(Thread.currentThread().getName() + " " + i);
			if (i == 30) {
				Thread thread = new Thread(ft); // FutureTask对象作为Thread对象的target创建新的线程
				thread.start(); // 线程进入到就绪状态
			}
		}

		System.out.println("主线程for循环执行完毕..");

		try {
			List<String> sum = ft.get(); // 取得新创建的新线程中的call()方法返回的结果
			System.out.println("sum = " + sum);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

	}
}

class MyCallable implements Callable<List<String>> {
	private int i = 0;

	// 与run()方法不同的是，call()方法具有返回值
	@Override
	public List<String> call() {
		List<String> list=new ArrayList<>();
		int sum = 0;
		for (; i < 100; i++) {
			System.out.println(Thread.currentThread().getName() + " " + i);
			sum += i;
			list.add(sum+"");
			System.out.println(list);
		}
		return list;
	}

}
