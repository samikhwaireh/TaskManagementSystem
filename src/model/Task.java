package model;

public class Task {
	
	public static final String TASK_IN_PROCCES = "InProcces";
	public static final String TASK_COMPLETED = "Completed";
	
	private int taskId;
	private String taskDate;
	private String expectedTime;
	private int taskCreaterId;
	private String craeterName;
	private String taskDescription;
	private int forUserId;
	private String assignedFor;
	private String isApproved;
	private int taskState;
	
	public Task(int taskId, String taskDate, String expectedTime, int taskCreaterId, String craeterName,
			String taskDescription, int forUserId, String assignedFor, String isApproved, int taskState) {
		super();
		this.taskId = taskId;
		this.taskDate = taskDate;
		this.expectedTime = expectedTime;
		this.taskCreaterId = taskCreaterId;
		this.craeterName = craeterName;
		this.taskDescription = taskDescription;
		this.forUserId = forUserId;
		this.assignedFor = assignedFor;
		this.isApproved = isApproved;
		this.taskState = taskState;
	}
	
	public Task(String taskDate, String expectedTime, int taskCreaterId, String craeterName,
			String taskDescription, int forUserId, String assignedFor, String isApproved, int taskState) {
		super();
		this.taskDate = taskDate;
		this.expectedTime = expectedTime;
		this.taskCreaterId = taskCreaterId;
		this.craeterName = craeterName;
		this.taskDescription = taskDescription;
		this.forUserId = forUserId;
		this.assignedFor = assignedFor;
		this.isApproved = isApproved;
		this.taskState = taskState;
	}

	public Task(int taskId, String taskDate, String expectedTime, int taskCreaterId, String taskDescription,
			int forUserId, String isApproved, int taskState) {
		super();
		this.taskId = taskId;
		this.taskDate = taskDate;
		this.expectedTime = expectedTime;
		this.taskCreaterId = taskCreaterId;
		this.taskDescription = taskDescription;
		this.forUserId = forUserId;
		this.isApproved = isApproved;
		this.taskState = taskState;
	}
	
	public Task() {
		super();
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public String getTaskDate() {
		return taskDate;
	}

	public void setTaskDate(String taskDate) {
		this.taskDate = taskDate;
	}

	public String getExpectedTime() {
		return expectedTime;
	}

	public void setExpectedTime(String expectedTime) {
		this.expectedTime = expectedTime;
	}

	public int getTaskCreaterId() {
		return taskCreaterId;
	}

	public void setTaskCreaterId(int taskCreaterId) {
		this.taskCreaterId = taskCreaterId;
	}

	public String getCraeterName() {
		return craeterName;
	}

	public void setCraeterName(String craeterName) {
		this.craeterName = craeterName;
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public int getForUserId() {
		return forUserId;
	}

	public void setForUserId(int forUserId) {
		this.forUserId = forUserId;
	}

	public String getAssignedFor() {
		return assignedFor;
	}

	public void setAssignedFor(String assignedFor) {
		this.assignedFor = assignedFor;
	}

	public String getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(String isApproved) {
		this.isApproved = isApproved;
	}

	public int getTaskState() {
		return taskState;
	}

	public void setTaskState(int taskState) {
		this.taskState = taskState;
	}

	public static String getTaskInProcces() {
		return TASK_IN_PROCCES;
	}

	public static String getTaskCompleted() {
		return TASK_COMPLETED;
	}

}
