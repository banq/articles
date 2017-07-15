package sample;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class BusinessCompTaskSharding extends RecursiveTask<BatchComputeResult> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final char key;
	private final List<String> letters;
	
	private final int minUnitCount;
	private final SharedParameter sharedParameter;
	
	public BusinessCompTaskSharding(char key, List<String> letters,
			int minUnitCount, SharedParameter sharedParameter) {
		super();
		this.key = key;
		this.letters = letters;
		this.minUnitCount = minUnitCount;
		this.sharedParameter = sharedParameter;
	}

	public List<BusinessCompTask> sharding(BusinessCompTask task) {
		List<BusinessCompTask> tasks = new ArrayList<BusinessCompTask>();
		if (task.getLetters().size() <= minUnitCount) {
			BusinessCompTask one = new BusinessCompTask(key, task.getLetters(), sharedParameter);
			tasks.add(one);
		} else {
			int mid = task.getLetters().size() / 2;
			BusinessCompTask left = new BusinessCompTask(key, task.getLetters().subList(
					0, mid),sharedParameter);
			BusinessCompTask right = new BusinessCompTask(key, task.getLetters().subList(
					mid, task.getLetters().size()), sharedParameter);
			tasks.addAll(sharding(left));
			tasks.addAll(sharding(right));
		}
		return tasks;
	}

	@Override
	protected BatchComputeResult compute() {
		BusinessCompTask bigger = new BusinessCompTask(key, letters, sharedParameter);
		List<BusinessCompTask> tasks = sharding(bigger);
		Collection<BusinessCompTask> tasks2 = invokeAll(tasks);
		List<String> lettersresult = new ArrayList<String>();
		int countresult = 0;
		for (BusinessCompTask task : tasks2) {
			BatchComputeResult batchComputeResultsub = task.join();
			lettersresult.addAll(batchComputeResultsub.getLetters());
			countresult = countresult + batchComputeResultsub.getCount();
		}
		BatchComputeResult batchComputeResult = new BatchComputeResult(
				lettersresult, countresult);
		return batchComputeResult;
	}

}
