using System.Collections;
using UnityEngine;

public class ScreenBorder : MonoBehaviour 
{
	private static ScreenBorder instance = null;

	public static ScreenBorder Instance
	{
		get
		{
			if(instance == null)
				instance = new GameObject("ScreenBorder").AddComponent<ScreenBorder>();
			return instance;
		}
	}

	public void OnApplicationQuit()
	{
		DestroyInstance();
	}

	public void DestroyInstance()
	{
		print("Screen Border Instance Destroyed");

		instance = null;
	}

	public void Initialize()
	{
		print("ScreenBorder Initialized");
		
		transform.position = new Vector3(0, 0, -1);
		transform.rotation = Quaternion.identity;
		transform.localScale = new Vector3(0.01f, 0.01f, 1.0f);

		GUITexture gameBorderBackground = gameObject.AddComponent<GUITexture>();

		Texture2D gameBorderTexture = TextureHelper.Create1x1Texture(Color.gray);

		gameBorderBackground.texture = gameBorderTexture;
		gameBorderBackground.pixelInset = new Rect(0, 0, 1024, 768);
	}
}
