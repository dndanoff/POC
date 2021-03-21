package io.github.dndanoff.utils;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.mockito.Mockito;
import org.mockito.invocation.Invocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AsyncTestUtils {
	
	private static final Logger log = LoggerFactory.getLogger(AsyncTestUtils.class);
	
	private static final ExecutorService pool = Executors.newFixedThreadPool(2);
	
	public static final CompletableFuture<Object> waitConsumerMethodToBeExecuted(Object kafkaConsumer, String methodName, AtomicBoolean ready) throws InterruptedException, ExecutionException{
		
		 return CompletableFuture.supplyAsync(() -> {
			
			while(
				Mockito.mockingDetails(kafkaConsumer).getInvocations()
				.stream().noneMatch(new Predicate<Invocation>(){
					@Override
					public boolean test(Invocation t) {
						return t.getMethod().getName().equalsIgnoreCase(methodName);
					}
				})
			){
				try {
					Thread.sleep(200);
					log.info("Sleeping {}", Mockito.mockingDetails(kafkaConsumer).getInvocations());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					log.error(e.getMessage(), e);
					ready.set(true);
				}
			}
			ready.set(true);
			return null;
		},pool);
		
	}
	
	public static final Future<Object> waitConsumerMethodToBeExecuted2(Object kafkaConsumer, String methodName,
			AtomicBoolean ready, long timeoutMilis)  {

		return pool.submit(new Callable<Object>() {
			@Override
			public Object call() throws Exception {

				try {
					log.info("Waiting for method from {}.{} to be executed",kafkaConsumer.getClass().getName(), methodName);
					long sleepMilis = 200;
					long totalMilis = 0;
					long _timeoutMilis = timeoutMilis;
					while (Mockito.mockingDetails(kafkaConsumer).getInvocations().stream()
							.noneMatch((Invocation t) -> t.getMethod().getName().equalsIgnoreCase(methodName))) {

						if (totalMilis >= _timeoutMilis) {
							throw new InterruptedException("The method:" + methodName + " was not called after "
									+ _timeoutMilis + " aborting the wait and thread");
						}
						Thread.sleep(sleepMilis);
						totalMilis += sleepMilis;
						log.info("Sleeping {}", Mockito.mockingDetails(kafkaConsumer).getInvocations());

					}
					ready.set(true);
					return null;

				} catch (Throwable e) {
					log.error(e.getMessage(), e);
					ready.set(true);
					return null;
				}
			}
		});

	}
	
	public static final boolean waitForMethodExecution(Object obj,String methodName, long timeoutMilis){
		AtomicBoolean ready = new AtomicBoolean(false);
		AsyncTestUtils.waitConsumerMethodToBeExecuted2(obj,methodName,ready,timeoutMilis);
		boolean success=true;
		while(!ready.get()){
			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				log.error(e.getMessage(), e);
				success=false;
			}
		}
		return success;
	}
	
	public static final <T> void waitForConditionWithTimeout(Supplier<T> sup, Predicate<T> predicate, long timeout){
		AtomicBoolean ready = new AtomicBoolean(false);
		waitForSupplierToFullfilPredicate(sup,predicate,timeout,ready);
		while(!ready.get()){
			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				log.error(e.getMessage(), e);
			}
		}
	}
	
	private static <T> void waitForSupplierToFullfilPredicate(Supplier<T> sup, Predicate<T> predicate, long timeout, AtomicBoolean b){
		pool.submit(new Callable<T>(){

			@Override
			public T call() throws Exception {
				long sleepMilis = 200;
				long totalMilis = 0;
				long timeoutMilis = timeout;
				try{
					while(predicate.test(sup.get())==false){
						if (totalMilis >= timeoutMilis) {
							throw new InterruptedException("waitForSupplierToFullfilPredicate " + "was not called after "
									+ timeoutMilis + " aborting the wait and thread");
						}
						Thread.sleep(sleepMilis);
						totalMilis += sleepMilis;
					}
				}catch(Throwable e){
					log.error(e.getMessage(), e);
					b.set(true);
					return null;
				}
				b.set(true);
				return null;
			}});
	}

}
