package org.example.sparsearray;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * created by huangyating
 *
 * @Date 2021/7/1
 */
public class SprseArrayStorage {
    public static void main(String[] args) {
        // 创建一个原始的二维数组 11 * 11
        // 0: 表示没有棋子， 1 表示 黑子 2 表蓝子
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[4][5] = 2;
        // 输出原始的二维数组
        System.out.println("原始的二维数组~~");
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        // 将二维数组 转 稀疏数组的思
        // 1. 先遍历二维数组 得到非0数据的个数
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    sum++;
                }
            }
        }

        // 2. 创建对应的稀疏数组
        int sparseArr[][] = new int[sum + 1][3];
        // 给稀疏数组赋值
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;

        // 遍历二维数组，将非0的值存放到 sparseArr中
        int count = 0; //count 用于记录是第几个非0数据
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }
        //将稀疏数组存储到文件中去
        String filePath = "E:\\Tracy\\map.data";
        try(BufferedWriter out = new BufferedWriter(new FileWriter(filePath))){
            //遍历数组，每行存储
            for(int [] row: sparseArr){
                for (int num : row){
                    out.write(num + " ");
                }
                out.newLine();
            }
            out.flush();
        }catch (IOException e){
        }
        // 输出稀疏数组的形式
        System.out.println();
        System.out.println("得到稀疏数组为~~~~");
        for (int i = 0; i < sparseArr.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n", sparseArr[i][0], sparseArr[i][1], sparseArr[i][2]);
        }
        System.out.println();
        //将稀疏数组 --》 恢复成 原始的二维数组
		/*
		 *  1. 先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组，比如上面的  chessArr2 = int [11][11]
			2. 在读取稀疏数组后几行的数据，并赋给 原始的二维数组 即可.
		 */

		//将文件中的数据读取出来
        try(BufferedReader in = new BufferedReader(new FileReader(filePath))){
            //读取第一行
            int rows = 0;
            int cols = 0;
            int nums = 0;
            String line = in.readLine();
            String[] s1 = line.split(" ");
            rows = Integer.parseInt(s1[0]);
            cols = Integer.parseInt(s1[1]);
            nums = Integer.parseInt(s1[2]);
            //初始化数组
            int chessArr2[][] = new int[rows][cols];
            int start = 0;
            while((line = in.readLine()) != null){
                start ++;
                if(start <= sum){
                    String [] split = line.split(" ");
                    chessArr2[Integer.parseInt(split[0])][Integer.parseInt(split[1])] = Integer.parseInt(split[2]);
                }
            }
            System.out.println();
            System.out.println("恢复后的二维数组");
            // 输出恢复后的二维数组
            for (int[] row : chessArr2) {
                for (int data : row) {
                    System.out.printf("%d\t", data);
                }
                System.out.println();
            }
        }catch (Exception e){

        }
    }
}
