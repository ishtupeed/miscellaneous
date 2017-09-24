using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class EnemyDestructionDelegate : MonoBehaviour 
{
	public delegate void EnemyDelegate (GameObject enemy);
	public EnemyDelegate enemyDelegate;
	
	void OnDestroy()
	{
        GameManagerBehavior.destroyedEnemies++;
		if(enemyDelegate != null)
			enemyDelegate(gameObject);
	}
}
