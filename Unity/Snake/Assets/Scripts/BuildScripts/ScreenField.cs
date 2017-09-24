using System.Collections;
using UnityEngine;

public class ScreenField : MonoBehaviour 
{
	private static ScreenField instance = null;

	public static ScreenField Instance
	{
		get
		{
			if(instance == null)
				instance = new GameObject("ScreenField").AddComponent<ScreenField>();
			
			return instance;
		}
	}

	public void OnApplicationQuit()
	{
		DestroyInstance();
	}

	public void DestroyInstance()
	{
		print("Scree Field Instance Destroyed");

		instance = null;
	}

	public void Initialize()
	{
		print("ScreenField Initialized");

		transform.position = Vector3.zero;
		transform.rotation = Quaternion.identity;
		transform.localScale = new Vector3(0.01f, 0.01f, 1.0f);

		GUITexture gameScreenBackground = gameObject.AddComponent<GUITexture>();

		Texture2D gameTexture = TextureHelper.Create1x1BlackTexture();

		gameScreenBackground.texture = gameTexture;
		gameScreenBackground.pixelInset = new Rect(22, 84, 980, 600);
	}
}
