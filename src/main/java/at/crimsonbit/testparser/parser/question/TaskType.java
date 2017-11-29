package at.crimsonbit.testparser.parser.question;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import com.koloboke.collect.map.hash.HashObjObjMap;
import com.koloboke.collect.map.hash.HashObjObjMaps;

import at.crimsonbit.testparser.parser.question.solutions.Solution;
import at.crimsonbit.testparser.parser.question.tasks.Task;
import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;

@SuppressWarnings("rawtypes")
public class TaskType {
	public static final HashObjObjMap<EnumTaskType, TaskType> tasks;

	static {
		HashObjObjMap<EnumTaskType, Class<? extends Solution>> solutionMap = HashObjObjMaps.newMutableMap();
		HashObjObjMap<EnumTaskType, Class<? extends Task>> taskMap = HashObjObjMaps.newMutableMap();
		HashObjObjMap<EnumTaskType, TaskType> tmpMap = HashObjObjMaps.newMutableMap();

		FastClasspathScanner scanner = new FastClasspathScanner(Solution.class.getPackage().getName());
		scanner.matchSubclassesOf(Solution.class, c -> addSolutionToTaskType(solutionMap, c));
		scanner.scan();

		scanner = new FastClasspathScanner(Task.class.getPackage().getName());
		scanner.matchSubclassesOf(Task.class, c -> addSolutionToTaskType(taskMap, c));
		scanner.scan();
		System.out.println(taskMap.getClass().getName());
		for (EnumTaskType type : EnumTaskType.values()) {
			tmpMap.put(type, new TaskType(solutionMap.get(type), taskMap.get(type)));
		}
		tasks = HashObjObjMaps.newImmutableMap(tmpMap);
	}

	private static <T> void addSolutionToTaskType(HashObjObjMap<EnumTaskType, Class<? extends T>> solutions,
			Class<? extends T> c) {
		try {
			Field f = c.getDeclaredField("type");
			f.setAccessible(true);
			solutions.put((EnumTaskType) f.get(null), c);
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
			System.err.println("Error in class: " + c.getName());
		}
	}

	private final Class<? extends Solution<?>> solutionClass;
	private final Class<? extends Task<?>> taskClass;

	@SuppressWarnings("unchecked")
	private TaskType(Class<? extends Solution> c, Class<? extends Task> t) {
		this.solutionClass = (Class<? extends Solution<?>>) c;
		this.taskClass = (Class<? extends Task<?>>) t;
	}

	public Task<?> getTask(String taskText) {
		try {
			return taskClass.getConstructor(String.class).newInstance(taskText);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			throw new RuntimeException(e);
		}
	}

	public Solution<?> getSolution(String taskText) {
		try {
			return solutionClass.getConstructor(String.class).newInstance(taskText);

		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			throw new RuntimeException(e);
		}
	}

	public static TaskType getTaskType(EnumTaskType taskType) {
		return tasks.get(taskType);
	}
}
