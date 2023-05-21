package com.muqi.backendsl.exception;

public class TaskException extends Exception {

    private static final long serialVersionUID = 1L;

    private final Code code;

    public TaskException(String msg, Code code) {
        this(msg, code, null);
    }

    public TaskException(String msg, Code code, Exception exception) {
        super(msg, exception);
        this.code = code;
    }

    public Code getCode() {
        return code;
    }

    public enum Code {
        TASK_EXISTS, NO_TASK_EXISTS, TASK_ALREADY_STARTED, UNKNOWN, CONFIG_ERROR, TASK_NODE_NOT_AVAILABLE
    }
}

/*

第一个构造函数将消息和代码作为参数，并调用第二个构造函数。
第二个构造函数将消息、Code 和 Exception 作为参数，并调用超类构造函数，将消息和异常作为参数传递。
第三个构造函数没有参数，它是默认构造函数。
它还有一个返回 Code 变量的 getCode（） 方法。该类还有一个名为“Code”的内部枚举，它定义了不同类型的代码，如TASK_EXISTS、NO_TASK_EXISTS、TASK_ALREADY_STARTED、UNKNOWN、CONFIG_ERROR TASK_NODE_NOT_AVAILABLE。这些代码用于指示异常的具体原因。
 */
