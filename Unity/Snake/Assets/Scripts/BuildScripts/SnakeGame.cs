using System.Collections;
using UnityEngine;

public class SnakeGame : MonoBehaviour 
{
	private static SnakeGame instance = null;

	public int gameScore = 0;
	public int gameLives = 3;
	public int scoreMultiplier = 100;

	public static SnakeGame Instance
	{
		get
		{
			if(instance == null)
				instance = new GameObject("SnakeGame").AddComponent<SnakeGame>();
			
			return instance;
		}
	}

	public void OnApplicationQuit()
	{
		DestroyInstance();
	}

	public void DestroyInstance()
	{
		print("Snake Game Instance Destroyed");

		instance = null;
	}

	public void UpdateScore(int additive)
	{
		gameScore += additive * scoreMultiplier;

		Score.Instance.UpdateScoreText(gameScore.ToString());
	}

	public void UpdateLives(int additive)
	{
		gameLives += additive;

		gameLives = Mathf.Clamp(gameLives, 0, gameLives);

		Lives.Instance.UpdateLivesText(gameLives.ToString());
	}

	public void Initialize()
	{
		print("SnakeGame Initialized");

		transform.position = Vector3.zero;
		transform.rotation = Quaternion.identity;
		transform.localScale = Vector3.one;

		gameScore = 0;
		gameLives = 3;
		scoreMultiplier = 100;

		UpdateScore(0);

		UpdateLives(0);
	}
}
