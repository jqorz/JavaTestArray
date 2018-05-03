package encode;

/**
 * 猫脸加密
 */
public class Cat {
    static int iteration = 10;//每次基于Cat映射的循环次数，即取循环iteration次后的xn+1和yn+1
    private static int[][] data = {{12, 23, 34}, {45, 234, 245}, {32, 145, 112}};
    private static int[][] newData = new int[3][3];
    private static int[][] newnewData = new int[3][3];

    public static void main(String[] args) {
        System.out.print("原数据为:\n");
        output(data);
        for (int i = 0; i < data.length; i++) {//行数x
            for (int j = 0; j < data[i].length; j++) {//列数y
                IntClass ic = encrypt(i, j);
                int x = ic.getX();
                int y = ic.getY();
                newData[x][y] = data[i][j];
            }
        }
        System.out.print("\n加密后的数据为:\n");
        output(newData);

        for (int i = 0; i < newData.length; i++) {//行数x
            for (int j = 0; j < newData[i].length; j++) {//列数y
                IntClass ic = decrypt(i, j);

                int x = ic.getX();
                int y = ic.getY();
                newnewData[x][y] = newData[i][j];
            }
        }
        System.out.print("\n解密后的数据为:\n");
        output(newnewData);
    }

    //解密 使用一个类保存返回的x,y
    private static IntClass decrypt(int x, int y) {
        for (int i = 0; i < iteration; i++) {
            int x1 = (2 * x - y + 3) % 3;
            int y1 = (y - x + 3) % 3;
            x = x1;
            y = y1;
        }
        return new IntClass(x, y);
    }

    //加密 使用一个类保存返回的x,y
    private static IntClass encrypt(int x, int y) {
        for (int i = 0; i < iteration; i++) {
            int x1 = (x + y) % 3;
            int y1 = (x + 2 * y) % 3;
            x = x1;
            y = y1;
        }
        return new IntClass(x, y);
    }

    //输出
    private static void output(int[][] data) {
        for (int[] d : data) {
            for (int dd : d)
                System.out.print(" " + dd);
            System.out.println();
        }
    }

    static class IntClass {
        private int x;
        private int y;

        IntClass(int x, int y) {

            this.x = x;
            this.y = y;
        }

        int getX() {
            return x;
        }

        int getY() {
            return y;
        }

    }
}
