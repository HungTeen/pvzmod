package com.hungteen.pvz.common.world;

import java.util.Random;

import static java.lang.Math.pow;
import static java.lang.Math.round;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-15 21:34
 **/
public class ChunkManager_Original {
    //TODO GrassCarp edited
    private static final int CHUNK_LEN = 16;
    private static final int COUNT = 16;
    private static final int[][][] STONE = new int[CHUNK_LEN * COUNT][CHUNK_LEN * COUNT][6];
    private static final int[][][] BEDROCK = new int[CHUNK_LEN * COUNT][CHUNK_LEN * COUNT][2];
    private static final long seed = 114514;//welcome to abyssal dark the smelly land

    static {
        final Random rand = new Random();
        for(int i = 0; i < COUNT; ++ i){
            for(int j = 0; j < COUNT; ++ j){
                for (int l = 0;l < 6; ++ l) {//layer
                    genStone(rand, i, j, seed, l);
                }
                genTerrain(rand, i ,j ,seed);
                genBedRock(rand, i, j, seed, 0);
                genBedRock(rand, i ,j ,seed, 1);
            }
        }
    }

    public static int getChunkHeight(int chunkX, int chunkZ, int offsetX, int offsetZ, int layer){//each level has ceil&floor; three levels have 6 layers in total
        final int x = (chunkX % COUNT + COUNT) % COUNT * CHUNK_LEN + offsetX;
        final int z = (chunkZ % COUNT + COUNT) % COUNT * CHUNK_LEN + offsetZ;
        return STONE[x][z][layer];
    }

    public static int getBedRock(int chunkX, int chunkZ, int offsetX, int offsetZ, int layer){
        final int x = (chunkX % COUNT + COUNT) % COUNT * CHUNK_LEN + offsetX;
        final int z = (chunkZ % COUNT + COUNT) % COUNT * CHUNK_LEN + offsetZ;
        return BEDROCK[x][z][layer];
    }
    public static void genStone(Random random, int chunkX, int chunkZ, long seed, int layer){
        //basic noise below
        int[][] arrowx = new int[4][4];
        int[][] arrowz = new int[4][4];
        for (int i = 0; i < 4; ++ i){
            for (int j = 0; j < 4; ++ j){
                random.setSeed(seed-(chunkX+i)%COUNT*2-(chunkZ+j)%COUNT+layer);
                arrowx[i][j] = random.nextInt(0,round(CHUNK_LEN/2));
                if (random.nextInt(2) == 1){
                    arrowx[i][j] = -arrowx[i][j];
                }
                random.setSeed(seed-(chunkX+i)%COUNT-(chunkZ+j)%COUNT*3+layer);
                arrowz[i][j] = random.nextInt(0,round(CHUNK_LEN/2));
                if (random.nextInt(2) == 1){
                    arrowz[i][j] = -arrowz[i][j];
                }
            }
        }//chunk outline arrow defined above
        for(int i = 0; i < CHUNK_LEN; ++ i){
            for(int j = 0; j < CHUNK_LEN; ++ j){
                final int realX = chunkX * CHUNK_LEN + i;
                final int realZ = chunkZ * CHUNK_LEN + j;
                int height = 0;
                int tmp;
                for (int k = 0; k < 4; ++ k){
                    for (int l = 0; l < 4; ++l) {
                        tmp = (int) (20 - pow(round(pow(i-(CHUNK_LEN)*(k-1)-arrowx[k][l],2) + pow(j-(CHUNK_LEN)*(l-1)-arrowz[k][l],2)),0.5));
                        if (tmp > 0){
                            height += tmp;
                        }
                    }
                }
                height = (int) round(pow(height+5,0.5)+8);
                if (height < 0){
                    height = 0;
                }
                STONE[realX][realZ][layer] = height;
//                RESULT[realX][realZ] = 1;
            }
        }
    }
    public static void genTerrain(Random random, int chunkX, int chunkZ, long seed){
        //define columns & highlands & holes below
        int[][][] arrowx = new int[4][4][3];
        int[][][] arrowz = new int[4][4][3];
        for (int i = 0; i < 4; ++ i) {
            for (int j = 0; j < 4; ++j) {
                for (int l = 0; l < 4; ++l) {
                    random.setSeed(seed - (chunkX + i) % COUNT * 2 - (chunkZ + j) % COUNT + l);
                    arrowx[i][j][l] = random.nextInt(0, round(CHUNK_LEN / 2));
                    if (random.nextInt(2) == 1) {
                        arrowx[i][j][l] = -arrowx[i][j][l];
                    }
                    random.setSeed(seed - (chunkX + i) % COUNT - (chunkZ + j) % COUNT * 3 + l);
                    arrowz[i][j][l] = random.nextInt(0, round(CHUNK_LEN / 2));
                    if (random.nextInt(2) == 1) {
                        arrowz[i][j][l] = -arrowz[i][j][l];
                    }
                }
            }
        }
    }

    public static void genBedRock(Random random, int chunkX, int chunkZ, long seed, int layer){
        for (int i = 0;i < CHUNK_LEN; ++ i){
            for (int j = 0; j < CHUNK_LEN; ++ j){
                random.setSeed(seed-(chunkX+i*6)-(chunkZ+j*8));
                final int realX = chunkX * CHUNK_LEN + i;
                final int realZ = chunkZ * CHUNK_LEN + j;
                if (random.nextInt(3) == 1){
                    BEDROCK[realX][realZ][layer] = 1;
                }else{
                    BEDROCK[realX][realZ][layer] = 0;
                }
                if (random.nextInt(6) == 1){
                    BEDROCK[realX][realZ][layer] += 2;
                }
            }
        }
    }
}
