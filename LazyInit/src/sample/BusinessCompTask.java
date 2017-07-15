package sample;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.atomic.AtomicInteger;

public class BusinessCompTask extends RecursiveTask<BatchComputeResult> {

	private static final long serialVersionUID = 1L;
	private final List<String> letters;
	private final char key;
	private final SharedParameter sharedParameter;
	
	public BusinessCompTask(char key, List<String> letters,
			SharedParameter sharedParameter) {
		this.key = key;
		this.letters = letters;
		this.sharedParameter = sharedParameter;
	}

	@Override
	protected BatchComputeResult compute() {
		int count = 0;
		List<String> lettersresult = new ArrayList<String>();
		for (String s : letters) {
			count++;
			if (String.valueOf(key).equals(s)) {
				//((AtomicInteger)sharedParameter.getBlockEventResult()).incrementAndGet();
				((AtomicInteger)sharedParameter.getCount(key)).incrementAndGet();
				sharedParameter.testGetHelp();
				lettersresult.add(s);
			}
		}
		BatchComputeResult batchComputeResult = new BatchComputeResult(
				lettersresult, count);
		return batchComputeResult;
	}
	


	public List<String> getLetters() {
		return letters;
	}

	public char getKey() {
		return key;
	}

}
