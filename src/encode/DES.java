package encode;

/**
 * DES加密
 */
public class DES {

    private static int c[] = {
            57, 49, 41, 33, 25, 17, 9,
            1, 58, 50, 42, 34, 26, 18,
            10, 2, 59, 51, 43, 35, 27,
            19, 11, 3, 60, 52, 44, 36
    };
    private static int d[] = {
            63, 55, 47, 39, 31, 23, 15,
            7, 62, 54, 46, 38, 30, 22,
            14, 6, 61, 53, 45, 37, 29,
            21, 13, 5, 28, 20, 12, 4
    };
    private static int keyoff[] = {
            1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1
    };
    private static int di[] = {
            14, 17, 11, 24, 1, 5,
            3, 28, 15, 6, 21, 10,
            23, 19, 12, 4, 26, 8,
            16, 7, 27, 20, 13, 2,
            41, 52, 31, 37, 47, 55,
            30, 40, 51, 45, 33, 48,
            44, 49, 39, 56, 34, 53,
            46, 42, 50, 36, 29, 32
    };
    private static int ip[] = {
            58, 50, 42, 34, 26, 18, 10, 2,
            60, 52, 44, 36, 28, 20, 12, 4,
            62, 54, 46, 38, 30, 22, 14, 6,
            64, 56, 48, 40, 32, 24, 16, 8,
            57, 49, 41, 33, 25, 17, 9, 1,
            59, 51, 43, 35, 27, 19, 11, 3,
            61, 53, 45, 37, 29, 21, 13, 5,
            63, 55, 47, 39, 31, 23, 15, 7
    };
    private static int e_operate[] = {
            32, 1, 2, 3, 4, 5,
            4, 5, 6, 7, 8, 9,
            8, 9, 10, 11, 12, 13,
            12, 13, 14, 15, 16, 17,
            16, 17, 18, 19, 20, 21,
            20, 21, 22, 23, 24, 25,
            24, 25, 26, 27, 28, 29,
            28, 29, 30, 31, 32, 1
    };
    private static int sbox[][] =
            {
                    {
                            14, 4, 13, 1, 1, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7,
                            0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8,
                            4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0,
                            15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13
                    },

                    {
                            15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10,
                            3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5,
                            0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15,
                            13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9
                    },

                    {
                            10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8,
                            13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1,
                            13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7,
                            1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12
                    },

                    {
                            7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15,
                            13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9,
                            10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4,
                            3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14
                    },

                    {
                            2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9,
                            14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6,
                            4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14,
                            11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3
                    },

                    {
                            12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11,
                            10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8,
                            9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6,
                            4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13
                    },

                    {
                            4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1,
                            13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6,
                            1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2,
                            6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12
                    },

                    {
                            13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 13, 14, 5, 0, 12, 7,
                            1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2,
                            7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8,
                            2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11
                    }
            };
    private static int p_operate[] = {
            16, 7, 20, 21,
            29, 12, 28, 17,
            1, 15, 23, 26,
            5, 18, 31, 10,
            2, 8, 24, 14,
            32, 27, 3, 9,
            19, 13, 30, 6,
            22, 11, 4, 25
    };
    private static int back_ip[] =
            {
                    40, 8, 48, 16, 56, 24, 64, 32,
                    39, 7, 47, 15, 55, 23, 63, 31,
                    38, 6, 46, 14, 54, 22, 62, 30,
                    37, 5, 45, 13, 53, 21, 61, 29,
                    36, 4, 44, 12, 52, 20, 60, 28,
                    35, 3, 43, 11, 51, 19, 59, 27,
                    34, 2, 42, 10, 50, 18, 58, 26,
                    33, 1, 41, 9, 49, 17, 57, 25
            };
    //二进制转成字符
    private static char bitmask[] = {128, 64, 32, 16, 8, 4, 2, 1};

    public static void main(String[] args) {
        String content = "01234567";//明文
        String key = "1234567";//密钥
        int[][] cd_ = createKey(key);//生成的16个48位密钥
        char[] chars = content.toLowerCase().toCharArray();
        int[] bits = Char2Bit(chars);//将String转为2进制int[]
        //加密得到int[]
        int[] result = encrypt(bits, cd_);
        out("加密结果", result);
        String re1 = String.valueOf(Bit2Char(result));
        System.out.println("密文=" + re1);
        //解密所需的倒序K数组
        int[][] de = new int[16][48];
        for (int i = 0; i < cd_.length; i++) {
            de[i] = cd_[15 - i];
        }
        //解密得到int[]
        int[] d_re = encrypt(result, de);
        String re2 = String.valueOf(Bit2Char(d_re));
        System.out.println("明文=" + re2);
    }

    /**
     * 对2进制int[]进行加密解密
     *
     * @param cd 生成的16个48位密钥
     */
    private static int[] encrypt(int[] bits, int[][] cd) {

        int[] L0 = new int[32];
        int[] R0 = new int[32];
        for (int i = 0; i < L0.length; i++) {
            L0[i] = bits[ip[i] - 1];
            R0[i] = bits[ip[i + 32] - 1];
        }
        int[] temp_L = L0;
        int[] temp_R = R0;
        for (int i = 0; i < 16; i++) {
            int[] tL = temp_L;
            int[] tR = temp_R;
            LR lr = startLoop(tL, tR, cd[i]);
            temp_L = lr.getL();
            temp_R = lr.getR();
        }
        //交换L16与R16,得到64位数组
        int[] RL = new int[64];
        for (int i = 0; i < temp_L.length; i++) {
            RL[i] = temp_R[i];
            RL[i + 32] = temp_L[i];
        }
        //末置换
        int[] output = new int[64];
        for (int i = 0; i < output.length; i++) {
            output[i] = RL[back_ip[i] - 1];
        }
        return output;
    }


    /**
     * 用于循环生成加密的明文
     */
    private static LR startLoop(int[] L0, int[] R0, int[] cdcd) {
        //将32位R0部分置换成48位
        int[] eP = new int[48];
        for (int i = 0; i < eP.length; i++) {
            eP[i] = R0[e_operate[i] - 1];
        }
        //扩展后的48位与K1进行异或
        int[] epp = new int[48];
        for (int i = 0; i < epp.length; i++) {
            epp[i] = eP[i] ^ cdcd[i];//此为K1
        }
        //将异或后的48位转成8行6列的数组
        int[][] s_ = new int[8][6];//length=8
        for (int i = 0; i < s_.length; i++) {
            System.arraycopy(epp, i * 6, s_[i], 0, s_[i].length);
        }
        //通过S盒转换为32位2进制
        int[][] out = new int[8][4];
        for (int i = 0; i < s_.length; i++) {
            int[] temp = s_[i];//8行6列数组的每一行,length=6
            int row = temp[0] * 2 + temp[5];//十进制的行数
            int column = temp[1] * 8 + temp[2] * 4 + temp[3] * 2 + temp[4];//十进制的列数
            int o = sbox[i][row * 16 + column];//十进制的S盒输出
            for (int j = 0; j < out[i].length; j++) {
                out[i][j] = o / ((int) Math.pow(2, j)) % 2;
            }
        }
        //将生成的8x4的二维数组转为一维32位数组
        int[] out_ = new int[32];
        for (int i = 0; i < out.length; i++) {
            System.arraycopy(out[i], 0, out_, i * 4, out[i].length);
        }
        //进行P盒置换
        int[] p = new int[32];
        for (int i = 0; i < p.length; i++) {
            p[i] = out_[p_operate[i] - 1];
        }
//        out("p盒置换结果", p);
        //生成R1
        int[] p_ = new int[32];
        for (int i = 0; i < p_.length; i++) {
            p_[i] = L0[i] ^ p[i];
        }
        return new LR(R0, p_);
    }

    /**
     * 生成密钥流
     */
    private static int[][] createKey(String key) {
        char[] chars_key = key.toCharArray();
        int[] bits = Char2Bit(chars_key);
        int[] c0 = new int[28];
        int[] d0 = new int[28];
        //64位置换成56位
        for (int i = 0; i < 28; i++) {
            c0[i] = bits[c[i] - 1];
            d0[i] = bits[d[i] - 1];
        }
        //生成c0 d0
        out("c0", c0);
        out("d0", d0);
        //对C0和D0循环左移16轮
        int[][] cc = new int[16][28];
        int[][] dd = new int[16][28];
        int[] temp_c = c0;
        int[] temp_d = d0;
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 28; j++) {
                cc[i][j]=temp_c[(j-keyoff[i]+28)%28];
                dd[i][j]=temp_d[(j-keyoff[i]+28)%28];
            }
            temp_c=cc[i];
            temp_d=dd[i];
        }
        //将cc[]dd[]组合为cd[]
        int[][] cd = new int[16][56];
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 28; j++) {
                cd[i][j] = cc[i][j];
                cd[i][j + 28] = dd[i][j];
            }
        }
        //将cd[]按照表3进行压缩置换
        int[][] cd_ = new int[16][48];
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 48; j++) {
                cd_[i][j] = cd[i][di[j] - 1];
            }
        }
        //生成16个48位的密钥
        out("子密钥 cd_", cd_);
        return cd_;
    }

    /**
     * 打印
     */
    private static void out(String tag, int[] a) {
        System.out.print(tag + "-");
        System.out.print("size=" + a.length + " ");
        System.out.println();
        for (int aa : a) {
            System.out.print(" " + aa);
        }
        System.out.println();
    }

    private static void out(String tag, int[][] a) {
        System.out.print(tag + "-");
        System.out.print("size=" + a.length + " ");
        System.out.println();
        for (int i = 0; i < a.length; i++) {
            System.out.print("(" + i + ")  ");
            for (int aaa : a[i]) {
                System.out.print(" " + aaa);
            }
            System.out.println();
        }
        System.out.println();
    }


    //字符转成二进制
    private static int[] Char2Bit(char[] chars) {
        int[] bits = new int[64];
        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < 8; j++) {
                bits[i * 8 + 7 - j] = (chars[i] >> j) & 1;
            }
        }
        return bits;
    }

    //二进制转成字符
    private static char[] Bit2Char(int[] bits) {
        char[] chars = new char[256];
        char temp;
        for (int i = 0; i < 8; i++) {
            temp = 0;
            for (int j = 0; j < 8; j++) {
                if (bits[i * 8 + j] == 1) {
                    temp |= bitmask[j];
                }
            }

            chars[i] = temp;
        }
        return chars;
    }

    private static class LR {
        private static int[] L;
        private static int[] R;

        private LR(int[] l, int[] r) {
            L = l;
            R = r;
        }

        private int[] getL() {
            return L;
        }

        private int[] getR() {
            return R;
        }

    }
}
