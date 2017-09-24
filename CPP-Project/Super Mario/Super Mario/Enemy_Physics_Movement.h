#pragma once

class Enemy_Physics_Movement
{
public:
    void Change_Direction(int element)
    {
        if(walkers[element].walkingDirection == LEFT)
            walkers[element].walkingDirection = RIGHT;
        else
            walkers[element].walkingDirection = LEFT;
    }
    int Colliding_With_Walker(int element)
    {
        for(int i=0; i<maxWalkers; i++)
        {
            if(walkers[i].isAlive && dbSpriteExist(walkers[i].id) && walkers[i].id != walkers[element].id)
            {
                if(dbSpriteCollision(walkers[element].id,walkers[i].id))
                    return i;
            }
        }
        return -1;
    }
    void Enemy_Flash(int TimeToFlash, int CharacterToFlash)
    {
        int Time_space = ceil((double)TimeToFlash / 100.0);
        if(Time_space & 1)
            dbHideSprite(CharacterToFlash);
        else dbShowSprite(CharacterToFlash);

    }

    void Goomba_Animations()
    {
        for(int i=0; i<maxWalkers; i++)
        {
            if(dbSpriteExist(walkers[i].id))
            {
                if(walkers[i].isAlive)
                {
                    dbSprite(walkers[i].id,walkers[i].x - mapX,walkers[i].y,G+walkers[i].currentFrame);
                    if(walkers[i].walking)
                    {
                        if(dbTimer()-walkers[i].walkingTimer > 90)
                        {
                            walkers[i].walkingTimer = dbTimer();
                            if(walkers[i].currentFrame>5)
                                walkers[i].currentFrame = 0;
                            walkers[i].currentFrame++;
                        }
                    }
                }
                else if(walkers[i].isDying)
                {
                    dbSprite(walkers[i].id,walkers[i].x - mapX,walkers[i].y - mapY,G+7);
                    int TimeToFlash = dbTimer() - walkers[i].walkingTimer;
                    Enemy_Flash(TimeToFlash,walkers[i].id);
                    if(TimeToFlash > 1150)
                    {
                        walkers[i].isDying = false;
                        dbHideSprite(walkers[i].id);
                    }
                }
            }
        }
    }

    void Gravity()
    {
        for(int i=0; i<maxWalkers; i++)
        {
            if(walkers[i].isAlive)
            {
                if(!walkers[i].touchingGround)
                {
                    walkers[i].oldy = walkers[i].y;
                    walkers[i].y = walkers[i].y + speedy;
                    Enemy_Collision_Checker(i);
                }
            }
        }
    }

    bool Enemy_Collision_Checker(int element)
    {
        int collidedWithX = 0;
        int collidedWithY = 0;

        int lefttilenum = int( walkers[element].x / tilesizex );
        int righttilenum = int( ( walkers[element].x + ( tilesizex - 1 ) ) / tilesizex );

        int toptilenum = int( walkers[element].y / tilesizey );
        int bottomtilenum = int( ( walkers[element].y + ( tilesizey - 1 ) ) / (tilesizey) );

        int oldlefttilenum = int( walkers[element].oldx / tilesizex );
        int oldrighttilenum = int( ( walkers[element].oldx + ( tilesizex - 1 ) ) / tilesizex );


        int oldtoptilenum = int( walkers[element].oldy / tilesizey );
        int oldbottomtilenum = int( ( walkers[element].oldy + ( tilesizey - 1 ) ) / (tilesizey) );

        bool wasThereACollision = false;

        if ( map[lefttilenum][oldtoptilenum] >= 10)
        {
            if(map[lefttilenum][oldtoptilenum] < 100)
            {
                walkers[element].x = walkers[element].oldx;
                wasThereACollision = true;
            }
            collidedWithX = lefttilenum;
            collidedWithY = oldtoptilenum;
        }

        if ( map[oldlefttilenum][toptilenum] >= 10)
        {
            if(map[oldlefttilenum][toptilenum] < 100)
            {
                walkers[element].y = walkers[element].oldy;
                wasThereACollision = true;
            }
            collidedWithX = oldlefttilenum;
            collidedWithY = toptilenum;
        }
        if ( map[righttilenum][oldtoptilenum] >= 10)
        {
            if(map[righttilenum][oldtoptilenum] < 100)
            {
                walkers[element].x = walkers[element].oldx;
                wasThereACollision = true;
            }
            collidedWithX = righttilenum;
            collidedWithY = oldtoptilenum;
        }

        if ( map[oldrighttilenum][toptilenum] >= 10)
        {
            if(map[oldrighttilenum][toptilenum] < 100)
            {
                walkers[element].y = walkers[element].oldy;
                wasThereACollision = true;
            }
            collidedWithX = oldrighttilenum;
            collidedWithY = toptilenum;
        }

        if ( map[lefttilenum][oldbottomtilenum] >= 10)
        {
            if(map[lefttilenum][oldbottomtilenum] < 100)
            {
                walkers[element].x = walkers[element].oldx;
                wasThereACollision = true;
            }
            collidedWithX = lefttilenum;
            collidedWithY = oldbottomtilenum;
        }

        if ( map[oldlefttilenum][bottomtilenum] >= 10)
        {
            if(map[oldlefttilenum][bottomtilenum] < 100)
            {
                walkers[element].touchingGround = true;
                walkers[element].y = walkers[element].oldy;
                wasThereACollision = true;
            }
            collidedWithX = oldlefttilenum;
            collidedWithY = bottomtilenum;
        }

        if ( map[righttilenum][oldbottomtilenum] >= 10)
        {
            if(map[righttilenum][oldbottomtilenum] < 100)
            {
                walkers[element].x = walkers[element].oldx;
                wasThereACollision = true;
            }
            collidedWithX = righttilenum;
            collidedWithY = oldbottomtilenum;
        }

        if ( map[oldrighttilenum][bottomtilenum] >= 10)
        {
            if(map[oldrighttilenum][bottomtilenum] < 100)
            {
                walkers[element].touchingGround = true;
                walkers[element].y = walkers[element].oldy;
                wasThereACollision = true;
            }
            collidedWithX = oldrighttilenum;
            collidedWithY = bottomtilenum;
        }

        if ( map[oldrighttilenum][bottomtilenum] < 10 && map[oldlefttilenum][bottomtilenum] < 10)
            walkers[element].touchingGround = false;
        if(wasThereACollision)
        {
            Auto_Kill(collidedWithX, collidedWithY, element);
            return true;
        }
        return false;
    }

    void Collision_Resolution()
    {
        bool thereWasACollision = false;
        for(int i=0; i<maxWalkers; i++)
        {
            if(dbSpriteExist(walkers[i].id))
            {
                if(walkers[i].isAlive)
                {
                    if(walkers[i].touchingGround)
                    {
                        walkers[i].oldx = walkers[i].x;
                        if(walkers[i].walkingDirection == LEFT)
                        {
                            if(dbSpriteMirrored(walkers[i].id) == 0)
                                dbMirrorSprite(walkers[i].id);
                            walkers[i].x -= speedxEN;
                        }
                        else if(walkers[i].walkingDirection == RIGHT)
                        {
                            if(dbSpriteMirrored(walkers[i].id) == 1)
                                dbMirrorSprite(walkers[i].id);
                            walkers[i].x += speedxEN;
                        }
                        if(Enemy_Collision_Checker(i))
                        {
                            walkers[i].x = walkers[i].oldx;
                            Change_Direction(i);
                        }
                        int collidedAgainstSomeone = Colliding_With_Walker(i);
                        if(collidedAgainstSomeone != -1)
                        {
                            if(dbTimer() - walkers[i].CollisionTimer < 20)
                            {
                                if(walkers[collidedAgainstSomeone].id == walkers[i].LastEnemyCollided)
                                    thereWasACollision = false;
                            }
                            else
                                thereWasACollision = true;

                            if(thereWasACollision)
                            {
                                walkers[i].x = walkers[i].oldx;
                                Change_Direction(i);
                                Change_Direction(collidedAgainstSomeone);
                                walkers[i].LastEnemyCollided = walkers[collidedAgainstSomeone].id;
                                walkers[i].CollisionTimer = dbTimer();
                                walkers[collidedAgainstSomeone].LastEnemyCollided = walkers[i].id;
                                walkers[collidedAgainstSomeone].CollisionTimer = dbTimer();
                            }
                        }
                    }
                }
            }
        }
    }
    void Auto_Kill(int arrX, int arrY, int id)
    {
        if(map[arrX][arrY] == d)
        {
            if(walkers[id].typeOf == GOOMBA)
            {
                walkers[id].isAlive = walkers[id].walking = false;
                dbHideSprite(walkers[id].id);
            }
        }
    }
    void Kill_Goomba(int ElementToKill)
    {
        walkers[ElementToKill].isAlive = false;
        walkers[ElementToKill].walking = false;
        walkers[ElementToKill].isDying = true;
        walkers[ElementToKill].walkingTimer = dbTimer();
    }


};
