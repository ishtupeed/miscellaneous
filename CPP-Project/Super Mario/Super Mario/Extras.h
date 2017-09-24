#pragma once
#include <cmath>

class Extras
{
public:

    void Extras::Clear_Stage()
    {
        for(int i = 0; i < maxWalkers; i++)
		{
			if(dbSpriteExist(walkers[i].id))
			{
				dbDeleteSprite(walkers[i].id);
				walkers[i].isAlive = false;
				walkers[i].walking = false;
				walkers[i].touchingGround = false;
				walkers[i].x = 0;
				walkers[i].y = 0;
				walkers[i].typeOf = 0;
				walkers[i].isDying = false;
				walkers[i].id = 0;
			}
		}
		currentWalker = 1;
		for(int i = 0; i < maxPiranhas; i ++)
		{
			if(dbSpriteExist(piranhaPlants[i].id))
			{
				dbDeleteSprite(piranhaPlants[i].id);
				piranhaPlants[i].isAlive = false;
				piranhaPlants[i].tooClose = false;
				piranhaPlants[i].ReferenceI = 0;
				piranhaPlants[i].ReferenceJ = 0;
				piranhaPlants[i].plantAnim = 0;
				piranhaPlants[i].id = 0;
			}
		}
    }


    bool No_Key_Pressed()
    {
        if(!dbUpKey() && !dbLeftKey() && !dbRightKey() && !dbSpaceKey())
            return true;

        return false;
    }

    int Find_Free_Sprite()
    {
        int i = 10;
        while(dbSpriteExist(i)) {
                i++;
        }
        return i;
    }

    int Find_Free_Animation()
    {
        for(int i=0;i<maxAnimations;i++){
                if(!anims[i].busy){
                        return i;
                }
        }
        return 0;
    }
    float Extras::Distance_2D(int x1, int y1, int x2, int y2)
	{
		return sqrt(pow((double)(x2-x1),2.0) + pow((double)(y2-y1),2.0));
	}

};
