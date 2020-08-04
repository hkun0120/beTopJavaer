package top.pub.bean;

/**
 * @description: TODO
 * @author: H.K
 * @program: beTopJavaer
 * @create: 2020-02-04 14:15
 */
public class StdContract {
    private String cntrId;
    private String cntrNo;
    private String applNo;

    public String getCntrId() {
        return cntrId;
    }

    public void setCntrId(String cntrId) {
        this.cntrId = cntrId;
    }

    public String getCntrNo() {
        return cntrNo;
    }

    public void setCntrNo(String cntrNo) {
        this.cntrNo = cntrNo;
    }

    public String getApplNo() {
        return applNo;
    }

    public void setApplNo(String applNo) {
        this.applNo = applNo;
    }

    @Override
    public String toString() {
        return "StdContract{" +
                "cntrId='" + cntrId + '\'' +
                ", cntrNo='" + cntrNo + '\'' +
                ", applNo='" + applNo + '\'' +
                '}';
    }
}
