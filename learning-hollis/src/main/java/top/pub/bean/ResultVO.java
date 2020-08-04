package top.pub.bean;

/**
 * @description: TODO
 * @author: H.K
 * @program: beTopJavaer
 * @create: 2020-07-27 11:03
 */
public class ResultVO<T> {
    private int code;
    private String message;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
