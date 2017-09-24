#pragma once

#include "Extras.h"
#include "Mario_Operations.h"
#include "Enemies.h"

Enemies enemy;

class Map_Loading
{
public:

    void playSounds()
    {
        if(Is_Mario_Alive)
        {
            if(dbSoundPlaying(ScheckPoint))
                dbSetSoundVolume(SmainTheme, 80);
            else
                dbSetSoundVolume(SmainTheme, 100);
            if(dbSoundPlaying(SmainTheme)==0 && levelOver==0)
                dbPlaySound(SmainTheme);
        }
    }
    void displayInformation()
    {
        dbSetTextSize(30);

        char points[100];
        sprintf_s(points, 200, "Score \n %d", score);
        dbText(50, 50, points);

        char coinNum[100];
        sprintf_s(coinNum, 200, "Coins \n %d", coins);
        dbText(150, 50, coinNum);

        char levelNum[100];
        sprintf_s(levelNum, 200, "Level \n %d", level);
        dbText(300, 50, levelNum);

        char life[100];
        sprintf_s(life, 200, "Life \n %d", lifes);
        dbText(400, 50, life);

        char timeLeft[100];
        sprintf_s(timeLeft, 200, "Time \n %d", time);
        dbText(500, 50, timeLeft);
    }

    void displayLevel()
    {
        int tilenumx=mapX/numoftilesx;
        int tilenumy=mapY/numoftilesy;

        int tempx=mapX-(tilenumx*tilesizex);
        int tempy=mapY-(tilenumy*tilesizey);

        for(int y=0; y<13; y++)
        {
            for(int x=0; x<16; x++)
                pasteTiles(x, tilenumx, y, tilenumy, tempx, tempy);
        }
    }

    void pasteTiles(int x, int tilenumx, int y, int tilenumy, int tempx, int tempy)
    {
        if(map[x+tilenumx][y+tilenumy]==qC || map[x+tilenumx][y+tilenumy]==qF)
        {
            if(dbTimer()-questionBoxTimer>75)
            {
                questionBoxTimer=dbTimer();
                qBoxAnim++;
                if(qBoxAnim==26)
                    qBoxAnim=0;
            }
            dbPasteImage(q+qBoxAnim, (x*tilesizex)-tempx, (y*tilesizey)-tempy);
        }
        else if(map[x+tilenumx][y+tilenumy]==C)
        {
            if(dbTimer()-coinTimer>100)
            {
                coinTimer=dbTimer();
                coinAnim++;
                if(coinAnim==6)
                    coinAnim=0;
            }
            dbPasteImage(map[x+tilenumx][y+tilenumy]+coinAnim, (x*tilesizex)-tempx, (y*tilesizey)-tempy);
        }
        else if(map[x+tilenumx][y+tilenumy]==EPl)
        {
            for(int i=0; i<maxPiranhas; i++)
            {
                if(piranhaPlants[i].ReferenceJ==x+tilenumx && piranhaPlants[i].ReferenceI==y+tilenumy)
                {
                    if(dbSpriteVisible(piranhaPlants[i].id)==0)
                        dbShowSprite(piranhaPlants[i].id);
                    dbPasteImage(map[x+tilenumx][y+tilenumy]+piranhaPlants[i+1].plantAnim, (x*tilesizex)-tempx, (y*tilesizey)-tempy);
                    dbSprite(piranhaPlants[i].id, (x*tilesizex)-tempx, (y*tilesizey)-tempy, map[x+tilenumx][y+tilenumy]+piranhaPlants[i+1].plantAnim);
                    break;
                }
            }
        }
        else if(map[x+tilenumx][y+tilenumy]==EPr)
        {
            for(int i=0; i<maxPiranhas; i++)
            {
                if(piranhaPlants[i].ReferenceJ==x+tilenumx && piranhaPlants[i].ReferenceI==y+tilenumy)
                {
                    if(dbSpriteVisible(piranhaPlants[i].id)==0)
                        dbShowSprite(piranhaPlants[i].id);
                    if(dbTimer()-piranhaPlants[i].plantTimer>100 && dbTimer()-piranhaPlants[i].plantLoopTimer>1400)
                    {
                        piranhaPlants[i].plantTimer=dbTimer();
                        if(piranhaPlants[i].tooClose==true && piranhaPlants[i].plantAnim==0)
                            piranhaPlants[i].plantLoopTimer = dbTimer();
                        else if(piranhaPlants[i-1].tooClose==true && piranhaPlants[i].plantAnim==0)
                            piranhaPlants[i].plantLoopTimer=dbTimer();
                        else
                        {
                            piranhaPlants[i].plantAnim++;
                            if(piranhaPlants[i].plantAnim==5 || piranhaPlants[i].plantAnim==10 || piranhaPlants[i].plantAnim==15 || piranhaPlants[i].plantAnim==25)
                                if(piranhaPlants[i].distance<500)
                                    dbPlaySound(SplantCrunch);
                        }
                        if(piranhaPlants[i].plantAnim==30)
                        {
                            piranhaPlants[i].plantAnim=0;
                            piranhaPlants[i].plantLoopTimer=dbTimer();
                        }
                    }
                    dbPasteImage(map[x+tilenumx][y+tilenumy]+piranhaPlants[i].plantAnim, (x*tilesizex)-tempx, (y*tilesizey)-tempy);
                    dbSprite(piranhaPlants[i].id, (x*tilesizex)-tempx, (y*tilesizey)-tempy, map[x+tilenumx][y+tilenumy]+piranhaPlants[i].plantAnim);
                    break;
                }
            }
        }
        else if(map[x+tilenumx][y+tilenumy]==f)
            dbPasteImage( map[x + tilenumx][y + tilenumy], ( x * tilesizex ) - tempx, ( y * tilesizey ) - tempy );
        else if(map[x+tilenumx][y+tilenumy]==SA)
        {
            bool foundRightElement=false;
            for(int i=0; i<maxAnimations && foundRightElement==false; i++)
            {
                if(anims[i].busy==true)
                {
                    if(anims[i].typeOf==IMAGE)
                    {
                        if(anims[i].id==map[x+tilenumx][y+tilenumy] && anims[i].x==x+tilenumx && anims[i].y==y+tilenumy)
                        {
                            foundRightElement=true;
                            if(dbTimer()-anims[i].timer>80)
                            {
                                anims[i].timer=dbTimer();
                                anims[i].frames++;
                                if(anims[i].frames==anims[i].HighestFrame)
                                {
                                    anims[i].busy=false;
                                    map[x+tilenumx][y+tilenumy]=anims[i].FinalFrame;
                                }
                            }
                            dbPasteImage(anims[i].SetImage+anims[i].frames, (x*tilesizex)-tempx, (y*tilesizey)-tempy);
                        }
                    }
                }
            }
        }
        else if(map[x+tilenumx][y+tilenumy]==G)
        {
            map[x+tilenumx][y+tilenumy]=a;
            enemy.Spawn_Enemy(((x*tilesizex)-tempx), (y*tilesizey)-tempy);
        }
        else if(map[x+tilenumx][y+tilenumy]==M)
        {
            map[x+tilenumx][y+tilenumy]=a;
            if(marioSpawned==false)
            {
                marioSpawned=true;
                myEvents.Spawn_Mario(((x*tilesizex)-tempx), (y*tilesizey)-tempy);
            }
        }
        else if(map[x+tilenumx][y+tilenumy]==L)
            dbPasteImage(map[x+tilenumx][y+tilenumy], (x*tilesizex)-tempx, (y*tilesizey)-tempy);
        else if(map[x+tilenumx][y+tilenumy]==gM)
            dbPasteImage(_1up_1+16, (x*tilesizex)-tempx, (y*tilesizey)-tempy);
        else
            dbPasteImage(map[x+tilenumx][y+tilenumy], (x*tilesizex)-tempx, (y*tilesizey)-tempy);
    }
};
