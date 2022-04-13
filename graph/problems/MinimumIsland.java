package graph.problems;

class Land {
    private int landSize;
    public Land() {
        this.landSize = 0;
    }

    public void incLandSize(int value) {
        this.landSize += value;
    }
    public int getLandSize() {
        return this.landSize;
    }
    public void resetLandSize() {
        this.landSize = 0;
    }
}

public class MinimumIsland {
    private int[] ivals = new int[]{ 0, 0, -1, 1};
    private int[] jvals = new int[]{ -1, 1, 0, 0};

    public int getMinIslandSize(char[][] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        Integer minLandSize = Integer.MAX_VALUE;
        Land land = new Land();
        
        int rows = arr.length;
        for(int i = 0; i < rows; i++) {
            int cols = arr[i].length;
            for (int j = 0; j < cols; j++) {
                if (arr[i][j] == 'w') {
                    continue;
                } else {
                    land.resetLandSize();
                    dfs(arr, i, j, rows, cols, land);
                    if (land.getLandSize() < minLandSize) {
                        minLandSize = land.getLandSize();
                    }
                }
            }
        }
        return minLandSize;
    }

    private void dfs(char[][] arr, int i, int j, int row, int col, Land land) {
        if (i < 0 || j < 0 || i > row || j > col) {
            return;
        }
        if (arr[i][j] == 'w') {
            return;
        }
        land.incLandSize(1);
        arr[i][j] = 'w';
        for (int x = 0; x < 4; x++) {
            int iIndex = i + ivals[x];
            int jIndex = j + jvals[x];
            if (iIndex >= 0 && jIndex >=0 && iIndex < row && jIndex < col && arr[iIndex][jIndex] != 'w') {
                dfs(arr, iIndex, jIndex, row, col, land);
            }
        }
    }

    public static void main(String[] args) {
        MinimumIsland minimumIsland = new MinimumIsland();
        char[][] arr = new char[][] {
            new char[] {'w', 'l', 'w', 'w', 'l', 'w' },
            new char[] {'l', 'l', 'w', 'w', 'l', 'w' },
            new char[] {'w', 'l', 'w', 'w', 'w', 'w' },
            new char[] {'w', 'w', 'w', 'l', 'l', 'w' },
            new char[] {'w', 'w', 'w', 'l', 'l', 'w' },
            new char[] {'w', 'w', 'w', 'w', 'l', 'w' }
        };
        int landSize = minimumIsland.getMinIslandSize(arr);
        System.out.println("Min Island Size = " + landSize);
    }
}
