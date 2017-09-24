#pragma once

#include "Enemies.h"
#include "Enemy_Physics_Movement.h"
#include "Map_Details.h"

Enemies enemies;
Enemy_Physics_Movement enPhy;
Map_Loading mapping;

class Physics_Movement
{
public:
    void playerJump()
    {
        if(dbSpaceKey() && !spaceKeyPressed)
        {
            if(!jumping && touchingGround)
            {
                dbPlaySound(Sjump);
                spaceKeyPressed = jumping = true;
                touchingGround = false;
                jumpStartTimer = dbTimer();
            }
        }
        if(jumping)
        {
            if(dbTimer() - jumpStartTimer < 650)
                playerY = playerY - speedy;
            if(dbTimer() - jumpStartTimer > 720)
                jumping = false;
        }
        if(dbSpaceKey() == 0)
            spaceKeyPressed = false;
    }

    void Gravity()
    {
        if(Is_Mario_Alive == true)
        {
            if(!touchingGround)
            {
                if(!jumping)
                {
                    if(dbTimer() - touchingGroundTimer > 150) falling = true;
                    playerY = playerY + speedy;
                }
            }
            if(touchingGround)
            {
                if(jumping)jumping = false;
                if(falling)falling = false;
            }
        }
    }

    int MarioWalkerCollision()
    {
        for(int i=0; i<maxWalkers; i++)
        {
            if(dbSpriteExist(walkers[i].id) && walkers[i].isAlive && dbSpriteCollision(mario,walkers[i].id))
                return i;
        }
        return -1;
    }

    void AiPlayerCollisions()
    {
        int didMarioCollide = MarioWalkerCollision();
        if(didMarioCollide != -1 && Is_Mario_Alive)
        {
            if(dbSpriteY(walkers[didMarioCollide].id) - dbSpriteY(mario) >= 65 && !walkers[didMarioCollide].isDying)
            {
                if(dbSpriteX(mario) > dbSpriteX(walkers[didMarioCollide].id) + 65 || dbSpriteX(mario) < dbSpriteX(walkers[didMarioCollide].id) - 65)
                    myEvents.Take_Damage();
                else
                {
                    if(walkers[didMarioCollide].typeOf == GOOMBA)
                    {
                        playerY = playerY - speedy*5;
                        dbPlaySound(Sstomp);
                        enPhy.Kill_Goomba(didMarioCollide);
                        mHandling.Start_Animation(score100_1,SPRITE,dbSpriteX(walkers[didMarioCollide].id),dbSpriteY(walkers[didMarioCollide].id) - 65,9,a,80);
                    }
                }
            }
            else myEvents.Take_Damage();
        }
    }

    void StaticPlayerCollisions()
    {
        int lefttilenum = (playerX / tilesizex);
        int righttilenum = (playerX + (tilesizex-1)) / tilesizex;

        int toptilenum = (playerY /tilesizey);
        int bottomtilenum = ( ( playerY + ( tilesizey - 1 ) ) / (tilesizey) );

        int oldlefttilenum = ( oldx1 / tilesizex );
        int oldrighttilenum = ( ( oldx1 + ( tilesizex - 1 ) ) / tilesizex );

        int oldtoptilenum = ( oldy1 / tilesizey );
        int oldbottomtilenum = ( ( oldy1 + ( tilesizey - 1 ) ) / (tilesizey) );

        bool collidedWithSomething = false;
        int typeOfCollision = 0;

        int collidedWithX = 0;
        int collidedWithY = 0;
        if ( map[lefttilenum][oldtoptilenum] >= 10)
        {
            if(map[lefttilenum][oldtoptilenum] < 100)
            {
                playerX = oldx1;
                mapX = oldmapX;
            }
            collidedWithSomething = true;
            collidedWithX = lefttilenum;
            collidedWithY = oldtoptilenum;
        }

        if ( map[oldlefttilenum][toptilenum] >= 10)
        {
            if(map[oldlefttilenum][toptilenum] < 100)
            {
                playerY = oldy1;
                if(jumping == true)jumping = false;
            }
            typeOfCollision = bottom;
            collidedWithSomething = true;
            collidedWithX = oldlefttilenum;
            collidedWithY = toptilenum;
        }
        if ( map[righttilenum][oldtoptilenum] >= 10)
        {
            if(map[righttilenum][oldtoptilenum] < 100)
            {
                playerX = oldx1;
                mapX = oldmapX;
            }
            collidedWithSomething = true;
            collidedWithX = righttilenum;
            collidedWithY = oldtoptilenum;
        }

        if ( map[oldrighttilenum][toptilenum] >= 10)
        {
            if(map[oldrighttilenum][toptilenum] < 100)
            {
                playerY = oldy1;
                if(jumping == true)jumping = false;
            }
            typeOfCollision = bottom;
            collidedWithSomething = true;
            collidedWithX = oldrighttilenum;
            collidedWithY = toptilenum;
        }

        if ( map[lefttilenum][oldbottomtilenum] >= 10)
        {
            if(map[lefttilenum][oldbottomtilenum] < 100)
            {
                playerX = oldx1;
                mapX = oldmapX;
            }
            collidedWithSomething = true;
            collidedWithX = lefttilenum;
            collidedWithY = oldbottomtilenum;
        }

        if ( map[oldlefttilenum][bottomtilenum] >= 10)
        {
            if(map[oldlefttilenum][bottomtilenum] < 100)
            {
                if(jumping == true)jumping = false;
                touchingGround = true;
                touchingGroundTimer = dbTimer();
                playerY = oldy1;
            }
            typeOfCollision = top;
            collidedWithSomething = true;
            collidedWithX = oldlefttilenum;
            collidedWithY = bottomtilenum;
        }


        if ( map[righttilenum][oldbottomtilenum] >= 10)
        {
            if(map[righttilenum][oldbottomtilenum] < 100)
            {
                playerX = oldx1;
                mapX = oldmapX;
            }
            collidedWithSomething = true;
            collidedWithX = righttilenum;
            collidedWithY = oldbottomtilenum;
        }

        if ( map[oldrighttilenum][bottomtilenum] >= 10)
        {
            if(map[oldrighttilenum][bottomtilenum] < 100)
            {
                touchingGround = true;
                touchingGroundTimer = dbTimer();
                if(jumping == true)jumping = false;
                playerY = oldy1;
            }
            typeOfCollision = top;
            collidedWithSomething = true;
            collidedWithX = oldrighttilenum;
            collidedWithY = bottomtilenum;
        }
        if(mapX<60)
            mapX = 60;

        if ( map[oldrighttilenum][bottomtilenum] < 10 && map[oldlefttilenum][bottomtilenum] < 10)
            touchingGround = false;

        myEvents.Item_Pick_Up(collidedWithX, collidedWithY, typeOfCollision);

        enemies.Collide_With_Piranha(collidedWithX, collidedWithY, typeOfCollision);
    }

    void controlCharacter()
    {
        if(Is_Mario_Alive==true)
        {
            for(int i=0; i<maxWalkers; i++)
            {
                if(walkers[i].isAlive)
                {
                    if(Is_Mario_Alive==true)
                    {
                        walkers[i].oldx=walkers[i].x;
                        walkers[i].oldy=walkers[i].y;
                    }
                }
            }
            oldmapX=mapX;
            oldmapY=mapY;

            oldx1=playerX;
            oldy1=playerY;
            if(stopMovement==false)
            {
                if(dbLeftKey()==1)
                {
                    if(walkingRight==true && speedx>0)
                    {
                        speedx=0;
                        walkingRight=false;
                    }
                    else if(dbTimer()-speedTimer>150)
                    {
                        speedTimer=dbTimer();
                        if(speedx<5)
                            speedx++;
                        walkingLeft=true;
                    }
                }
                if(dbRightKey()==1)
                {
                    if(walkingLeft==true && speedx>0)
                    {
                        speedx=0;
                        walkingLeft=false;
                    }
                    else if(dbTimer()-speedTimer>150)
                    {
                        speedTimer=dbTimer();
                        if(speedx<5)
                            speedx++;
                        walkingRight=true;
                    }
                }
                if(walkingRight==true)
                {
                    if(mapX<playerX-(screenCenterX-5) && mapX>playerX-(screenCenterX+5))
                        mapX=mapX+speedx;
                    playerX=playerX+speedx;
                }

                if(walkingLeft==true)
                {
                    if(mapX<playerX-(screenCenterX-5) && mapX>playerX-(screenCenterX+5))
                        mapX=mapX-speedx;
                    playerX=playerX-speedx;
                }
                if(dbLeftKey()==0)
                {
                    if(walkingLeft==true && speedx>0)
                    {
                        if(dbTimer()-speedTimer>150)
                        {
                            speedTimer=dbTimer();
                            if(speedx>0)
                                speedx--;
                        }
                        if(speedx==0)
                            walkingLeft=false;
                    }
                }
                if(dbRightKey()==0)
                {
                    if(walkingRight==true && speedx>0)
                    {
                        if(dbTimer()-speedTimer>150)
                        {
                            speedTimer=dbTimer();
                            if(speedx>0)
                                speedx--;
                        }
                        if(speedx==0)
                            walkingRight=false;
                    }
                }
                if(dbRightKey() && dbLeftKey())
                {
                    walkingRight = false;
                    walkingLeft = false;
                }

                if(dbShiftKey())
                {
                    if(speedx>4)
                    {
                        if(dbRightKey() || dbLeftKey())
                        {
                            if(touchingGround)
                                speedx+=1;
                        }
                    }
                }
                else
                {
                    if(speedx > 4)
                        speedx -= 1;
                }
                if(speedx > 6)
                    speedx = 6;
            }
        }
    }

};


