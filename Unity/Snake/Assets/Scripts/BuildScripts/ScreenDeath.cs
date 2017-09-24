using System.Collections;
using UnityEngine;

public class ScreenDeath : MonoBehaviour
{
	private static ScreenDeath instance = null;
	private GUITexture deathScreenBackground;
	private Color deathColor;
	private Color invisColor;

	public static ScreenDeath Instance
	{
		get
		{
			if(instance == null)
				instance = new GameObject("ScreenDeath").AddComponent<ScreenDeath>();
			
			return instance;
		}
	}

	public void OnApplicationQuit()
	{
		DestroyInstance();
	}

	public void DestroyInstance()
	{
		print("Screen Death Instance Destroyed");

		instance = null;
	}

	public IEnumerator FlashDeathScreen()
	{
		deathScreenBackground.color = deathColor;

		yield return new WaitForSeconds(0.1f);

		deathScreenBackground.color = invisColor;

		yield return new WaitForSeconds(0.1f);

		deathScreenBackground.color = deathColor;

		yield return new WaitForSeconds(0.1f);

		deathScreenBackground.color = invisColor;

		yield return new WaitForSeconds(0.1f);

		deathScreenBackground.color = deathColor;

		yield return new WaitForSeconds(0.1f);

		deathScreenBackground.color = invisColor;

		yield return new WaitForSeconds(0.1f);

		deathScreenBackground.color = deathColor;

		yield return new WaitForSeconds(0.1f);

		deathScreenBackground.color = invisColor;

		yield return new WaitForSeconds(0.1f);
	}

	public void Initialize()
	{
		print("ScreenDeath Intialized");

		transform.position = new Vector3(0, 0, 2);
		transform.rotation = Quaternion.identity;
		transform.localScale = new Vector3(0.01f, 0.01f, 1.0f);

		deathScreenBackground = gameObject.AddComponent<GUITexture>();

		Texture2D deathTexture = TextureHelper.Create1x1Texture(Color.red);

		deathScreenBackground.texture = deathTexture;

        invisColor = new Color(deathScreenBackground.color.r, deathScreenBackground.color.g, deathScreenBackground.color.b, 0.0f);

        deathColor = new Color(deathScreenBackground.color.r, deathScreenBackground.color.g, deathScreenBackground.color.b, 0.3f);

		deathScreenBackground.pixelInset = new Rect(0, 0, 1024, 768);

		deathScreenBackground.color = invisColor;
	}
}
