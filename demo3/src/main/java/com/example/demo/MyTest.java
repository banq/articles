package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class MyTest {

	private final Map<Object, Consumer<Object>> listeners = new ConcurrentHashMap<>();

	public static Optional<String> parseText(File file) {
		return file.getType() == File.Type.TEXT ?
				Optional.of("Text file: " + file.getContent()) :
				Optional.empty();
	}

	public static Optional<String> parseAudio(File file) {
		return file.getType() == File.Type.AUDIO ?
				Optional.of("Audio file: " + file.getContent()) :
				Optional.empty();
	}

	public static Optional<String> parseVideo(File file) {
		return file.getType() == File.Type.VIDEO ?
				Optional.of("Video file: " + file.getContent()) :
				Optional.empty();
	}

	public static void log(String message) {
		System.out.println("Logging: " + message);
	}

	public static void save(String message) {
		System.out.println("Saving: " + message);
	}

	public static void send(String message) {
		System.out.println("Sending: " + message);
	}

	public static void execute(List<Runnable> tasks) {
		tasks.forEach(Runnable::run);
	}

	public static void withResource(Consumer<Resource> consumer) {

		//模板模式
		Resource resource = new Resource();
		try {
			consumer.accept(resource);
		} finally {
			resource.dispose();
		}

	}

	public static void publishText(String text, Predicate<String> filter, UnaryOperator<String>
			format) {
		if (filter.test(text)) {
			System.out.println(format.apply(text));
		}
	}

	public static void main(String[] args) {

		//命令模式 https://www.jdon.com/51615
		List<Runnable> tasks = new ArrayList<>();
		tasks.add(() -> log("Hi"));
		tasks.add(() -> save("Cheers"));
		tasks.add(() -> send("Bye"));

		execute(tasks);

		//模板模式 https://www.jdon.com/51632
		withResource(resource -> resource.useResource());
		withResource(resource -> resource.employResource());


		//职责链模式 https://www.jdon.com/51614
		File file = new File(File.Type.AUDIO, "Dream Theater  - The Astonishing");
		String result = Stream.<Function<File, Optional<String>>>of( // [1]
				MyTest::parseText,
				MyTest::parseAudio,
				MyTest::parseVideo)
				.map(f -> f.apply(file)) // [2]
				.filter(Optional::isPresent) // [3]
				.findFirst() // [4]
				.flatMap(Function.identity()) // [5]
				.orElseThrow(() -> new RuntimeException("Unknown file: " + file));


		//装饰者模式 https://www.jdon.com/51608
		double netSalary = new DefaultSalaryCalculator()
				.andThen(Taxes::generalTax)
				.andThen(Taxes::regionalTax)
				.andThen(Taxes::healthInsurance)
				.applyAsDouble(30000.00);


		//责任链模式 https://www.jdon.com/51607
		// First processing object
		UnaryOperator<String> headerProcessing =
				(String text) -> "From Raoul, Mario and Alan: " + text;
		//Second processing object
		UnaryOperator<String> spellCheckerProcessing =
				(String text) -> text.replaceAll("labda", "lambda");
		// Compose the two functions resulting in a chain of operations.
		Function<String, String> pipeline = headerProcessing.andThen(spellCheckerProcessing);
		String result2 = pipeline.apply("Aren't labdas really sexy?!!");
		System.out.println(result2);

		//观察者模式  https://www.jdon.com/51633
		MyTest observable = new MyTest();
		observable.register("key1", e -> System.out.println(e));
		observable.register("key2", System.out::println);
		observable.sendEvent("Hello World!");

		//战略模式  https://www.jdon.com/51634
		publishText("DEBUG - I'm here", MyTest::acceptAll, MyTest::noFormatting);
		publishText("ERROR - something bad happened", MyTest::acceptErrors,
				MyTest::formatError);

	}

	public static boolean acceptAll(String text) {
		return true;
	}

	public static String noFormatting(String text) {
		return text;
	}

	public static boolean acceptErrors(String text) {
		return text.startsWith("ERROR");
	}

	public static String formatError(String text) {
		return text.toUpperCase();
	}

	public void register(Object key, Consumer<Object> listener) {
		listeners.put(key, listener);
	}

	public void unregister(Object key) {
		listeners.remove(key);
	}

	public void sendEvent(Object event) {
		listeners.values().forEach(listener -> listener.accept(event));
	}

}
