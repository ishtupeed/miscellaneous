using System.Collections;
using UnityEngine;

public class Score : MonoBehaviour 
{
	public static Score instance = null;
	private GUIText gameScoreText;

	public static Score Instance
	{
		get
		{
			if(instance == null)
				instance = new GameObject("GameScore").AddComponent<Score>();
			
			return instance;
		}
	}

	public void DestroyInstance()
	{
		print("Score Instance Destroyed");

		instance = null;
	}

	public void UpdateScoreText(string appendString)
	{
		gameScoreText.text = "";
		gameScoreText.text = "Score: " + appendString;
	}

	public void Initialize()
	{
		print("Score Initialized");

		transform.position = Vector3.zero;
		transform.rotation = Quaternion.identity;
		transform.localScale = Vector3.one;

		gameScoreText = gameObject.AddComponent<GUIText>();

		gameScoreText.text = "Score: ";

		gameScoreText.pixelOffset = new Vector2(10, 758);
	}
}
