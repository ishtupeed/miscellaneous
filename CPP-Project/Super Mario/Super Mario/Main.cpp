#include "DarkGDK.h"
#include "Definitions.h"
#include "Map_Loading.h"
#include "Physics_Movement.h"

Physics_Movement phy;

void MarioPhysics()
{
	phy.controlCharacter();
	phy.Gravity();
	phy.StaticPlayerCollisions();
	phy.AiPlayerCollisions();
	phy.playerJump();
}

void EnemyPhysics()
{
	enPhy.Collision_Resolution();
	enPhy.Gravity();
	enPhy.Goomba_Animations();
}

void Mario_Operations()
{
	myEvents.Mario_Animation();
	myEvents.Game_Timer();
	myEvents.Death_Flash();
	myEvents.Damage_Flash();
	myEvents.Finish();
}

void EnemyAnim()
{
	enemy.Activate_Enemies();
	enemy.Keep_Piranha_Down();
}

void DarkGDK()
{
    dbSyncOn();
    dbSyncRate(60);
	dbSetDisplayMode(800, 600, 32);
	dbLoadSound("MainMenu.wav", main);
	dbLoadImage("Main.png", MainMenu);
	dbPlaySound(main);	
	dbPasteImage(MainMenu, 0, 0);
	dbMaximizeWindow();
	dbWaitKey();
	dbStopSound(main);
	dbBackdropOn();
	dbBackdropColor(dbRGB(0, 255, 0));
	load.Load_Images();
	load.Load_Current_Level(level);
	load.Load_Sounds();
	myEvents.reset_Mario();
	dbHideMouse();

    while(LoopGDK())
	{
		mapping.playSounds();
		mapping.displayLevel();
		mHandling.Level_Related_Animations();
		EnemyAnim();
		mapping.displayInformation();
		MarioPhysics();
		Mario_Operations();
		EnemyPhysics();
		dbSync();
	}
    return;
}
