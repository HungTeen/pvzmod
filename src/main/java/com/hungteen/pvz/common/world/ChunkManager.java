package com.hungteen.pvz.common.world;

import java.util.Random;

import static java.lang.Math.*;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-15 21:34
 **/
public class ChunkManager {
    //TODO GrassCarp edited
    private static final int CHUNK_LEN = 16;//length of a chunk
    private static final int COUNT = 16;//number of chunks
    private static final float[][][] STONE = new float[CHUNK_LEN * COUNT][CHUNK_LEN * COUNT][6];
    private static final float[][][] TERRAIN = new float[CHUNK_LEN * COUNT][CHUNK_LEN * COUNT][6];
    private static final int[][][] BEDROCK = new int[CHUNK_LEN * COUNT][CHUNK_LEN * COUNT][2];
    private static final long seed = 114514;//zhege daima zheme chou zhende yong de xiaqu ma?

    static {
        final Random rand = new Random();
        //build start below
        for (int i = 0; i < COUNT; ++i) {
            for (int j = 0; j < COUNT; ++j) {//i&j stand for chunk relative position
                //Bedrock layer
                genBedRock(rand, i, j, seed, 0);
                genBedRock(rand, i, j, seed, 1);
                //build stone noise plain
                for (int l = 0; l < 6; ++l) {
                    genPlain(rand, i, j, seed, l);
                }
            }
        }
    }

    public static void Build(int chunkX, int chunkZ,int[] floors) {
        final Random rand = new Random();
        int[] chunkpos = new int[2];
        chunkpos[0] = chunkX;
        chunkpos[1] = chunkZ;
        //build stone terrain
        genTerrain(rand, seed, chunkpos, floors);
    }

    //builds below
    public static void genBedRock(Random random, int chunkX, int chunkZ, long seed, int layer) {
        for (int i = 0; i < CHUNK_LEN; ++i) {
            for (int j = 0; j < CHUNK_LEN; ++j) {
                random.setSeed(seed - (chunkX + i * 6) - (chunkZ + j * 8));
                final int realX = chunkX * CHUNK_LEN + i;
                final int realZ = chunkZ * CHUNK_LEN + j;
                if (random.nextInt(3) == 1) {
                    BEDROCK[realX][realZ][layer] = 1;
                } else {
                    BEDROCK[realX][realZ][layer] = 0;
                }
                if (random.nextInt(6) == 1) {
                    BEDROCK[realX][realZ][layer] += 2;
                }
            }
        }
    }

    public static void genPlain(Random random, int chunkX, int chunkZ, long seed, int layer) {
        //chunk outline arrow defined above
        int[][] arrowx = new int[4][4];
        int[][] arrowz = new int[4][4];
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                random.setSeed(seed - (chunkX + i) % COUNT * 2 - (chunkZ + j) % COUNT + layer);
                arrowx[i][j] = random.nextInt(0, CHUNK_LEN / 2);
                arrowx[i][j] = (random.nextInt(2) == 1 ? 1 : -1) * arrowx[i][j];

                random.setSeed(seed - (chunkX + i) % COUNT - (chunkZ + j) % COUNT * 3 + layer);
                arrowz[i][j] = random.nextInt(0, CHUNK_LEN / 2);
                arrowz[i][j] = (random.nextInt(2) == 1 ? 1 : -1) * arrowz[i][j];
            }
        }
        for (int i = 0; i < CHUNK_LEN; ++i) {
            for (int j = 0; j < CHUNK_LEN; ++j) {
                final int realX = chunkX * CHUNK_LEN + i;
                final int realZ = chunkZ * CHUNK_LEN + j;
                double height = 0;
                double tmp;
                for (int k = 0; k < 4; ++k) {
                    for (int l = 0; l < 4; ++l) {
                        tmp = (20 - pow(round(pow(i - (CHUNK_LEN) * (k - 1) - arrowx[k][l], 2) + pow(j - (CHUNK_LEN) * (l - 1) - arrowz[k][l], 2)), 0.5));
                        if (tmp > 0) {
                            height += tmp;
                        }
                    }
                }
                height = round(pow(height, 0.5) + 8);
                if (height < 0) {
                    height = 0;
                }
                STONE[realX][realZ][layer] =(float) height;
            }
        }
    }

    public static void genTerrain(Random random, long seed, int[] chunkpos, int[] floors) {
        int chunkX = (chunkpos[0] % COUNT + COUNT) % COUNT;
        int chunkZ = (chunkpos[1] % COUNT + COUNT) % COUNT;
        int arrowx[][][] = new int[4][4][3];
        int arrowz[][][] = new int[4][4][3];
        float Terrain[][][] = new float[CHUNK_LEN][CHUNK_LEN][3];
        double tmp;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int l = 0; l < 3; l++) {
                    random.setSeed((seed + chunkpos[0] - chunkX) / COUNT + i + (chunkpos[1] + j * COUNT - chunkZ) % 50 * 8 + l);
                    arrowx[i][j][l] = random.nextInt(COUNT * CHUNK_LEN / 2) * (random.nextInt(2) == 1 ? 1 : -1);
                    random.setSeed((seed + chunkpos[1] - chunkZ) / COUNT + j + (chunkpos[0] + i * COUNT - chunkX) % 50 * 7 + l);
                    arrowz[i][j][l] = random.nextInt(COUNT * CHUNK_LEN / 2) * (random.nextInt(2) == 1 ? 1 : -1);
                }
            }
        }
        for (int h = 0; h < 6; h++) {
            for (int i = 0; i < CHUNK_LEN; i++) {
                for (int j = 0; j < CHUNK_LEN; j++) {
                    TERRAIN[chunkX*CHUNK_LEN+i][chunkZ*CHUNK_LEN+j][h] = 0;
                }
            }
            for (int i = 0; i < CHUNK_LEN; i++) {
                for (int j = 0; j < CHUNK_LEN; j++) {
                    Terrain[i][j][h] = 0;
                    for (int k = 0; k < 4; k++) {
                        for (int l = 0; l < 4; l++) {
                            tmp = (1.5 * CHUNK_LEN * COUNT - pow(pow(i + chunkX * CHUNK_LEN - (k - 1) * COUNT * CHUNK_LEN - arrowx[k][l][h], 2) + pow(j + chunkZ * CHUNK_LEN - (l - 1) * COUNT * CHUNK_LEN - arrowz[k][l][h], 2), 0.5));
                            if (tmp >= 0) {
                                Terrain[i][j][h] += tmp;
                            }
                        }
                    }
                    //build columns
                    if (Terrain[i][j][h] > 1100) {
                        TERRAIN[chunkX * CHUNK_LEN + i][chunkZ * CHUNK_LEN + j][h * 2] += pow(pow((Terrain[i][j][h] - 1100) / 10, 3)/10,2)*10;
                        if (TERRAIN[chunkX * CHUNK_LEN + i][chunkZ * CHUNK_LEN + j][h * 2] > floors[h+1]-floors[h]-STONE[chunkX * CHUNK_LEN + i][chunkZ * CHUNK_LEN + j][h * 2]){
                            TERRAIN[chunkX * CHUNK_LEN + i][chunkZ * CHUNK_LEN + j][h * 2] = floors[h+1]-floors[h]-STONE[chunkX * CHUNK_LEN + i][chunkZ * CHUNK_LEN + j][h * 2];
                        }
                        TERRAIN[chunkX * CHUNK_LEN + i][chunkZ * CHUNK_LEN + j][h * 2 + 1] += pow(pow((Terrain[i][j][h] - 1100) / 15, 3)/10,2)*10;
                        if (TERRAIN[chunkX * CHUNK_LEN + i][chunkZ * CHUNK_LEN + j][h * 2 + 1] > floors[h+1]-floors[h]-STONE[chunkX * CHUNK_LEN + i][chunkZ * CHUNK_LEN + j][h * 2 + 1]){
                            TERRAIN[chunkX * CHUNK_LEN + i][chunkZ * CHUNK_LEN + j][h * 2 + 1] = floors[h+1]-floors[h]-STONE[chunkX * CHUNK_LEN + i][chunkZ * CHUNK_LEN + j][h * 2 + 1];
                        }
                    }
                    //build highlands
                    if (Terrain[i][j][h] > 900) {
                        TERRAIN[chunkX*CHUNK_LEN+i][chunkZ*CHUNK_LEN+j][h * 2] += Terrain[i][j][h] < 902 ? sin(sin(Terrain[i][j][h]-900)*3.14/2)*6 : 10;
                    }
                    //build holes
                    else if (h > 0 && Terrain[i][j][h] < 800 && Terrain[i][j][h - 1] < 800) {
                        TERRAIN[chunkX*CHUNK_LEN+i][chunkZ*CHUNK_LEN+j][h * 2] -= max(Terrain[i][j][h] , Terrain[i][j][h - 1]) > 798 ? sin(sin(max(Terrain[i][j][h] , Terrain[i][j][h - 1]) - 800)*3.14/2)*6 : 100;
                        TERRAIN[chunkX*CHUNK_LEN+i][chunkZ*CHUNK_LEN+j][h * 2 - 1] -= max(Terrain[i][j][h] , Terrain[i][j][h - 1]) > 798 ? sin(sin(max(Terrain[i][j][h] , Terrain[i][j][h - 1]) - 800)*3.14/2)*6 : 100;
                    }
//                    TERRAIN[chunkX * CHUNK_LEN + i][chunkZ * CHUNK_LEN + j][h * 2] = (int) ((Terrain[i][j][h]) / 15);
//                    STONE[chunkX * CHUNK_LEN + i][chunkZ * CHUNK_LEN + j][h * 2] = 0;
                }
            }
        }
    }

    //Outputs below
    public static float getTerrain(int chunkX, int chunkZ, int offsetX, int offsetZ, int layer) {//each level has ceil&floor; three levels have 6 layers in total
        final int x = (chunkX % COUNT + COUNT) % COUNT * CHUNK_LEN + offsetX;
        final int z = (chunkZ % COUNT + COUNT) % COUNT * CHUNK_LEN + offsetZ;
        return STONE[x][z][layer] + TERRAIN[x][z][layer];
    }

    public static int getBedRock(int chunkX, int chunkZ, int offsetX, int offsetZ, int layer) {
        final int x = (chunkX % COUNT + COUNT) % COUNT * CHUNK_LEN + offsetX;
        final int z = (chunkZ % COUNT + COUNT) % COUNT * CHUNK_LEN + offsetZ;
        return BEDROCK[x][z][layer];
    }
}
