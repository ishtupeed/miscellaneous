using System.Collections;
using UnityEngine;

public class GameManager : MonoBehaviour 
{
	void Start()
	{
		ScreenBorder.Instance.Initialize();
		
		ScreenField.Instance.Initialize();

		Score.Instance.Initialize();

		Lives.Instance.Initialize();

		SnakeGame.Instance.Initialize();

        Snake.Instance.Initialize();

        Food.Instance.Initialize();

        ScreenDeath.Instance.Initialize();
	}
}
