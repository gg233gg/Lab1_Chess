
/*                  !!!
   请在每一行输入后加一个回车,来确保程序正常输入
                    !!!                 */

import java.util.Scanner;

public class Chess {

    public static void main(String[] args) {

        Chess chess = new Chess();
        Scanner input = new Scanner(System.in);

        //输入棋盘
        try {
            for (int i = 0; i < size; i++) {
                String line = input.nextLine();
                for (int j = 0; j < size; j++) {
                    chess.writeBoard(line.charAt(2 * j), i, j);
                }
            }
        } catch (Exception ex) {
            System.out.println("invalid input");
            System.exit(0);
        }

        //命令模式
        while (true) {
            try {
                String command = input.nextLine();
                if (command.equals("print")) chess.printBoard();
                else if (command.equals("exit")) System.exit(0);
                else if (command.startsWith("at")) {
                    //chess.atBoard((int)command.charAt(3)-48,(int)command.charAt(5)-48);
                    StringBuilder num = new StringBuilder();
                    for (char ch : command.toCharArray())
                        if (Character.isDigit(ch)) num.append(ch);
                    chess.atBoard(num.charAt(0) - 48, num.charAt(1) - 48);
                } else System.out.println("invalid input");
            }
            catch (Exception ex){
                System.out.println("invalid input");
            }
        }

    }


    private final char[][] board;

    private static final int size = 5;

    Chess() {
        board = new char[size][size];
    }

    //打印整个棋盘
    public void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    //打印(x,y)周边的棋子
    public void atBoard (int x, int y) throws Exception {
        System.out.print("[");
        if (readBoard(x, y)) System.out.print(",");
        else {
            System.out.print("\b");
            throw new Exception();
        }
        if (readBoard(x, y - 1)) System.out.print(",");
        if (readBoard(x, y + 1)) System.out.print(",");
        if (readBoard(x - 1, y)) System.out.print(",");
        if (readBoard(x + 1, y)) System.out.print(",");
        System.out.print("\b");
        System.out.println("]");
    }

    //打印位于(x,y)的棋子 格式为(x,y,ch) 并返回一个布尔值来表示是否打印成功
    public boolean readBoard(int x, int y) {
        if ((x < 0) || (y < 0) || (x > 4) || (y > 4)) {
            return false;
        } else {
            System.out.print("(" + x + "," + y + "," + board[y][x] + ")");
            return true;
        }
    }

    //在(x,y)处写入棋子
    public void writeBoard(char ch, int x, int y) {
        board[x][y] = ch;
    }
}
