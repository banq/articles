package sample;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

public class SharedParameter {

	private final transient ConcurrentMap<Character, AtomicInteger> map;

	private final transient ConcurrentMap<Integer, Helper> map2;
	
	private Foo foo = new Foo();

	public SharedParameter(ConcurrentMap<Character, AtomicInteger> map) {
		super();
		this.map = map;
		map2 = new ConcurrentHashMap<>();

	}

	/**
	 * 空错误，使用putIfAbsent没用 替换成return map.get(modelKey)也没有用 只能再加synchronized 最差写法
	 * 
	 * @param modelKey
	 * @return
	 */
	public AtomicInteger getCount(char modelKey) {
		Object model = map.get(modelKey);
		if (model == null) {
			model = new AtomicInteger(0);
			map.put(modelKey, (AtomicInteger) model);
		}
		return (AtomicInteger) model;
	}

	public synchronized AtomicInteger getCount012(char modelKey) {
		Object model = map.get(modelKey);
		if (model == null) {
			model = new AtomicInteger(0);
			model = map.put(modelKey, (AtomicInteger) model);
		}
		return (AtomicInteger) model;
	}

	/**
	 * 类似AtomicReference
	 * 
	 * @param modelKey
	 * @return
	 */
	public AtomicInteger getCount001(char modelKey) {
		Object model = map.get(modelKey);
		if (model == null) {
			model = new AtomicInteger(0);
			if (map.putIfAbsent(modelKey, (AtomicInteger) model) != null)
				model = map.get(modelKey);
		}
		return (AtomicInteger) model;
	}

	/**
	 * 无用
	 * 
	 * @param modelKey
	 * @return
	 */

	public AtomicInteger getCount0(char modelKey) {
		Object model = map.get(modelKey);
		if (model == null) {
			model = new AtomicInteger(0);
			map.putIfAbsent(modelKey, (AtomicInteger) model);
		}
		return (AtomicInteger) model;
	}

	// ok
	public Object getCount01(char modelKey) {
		Object model = map.get(modelKey);
		if (model != null)
			return (AtomicInteger) model;

		Object modelNew = new AtomicInteger(0);
		model = map.putIfAbsent(modelKey, (AtomicInteger) modelNew);

		return model != null ? model : modelNew;
	}

	/**
	 * 必须使用putIfAbsent
	 * 
	 * @param modelKey
	 * @return
	 */
	public AtomicInteger getCount1(char modelKey) {
		if (!map.containsKey(modelKey)) {
			Object model = new AtomicInteger(0);
			model = map.putIfAbsent(modelKey, (AtomicInteger) model);
		}
		return (AtomicInteger) map.get(modelKey);
	}

	/**
	 * 必须使用putIfAbsent
	 * 
	 * @param modelKey
	 * @return
	 */
	public AtomicInteger getCount11(char modelKey) {
		if (!map.containsKey(modelKey)) {
			map.put(modelKey, new AtomicInteger(0));
		}
		return (AtomicInteger) map.get(modelKey);
	}

	public AtomicInteger getCount2(char key) {
		AtomicInteger existo = (AtomicInteger) map.get(key);
		AtomicInteger newo = null;
		if (existo == null) {
			newo = new AtomicInteger(0);
			existo = (AtomicInteger) map.putIfAbsent(key, newo);
		}
		return existo != null ? existo : newo;
	}

	/**
	 * 最新简单执行方式，但是每次get都会执行putIfAbsent，相当于每次执行一次检查是否存在 的判断。适合数据量不大的场合。
	 * 
	 * @param key
	 * @return
	 */
	public AtomicInteger getCountSimple(char key) {
		map.putIfAbsent(key, new AtomicInteger(0));
		return (AtomicInteger) map.get(key);
	}

	public AtomicInteger getCount3(char key) {
		AtomicInteger existo = (AtomicInteger) map.get(key);
		AtomicInteger newo = null;
		if (existo == null) {
			existo = getCount31(key);
		}
		return existo != null ? existo : newo;
	}

	public AtomicInteger getCount31(char key) {
		AtomicInteger newo = new AtomicInteger(0);
		AtomicInteger existo = (AtomicInteger) map.putIfAbsent(key, newo);
		return existo != null ? existo : newo;
	}
	
	public void testGetHelp(){
//		Helper helper = foo.getHelper();//not ok
//		Helper helper = foo.getHelper2();//ok
		Helper helper = foo.getHelper3();//better
		this.map2.put(helper.hashCode(), helper);
	}

	public ConcurrentMap<Integer, Helper> getMap2() {
		return map2;
	}

		
	


}
