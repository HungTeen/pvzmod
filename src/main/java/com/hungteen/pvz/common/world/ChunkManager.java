package com.hungteen.pvz.common.world;

import java.util.Random;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-15 21:34
 **/
public class ChunkManager {

    private static final int CHUNK_LEN = 16;
    private static final int HEIGHT = 8;
    private static final int COUNT = 128;
    private static final int[][] RESULT = new int[CHUNK_LEN * COUNT][CHUNK_LEN * COUNT];

    static {
        final Random rand = new Random();
//        rand.setSeed(114514);
        for(int i = 0; i < COUNT; ++ i){
            for(int j = 0; j < COUNT; ++ j){
                genChunk(rand, i, j);
            }
        }
    }

    public static int getChunkHeight(int chunkX, int chunkZ, int offsetX, int offsetZ){
        final int x = (chunkX % COUNT + COUNT) % COUNT * CHUNK_LEN + offsetX;
        final int z = (chunkZ % COUNT + COUNT) % COUNT * CHUNK_LEN + offsetZ;
        return RESULT[x][z];
    }

    public static void genChunk(Random random, int chunkX, int chunkZ){
        final int x = chunkX * CHUNK_LEN;
        final int z = chunkZ * CHUNK_LEN;
        RESULT[x][z] = random.nextInt(HEIGHT);
        RESULT[x + CHUNK_LEN - 1][z] = random.nextInt(HEIGHT);
        RESULT[x][z + CHUNK_LEN - 1] = random.nextInt(HEIGHT);
        RESULT[x + CHUNK_LEN - 1][z + CHUNK_LEN - 1] = random.nextInt(HEIGHT);
        for(int i = 0; i < CHUNK_LEN; ++ i){
            for(int j = 0; j < CHUNK_LEN; ++ j){
                final int realX = chunkX * CHUNK_LEN + i;
                final int realZ = chunkZ * CHUNK_LEN + j;
                final int height = genHeight(x, z, i, j);
                RESULT[realX][realZ] = height;
            }
        }
    }

    /**
     * Point 1 ---------- Point 3
     * -
     * -
     * -
     * -
     * Point 2 ---------- Point 4
     */
    public static int genHeight(int baseX, int baseZ, int offsetX, int offsetZ){
        //Average value between Point 1 and Point 2.
        final int value1 = getAverage(RESULT[baseX][baseZ], RESULT[baseX + CHUNK_LEN - 1][baseZ], offsetX, CHUNK_LEN - offsetX - 1);
        //Average value between Point 3 and Point 4.
        final int value2 = getAverage(RESULT[baseX][baseZ + CHUNK_LEN - 1], RESULT[baseX + CHUNK_LEN - 1][baseZ + CHUNK_LEN - 1], offsetX, CHUNK_LEN - offsetX - 1);
        final int value = getAverage(value1, value2, offsetZ, CHUNK_LEN - offsetZ - 1);
        return value;
    }

    public static int getAverage(int A, int B, int a, int b){
        return (b * A + a * B) / (a + b);
    }

}
