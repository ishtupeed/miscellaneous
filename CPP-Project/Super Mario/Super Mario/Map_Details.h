#pragma once

class Map_Details
{
public:

    void Start_Animation(int score_to_display, int scoreType, int x, int y, int topFrame, int solidFrame, int delay)
    {
        if(scoreType == IMAGE) map[x][y] = SA;

        int i = misc.Find_Free_Animation();
        if(!anims[i].busy)
        {
            if(scoreType == IMAGE) anims[i].id = map[x][y];
            anims[i].animDelay = delay;
            anims[i].SetImage = score_to_display;
            anims[i].HighestFrame = topFrame;
            anims[i].x = x;
            anims[i].y = y;
            anims[i].busy = true;
            anims[i].timer = dbTimer();
            anims[i].frames = 0;
            anims[i].typeOf = scoreType;
            anims[i].OriginalMapX = mapX;
            anims[i].FinalFrame = solidFrame;
        }
    }

    void Level_Related_Animations()
    {
        for(int i = 0; i < maxAnimations; i++)
        {
            if(anims[i].busy == true)
            {
                if(dbTimer() - anims[i].timer > anims[i].animDelay)
                {
                    anims[i].timer = dbTimer();
                    anims[i].frames++;

                    if(anims[i].frames == anims[i].HighestFrame)
                    {
                        anims[i].busy = false;
                    }
                }
                if(anims[i].typeOf == SPRITE)
                {
                    dbPasteImage( anims[i].SetImage + anims[i].frames, anims[i].x + (anims[i].OriginalMapX - mapX), anims[i].y);
                }
            }
        }
    }

};
