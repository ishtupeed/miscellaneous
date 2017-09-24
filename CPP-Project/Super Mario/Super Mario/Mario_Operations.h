#pragma once

#include "Load_From_Files.h"
#include "Map_Details.h"

Load_From_Files load;
Map_Details mHandling;

class Mario_Operations
{
public:
	void reset_Mario()
	{
        Save_PlayerX=playerX;
        Save_PlayerY=playerY;
        time=400;
        lifes=3;
        score=0;
        Is_Mario_Alive=true;
	}

	void Kill_Mario()
	{
        if(Is_Mario_Alive==true && dying==false && takingDamage == false)
        {
            dying=true;
            lifes--;
            Is_Mario_Alive=false;
            dbPlaySound(Sdeath);
            deathFlash=dbRnd(1);
            if(dbSoundPlaying(SmainTheme))
                dbStopSound(SmainTheme);
        }
	}

	void Take_Damage()
	{
	    if(currentMario==FireMario)
        {
            takingDamage=true;
            currentMario=normalMario;
            dbPlaySound(SPipeAndDamage);
        }
        else
            Kill_Mario();
	}

	void Spawn_Mario(int initial_X, int initial_Y)
	{
        playerX=initial_X;
        playerY=initial_Y;
        currentMario=normalMario;
        dbSprite(mario, playerX, playerY, mario);
		dbSetSprite(mario, 0, 1);
		dbSetSpritePriority(mario, 1);
	}

	void Respawn_Mario()
	{
	    misc.Clear_Stage();
	    load.Load_Current_Level(level);
	    currentMario=normalMario;
	    dying=false;
	    Is_Mario_Alive=true;
	    damageFlashes=0;
	    playerX=Save_PlayerX;
	    playerY=Save_PlayerY;
	    mapX=checkPointX;
	    mapY=0;
	    time=400;
	}

	void Game_Timer()
	{
	    if(levelOver==0)
        {
            if(dbTimer()-gameTimer >1000)
            {
                gameTimer=dbTimer();
                if(time>0)
                    time--;
                else
                    Kill_Mario();
                if(time==200)
                    dbPlaySound(StimeWarning);
            }
        }
	}

	void Finish()
	{
	    if(levelOver)
            if(dbSoundPlaying(SmainTheme))
				dbStopSound(SmainTheme);
        if(levelOver==1)
            if(touchingGround)
                levelOver=2;
        if(levelOver==2)
        {
            dbPlaySound(SlevelClear);
			levelOver=3;
        }
        if(levelOver==3)
		{
			walkingRight=true;
			playerX=playerX+(speedx/2);
			EndGame=true;
			dbSetTextSize(30);
		}
		if(EndGame)
		{
			dbText(screenCenterX/2, screenCenterY, "Game is over,\n Press any key to exit");
			if(dbScanCode()>0)
				exit(0);
		}
	}

	void Coin_Pick_Up()
	{
		coins++;
		dbPlaySound(ScoinPickup);
		score+=200;
		if(coins>=100)
		{
			coins=0;
			lifes++;
			dbPlaySound(SnewLife);
		}
	}

    void Mario_Animation()
    {
        if(dbSpriteExist(mario))
        {
            if(marioSpawned)
            {
                if(Is_Mario_Alive==true)
                {
                    if(misc.No_Key_Pressed() && dbTimer()-walkingTimer>120 && jumping==false && falling==false)
                    {
                        walkingTimer=dbTimer();
						if(currentMario==normalMario)
							dbSprite(mario, playerX-mapX, playerY-mapY, mario);
						else if(currentMario==FireMario)
							dbSprite(mario, playerX-mapX, playerY-mapY, FireMarioStill);
                    }
                    else if(jumping==true || falling==true)
                    {
                        if(currentMario==normalMario)
                            dbSprite(mario, playerX-mapX, playerY-mapY, marioJump);
                        else if(currentMario==FireMario)
                            dbSprite(mario, playerX-mapX, playerY-mapY, FireMarioJump);
                    }
                    if(walkingRight==true)
                    {
                        if(dbSpriteMirrored(mario))
                            dbMirrorSprite(mario);
                        if(dbTimer()-walkingTimer>100)
                        {
                            walkingTimer=dbTimer();
                            if(currentMario==normalMario && jumping==false && falling==false)
                            {
                                if(dbSpriteImage(mario)==mario)
                                    dbSprite(mario, playerX-mapX, playerY-mapY, marioWalk);
                                else
                                    dbSprite(mario, playerX-mapX, playerY-mapY, mario);
                            }
                            else if(currentMario==FireMario && jumping==false && falling==false)
							{
								if(dbSpriteImage(mario)==FireMarioStill)
									dbSprite(mario, playerX-mapX, playerY-mapY, FireMarioWalk);
								else
									dbSprite(mario, playerX-mapX, playerY-mapY, FireMarioStill);
                            }
                        }
                    }
                    if(walkingLeft==true)
                    {
                        if(dbSpriteMirrored(mario)==0)
                            dbMirrorSprite(mario);
                        if(dbTimer()-walkingTimer>100)
                        {
                            walkingTimer=dbTimer();
                            if(currentMario==normalMario && jumping==false && falling==false)
                            {
                                if(dbSpriteImage(mario)==mario)
                                    dbSprite(mario, playerX-mapX, playerY-mapY, marioWalk);
                                else
                                    dbSprite(mario, playerX-mapX, playerY-mapY, mario);
                            }
                            else if(currentMario==FireMario && jumping==false && falling==false)
							{
								if(dbSpriteImage(mario)==FireMarioStill)
									dbSprite(mario, playerX-mapX, playerY-mapY, FireMarioWalk);
								else
                                dbSprite(mario, playerX-mapX, playerY-mapY, FireMarioStill);
                            }
                        }
                    }
                }
            }
        }
    }

    void Damage_Flash()
    {
        if(dying==false && Is_Mario_Alive==true && takingDamage==true)
        {
            if(dbTimer()-flashTimer>40)
            {
                damageFlashes++;
                flashTimer=dbTimer();
                if(deathFlash==1)
                {
                    dbHideSprite(mario);
                    deathFlash=0;
                }
                else
                {
                    dbShowSprite(mario);
                    deathFlash=1;
                }
                if(damageFlashes==25)
                {
                    damageFlashes=0;
                    takingDamage=false;
                    dbShowSprite(mario);
                }
            }
        }
    }

    void Death_Flash()
    {
        if(dying==true && Is_Mario_Alive==false)
        {
           if(dbTimer()-flashTimer>40)
           {
               playerY=playerY-3.0;
               damageFlashes++;
               flashTimer=dbTimer();
               if(deathFlash==1)
               {
                   dbHideSprite(mario);
                   deathFlash=0;
               }
               else
               {
                   dbShowSprite(mario);
                   deathFlash=1;
               }
               if(damageFlashes==50)
               {
                    dbShowSprite(mario);
                    if(lifes>0)
                        Respawn_Mario();
                    else
                    {
                        dying=false;
                        EndGame=true;
                    }

               }
           }
           dbSprite( mario, playerX-mapX, playerY-mapY, mario );
        }
    }

    void Item_Pick_Up(int arrX, int arrY, int typeofCollision)
    {
        if(map[arrX][arrY]==C)
        {
            map[arrX][arrY]=a;
            Coin_Pick_Up();
        }
        else if(map[arrX][arrY]==f+11)
        {
            if(currentMario==normalMario)
                currentMario=FireMario;
            score+=1000;
            dbPlaySound(SFlowerPickup);
            mHandling.Start_Animation(score1000_1, IMAGE, arrX, arrY-1, 10, a, 80);
            map[arrX][arrY]=a;
        }
        else if(map[arrX][arrY]==b)
        {
            if(typeofCollision==bottom)
            {
                dbPlaySound(Sbrick);
                mHandling.Start_Animation(dust_1, IMAGE, arrX, arrY, 8, a, 80);
            }
        }
        else if(map[arrX][arrY]==L)
        {
            if(typeofCollision==bottom)
            {
                dbPlaySound(SFlowerAppear);
                mHandling.Start_Animation(_1up_1, IMAGE, arrX, arrY-1, 16, gM, 100);
                map[arrX][arrY]=e;
            }
        }
        else if(map[arrX][arrY]==e || map[arrX][arrY]==k)
        {
            if(typeofCollision==bottom)
                dbPlaySound(Sbump);
        }
        else if(map[arrX][arrY]==qC)
        {
            if(typeofCollision==bottom)
            {
                mHandling.Start_Animation(C, IMAGE, arrX, arrY-1, 6, a, 120);
                map[arrX][arrY]=e;
                Coin_Pick_Up();
            }
        }
        else if(map[arrX][arrY]==qF)
        {
            if(typeofCollision==bottom)
            {
                map[arrX][arrY]=e;
                dbPlaySound(SFlowerAppear);
                mHandling.Start_Animation(f, IMAGE, arrX, arrY-1, 11, f+11, 100);
            }
        }
        else if(map[arrX][arrY]==d)
            Kill_Mario();
        else if(map[arrX][arrY]==fB || map[arrX][arrY]==fT)
        {
            if(CheckPointReached==false)
            {
                CheckPointReached=true;
                checkPointX=mapX;
                checkPointY=mapY;
                Save_PlayerX=playerX;
				Save_PlayerY=playerY;

				score+=500;
				dbPlaySound(ScheckPoint);
				mHandling.Start_Animation(check_1, SPRITE, dbSpriteX(mario), dbSpriteY(mario)-100, 8, a, 150);
            }
        }
        else if(map[arrX][arrY]==gM)
        {
            map[arrX][arrY]=a;
            lifes++;
            dbPlaySound(Spowerup);
            mHandling.Start_Animation(_1upText_1, IMAGE, arrX, arrY-1, 6, a, 100);
        }
        else if(map[arrX][arrY] == fl || map[arrX][arrY] == P1 ||
			map[arrX][arrY] == P2 || map[arrX][arrY] == P3 ||
			map[arrX][arrY] == P4 || map[arrX][arrY] == P5 ||
			map[arrX][arrY] == P5l || map[arrX][arrY] == P6)
			{
                if(levelOver==0)
                {
                    stopMovement=true;
                    dbPlaySound(SFinish);
                    levelOver=1;
                }
			}
    }

};
