package graph.problems;

import java.util.LinkedList;
import java.util.Queue;

public class NumberIslands {
    private int[] ivals = new int[]{ 0, 0, -1, 1};
    private int[] jvals = new int[]{ -1, 1, 0, 0};

    public int getNumberOfIslands(char[][] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        
        int count = 0, rows = arr.length;
        for(int i = 0; i < rows; i++) {
            int cols = arr[i].length;
            for (int j = 0; j < cols; j++) {
                if (arr[i][j] == 'w') {
                    continue;
                }
                else {
                    dfs(arr, i, j, rows, cols);
                    count++;
                }
            }
        }
        return count;
    }

    private void dfs(char[][] arr, int i, int j, int row, int col) {
        if (i < 0 || j < 0 || i > row || j > col) {
            return;
        }
        if (arr[i][j] == 'w') {
            return;
        }

        arr[i][j] = 'w';
        for (int x = 0; x < 4; x++) {
            int iIndex = i + ivals[x];
            int jIndex = j + jvals[x];
            if (iIndex >= 0 && jIndex >=0 && iIndex < row && jIndex < col && arr[iIndex][jIndex] != 'w') {
                dfs(arr, iIndex, jIndex, row, col);
            }
        }
    }

    public static void main(String[] args) {
        NumberIslands numIslands = new NumberIslands();
        char[][] arr = new char[][] {
            new char[] {'w', 'l', 'w', 'w', 'l', 'w' },
            new char[] {'l', 'l', 'w', 'w', 'l', 'w' },
            new char[] {'w', 'l', 'w', 'w', 'w', 'l' },
            new char[] {'w', 'w', 'w', 'l', 'l', 'w' },
            new char[] {'w', 'l', 'w', 'l', 'l', 'w' },
            new char[] {'w', 'w', 'w', 'w', 'w', 'l' }
        };
        int countOfIslands = numIslands.getNumberOfIslands(arr);
        System.out.println("Number of islands = " + countOfIslands);
    }
}
