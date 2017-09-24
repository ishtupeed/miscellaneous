#pragma once

Mario_Operations myEvents;

class Enemies
{
public:
    void Spawn_Enemy(int initial_X, int initial_Y)
    {
        walkers[currentWalker].id=misc.Find_Free_Sprite();
        dbSprite(walkers[currentWalker].id, initial_X, initial_Y, G);
        walkers[currentWalker].x=initial_X+mapX;
        walkers[currentWalker].y=initial_Y;
        walkers[currentWalker].ASleep=true;
		currentWalker++;
    }
    void Activate_Enemies()
    {
        int distance;
        for(int i=0;i<maxWalkers;i++)
        {
            if(dbSpriteExist(walkers[i].id))
            {
                if(walkers[i].ASleep)
                {
                    distance=misc.Distance_2D(dbSpriteX(mario), dbSpriteY(mario), dbSpriteX(walkers[i].id), dbSpriteY(walkers[i].id));
                    if(distance<1500)
                    {
                        walkers[i].ASleep=false;
					   walkers[i].isAlive=true;
					   walkers[i].walking=true;
					   walkers[i].touchingGround=false;
					   walkers[i].isDying=false;
					   walkers[i].typeOf=GOOMBA;
					   walkers[i].walkingDirection=LEFT;
                    }
                }
            }
        }
    }

    void Collide_With_Piranha(int arrX, int arrY, int typeOfCollision)
    {
        if(map[arrX][arrY]==EPl || map[arrX][arrY]==EPr)
        {
            for(int i=0;i<maxPiranhas;i++)
            {
                if(piranhaPlants[i].ReferenceJ == arrX && piranhaPlants[i].ReferenceI == arrY)
                {
                    if(piranhaPlants[i].plantAnim>0 && piranhaPlants[i].plantAnim<28)
                        myEvents.Take_Damage();
                    break;
                }
            }
        }
    }

    void Keep_Piranha_Down()
    {
        for(int i=0;i<maxPiranhas;i++)
        {
            if(piranhaPlants[i].isAlive)
            {
                if(piranhaPlants[i].typeOf==EPl)
				{
					piranhaPlants[i].distance=misc.Distance_2D(dbSpriteX(mario), dbSpriteY(mario), dbSpriteX(piranhaPlants[i].id), dbSpriteY(piranhaPlants[i].id));
					if(piranhaPlants[i].distance<225)
						piranhaPlants[i].tooClose=true;
					else
                        piranhaPlants[i].tooClose=false;
				}
				else if(piranhaPlants[i].typeOf==EPr)
				{
					piranhaPlants[i].distance=misc.Distance_2D(dbSpriteX(mario), dbSpriteY(mario), dbSpriteX(piranhaPlants[i].id), dbSpriteY(piranhaPlants[i].id));
					if(piranhaPlants[i].distance < 225)
						piranhaPlants[i].tooClose = true;
					else
                        piranhaPlants[i].tooClose = false;
				}
            }
        }
    }
};
