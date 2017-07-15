package sample;

import java.util.List;

public class BatchComputeResult {
	
	private final List<String> letters;
	private final int count;
	
	public BatchComputeResult(List<String> letters, int count) {
		super();
		this.letters = letters;
		this.count = count;
	}
	public List<String> getLetters() {
		return letters;
	}
	public int getCount() {
		return count;
	}
	
	
	

}
