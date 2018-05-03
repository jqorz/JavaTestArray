package method;

/**
 * Created by jqorz on 2017/9/10.
 * 测试单例写法
 */
public class Single {
    private volatile static Single mInstance;
    private int a = 0;

    public static Single getInstance() {
        if (mInstance == null) {
            synchronized (Single.class) {
                if (mInstance == null) {
                    mInstance = new Single();
                }
            }
        }
        return mInstance;
    }

    public int getA() {
        return a;
    }

    public void setA() {
        this.a = 3;
    }
}
