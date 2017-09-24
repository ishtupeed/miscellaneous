#define normalMario 1
#define FireMario 2
#define bottom 1
#define top 2
#define M 0
#define a 1
#define r 10
#define g 11
#define B 12
#define p1 13
#define p2 14
#define p3 15
#define p4 16
#define b 20
#define fr 150
#define fl 151
#define P1 152
#define P2 153
#define P3 154
#define P4 155
#define P5 156
#define P5l 157
#define P6 158
#define q 40
#define qC 32
#define qF 33
#define e 34
#define k 35
#define d 70
#define c 9
#define L 99
#define C 100
#define SA 105
#define f 300
#define fB 115
#define fT 116
#define gM 117
#define maxAnimations 20
#define EPl 170
#define EPr 200
#define maxPiranhas 10
#define maxWalkers 30
#define G 230
#define GOOMBA 1
#define TURTLE 2
#define LEFT 1
#define RIGHT 2
#define SPRITE 1
#define IMAGE 2

int currentWalker = 1;
int speedxEN = 2;
int numoftilesx = 65;
int numoftilesy = 65;
int tilesizex = 65;
int tilesizey = 65;
int speedx = 5;
int speedy = 5;
int screenCenterX = 380;
int screenCenterY = 280;
int playerX = screenCenterX;
int playerY = screenCenterY;
int mapX = 0;
int mapY = 0;
int oldmapX = 0;
int oldmapY = 0;
int oldx1 = 0;
int oldy1 = 0;
const int COLS = 170;
const int ROWS = 12;
int level = 1;
int MainMenu=721;
int main=720;
int ins=723;

int mario = 2;
int marioWalk = 3;
int marioJump = 4;

int FireMarioStill = 5;
int FireMarioWalk = 6;
int FireMarioJump = 7;

int walkingTimer = dbTimer();
int jumpStartTimer = dbTimer();
const float GRAVITY = 0.2;
bool jumping = false;
bool falling = false;
bool touchingGround = false;
int touchingGroundTimer = dbTimer();
int currentMario;
bool walkingRight = false;
bool walkingLeft = false;
bool takingDamage = false;
bool marioSpawned = false;
bool CheckPointReached = false;
bool TEMPGombaSpawned = false;

bool EndGame = false;
int speedTimer = dbTimer();


int qBoxAnim = 0;
int questionBoxTimer = dbTimer();

int coinAnim = 0;
int coinTimer = dbTimer();


bool spaceKeyPressed = false;
int map[COLS][ROWS];


int SmainTheme = 1;
int ScoinPickup = 2;
int SFlowerAppear = 3;
int Sjump = 4;
int Sdeath = 5;
int Sbrick = 6;
int Sbump = 7;
int SFlowerPickup = 8;
int SnewLife = 11;
int SFinish = 12;
int StimeWarning = 13;
int SlevelClear = 14;
int Spowerup = 15;
int SplantCrunch = 16;
int ScheckPoint = 93;
int Sstomp = 17;
int SPipeAndDamage = 18;
int score200_1 = 71;
int score100_1 = 270;
int score1000_1 = 82;
int check_1 = 120;
int dust_1 = 133;
int _1up_1 = 245;
int _1upText_1 = 280;


int coins = 0;
int time;
int gameTimer = dbTimer();
int score;
int lifes;
int checkPointX;
int checkPointY;
int Save_PlayerX;
int Save_PlayerY;
int levelOver = 0;
bool stopMovement = false;


int deathFlash;
int damageFlashes;
bool Is_Mario_Alive = false;
bool dying = false;
int flashTimer = dbTimer();

typedef struct extraAnimations
{
	bool busy;
	int SetImage;
	int frames;
	int timer;
	int id;
	int typeOf;
	int y;
	int x;
	int HighestFrame;
	int OriginalMapX;
	int FinalFrame;
	int animDelay;
} extraAnimations;

typedef struct piranhaAI
{
	int id;
	int typeOf;
	bool tooClose;
	bool isAlive;
	int plantTimer;
	int plantLoopTimer;
	int plantAnim;
	int distance;
	int ReferenceJ;
	int ReferenceI;

} piranhaAI;


typedef struct walkingEN
{
	int id;
	int typeOf;
	bool isAlive;
	bool touchingGround;
	bool walking;
	bool isDying;
	int walkingDirection;
	int walkingTimer;
	int x;
	int y;
	int oldx;
	int oldy;
	int currentFrame;
	int CollisionTimer;
	int LastEnemyCollided;
	bool ASleep;

} walkingEN;

walkingEN walkers[maxWalkers];
piranhaAI piranhaPlants[maxPiranhas*2];
extraAnimations anims[maxAnimations];


