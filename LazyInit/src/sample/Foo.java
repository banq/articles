package sample;

import java.util.concurrent.atomic.AtomicReference;

public class Foo {

	private Helper helper;
	private AtomicReference<Helper>  helper2 = new AtomicReference<>();

	//not ok
	public Helper getHelper() {
		if (helper == null) {
			helper = new Helper();
		}
		return helper;
	}

	//ok
	public Helper getHelper2() {
		if (helper == null) {
			synchronized (this) {
				if (helper == null) {
					helper = new Helper();
				}
			}
		}
		return helper;
	}

	
	public Helper getHelper3() {
		Helper helper = this.helper2.get();
		if (helper == null){
			helper = new Helper();
			if (!this.helper2.compareAndSet(null, helper)) {
				helper = helper2.get();
			}
		}
		return helper;
	}

}
